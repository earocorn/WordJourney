package wordjourney.graphics;


import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import wordjourney.util.GameManager;

public class GameOverPanel extends JPanel{
    
    ImageIcon gameOverImage;

    public static JLabel gameOver;
    JButton startButton;
    JButton quitButton;
    
    public GameOverPanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT));
        
        gameOver = new JLabel();
        gameOver.setLayout(new GridBagLayout());
        gameOver.setVisible(true);
        gameOverImage = new ImageIcon("src/assets/gameOver.png");
        gameOver.setIcon( gameOverImage);
        add(gameOver);
        
        startButton = new JButton();
        quitButton = new JButton();
        startButton.setLocation(450, 150);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.showNewGameScreen();
            }
        } );
        gameOver.add(startButton);
        //System.out.println("wordjourney.graphics.GameOverPanel.<init>()");
    }

}
