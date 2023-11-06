package wordjourney.view.components;

import javax.swing.*;
import java.awt.*;

public class WordleView extends JPanel {

    private final InputComponent input;
    private final WordComponent[] wordPanelArray = new WordComponent[6];

    public WordleView(){

        JPanel wordleContainer = new JPanel(new GridLayout(7, 1));
        input = new InputComponent();

        for (int i = 0; i < 6; i++) {
            wordPanelArray[i] = new WordComponent();
            wordPanelArray[i].setOpaque(false);
            wordPanelArray[i].setBackground(new Color(0, 0, 0, 50));
            wordleContainer.add(wordPanelArray[i]);
        }
        wordleContainer.add(input);
        setLayout(new FlowLayout());
        add(wordleContainer);
    }

    public InputComponent getInput() {
        return input;
    }

    public WordComponent[] getWordPanelArray() {
        return wordPanelArray;
    }

}