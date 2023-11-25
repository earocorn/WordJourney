package wordjourney.model;

/**
 *
 */
public enum GameState {
    MENU("MAIN MENU"),
    GAME_OVER("GAME-OVER"),
    IN_GAME("WORD JOURNEY") {
        // TODO: NOT CONCRETE: We could either use the GameController.setState(gameState) and a switch-case statement that goes through whichever gameState, OR I believe we could put functions in the constructor of the enum yet I have not tested this. Please let me know opinions and any questions.
        // GameController.doSomething()
    },
    LEADERBOARD("LEADERBOARD");

    private String title;

    GameState(String s) {
        title = s;
    }

    /**
     * method to return the game state title
     * @return title
     */
    public String getTitle() {
        return title;
    }

}