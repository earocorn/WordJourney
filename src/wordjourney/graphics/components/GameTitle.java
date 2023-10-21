package wordjourney.graphics.components;

import wordjourney.Main;
import wordjourney.util.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTitle extends JLabel {

    private final ImageIcon title;
    public final int titleXLimitRight = 45;
    public final int titleXLimitLeft = 25;
    public final int titleYLimitTop = 120;
    public final int titleYLimitBottom = 100;
    public int titleX = 35;
    public int titleY = 110;
    public int titleXVelocity = 3;
    public int titleYVelocity = 2;

    public GameTitle() {
        // change to getCurrentGameState and use GameState as constructor parameter
        if (GameManager.getGamePanel().livesCount == 3){
            title = new ImageIcon("src/assets/title.png");
        }
        else{
            title = new ImageIcon("src/assets/gameOverTitle.png");
        }
    }

    public ImageIcon getTitle() {
        return title;
    }

}
