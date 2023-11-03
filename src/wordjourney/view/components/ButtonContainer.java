package wordjourney.view.components;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for a graphical panel that will store buttons and their functionality
 */
public class ButtonContainer extends JPanel{

    JButton startButton;
    JButton quitButton;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;

    /**
     * Constructor that initializes the button container and adds its components
     */
    public ButtonContainer() {
        startButton = new JButton();
        quitButton = new JButton();

        setLayout(new GridLayout(2, 1));

        //creates new instances of image icons
        startButtonIcon = new ImageIcon("src/assets/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.png");

        //adding the graphics to the buttons
        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        //creating functionality of the buttons to set the game state to be in game or to exit the game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().setGameState(GameState.IN_GAME);
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //adding the buttons start and quit to the button container
        setOpaque(false);
        add(startButton);
        add(quitButton);
    }
}
