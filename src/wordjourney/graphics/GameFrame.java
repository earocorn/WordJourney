package wordjourney.graphics;

import wordjourney.util.GameUtility;

import javax.swing.JFrame;
import java.awt.FlowLayout;

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

