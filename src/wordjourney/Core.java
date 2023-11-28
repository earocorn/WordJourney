package wordjourney;

import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.model.GameTimer;
import wordjourney.model.Player;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.GameFrame;
import wordjourney.view.components.WordleView;

import javax.swing.*;
import java.awt.*;

public class Core {
    public static GameUtility utility;
    public static GameController manager;
//    public static WordleController wordManager;

    public static void main(String[] args) {
        //// setting task bar icon
        Image icon = Toolkit.getDefaultToolkit().getImage("src/assets/ui/titlebar/wordJourneyIcon.png");
        final Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setIconImage(icon);

        SwingUtilities.invokeLater(() -> {


            // prompt username
            String playerName = (String)JOptionPane.showInputDialog(null,"Enter your name to keep track of your progress", "Enter Name", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/assets/ui/titlebar/wordJourneyIcon.png"), null, "");



            // load assets and important data
            utility = GameUtility.getInstance();

            // Base controller
            manager = GameController.getInstance();

            // base model
            Player player = new Player();
            if(playerName != null && playerName.length() < 10 && !playerName.isEmpty()) {
                player.setName(playerName);
            }
            manager.setPlayer(player);

            // game timer
            GameTimer gameTimer = new GameTimer();
            manager.setGameTimer(gameTimer);

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