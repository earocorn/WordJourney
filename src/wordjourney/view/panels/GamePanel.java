package wordjourney.view.panels;


import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.controller.listener.PlayerAnimationListener;
import wordjourney.controller.listener.PlayerJumpListener;
import wordjourney.model.Player;
import wordjourney.model.SoundEffect;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.components.WordleView;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to represent a graphical panel for displaying the main game Panel
 */
public class GamePanel extends JPanel {
    public JLabel background;
    JLabel monsterLabel;
    ImageIcon  backgroundImage;
    WordleView wordleView;
    WordleModel wordleModel;
    WordleController wordleController;
    ImageIcon playerIcon;
    Icon monsterIcon;
    Player player;
    Timer jumpTimer;
    Timer timer;

    /**
     * Constructor for game panel and initializes properties for this panel: player, game timer, monster, wordle,
     */
    public GamePanel(){

        player = GameController.getInstance().getPlayer();

        background = new JLabel();

        //get current instances
        wordleView = GameController.getInstance().getCurrentWordleView();
        wordleModel = GameController.getInstance().getCurrentWordleModel();
        wordleController = GameController.getInstance().getCurrentWordleController();
        playerIcon = player.getPlayerIcon();

        timer = new Timer(10, new PlayerAnimationListener(player, this));
        jumpTimer = new Timer(20, new PlayerJumpListener(player, jumpTimer));


        background.setVisible(true);

        // get players current level for background
        backgroundImage = GameUtility.getLevels()[player.getCurrentLevel()].getLevelBackground();
        background.setIcon(backgroundImage);

        // spacing between wordle monster and wordle
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 60));

        background.add(wordleView);

        monsterIcon = GameUtility.getInstance().getMonsterIcon();

        monsterLabel = new JLabel(monsterIcon);
        monsterLabel.setLayout(new GridBagLayout());

        background.add(monsterLabel);

        add(background);

        player.setInitialHeartY(GameUtility.WINDOW_HEIGHT- player.getPlayerIcon().getImage().getHeight(null) - (GameUtility.WINDOW_HEIGHT - GameUtility.getLevels()[player.getCurrentLevel()].getStartingHeight()) - player.getY() -20);

        animateHearts(); //method for hearts above little guys head to animate

        timer.start();
    }

    /**
     * Method to paint all dynamic content onto the main view
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        // draw Little Guy
        g2D.drawImage(playerIcon.getImage(), player.getLives() > 0 ? player.getX() : 0, GameUtility.WINDOW_HEIGHT-playerIcon.getIconHeight() - (GameUtility.WINDOW_HEIGHT- GameUtility.getLevels()[0].getStartingHeight()) - player.getY(), null);

        // draw hearts on the screen
        if(player.getLives() > 0) {
            g2D.drawImage(player.getHeartIcon().getImage(), player.getX(), player.getHeartY()[0], null);
        }
        for (int i = 1; i < player.getLives(); i++) {
            g2D.drawImage(player.getHeartIcon().getImage(), player.getX() + 18 * i, player.getHeartY()[i], null);
        }
        
        //set font and font color for paint component
        g.setColor(Color.BLACK);
        g.setFont(GameUtility.getFont());
        
        //draw score on the screen
        g.drawString("Score: " + player.getScore(), 700, 45);
        g.drawString("Time: " + player.getTimeLeft(), 350, 45);
    }

    /**
     * method to return current instance of wordleView
     * @return wordleView
     */
    public WordleView getWordleView() {
        return wordleView;
    }

    /**
     * method to return current instance of wordleController
     * @return wordleController
     */
    public WordleController getWordleController() {
        return wordleController;
    }

    /**
     *  method to call explosion of monster when the player moves to a new level
     */
    public void explodeMonster() {
        System.out.println("Exploding monster ... ");
        GameUtility.getInstance().playSoundEffect(SoundEffect.BOOM);
        GameController.getInstance().getGameTimer().stopGameTimer();
        player.setXVelocity(0);
        wordleView.getInput().getUserInput().setEnabled(false);
        JLabel explosion = new JLabel(new ImageIcon("src/assets/ui/sprites/explosion3.gif"));
        monsterLabel.add(explosion);
        monsterLabel.validate();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        monsterLabel.setIcon(null);
                        wordleView.setVisible(false);
                        player.setXVelocity(GameUtility.STARTING_PLAYER_X_VELOCITY);
                        player.setRunningToNextLevel(true);
                    }
                },
                3000
        );
    }

    /**
     * method to redraw the monster that is displayed on the game panel
     */
    public void resetMonster() {
        monsterLabel.removeAll();
        monsterLabel.setIcon(GameUtility.getInstance().getMonsterIcon());
        background.add(monsterLabel);
        backgroundImage = GameUtility.getLevels()[player.getCurrentLevel()].getLevelBackground();
        background.setIcon(backgroundImage);
        // animate player moving across screen to next level
        background.repaint();
        background.revalidate();
        super.repaint();
    }

    /**
     * method to change background based on the players current score
     */
    public void updateBackground() {
        int targetScore = (player.getCurrentLevel() + 1) * 2; // target score increment of 2 points per level

        // Check if the player's score is greater than or equal to the target score
        if (player.getScore() >= targetScore) {
            player.setCurrentLevel(player.getCurrentLevel() + 1);

            // Check if the new level exists in the array of levels
            if (player.getCurrentLevel() >= GameUtility.getLevels().length) {
                // TODO: should we start the levels over or should we do a completion of the game
                // im just going to reset the score and levels for now
                // so if score reaches 22 restarts points etc
                //player.setScore(0); //TODO: check if this messes up past 22 points
                player.setCurrentLevel(0);
            }
            backgroundImage = GameUtility.getLevels()[player.getCurrentLevel()].getLevelBackground();
            background.setIcon(backgroundImage);

            // Repaint the panel to reflect the background changes
            repaint();
        }
    }

    /**
     * method to animate the hearts that represent the players lives
     */
    public void animateHearts(){
        for (int i = 0; i < player.getLives(); i++) {
            player.getHeartY()[i] = player.getInitialHeartY();
            player.getHeartYLimits()[i] = player.getHeartY()[i] - player.getHeartJumpDistances()[i];
            player.getHeartY()[i] -= 7*(i+2);
            player.getHeartAscending()[i] = true;
        }
    }

}