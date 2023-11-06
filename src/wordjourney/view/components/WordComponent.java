package wordjourney.view.components;

<<<<<<< HEAD:src/wordjourney/graphics/WordPanel.java
import wordjourney.Main;
import wordjourney.util.GameManager;
=======

import wordjourney.util.GameUtility;

>>>>>>> 605d5689b710feb124daed22cef01616fc20f42d:src/wordjourney/view/components/WordComponent.java
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * A class used to represent a graphical panel with 5 columns for displaying 5-letter words
 *
 */
public class WordComponent extends JPanel {

    /** an array to represent the columns**/
    JLabel[] wordColumns = new JLabel[5];

<<<<<<< HEAD:src/wordjourney/graphics/WordPanel.java
    public WordPanel() {

=======
    /**Constructor WordComponent and initializes components**/
    public WordComponent() {
>>>>>>> 605d5689b710feb124daed22cef01616fc20f42d:src/wordjourney/view/components/WordComponent.java
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

    /**Clears the content of the wordPanel by setting each column to empty text & white background**/
    public void clearWordPanel() {
        for (int i = 0; i < 5; i++) {
            wordColumns[i].setText("");
            wordColumns[i].setBackground(Color.WHITE);
        }
    }

    /**
     * method to set the text and background color of a specific column in the wordPanel
     *
     * @param charValue the character/text displayed in  the column
     * @param position the index of the column
     * @param color  the background color of the column
     */
    public void setPanelText(String charValue, int position, Color color) {
        this.wordColumns[position].setFont(GameUtility.getFont().deriveFont(22f));
        this.wordColumns[position].setText(charValue);
        this.wordColumns[position].setBackground(color);

    }


}