package wordjourney.view.components;

<<<<<<< HEAD:src/wordjourney/graphics/UserPanel.java
=======
import wordjourney.util.GameUtility;

>>>>>>> 605d5689b710feb124daed22cef01616fc20f42d:src/wordjourney/view/components/InputComponent.java
import javax.swing.*;
import java.awt.*;


<<<<<<< HEAD:src/wordjourney/graphics/UserPanel.java
    private final JTextField userInput;
    private final JButton enterButton;

    public UserPanel() {
=======
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

>>>>>>> 605d5689b710feb124daed22cef01616fc20f42d:src/wordjourney/view/components/InputComponent.java
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