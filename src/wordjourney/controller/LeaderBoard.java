package wordjourney.controller;

import wordjourney.util.GameUtility;
import wordjourney.view.components.LeaderBoardView;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class LeaderBoard {
    private static LeaderBoard lBoard;
    String playerName;
    int playerScore;
    private ArrayList<String> players;
    private ArrayList<String> scores;

    public LeaderBoard(){
        players = new ArrayList<>();
        scores = new ArrayList<>();
    }
    public static LeaderBoard getInstance() {
        if (lBoard == null) {
            lBoard = new LeaderBoard();
        }
        return lBoard;
    }

    public void setPlayer(String playerName, int playerScore){
        this.playerScore = playerScore;
        this.playerName = playerName;
    }

    public void saveScores(){
        FileWriter output = null;
        try{
            File f = new File("currentPlayerStats.txt");
//            File f = new File(filePath, highScores);
            output = new FileWriter(f, true);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(playerName+ "-"+ playerScore + "\n");
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createSaveData(){
        FileWriter output;
        try{
            File f = new File("allPlayerStats.txt");
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write( "Player-0");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void readPlayerStats(){
        try(BufferedReader reader = new BufferedReader(new FileReader("src/wordjourney/util/currentPlayerStats.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stats = line.split("-");
                if(stats.length ==2){
                    players.add(stats[0].trim());
                    scores.add(stats[1].trim());
                    //playerStats.add(new PlayerStats(players, scores));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLeaderBoard() {
        LeaderBoard.getInstance().readPlayerStats();
        for (int i =0; i< Math.min(5, players.size()); i++){
           LeaderBoardView.setLBText(players.get(i), Integer.parseInt(scores.get(i)),i);
        }
    }

}
