package wordjourney.graphics;

import javax.swing.*;
import java.awt.*;

class UserPanel extends JPanel {

    private JTextField userInput;
    private JButton enterButton;

    public UserPanel() {
        this.setLayout(new GridLayout(1, 1));
        userInput = new JTextField();
        this.add(userInput);
        enterButton = new JButton("ENTER");
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