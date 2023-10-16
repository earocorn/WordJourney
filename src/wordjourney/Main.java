/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordjourney;

//import wordjourney.util.DataManager;
import wordjourney.graphics.GameFrame;
import wordjourney.graphics.WordleGame;
import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;

import javax.swing.*;

/**
 *
 * @author alexalmanza
 */
public class Main {

    public static WordleGame wordleGame;
    public static GameFrame gameFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // GameData test function
//        DataManager.test();

        SwingUtilities.invokeLater(() -> {
            GameUtility.loadFont();
            GameUtility.loadMusic();
            GameManager.init();
            gameFrame = new GameFrame();
            wordleGame = new WordleGame(gameFrame);
        });
    }
    
}
