package com.blackjack.model;


/**
 * Represents a playing card with a rank and suit.
 */
public class Card {

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5),
        SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private Suit suit = null;
    private Rank rank = null;

    /**
     * Constructs a card with the given rank and suit.
     * @param rank the card rank
     * @param suit the card suit
     */
    public Card(Rank rank, Suit suit){
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the rank of the card.
     * @return the rank
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Gets the suit of the card.
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the value of the card for Blackjack.
     * @return the card value
     */
    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    /**
     * Returns a short display string for the card.
     * @return short display string
     */
    public String getShortDisplay() {
        return String.format("[%s%s]", getDisplayRank(), getDisplaySuit());
    }

    private String getDisplayRank() {
        return switch (rank) {
            case ACE -> "A";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
            default -> String.valueOf(rank.getValue());
        };
    }

    private String getDisplaySuit() {
        return switch (suit) {
            case SPADES -> "♠";
            case HEARTS -> "♥";
            case DIAMONDS -> "♦";
            case CLUBS -> "♣";
        };
    }

    /**
     * Returns an ASCII art representation of the card.
     * @return array of strings for ASCII art
     */
    public String[] getAsciiDisplay() {
        String rankStr = switch (rank) {
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
            case ACE -> "A";
            default -> String.valueOf(rank.getValue());
        };

        String suitStr = switch (suit) {
            case HEARTS -> "♥";
            case DIAMONDS -> "♦";
            case CLUBS -> "♣";
            case SPADES -> "♠";
        };

        return new String[]{
                "┌─────┐",
                String.format("│%s   │", rankStr.length() == 2 ? rankStr : rankStr + " "),
                String.format("│  %s  │", suitStr),
                String.format("│   %s│", rankStr.length() == 2 ? rankStr : " " + rankStr),
                "└─────┘"
        };
    }


}
