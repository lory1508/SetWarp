package net.staticcraft.setwarp.commands;

import java.sql.Timestamp;
import java.util.HashMap;
import net.staticcraft.setwarp.SetWarp;
import net.staticcraft.setwarp.utils.FormattedStrings;
import net.staticcraft.setwarp.utils.SetWarpUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SetPWarpCommand implements CommandExecutor {
    
    private HashMap<Player, Timestamp> hmap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SetWarp.getPlugin().getLogger().log(Level.WARNING, "Sorry, but only in-game players may use this command!");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equals("setpwarp")) {
            if (!player.hasPermission("setwarp.setpwarp")) {
                player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.PERMISSION_ERROR());
                return false;
            } else {
                if (args.length == 0) {
                    player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + ChatColor.RED + "Usage: /setpwarp <name>");
                    return false;
                }
                if (args.length == 1) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    if(!hmap.containsKey(player)){
                        hmap.put(player, ts);
                    }
                    SetWarpUtils.setPWarp(player, args[0], hmap);
                    return false;
                }
            }
        }

        return false;
    }
}