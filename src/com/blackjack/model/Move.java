package com.blackjack.model;

/**
 * Enum representing possible player moves in Blackjack.
 */
public enum Move {
    HIT, STAY, DOUBLE, SPLIT, SURRENDER, EXIT;

    /**
     * Parses a string input into a Move.
     * @param input the user input
     * @return the corresponding Move
     * @throws IllegalArgumentException if input is invalid
     */
    public static Move fromString(String input) {
        return switch (input.toLowerCase()) {
            case "hit", "h" -> HIT;
            case "stay", "st" -> STAY;
            case "double", "d" -> DOUBLE;
            case "split", "sp" -> SPLIT;
            case "surrender", "sr" -> SURRENDER;
            case "exit", "q", "e" , "quit" -> EXIT;
            default -> throw new IllegalArgumentException("Invalid move: " + input);
        };
    }
}