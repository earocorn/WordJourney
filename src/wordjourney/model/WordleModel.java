package wordjourney.model;

/**
 *
 */
public class WordleModel {

    private int currentLine;
    private String currentWordle;
    private String userWord;

    public WordleModel(){
        this.currentLine = 0;
        this.currentWordle = "";
        this.userWord = "";
    }

    public int getCurrentLine(){
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    public String getCurrentWordle() {

        return currentWordle;
    }

    public void setCurrentWordle(String currentWordle) {
        this.currentWordle = currentWordle;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

}