///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package wordjourney.util;
//
//import java.io.FileReader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
///**
// *
// * @author alexalmanza
// */
//public class DataManager {
//
//    // just to test READING from a JSON file to read game data for leaderboard
//    public static void test() {
//
//        JSONParser parser = new JSONParser();
//
//        Map<Long, String> leaderboardMap = new HashMap<>();
//
//        try {
//            Object obj = parser.parse(new FileReader("src/wordjourney/data/leaderboard.json"));
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONArray players = (JSONArray) jsonObject.get("players");
//            for (Object playerObject : players) {
//                JSONObject player = (JSONObject) playerObject;
//                leaderboardMap.put((Long) player.get("score"),(String) player.get("name"));
//            }
//
//            // adding data to tree automatically sorts it??
//            TreeMap<Long, String> sortedLeaderboard = new TreeMap<>(leaderboardMap);
//            sortedLeaderboard.putAll(leaderboardMap);
//            System.out.println(sortedLeaderboard);
//
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//
//    }
//
//}
