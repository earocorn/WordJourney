/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author bhump
 */
public class DataManager {
   
   private ArrayList<String> scores = new ArrayList<>();
   private JSONParser parser = new JSONParser();
   JSONObject jo;
    
    /**
     *
     */
    public DataManager() {
        try {
            Object obj = parser.parse(new FileReader("src/assets/playerdata/highscores.json"));
            jo = (JSONObject) obj;
            scores = (ArrayList<String>) jo.get("scores");
            
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     * @return scores
     */
    public ArrayList getScores() {
       return scores;
    }
    
    public void print() {
        System.out.println("Highscores = " + scores);
    }
    
    public void updateScore(int score){
        if (score > Integer.parseInt(scores.get(0))) {
            scores.set(2, scores.get(1));
            scores.set(1, scores.get(0));
            scores.set(0, Integer.toString(score));
        }
        else if (score > Integer.parseInt(scores.get(1))) {
            scores.set(2, scores.get(1));
            scores.set(1, Integer.toString(score));
        }
        else if (score > Integer.parseInt(scores.get(2))) {
            scores.set(2, Integer.toString(score));
        }
        this.writeScores();
    }
    
    public void writeScores() {
        jo.put("scores", scores);
    }
}
