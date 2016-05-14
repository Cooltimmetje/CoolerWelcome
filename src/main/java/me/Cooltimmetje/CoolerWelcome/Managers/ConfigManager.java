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

package me.Cooltimmetje.CoolerWelcome.Managers;

import me.Cooltimmetje.CoolerWelcome.Listeners.JoinEvent;
import me.Cooltimmetje.CoolerWelcome.Main;
import org.bukkit.configuration.Configuration;

import java.io.File;
import java.util.List;

/**
 * Created by Cooltimmetje on 5/13/2016 at 9:40 PM.
 */
public class ConfigManager {

    static Configuration config = Main.getPlugin().getConfig();

    public static void onEnableConfig() {
        Main.getPlugin().saveDefaultConfig();

        if(config.getInt("DoNotTouch.IfYouDoConfigWillReset") != Main.configVersion || !config.contains("DoNotTouch.IfYouDoConfigWillReset")){
            File configFile = new File(Main.getPlugin().getDataFolder(), "config.yml");
            configFile.delete();
            Main.getPlugin().saveDefaultConfig();
            Main.getPlugin().reloadConfig();
        }

        List<String> Players = config.getStringList("data.users");

        for (String s : Players) {
            JoinEvent.users.add(s);
        }
        JoinEvent.amount = config.getInt("data.amount");
    }

    public static void writeConfig(){
        config.set("data.amount", JoinEvent.amount);
        config.set("data.users", JoinEvent.users);
        Main.getPlugin().saveConfig();
}

}
