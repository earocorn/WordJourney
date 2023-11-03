package wordjourney.controller;

import wordjourney.model.GameState;
import wordjourney.model.Player;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;

/**
 *
 */
public class GameController {

    private static GameController instance = null;
    private GameState gameState = GameState.MENU;
    private WordleModel currentWordle = new WordleModel();
    private Player player = null;
    private GameFrame gameFrame = null;

    private GameController() {
        System.out.println("GameController singleton has been created!");

        // TODO: initialize game to start on menu screen
    }

    public void setGameView(GameFrame gameFrame) {
        if(this.gameFrame == null) {
            this.gameFrame = gameFrame;
            gameFrame.setPanel(gameState);
        }
    }

    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if(this.gameState != gameState) {
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

//    public void updateWordle(WordleModel wordle, ) {
//        WordleController controller = new WordleController(wordle);
//        WordleView view = new WordleView(controller);
//        controller.loadNewWord();
//    }
}
