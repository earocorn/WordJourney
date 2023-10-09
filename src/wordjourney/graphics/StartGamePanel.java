package wordjourney.graphics;

import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGamePanel extends JPanel implements  ActionListener{

    ImageIcon startGameImage;
    ImageIcon titleIcon;
//    ImageIcon pinkBubbleIcon;
    JLabel title;
//    JLabel pinkBubble;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;
    public static JLabel startGame;
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
    Timer timer;

    public StartGamePanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT ));

        Timer testTimer = new Timer(30, this);
        testTimer.start();
        timer = new Timer(30, this);

        startGame = new JLabel();
        startButton = new JButton();
        quitButton = new JButton();
        buttonContainer = new JPanel();
        title = new JLabel();
//        pinkBubble = new JLabel();

        startGame.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));
        buttonContainer.setLayout(new GridLayout(2,1));
        startGame.setVisible(true);

//        pinkBubbleIcon = new ImageIcon("src/assets/pinkBubble.png");
        titleIcon = new ImageIcon("src/assets/title.png");
        startGameImage = new ImageIcon("src/assets/pinkBG.png");

        startButtonIcon = new ImageIcon("src/assets/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.png");

        startGame.setIcon(startGameImage);
        title.setIcon(titleIcon);

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


        add(startGame);

        //start button functionality
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GameManager.showGamePanel();
            }
        } );
        startGame.add(startButton);

        //quit button functionality
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.quitGame();
            }
        });

        startGame.add(quitButton);
        timer.start();
    }
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(titleIcon.getImage(), titleX, titleY, null);

        //set font and font color
        g2D.setColor(Color.WHITE);
        g2D.setFont(GameUtility.getFont());

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
