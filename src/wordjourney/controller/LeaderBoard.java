package wordjourney.controller;

import wordjourney.view.components.LeaderBoardView;

import java.io.*;

/**
 *
 */
public class LeaderBoard {
    private static LeaderBoard lBoard;
    String playerName;
    int playerScore;

    public LeaderBoard(){
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
}
