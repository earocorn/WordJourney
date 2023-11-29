package wordjourney.util;

import wordjourney.model.GameState;
import wordjourney.model.Level;
import wordjourney.model.SoundEffect;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utility class that contains various game-related functionality, such as loading fonts, music, levels, and managing the leaderboard.
 */
public final class GameUtility {
    public ClassLoader getCl() {
        return cl;
    }
    private final ClassLoader cl = this.getClass().getClassLoader();
    private static GameUtility instance = null;  //
    public static final int WINDOW_WIDTH = 900; // width of the game
    public static final int WINDOW_HEIGHT = 600; // height of the game
    public static final int WORDLEVIEW_EDGE = 250;
    public static final Dimension windowDimension = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT); // game frame  dimensions
    private static Font gameFont; // font for the game
    private static Clip gameAudioClip; //game audio clip
    private static Clip soundEffectClip;  //sound effect audio clip

    //character settings
    public static final int STARTING_LIVES = 3; //players starting live
    public static final int STARTING_SCORE = 0; // players initial score when starting game
    public static final int STARTING_TIME = 180; // 3 minute initial start of game time
    public static final int TIMER_DECREMENT = 7; // number the timer decrements by when you reach new level
    public static final double TIMER_EXPONENT_BASE = 1.2; // number the timer uses when reaching a new level to modify inital start time
    public static final int STARTING_LEVEL = 0; //initial starting level
    public static final int STARTING_PLAYER_X_VELOCITY = 3; //players initial x velocity when moving left and right on the screen
    public static final int numLevels = 11; // total number of levels in the game
    private static final Level[] levels = new Level[numLevels]; // array to store levels, and the level the player is on
    public static final Color GREEN_TRANSPARENT = new Color(0, 255, 0, 220); //variable that stores color code for the correct letter in correct position
    public static final Color YELLOW_TRANSPARENT = new Color(255, 255, 0, 220); // variable that stores color code for the correct letter in wrong position
    public static final Color GRAY_TRANSPARENT = new Color(100, 100, 100, 220); // variable that stores color code for incorrect color in incorrect position
    public static final Color TRANSPARENT = new Color(0, 0, 0, 70);
    private Icon monsterIcon; // variable for monster image icon
    private DataManager scoreData = null; // object of data manager class that stores score and player name
    
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
     * method to play music when in a game state
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
        String sound = switch (soundEffect) {
            case BOOM -> "boom.wav";
            case DING -> "ding.wav";
            case ERROR -> "error.wav";
            default -> "";
        };
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
     * method to switch between audio depending on the current game state: menu, in game, leaderboard and game over
     * @param gameState
     * @return gameAudioClip
     */
    private static AudioInputStream getGameAudioInput(GameState gameState) throws UnsupportedAudioFileException, IOException {
        String song = switch (gameState) {
            case IN_GAME -> "gameMusic.wav";
            case MENU, LEADERBOARD -> "menuMusic.wav";
            case GAME_OVER -> "gameOverMusic.wav";
            default -> throw new IOException();
        };
        String audioRootPath = "src/assets/audio/" + song;
        File file = new File(audioRootPath);
        return AudioSystem.getAudioInputStream(file);
    }

    /**
     * loads the leaderboard and creates new instance of DataManager class if one was not created
     */
    public void loadLeaderboard() {
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