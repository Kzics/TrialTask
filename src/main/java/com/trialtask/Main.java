package com.trialtask;

import com.trialtask.commands.blacklist.PotionBlacklistCommand;
import com.trialtask.listeners.PotionListeners;
import com.trialtask.manager.BlacklistManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private BlacklistManager blacklistManager;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        this.blacklistManager = new BlacklistManager(this);
        blacklistManager.loadBlacklistedPlayers();

        getLogger().info("TrialTask has been enabled!");

        getCommand("potion").setExecutor(new PotionBlacklistCommand(this));

        getServer().getPluginManager().registerEvents(new PotionListeners(blacklistManager), this);
    }

    @Override
    public void onDisable() {
        blacklistManager.saveBlacklistedPlayers();
        getLogger().info("TrialTask has been disabled!");
    }

    public BlacklistManager getBlacklistManager() {
        return blacklistManager;
    }

}
