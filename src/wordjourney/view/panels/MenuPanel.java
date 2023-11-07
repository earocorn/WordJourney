package wordjourney.view.panels;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.components.ButtonContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to represent a graphical panel displaying a menu
 */
public class MenuPanel extends JPanel implements ActionListener {


    ImageIcon bgIcon;
    JLabel background;
    ImageIcon title;
    Timer timer;

    ButtonContainer buttonContainer;
    int titleXLimitRight = 45;
    int titleXLimitLeft = 25;
    int titleYLimitTop = 0;
    int titleYLimitBottom = 1000;
    int titleX = 35;
    int titleY = 50;
    int titleXVelocity = 3;
    int titleYVelocity = 2;

    /**
     * Constructor to initialize MenuPanel and  its properties
     */
    public MenuPanel(){
        this.setSize(GameUtility.windowDimension);

        timer = new Timer(10, this);

        //create an instance of the panel
        bgIcon = new ImageIcon("src/assets/pinkBG.png");
        background = new JLabel(bgIcon);
        buttonContainer  =  new ButtonContainer();


        //set and add components
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));

        add(background);

        background.add(buttonContainer);

        timer.start();
        System.out.println("MenuPanel created!");
    }

    /**
     * method to get the title depending on the game state
     * @return title
     */
    public ImageIcon getTitle(){
        try{
            if(true){
                title =  new ImageIcon("src/assets/title.png");
            }
            else{
                title = new ImageIcon("src/assets/gameOverTitle.png");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return title;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(getTitle().getImage(), titleX, titleY, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameController.getInstance().getGameState() != GameState.MENU) {
            // TODO: figure out how to stop and start menu timer gracefully when GameState changes
            timer.stop();
        }

        if ((titleX > titleXLimitRight) || (titleX < titleXLimitLeft)) {
            titleXVelocity -= titleXVelocity * 2;
        }
        if ((titleY > titleYLimitTop) || (titleY < titleYLimitBottom)) {
            titleYVelocity -= titleYVelocity * 2;
        }
        titleX += titleXVelocity;
        titleY += titleYVelocity;

        repaint();
    }

}
