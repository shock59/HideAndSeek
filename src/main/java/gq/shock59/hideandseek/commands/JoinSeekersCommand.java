package gq.shock59.hideandseek.commands;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class JoinSeekersCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {

            HideAndSeek plugin = HideAndSeek.getPlugin();
            ArrayList<UUID> hiders = plugin.getHiders();
            ArrayList<UUID> seekers = plugin.getSeekers();

            UUID uuid = player.getUniqueId();

            if (!seekers.contains(uuid)) {

                seekers.add(uuid);
                if (hiders.contains(uuid)) {
                    hiders.remove(uuid);
                    plugin.setHiders(hiders);
                }
                player.sendMessage("Joined the seekers team");

                plugin.setSeekers(seekers);

            } else {
                player.sendMessage(ChatColor.RED + "You're already on the seekers team!");
            }

        }

        return true;
    }

}
