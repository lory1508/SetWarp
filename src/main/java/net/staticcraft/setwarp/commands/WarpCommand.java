package net.staticcraft.setwarp.commands;

import net.staticcraft.setwarp.SetWarp;
import net.staticcraft.setwarp.utils.FormattedStrings;
import net.staticcraft.setwarp.utils.SetWarpUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SetWarp.getPlugin().getLogger().log(Level.WARNING, "Sorry, but only in-game players may use this command!");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equals("warp")) {
            if (!player.hasPermission("setwarp.warp")) {
                player.sendMessage(FormattedStrings.getErrorPrefix() + FormattedStrings.PERMISSION_ERROR());
                return false;
            } else {
                if (args.length == 0) {
                    player.sendMessage(FormattedStrings.getErrorPrefix() + ChatColor.RED + "Usage: /warp <name>");
                    return false;
                }
                if (args.length == 1) {
                    Location warp = SetWarpUtils.getWarp(player, args[0]);
                    if (warp != null) {
                        player.teleport(warp);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        return false;
                    } else {
                        player.sendMessage(FormattedStrings.getErrorPrefix() + FormattedStrings.MISSING_WARP());
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
