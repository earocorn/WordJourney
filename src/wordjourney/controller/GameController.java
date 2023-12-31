package wordjourney.controller;

import wordjourney.model.GameState;
import wordjourney.model.Player;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;


import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    private static GameController instance = null;
    private GameState gameState = GameState.MENU;
    private WordleModel currentWordle = new WordleModel();
    private Player player = null;
    private GameFrame gameFrame = null;
    

    private GameController() {
        System.out.println("GameController singleton has been created!");

        // TODO: Put other initialization here. When GameController is constructed, we are in the main menu screen so we don't need to initialize Wordle-MVC here but might need to initialize other stuff such as getting locally stored data or something, idk.
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void setGameView(GameFrame gameFrame) {
        if (this.gameFrame == null) {
            this.gameFrame = gameFrame;
            gameFrame.setPanel(gameState);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if (this.gameState != gameState) {
            // TODO: Somehow check if Wordle-MVC is initialized so that we only have to initialize everything once. This could be done using methods like if getWordleView/Model/Controller() returns null.
            // ALSO, we could put a switch-case statement here to check which GameState is being set, so we can do other stuff such as
            gameFrame.setPanel(gameState);
            GameUtility.getInstance().playMusic(gameState);
            switch (gameState) {
                case IN_GAME -> {
                    // TODO: do setup for timer here so that we dont call it on window open
                    break;
                }
                case MENU -> {

                    break;
                }
                case GAME_OVER -> {

                    break;
                }
                case LEADERBOARD -> {

                    break;
                }
                default -> {

                }
            }
        }
        this.gameState = gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (this.player == null) {
            this.player = player;
        }
    }

    public WordleModel getCurrentWordle() {
        return currentWordle;
    }

    public void setCurrentWordle(WordleModel currentWordle) {
        this.currentWordle = currentWordle;

    }
    
    
}


/**
 *
 
public class GameController {

    private static GameController instance = null;
    private GameState gameState = GameState.MENU;
    private WordleModel currentWordle = new WordleModel();
    private Player player = null;
    private GameFrame gameFrame = null;

    private GameController() {
        System.out.println("GameController singleton has been created!");

        // TODO: Put other initialization here. When GameController is constructed we are in the main menu screen so we don't need to initialize Wordle-MVC here but might need to initialize other stuff such as getting locally stored data or something idk.
    }

    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public void setGameView(GameFrame gameFrame) {
        if(this.gameFrame == null) {
            this.gameFrame = gameFrame;
            gameFrame.setPanel(gameState);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if(this.gameState != gameState) {
            // TODO: Somehow check for if Wordle-MVC is initialized so that we only have to initialize everything once. This could be using methods like if getWordleView/Model/Controller() returns null. ALSO we could put a switch-case statement here to check which GameState is being set so that we can do other stuff such as
            gameFrame.setPanel(gameState);
            GameUtility.getInstance().playMusic(gameState);
        }
        this.gameState = gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if(this.player == null) {
            this.player = player;
        }
    }

    public WordleModel getCurrentWordle() {
        return currentWordle;
    }

    public void setCurrentWordle(WordleModel currentWordle) {
        this.currentWordle = currentWordle;
    }
    * 
    */

//    public void updateWordle(WordleModel wordle, ) {
//        WordleController controller = new WordleController(wordle);
//        WordleView view = new WordleView(controller);
//        controller.loadNewWord();
//    }

