/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import wordjourney.graphics.GameFrame;
import wordjourney.graphics.GameOverPanel;
import wordjourney.graphics.GamePanel;
import wordjourney.graphics.WordleComponent;

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
            showGameOverScreen(panel);
            
            System.out.println("Out of lives");
        }
    }
    
    public static void showGameOverScreen(GamePanel panel) {
        gameOverPanel = new GameOverPanel();
        WordleComponent.gameFrame.remove(panel);
        WordleComponent.gameFrame.add(gameOverPanel);
        WordleComponent.gameFrame.invalidate();
        WordleComponent.gameFrame.validate();
    }
    
    public static void showNewGameScreen() {
        WordleComponent.gameFrame.remove(gameOverPanel);
        WordleComponent.gameFrame.dispose();
        new WordleComponent();
    }
    
}
