package com.trialtask.listeners;

import com.trialtask.manager.BlacklistManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PotionListeners implements Listener {

    private final BlacklistManager blacklistManager;
    private final Map<UUID, Long> messageTimestamps;
    private static final long MESSAGE_COOLDOWN = 2000;

    public PotionListeners(BlacklistManager blacklistManager) {
        this.blacklistManager = blacklistManager;
        this.messageTimestamps = new HashMap<>();
    }

    @EventHandler
    public void onUsePotion(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final UUID playerUUID = player.getUniqueId();
        final ItemStack item = player.getInventory().getItemInMainHand();

        if(!(blacklistManager.isBlacklisted(playerUUID))){
            return;
        }

        if(item.getType().name().contains("POTION")) {
            long currentTime = System.currentTimeMillis();
            long lastMessageTime = messageTimestamps.getOrDefault(playerUUID, 0L);

            if (currentTime - lastMessageTime >= MESSAGE_COOLDOWN) {
                player.sendMessage("You can't use potions");
                messageTimestamps.put(playerUUID, currentTime);
            }

            event.setCancelled(true);
        }
    }
}

