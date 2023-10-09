package wordjourney.graphics;


import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class MenuPanel extends JPanel implements ActionListener {

    public static JLabel menu;
    JPanel buttonContainer;
    JButton startButton;
    JButton quitButton;
    ImageIcon menuBG;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;
    GameTitle gameTitle;
    Timer newTimer;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT));
        newTimer = new Timer(10, this);
        setLayout(new BorderLayout());

        //create new instances of components
        gameTitle = new GameTitle();
//        gameTitle.startAnimation();
        add(gameTitle);
        menu = new JLabel();
        buttonContainer = new JPanel();
        startButton = new JButton();
        quitButton = new JButton();

        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));
        buttonContainer.setLayout(new GridLayout(2, 1));
        menu.setVisible(true);

        menuBG = new ImageIcon("src/assets/pinkBG.png");
        startButtonIcon = new ImageIcon("src/assets/startButton.PNG");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.PNG");

        menu.setIcon(menuBG);

        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        //start and quit button functionality
        startButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                GameManager.showNewGame();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                GameManager.quitGame();
            }
        });

        buttonContainer.setOpaque(true);
        buttonContainer.add(startButton);
        buttonContainer.add(quitButton);

        menu.add(buttonContainer);
        add(menu);

        buttonContainer.setVisible(true);
        newTimer.start();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);

        //draw menu background
        g.drawImage(menuBG.getImage(), 0, 0, getWidth(), getHeight(), this);

        g.drawImage(gameTitle.getTitle().getImage(), gameTitle.titleX, gameTitle.titleY, null);
        //draw the buttons
        buttonContainer.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("X: " + gameTitle.titleX + ", Y: " + gameTitle.titleY);
        if ((gameTitle.titleX > gameTitle.titleXLimitRight) || (gameTitle.titleX < gameTitle.titleXLimitLeft)) {
            gameTitle.titleXVelocity -= gameTitle.titleXVelocity * 2;
        }
        if (gameTitle.titleY > gameTitle.titleYLimitTop || gameTitle.titleY <= gameTitle.titleYLimitBottom) {
            gameTitle.titleYVelocity -= gameTitle.titleYVelocity * 2;
        }
        gameTitle.titleX += gameTitle.titleXVelocity;
        gameTitle.titleY += gameTitle.titleYVelocity;

        repaint();
    }


}

