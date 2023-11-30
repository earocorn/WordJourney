package wordjourney.controller;

import wordjourney.model.*;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;
import wordjourney.view.components.WordleView;

/**
 * Class that controls most aspects of the game, including the Wordle Controller and Game State.
 */
public class GameController {
    private static GameController instance = null;
    private GameState gameState = GameState.MENU;
    private WordleModel currentWordleModel = null;
    private WordleController currentWordleController = null;
    private WordleView currentWordleView = null;
    private Player player = null;
    private GameFrame gameFrame = null;
    private GameTimer gameTimer = null;

    /**
     * constructor for GameController
     */
    private GameController() {
        System.out.println("GameController singleton has been created!");
    }
    
    /**
     * returns the current instance of the the GameController
     * @return instance
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }
    
    /**
     * returns the current Game Frame
     * @return gameFRAME
     */
    public GameFrame getGameView() {
        return gameFrame;
    }
    
    /**
     * sets the game frame to some other game frame.
     * @param gameFrame the new game frame to be set
     */
    public void setGameView(GameFrame gameFrame) {
        if (this.gameFrame == null) {
            this.gameFrame = gameFrame;
            gameFrame.setPanel(gameState);
        }
    }

    /**
     * returns the current game state
     * @return gameState
     */
    public GameState getGameState() {

        return gameState;
    }
    
    /**
     * sets the game state to some different game state
     * @param gameState game state to be swapped to
     */
    public void setGameState(GameState gameState) {
        if (this.gameState != gameState) {
            gameFrame.setPanel(gameState);
            GameUtility.getInstance().playMusic(gameState);
            switch (gameState) {
                case IN_GAME -> {
                    if (gameTimer == null) {
                        gameTimer = new GameTimer();
                    }
                    // checks if player has lost a life
                    if (player.getLives()!= GameUtility.STARTING_LIVES) {
                        player.setTimeLeft(GameUtility.STARTING_TIME);
                        player.setCurrentLevel(GameUtility.STARTING_LEVEL);
                        player.setScore(GameUtility.STARTING_SCORE);
                        player.setLives(GameUtility.STARTING_LIVES);

                        //gameTimer.resetGameTimer();
                        //restart the game timer has a resetGameTimerin it
                        gameTimer.restartGameTimer();
                    }
                    //player got the word correctly we still need to restart the timer!!
                    else {
                        gameTimer.resetTime();
                        gameTimer.startGameTimer();
                    }

                    break;
                }
                case MENU -> {
                    break;
                }
                case GAME_OVER -> {
                    gameTimer.stopGameTimer();
                    System.out.println("current player score is " + player.getScore());
                    GameUtility.getInstance().getLeaderboardData().pushEntry(player.getName(), player.getScore());
                    GameUtility.getInstance().getLeaderboardData().writeScores();
                    getGameView().getLeaderBoardPanel().getLeaderBoardView().updateLeaderboardText();

                    //reset wordle panel
                    getCurrentWordleController().clearAllPanels();
                    break;
                }
                case LEADERBOARD -> {
                    getGameView().getLeaderBoardPanel().getLeaderBoardView().updateLeaderboardText();
                }
                default -> {

                }
            }
        }
        this.gameState = gameState;
    }

    /**
     * get an instance of a player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set an instance of a player to a specific player
     * @param player player to be set as
     */
    public void setPlayer(Player player) {
        if (this.player == null) {
            this.player = player;
        }
    }
    
    /**
     * returns current wordle model
     * @return currentWordleModel
     */
    public WordleModel getCurrentWordleModel() {
        return currentWordleModel;
    }

    /**
     * sets the current wordle model
     * @param currentWordleModel current wordle model
     */
    public void setCurrentWordleModel(WordleModel currentWordleModel) {
        this.currentWordleModel = currentWordleModel;
    }

    /**
     * returns the current wordle controller
     * @return currentWordleController
     */
    public WordleController getCurrentWordleController() {
        return currentWordleController;
    }

    /**
     * sets the current wordle controller
     * @param currentWordleController 
     */
    public void setCurrentWordleController(WordleController currentWordleController) {
        this.currentWordleController = currentWordleController;
    }

    /**
     * returns the current wordle view
     * @return currentWordleView
     */
    public WordleView getCurrentWordleView() {
        return currentWordleView;
    }

    /**
     * sets the current wordle view
     * @param currentWordleView current wordle view
     */
    public void setCurrentWordleView(WordleView currentWordleView) {
        this.currentWordleView = currentWordleView;
    }
    
    /**
     * returns the current game timer
     * @return gameTimer
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * sets the game timer to a specific game timer.
     * @param gameTimer timer that needs to be set
     */
    public void setGameTimer(GameTimer gameTimer) {
        if (this.gameTimer == null) {
            this.gameTimer = gameTimer;
        }
    }
}
