package wordjourney.model;

import wordjourney.controller.GameController;
import wordjourney.util.GameUtility;

import javax.swing.*;

/**
 * Class for the player and properties of the player, score; lives , time
 */
public class Player {


    private int xVelocity;  // Velocity of the player in the horizontal direction
    private int x; // Current x-coordinate of the player
    private int y;   // Current y-coordinate of the player
    private int yMoveLimit;    // Limit for vertical movement of the player
    private boolean ascending;     // Flag indicating whether the player is ascending in the vertical direction
    private int[] heartY;    // Array storing y-coordinates of hearts representing player lives
    private int initialHeartY; // Initial y-coordinate of hearts representing player lives
    private int[] heartYLimits;     // Array storing limits for vertical movement of each heart
    private int[] heartJumpDistances;     // Array storing jump distances for each heart
    private boolean[] heartAscending;  // Array indicating whether each heart is ascending in the vertical direction
    private int score;     // Player's current score
    private int lives;  // Player's remaining lives
    private int currentLevel;     // Player's current leve
    private int timeLeft; // time remaining
    private int startTime;  // Initial time set for the player
    private String name;     // Player's name
    private final ImageIcon playerIcon;   // Icon representing the player
    private final ImageIcon heartIcon;     // Icon representing player lives
    private boolean isRunningToNextLevel;  // Flag indicating whether the player is currently running to the next level


    /**
     * constructor for the player class
     */
    public Player() {

        // initialize player data
        this.score = GameUtility.STARTING_SCORE;
        this.lives = GameUtility.STARTING_LIVES;
        this.name = "PLAYER";
        this.startTime = GameUtility.STARTING_TIME;
        this.timeLeft = startTime;
        this.currentLevel = 0;

        // initialize player graphics
//        this.playerIcon = new ImageIcon("src/assets/ui/sprites/sprite.png");
        this.heartIcon = new ImageIcon("src/assets/ui/sprites/hearts.png");
        this.playerIcon = new ImageIcon("src/assets/ui/sprites/littleGuy.png");
        this.xVelocity = GameUtility.STARTING_PLAYER_X_VELOCITY;
        this.x = 0;
        this.y = 0;
        this.yMoveLimit = 100;
        this.ascending = false;
        //initialize  hearts graphics
        this.heartY = new int[lives];
        this.heartYLimits = new int[lives];
        this.heartJumpDistances = new int[lives];

        for(int i = 0; i < lives; i++) {
            heartJumpDistances[i] = 15;
        }
        this.heartAscending = new boolean[lives];
        this.isRunningToNextLevel = false;
    }


    /**
     * returns the players current score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the players score
     * @param score The new score to set for the player.
     */
    public void setScore(int score) {
      this.score = score;
    }

    /**
     * returns the players name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the players anme
     * @param name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the players current lives
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * sets the players current lives
     * @param lives the player has
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * returns the current time left
     * @return timeLeft
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * sets the time remaining
     * @param timeLeft remaining during the game
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }


    /**
     * returns the player icons
     * @return playerIcon
     */
    public ImageIcon getPlayerIcon() {
        return playerIcon;
    }

    /**
     * sets the current players level
     * @return currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * returns the players current level
     * @param currentLevel that the player is on
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * returns the players lives heart icon
     * @return heartIcon
     */
    public ImageIcon getHeartIcon() {
        return heartIcon;
    }

    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return ascending
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * @param ascending value
     */
    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * @return heartY
     */
    public int[] getHeartY() {
        return heartY;
    }

    /**
     * @param heartY setter
     */
    public void setHeartY(int[] heartY) {
        this.heartY = heartY;
    }

    /**
     * @return heartYLimits
     */
    public int[] getHeartYLimits() {
        return heartYLimits;
    }

    /**
     * @param heartYLimits bounds for y axis
     */
    public void setHeartYLimits(int[] heartYLimits) {
        this.heartYLimits = heartYLimits;
    }

    /**
     * @return heartJumpDistances
     */
    public int[] getHeartJumpDistances() {
        return heartJumpDistances;
    }

    /**
     * @param heartJumpDistances setter
     */
    public void setHeartJumpDistances(int[] heartJumpDistances) {
        this.heartJumpDistances = heartJumpDistances;
    }

    /**
     * @return heartAscending
     */
    public boolean[] getHeartAscending() {
        return heartAscending;
    }

    /**
     * @param heartAscending value setter
     */
    public void setHeartAscending(boolean[] heartAscending) {
        this.heartAscending = heartAscending;
    }

    /**
     * @param deltaX change in x for player movement
     * @param deltaY change in y for player moves
     */
    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * @return
     */
    public int getYMoveLimit() {
        return yMoveLimit;
    }

    /**
     * @param yMoveLimit
     */
    public void setYMoveLimit(int yMoveLimit) {
        this.yMoveLimit = yMoveLimit;
    }

    /**
     *
     * @return y
     */
    public int getInitialHeartY() {
        return initialHeartY;
    }

    /**
     * @param initialHeartY
     */
    public void setInitialHeartY(int initialHeartY) {
        this.initialHeartY = initialHeartY;
    }

    /**
     * @return
     */
    public int getXVelocity() {
        return xVelocity;
    }

    /**
     * @param xVelocity
     */
    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * method to remove lives when the player uses all the guess attempts or runs out of time
     */
    public void decrementLives() {
        if (lives > 1) {
            lives--; // Decrement lives by 1
        } else {
            lives--;
            GameController.getInstance().getGameTimer().stopGameTimer();
            GameController.getInstance().setGameState(GameState.GAME_OVER);
            System.out.println("Switched to GameState.GAME_OVER");
            return;
        }
        GameController.getInstance().getGameTimer().resetTime();
        GameController.getInstance().getCurrentWordleController().clearAllPanels();
    }

    /**
     * Checks if the player is currently running to the next level.
     * @return True if the player is running to the next level, false otherwise.
     */
    public boolean isRunningToNextLevel() {
        return isRunningToNextLevel;
    }

    /**
     * Sets whether the player is running to the next level.
     * @param runningToNextLevel True if the player is running to the next level, false otherwise.
     */
    public void setRunningToNextLevel(boolean runningToNextLevel) {
        this.isRunningToNextLevel = runningToNextLevel;
    }

    /**
     * Increases the player's score. Triggers additional actions such as exploding monsters and adjusting game time.
     */
    public void incrementScore() {
        score++;

        if (score % 2 == 0 && score != 0) {
            GameController.getInstance().getGameView().getGamePanel().explodeMonster();
            if (score <= 10) {
                // decrease the timer at an exponential rate
                startTime = (int) (GameUtility.STARTING_TIME / Math.pow(GameUtility.TIMER_EXPONENT_BASE, score)) + 30;
            } else {
                // after a few rounds, switch to a constant rate
                startTime -= GameUtility.TIMER_DECREMENT;
            }
            System.out.println("Current level = " + currentLevel);
        }

        if(score >= GameUtility.numLevels * 2) {
            GameController.getInstance().setGameState(GameState.GAME_OVER);
        }
    }

    /**
     * gets the initial start time for the game
     * @return startTime
     */
    public int getStartTime() {

        return startTime;
    }

    /**
     * sets the initial start time
     * @param startTime of each initial game
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
