package com.blackjack.user;

/**
 * Specialized PlayerProfile for Blackjack.
 */
public class BlackjackProfile extends PlayerProfile {

    public BlackjackProfile(String username, String password, double startingBalance) {
        super(username, password, startingBalance);
    }

}