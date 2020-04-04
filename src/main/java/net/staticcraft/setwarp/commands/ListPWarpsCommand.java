package net.staticcraft.setwarp.commands;

import net.staticcraft.setwarp.SetWarp;
import net.staticcraft.setwarp.utils.FormattedStrings;
import net.staticcraft.setwarp.utils.SetWarpUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ListPWarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            SetWarp.getPlugin().getLogger().log(Level.WARNING, "Sorry, but only in-game players may use this command!");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equals("listpwarps")) {
            if (!player.hasPermission("setwarp.listpwarps")) {
                player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + FormattedStrings.PERMISSION_ERROR());
                return false;
            } else {
                if (args.length > 0) {
                    player.sendMessage(FormattedStrings.CHAT_ERROR_PREFIX() + ChatColor.RED + "Usage: /listpwarps");
                    return false;
                } else {
                    SetWarpUtils.listPWarps(player);
                }
            }
        }

        return false;
    }
}
