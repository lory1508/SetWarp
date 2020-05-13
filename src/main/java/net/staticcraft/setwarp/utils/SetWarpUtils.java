package net.staticcraft.setwarp.utils;

import net.staticcraft.setwarp.SetWarp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetWarpUtils {

    private static File dataFolder = SetWarp.getPlugin().getDataFolder();

    public static void setPWarp(Player player, String name) {

        YamlConfiguration data = YamlConfiguration.loadConfiguration(getPlayerWarpsFile(player));

        if (data.getConfigurationSection("data") == null) {
            data.set("data.account", player.getName());
            try {
                data.save(getPlayerWarpsFile(player));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int size = data.getConfigurationSection("data").getKeys(false).size() - 1;

        if (size < getMaxAllowedWarps()) {
            data.set("data." + name.toLowerCase() + ".World", player.getLocation().getWorld().getName());
            data.set("data." + name.toLowerCase() + ".X", player.getLocation().getX());
            data.set("data." + name.toLowerCase() + ".Y", player.getLocation().getY());
            data.set("data." + name.toLowerCase() + ".Z", player.getLocation().getZ());
            data.set("data." + name.toLowerCase() + ".Yaw", player.getLocation().getYaw());
            data.set("data." + name.toLowerCase() + ".Pitch", player.getLocation().getPitch());
            player.sendMessage(FormattedStrings.CHAT_SUCCESS_PREFIX() + FormattedStrings.SET_WARP_MESSAGE(name.toLowerCase()));
        } else {
            // print error to player.
            player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.WARP_LIMIT_MESSAGE(getMaxAllowedWarps()));
        }

        try {
            data.save(getPlayerWarpsFile(player));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void removePWarp(Player player, String name) {
        YamlConfiguration data = YamlConfiguration.loadConfiguration(getPlayerWarpsFile(player));

        if (data.get("data." + name.toLowerCase()) != null) {
            data.set("data." + name.toLowerCase(), null);
            player.sendMessage(FormattedStrings.CHAT_SUCCESS_PREFIX() + FormattedStrings.DELETE_WARP_MESSAGE(name.toLowerCase()));
            try {
                data.save(getPlayerWarpsFile(player));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (data.get("data." + name.toLowerCase()) == null) {
            player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.MISSING_WARP_ERROR());
        }

    }

    public static Location getPWarp(Player player, String name) {
        YamlConfiguration data = YamlConfiguration.loadConfiguration(getPlayerWarpsFile(player));

        if (data.get("data." + name.toLowerCase()) == null) {
            return null;
        } else {
            return new Location(
                    Bukkit.getWorld(data.getString("data." + name.toLowerCase() + ".World")),
                    data.getDouble("data." + name.toLowerCase() + ".X"),
                    data.getDouble("data." + name.toLowerCase() + ".Y"),
                    data.getDouble("data." + name.toLowerCase() + ".Z"),
                    (float) data.getDouble("data." + name.toLowerCase() + ".Yaw"),
                    (float) data.getDouble("data." + name.toLowerCase() + ".Pitch")
            );
        }
    }

    public static List<String> listWarpsAutocomplete(Player player) {

        YamlConfiguration data = YamlConfiguration.loadConfiguration(getPlayerWarpsFile(player));

        if (data.getConfigurationSection("data") != null) {
            Set<String> warps = data.getConfigurationSection("data").getKeys(false);
            warps.remove("account");
            
            List<String> warpsList = new ArrayList<>(warps);
            if (!warpsList.isEmpty()) {
                return warpsList;
            } else {
                player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.CREATE_WARP_HELP());
            }
        } else {
            player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.NO_WARPS_ERROR());
        }
        return null;
    }
    
    public static void listPWarps(Player player) {

        YamlConfiguration data = YamlConfiguration.loadConfiguration(getPlayerWarpsFile(player));

        if (data.getConfigurationSection("data") != null) {
            Set<String> warps = data.getConfigurationSection("data").getKeys(false);
            warps.remove("account");

            String[] warpsArray = warps.toArray(new String[warps.size()]);

            int index = 0;
            for (String str : warps) {
                warpsArray[index++] = str;
            }

            String formattedList = "";
            for (String str : warpsArray) {
                formattedList = formattedList.concat(ChatColor.GOLD + "  - " + ChatColor.GREEN + str + "\n");
            }

            String coolStartLine = ChatColor.GOLD + "*" + ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "*" + ChatColor.YELLOW + ChatColor.BOLD + " Your Warps " + ChatColor.GOLD + "*" + ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "*";
            String coolEndLine = ChatColor.GOLD + "*" + ChatColor.YELLOW + " ------------------------ " + ChatColor.GOLD + "*";

            if (warpsArray.length > 0) {
                player.sendMessage(coolStartLine + "\n" + ChatColor.GREEN + formattedList + "\n" + coolEndLine);
            } else {
                player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.CREATE_WARP_HELP());
            }
        } else {
            player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.NO_WARPS_ERROR());
        }


    }

    public static int getMaxAllowedWarps() {
        return SetWarp.getPlugin().getConfig().getInt("MAX_ALLOWED_WARPS");
    }

    private static File getPlayerWarpsFile(Player player) {
        File file = new File(dataFolder + File.separator + "data", player.getUniqueId().toString() + ".yml");

        if (!file.exists()) {
            YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

            try {
                data.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
