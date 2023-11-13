/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.model;

import wordjourney.util.GameUtility;

import java.lang.Boolean;
/**
 * Attribute handler class
 * Used to store game data that should persist between rounds
 * Should be updated every round, and then used to get necessary information for the next round
 * @author Britton
 */
public class AttributeHandler {
    private int guessesAllowed;
    private int wordsSolved;
    private int difficulty;
    private Player player;
    
    /**
     * Default constructor - Initializes attributes for the first round of guessing
     */
    public AttributeHandler(Player player) {
        this.player = player;
        guessesAllowed = 6;
        wordsSolved = 0;
        difficulty = 0;
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
                player.setScore(guessesAllowed - attempts + 1);
                wordsSolved++;
//                mapIndex = (wordsSolved / 5) % mapCount;
                player.setCurrentLevel((wordsSolved / 5) % GameUtility.numLevels);
            }
            else {
                player.decrementLives();
            }
//            if(player.getLives() <= 0) {
//                gameOver = true;
//            }
            
            difficulty = wordsSolved;
            
            guessesAllowed = 4 + ((500) / (difficulty + 167));
            
            float tmp = difficulty;
            if((tmp = 0.002f * (tmp + 10f)) != 0f) {
                player.setTimeLeft(100 + (int)(10f / tmp));
            }
        }
    }

    /**
     * Used to get the max guesses allowed for the next word
     * @return guesses allowed
     */
    public int GetGuessesAllowed() {
        return guessesAllowed;
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
