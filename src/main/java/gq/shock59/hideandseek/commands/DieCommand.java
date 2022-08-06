package gq.shock59.hideandseek.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            
        if (sender instanceof Player player) {
            player.setHealth(0);
            player.sendMessage(ChatColor.RED + "You have died!");
        }

        return true;
    }

}
