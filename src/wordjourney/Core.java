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
        });
    }


}