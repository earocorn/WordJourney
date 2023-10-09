package wordjourney.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTitle extends JLabel {

    public static ImageIcon title;
    JLabel pinkBubble;
    ImageIcon pinkBubbleIcon;
    JPanel titleContainer;
    final int titleXLimitRight = 45;
    final int titleXLimitLeft = 25;
    final int titleYLimitTop = 120;
    final int titleYLimitBottom = 100;
    public int titleX = 35;
    public int titleY = 110;
    public int titleXVelocity = 3;
    public int titleYVelocity = 2;

    public GameTitle(){
        if (WordleGame.panel.livesCount == 3){
            title = new ImageIcon("src/assets/title.png");
            titleContainer = new JPanel();
            pinkBubble = new JLabel();

            pinkBubbleIcon = new ImageIcon("src/assets/pinkBubble.png");
            pinkBubble.setIcon(pinkBubbleIcon);
            pinkBubble.setOpaque(false);
            titleContainer.setOpaque(false);

            titleContainer.add(pinkBubble);
        }
        else{
            title = new ImageIcon("src/assets/gameOverTitle.png");
        }
        setOpaque(false);
        setIcon(title);

    }

    public ImageIcon getTitle() {
        return title;
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        System.out.println("Painting GameTitle!"); // Add this line
        Graphics2D g2D = (Graphics2D) g;

    }





}
