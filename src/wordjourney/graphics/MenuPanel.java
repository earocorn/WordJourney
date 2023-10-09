package wordjourney.graphics;


import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static wordjourney.graphics.GameTitle.setTitle;


public class MenuPanel extends JPanel {

    public static JLabel menu;
    JPanel buttonContainer;
    JButton startButton;
    JButton quitButton;
    ImageIcon menuBG;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;

    GameTitle gameTitle;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT));

        setLayout(new BorderLayout());

        //create new instances of components
        gameTitle = new GameTitle();
        gameTitle.startAnimation();
        menu = new JLabel();
        buttonContainer = new JPanel();
        startButton = new JButton();
        quitButton = new JButton();

        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));
        buttonContainer.setLayout(new GridLayout(2, 1));
        menu.setVisible(true);

        menuBG = new ImageIcon("src/assets/pinkBG.png");
        startButtonIcon = new ImageIcon("src/assets/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.png");

        menu.setIcon(menuBG);

        setTitle();

        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(true);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(true);
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


        revalidate();;
        repaint();

    }
    @Override
    public void paint(Graphics g){
        super.paint(g);

        //draw menu background
        g.drawImage(menuBG.getImage(), 0, 0, getWidth(), getHeight(), this);

        //draw the game Title and buttons
        gameTitle.paintComponent(g);
        buttonContainer.repaint();
    }


}

