package wordjourney.controller;


import wordjourney.model.Player;
import wordjourney.model.SoundEffect;
import wordjourney.model.WordleModel;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class WordleController implements ActionListener, KeyListener {
    private static WordleController instance = null;

    WordleModel currentWordle;
    WordleView wordleView;
    
    Player player;
    private Timer gameTimer = new Timer();

    public WordleController(WordleModel wordleModel, WordleView wordleView){
        this.wordleModel = wordleModel;
        this.wordleView = wordleView;
        player = GameController.getInstance().getPlayer();
        wordleModel.setCurrentWordle(getWordleString());
        System.out.println("WordleController constructor");

        // add input listeners
        wordleView.getInput().getUserInput().addKeyListener(this);
        wordleView.getInput().getEnterButton().addActionListener(this);

    }
    public static WordleController getInstance(){
        if (instance == null) {
            instance = new WordleController(new WordleModel(), new WordleView(), GameController.getInstance().getPlayer());
        }
        return instance;
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

    private boolean isWordleEqualTo(String userWord){
        List<String> wordleWordsList = Arrays.asList(currentWordle.getCurrentWordle().split(""));
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

    private WordComponent getActivePanel() {
        return wordleView.getWordPanelArray()[currentWordle.getCurrentLine()];
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
            player.incrementScore();
            clearAllPanels();
            resetTime();
            GameController.getInstance().gameTimer.resetGameTimer();
            return;
        }
        
        //checks if users current line is over guess limit, if so removes life and clears panel
        if (currentWordle.getCurrentLine() >= 5) {
            clearAllPanels();
            player.decrementLives();
            GameUtility.getInstance().playSoundEffect(SoundEffect.ERROR);
            if(player.getLives() <= 0) {
                // TODO: move this to Player class
                //GameController.getInstance().setGameState(GameState.GAME_OVER);
            }
            resetTime();
            GameController.getInstance().gameTimer.resetGameTimer();
            return;
        }
        currentWordle.setCurrentLine(currentWordle.getCurrentLine()+1);

    }

    public void clearAllPanels() {
        for (int i = 0; i <= currentWordle.getCurrentLine(); i++) {
            wordleView.getWordPanelArray()[i].clearWordPanel();
        }
        currentWordle.setCurrentWordle(getWordleString());
        System.out.println("Word for the day : " + currentWordle.getCurrentWordle());
        currentWordle.setCurrentLine(0);
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterButtonEvent();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}


    public void resetTime() {
        player.setTimeLeft(player.getStartTime()); // Reset to 3 minutes
    }

    public void startGameTimer() {
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (player.getTimeLeft() > 0) {
                    // Update your game timer UI or perform other game-related tasks here
                    System.out.println("Time remaining: " + player.getTimeLeft() + " seconds");
                    player.setTimeLeft(player.getTimeLeft() - 1);
                } else {
                    // The game is over, handle it here
                    gameTimer.cancel();
                    gameTimer.purge();
                    GameController.getInstance().setGameState(GameState.GAME_OVER);
                    System.out.println("Game Over");
                    // You can perform game over actions here
                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    public void stopGameTimer()  {
        gameTimer.cancel();
        gameTimer.purge();
    }

    public void restartGameTimer() {
        gameTimer = new Timer();
        startGameTimer();
    }

}
