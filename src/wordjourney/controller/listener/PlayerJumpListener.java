package wordjourney.controller.listener;

import wordjourney.model.Player;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that listens for when the player graphic "jumps" when getting a wordle right.
 */
public class PlayerJumpListener implements ActionListener {
    private final Player player;
    private final Timer timer;
    
    /**
     * constructor for the listener
     * @param player current player
     * @param timer  current timer
     */
    public PlayerJumpListener(Player player, Timer timer) {
        this.player = player;
        this.timer = timer;
    }
    
    /**
     * method that checks for if an action is performed so the player can actually jump
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
