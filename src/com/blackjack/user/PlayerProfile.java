package com.blackjack.user;

/**
 * Represents a player's profile, including credentials and balance.
 */
public class PlayerProfile {
    private final String username;
    private String password;
    private double balance;

    /**
     * Constructs a player profile.
     * @param username the username
     * @param password the hashed password
     * @param startingBalance the starting balance
     */
    public PlayerProfile(String username, String password, double startingBalance) {
        this.username = username;
        this.password = password;
        this.balance = startingBalance;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Gets the hashed password.
     * @return the password hash
     */
    public String getPassword() { return password; }

    /**
     * Sets the password hash.
     * @param password the new password hash
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the balance.
     * @return the balance
     */
    public double getBalance() { return balance; }

    /**
     * Sets the balance.
     * @param balance the new balance
     */
    public void setBalance(double balance) { this.balance = balance; }
}
