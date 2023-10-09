package wordjourney.graphics;

import wordjourney.util.GameUtility;

import javax.swing.JFrame;
import java.awt.FlowLayout;

public class GameFrame extends JFrame {


    public GameFrame() {
        super ("Word Journey");

        setSize(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        pack();
        setVisible(true);

    }

}

