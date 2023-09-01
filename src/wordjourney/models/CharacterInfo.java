/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.models;

/**
 *
 * @author alexalmanza
 */
public class CharacterInfo {
    private char character;
    private boolean inWord;
    private boolean correctIndex;

    public CharacterInfo(char character, boolean inWord, boolean correctIndex) {
        this.character = character;
        this.inWord = inWord;
        this.correctIndex = correctIndex;
    }

    public char getChar() {
        return character;
    }

    public boolean isInWord() {
        return inWord;
    }

    public boolean isCorrectIndex() {
        return correctIndex;
    }
        
}