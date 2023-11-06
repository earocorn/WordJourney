package wordjourney.controller;


import wordjourney.model.WordleModel;
import wordjourney.view.components.WordComponent;
import wordjourney.view.components.WordleView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import wordjourney.model.Player;
import wordjourney.model.GameState;

/**
 *
 */
public class WordleController implements ActionListener, KeyListener {
    WordleModel wordleModel;
    WordleView wordleView;
    
    Player player;
    
    GameState gameState;
    private Timer gameTimer = new Timer();
    private int remainingTimeInSeconds = 180; // 3 minutes in seconds

    public WordleController(WordleModel wordleModel, WordleView wordleView, Player player){
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        this.player = player;
        wordleModel.setCurrentWordle(getWordleString());
        startGameTimer();
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
        List<String> wordList = new ArrayList<>();
        try {
            wordList = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random();
        int position = random.nextInt(wordList.size());
        return wordList.get(position).trim().toUpperCase();
        
    }

    private boolean isWordleEqualTo(String userWord){
        List<String> wordleWordsList = Arrays.asList(wordleModel.getCurrentWordle().split(""));
        String[] userWordsArray = userWord.split("");
        List<Boolean> wordMatchesList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (wordleWordsList.contains(userWordsArray[i])) {
                if (wordleWordsList.get(i).equals(userWordsArray[i])) {
                    getActivePanel().setPanelText(userWordsArray[i], i, Color.GREEN);
                    wordMatchesList.add(true);
                } else {
                    getActivePanel().setPanelText(userWordsArray[i], i, Color.YELLOW);
                    wordMatchesList.add(false);
                }
            } else {
                getActivePanel().setPanelText(userWordsArray[i], i, Color.GRAY);
                wordMatchesList.add(false);
            }
        }
        return !wordMatchesList.contains(false);
    }

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

        if (isCorrect) {
            // TODO: Implement game logic to update player's score
            clearAllPanels();
            resetGameTimer();
        } 
            
        
        //checks if users current line is over guess limit, if so removes life and clears panel
        if (wordleModel.getCurrentLine() >= 5) {
            // TODO: Implement game logic for player losing a life with proper error checking
            clearAllPanels();
            player.decrementLives();
            resetGameTimer();
            return;
        }
        wordleModel.setCurrentLine(wordleModel.getCurrentLine()+1);

    }

    private void clearAllPanels() {
        for (int i = 0; i <= wordleModel.getCurrentLine(); i++) {
            wordleView.getWordPanelArray()[i].clearWordPanel();
        }
        wordleModel.setCurrentWordle(getWordleString());
        System.out.println("Word for the day : " + wordleModel.getCurrentWordle());
        wordleModel.setCurrentLine(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        enterButtonEvent();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterButtonEvent();
            System.out.println("jump key");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    
    private void resetGameTimer() {
        remainingTimeInSeconds = 180; // Reset to 3 minutes
    }

    private void startGameTimer() {
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds > 0) {
                    // Update your game timer UI or perform other game-related tasks here
                    System.out.println("Time remaining: " + remainingTimeInSeconds + " seconds");
                    remainingTimeInSeconds--;
                } else {
                    // The game is over, handle it here
                    gameTimer.cancel();
                    gameTimer.purge();
                    System.out.println("Game Over");
                    // You can perform game over actions here
                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    private void stopGameTimer() {
        gameTimer.cancel();
        gameTimer.purge();
    }
    
    private void setGameState(GameState gameState) {
        if (this.gameState != gameState) {
            if (gameState == GameState.IN_GAME) {
                // Start the WordleController timer when entering the IN_GAME state
                startGameTimer();
            } else {
                // Stop the WordleController timer when leaving the IN_GAME state
                stopGameTimer();
                // Reset the WordleController timer if needed
                resetGameTimer();
            }
    }
    this.gameState = gameState;
}

}
