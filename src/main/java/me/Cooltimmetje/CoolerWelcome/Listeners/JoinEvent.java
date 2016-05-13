package me.Cooltimmetje.CoolerWelcome.Listeners;

import me.Cooltimmetje.CoolerWelcome.Main;
import me.Cooltimmetje.CoolerWelcome.Managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cooltimmetje on 5/13/2016 at 9:44 PM.
 */
public class JoinEvent implements Listener {

    public static List<String> users = new ArrayList<>();
    public static int amount;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();

        if(users.contains(uuid)){
            return;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(amount);
            String amountString = sb.toString();
            Bukkit.broadcastMessage((Main.getPlugin().getConfig().get("settings.welcome_message").toString().replace("{0}", p.getName()).replace("{1}", amountString).replace('&', '§')));
            users.add(uuid);
            amount = amount + 1;
            ConfigManager.writeConfig();
        }
    }

}
