package wordjourney.graphics.panels;


import wordjourney.graphics.components.ButtonContainer;
import wordjourney.graphics.components.GameTitle;
import wordjourney.util.GameManager;
import wordjourney.util.GameUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class MenuPanel extends JPanel implements ActionListener {

    public static JLabel menu;
    ButtonContainer buttonContainer;
    GameTitle gameTitle;
    ImageIcon bgIcon;
    Timer timer;

    public MenuPanel(){

        this.setPreferredSize(new Dimension(GameUtility.WINDOW_WIDTH,GameUtility.WINDOW_HEIGHT));
        timer = new Timer(10, this);

        //create new instances of components
        menu = new JLabel();
        gameTitle = new GameTitle();
        buttonContainer = new ButtonContainer();

        bgIcon = new ImageIcon("src/assets/pinkBG.png");

        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));
        menu.setIcon(bgIcon);

        add(menu);

        menu.add(buttonContainer);

        timer.start();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);

        g.drawImage(gameTitle.getTitle().getImage(), gameTitle.titleX, gameTitle.titleY, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("X: " + gameTitle.titleX + ", Y: " + gameTitle.titleY);
        if ((gameTitle.titleX > gameTitle.titleXLimitRight) || (gameTitle.titleX < gameTitle.titleXLimitLeft)) {
            gameTitle.titleXVelocity -= gameTitle.titleXVelocity * 2;
        }
        if (gameTitle.titleY > gameTitle.titleYLimitTop || gameTitle.titleY <= gameTitle.titleYLimitBottom) {
            gameTitle.titleYVelocity -= gameTitle.titleYVelocity * 2;
        }
        gameTitle.titleX += gameTitle.titleXVelocity;
        gameTitle.titleY += gameTitle.titleYVelocity;

        repaint();
    }


}

