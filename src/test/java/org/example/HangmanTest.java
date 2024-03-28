package org.example;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class HangmanTest {

    @Test
    public void hiddenWords() {
        assertEquals("**", Hangman.hiddenWords("ab"));
        assertEquals("*****", Hangman.hiddenWords("world"));
        assertEquals("", Hangman.hiddenWords(""));
    }

    @Test
    public void isLetterInWord() {
        assertTrue(Hangman.isLetterInWord("a", "apple"));
        assertFalse(Hangman.isLetterInWord("z", "apple"));
        assertTrue(Hangman.isLetterInWord("t", "elephant"));
        assertFalse(Hangman.isLetterInWord("a", ""));
        assertFalse(Hangman.isLetterInWord("", "elephant"));
        assertTrue(Hangman.isLetterInWord("e", "E")); // Testa la non distinzione tra maiuscole e minuscole
    }

    @Test
    public void alreadyGuessed() {
        assertTrue(Hangman.alreadyGuessed("aeiou", "a"));
        assertTrue(Hangman.alreadyGuessed("aeiou", "u"));

        assertFalse(Hangman.alreadyGuessed("aeiou", "b"));
        assertFalse(Hangman.alreadyGuessed("", "a"));
    }
}