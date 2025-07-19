package com.blackjack.game;

import com.blackjack.model.Card;
import com.blackjack.model.Hand;
import java.util.List;

/**
 * Encapsulates the rules for Blackjack, including move validation and payouts.
 */
public class GameRules {

    /**
     * Checks if the player can hit.
     * @param playerHand the player's hand
     * @return true if hit is allowed
     */
    public boolean canHit(Hand playerHand) {
        return !isBust(playerHand);
    }

    /**
     * Checks if the player can stay.
     * @return always true
     */
    public boolean canStay() {
        return true;
    }

    /**
     * Checks if the player can double down.
     * @param playerHand the player's hand
     * @param playerBalance the player's balance
     * @return true if double is allowed
     */
    public boolean canDouble(Hand playerHand, double playerBalance) {
        return ( playerHand.getCards().size() == 2 && playerBalance >= playerHand.getBet() );
    }

    /**
     * Checks if the player can split.
     * @param playerHand the player's hand
     * @param playerBalance the player's balance
     * @return true if split is allowed
     */
    public boolean canSplit(Hand playerHand, double playerBalance) {
        List<Card> cards = playerHand.getCards();
        return ( cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank()
            &&  playerBalance >= playerHand.getBet() );
    }

    /**
     * Checks if the hand is a blackjack.
     * @param playerHand the hand to check
     * @return true if blackjack
     */
    public boolean isBlackjack(Hand playerHand) {
        return (playerHand.getValue() == 21);
    }

    /**
     * Checks if the hand is bust.
     * @param playerHand the hand to check
     * @return true if bust
     */
    public boolean isBust(Hand playerHand) {
        return playerHand.getValue() > 21;
    }

    /**
     * Checks if the player can surrender.
     * @param hand the player's hand
     * @param hasActed whether the player has acted
     * @return true if surrender is allowed
     */
    public boolean canSurrender(Hand hand, boolean hasActed) {
        return hand.getCards().size() == 2 && !hasActed;
    }

    /**
     * Calculates the payout for a blackjack.
     * @param playerHand the blackjack hand
     * @return the payout amount
     */
    public double getBlackjackPayout(Hand playerHand) {
        return ( playerHand.getBet() * 1.5 + playerHand.getBet());
    }

}
