package wordjourney.listeners;

import wordjourney.graphics.GamePanel;
import wordjourney.util.GameUtility;
import wordjourney.util.Test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameAnimationListener implements ActionListener {

    private final GamePanel gamePanel;

    public GameAnimationListener(GamePanel gamePanel) {
        Test.printObject(this);
        this.gamePanel = gamePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(gamePanel.x > GameUtility.WINDOW_WIDTH-gamePanel.player.getWidth(null) || gamePanel.x<0) {
            gamePanel.xVelocity = gamePanel.xVelocity * -1;
        }
        gamePanel.x += gamePanel.xVelocity;

        for (int i = 0; i < gamePanel.livesCount; i++) {
            if (gamePanel.heartAscending[i]) {
                gamePanel.heartY[i]--;
                if (gamePanel.heartY[i] < gamePanel.heartYLimits[i]) {
                    gamePanel.heartAscending[i] = false;
                }
            } else {
                gamePanel.heartY[i]++;
                if (gamePanel.heartY[i] >= gamePanel.initialHeartY + gamePanel.heartJumpDistances[i]) {
                    gamePanel.heartAscending[i] = true;
                }
            }
        }

        gamePanel.repaint();
    }
}
