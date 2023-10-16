/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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

    public static int PLAYER_MAX_HEIGHT =120;
    public static int GROUND_HEIGHT = 85;
    public static int STARTING_LIVES = 1;
    public static int STARTING_SCORE =0;


    private static Font gameFont;

    // call wherever we load assets
    public static void loadFont() {
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/supergame.ttf")).deriveFont(35f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getFont() {
        return gameFont;
    }
    
    private static AudioInputStream gameAudioInput;
    
    private static Clip gameAudioClip;
    
    public static void loadMusic() {
        try {
            gameAudioInput = AudioSystem.getAudioInputStream(new File("src/assets/gameMusic.wav").getAbsoluteFile());
            gameAudioClip = AudioSystem.getClip();
            gameAudioClip.open(gameAudioInput);
            gameAudioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Clip getClip(GameState gameState) {
        // get audio clip for whatever gamestate
        switch (gameState) {
            case IN_GAME:
                return gameAudioClip;
            default:
                throw new AssertionError();
        }
    }
    
}
