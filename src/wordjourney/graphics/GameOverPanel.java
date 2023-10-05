package wordjourney.graphics;


import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static wordjourney.graphics.GamePanel.gameFont;

public class GameOverPanel extends JPanel{
    ImageIcon gameOverImage;

    public static JLabel gameOver;
    JButton startButton;
    JButton quitButton;

    public GameOverPanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT ));

        gameOver = new JLabel();
        gameOver.setLayout(new GridBagLayout());
        gameOver.setVisible(true);
        gameOverImage = new ImageIcon("src/assets/pinkBG.png");
        gameOver.setIcon(gameOverImage);
        add(gameOver);

        //start button
        startButton = new JButton();
        startButton.setPreferredSize(new Dimension(237,74));
        startButton.setLocation(300, 50);
        startButton.setIcon(new ImageIcon("src/assets/startButton.png"));


        //quit button
        quitButton = new JButton();

        quitButton.setPreferredSize(new Dimension(235,74));
        quitButton.setIcon(new ImageIcon("src/assets/quitButton.png"));
        quitButton.setLocation(300, 0);

        //start button functionality
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.showNewGameScreen();
            }
        } );
        gameOver.add(startButton);

        //quit button functionality
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.quitGame();
            }
        });

        gameOver.add(quitButton);
    }
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        //score displayed on screen
        //set font and font color
        g.setColor(Color.WHITE);
        g.setFont(gameFont);
        g.drawString(" HIGH SCORE: "+ WordleComponent.score, 600, 60);
    }

}
