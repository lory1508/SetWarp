package net.staticcraft.setwarp;

import net.staticcraft.setwarp.commands.ListWarps;
import net.staticcraft.setwarp.commands.RemoveWarp;
import net.staticcraft.setwarp.commands.SetWarpCommand;
import net.staticcraft.setwarp.commands.WarpCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SetWarp extends JavaPlugin {

    private static SetWarp plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().addDefault("MAX_ALLOWED_WARPS", 5);
        getConfig().options().copyDefaults(true);
        saveConfig();
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new RemoveWarp());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("listwarps").setExecutor(new ListWarps());
    }

    public static SetWarp getPlugin() {
        return plugin;
    }
}
