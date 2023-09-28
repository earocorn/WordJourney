 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.graphics;

import wordjourney.util.GameUtility;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alexalmanza
 */
public class GamePanel extends JPanel implements ActionListener {
    Image player;
    Image lives;
    ImageIcon backgroundImage;
    Timer timer;
    Timer moveTimer;
    public static JLabel background;
    int xVelocity = 2;
    static int x = 0;
    int y = 0;
    int yMoveLimit = y+50;
    boolean isAscending = false;
    public static int time =365;
    public static int score =0;
    public Font gameFont;
    
    public void movePlayer() {
        yMoveLimit = y+50;
        isAscending = true;
        moveTimer.restart();
    }
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT));

        background = new JLabel();
        background.setLayout(new GridBagLayout());
        background.setVisible(true);
        player = new ImageIcon("src/assets/sprite.png").getImage();
        lives = new ImageIcon("src/assets/hearts.png").getImage();
        backgroundImage = new ImageIcon("src/assets/gameBackground.jpeg");
        background.setIcon(backgroundImage);
        timer = new javax.swing.Timer(10, this);



        
        moveTimer = new Timer(20, new ActionListener() {
            // LITTLE BUDDY JUMPING LOGIC, can put anything in here bc this is activated from movePlayer() function
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isAscending){
                    //System.out.println("Limit: " + yMoveLimit + "\nY: " + y);
                    y+=10;
                    if(y == yMoveLimit) {
                        yMoveLimit = yMoveLimit * -1;
                        isAscending = false;
                    }
                } else {
                    if(y == 0) {
                        moveTimer.stop();
                    } else {
                        y-=5;
                    }
                }
            }
        });

        this.add(background);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(player, x, GameUtility.WINDOW_HEIGHT-player.getHeight(null) - GameUtility.GROUND_HEIGHT - y, null);


        //display hearts on the screen
        g2D.drawImage(lives,x, GameUtility.WINDOW_HEIGHT-lives.getHeight(null)- GameUtility.GROUND_HEIGHT-y, null);
        //set font and font color
        gameFont= new Font ("SUPER GAME", Font.PLAIN, 30);
        g.setColor(Color.BLACK);
        g.setFont(gameFont);


        //display time on screen
        g.drawString("Time: "+ time, 700, 45);
        //score displayed on screen
        g.drawString("Score: "+ score, 100, 45);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x > GameUtility.WINDOW_WIDTH-player.getWidth(null) || x<0) {
            xVelocity = xVelocity * -1;
        }
        x = x + xVelocity;
        repaint();
    }
    
    
    
}
