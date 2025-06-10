package com.blackjack.model;

public enum Move {
    HIT, STAY, DOUBLE, SPLIT, SURRENDER, EXIT;

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