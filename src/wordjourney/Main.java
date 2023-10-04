/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordjourney;

//import wordjourney.util.DataManager;
import wordjourney.graphics.WordleComponent;
import wordjourney.graphics.GamePanel;

/**
 *
 * @author alexalmanza
 */
public class Main {
    
    public static WordleComponent wordleComponent;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // GameData test function
//        DataManager.test();
        
        // look in WordleComponent constructor for more information
        // this is definitely a tiny bit CRAZY doing this but we will
        // change it later to make the code pretty
        wordleComponent = new WordleComponent();
    }
    
}
