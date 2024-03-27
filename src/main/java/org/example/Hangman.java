package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.Scanner;

@Getter
@Setter

public class Hangman {
    /**
     * variabile game.
     */
    private boolean game;

    /**
     * variabile replay.
     */
    private String replay;

    /**
     * variabile per numero magic checkstyle.
     */
    private static final int TREDUE = 32;

    /**
     * variabile magic number.
     */
    private static final int SEI = 6;

    /**
     * variabile magic number.
     */
    private static final int CINQUE = 5;

    /**
     * variabile magic number.
     */
    private static final int QUATTRO = 4;

    /**
     * variabile magic number.
     */
    private static final int TRE = 3;

    /**
     * variabile chances.
     */
    private int chances = SEI;

    /**
     * inizio del game.
     */
    public Hangman() {
        // Starts game
        game = true;
        replay = "";

        while (game) {
            this.startGame();
        }
    }

    private void startGame() {
        replay = "";
        String[] wordList = {"fun", "random", "brown", "football", "outside",
                "computer", "basketball", "rain", "europe", "america", "hot",
                "food", "cool", "ice", "body", "person"};
        Random wordChoice = new Random();
        String word = wordList[wordChoice.nextInt(wordList.length)];
        String hiddenWord = hiddenWords(word);
        String insertLetter = "_";
        String lettersGuessed = "";
        boolean wordDone = false;

        Scanner start = new Scanner(System.in);
        Scanner answer = new Scanner(System.in);

        this.printStart(word,
                        hiddenWord,
                        insertLetter,
                        wordDone,
                        lettersGuessed,
                        start,
                        answer);
    }

    private void printStart(final String word,
                            final String hiddenWord,
                            final String insertLetter,
                            final boolean wordDone,
                            final String lettersGuessed,
                            final Scanner start,
                            final Scanner answer) {
        String tmpHiddenWord = hiddenWord;
        String tmpInsertLetter = insertLetter;
        boolean tmpWordDone = wordDone;
        String tmpLettersGuessed = lettersGuessed;

        // Welcoming and introduction
        System.out.println("Welcome to hangman.");
        String newWord = letterInWord(word, tmpHiddenWord,
                tmpInsertLetter, chances);
        tmpHiddenWord = newWord;
        System.out.println("The chosen word is a length of "
                + word.length() + " long.");
        System.out.println(tmpHiddenWord);
        System.out.println("Type a letter to guess! Good luck.");

        while (!tmpWordDone) {
            tmpInsertLetter = answer.nextLine();
            System.out.println();

            if (!alreadyGuessed(tmpLettersGuessed, tmpInsertLetter)) {
                tmpLettersGuessed += tmpInsertLetter + ", ";

                if (isLetterInWord(tmpInsertLetter, word)) {
                    System.out.println("Good job! The letter "
                            + tmpInsertLetter + " was in the word.");
                    newWord = letterInWord(word, tmpHiddenWord,
                            tmpInsertLetter, chances);
                    tmpHiddenWord = newWord;
                    System.out.println("Guess another letter. You still have "
                            + chances + " chances remaining.");
                    System.out.println("The letters you have guessed are: ");
                    System.out.println(tmpLettersGuessed.substring(0,
                            tmpLettersGuessed.length() - 2) + ".");
                    System.out.println(tmpHiddenWord);
                    tmpWordDone = isWordDone(word, newWord);
                } else {
                    System.out.println("Sorry the letter "
                            + tmpInsertLetter + " is not in the word.");

                    chances = chances - 1;

                    // Quando il giocatore fails
                    while (chances <= 0) {
                        chooseHangMan(chances);
                        System.out.println("\nSorry you are out of tries.");
                        System.out.println("       ! You lost !        ");
                        System.out.println("     -> Goodbye... <-      ");
                        System.exit(0);
                    }

                    if (!tmpWordDone) {
                        System.out.println("Try again. You have "
                                + chances + " remaining.");
                        tmpWordDone = isWordDone(word,
                                              newWord);
                        System.out.println("The letters you have guessed are:");
                        System.out.println(tmpLettersGuessed.substring(0,
                                tmpLettersGuessed.length() - 2) + ".");
                        chooseHangMan(chances);
                        System.out.println(tmpHiddenWord);
                        System.out.println();
                        System.out.print("Enter another letter: ");
                    }
                }
            } else {
                System.out.println("Pls enter a letter you haven't guessed:");
                System.out.println("The letters you have guessed are: ");
                System.out.println(tmpLettersGuessed.substring(0,
                        tmpLettersGuessed.length() - 2) + ".");
            }
        }


        System.out.println("\nGood Job! Would you like to play again?");

        replay = start.nextLine();
        if (replay.equalsIgnoreCase("Yes")) {
            game = true;
        }
        if (replay.equalsIgnoreCase("No")) {
            System.exit(0);
        }

    }

