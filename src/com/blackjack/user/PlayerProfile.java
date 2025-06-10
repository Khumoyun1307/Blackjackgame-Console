package com.blackjack.user;

public class PlayerProfile {
    private final String username;
    private String password;
    private double balance;

    public PlayerProfile(String username, String password, double startingBalance) {
        this.username = username;
        this.password = password;
        this.balance = startingBalance;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
