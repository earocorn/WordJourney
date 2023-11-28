/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author bhump
 */
public class DataManager {

    JSONArray players = null;
   TreeMap<String, Long> sortedLeaderboard = null;
    
    /**
     *
     */
    public DataManager() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/assets/playerdata/highscores.json"));
            players = (JSONArray) obj;
            Map<String, Long> leaderboardMap = new HashMap<>();
            for (Object playerObject : players) {
                JSONObject player = (JSONObject) playerObject;
                leaderboardMap.put((String) player.get("name"), (Long) player.get("score"));
            }

            System.out.println("Leaderboard loaded");
            // adding data to tree automatically sorts it??
            sortedLeaderboard = new TreeMap<>(leaderboardMap);
            sortedLeaderboard.putAll(leaderboardMap);
            System.out.println(sortedLeaderboard);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     * @return scores
     */
    public TreeMap<String, Long> getLeaderboard() {
       return sortedLeaderboard;
    }

    public void pushEntry(String name, int score) {
        sortedLeaderboard.put(name, (long) score);
        System.out.println(sortedLeaderboard + "\nAdded " + name + " with score of " + score);
    }

//    public void updateScore(int finalScore){
//        if (finalScore > Integer.parseInt(scores.get(0), 10)) {
//            scores.set(2, scores.get(1));
//            scores.set(1, scores.get(0));
//            scores.set(0, Integer.toString(finalScore));
//        }
//        else if (finalScore > Integer.parseInt(scores.get(1), 10)) {
//            scores.set(2, scores.get(1));
//            scores.set(1, Integer.toString(finalScore));
//        }
//        else if (finalScore > Integer.parseInt(scores.get(2), 10)) {
//            scores.set(2, Integer.toString(finalScore));
//        }
//        this.writeScores();
//    }
    
    public void writeScores() {
        for(Map.Entry<String, Long> entry : sortedLeaderboard.entrySet()) {
            String name = entry.getKey();
            Long value = entry.getValue();
            System.out.println(name + " => " + value);
            JSONObject newEntry = new JSONObject();
            newEntry.put("name", name);
            newEntry.put("score", value);
            players.add(newEntry);
        }

        try (FileWriter file = new FileWriter("src/assets/playerdata/highscores.json")) {
            file.write(players.toJSONString());
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
