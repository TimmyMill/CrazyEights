package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Players> playersList;     /* Initiate an ArrayList to store players */
    public static ArrayList<Card> discard;            /* ArrayList for discarded cards */


    /*
     * Method to start the game
     */

    public static ArrayList<Players> startGame() {
        playersList = new ArrayList<>(); /* Create ArrayList of Players */
        discard = new ArrayList<>();     /* Create ArrayList to keep the cards that have been played */

        /*
         * We will first ask the user to enter a name. Then they will be give a hand containing 5 cards
         * which will be used to create their player. This will be done again for the computer player.
         */

        System.out.println("Please enter your name: ");
        String name = in.next();
        ArrayList<Card> hand = Hand.dealHand();           /* Call dealHand method and assign it to the 1st player */
        HumanPlayer player = new HumanPlayer(name, hand); /* Create new player */
        playersList.add(player);                          /* Add player to list of players */

        ArrayList<Card> comHand = Hand.dealHand();
        ComputerPlayer com = new ComputerPlayer("Computer", comHand);
        playersList.add(com);

        Card startCard = Hand.deck.drawCard();
//        System.out.println(startCard);
        discard.add(startCard);
//        int size = Hand.deck.getTotalCards();
//        System.out.println("Cards left in deck: " + size);
//        size = Hand.deck.getTotalCards();
//        System.out.println("Cards left in deck: " + size);

        return playersList;
    }

    public static void showHand(ArrayList<Players> playersList) {
        playersList.forEach(System.out::println);
    }

    /*
     * Method for each players turn
     */

    public static void playerTurn() {
        Card playCard = discard.get(discard.size() - 1); // Grabs the last element of the discard pille
        System.out.println(playCard);

        for (Players p : playersList) {
            System.out.println(p.getName() + "'s Turn");
            System.out.println("Press (Y) to draw card\nPress any other key to continue: ");
            char draw = in.next().toUpperCase().charAt(0);
            if (draw == 'Y') {
                Card playerDraws = Hand.deck.drawCard();
                p.getHand().add(playerDraws);
                System.out.println(p.getHand());
            }
        }
    }
}