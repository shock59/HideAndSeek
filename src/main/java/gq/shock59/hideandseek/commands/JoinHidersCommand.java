package gq.shock59.hideandseek.commands;

import gq.shock59.hideandseek.HideAndSeek;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class JoinHidersCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {

            HideAndSeek plugin = HideAndSeek.getPlugin();
            ArrayList<UUID> hiders = plugin.getHiders();

            if (!hiders.contains(player.getUniqueId())) {

                hiders.add(player.getUniqueId());
                player.sendMessage("Joined the hiders team");

                plugin.setHiders(hiders);

            } else {
                player.sendMessage(ChatColor.RED + "You're already on the hiders team!");
            }

        }

        return true;
    }

}
