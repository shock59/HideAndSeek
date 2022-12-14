package gq.shock59.hideandseek.tasks;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class FinishCountdownTask extends BukkitRunnable {

    public HideAndSeek plugin;

    public FinishCountdownTask(HideAndSeek plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        ArrayList<UUID> hiders = plugin.getHiders();
        ArrayList<UUID> seekers = plugin.getSeekers();

        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "The seekers have been released!");

        for (UUID uuid : hiders) {

            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(ChatColor.YELLOW + "You have been given slowness, and if a seeker punches you you'll be eliminated!");

            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3, false, false));

        }
        for (UUID uuid : seekers) {

            Player player = Bukkit.getPlayer(uuid);
            player.sendMessage(ChatColor.YELLOW + "You can search for hiders now! All you have to do is punch one!");

            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0, false, false));

            Location location = player.getLocation();
            int highest = location.getWorld().getHighestBlockAt(location.getBlockX(), location.getBlockZ()).getY() + 1;
            player.teleport(new Location(location.getWorld(), location.getX(), highest, location.getZ()));

        }

        plugin.setGameState(2);

    }

}
