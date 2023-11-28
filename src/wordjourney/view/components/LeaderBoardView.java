package wordjourney.view.components;

import wordjourney.controller.LeaderBoard;
import wordjourney.util.GameUtility;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LeaderBoardView extends JPanel {
    static JLabel[] lbColumns = new JLabel[5];
    LeaderBoard leaderBoard;


    public LeaderBoardView() {
        leaderBoard = new LeaderBoard();

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
    

}
