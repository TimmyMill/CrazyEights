package com.company;

import java.util.ArrayList;

public class Hand {
    public static ArrayList<Card> hand;     /* ArrayList for each player's hand */
    public static Deck deck = new Deck();  /* Create deck object */
    static Card C;                          /* Create card Object */

    /*
     * Deal Hand to each player
     */

    public static ArrayList<Card> dealHand() {
        hand = new ArrayList<>();
//        System.out.println("Cards drawn: ");
        for (int i = 0; i < 5; i++) {
            C = deck.drawCard();
//            System.out.println(C.toString());
            hand.add(C);
        }
//        deck.getTotalCards();
        return hand;
    }
}
    /* Flip card after dealing */
//    public static ArrayList<Deck> stockPile(){
//        stock = new ArrayList<>();
//    }