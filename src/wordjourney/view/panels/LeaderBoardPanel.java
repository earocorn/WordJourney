package wordjourney.view.panels;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.components.LBbuttonsContainer;
import wordjourney.view.components.LeaderBoardView;

import javax.swing.*;
import java.awt.*;


/**
 * A class used to represent a graphical panel for displaying the leaderboard panel
 */
public class LeaderBoardPanel extends JPanel{

    ImageIcon bgIcon;
    JLabel background;
    LeaderBoardView leaderBoard;
    LBbuttonsContainer lBbuttonsContainer;

    /**
     * Constructor for the leaderboard that initializes panels properties
     */
    public LeaderBoardPanel(){
        setSize(GameUtility.windowDimension);

        bgIcon = new ImageIcon("src/assets/ui/menubackgrounds/pinkBG.png");
        background = new JLabel(bgIcon);

        background.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 60));

        background.setVisible(true);

        //add panels to background
        leaderBoard = new LeaderBoardView();
        lBbuttonsContainer = new LBbuttonsContainer();
        background.add(leaderBoard);
        background.add(lBbuttonsContainer);

        add(background);

    }

    /**
     * method to paint the leaderboard text at the top of the panel
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(250,28,121,250));
        g.setFont(GameUtility.getFont().deriveFont(50f));
        g.drawString("LeaderBoard", 320,50);
    }

    /**
     * method to get the current leaderboard
     * @return leaderBoard
     */
    public LeaderBoardView getLeaderBoardView(){
        return leaderBoard;
    }
}
