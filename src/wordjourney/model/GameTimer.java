package wordjourney.model;

import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.view.components.WordleView;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class GameTimer {
    Timer timer;
    Player player;
    WordleModel wordleModel;
    WordleView wordleView;
    WordleController wordleController;

    /**
     * Constructor for Game timer that initializes the properties of the game timer
     */
    public GameTimer(){
        timer = new Timer();
        player = GameController.getInstance().getPlayer();
//        wordleView = GameController.getInstance().getCurrentWordleView();
//        wordleModel = GameController.getInstance().getCurrentWordleModel();
//        wordleController = GameController.getInstance().getCurrentWordleController();
    }

    /**
     * method to reset the game time
     */
    public void resetTime() {
        player.setTimeLeft(player.getStartTime());
    }

    /**
     * method to start the game timer
     */
    public void startGameTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (player.getTimeLeft() > 0) {
                    //System.out.println("Time remaining: " + player.getTimeLeft() + " seconds");
                    player.setTimeLeft(player.getTimeLeft() - 1);
                } else {
                    // The game is over, handle it here
                    timer.cancel();
                    timer.purge();
                    resetTime();
//                    GameController.getInstance().setGameState(GameState.GAME_OVER);
                    GameController.getInstance().getPlayer().decrementLives();
                    //GameController.getInstance().getCurrentWordleController().clearAllPanels();
                    //restartGameTimer();

                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    /**
     * method to cancel the game timer when it moves from in game state to game over or menu state
     */
    public void stopGameTimer() {
        timer.cancel();
        timer.purge();
    }

    /**
     * method to restart the game timer when the time runs out
     */
    public void restartGameTimer() {
        timer = new Timer();
//        resetTime();
        startGameTimer();
    }

}
