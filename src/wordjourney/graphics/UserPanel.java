package wordjourney.graphics;

import wordjourney.util.Test;

import javax.swing.*;
import java.awt.*;
import wordjourney.util.GameUtility;

class UserPanel extends JPanel {

    private JTextField userInput;
    private JButton enterButton;

    public UserPanel() {
        Test.printObject(this);
        this.setLayout(new GridLayout(1, 1));
        userInput = new JTextField();
        userInput.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(userInput);
        enterButton = new JButton("ENTER");
        enterButton.setVisible(false);
        enterButton.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(enterButton);

    }
    public JTextField getUserInput() {
        return userInput;
    }
    public JButton getEnterButton() {
        return enterButton;
    }

}