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
 * Class used to represent a graphical panel displaying the main menu and the gamer panel
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
     * Constructor to initialize MenuPanel and its properties
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
     * method to get the title depending on the game state, will return the game Over title icon or the game  title icon
     * @return title
     */
    public ImageIcon getTitle(){
        try{
            if(GameController.getInstance().getGameState() == GameState.MENU){
                title =  new ImageIcon("src/assets/ui/titles/title.png");
               // return new ImageIcon("src/assets/ui/titles/title.png");
            }
            else if (GameController.getInstance().getGameState() ==  GameState.GAME_OVER){
                title = new ImageIcon("src/assets/ui/titles/gameOverTitle.png");
               // return new ImageIcon("src/assets/ui/titles/gameOverTitle.png");
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return title;
//        return null;
    }

    /**
     * method to pain the highscore on the panel if the game state is game over it also paints the title on the panel
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
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

    /**
     * method that animates the title to give it the shaking effect, calls the buttoncontainer method , and the repaint function
     *
     * @param e the event to be processed
     */
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

    /**
     * method to get the current panel timer
     * @return timer
     */
    public Timer getTimer() {

        return timer;
    }

    /**
     * method to add the button container based on the game state,  since the gameover panel and the menu panel have separate buttons to display
     * @return newButtonContainer
     */
    public JPanel setButtonContainer(){
        background.remove(newButtonContainer); //removes the current button container
        if(GameController.getInstance().getGameState().equals(GameState.MENU)){
            newButtonContainer = startButtonContainer; //sets the new button container if game state is menu
        }
        else if(GameController.getInstance().getGameState().equals(GameState.GAME_OVER)) {
            newButtonContainer = gameOverButtonContainer; // sets the new button container if game state is game over
        }
        background.add(newButtonContainer); //adds button container
        revalidate();
        return newButtonContainer;
    }

}
