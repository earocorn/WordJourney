package wordjourney.controller;

import wordjourney.model.*;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;
import wordjourney.view.components.WordleView;
import wordjourney.view.panels.GamePanel;


/**
 * Singleton class used to manage all game objects and handle GameState updates.
 */
public class GameController {

    /**
     * Singleton object of the GameController class.
     */
    private static GameController instance = null;

    /**
     * Current game state.
     */
    private GameState gameState = GameState.MENU;

    /**
     * Current Wordle model.
     */
    private WordleModel currentWordleModel = null;

    /**
     * Current Wordle controller.
     */
    private WordleController currentWordleController = null;

    /**
     * Current Wordle view.
     */
    private WordleView currentWordleView = null;

    /**
     * Current player.
     */
    private Player player = null;

    /**
     * Current JFrame instance that contains all graphics for the game.
     */
    private GameFrame gameFrame = null;

    /**
     * Countdown timer that is used to facilitate game difficulty.
     */
    private GameTimer gameTimer = null;

    /**
     * Dummy singleton constructor.
     */
    private GameController() {}

    /**
     *  Constructs singleton if not already created.
     *
     * @return the current instance of the GameController singleton.
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }


    /**
     * Get current GameFrame in order to manipulate and get graphical data.
     *
     * @return current GameFrame, or current JFrame window.
     */
    public GameFrame getGameView() {
        return gameFrame;
    }

    /**
     * Sets the current GameFrame, or current JFrame window, if not already set.
     *
     * @param gameFrame GameFrame instance to set.
     */
    public void setGameView(GameFrame gameFrame) {
        if (this.gameFrame == null) {
            this.gameFrame = gameFrame;
            gameFrame.setPanel(gameState);
        }
    }

    /**
     * Get the current GameState to check if player is in MENU, LEADERBOARD, IN_GAME, GAME_OVER, etc.
     *
     * @return the current GameState.
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Sets the current GameState. Two main functionalities are updating the game music and updating the screen that's rendered.
     *
     * @param gameState Desired GameState to be set.
     */
    public void setGameState(GameState gameState) {
        if (this.gameState != gameState) {
            gameFrame.setPanel(gameState);
            GameUtility.getInstance().playMusic(gameState);

            switch (gameState) {
                case IN_GAME -> {
                    if (player.getLives() != GameUtility.STARTING_LIVES) {
                        player.setTimeLeft(GameUtility.STARTING_TIME);
                        player.setCurrentLevel(GameUtility.STARTING_LEVEL);
                        player.setScore(GameUtility.STARTING_SCORE);
                        player.setLives(GameUtility.STARTING_LIVES);
                        GameController.getInstance().getGameTimer().restartGameTimer();
                    } else {
                        GameController.getInstance().getGameTimer().startGameTimer();
                    }
                    if (gameTimer == null) {
                        gameTimer = new GameTimer();
                    }
                }
                case MENU -> {}
                case GAME_OVER -> {
                    gameTimer.stopGameTimer();
                    System.out.println("current player score is " + player.getScore());
                    GameUtility.getInstance().getLeaderboardData().pushEntry(player.getName(), player.getScore());
                    GameUtility.getInstance().getLeaderboardData().writeScores();
                    getGameView().getLeaderBoardPanel().getLeaderBoardView().updateLeaderboardText();
                }
                case LEADERBOARD -> getGameView().getLeaderBoardPanel().getLeaderBoardView().updateLeaderboardText();
                default -> {}
            }
        }
        this.gameState = gameState;
    }

    /**
     * Used to perform actions and get data on the current player.
     *
     * @return current player instance.
     */
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (this.player == null) {
            this.player = player;
        }
    }

    public WordleModel getCurrentWordleModel() {
        return currentWordleModel;
    }

    public void setCurrentWordleModel(WordleModel currentWordleModel) {
        this.currentWordleModel = currentWordleModel;
    }

    public WordleController getCurrentWordleController() {
        return currentWordleController;
    }

    public void setCurrentWordleController(WordleController currentWordleController) {
        this.currentWordleController = currentWordleController;
    }

    public WordleView getCurrentWordleView() {
        return currentWordleView;
    }

    public void setCurrentWordleView(WordleView currentWordleView) {
        this.currentWordleView = currentWordleView;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(GameTimer gameTimer) {
        if (this.gameTimer == null) {
            this.gameTimer = gameTimer;
        }
    }
}
