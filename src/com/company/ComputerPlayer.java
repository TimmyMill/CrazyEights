package com.company;
import java.util.ArrayList;

public class ComputerPlayer extends Players {
    public ComputerPlayer(String name, ArrayList<Card> hand){
        this.name = name;
        this.hand = hand;
    }

    @Override
    public String toString() {return name + "'s hand: " + hand;}
}