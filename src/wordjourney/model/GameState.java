package wordjourney.model;

/**
 * GameState enum represents the different states of the game
 */
public enum GameState {
    MENU("MAIN MENU"),
    GAME_OVER("GAME-OVER"),
    IN_GAME("WORD JOURNEY") ,
    LEADERBOARD("LEADERBOARD");

    private String title;

    /**
     * Constructs a new game state enum constant with specified title
     * @param s title of the game state
     */
    GameState(String s) {
        title = s;
    }

    /**
     * method to retrieve the game state title
     * @return the title of the game state
     */
    public String getTitle() {
        return title;
    }

}