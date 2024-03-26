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
        String lettersGuessed = "";
        boolean wordDone = false;

        Scanner start = new Scanner( System.in );
        Scanner answer = new Scanner( System.in );

        this.printStart(word,
                        hiddenWord,
                        insertLetter,
                        chances,
                        wordDone,
                        lettersGuessed,
                        start,
                        answer);
    }

    private void printStart(String word,
                            String hiddenWord,
                            String insertLetter,
                            int chances,
                            boolean wordDone,
                            String lettersGuessed,
                            Scanner start,
                            Scanner answer){

        // Welcoming and introduction
        System.out.println("Welcome to hangman.");
        String newWord = letterInWord(word, hiddenWord, insertLetter);
        hiddenWord = newWord;
        System.out.println("The chosen word is a length of " + word.length() + " long.");
        System.out.println(hiddenWord);
        System.out.println("Type a letter to guess! Good luck.");


        while (wordDone == false){
            insertLetter = answer.nextLine();
            System.out.println();

            if (!alreadyGuessed(lettersGuessed, insertLetter)){
                lettersGuessed += insertLetter + ", ";


                if (isLetterInWord(insertLetter, word ) == true){
                    System.out.println("Good job! The letter " + insertLetter + " was in the word.");
                    newWord = letterInWord(word, hiddenWord, insertLetter);
                    hiddenWord = newWord;
                    System.out.println("Guess another letter. You still have " + chances + " chances remaining.");
                    System.out.println("The letters you have guessed are: ");
                    System.out.println(lettersGuessed.substring( 0, lettersGuessed.length()-2) + ".");
                    System.out.println(hiddenWord);
                    wordDone = isWordDone(word, newWord);
                } else {
                    System.out.println("Sorry the letter " + insertLetter + " is not in the word.");

                    chances = chances - 1;

                    // Quando il giocatore fails
                    while (chances <= 0){
                        System.out.println("Sorry you are out of tries. Would you like to replay(Yes/No)[Y,N]: ");
                        replay = start.nextLine();
                        if (replay.equalsIgnoreCase("Yes")){
                            wordDone = true;
                            break;
                        } else if (replay.equalsIgnoreCase("No")){
                            System.out.println("Goodbye.");
                            System.exit(0);
                        }
                    }

                    if (wordDone == false){
                        System.out.println("Try again. You have " + chances + " remaining.");
                        wordDone = isWordDone(word, newWord);
                        System.out.println("The letters you have guessed are: ");
                        System.out.println(lettersGuessed.substring( 0, lettersGuessed.length()-2) + ".");
                        System.out.println(hiddenWord);
                        System.out.println();
                        System.out.print("Enter another letter: ");
                    }
                }
            } else {
                System.out.println("Please enter a letter you haven't guessed: ");
                System.out.println("The letters you have guessed are: ");
                System.out.println(lettersGuessed.substring( 0, lettersGuessed.length()-2) + ".");
            }
        }
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

    public static boolean isLetterInWord(String insertLetter, String word){
        boolean letterInWord = false;
        int i = 0;
        String currentLetter;

        while (i < word.length()){
            currentLetter = word.substring(i, i + 1 );
            if (insertLetter.equalsIgnoreCase(currentLetter)){
                letterInWord = true;
            }
            i = i + 1;
        }
        return letterInWord;
    }

    public static boolean alreadyGuessed(String guessed, String letterGuessing){
        char letter = letterGuessing.charAt(0);
        for (int i = 0; i < guessed.length(); i++) {
            if (guessed.charAt(i) == letter || guessed.charAt(i) + 32 == letter){
                return true;
            }
        }
        return false;
    }

    public static boolean isWordDone(String word, String wordString){
        boolean wordDone = false;
        if (word.equalsIgnoreCase(wordString)){
            wordDone = true;
        }
        return wordDone;
    }


}
