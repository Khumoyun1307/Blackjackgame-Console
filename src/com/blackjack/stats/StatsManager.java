package com.blackjack.stats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

/**
 * Manages saving and loading player statistics to disk.
 */
public class StatsManager {

    private static final String STATS_DIR = "stats";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        File dir = new File(STATS_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    /**
     * Saves the stats for a user.
     * @param username the username
     * @param stats the GameStats object
     */
    public static void saveStats(String username, GameStats stats) {
        File file = new File(STATS_DIR, username + "_stats.json");

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(stats, writer);
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save stats for " + username + ": " + e.getMessage());
        }
    }

    /**
     * Loads the stats for a user.
     * @param username the username
     * @return the loaded GameStats
     */
    public static GameStats loadStats(String username) {
        File file = new File(STATS_DIR, username + "_stats.json");

        if (!file.exists()) {
            return new GameStats(); // fresh stats if not found
        }

        try (Reader reader = new FileReader(file)) {
            GameStats stats = gson.fromJson(reader, GameStats.class);
            if (stats == null) {
                System.out.println("⚠️ Deserialized stats is null. Returning new GameStats.");
                return new GameStats();
            }
            return stats;
        } catch (IOException e) {
            System.out.println("⚠️ Failed to load stats for " + username + ": " + e.getMessage());
            return new GameStats();
        }
    }

    /**
     * Deletes the stats for a user.
     * @param username the username
     */
    public static void deleteStats(String username) {
        File file = new File(STATS_DIR, username + "_stats.json");
        if (file.exists()) file.delete();
    }

}
