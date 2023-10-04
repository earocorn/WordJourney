/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.graphics;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import wordjourney.util.GameUtility;

/**
 *
 * @author alexalmanza
 */
public class GameFrame extends JFrame {
    
    GamePanel panel = WordleComponent.panel;
    
    public GameFrame() {
        setSize(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());
            setVisible(true);
            setResizable(false);
            setAlwaysOnTop(true);
            add(panel, "Graphics");
    }
    
}
