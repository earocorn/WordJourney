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
        
        //pull json data for leaderboard
        
        //create an instance of the panel
        bgIcon = new ImageIcon("src/assets/ui/menubackgrounds/pinkBG.png");
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
            if(GameController.getInstance().getGameState() == GameState.MENU){
                title =  new ImageIcon("src/assets/ui/titles/title.png");
            }
            else if (GameController.getInstance().getGameState() == GameState.GAME_OVER){
                title = new ImageIcon("src/assets/ui/titles/gameOverTitle.png");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return title;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        // TODO: Also print the player's high score on the gameover screen after they die. Print giant bubble image on the menu when in the main menu gamestate. Just make main menu and game over menu look clean basically
        g.drawImage(getTitle().getImage(), titleX, titleY, null);
        
        g.setColor(Color.BLACK);
        g.setFont(GameUtility.getFont());
        g.drawString("Leaderboard: ", 80, 355);
        
        for(int i = 0; i < 3; i++) {
            // assuming name and score are 0 and 1 index
            // getWidth() and getHeight() get width and height of parent container but you can use GameUtility to get width and height
            // basing where they are placed by a constant c (20) + the iterator i will evenly space them out
            g.drawString(Integer.toString(i + 1) + " - " + GameUtility.getInstance().scoreData.scores.get(i), 105, 385 + (30*i));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameController.getInstance().getGameState() != GameState.MENU) {
            // TODO: figure out how to stop and start menu timer gracefully when GameState changes
            timer.stop();
        }

        System.out.println("X: " + titleX + ", Y: " + titleY);
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
