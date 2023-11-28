package wordjourney.model;

import wordjourney.util.GameUtility;
import wordjourney.controller.GameController;
import wordjourney.view.components.LeaderBoardView;

import javax.swing.*;

/**
 * Class for the player and properties of the player, score; lives , time
 */
public class Player {

    private int xVelocity;
    private int x;
    private int y;
    private int yMoveLimit;
    private boolean ascending;
    private int[] heartY;
    private int initialHeartY;
    private int[] heartYLimits;
    private int[] heartJumpDistances;
    private boolean[] heartAscending;
    private int score;
    private int lives;
    private int currentLevel;
    private int timeLeft;
    private int startTime;
    private String name;
    private final ImageIcon playerIcon;
    private final ImageIcon heartIcon;
    private boolean isRunningToNextLevel;


    /**
     * @constructor for the player class
     */
    public Player() {
        //leaderBoardView = LeaderBoard.getInstance().getLeaderBoardPanel.getLeaderBoardView();

        // player data
        this.score = GameUtility.STARTING_SCORE;
        this.lives = GameUtility.STARTING_LIVES;
        this.name = "PLAYER";
        this.startTime = GameUtility.STARTING_TIME;
        this.timeLeft = startTime;
        this.currentLevel = 0;

        // guy graphics
//        this.playerIcon = new ImageIcon("src/assets/ui/sprites/sprite.png");
        this.heartIcon = new ImageIcon("src/assets/ui/sprites/hearts.png");
        this.playerIcon = new ImageIcon("src/assets/ui/sprites/littleGuy.png");
        this.xVelocity = GameUtility.STARTING_PLAYER_X_VELOCITY;
        this.x = 0;
        this.y = 0;
        this.yMoveLimit = 100;
        this.ascending = false;
        // hearts graphics
        this.heartY = new int[lives];
        //this.initialHeartY;
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
        // I think this is the best place to call difficulty logic / animation logic for changing backgrounds
        // Either change this setScore() method to addPoint() and losePoint() in order to not allow score of more/less than +/- 1
        // TODO: Implement logic to let the game view know that the background should be updated and to change the number of level that the player is on and difficulty logic and shit like that.
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
     * @param name
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
     * @param lives
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
     * @param timeLeft
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
     * @param currentLevel
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public int[] getHeartY() {
        return heartY;
    }

    public void setHeartY(int[] heartY) {
        this.heartY = heartY;
    }

    public int[] getHeartYLimits() {
        return heartYLimits;
    }

    public void setHeartYLimits(int[] heartYLimits) {
        this.heartYLimits = heartYLimits;
    }

    public int[] getHeartJumpDistances() {
        return heartJumpDistances;
    }

    public void setHeartJumpDistances(int[] heartJumpDistances) {
        this.heartJumpDistances = heartJumpDistances;
    }

    public boolean[] getHeartAscending() {
        return heartAscending;
    }

    public void setHeartAscending(boolean[] heartAscending) {
        this.heartAscending = heartAscending;
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public int getYMoveLimit() {
        return yMoveLimit;
    }

    public void setYMoveLimit(int yMoveLimit) {
        this.yMoveLimit = yMoveLimit;
    }

    public int getInitialHeartY() {
        return initialHeartY;
    }

    public void setInitialHeartY(int initialHeartY) {
        this.initialHeartY = initialHeartY;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * method to remove lives when the player uses all the guess attempts or runs out of time
     */
    public void decrementLives() {
        if (lives > 1) {
            lives--; // Decrement lives by 1
            GameController.getInstance().getGameTimer().restartGameTimer();
            GameController.getInstance().getCurrentWordleController().clearAllPanels();
        } else {
            lives--;
            GameController.getInstance().getGameTimer().stopGameTimer();
            GameController.getInstance().setGameState(GameState.GAME_OVER);
            System.out.println("Switched to GameState.GAME_OVER");
        }
    }


    /**
     * Checks if the player is currently running to the next level.
     *
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
     * gets the inital start time for the game
     * @return startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * sets the initial start time
     * @param startTime
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
