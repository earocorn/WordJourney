package wordjourney.model;

import javax.swing.*;

public class Level {
    private ImageIcon levelBackground;
    private int startingHeight;

    public Level(ImageIcon levelBackground, int startingHeight) {
        this.levelBackground = levelBackground;
        this.startingHeight = startingHeight;
    }

    public ImageIcon getLevelBackground() {
        return levelBackground;
    }

    public void setLevelBackground(ImageIcon levelBackground) {
        this.levelBackground = levelBackground;
    }

    public int getStartingHeight() {
        return startingHeight;
    }

    public void setStartingHeight(int startingHeight) {
        this.startingHeight = startingHeight;
    }

}
