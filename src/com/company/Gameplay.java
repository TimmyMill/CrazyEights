package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Players> playersList;     /* Initiate an ArrayList to store players */
    public static ArrayList<Card> discard;            /* ArrayList for discarded cards */
    public static int cardChoice;
    public static Card stockCard;
    public static String winner;
    public static String playersTurn;


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

        return playersList;
    }

//    public static void showHand(ArrayList<Players> playersList) {
//        playersList.forEach(System.out::println);
//    }

    public static int playerMenu() {
        /* Menu */
        System.out.println("\tMenu");
        System.out.println("Please select 1-3");
        System.out.println("(1) Draw card: ");
        System.out.println("(2) Play card: ");
        System.out.println("(3) Pass: \n");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        while (! choice.matches("[1-3]")) {
            System.out.println("Invalid selection");
            choice = in.nextLine();
        }
        return Integer.parseInt(choice);
    }

    /*
     * Method for each players turn
     */

    public static void playerTurn() {
        Scanner in = new Scanner(System.in);
        Card startCard = Hand.deck.drawCard();
        discard.add(startCard);
        boolean current = true;
        while (current) {
            for (Players p : playersList) {
                System.out.println(p.getName() + "'s Turn");
                System.out.println("Hand: ");
                for (Card c : p.getHand()) {
                    System.out.print(c + ", ");
                }
                stockCard = discard.get(discard.size() - 1); /* Grabs the last element of the discard pile */
                System.out.println("\nCurrent card:\n" + stockCard + "\n");
                int choice = playerMenu();
                if (choice == 1) {
                    Card playerDraws = Hand.deck.drawCard();
                    p.getHand().add(playerDraws);
                    System.out.println(p.getHand());
                }
                else if (choice == 2) {
                    System.out.println("\nCurrent card:\n" + stockCard + "\n");
                    System.out.println("Select which card you'd like to play");
                    ArrayList<Card> cards = p.getHand();
                    for (int i = 0; i < cards.size(); i++) {
                        System.out.println("(" + (i+1) + ") " + cards.get(i));
                    }
                    String str = in.nextLine();
                    cardChoice = Integer.parseInt(str);
                    playersTurn = p.getName();
                    playCard();

                    // exit if hand empty
                    if (p.getHand().isEmpty()) {
                        winner = p.getName();
                        endGame();
                        current = false;
                        break;
                    }
                }
                else if (choice == 3) {
                    System.out.println("Passing");
                }
            }
        }
    }


    /*
     * Method to play a card
     */

    public static void playCard() {
        /*
         * Look through the first players hand
         * Find the element that corresponds to the user's choice
         */
        for (Players p : playersList) {
            if (p.getName().matches(playersTurn)) {
                for (int i = 0; i < p.getHand().size(); i++)
                if (cardChoice == (i + 1)) {
                    Card j = p.getHand().get(i);        /* Creates a reference to the card the player selected */
                    int r = j.getRank();                /* Creates an int reference to the rank of the card the player selected */
                    int s = j.getSuit();                /* Creates an int reference to the suit of the card the player selected */
                    int r1 = stockCard.getRank();       /* Creates an int reference to the rank of the stock card*/
                    int s1 = stockCard.getSuit();       /* Creates an int reference to the suit of the stock card*/
                    if (r == 7) {
                        int wild = wildCard();          /* Use wildCard method */
                        Card eight = new Card(wild, r); /* Create a new card using the wild value to change the suit to
                                                           player's selection and the rank of 8 */
                        discard.add(eight);             /* Add the newly created card to the discard pile */
                        p.getHand().remove(i);          /* Remove the card from the player's hand */

                    } else if (r == r1 || s == s1) {
                        System.out.println("Playing the " + p.getHand().get(i));
                        discard.add(p.getHand().get(i));
                        p.getHand().remove(i);
                    } else {
                        System.out.println("You cannot play this card");
                    }
                }
            }
        }
    }





    public static int wildCard() {
        System.out.println("What suit would you like to change to?");
        System.out.println("(1) Clubs");
        System.out.println("(2) Diamonds");
        System.out.println("(3) Hearts");
        System.out.println("(4) Spades\n");
        String str = in.next();
        while ( ! str.matches("[1-4]")) {
            System.out.println("Invalid selection");
            str = in.next();
        }
        int suitChange = Integer.parseInt(str);
        if (suitChange == 1) {
            System.out.println("Suit is changed to Clubs");
        }
        if (suitChange == 2) {
            System.out.println("Suit is changed to Diamonds");
        }
        if (suitChange == 3) {
            System.out.println("Suit is changed to Hearts");
        }
        if (suitChange == 4) {
            System.out.println("Suit is changed to Spades");
        }
        suitChange -= 1;
        return suitChange;
    }

    public static void endGame() {
        System.out.println(winner + " wins the game!");
        System.exit(0);
    }

}