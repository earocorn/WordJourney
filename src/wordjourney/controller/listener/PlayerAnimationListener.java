package wordjourney.controller.listener;

import wordjourney.controller.GameController;
import wordjourney.model.Player;
import wordjourney.util.GameUtility;
import wordjourney.view.panels.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that serves as an action listener for the player graphic's animations.
 */
public class PlayerAnimationListener implements ActionListener {

    private final Player player;
    private final GamePanel container;
    
    /**
     * constructor for Player Animation Listener
     * @param player current player
     * @param container current game panel
     */
    public PlayerAnimationListener(Player player, GamePanel container) {
        this.player = player;
        this.container = container;
    }
    
    /**
     * method to detect if an action was performed and how to handle it
     * @param e action performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!player.isRunningToNextLevel()) {
            if(player.getX() > GameUtility.WORDLEVIEW_EDGE-player.getPlayerIcon().getImage().getWidth(null) || player.getX()<0) {
                player.setXVelocity(player.getXVelocity()*-1);
            }
        } else {
            if(player.getX() < GameUtility.WINDOW_WIDTH || player.getX()<1) {
                player.setXVelocity(GameUtility.STARTING_PLAYER_X_VELOCITY + 5);
            } else {
                if (player.getX() > GameUtility.WINDOW_WIDTH - player.getPlayerIcon().getImage().getWidth(null)) {
                    player.setRunningToNextLevel(false);
                    player.setX(0);
                    player.setXVelocity(GameUtility.STARTING_PLAYER_X_VELOCITY);
                    player.setCurrentLevel(player.getCurrentLevel()+1);
                    container.getWordleView().setVisible(true);
                    container.getWordleView().getInput().getUserInput().setEnabled(true);
                    container.getWordleView().getInput().getUserInput().grabFocus();
                    GameController.getInstance().getGameTimer().resetTime();
//                    GameController.getInstance().getGameTimer().restartGameTimer();
                    container.resetMonster();
                }
            }
        }
        player.move(player.getXVelocity(), 0);

        for (int i = 0; i < player.getLives(); i++) {
            if (player.getHeartAscending()[i]) {
                player.getHeartY()[i]--;
                if (player.getHeartY()[i] < player.getHeartYLimits()[i]) {
                    player.getHeartAscending()[i] = false;
                }
            } else {
                player.getHeartY()[i]++;
                if (player.getHeartY()[i] >= player.getInitialHeartY() + player.getHeartJumpDistances()[i]) {
                    player.getHeartAscending()[i] = true;
                }
            }
        }

        container.repaint();
    }
}
