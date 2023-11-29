package wordjourney.model;

/**
 * Represents the model component for the Wordle game, storing information
 * about the current game state.
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

    /**
     * Gets the current line in the Wordle game.
     *
     * @return The current line number.
     */
    public int getCurrentLine(){
        return currentLine;
    }

    /**
     * Sets the current line in the Wordle game.
     *
     * @param currentLine The new current line number.
     */
    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    /**
     * Gets the current wordle in the Wordle game.
     *
     * @return The current wordle string.
     */
    public String getCurrentWordle() {
        return currentWordle;
    }

    /**
     * Sets the current wordle in the Wordle game.
     *
     * @param currentWordle The new current wordle string.
     */
    public void setCurrentWordle(String currentWordle) {
        this.currentWordle = currentWordle;
    }

    /** Gets the user's input word in the Wordle game.
    *
    * @return The user's input word.
    */
    public String getUserWord() {
        return userWord;
    }

    /**
     * Sets the user's input word in the Wordle game.
     *
     * @param userWord The new user input word.
     */
    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }
}