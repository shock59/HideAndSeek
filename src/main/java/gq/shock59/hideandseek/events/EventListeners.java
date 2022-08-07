package gq.shock59.hideandseek.events;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.UUID;

public class EventListeners implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.getPlayer().sendMessage("Welcome to the server!");
        event.setJoinMessage(event.getPlayer().getName() + " joined the server");

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player victim && event.getDamager() instanceof Player attacker) {

            HideAndSeek plugin = HideAndSeek.getPlugin();
            ArrayList<UUID> hiders = plugin.getHiders();

            if (hiders.contains(victim.getUniqueId()) && !hiders.contains(attacker.getUniqueId())) {

                victim.setGameMode(GameMode.SPECTATOR);
                victim.sendMessage("You were caught by " + attacker.getName() + "!");

                attacker.sendMessage("You caught " + victim.getName() + "!");

            }

        }

    }

}
