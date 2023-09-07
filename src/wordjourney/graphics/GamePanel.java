/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alexalmanza
 */
public class GamePanel extends JPanel implements ActionListener {

    
    
    final int WIDTH = 508;
    final int HEIGHT = 254;
    Image player;
    ImageIcon backgroundImage;
    Timer timer;
    Timer moveTimer;
    JButton moveButton;
    JLabel background;
    int xVelocity = 1;
    static int x = 0;
    int y = 0;
    int xMoveLimit = x+50;
    
    private void movePlayer() {
        xMoveLimit = x+50;
        moveTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(x != xMoveLimit) {
                    if(x > WIDTH-player.getWidth(null)) {
                        x--;
                    }
                    else {
                        x++;
                    }
                } else {
                    moveTimer.stop();
                }
            }
        });
        moveTimer.restart();
    }
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        moveButton = new JButton("Solve Wordle");
        moveButton.setLocation(WIDTH + 50, HEIGHT + 50);
        moveButton.setEnabled(true);
        moveButton.setVisible(true);
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer();
            }
        });
        
        background = new JLabel();
        background.setLayout(new GridBagLayout());
        background.setVisible(true);
        player = new ImageIcon("src/assets/sprite.png").getImage();
        backgroundImage = new ImageIcon("src/assets/forestBackground.png");
        background.setIcon(backgroundImage);
        timer = new Timer(10, this);
        this.add(background);
        background.add(moveButton, new GridBagConstraints());
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(player, x, HEIGHT-player.getHeight(null), null);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= WIDTH-player.getWidth(null) || x<0) {
            xVelocity = xVelocity * -1;
        }
        //x = x + xVelocity;
        repaint();
    }
    
    
    
}
