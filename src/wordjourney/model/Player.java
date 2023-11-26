package wordjourney.model;

import wordjourney.util.GameUtility;
import wordjourney.controller.GameController;
import wordjourney.view.GameFrame;
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
    private String name;
    private final ImageIcon playerIcon;
    private final ImageIcon heartIcon;
    private boolean isRunningToNextLevel;


    /**
     * @constructor 
     */
    public Player() {
        // player data
        this.score = GameUtility.STARTING_SCORE;
        this.lives = GameUtility.STARTING_LIVES;
        this.name = "PLAYER";
        this.timeLeft = GameUtility.STARTING_TIME;
        this.currentLevel = 0;

        // guy graphics
        this.playerIcon = new ImageIcon("src/assets/ui/sprites/sprite.png");
        this.heartIcon = new ImageIcon("src/assets/ui/sprites/hearts.png");
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
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(int score) {
        // I think this is the best place to call difficulty logic / animation logic for changing backgrounds
        // Either change this setScore() method to addPoint() and losePoint() in order to not allow score of more/less than +/- 1
        // TODO: Implement logic to let the game view know that the background should be updated and to change the number of level that the player is on and difficulty logic and shit like that.
        this.score = score;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * @param lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * @return timeLeft
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * @param timeLeft
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }


    /**
     * @return playerIcon
     */
    public ImageIcon getPlayerIcon() {
        return playerIcon;
    }

    /**
     * @return currentLevel
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * @param currentLevel
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
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
     
    public void decrementLives() {
        // TODO: Set game state to GAME_OVER and do any destruction/resetting of player/wordle models if player is dead (lives == 0)-- use elseif?
        if (lives > 1) {
            lives--; // Decrement lives by 1
        } else if (lives <= 1) {
            lives--;
            GameController.getInstance().setGameState(GameState.GAME_OVER);
            System.out.println("Switched to GameState.GAME_OVER");
        }
    }

    public boolean isRunningToNextLevel() {
        return isRunningToNextLevel;
    }

    public void setRunningToNextLevel(boolean runningToNextLevel) {
        this.isRunningToNextLevel = runningToNextLevel;
    }
    public void incrementScore() {
        score++;
        if(score % 2 == 0 && score != 0) {
            GameController.getInstance().getGameView().getGamePanel().explodeMonster();
            System.out.println("Current level = " + currentLevel);
        }
    }
}
