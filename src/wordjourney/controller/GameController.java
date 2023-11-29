package wordjourney.controller;

import wordjourney.model.*;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;
import wordjourney.view.components.WordleView;

public class GameController {
    private static GameController instance = null;
    private GameState gameState = GameState.MENU;
    private WordleModel currentWordleModel = null;
    private WordleController currentWordleController = null;
    private WordleView currentWordleView = null;
    private Player player = null;
    private GameFrame gameFrame = null;
    private GameTimer gameTimer = null;


    private GameController() {
        System.out.println("GameController singleton has been created!");
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public GameFrame getGameView() {
        return gameFrame;
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
                    break;
                }
                case LEADERBOARD -> {
                    //getGameView().getLeaderBoardPanel().getLeaderBoardView().updateLeaderboardText();
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
