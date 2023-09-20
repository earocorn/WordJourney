/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.graphics;

import javax.swing.JFrame;

/**
 *
 * @author alexalmanza
 */
public class GameFrame extends JFrame {
    
    public static GamePanel panel;
    
    public GameFrame() {
        panel = new GamePanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
