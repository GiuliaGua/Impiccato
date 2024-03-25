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
        int chances = 6;
        String hiddenWord = hiddenWords(word);

        this.printStart(word, hiddenWord);
    }

    private void printStart(String word, String hiddenWord){
        // Welcoming and introduction
        System.out.println("Welcome to hangman.");
        String newWord = letterInWord();
        hiddenWord = newWord;
        System.out.println("The chosen word is a length of " + word.length() + " long.");
        System.out.println(hiddenWord);
        System.out.println("Type a letter to guess! Good luck.");
    }

    public static String hiddenWords(String word){
        char[] temp = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            temp[i] = word.charAt(i);
        }

        for (int i = 0; i < temp.length; i++){
            temp[i] = '*';
        }

        String guessedLetterInWord = new String(temp);

        return guessedLetterInWord;
    }

    public static String letterInWord(){

        return null;
    }


}
