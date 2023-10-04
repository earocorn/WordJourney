/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import wordjourney.graphics.GamePanel;

/**
 *
 * @author alexalmanza
 */
public class GameManager {
    
    // should add parameter for how far we want to move him
    public static void move(GamePanel panel) {
        panel.movePlayer();
    }
    
    
    public static void removeOneLife(GamePanel panel){
        panel.livesCount--;
        System.out.println("A life was lost");
        if (panel.livesCount<=0){
                System.out.println("Out of lives");
        }
    }
    
}