    /**
     * setta la parola con gli asterischi.
     * @param word
     * @return stringa
     */
    public static String hiddenWords(final String word) {
        char[] temp = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {
            temp[i] = word.charAt(i);
        }

        for (int i = 0; i < temp.length; i++) {
            temp[i] = '*';
        }

        String asteriskWord = new String(temp);

        return asteriskWord;
    }

    /**
     * place la lettera nella parola nascosta.
     * @param word
     * @param wordOld
     * @param insertLetter
     * @param chances
     * @return stringa
     */
    public static String letterInWord(final String word,
                                      final String wordOld,
                                      final String insertLetter,
                                      final int chances) {
        char[] temp = new char[word.length()];

        chooseHangMan(chances);

        for (int i = 0; i < word.length(); i++) {
            temp[i] = word.charAt(i);
        }

        String insertLetterUpper = insertLetter.toUpperCase();

        for (int i = 0; i < word.length(); i++) {
            if (insertLetterUpper.charAt(0) == word.charAt(i)
                    && wordOld.charAt(i) == '*') {
                temp[i] = insertLetterUpper.charAt(0);
            } else if (wordOld.charAt(i) == '*') {
                temp[i] = '*';
            }
        }

        for (int i = 0; i < word.length(); i++) {
            if (insertLetter.toLowerCase().charAt(0) == word.charAt(i)
                    && wordOld.charAt(i) == '*') {
                if (insertLetter.charAt(0) >= 'a'
                        && insertLetter.charAt(0) <= 'z') {
                    temp[i] = (char) (insertLetter.charAt(0) - TREDUE);
                } else {
                    temp[i] = (char) (insertLetter.charAt(0));
                }
            } else if (wordOld.charAt(i) == '*') {
                temp[i] = '*';
            }
        }

        String wordNow = new String(temp);

        return wordNow;
    }

    /**
     * controlla se la lettera è nella parola.
     * @param insertLetter
     * @param word
     * @return boolean
     */
    public static boolean isLetterInWord(final String insertLetter,
                                         final String word) {
        boolean letterInWord = false;
        int i = 0;
        String currentLetter;

        while (i < word.length()) {
            currentLetter = word.substring(i, i + 1);
            if (insertLetter.equalsIgnoreCase(currentLetter)) {
                letterInWord = true;
            }
            i = i + 1;
        }
        return letterInWord;
    }

    /**
     * controllo se la lettera che si vuole indovinare è già stata indovinata.
     * @param guessed
     * @param letterGuessing
     * @return boolean
     */
    public static boolean alreadyGuessed(final String guessed,
                                         final String letterGuessing) {
        char letter = letterGuessing.charAt(0);
        for (int i = 0; i < guessed.length(); i++) {
            if (guessed.charAt(i) == letter
                    || guessed.charAt(i) + TREDUE == letter) {
                return true;
            }
        }
        return false;
    }

    /**
     * funzione che controlla se è finita la parola.
     * @param word
     * @param wordString
     * @return boolean
     */
    public static boolean isWordDone(final String word,
                                     final String wordString) {
        boolean wordDone = false;
        if (word.equalsIgnoreCase(wordString)) {
            wordDone = true;
        }
        return wordDone;
    }

    private static void hangMan() {
        System.out.println("        _______    ");
        System.out.println("     __|           ");
        System.out.println("    |              ");
        System.out.println("   |               ");
        System.out.println(" __|__             ");
    }

    private static void hangMan1() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |              ");
        System.out.println("   |               ");
        System.out.println(" __|__             ");
    }

    private static void hangMan2() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |         |    ");
        System.out.println("   |               ");
        System.out.println(" __|__             ");
    }

    private static void hangMan3() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |         |    ");
        System.out.println("   |         /     ");
        System.out.println(" __|__             ");
    }

    private static void hangMan4() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |         |    ");
        System.out.println("   |         / \\  ");
        System.out.println(" __|__             ");
    }

    private static void hangMan5() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |        /|    ");
        System.out.println("   |         / \\  ");
        System.out.println(" __|__             ");
    }

    private static void hangMan6() {
        System.out.println("        _______    ");
        System.out.println("     __|     ( )   ");
        System.out.println("    |        /|\\  ");
        System.out.println("   |         / \\  ");
        System.out.println(" __|__             ");
    }

    private static void chooseHangMan(final int choice) {
        switch (choice) {
            case SEI:
                hangMan();
                break;
            case CINQUE:
                hangMan1();
                break;
            case QUATTRO:
                hangMan2();
                break;
            case TRE:
                hangMan3();
                break;
            case 2:
                hangMan4();
                break;
            case 1:
                hangMan5();
                break;
            case 0:
                hangMan6();
                break;
            default:
                break;
        }
    }

}
