package wordjourney.view.components;

import wordjourney.util.GameUtility;

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
    /**Constructor WordComponent and initializes components**/
    public WordComponent() {
        this.setLayout(new GridLayout(1, 5));
        Border blackBorder = BorderFactory.createLineBorder(new Color(200, 200, 200, 99));
        for (int i = 0; i < 5; i++) {
            wordColumns[i] = new JLabel();
            wordColumns[i].setSize(new Dimension(50, 50));
            wordColumns[i].setPreferredSize(new Dimension(50, 50));
            wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
            wordColumns[i].setOpaque(true);
            wordColumns[i].setBackground(new Color(0, 0, 0, 70));
            wordColumns[i].setBorder(blackBorder);
            this.add(wordColumns[i]);
        }
    }

    /**Clears the content of the wordPanel by setting each column to empty text & white background**/
    public void clearWordPanel() {
        for (int i = 0; i < 5; i++) {
            wordColumns[i].setText("");
            wordColumns[i].setBackground(GameUtility.TRANSPARENT);
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