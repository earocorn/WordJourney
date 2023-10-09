 /*
  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
  */
 package wordjourney.graphics;

import wordjourney.listeners.GameAnimationListener;
import wordjourney.util.GameUtility;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import wordjourney.Main;
import wordjourney.util.GameState;
import wordjourney.util.Test;

 /**
 *
 * @author alexalmanza
 */
public class GamePanel extends JPanel {
    private GameAnimationListener gameAnimationListener;
    public Image player;
    Timer timer;
    Image lives;
    ImageIcon backgroundImage;
    Timer moveTimer;
    GameOverPanel gameOverPanel;
    public static JLabel background;
    public int xVelocity = 2;
    public int x = 0;
    public int y = 0;
    int yMoveLimit = y+50;
    
    public int livesCount = GameUtility.STARTING_LIVES;

    public int[] heartY = new int[livesCount];
    public int[] heartYLimits = new int[livesCount];
    public int[] heartJumpDistances = {10, 10, 10};
    public int initialHeartY;

    public boolean isAscending = false;

    public boolean[] heartAscending = new boolean[livesCount];
    
    public static int time = 365;
    public static int score = 0;
    
    
    public void movePlayer() {
        yMoveLimit = y+50;
        isAscending = true;
        moveTimer.restart();
    }
    
    public GamePanel() {
        super.invalidate();

        setDoubleBuffered(true);

        Test.printObject(this);

        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT));
        gameAnimationListener = new GameAnimationListener(this);
        timer = new Timer(10, gameAnimationListener);
        
        gameOverPanel = new GameOverPanel();

        background = new JLabel();
        background.setLayout(new GridBagLayout());
        background.setVisible(true);
        player = new ImageIcon("src/assets/sprite.png").getImage();
        lives = new ImageIcon("src/assets/hearts.png").getImage();
        backgroundImage = new ImageIcon("src/assets/gameBackground.jpg");
        background.setIcon(backgroundImage);
        
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
        
        initialHeartY = GameUtility.WINDOW_HEIGHT - player.getHeight(null) - GameUtility.GROUND_HEIGHT - y - 20;
        
        for (int i = 0; i < livesCount; i++) {
            heartY[i] = initialHeartY;
            heartYLimits[i] = heartY[i] - heartJumpDistances[i];
            heartY[i] -= 7*(i+2);
            heartAscending[i] = true;
        }

        this.add(background);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(player, livesCount > 0 ? x : 0, GameUtility.WINDOW_HEIGHT-player.getHeight(null) - GameUtility.GROUND_HEIGHT - y, null);

        //display hearts on the screen
        if(livesCount > 0) {
            g2D.drawImage(lives, x, heartY[0], null);
        } else {
            this.add(gameOverPanel);
        }
        
        
        for (int i = 1; i < livesCount; i++) {
            g2D.drawImage(lives, x + 18 * i, heartY[i], null);
        }
        //set font and font color
        g.setColor(Color.BLACK);
        g.setFont(GameUtility.getFont());

         //display time on screen
//         g.drawString("Time: "+ time, 700, 45);
         //score displayed on screen
         g.drawString("Score: "+ score, 100, 45);
     }

     @Override
     public void actionPerformed(ActionEvent e) {
         if(x > GameUtility.WINDOW_WIDTH-player.getWidth(null) || x<0) {
             xVelocity = xVelocity * -1;
         }
         x = x + xVelocity;

         for (int i = 0; i < livesCount; i++) {
             if (heartAscending[i]) {
                 heartY[i]--;
                 if (heartY[i] < heartYLimits[i]) {
                     heartAscending[i] = false;
                 }
             } else {
                 heartY[i]++;
                 if (heartY[i] >= initialHeartY + heartJumpDistances[i]) {
                     heartAscending[i] = true;
                 }
             }
         }

         repaint();
     }


 }