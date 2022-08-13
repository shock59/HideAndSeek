package gq.shock59.hideandseek.events;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerQuitEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {

        HideAndSeek plugin = HideAndSeek.getPlugin();
        int gameState = plugin.getGameState();
        if (gameState > 0) {

            ArrayList<UUID> hiders = plugin.getHiders();
            ArrayList<UUID> seekers = plugin.getSeekers();

            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();

            if (hiders.contains(uuid)) {

                int remainingHiders = plugin.getRemainingHiders();

                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " left the game and was considered found!");

                remainingHiders --;
                if (remainingHiders > 0) {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "There are " + remainingHiders + " hiders remaining.");
                    plugin.setRemainingHiders(remainingHiders);
                } else {
                    Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "All hiders have been found!");
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Thanks for playing!");

                    for (UUID seekerUuid : seekers) {
                        Player seeker = Bukkit.getPlayer(seekerUuid);
                        seeker.setGameMode(GameMode.SPECTATOR);
                    }

                    plugin.setGameState(0);
                }

            } else if (seekers.contains(uuid)) {

                int onlineSeekers = plugin.getOnlineSeekers();
                if (onlineSeekers == 1) {

                    Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " left the game and because they were the last hunter the game has ended!");
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Thanks for playing!");

                    for (UUID hiderUuid : hiders) {
                        Player hider = Bukkit.getPlayer(hiderUuid);
                        hider.setGameMode(GameMode.SPECTATOR);
                    }

                    plugin.setGameState(0);

                } else {

                    Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " left the game! They can continue to seek if they rejoin!");
                    plugin.setOnlineSeekers(onlineSeekers - 1);

                }

            }

        }

    }

}
