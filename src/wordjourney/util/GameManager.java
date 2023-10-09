/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import wordjourney.graphics.GameOverPanel;
import wordjourney.graphics.GamePanel;
import wordjourney.Main;
import wordjourney.graphics.WordleGame;

import java.awt.event.WindowEvent;

/**
 *
 * @author alexalmanza
 */
public class GameManager {
    
    static GameOverPanel gameOverPanel;
    
    // should add parameter for how far we want to move him
    public static void move(GamePanel panel) {
        panel.movePlayer();
    }
    
    public static void removeOneLife(GamePanel panel){
        panel.livesCount--;
        System.out.println("A life was lost");
        if (panel.livesCount<=0){
            showGameOverScreen();
            
            System.out.println("Out of lives");
        }
    }
    
    public static void showGameOverScreen() {
        gameOverPanel = new GameOverPanel();
        Main.gameFrame.getContentPane().removeAll();
        Main.gameFrame.getContentPane().add(gameOverPanel);
        Main.gameFrame.getContentPane().revalidate();
        Main.gameFrame.getContentPane().repaint();
    }
    
    public static void showNewGameScreen() {
//        gameOverPanel.removeAll();
//        gameOverPanel.invalidate();
//        Main.gameFrame.remove(gameOverPanel);
//        Main.wordleGame = new WordleGame(Main.gameFrame);
//        Main.wordleGame.panel.invalidate();
//        Main.wordleGame.panel.validate();
//        Main.gameFrame.invalidate();
//        Main.gameFrame.validate();
        gameOverPanel.removeAll();
        Main.gameFrame.getContentPane().removeAll();
        Main.wordleGame = new WordleGame(Main.gameFrame);
        Main.gameFrame.getContentPane().add(Main.wordleGame.panel);
        Main.gameFrame.getContentPane().revalidate();
        Main.gameFrame.getContentPane().repaint();
    }

    public static void quitGame() {
        // https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
        //Main.gameFrame.dispatchEvent(new WindowEvent(Main.gameFrame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }
    
}
