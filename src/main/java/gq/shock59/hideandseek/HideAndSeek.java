package gq.shock59.hideandseek;

import gq.shock59.hideandseek.commands.JoinHidersCommand;
import gq.shock59.hideandseek.commands.JoinSeekersCommand;
import gq.shock59.hideandseek.commands.LeaveTeamCommand;
import gq.shock59.hideandseek.commands.StartGameCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import gq.shock59.hideandseek.events.PlayerDamageByPlayerEvent;

import java.util.ArrayList;
import java.util.UUID;

public class HideAndSeek extends JavaPlugin {

    private static HideAndSeek plugin;

    private ArrayList<UUID> hiders = new ArrayList<>();
    private ArrayList<UUID> seekers = new ArrayList<>();

    private int gameState = 0; // 0 = not playing, 1 = during countdown, 2 = after countdown but still during game
    private int remainingHiders;

    @Override
    public void onEnable() {

        plugin = this;

        getLogger().info("Hello, SpigotMC!");

        Bukkit.getPluginManager().registerEvents(new PlayerDamageByPlayerEvent(), this);

        getCommand("joinhiders").setExecutor(new JoinHidersCommand());
        getCommand("joinseekers").setExecutor(new JoinSeekersCommand());
        getCommand("leaveteam").setExecutor(new LeaveTeamCommand());
        getCommand("startgame").setExecutor(new StartGameCommand());

    }

    // Getters and setters
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

    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getRemainingHiders() {
        return remainingHiders;
    }
    public void setRemainingHiders(int remainingHiders) {
        this.remainingHiders = remainingHiders;
    }
}
