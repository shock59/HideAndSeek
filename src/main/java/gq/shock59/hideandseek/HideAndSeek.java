package gq.shock59.hideandseek;

import gq.shock59.hideandseek.commands.JoinHidersCommand;
import gq.shock59.hideandseek.commands.LeaveHidersCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gq.shock59.hideandseek.commands.DieCommand;
import gq.shock59.hideandseek.events.EventListeners;

import java.util.ArrayList;
import java.util.UUID;

public class HideAndSeek extends JavaPlugin {

    private static HideAndSeek plugin;
    private ArrayList<UUID> hiders = new ArrayList<>();

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("Hello, SpigotMC!");

        Bukkit.getPluginManager().registerEvents(new EventListeners(), this);

        getCommand("die").setExecutor(new DieCommand());
        getCommand("joinhiders").setExecutor(new JoinHidersCommand());
        getCommand("leavehiders").setExecutor(new LeaveHidersCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    public static HideAndSeek getPlugin() {
        return plugin;
    }

    public ArrayList<UUID> getHiders() {
        return hiders;
    }

    public void setHiders(ArrayList<UUID> hiders) {
        this.hiders = hiders;
    }

}
