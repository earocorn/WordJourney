package wordjourney.graphics;

import javax.swing.*;
import java.awt.*;
import wordjourney.util.GameUtility;

class UserPanel extends JPanel {

    private final JTextField userInput;
    private final JButton enterButton;

    public UserPanel() {
        this.setLayout(new GridLayout(1, 1));
        userInput = new JTextField();
        userInput.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(userInput);
        enterButton = new JButton("ENTER");
        enterButton.setVisible(false);
        enterButton.setFont(GameUtility.getFont().deriveFont(22f));
        this.add(enterButton);
//        this.getUserInput().grabFocus();
//        this.getUserInput().addKeyListener( );

    }
    public JTextField getUserInput() {
        return userInput;
    }
    public JButton getEnterButton() {
        return enterButton;
    }
    public void clearUserInput(){
        userInput.setText("");
    }

}