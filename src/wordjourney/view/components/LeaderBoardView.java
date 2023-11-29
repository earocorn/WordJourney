package wordjourney.view.components;

import wordjourney.util.GameUtility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;

/**
 *  The LeaderBoardView class represents the graphical user interface component for displaying
 *  the leaderboard in the Word Journey game. It extends JPanel and provides methods to update
 *  and customize the appearance of the leaderboard.
 */
public class LeaderBoardView extends JPanel {

    // Array to store JLabel columns for displaying leaderboard entries
    static JLabel[] lbColumns = new JLabel[5];

    /**
     * Constructs a new LeaderBoardView object. Initializes the layout and appearance
     * of the leaderboard columns.
     */
    public LeaderBoardView() {

        setLayout(new GridLayout(5,1));
        Border pinkBorder = BorderFactory.createLineBorder(new Color(250,28,121,250));
        // Initialize and customize JLabel columns
        for (int i = 0; i < 5; i++) {
            lbColumns[i] = new JLabel();
            lbColumns[i].setSize(new Dimension(300, 50));
            lbColumns[i].setPreferredSize(new Dimension(300, 50));
            lbColumns[i].setHorizontalAlignment(JLabel.CENTER);
            lbColumns[i].setOpaque(true);
            lbColumns[i].setBackground(new Color(255, 192, 203, 99));
            lbColumns[i].setBorder(pinkBorder);
            add(lbColumns[i]);
        }
    }

    /**
     * Updates the text content of the leaderboard columns based on the latest data from
     * the game's leaderboard.
     */
    public void updateLeaderboardText() {
        if(lbColumns != null && lbColumns.length >= 5) {

            // 2D array to store leaderboard entries (player name and score)

            // Populate the array with data from the game's leaderboard
            int i = 0;
            int setSize = GameUtility.getInstance().getLeaderboardData().getLeaderboard().entrySet().size();
            String[][] leaderboardArray = new String[setSize][2];
            for (Map.Entry<String, Long> entry : GameUtility.getInstance().getLeaderboardData().getLeaderboard().entrySet()) {
                if (i < setSize) {
                    leaderboardArray[i][0] = entry.getKey();
                    leaderboardArray[i][1] = String.valueOf(entry.getValue());
                    System.out.println(leaderboardArray[i][0] + " : " + leaderboardArray[i][1]);
                    i++;
                } else {
                    break;
                }

            }
            while (i < setSize) {
                leaderboardArray[i][0] = "";
                leaderboardArray[i][1] = "";
                System.out.println(leaderboardArray[i][0] + " : " + leaderboardArray[i][1]);
                i++;
            }
            Arrays.sort(leaderboardArray, Comparator.comparing(row -> !row[1].isEmpty() ? Long.parseLong((String) row[1]) : 0, Comparator.reverseOrder()));
            // populates the leaderboard and sets the data with fonts, colors, etc
            for (int j = 0; j <lbColumns.length; j++) {
                lbColumns[j].setFont(GameUtility.getFont());
                String text = leaderboardArray[j][0] + "-" + leaderboardArray[j][1];
                lbColumns[j].setText(text);
                lbColumns[j].setBackground(Color.PINK);
            }
        }
    }

}
