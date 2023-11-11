package wordjourney.view.components;

import wordjourney.util.GameUtility;

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
        // TODO: PROBABLY DIFFICULT: Figure out an algorithm to let user enter words one at a time in each JLabel of WordComponent. Might have to change them to JTextFields but they will look thet same. This should sort of be like how normal wordle works. This will make our game look cleaner and more interactive. Use things such as grabFocus() and grab() to set focus state of a Swing component and iterate through the letter components in WordComponent to change the focus after each letter has been input. Any help on this is appreciated :p
        // TODO: EASY: Figure out how to change opacity of entirety of the wordle view so that we can put wordle monster behind it.

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

    public InputComponent getInput() {
        return input;
    }

    public WordComponent[] getWordPanelArray() {
        return wordPanelArray;
    }

}