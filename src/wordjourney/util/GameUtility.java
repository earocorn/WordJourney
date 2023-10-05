/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import wordjourney.Main;
import wordjourney.listeners.GameAnimationListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author alexalmanza
 */
public class GameUtility {
    
    // store these kind of global variables here to make stuff easier
    
    public static final int WINDOW_WIDTH = 900;    
    public static final int WINDOW_HEIGHT = 600;
    public static int PLAYER_X = 0;
    public static int PLAYER_Y = 0;
    public static int PLAYER_VELOCITY_X = 1;
    public static int PLAYER_VELOCITY_Y = 0;
    
    public static int GROUND_HEIGHT = 85;
    
    public static int STARTING_LIVES = 3;

    public static Font gameFont;

    // call wherever we load assets
    public static void loadFont() {
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/supergame.ttf")).deriveFont(35f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    
}
