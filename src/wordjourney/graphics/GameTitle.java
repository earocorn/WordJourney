package wordjourney.graphics;

import wordjourney.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTitle extends JLabel {

    public static ImageIcon title;
    final int titleXLimitRight = 45;
    final int titleXLimitLeft = 25;
    final int titleYLimitTop = 120;
    final int titleYLimitBottom = 100;
    public int titleX = 35;
    public int titleY = 110;
    public int titleXVelocity = 3;
    public int titleYVelocity = 2;

    public GameTitle(){
        if (Main.wordleGame.panel.livesCount == 3){
            title= new ImageIcon("src/assets/title.png");
        }
        else{
            title = new ImageIcon("src/assets/gameOverTitle.png");
        }
        setIcon(title);

    }

    public ImageIcon getTitle() {
        return title;
    }

}
