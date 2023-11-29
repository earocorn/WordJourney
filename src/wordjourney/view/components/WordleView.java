package wordjourney.view.components;

import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;

/**
 *Represents the view component for the Wordle game, responsible for displaying
 *the word panels and input component for player interaction.
 */

public class WordleView extends JPanel {

    private final InputComponent input;
    private final WordComponent[] wordPanelArray = new WordComponent[6];

    /**
     *Constructs a new WordleView, initializing the UI components.
     */
    public WordleView(){
        JPanel wordleContainer = new JPanel(new GridLayout(8, 1));
        wordleContainer.setBackground(GameUtility.TRANSPARENT);
        wordleContainer.setOpaque(false);

        input = new InputComponent();

        for (int i = 0; i < 6; i++) {
            wordPanelArray[i] = new WordComponent();
            wordPanelArray[i].setOpaque(false);
            wordPanelArray[i].setBackground(GameUtility.TRANSPARENT);
            wordleContainer.add(wordPanelArray[i]);
        }
        wordleContainer.add(input);
        setLayout(new FlowLayout());
        this.setOpaque(false);
        this.setBackground(GameUtility.TRANSPARENT);
        add(wordleContainer);
    }

    /**
     * Gets the input component associated with this WordleView.
     * @return The InputComponent instance.
     */
    public InputComponent getInput() {
        return input;
    }

    /**
     * Gets an array of word panels used in the Wordle game.
     * @return An array of WordComponent instances.
     */
    public WordComponent[] getWordPanelArray() {
        return wordPanelArray;
    }

}