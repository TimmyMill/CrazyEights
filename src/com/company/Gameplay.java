package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Players> playersList;     /* Initiate an ArrayList to store players */
    public static ArrayList<Card> discard;            /* ArrayList for discarded cards */
    public static int cardChoice;


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

    public static int playerMenu() {
        /* Menu */
        System.out.println("Menu\nPlease select 1-3\n");
        System.out.println("(1) Draw card: ");
        System.out.println("(2) Play card: ");
        System.out.println("(3) Pass: \n");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        return Integer.parseInt(choice);
    }

    /*
     * Method for each players turn
     */

    public static void playerTurn() {
        Scanner in = new Scanner(System.in);
        Card playCard = discard.get(discard.size() - 1); // Grabs the last element of the discard pile
        System.out.println(playCard);
        for (Players p : playersList) {
            System.out.println(p.getName() + "'s Turn");
            while (true) {
                int choice = playerMenu();
                if (choice == 1) {
                    Card playerDraws = Hand.deck.drawCard();
                    p.getHand().add(playerDraws);
                    System.out.println(p.getHand());
                } else if (choice == 2) {
                    System.out.println("Select which card you'd like to play");
                    ArrayList<Card> cards = p.getHand();
                    for (int i = 0; i < cards.size(); i++) {
                        System.out.println("(" + (i+1) + ") " + cards.get(i));
                    }
                    String str = in.nextLine();
                    cardChoice = Integer.parseInt(str);
                    playCard();
                } else if (choice == 3) {
                    break;
                } else {
                    break;
                }
            }
        }
    }

    /*
     * Method to play a card
     */

    public static void playCard() {
        Players p1 = playersList.get(0);
        for (int i = 0; i < p1.getHand().size(); i++) {
            if (cardChoice == (i + 1)) {
                System.out.println("Playing the " + p1.getHand().get(i));
            }
        }
    }

}