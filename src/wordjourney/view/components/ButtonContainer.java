package wordjourney.view.components;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;

import javax.swing.*;
import java.awt.*;

/**
 * Class for a graphical panel that will store buttons and their functionality
 */
public class ButtonContainer extends JPanel{

    JButton startButton;
    JButton quitButton;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;
    JButton leaderBoardButton;
    ImageIcon leaderBoardButtonIcon;



    /**
     * Constructor that initializes the button container and adds its components
     */
    public ButtonContainer() {
        startButton = new JButton();
        quitButton = new JButton();
        leaderBoardButton = new JButton();

        setLayout(new GridLayout(1, 3));

        //creates new instances of image icons
        startButtonIcon = new ImageIcon("src/assets/ui/buttons/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/ui/buttons/quitButton.png");
        leaderBoardButtonIcon = new ImageIcon("src/assets/ui/buttons/leaderBoardButton.png");

        //adding the graphics to the buttons
        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        leaderBoardButton.setIcon(leaderBoardButtonIcon);
        leaderBoardButton.setOpaque(false);
        leaderBoardButton.setContentAreaFilled(false);
        leaderBoardButton.setBorderPainted(false);

        //creating functionality of the buttons to set the game state to be in game or to exit the game
        startButton.addActionListener(e -> GameController.getInstance().setGameState(GameState.IN_GAME));
        quitButton.addActionListener(e -> System.exit(0));
        leaderBoardButton.addActionListener(e -> GameController.getInstance().setGameState(GameState.LEADERBOARD));

        //adding the buttons start and quit to the button container
        setOpaque(false);
        add(startButton);
        add(leaderBoardButton);
        add(quitButton);
    }
}
