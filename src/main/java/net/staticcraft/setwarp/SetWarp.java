package net.staticcraft.setwarp;

import net.staticcraft.setwarp.commands.ListPWarpsCommand;
import net.staticcraft.setwarp.commands.PWarpCommand;
import net.staticcraft.setwarp.commands.RemovePWarpCommand;
import net.staticcraft.setwarp.commands.SetPWarpCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SetWarp extends JavaPlugin {

    private static SetWarp plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().addDefault("MAX_ALLOWED_WARPS", 5);
        getConfig().options().copyDefaults(true);
        saveConfig();
        getCommand("setpwarp").setExecutor(new SetPWarpCommand());
        getCommand("delpwarp").setExecutor(new RemovePWarpCommand());
        getCommand("pwarp").setExecutor(new PWarpCommand());
        getCommand("listpwarps").setExecutor(new ListPWarpsCommand());
    }

    public static SetWarp getPlugin() {
        return plugin;
    }
}
