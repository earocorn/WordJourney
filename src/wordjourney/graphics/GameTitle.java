package wordjourney.graphics;

import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTitle implements ActionListener {

    JLabel gameTitle;
    public static ImageIcon title;
    final int titleXLimitRight = 45;
    final int titleXLimitLeft = 25;
    final int titleYLimitTop = 120;
    final int titleYLimitBottom = 100;
    int titleX = 35;
    int titleY = 110;
    int titleXVelocity = 3;
    int titleYVelocity = 2;
    Timer timer;

    public GameTitle(){
        timer = new Timer(10, this);
        gameTitle = new JLabel();
        gameTitle.setOpaque(false);
        gameTitle.setIcon(title);
    }

    public static void setTitle(){

        if (WordleGame.panel.livesCount == 3){
            title= new ImageIcon("src/assets/title.png");
        }
        else{
            title = new ImageIcon("src/assets/gameOverTitle.png");
        }
//        if (WordleGame.panel.livesCount == 3){
//            title = new ImageIcon("src/assets/gameOverTitle.png");
//        }
//        else{
//            title= new ImageIcon("src/assets/title.png");
//        }

    }
    public void startAnimation(){
        System.out.println("animation started for title");
        timer.start();
    }

    public void paintComponent(Graphics g) {
//        System.out.println("Painting GameTitle!"); // Add this line
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(title.getImage(), titleX, titleY, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("X: " + titleX + ", Y: " + titleY);
        if ((titleX > titleXLimitRight) || (titleX < titleXLimitLeft)) {
            titleXVelocity -= titleXVelocity * 2;
        }
        if (titleY > titleYLimitTop || titleY <= titleYLimitBottom) {
            titleYVelocity -= titleYVelocity * 2;
        }
        titleX += titleXVelocity;
        titleY += titleYVelocity;

        gameTitle.repaint();
    }

}
