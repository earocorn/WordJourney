package wordjourney.view;

import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.panels.LeaderBoardPanel;
import wordjourney.view.panels.GamePanel;
import wordjourney.view.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to represent a graphical frame to display three panels: gamePanel, leaderboard and menuPanel
 * uses card layout to switch between the panels
 */
public class GameFrame extends JFrame {
    JPanel mainPanel;
    CardLayout cardLayout;
    GamePanel gamePanel;
    MenuPanel menuPanel;
    LeaderBoardPanel leaderBoardPanel;


    /**
     * Constructor GameFrame and initializes properties
     * Sets up the frame window with specified dimensions, default close operation, and title.
     * Adds mainPanel to the game frame, which contains menuPanel, gamePanel, and leaderBoardPanel.
     * Uses CardLayout to manage the switching of panels.
     */
    public GameFrame(){

        //set up the gameFrame
        setSize(GameUtility.windowDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Word Journey");

        //add main panel to game frame
        mainPanel = new JPanel();
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        leaderBoardPanel = new LeaderBoardPanel();
        cardLayout = new CardLayout();

        mainPanel.setLayout(cardLayout);

        mainPanel.add(menuPanel, GameState.MENU.getTitle());
        mainPanel.add(gamePanel, GameState.IN_GAME.getTitle());
        mainPanel.add(leaderBoardPanel, GameState.LEADERBOARD.getTitle());

        add(mainPanel);

        //display the frame window
        pack();
        setVisible(true);
    }

    /**
     * Sets the current panel based on the provided game state.
     *If the game state is GAME_OVER, it switches to the MENU panel, and the menuPanel's timer is restarted.
     *@param gameState The current game state.
     **/
    public void setPanel(GameState gameState){
        GameState cardState = gameState == GameState.GAME_OVER ? GameState.MENU : gameState;
        if(cardState == GameState.MENU) {
            menuPanel.getTimer().restart();
        }
        cardLayout.show(mainPanel, cardState.getTitle());
    }

    /**
     * method to get the current game panel
     * @return gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * method to get the current leaderboard
     * @return leaderBoardPanel
     */
    public LeaderBoardPanel getLeaderBoardPanel() {
        return leaderBoardPanel;
    }

}