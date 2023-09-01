/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.models;

import java.util.ArrayList;

/**
 *
 * @author alexalmanza
 */
public class Wordle {
    
    private String solution;
    private boolean isSolved;
    private int wordLength;
    private int maxGuesses;
    private int guessesRemaining;
    private ArrayList<CharacterInfo> characterInfo;
    
    public Wordle() {
        // TODO ?
    }
    
    public Wordle(String solution, int maxGuesses) {
        this.solution = solution;
        this.isSolved = false;
        this.wordLength = solution.length();
        this.maxGuesses = maxGuesses;
        this.guessesRemaining = maxGuesses;
    }

    private String getSolution() {
        return solution;
    }

    private void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isSolved() {
        return isSolved;
    }

    private void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public int getWordLength() {
        return wordLength;
    }

    private void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getMaxGuesses() {
        return maxGuesses;
    }

    private void setMaxGuesses(int maxGuesses) {
        this.maxGuesses = maxGuesses;
    }

    public int getGuessesRemaining() {
        return guessesRemaining;
    }

    private void setGuessesRemaining(int guessesRemaining) {
        this.guessesRemaining = guessesRemaining;
    }

    public ArrayList<CharacterInfo> getCharacterInfo() {
        return characterInfo;
    }

    private void setCharacterInfo(ArrayList<CharacterInfo> characterInfo) {
        this.characterInfo = characterInfo;
    }
    
    public void submitGuess(String guess) {
        // TODO: Implement functionality for making a guess.
    }
    
}
