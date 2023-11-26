package wordjourney.util;

import wordjourney.model.GameState;
import wordjourney.model.Level;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that contains
 */
public final class GameUtility {
    private static GameUtility instance = null;
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;
    // this is static, we should be retrieving wordleview edge somehow from the ACTUAL wordleview component
    public static final int WORDLEVIEW_EDGE = 250;
    public static final Dimension windowDimension = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private static Font gameFont;
    private static Clip gameAudioClip;

    //character settings
    public static final int STARTING_LIVES = 3;
    public static final int STARTING_SCORE = 0;
    public static final int numLevels = 11;
    private static final Level[] levels = new Level[numLevels];

    public static final Color GREEN_TRANSPARENT = new Color(0, 255, 0, 220);
    public static final Color YELLOW_TRANSPARENT = new Color(255, 255, 0, 220);
    public static final Color GRAY_TRANSPARENT = new Color(80, 80, 80, 220);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 220);
    
    public final DataManager scoreData = new DataManager();
    public ArrayList<String> scores = new ArrayList<>();
    
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


    public static Level[] getLevels() {
        return levels;
    }

    /**
     * Load the background images in to the array of levels
     */
    private void loadLevels() {
        System.out.println("Loading background images...");
        for(int i = 0; i < levels.length; i++) {
            String levelBackgroundPath = "src/assets/ui/levels/level" + (i+1)+ ".png";
            System.out.println(levelBackgroundPath + " loaded");
            levels[i] = new Level(new ImageIcon(levelBackgroundPath), levelsStartingHeight[i]);
        }
    }

    /**
     * @return gameFont
     */
    public static Font getFont() {
        return gameFont;
    }

    public void load() {
        System.out.println("Loading game utilities...");
        loadFont();
        loadMusic();
        loadLevels();
        scores = scoreData.getScores();
    }

    /**
     * loads the music for the game
     */
    private void loadMusic() {
        System.out.println("Loading music...");
        try {
            AudioInputStream gameAudioInput = getGameAudioInput(GameState.MENU);
            gameAudioClip = AudioSystem.getClip();
            gameAudioClip.open(gameAudioInput);
            gameAudioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                song = "menuMusic.wav";
                break;
            default:
                throw new IOException();
        }
        String audioRootPath = "src/assets/audio/" + song;
        File file = new File(audioRootPath);
        return AudioSystem.getAudioInputStream(file);
    }





}