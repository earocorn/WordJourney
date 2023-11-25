package wordjourney.model;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class LeaderBoard {


    private static LeaderBoard lBoard;
    String playerName;
    int playerScore;

    private String filePath;
    private String highScores;
    private ArrayList<Integer> topScores;


    public LeaderBoard(){

        highScores = "Scores:";
        topScores = new ArrayList<Integer>();

        //leaderboard path stored
        filePath = new File("").getAbsolutePath();


    }
    public static LeaderBoard getInstance(){
        if(lBoard ==null){
            lBoard = new LeaderBoard();
        }
        return lBoard;
    }

    /**
     * @param score
     * method to add score to the leaderboard and removes the lowest score
     */
    public void addScore(int score){
        for(int i =0; i<topScores.size(); i++){
            if (score>= topScores.get(i)){
                topScores.add(i,score);
                topScores.remove(topScores.size() - 1);
                return;
            }
        }
    }



    public void loadScores(){
        try{
            File f = new File(filePath, highScores);
            if(!f.isFile()){
                createSaveData();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            topScores.clear();

            String[] scores = reader.readLine().split("-");

            for (String score : scores) {
                topScores.add(Integer.parseInt(score));
            }

            reader.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveScores(){
        FileWriter output = null;
        try{
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write(topScores.get(0)+"-"+ topScores.get(1)+ "-"+ topScores.get(2)+ "-"+ topScores.get(3)+ "-"+ topScores.get(4));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createSaveData(){
        FileWriter output = null;
        try{
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write( "0-0-0-0-0");
            writer.write(Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE +"-"+ Integer.MAX_VALUE);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public int getHighScore(){
        return topScores.get(0);
    }

    public ArrayList<Integer> getTopScores() {
        return topScores;
    }


}
