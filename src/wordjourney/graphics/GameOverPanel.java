package wordjourney.graphics;


import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class GameOverPanel extends JPanel{
    Image gameOverImage;

    public static JLabel gameOver;
    public GameOverPanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT ));
        gameOver = new JLabel();
        gameOver.setLayout(new GridBagLayout());
        gameOver.setVisible(true);
        gameOverImage = new ImageIcon("src/assets/gameOver.png").getImage();
        gameOver.setIcon((Icon) gameOverImage);

    }

}
