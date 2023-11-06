package wordjourney.controller.listener;

import wordjourney.model.Player;
import wordjourney.util.GameUtility;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerJumpListener implements ActionListener {
    private final Player player;
    private final Timer timer;
    public PlayerJumpListener(Player player, Timer timer) {
        this.player = player;
        this.timer = timer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: DIFFICULT: Replace jumping logic with shooting an arrow at the wordle monster.
        if(player.isAscending()){
            player.move(0, 10);
            if(player.getY() == player.getYMoveLimit()){
                player.setYMoveLimit(player.getYMoveLimit()*-1);
                player.setAscending(false);
            }
            else{
                if (player.getY()==0){
                    timer.stop();
                }
                else{
                    player.move(0,-5);
                }
            }
        }
    }
}
