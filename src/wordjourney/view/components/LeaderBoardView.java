package wordjourney.view.components;

import wordjourney.util.GameUtility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

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
    public static void setLBText(String name, int score, int position){
        System.out.println("Setting label text: " + name + " - " + score);
        lbColumns[position].setFont(GameUtility.getFont());
        lbColumns[position].setText(name+ "-"+ score);
        lbColumns[position].setBackground(Color.PINK);
    }

    public void updateLeaderboardText() {
        if(lbColumns != null && lbColumns.length >= 5) {
            int i = 0;
            for (Map.Entry<String, Long> entry : GameUtility.getInstance().getLeaderboardData().getLeaderboard().entrySet()) {
                if (i < 5) {
                    lbColumns[i].setFont(GameUtility.getFont());
                    String text = entry.getKey() + "-" + entry.getValue();
                    lbColumns[i].setText(text);
                    lbColumns[i].setBackground(Color.PINK);
                    i++;
                } else {
                    break;
                }
            }
            for (int j = i; j < 5; j++) {
                lbColumns[j].setText("");
            }
        }
    }

}
