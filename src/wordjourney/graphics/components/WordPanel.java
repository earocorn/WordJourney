package wordjourney.graphics.components;

import wordjourney.Main;
import wordjourney.util.GameManager;
import wordjourney.util.Test;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import wordjourney.util.GameUtility;

/**
 * Class that creates the panel that holds the users guesses
 *
 */
public class WordPanel extends JPanel {

    JLabel[] wordColumns = new JLabel[5];

    public WordPanel() {
        Test.printObject(this);

        this.setLayout(new GridLayout(1, 5));
        Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        for (int i = 0; i < 5; i++) {
            wordColumns[i] = new JLabel();
            wordColumns[i].setSize(new Dimension(50, 50));
            wordColumns[i].setPreferredSize(new Dimension(50, 50));
            wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
            wordColumns[i].setOpaque(true);
            wordColumns[i].setBackground(Color.WHITE);
            wordColumns[i].setBorder(blackBorder);
            this.add(wordColumns[i]);
        }
    }

    //function to iterate through the rows and resets them
    public void clearWordPanel() {

        for (int i = 0; i < 5; i++) {
            wordColumns[i].setText("");
            wordColumns[i].setBackground(Color.WHITE);
        }
    }

    //function that takes the users guess and puts each letter on the wordPanel
    public void setPanelText(String charValue, int position, Color color) {
        this.wordColumns[position].setFont(GameUtility.getFont().deriveFont(22f));
        this.wordColumns[position].setText(charValue);
        this.wordColumns[position].setBackground(color);
        GameManager.move(Main.wordleGame.panel);
    }


}

