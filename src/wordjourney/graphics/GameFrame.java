/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.graphics;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import wordjourney.util.GameUtility;
import wordjourney.util.Test;

/**
 *
 * @author alexalmanza
 */
public class GameFrame extends JFrame {
    
    private ImageIcon icon;
    
    public GameFrame() {
        super("Word Journey");

        Test.printObject(this);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/assets/wordJourneyIcon.png");
        //https://runmodule.com/2020/01/05/how-to-set-dock-icon-of-java-application/
        final Taskbar taskbar = Taskbar.getTaskbar();
        taskbar.setIconImage(icon);
        setIconImage(icon);
        
        setSize(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        pack();
        setVisible(true);
    }
    
}
