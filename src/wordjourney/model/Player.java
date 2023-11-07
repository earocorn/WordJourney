package wordjourney.model;

import wordjourney.util.GameUtility;

import javax.swing.*;
import wordjourney.controller.GameController;

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
    private static int score;
    private int lives;
    private int currentLevel;
    private int timeLeft;
    private String name;
    private final ImageIcon playerIcon;
    private final ImageIcon heartIcon;
    private GameState gameState;
    
   

    /**
     * Constructor for player class to initialize and set the properties
     */
    public Player() {
        // player data
        this.score = GameUtility.STARTING_SCORE;
        this.lives = GameUtility.STARTING_LIVES;
        this.name = "PLAYER";
        this.timeLeft = -1;
        this.currentLevel = 4;

        // guy graphics
        this.playerIcon = new ImageIcon("src/assets/sprite.png");
        this.heartIcon = new ImageIcon("src/assets/hearts.png");
        this.xVelocity = 2;
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
    }


    public void addPoint(){
        score++;
    }
    public int updateScore(){
        return score;
    }
    public void resetScore(){
        score = 0;
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

    /*
   public void setLives(int lives) {
       if(lives == 0) {
         GameController.getInstance().setGameState(GameState.GAME_OVER);
         gameState = GameController.getInstance().getGameState();
         System.out.println(gameState);
        }
        this.lives = lives;
    }
   */
     public void decrementLives() {
        if (lives > 0) {
            lives--; // Decrement lives by 1
        }
        System.out.println(lives);
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

    /**
     * @return
     */
    public int[] getHeartJumpDistances() {
        return heartJumpDistances;
    }

    /**
     * @param heartJumpDistances
     */
    public void setHeartJumpDistances(int[] heartJumpDistances) {
        this.heartJumpDistances = heartJumpDistances;
    }

    public boolean[] getHeartAscending() {
        return heartAscending;
    }

    /**
     * @param heartAscending
     */
    public void setHeartAscending(boolean[] heartAscending) {
        this.heartAscending = heartAscending;
    }

    /**
     * method to move the character at the bottom of the screen
     * @param deltaX
     * @param deltaY
     */
    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * @return yMoveLimit
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
     * @return initialHeartY
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
     * @return xVelocity
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
}
