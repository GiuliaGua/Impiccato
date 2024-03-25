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
        String[] wordList = {"fun", "random", "brown", "football", "outside", "computer",
                "basketball", "rain", "europe", "america", "hot", "food", "cool", "ice",
                "body", "person"};
        Random wordChoice = new Random();
        String word = wordList[wordChoice.nextInt(wordList.length)];

        this.printStart(word);
    }

    private void printStart(String word){
        // Welcoming and introduction
        System.out.println("Welcome to hangman.");
        String newWord = letterInWord();
        System.out.println("The chosen word is a length of " + word.length() + " long.");
        System.out.println("Type a letter to guess! Good luck.");
    }

    public static String letterInWord(){

        return null;
    }
}
