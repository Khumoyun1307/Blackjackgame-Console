package com.blackjack.stats;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages saving and loading round history for players.
 */
public class RoundHistoryManager {

    private static final String HISTORY_DIR = "history";

    static {
        File dir = new File(HISTORY_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    /**
     * Saves a round summary for a user.
     * @param username the username
     * @param summary the round summary string
     */
    public static void saveRound(String username, String summary) {
        File file = new File(HISTORY_DIR, username + "_rounds.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(summary);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save round history for " + username + ": " + e.getMessage());
        }
    }

    /**
     * Loads the round history for a user.
     * @param username the username
     * @return list of round summaries
     */
    public static List<String> loadHistory(String username) {
        List<String> history = new ArrayList<>();
        File file = new File(HISTORY_DIR, username + "_rounds.txt");

        if (!file.exists()) return history;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Failed to load history for " + username);
        }

        return history;
    }

    /**
     * Clears the round history for a user.
     * @param username the username
     */
    public static void clearHistory(String username) {
        File file = new File(HISTORY_DIR, username + "_rounds.txt");
        if (file.exists()) file.delete();
    }
}
