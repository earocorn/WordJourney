package wordjourney.view.panels;


import wordjourney.controller.GameController;
import wordjourney.controller.WordleController;
import wordjourney.controller.listener.PlayerAnimationListener;
import wordjourney.controller.listener.PlayerJumpListener;
import wordjourney.model.Player;
import wordjourney.model.WordleModel;
import wordjourney.util.GameUtility;
import wordjourney.view.components.InputComponent;
import wordjourney.view.components.WordleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    //hearts

    /**
     * Constructor for  game panel and initializes properties
     */
    public GamePanel(){
        // TODO: Ideally GameController should be able to get any current event/model being acted upon which is why it might make sense to initialize the wordle in GameController and get the instance of the wordle. SEE TODO COMMENT IN wordjourney.Core
        player = GameController.getInstance().getPlayer();

        background = new JLabel();

        // TODO: DONT DO ANYTHING HERE. If you think wordle initialization should go in a different class such as GameController (previously known as GameManager) or have a better idea please let me know. Also let me know if it makes sense that it just goes here.
        wordleView = new WordleView();
        wordleModel = new WordleModel();
        wordleController = new WordleController(wordleModel, wordleView, player);

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

    /**
     * Method to paint all dynamic content onto the main view
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        // TODO: NEED WORDLE MONSTER GUY: In this paint() method somehow we need to paint a wordle monster but I don't think we have decided on a wordle monster. This can be just painted behind the wordle box and we can animate it and change opacity of the panels to make it look better.

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
        g.drawString("Time: " + wordleController.getTime(), 350, 45);
    }


}