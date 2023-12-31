package wordjourney.view;

import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.panels.GamePanel;
import wordjourney.view.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to represent a graphical frame to display two panels; gamePanel and menuPanel
 */
public class GameFrame extends JFrame {
    JPanel mainPanel;
    CardLayout cardLayout;
    GamePanel gamePanel; //extends JPanel
    MenuPanel menuPanel; //extends JPanel

    /**
     * Constructor GameFrame and initializes properties
     */
    public GameFrame(){

        //set up the gameFrame
        setSize(GameUtility.windowDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        //add main panel to game frame
        mainPanel = new JPanel();

        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();

        cardLayout = new CardLayout();

        mainPanel.setLayout(cardLayout);

        mainPanel.add(gamePanel, GameState.IN_GAME.getTitle());
        mainPanel.add(menuPanel, GameState.MENU.getTitle());

        add(mainPanel);

        //display the frame window
        pack();
        setVisible(true);
    }

    /**
     * method to show the panel depending on game state
     */
    public void setPanel(GameState gameState){
        cardLayout.show(mainPanel, gameState.getTitle());
    }

}