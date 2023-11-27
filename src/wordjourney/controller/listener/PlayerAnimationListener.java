package wordjourney.controller.listener;

import wordjourney.model.Player;
import wordjourney.util.GameUtility;
import wordjourney.view.panels.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerAnimationListener implements ActionListener {

    private final Player player;
    private final GamePanel container;
    public PlayerAnimationListener(Player player, GamePanel container) {
        this.player = player;
        this.container = container;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Currently this method is to move the player back and forth but we want to have one fluid movement from one level to the next when the player's score reaches the threshold to change levels
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
                    container.getWordleController().resetTime();
                    container.getWordleController().restartGameTimer();
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
