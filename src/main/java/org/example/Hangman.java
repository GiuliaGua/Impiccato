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
        String insertLetter = "_";

        this.printStart(word, hiddenWord, insertLetter);
    }

    private void printStart(String word, String hiddenWord, String insertLetter){
        // Welcoming and introduction
        System.out.println("Welcome to hangman.");
        String newWord = letterInWord(word, hiddenWord, insertLetter);
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

        String asteriskWord = new String(temp);

        return asteriskWord;
    }

    public static String letterInWord(String word, String wordOld, String insertLetter){
        char[] temp = new char[word.length()];

        for (int i = 0; i < word.length(); i++){
            temp[i] = word.charAt(i);
        }

        String insertLetterUpper = insertLetter.toUpperCase();

        for (int i = 0; i<word.length(); i++){
            if (insertLetterUpper.charAt(0) == word.charAt(i) && wordOld.charAt(i) == '*' ){
                temp[i] = insertLetterUpper.charAt(0);
            } else if (wordOld.charAt(i) == '*' ){
                temp[i] = '*';
            }
        }

        for (int i = 0; i<word.length(); i++){
            if (insertLetter.toLowerCase().charAt(0) == word.charAt(i) && wordOld.charAt(i) == '*' ){
                if (insertLetter.charAt(0) >= 'a' && insertLetter.charAt(0) <= 'z' ){
                    temp[i] = (char) (insertLetter.charAt(0) - 32);
                } else {
                    temp[i] = (char) (insertLetter.charAt(0));
                }
            } else if (wordOld.charAt(i) == '*'){
                temp[i] = '*';
            }
        }

        String wordNow = new String(temp);

        return wordNow;
    }


}
