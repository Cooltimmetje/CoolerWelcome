package me.Cooltimmetje.CoolerWelcome;

import me.Cooltimmetje.CoolerWelcome.Listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Cooltimmetje on 5/13/2016 at 9:32 PM.
 */
public class Main extends JavaPlugin {

    private static Plugin plugin;
    private long startTime;
    public static int configVersion = 1;

    public void onEnable(){
        startTime = System.currentTimeMillis();

        getLogger().info("Enabling plugin... Please wait.");
        getLogger().info("Running Java Version: " + System.getProperty("java.version"));

        plugin = this;
        this.saveDefaultConfig();

        getLogger().info("Registering Listeners...");
        registerListeners(this, new JoinEvent());

        getLogger().info("Plugin ready! (Loadtime: " + getLoad() + "ms)");
    }

    public void onDisable(){
        plugin = null;
    }

    private void registerListeners(Plugin plugin, Listener... listeners){
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
            getLogger().info("Registered listener: " + listener.toString().replace("me.Cooltimmetje.CoolerWelcome."," ").trim());
        }
    }

    private long getLoad(){
        return System.currentTimeMillis() - startTime;
    }

    public static Plugin getPlugin(){
        return plugin;
    }
}
