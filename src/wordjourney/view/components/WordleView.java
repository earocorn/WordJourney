package wordjourney.view.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class WordleView extends JPanel {

    private final InputComponent input;
    private final WordComponent[] wordPanelArray = new WordComponent[6];

    public WordleView(){

        JPanel wordleContainer = new JPanel(new GridLayout(7, 1));
        //wordleContainer.setOpaque(false);
        //wordleContainer.setBackground(new Color(0, 0, 0, 0));

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