package wordjourney.model;

import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.view.components.WordleView;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    Timer timer;
    Player player;
    WordleModel wordleModel;
    WordleView wordleView;
    WordleController wordleController;

    /**
     *
     */
    public GameTimer(){
        timer = new Timer();
        player = GameController.getInstance().getPlayer();
        wordleView = GameController.getInstance().getCurrentWordleView();
        wordleModel = GameController.getInstance().getCurrentWordleModel();
        wordleController = GameController.getInstance().getCurrentWordleController();
    }

    /**
     *
     */
    public void resetTime() {
        player.setTimeLeft(player.getStartTime());
    }

    /**
     * method to start the game timer and adds functionality to remove player lives if time runs out
     */
    public void startGameTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (player.getTimeLeft() > 0) {

                    System.out.println("Time remaining: " + player.getTimeLeft() + " seconds");
                    player.setTimeLeft(player.getTimeLeft() - 1);
                } else {
                    // The game is over, handle it here
                    //timer.cancel();
                    //timer.purge();
//                    GameController.getInstance().setGameState(GameState.GAME_OVER);
                    GameController.getInstance().getPlayer().decrementLives();
                    System.out.println("Ran out of Time: Lost a life");
                    GameController.getInstance().getCurrentWordleController().clearAllPanels();
                    //restartGameTimer();
                    //startGameTimer();


                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    /**
     * method to cancel the game timer when it moves from game state to another state
     */
    public void stopGameTimer() {
        timer.cancel();
        timer.purge();
    }

    public void restartGameTimer() {
        timer = new Timer();
        startGameTimer();
    }

}
