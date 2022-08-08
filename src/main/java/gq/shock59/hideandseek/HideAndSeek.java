package gq.shock59.hideandseek;

import gq.shock59.hideandseek.commands.JoinHidersCommand;
import gq.shock59.hideandseek.commands.JoinSeekersCommand;
import gq.shock59.hideandseek.commands.LeaveTeamCommand;
import gq.shock59.hideandseek.commands.StartGameCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gq.shock59.hideandseek.events.EventListeners;

import java.util.ArrayList;
import java.util.UUID;

public class HideAndSeek extends JavaPlugin {

    private static HideAndSeek plugin;

    private ArrayList<UUID> hiders = new ArrayList<>();
    private ArrayList<UUID> seekers = new ArrayList<>();

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("Hello, SpigotMC!");

        Bukkit.getPluginManager().registerEvents(new EventListeners(), this);

        getCommand("joinhiders").setExecutor(new JoinHidersCommand());
        getCommand("joinseekers").setExecutor(new JoinSeekersCommand());
        getCommand("leaveteam").setExecutor(new LeaveTeamCommand());
        getCommand("startgame").setExecutor(new StartGameCommand());

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

    public ArrayList<UUID> getSeekers() {
        return seekers;
    }

    public void setSeekers(ArrayList<UUID> seekers) {
        this.seekers = seekers;
    }

}
