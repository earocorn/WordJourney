package wordjourney.graphics;


import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import wordjourney.util.GameManager;
import wordjourney.util.Test;

public class GameOverPanel extends JPanel implements ActionListener {

    Timer timer;

    ImageIcon gameOverImage;
    ImageIcon gameOverTitleIcon;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;

    public static JLabel gameOver;
    JLabel gameOverTitle;
    JPanel buttonContainer;
    JButton startButton;
    JButton quitButton;

    final int titleXLimitRight = 45;
    final int titleXLimitLeft = 25;
    final int titleYLimitTop = 120;
    final int titleYLimitBottom = 100;
    int titleX = 35;
    int titleY = 110;
    int titleXVelocity = 3;
    int titleYVelocity = 2;
    
    public GameOverPanel(){
        Test.printObject(this);

        setDoubleBuffered(true);

        Timer testTimer = new Timer(30, this);
        testTimer.start();
        timer = new Timer(30, this);
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT));
        
        gameOver = new JLabel();
        gameOverTitle = new JLabel();
        buttonContainer = new JPanel();
        startButton = new JButton();
        quitButton = new JButton();

        gameOver.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));
        buttonContainer.setLayout(new GridLayout(2, 1));
        gameOver.setVisible(true);

        gameOverImage = new ImageIcon("src/assets/gameOverBackground.png");
        gameOverTitleIcon = new ImageIcon("src/assets/gameOverTitle.png");
        startButtonIcon = new ImageIcon("src/assets/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.png");

        gameOver.setIcon( gameOverImage);
        gameOverTitle.setIcon(gameOverTitleIcon);


        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        buttonContainer.setOpaque(false);
        buttonContainer.add(startButton);
        buttonContainer.add(quitButton);

        add(gameOver);

        startButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {GameManager.showNewGameScreen();}});
        quitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {GameManager.quitGame();}});

        gameOver.add(buttonContainer);
        timer.start();
        //System.out.println("wordjourney.graphics.GameOverPanel.<init>()");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(gameOverTitleIcon.getImage(), titleX, titleY, null);

        // to draw high score number
        g2D.setFont(GameUtility.gameFont);
        g2D.setColor(Color.BLACK);
        g2D.drawString("500", 790, 55);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(titleX > titleXLimitRight || titleX <= titleXLimitLeft) {
            titleXVelocity -= (titleXVelocity + titleXVelocity);
        }
        if(titleY > titleYLimitTop || titleY <= titleYLimitBottom) {
            titleYVelocity -= (titleYVelocity + titleYVelocity);
        }
        titleX += titleXVelocity;
        titleY += titleYVelocity;
        repaint();
    }
}
