package wordjourney.view.components;

import wordjourney.util.GameUtility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;

public class LeaderBoardView extends JPanel {
    static JLabel[] lbColumns = new JLabel[5];

    public LeaderBoardView() {

        setLayout(new GridLayout(5,1));
        Border pinkBorder = BorderFactory.createLineBorder(new Color(250,28,121,250));
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

    public void updateLeaderboardText() {
        if(lbColumns != null && lbColumns.length >= 5) {

            String[][] leaderboardArray = new String[5][2];

            int i = 0;
            for (Map.Entry<String, Long> entry : GameUtility.getInstance().getLeaderboardData().getLeaderboard().entrySet()) {
                leaderboardArray[i][0] = entry.getKey();
                leaderboardArray[i][1] = String.valueOf(entry.getValue());
                System.out.println(leaderboardArray[i][0] + " : " + leaderboardArray[i][1]);
                i++;
            }

            while (i < 5) {
                leaderboardArray[i][0] = "";
                leaderboardArray[i][1] = "";
                System.out.println(leaderboardArray[i][0] + " : " + leaderboardArray[i][1]);
                i++;
            }

            Arrays.sort(leaderboardArray, Comparator.comparing(row -> !row[1].isEmpty() ? Long.parseLong((String) row[1]) : 0, Comparator.reverseOrder()));

            for (int j = 0; j < 5; j++) {
                lbColumns[j].setFont(GameUtility.getFont());
                String text = leaderboardArray[j][0] + "-" + leaderboardArray[j][1];
                lbColumns[j].setText(text);
                lbColumns[j].setBackground(Color.PINK);
            }
        }
    }

}
