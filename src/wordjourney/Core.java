package wordjourney;

import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.model.Player;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;
import wordjourney.view.components.WordleView;

import javax.swing.*;

public class Core {
    public static GameUtility utility;
    public static GameController manager;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // prompt username
            String playerName = (String)JOptionPane.showInputDialog("Enter your name to keep track of your progress");

            // load assets and important data
            utility = GameUtility.getInstance();

            // Base controller
            manager = GameController.getInstance();

            // base model
            Player player = new Player();
            if(playerName.length() < 10 && !playerName.isEmpty()) {
                player.setName(playerName);
            }
            manager.setPlayer(player);

            // wordle mvc init
            WordleModel wordleModel = new WordleModel();
            WordleView wordleView = new WordleView();
            WordleController wordleController = new WordleController(wordleModel, wordleView);
            manager.setCurrentWordleModel(wordleModel);
            manager.setCurrentWordleView(wordleView);
            manager.setCurrentWordleController(wordleController);

            // Base view
            GameFrame gameFrame = new GameFrame();
            manager.setGameView(gameFrame);
        });
    }


}