package com.blackjack.user;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages user registration, login, and profile persistence.
 */
public class UserManager {

    private static final String USERS_FILE = "users.txt";
    private final Map<String, PlayerProfile> profiles = new HashMap<>();
    private static final String USERS_DIR = "users";
    private static final String BACKUP_DIR = "backup";

    public UserManager() {
        File dir = new File(USERS_DIR);
        if (!dir.exists()) dir.mkdirs();

        loadProfiles();
    }

    /**
     * Checks if a username already exists.
     * @param username the username
     * @return true if exists
     */
    public boolean usernameExists(String username) {
        return profiles.containsKey(username);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

    /**
     * Registers a new user.
     * @param username the username
     * @param password the password
     * @return the created PlayerProfile, or null if username exists
     */
    public PlayerProfile register(String username, String password) {
        if (profiles.containsKey(username)) return null;

        String hashedPassword = hashPassword(password);
        PlayerProfile profile = new PlayerProfile(username, hashedPassword, 1000);
        profiles.put(username, profile);
        saveProfiles();
        return profile;
    }

    /**
     * Attempts to log in a user.
     * @param username the username
     * @param password the password
     * @return the PlayerProfile if successful, null otherwise
     */
    public PlayerProfile login(String username, String password) {
        PlayerProfile profile = profiles.get(username);
        String hashedInput = hashPassword(password);

        if (profile != null && profile.getPassword().equals(hashedInput)) {
            return profile;
        }
        return null;
    }

    /**
     * Saves all user profiles to disk.
     */
    public void saveProfiles() {
        for (PlayerProfile profile : profiles.values()) {
            saveProfile(profile); // delegate to per-user save
        }
    }

    private void loadProfiles() {
        File dir = new File(USERS_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files == null) return;

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String username = reader.readLine();
                String password = reader.readLine();
                double balance = Double.parseDouble(reader.readLine());

                if (username != null && password != null) {
                    PlayerProfile profile = new PlayerProfile(username, password, balance);
                    profiles.put(username, profile);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("⚠️ Skipping corrupted user file: " + file.getName());
            }
        }
    }

    /**
     * Saves a single user profile to disk.
     * @param profile the profile to save
     */
    public void saveProfile(PlayerProfile profile) {
        // backupUserDirectory();
        File file = new File(USERS_DIR, profile.getUsername() + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(profile.getUsername());
            writer.newLine();
            writer.write(profile.getPassword()); // already hashed
            writer.newLine();
            writer.write(String.valueOf(profile.getBalance()));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save profile for " + profile.getUsername() + ": " + e.getMessage());
        }

        // Keep in memory updated too
        profiles.put(profile.getUsername(), profile);
    }

    private void backupUserDirectory() {
        File sourceDir = new File(USERS_DIR);
        if (!sourceDir.exists()) return;

        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new java.util.Date());
        File backupDir = new File(BACKUP_DIR + File.separator + "users_" + timestamp);

        if (!backupDir.exists()) backupDir.mkdirs();

        File[] files = sourceDir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null) return;

        for (File file : files) {
            File destFile = new File(backupDir, file.getName());
            try (InputStream in = new FileInputStream(file);
                 OutputStream out = new FileOutputStream(destFile)) {

                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }

            } catch (IOException e) {
                System.out.println("⚠️ Failed to back up file: " + file.getName());
            }
        }
    }

    /**
     * Gets a BlackjackProfile for the given username.
     * @param username the username
     * @return the BlackjackProfile, or null if not found
     */
    public BlackjackProfile getBlackjackProfile(String username) {
        PlayerProfile base = profiles.get(username);
        if (base == null) return null;

        return new BlackjackProfile(base.getUsername(), base.getPassword(), base.getBalance());
    }

}