package wordjourney.controller;


import wordjourney.model.*;
import wordjourney.util.GameUtility;
import wordjourney.view.components.WordComponent;
import wordjourney.view.components.WordleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class that controls most aspects of the wordle section.
 */
public class WordleController implements ActionListener, KeyListener {
    private static WordleController instance = null;

    WordleModel wordleModel;
    WordleView wordleView;
    
    Player player;

    /**
     * constructor for the wordle controller.
     * @param wordleModel
     * @param wordleView 
     */
    public WordleController(WordleModel wordleModel, WordleView wordleView){
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        player = GameController.getInstance().getPlayer();
        wordleModel.setCurrentWordle(getWordleString());
        System.out.println("Word for the day : " + wordleModel.getCurrentWordle());
        System.out.println("WordleController constructor");

        // add input listeners
        wordleView.getInput().getUserInput().addKeyListener(this);
        wordleView.getInput().getEnterButton().addActionListener(this);

    }
    /**
     * @return wordList
     */
    public String getWordleString(){
        Path path = Paths.get("src/assets/words/Words.txt");
        List<String> wordList;
        try {
            wordList = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        int position = random.nextInt(wordList.size());
        return wordList.get(position).trim().toUpperCase();
    }

    /**
     * method that checks if a word that a user inputs is equal to the solution word.
     * @param userWord word that a user inputs.
     * @return boolean flag
     */
    private boolean isWordleEqualTo(String userWord){
        List<String> wordleWordsList = Arrays.asList(wordleModel.getCurrentWordle().split(""));
        String[] userWordsArray = userWord.split("");
        List<Boolean> wordMatchesList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (wordleWordsList.contains(userWordsArray[i])) {
                if (wordleWordsList.get(i).equals(userWordsArray[i])) {
                    getActivePanel().setPanelText(userWordsArray[i], i, GameUtility.GREEN_TRANSPARENT);
                    wordMatchesList.add(true);
                } else {
                    getActivePanel().setPanelText(userWordsArray[i], i, GameUtility.YELLOW_TRANSPARENT);
                    wordMatchesList.add(false);
                }
            } else {
                getActivePanel().setPanelText(userWordsArray[i], i, GameUtility.GRAY_TRANSPARENT);
                wordMatchesList.add(false);
            }
        }
        return !wordMatchesList.contains(false);
    }

    /**
     * returns the current word panel
     * @return wordPanelArray
     */
    private WordComponent getActivePanel() {
        return wordleView.getWordPanelArray()[wordleModel.getCurrentLine()];
    }


    /**
     * method to process user input and update the word component
     */
    private void enterButtonEvent(){
        String userWord = wordleView.getInput().getUserInput().getText().trim().toUpperCase();
        wordleView.getInput().clearUserInput();

        if(userWord.length() != 5){
            // TODO: LESS IMPORTANT: Either implement something to keep the user from inputting more than 5 letters such as disabling the input box at input.length > 5 and adding backspace key listener OR implement something to display an error message that the word is over the limit or call an animation to shake the wordle panel.
            return;
        }
         boolean isCorrect = isWordleEqualTo(userWord);

        //adds 1 point to score, clears panels and resets the game time
        if (isCorrect) {
            player.incrementScore();
            clearAllPanels();
            GameController.getInstance().getGameTimer().resetTime();
            return;
        }
        //checks if users current line is over guess limit, if so removes life and clears panel
        if (wordleModel.getCurrentLine() >= 5) {
            player.decrementLives();
            GameUtility.getInstance().playSoundEffect(SoundEffect.ERROR);
            GameController.getInstance().getGameTimer().resetTime();
            return;
        }
        wordleModel.setCurrentLine(wordleModel.getCurrentLine()+1);
    }
    
    /**
     * method that clears all of the wordle panels
     */
    public void clearAllPanels() {
        for (int i = 0; i <= wordleModel.getCurrentLine(); i++) {
            wordleView.getWordPanelArray()[i].clearWordPanel();
        }
        wordleModel.setCurrentWordle(getWordleString());
        System.out.println("Word for the day : " + wordleModel.getCurrentWordle());
        wordleModel.setCurrentLine(0);
    }

    /**
     * method that starts a chain of other methods when a user enters an input word
     * @param e enter button action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        enterButtonEvent();
    }

    /**
     * method to check if a key is pressed
     * @param e key pressed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /**
     * checks if the enter key is pressed
     * @param e keyboard action
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterButtonEvent();
        }
    }
    
    /**
     * method to check if a key is released
     * @param e key released
     */
    @Override
    public void keyReleased(KeyEvent e) {}

}
