package wordjourney.util;

import wordjourney.model.GameState;
import wordjourney.model.Level;
import wordjourney.model.SoundEffect;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class that contains various game-related functionality, such as loading fonts, music, levels, and managing the leaderboard.
 */
public final class GameUtility {
    private static GameUtility instance = null;
    public static final int WINDOW_WIDTH = 900; // width of the game
    public static final int WINDOW_HEIGHT = 600; // height of the game
    // this is static, we should be retrieving wordleview edge somehow from the ACTUAL wordleview component
    public static final int WORDLEVIEW_EDGE = 250;
    public static final Dimension windowDimension = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private static Font gameFont;
    private static Clip gameAudioClip;
    private static Clip soundEffectClip;

    //character settings
    public static final int STARTING_LIVES = 3;
    public static final int STARTING_SCORE = 0;
    public static final int STARTING_TIME = 180;
    public static final int TIMER_DECREMENT = 7;
    public static final double TIMER_EXPONENT_BASE = 1.2;
    public static final int STARTING_LEVEL = 0;
    public static final int STARTING_PLAYER_X_VELOCITY = 3;
    public static final int numLevels = 11;
    private static final Level[] levels = new Level[numLevels];
    public static final Color GREEN_TRANSPARENT = new Color(0, 255, 0, 220);
    public static final Color YELLOW_TRANSPARENT = new Color(255, 255, 0, 220);
    public static final Color GRAY_TRANSPARENT = new Color(100, 100, 100, 220);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 70);
    private Icon monsterIcon;
    private DataManager scoreData = null;
    
    /**
     * Custom starting heights of where to start the player's y value for each background image
     */
    private static final int[] levelsStartingHeight = {560, 540, 550, 480, 540, 485, 460, 450, 520, 458, 475};

    /**
     * Constructor for the game utility class
     */
    private GameUtility(){
        load();
    }

    /**
     * Singleton instance of the GameUtility class.
     * @return instance
     */
    public static GameUtility getInstance() {
        if(instance == null) {
            instance = new GameUtility();
        }
        return instance;
    }

    /**
     * loads game font into the game
     */
    private void loadFont() {
        try {
            System.out.println("Loading font...");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/supergame.ttf")).deriveFont(35f);
            System.out.println("Font successfully loaded");
        } catch (IOException | FontFormatException e) {
            System.out.println("Unable to load font");
            e.printStackTrace();
        }
    }


    /**
     * Returns an array of Level objects representing different game levels.
     *
     * @return Array of Level objects.
     *
     */
    public static Level[] getLevels() {
        return levels;
    }

    /**
     * Load the background images in to the array of levels
     */
    private void loadLevels() {
        System.out.println("Loading background images...");
        for(int i = 0; i < levels.length; i++) {
            String levelBackgroundPath = "src/assets/ui/levels/level" + (i+1) + ".png";
            System.out.println(levelBackgroundPath + " loaded");
            levels[i] = new Level(new ImageIcon(levelBackgroundPath), levelsStartingHeight[i]);
        }
        monsterIcon = new ImageIcon("src/assets/ui/sprites/monster7.gif");
    }

    /**
     * gets the game font used in the game
     * @return gameFont
     */
    public static Font getFont() {
        return gameFont;
    }

    /**
     * method to call all the loading functions for the game, font, music,levels leaderboard
     */
    public void load() {
        System.out.println("Loading game utilities...");
        loadFont();
        loadMusic();
        loadLevels();
        loadLeaderboard();
    }

    /**
     * loads the music for the game
     *
     */
    private void loadMusic() {
        System.out.println("Loading music...");
        try {
            AudioInputStream gameAudioInput = getGameAudioInput(GameState.MENU);
            soundEffectClip = AudioSystem.getClip();
            gameAudioClip = AudioSystem.getClip();
            gameAudioClip.open(gameAudioInput);
            gameAudioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * plays specified music based on the game state
     * @param gameState current game state
     */
    public void playMusic(GameState gameState) {
        try {
            gameAudioClip.stop();
            gameAudioClip.close();
            gameAudioClip.flush();
            gameAudioClip.open(getGameAudioInput(gameState));
            gameAudioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * plays sound effect based on the actions of the game
     * @param soundEffect
     */
    public void playSoundEffect(SoundEffect soundEffect) {
        String sound = "";
        switch (soundEffect) {
            case BOOM:
                sound = "boom.wav";
                break;
            case DING:
                sound = "ding.wav";
                break;
            case ERROR:
                sound = "error.wav";
                break;
            default:
                sound = "";
                break;
        }
        File file = new File("src/assets/audio/" + sound);
        System.out.println("File: " + file.getAbsolutePath());
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(file);
            soundEffectClip.stop();
            soundEffectClip.close();
            soundEffectClip.flush();
            soundEffectClip.open(ais);
            soundEffectClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param gameState
     * @return gameAudioClip
     */
    private static AudioInputStream getGameAudioInput(GameState gameState) throws UnsupportedAudioFileException, IOException {
        String song;
        switch (gameState) {
            case IN_GAME:
                song = "gameMusic.wav";
                break;
            case MENU:
                song = "menuMusic.wav";
                break;
            case GAME_OVER:
                // change to gameover music
                song = "gameOverMusic.wav";
                break;
            case LEADERBOARD:
                song = "menuMusic.wav";
                break;
            default:
                throw new IOException();
        }
        String audioRootPath = "src/assets/audio/" + song;
        File file = new File(audioRootPath);
        return AudioSystem.getAudioInputStream(file);
    }

    /**
     * loads the leaderboard
     */
    public void loadLeaderboard() {
        System.out.println("Loading leaderboard...");
        if (scoreData == null) {
            scoreData = new DataManager();
        }
    }

    /**
     * returns the datamanager for the leaderboard
     * @return scoreData
     */
    public DataManager getLeaderboardData() {
        if(scoreData == null) {
            System.out.println("Leaderboard data not loaded, loading now...");
            loadLeaderboard();
        }
        return scoreData;
    }

    /**
     * returns the icon that is the monster
     * @return monsterIcon
     */
    public Icon getMonsterIcon() {
        return monsterIcon;
    }

}