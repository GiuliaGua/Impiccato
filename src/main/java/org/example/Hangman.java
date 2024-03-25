package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.Scanner;

@Getter
@Setter


public class Hangman {
    boolean game;
    String replay;

    public Hangman() {
        // Starts game
        game = true;
        replay = "";

        while (game == true) {
            this.startGame();
        }
    }

    private void startGame(){
        replay = "";
        String[] Wordlist = {"fun", "random", "brown", "football", "outside", "computer",
                "basketball", "rain", "europe", "america", "hot", "food", "cool", "ice",
                "body", "person"};
        Random WordChoice = new Random();
        String Word = Wordlist[WordChoice.nextInt(Wordlist.length)];
    }
}
