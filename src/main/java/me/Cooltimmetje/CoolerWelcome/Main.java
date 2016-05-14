/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See: http://choosealicense.com/no-license/
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes. No need to ask permission, credits would be nice, but not required.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
 */

package me.Cooltimmetje.CoolerWelcome;

import me.Cooltimmetje.CoolerWelcome.Listeners.JoinEvent;
import org.bukkit.Bukkit;
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

        getLogger().info("Enabling plugin... Please wait...");
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
