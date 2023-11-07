/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.model;

import java.lang.Boolean;
/**
 * Attribute handler class
 * Used to store game data that should persist between rounds
 * Should be updated every round, and then used to get necessary information for the next round
 * @author Britton
 */
public class AttributeHandler {
    
    private int score;
    private int lives;
    private int timeLimit;
    private int guessesAllowed;
    private int wordsSolved;
    private int difficulty;
    private int mapIndex;
    private int mapCount;
    private boolean gameOver;
    
    /**
     * Default constructor - Initializes attributes for the first round of guessing
     */
    public AttributeHandler() {
        score = 0;
        lives = 3;
        timeLimit = 600;
        guessesAllowed = 6;
        wordsSolved = 0;
        difficulty = 0;
        mapIndex = 0;
        mapCount = 11;
        gameOver = false;
    }
    
        public AttributeHandler(int MapCount) {
        score = 0;
        lives = 3;
        timeLimit = 600;
        guessesAllowed = 6;
        wordsSolved = 0;
        difficulty = 0;
        mapIndex = 0;
        mapCount = MapCount;
        gameOver = false;
    }
        
    /**
     * Called by the game whenever the player finishes a round of guessing
     * Either by A. guessing correctly, B. using up all guesses, C. running out of time
     * This function should be called before getting any attributes each round
     * @param attempts
     * @param solvedWord 
     */
    public void UpdateAttributes(int attempts, boolean solvedWord) {
        
        if(IsInGuessRange(attempts)) {
            
            if(solvedWord) {
                score += guessesAllowed - attempts + 1;
                wordsSolved += 1;
                mapIndex = (wordsSolved / 5) % mapCount;
            }
            else {
                lives -= 1;
            }
            if(lives <= 0) {
                gameOver = true;
            }
            
            difficulty = wordsSolved;
            
            guessesAllowed = 4 + ((500) / (difficulty + 167));
            
            float tmp = difficulty;
            if((tmp = 0.002f * (tmp + 10f)) != 0f) {
                timeLimit = 100 + (int)(10f / tmp);
            }
        }
    }
    /**
     * Used to get the players score for display and logging
     * @return score
     */
    public int GetScore() {
        return score;
    }
    /**
     * Used to get the player's remaining lives for display
     * @return lives
     */
    public int GetLives() {
        return lives;
    }
    /**
     *  Used to get the time limit for the next word
     * @return timeLimit in seconds
     */
    public int GetTimeLimit() {
        return timeLimit;
    }
    /**
     * Used to get the max guesses allowed for the next word
     * @return guesses allowed
     */
    public int GetGuessesAllowed() {
        return guessesAllowed;
    }
    /**
     * Used to get the index of the background map that should be displayed
     * @return map index
     */
    public int GetMapIndex() {
        return mapIndex;
    }
    /**
     * Used to tell if the game should be over
     * @return game over boolean
     */
    public boolean IsGameOver() {
        return gameOver;
    }
    
    boolean IsInGuessRange(int attempts) {
        
        if(attempts > 0 && attempts <= guessesAllowed) {
            return true;
        }
        else {
            return false;
        }
    }
}
