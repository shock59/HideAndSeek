package gq.shock59.hideandseek.commands;

import gq.shock59.hideandseek.HideAndSeek;
import gq.shock59.hideandseek.tasks.FinishCountdownTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.UUID;

public class StartGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        HideAndSeek plugin = HideAndSeek.getPlugin();

        if (plugin.getGameState() < 1) {
            ArrayList<UUID> hiders = plugin.getHiders();
            ArrayList<UUID> seekers = plugin.getSeekers();

            StringBuilder hiderString = new StringBuilder();
            StringBuilder seekerString = new StringBuilder();

            System.out.println(hiders.size());

            // Making a string to show the players in the fancy chat message
            if (hiders.size() > 0) {
                for (UUID uuid : hiders) {
                    Player player = Bukkit.getPlayer(uuid);
                    hiderString.append(player.getName()).append(", ");
                }
                hiderString = new StringBuilder(hiderString.substring(0, hiderString.length() - 2));
            } else {
                hiderString.append(ChatColor.ITALIC);
                hiderString.append("None");
            }

            if (seekers.size() > 0) {
                for (UUID uuid : seekers) {
                    Player player = Bukkit.getPlayer(uuid);
                    seekerString.append(player.getName()).append(", ");
                }
                seekerString = new StringBuilder(seekerString.substring(0, seekerString.length() - 2));
            } else {
                seekerString.append(ChatColor.ITALIC);
                seekerString.append("None");
            }

            int remainingHiders = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {

                UUID uuid = player.getUniqueId();

                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Hide and Seek has started!");
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "Hiders: " + ChatColor.RESET + "" + ChatColor.YELLOW + hiderString);
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "Seekers: " + ChatColor.RESET + "" + ChatColor.YELLOW + seekerString);

                if (hiders.contains(uuid)) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GOLD + "You have 30 seconds to hide from the seekers!");
                    remainingHiders ++;
                } else if (seekers.contains(uuid)) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GOLD + "In 30 seconds, you will be able to search for the hiders.");
                    Location location = player.getLocation();
                    player.teleport(new Location(location.getWorld(), location.getX(), 10000, location.getZ()));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 10, false, false));
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GOLD + "You didn't join a team, so you will be able to spectate the game.");
                }

            }

            plugin.setGameState(1);
            plugin.setRemainingHiders(remainingHiders);
            new FinishCountdownTask(plugin).runTaskLater(plugin, 600L);

        } else {
            sender.sendMessage(ChatColor.RED + "The game is already running!");
        }

        return true;

    }

}
