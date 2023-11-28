package wordjourney.view.components;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;

import javax.swing.*;
import java.awt.*;

public class LBbuttonsContainer extends JPanel {
    JButton returnButton;
    JButton quitButton;
    ImageIcon returnButtonIcon;
    ImageIcon quitButtonIcon;

    public LBbuttonsContainer(){
        //create new instances of buttons
        returnButton = new JButton();
        quitButton = new JButton();

        setLayout(new GridLayout(1,2));

        //create new instances of icons
        returnButtonIcon = new ImageIcon("src/assets/ui/buttons/returnButton.png");
        quitButtonIcon = new ImageIcon("src/assets/ui/buttons/quitButton.png");

        //set instances of icons to buttons
        returnButton.setIcon(returnButtonIcon);
        returnButton.setOpaque(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        returnButton.addActionListener(e -> GameController.getInstance().setGameState(GameState.MENU)); //set return button to go back to the menu screen
        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        //adding the buttons to the container
        setOpaque(false);
        add(returnButton);
        add(quitButton);
    }
}
