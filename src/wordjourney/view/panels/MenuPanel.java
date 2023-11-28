package wordjourney.view.panels;

import wordjourney.controller.GameController;
import wordjourney.model.GameState;
import wordjourney.util.GameUtility;
import wordjourney.view.components.ButtonContainer;
import wordjourney.view.components.LBbuttonsContainer;

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
    ButtonContainer startButtonContainer; // panel buttons
    LBbuttonsContainer gameOverButtonContainer; //game over panel buttons
    JPanel newButtonContainer;
    int titleXLimitRight = 60;
    int titleXLimitLeft = 15;
    int titleYLimitTop = 50;
    int titleYLimitBottom = 80;
    int titleX = 35;
    int titleY = 50;
    int titleXVelocity = 2;
    int titleYVelocity = 2;


    /**
     * Constructor to initialize MenuPanel and  its properties
     */
    public MenuPanel(){
        this.setSize(GameUtility.windowDimension);

        timer = new Timer(10, this);

        //create an instance of the panel
        bgIcon = new ImageIcon("src/assets/ui/menubackgrounds/pinkBG.png");
        title = getTitle();
        background = new JLabel(bgIcon);


        startButtonContainer  =  new ButtonContainer();
        gameOverButtonContainer = new LBbuttonsContainer();
        newButtonContainer = new JPanel();

        //set and add components
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 100, GameUtility.WINDOW_HEIGHT/2));

        add(background);
        background.add(setButtonContainer());

        timer.start();
        System.out.println("MenuPanel created!");
    }

    /**
     * method to get the title depending on the game state
     * @return title
     */
    public ImageIcon getTitle(){
        try{
            if(GameController.getInstance().getGameState() == GameState.MENU){
                title =  new ImageIcon("src/assets/ui/titles/title.png");
               // return new ImageIcon("src/assets/ui/titles/title.png");
            }
            else if (GameController.getInstance().getGameState() == GameState.GAME_OVER){
                title = new ImageIcon("src/assets/ui/titles/gameOverTitle.png");
               // return new ImageIcon("src/assets/ui/titles/gameOverTitle.png");
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return title;
//        return null;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(GameController.getInstance().getGameState() == GameState.GAME_OVER) {
            //set font and font color for paint component
            g.setColor(Color.WHITE);
            g.setFont(GameUtility.getFont());
            g.drawString("High Score: " + GameController.getInstance().getPlayer().getScore(), 650, 50);
        }
        g.drawImage(getTitle().getImage(), titleX, titleY, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameController.getInstance().getGameState() != GameState.MENU && GameController.getInstance().getGameState() != GameState.GAME_OVER || GameController.getInstance().getGameState() != GameState.GAME_OVER && GameController.getInstance().getGameState() != GameState.MENU) {
            timer.stop();
        }

        if ((titleX > titleXLimitRight) || (titleX < titleXLimitLeft)) {
            titleXVelocity -= titleXVelocity * 2;
        }
        if ((titleY < titleYLimitTop) || (titleY > titleYLimitBottom)) {
            titleYVelocity -= titleYVelocity * 2;
        }
        titleX += titleXVelocity;
        titleY += titleYVelocity;

        setButtonContainer();
        repaint();
    }

    public Timer getTimer() {

        return timer;
    }

    public JPanel setButtonContainer(){
        background.remove(newButtonContainer);
        if(GameController.getInstance().getGameState().equals(GameState.MENU)){
            newButtonContainer = startButtonContainer;
//            System.out.println("Setting button container for MENU");
        }
        else if(GameController.getInstance().getGameState().equals(GameState.GAME_OVER)){
            newButtonContainer = gameOverButtonContainer;
//            System.out.println("Setting button container for GAME_OVER");
        }

        background.add(newButtonContainer);
        revalidate();

        return newButtonContainer;
    }

}
