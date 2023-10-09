/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import wordjourney.Main;
import wordjourney.graphics.*;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 *
 * @author alexalmanza
 */
public class GameManager {

//    static GameOverPanel gameOverPanel;
//    static StartGamePanel startGamePanel;
    static GamePanel gamePanel;
    static MenuPanel menuPanel;


    // should add parameter for how far we want to move him
    public static void move(GamePanel panel) {

        panel.movePlayer();
    }

    //function to remove hearts from little guys head
    public static void removeOneLife(GamePanel panel){

        //removes a heart above little guys head
        panel.livesCount--;
        //if lives are 0 it shows menu panel and resets points
        if (panel.livesCount == 0){
            showMenuPanel();
            resetPoints(panel);
        }
    }
    //when player gets the wordle correct increment their points by 1
    public static void addPoint(GamePanel panel){
        panel.score++;
        System.out.println("point added");
    }
    //When player loses all three lives call this method to reset their points
    public static void resetPoints(GamePanel panel){
        panel.score=0;
        System.out.println("score reset");
    }

    //show start menu panel
//    public static void showStartMenuScreen(){
//        startGamePanel = new StartGamePanel();
//        Main.gameFrame.getContentPane().removeAll();
////        Main.gameFrame.add(startGamePanel);
//        Main.gameFrame.getContentPane().add(startGamePanel);
//        Main.gameFrame.getContentPane().revalidate();
//        Main.gameFrame.getContentPane().repaint();
//
//    }

    //show a new gamePanel when user presses start from the start menu
    public static void showGamePanel(){
        gamePanel = new GamePanel();
        clearAndShowPanel(gamePanel);
    }

    // function to call menu panel
    public static void showMenuPanel(){
        menuPanel = new MenuPanel();
        clearAndShowPanel(menuPanel);
    }

    private static void clearAndShowPanel(JPanel newPanel){
        Main.gameFrame.getContentPane().removeAll();
        Main.gameFrame.getContentPane().add(newPanel);
        Main.gameFrame.getContentPane().revalidate();
        Main.gameFrame.getContentPane().repaint();
    }

    private static void clearAndClose(JPanel clearPanel){

    }

    //function to call game over panel
//    public static void showGameOverScreen() {
//        gameOverPanel = new GameOverPanel();
//        Main.gameFrame.getContentPane().removeAll();
//        Main.gameFrame.getContentPane().add(gameOverPanel);
//        Main.gameFrame.getContentPane().revalidate();
//        Main.gameFrame.getContentPane().repaint();
//    }

    //function to call for new game panel
    public static void showNewGame() {
        menuPanel.removeAll();
        Main.gameFrame.getContentPane().removeAll();
        Main.wordleGame = new WordleGame(Main.gameFrame);
        Main.gameFrame.getContentPane().add(WordleGame.panel);
        Main.gameFrame.getContentPane().revalidate();
        Main.gameFrame.getContentPane().repaint();
    }

    //function to call if user quits game
    public static void quitGame() {
        menuPanel.removeAll();
        Main.gameFrame.getContentPane().removeAll();
        Main.gameFrame.dispose();
//        System.out.println("Game Frame disposed");
        System.exit(0);
    }

}
