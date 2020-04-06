package net.staticcraft.setwarp.utils;

import net.staticcraft.setwarp.SetWarp;
import org.bukkit.ChatColor;

public class FormattedStrings {

    public static String CHAT_SUCCESS_PREFIX() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("CHAT_SUCCESS_PREFIX");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String CHAT_ERROR_PREFIX() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("CHAT_ERROR_PREFIX");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String PERMISSION_ERROR() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("PERMISSION_ERROR");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String MISSING_WARP() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("MISSING_WARP");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String SET_WARP_MESSAGE(String warp) {
        String oldStr = SetWarp.getPlugin().getConfig().getString("SET_WARP_MESSAGE");
        String replace = oldStr.replace("$WARP_NAME$", warp);
        return ChatColor.translateAlternateColorCodes('&', replace);
    }

    public static String DELETE_WARP_MESSAGE(String warp) {
        String oldStr = SetWarp.getPlugin().getConfig().getString("DELETE_WARP_MESSAGE");
        String replace = oldStr.replace("$WARP_NAME$", warp);
        return ChatColor.translateAlternateColorCodes('&', replace);
    }

    public static String WARP_LIMIT_MESSAGE(int limit) {
        String oldStr = SetWarp.getPlugin().getConfig().getString("WARP_LIMIT_MESSAGE");
        String replace = oldStr.replace("$WARP_LIMIT$", String.valueOf(limit));
        return ChatColor.translateAlternateColorCodes('&', replace);
    }

    public static String MISSING_WARP_ERROR() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("MISSING_WARP_ERROR");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String CREATE_WARP_HELP() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("CREATE_WARP_HELP");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

    public static String NO_WARPS_ERROR() {
        String oldStr = SetWarp.getPlugin().getConfig().getString("NO_WARPS_ERROR");
        return ChatColor.translateAlternateColorCodes('&', oldStr);
    }

}
