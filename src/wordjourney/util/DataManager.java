/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wordjourney.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *The DataManager class manages the data for the word journey game, including loading and
 * sorting player scores in a leaderboard, pushing new entries, and writing scores to a file.
 *
 * @author
 */
public class DataManager {

    // JSONArray to store player data
    JSONArray players = null;

    // TreeMap to store and automatically sort the leaderboard
   TreeMap<String, Long> sortedLeaderboard = null;
    
    /**
     * Constructs a new DataManager object. It loads player data from a JSON file and
     * initializes the leaderboard.
     */
    public DataManager() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/assets/playerdata/highscores.json"));
            players = (JSONArray) obj;

            //map to store players scores
            Map<String, Long> leaderboardMap = new HashMap<>();

            // populated the leaderboard with  data from players array
            for (Object playerObject : players) {
                JSONObject player = (JSONObject) playerObject;
                leaderboardMap.put((String) player.get("name"), (Long) player.get("score"));
            }

            System.out.println("Leaderboard loaded");

            //tree map automatically sorts data bassed on players score
            sortedLeaderboard = new TreeMap<>(Comparator.comparingLong(leaderboardMap::get));
            sortedLeaderboard.putAll(leaderboardMap);
            System.out.println(sortedLeaderboard);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * retrieves the sorted leaderbaord
     @return A TreeMap representing the leaderboard with player names and scores
     */
    public TreeMap<String, Long> getLeaderboard() {
       return sortedLeaderboard;
    }

    /**
     * adds a enw entry to the leaderboard
     * @param name name of player
     * @param score points achieved by player during their game
     */
    public void pushEntry(String name, int score) {
        sortedLeaderboard.put(name, (long) score);
//        System.out.println(sortedLeaderboard + "\nAdded " + name + " with score of " + score);
    }

    /**
     * method to write the players scores to a JSOn file
     */
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
