package wordjourney;

import wordjourney.controller.GameController;
import wordjourney.model.Player;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;

import javax.swing.*;

public class Core {
    public static GameUtility utility;
    public static GameController manager;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // load assets and important data
            utility = GameUtility.getInstance();

            // Base controller
            manager = GameController.getInstance();

            // base model
            Player player = new Player();
            manager.setPlayer(player);

            // Base view
            GameFrame gameFrame = new GameFrame();
            manager.setGameView(gameFrame);

            // TODO: Perhaps we should initialize the Wordle-MVC in here like how the whole game is set up instead of having it rely on the game panel. However it does make sense that we would only initialize the Wordle-MVC on first change of GameState from MENU to IN_GAME so its possible that the Wordle-MVC should actually be initialized on first instance of the GameState change when the player hits the start button
            /*
            Wordle-MVC as in

            wordleView = new WordleView();
            wordleModel = new WordleModel();
            wordleController = new WordleController(wordleModel, wordleView);

            which is found in the GamePanel class constructor

             */
        });
    }


}