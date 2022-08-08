package gq.shock59.hideandseek.events;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerDamageByPlayerEvent implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) { if (event.getEntity() instanceof Player victim && event.getDamager() instanceof Player attacker) {

        HideAndSeek plugin = HideAndSeek.getPlugin();
        int gameState = plugin.getGameState();
        if (gameState > 0) { // No damage should be taken during the game apart from being punched by a seeker

            ArrayList<UUID> hiders = plugin.getHiders();
            ArrayList<UUID> seekers = plugin.getSeekers();

            if (hiders.contains(victim.getUniqueId()) && seekers.contains(attacker.getUniqueId())) {

                int remainingHiders = plugin.getRemainingHiders();

                victim.setGameMode(GameMode.SPECTATOR);
                victim.removePotionEffect(PotionEffectType.SLOW);
                victim.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You were found by " + attacker.getName() + "!");

                attacker.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You found " + victim.getName() + "!");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player != victim && player != attacker) {
                        player.sendMessage(ChatColor.YELLOW + victim.getName() + " was found by " + attacker.getName() + "!");
                    }
                }

                if (remainingHiders == 1) {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "All hiders have been found!");
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Thanks for playing!");

                    for (UUID uuid : seekers) {
                        Player player = Bukkit.getPlayer(uuid);
                        player.setGameMode(GameMode.SPECTATOR);
                    }

                    plugin.setGameState(0);
                } else {
                    remainingHiders --;
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "There are " + remainingHiders + " hiders remaining.");
                    plugin.setRemainingHiders(remainingHiders);
                }

            }

            event.setCancelled(true);

        }

    }}

}
