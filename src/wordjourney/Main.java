/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordjourney;

import external.WordleGame;
import external.WordleMain;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import wordjourney.graphics.GameFrame;
import wordjourney.graphics.GamePanel;

/**
 *
 * @author alexalmanza
 */
public class Main {
    
    public static GameFrame wordJourneyFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        wordJourneyFrame = new GameFrame();
        
        JSONParser parser = new JSONParser();
        
        Map<Long, String> leaderboardMap = new HashMap<>();
        
        try {
            Object obj = parser.parse(new FileReader("src/gamedata/leaderboard.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray players = (JSONArray) jsonObject.get("players");
            for (Object playerObject : players) {
                JSONObject player = (JSONObject) playerObject;
                leaderboardMap.put((Long) player.get("score"),(String) player.get("name"));
            }
            
            TreeMap<Long, String> sortedLeaderboard = new TreeMap<>(leaderboardMap);
            sortedLeaderboard.putAll(leaderboardMap);
            System.out.println(sortedLeaderboard);
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        new WordleGame();
        WordleMain.initializeWordle();
        
    }
    
}
