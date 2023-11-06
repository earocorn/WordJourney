package wordjourney.view.panels;


import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.controller.listener.PlayerAnimationListener;
import wordjourney.controller.listener.PlayerJumpListener;
import wordjourney.model.Player;
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
    ImageIcon  backgroundImage;
    WordleView wordleView;
    WordleModel wordleModel;
    WordleController wordleController;
    ImageIcon playerIcon;
    Player player;
    Timer jumpTimer;
    Timer timer;


    public int score;

    /**
     * Constructor for  game panel and initializes properties
     */
    public GamePanel(){
        player = GameController.getInstance().getPlayer();

        background = new JLabel();
        wordleView = new WordleView();
        wordleModel = new WordleModel();
        wordleController = new WordleController(wordleModel, wordleView);

        wordleController.startGameTimer();

        playerIcon = new ImageIcon(player.getPlayerIcon().getImage());

        timer = new Timer(10, new PlayerAnimationListener(player, this));
        jumpTimer = new Timer(20, new PlayerJumpListener(player, jumpTimer));

        background.setLayout(new GridBagLayout());
        background.setVisible(true);
        // get players current level for background
        backgroundImage = GameUtility.getLevels()[player.getCurrentLevel()].getLevelBackground();
        background.setIcon(backgroundImage);

        background.add(wordleView);
        add(background);

        player.setInitialHeartY(GameUtility.WINDOW_HEIGHT- player.getPlayerIcon().getImage().getHeight(null) - (GameUtility.WINDOW_HEIGHT - GameUtility.getLevels()[player.getCurrentLevel()].getStartingHeight()) - player.getY() -20);
        for (int i = 0; i < player.getLives(); i++) {
            player.getHeartY()[i] = player.getInitialHeartY();
            player.getHeartYLimits()[i] = player.getHeartY()[i] - player.getHeartJumpDistances()[i];
            player.getHeartY()[i] -= 7*(i+2);
            player.getHeartAscending()[i] = true;
        }

        timer.start();
    }

    public void movePlayer() {
        player.setYMoveLimit(player.getY()+50);
        player.setAscending(true);
        jumpTimer.restart();
    }


    public void moveHearts(){

    }
    /**
     * / Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
     * @param g
     */
    @Override
    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(playerIcon.getImage(), player.getLives() > 0 ? player.getX() : 0, GameUtility.WINDOW_HEIGHT-playerIcon.getIconHeight() - (GameUtility.WINDOW_HEIGHT- GameUtility.getLevels()[0].getStartingHeight()) - player.getY(), null);

        //display hearts on the screen
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
        g.drawString("Score: "+ player.getScore(), 700, 45);
        g.drawString("Time: " + wordleController.getGameTimer(), 350, 45);
    }





}