package wordjourney.model;

import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.view.components.WordleView;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer = new Timer();
    private int remainingTimeInSeconds =20;// 3 minutes in seconds

    Player player;
    WordleModel wordleModel;
    WordleView wordleView;
    WordleController wordleController;

    /**
     *
     */
    public GameTimer(){
        player = GameController.getInstance().getPlayer();
        wordleView = GameController.getInstance().getCurrentWordleView();
        wordleModel = GameController.getInstance().getCurrentWordleModel();
        wordleController = GameController.getInstance().getCurrentWordleController();
    }

    /**
     *
     */
    public void resetGameTimer() {
        System.out.println("Timer reset");
        remainingTimeInSeconds = 20; // Reset to 3 minute
        GameController.getInstance().getPlayer().setTimeLeft(remainingTimeInSeconds);
    }

    /**
     * method to start the game timer and adds functionality to remove player lives if time runs out
     */
    public void startGameTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds > 0) {
                    remainingTimeInSeconds--;
                } else {
                    System.out.println("Ran out of time");
                    GameController.getInstance().getPlayer().decrementLives();

                    System.out.println("Clearing panels");
                    wordleController.clearAllPanels();
                    resetGameTimer();
                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    /**
     * @return remainingTimeInSeconds for the game timer
     */
    public int getTime() {

        return remainingTimeInSeconds;
    }

    /**
     * method to cancel the game timer when it moves from game state to another state
     */
    public void stopGameTimer() {

    }

}
