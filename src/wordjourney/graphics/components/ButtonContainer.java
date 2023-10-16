package wordjourney.graphics.components;

import wordjourney.util.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonContainer extends JPanel {

    JButton startButton;
    JButton quitButton;
    ImageIcon startButtonIcon;
    ImageIcon quitButtonIcon;
    public ButtonContainer() {
        startButton = new JButton();
        quitButton = new JButton();

        setLayout(new GridLayout(2, 1));

        startButtonIcon = new ImageIcon("src/assets/startButton.png");
        quitButtonIcon = new ImageIcon("src/assets/quitButton.png");


        startButton.setIcon(startButtonIcon);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        quitButton.setIcon(quitButtonIcon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);

        startButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {GameManager.showNewGame();}});
        quitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {GameManager.quitGame();}});
        setOpaque(false);
        add(startButton);
        add(quitButton);
    }
}
