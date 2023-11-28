package wordjourney.view;

import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.panels.LeaderBoardPanel;
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
    LeaderBoardPanel leaderBoardPanel; //extends JPanel


    /**
     * Constructor GameFrame and initializes properties
     */
    public GameFrame(){

        //set up the gameFrame
        setSize(GameUtility.windowDimension);
//        setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/ui/titlebar/wordJourneyIcon.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Word Journey");

        // setting task bar icon
        Image icon = Toolkit.getDefaultToolkit().getImage("src/assets/ui/titlebar/wordJourneyIcon.png");
        final Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setIconImage(icon);
        //setIconImage(icon);

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
     * method to show the panel depending on game state
     * @param gameState
     */
    public void setPanel(GameState gameState){
        GameState cardState = gameState == GameState.GAME_OVER ? GameState.MENU : gameState;
        if(cardState == GameState.MENU) {
            menuPanel.getTimer().restart();
        }
        cardLayout.show(mainPanel, cardState.getTitle());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public LeaderBoardPanel getLeaderBoardPanel() {
        return leaderBoardPanel;
    }

}