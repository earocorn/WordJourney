package wordjourney.model;

import wordjourney.controller.GameController;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The GameTimer class represents the main timer for the Word Journey game. It is responsible for managing
 * the game time, handling starting, restarting, and resetting the timer as needed during gameplay.
 */
public class GameTimer {
    Timer timer;
    Player player;

    /**
     * Constructs a new GameTimer instance, initializing the timer and obtaining the player from the GameController.
     */
    public GameTimer() {
        timer = new Timer();
        player = GameController.getInstance().getPlayer(); //player instance from game controller
    }

    /**
     * method to reset the game time to the starting time
     */
    public void resetTime() {
        System.out.println("Reset Game Timer");
        player.setTimeLeft(player.getStartTime());
    }

    /**
     * method to start the game timer and handles updating time left and decrements players lives
     */
    public void startGameTimer() {
        System.out.println("New Game Timer Started");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (player.getTimeLeft() > 0) {
                    player.setTimeLeft(player.getTimeLeft() - 1);  //sets the player time to  decrement by 1
                } else {
                    stopGameTimer();
                    //resetTime(); //resets time to the starting time
                    GameController.getInstance().getPlayer().decrementLives(); //decreases 1 life if player runs out of time
                    restartGameTimer();
                }
            }
        }, 0, 1000); // Start the timer with a 1-second delay and repeat every 1 second
    }

    /**
     * Stops the game timer when transitioning from the in-game state to the game over or menu state.
     */
    public void stopGameTimer() {
        if (timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    /**
     * Method to create a new instance of timer when necessary in the game: time runs out, starts new game, moves to another wordle
     */
    public void restartGameTimer() {
        resetTime();
        timer = new Timer();
        startGameTimer();
    }

}
