package wordjourney.view.components;

import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
/**
 * Class used to represent a graphical panel with a text box and enter button
 */
public class InputComponent extends JPanel{

    private final JTextField userInput;
    private final JButton enterButton;

    /**
     * Constructor to initialize UserPanel and its properties
     */
    public InputComponent(){

        this.setLayout(new GridLayout(1, 1));
        userInput = new JTextField();
        userInput.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(userInput);
        enterButton = new JButton("ENTER");
        enterButton.setVisible(true);
        enterButton.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(enterButton);
        this.getUserInput().grabFocus();
    }

    /**
     * @return userInput
     */
    public JTextField getUserInput() {
        return userInput;
    }


    /**
     * @return enterButton
     */
    public JButton getEnterButton() {
        return enterButton;
    }

    /**
     * method to clear the user input to empty text
     */
    public void clearUserInput(){
        userInput.setText("");
    }


}