package wordjourney.model;

import wordjourney.controller.WordleController;
import wordjourney.view.components.WordleView;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer gameTimer = new Timer();
    private int remainingTimeInSeconds =180;// 3 minutes in seconds
    Player player;

    WordleModel wordleModel;
    WordleView wordleView;
    WordleController wordleController;
    public GameTimer(){
        player = new Player();
        wordleView = new WordleView();
        wordleModel = new WordleModel();
        wordleController = new WordleController(wordleModel,wordleView,player);
    }

    public void resetGameTimer() {
        System.out.println("Timer reset");
        remainingTimeInSeconds = 180; // Reset to 3 minute
        player.setTimeLeft(remainingTimeInSeconds);
    }

    public void startGameTimer() {
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTimeInSeconds > 0) {
                    remainingTimeInSeconds--;
                    player.setTimeLeft(remainingTimeInSeconds);
                } else {
                    System.out.println("Ran out of time");
                    wordleController.clearAllPanels();
                    player.decrementLives();
                    resetGameTimer();
                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    public int getTime() {
        return remainingTimeInSeconds;
    }

//    public void update(){
//        System.out.println("Time remaining: " + remainingTimeInSeconds + " seconds");
//    }
    public void stopGameTimer() {
        gameTimer.cancel();
        gameTimer.purge();
    }

}
