package com.trialtask.manager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.trialtask.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BlacklistManager {

    private Set<UUID> blacklistedPlayers;
    private final Main main;
    public BlacklistManager(final Main main) {
        this.blacklistedPlayers = new HashSet<>();
        this.main = main;
    }

    public void addPlayer(UUID player) {
        blacklistedPlayers.add(player);
    }

    public void removePlayer(UUID player) {
        blacklistedPlayers.remove(player);
    }

    public boolean isBlacklisted(UUID player) {
        return blacklistedPlayers.contains(player);
    }

    public void saveBlacklistedPlayers() {
        final File file = new File(main.getDataFolder(), "blacklisted.json");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(blacklistedPlayers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBlacklistedPlayers() {
        final File file = new File(main.getDataFolder(), "blacklisted.json");

        if(!file.exists()) {
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type setType = new TypeToken<Set<UUID>>(){}.getType();
            Set<UUID> loadedPlayers = gson.fromJson(reader, setType);
            if (loadedPlayers != null) {
                blacklistedPlayers = loadedPlayers;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.err.println("Failed to parse blacklisted players file: " + e.getMessage());
        }
    }


}
