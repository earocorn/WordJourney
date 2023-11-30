package wordjourney.model;

import javax.swing.*;

/**
 * Class that loads and controls the level number and level backgrounds.
 */
public class Level {
    private ImageIcon levelBackground; // variable for the background images
    private int startingHeight; // starting height of the ground for the player to be loaded at

    /**
     * Constructor  that initalizes the level background and starting height
     * @param levelBackground
     * @param startingHeight
     */
    public Level(ImageIcon levelBackground, int startingHeight) {
        this.levelBackground = levelBackground;
        this.startingHeight = startingHeight;
    }

    /**
     * method to get the current level background
     * @return
     */
    public ImageIcon getLevelBackground() {
        return levelBackground;
    }

    /**
     * method to set the current level background
     * @param levelBackground
     */
    public void setLevelBackground(ImageIcon levelBackground) {
        this.levelBackground = levelBackground;
    }

    /**
     * method to get the starting height
     * @return
     */
    public int getStartingHeight() {
        return startingHeight;
    }

    /**
     * method to set  the starting height
     * @param startingHeight
     */
    public void setStartingHeight(int startingHeight) {
        this.startingHeight = startingHeight;
    }

}
