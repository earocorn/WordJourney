package wordjourney.model;

/**
 *
 */
public enum GameState {

    MENU("MAIN MENU"),
    GAME_OVER("GAME-OVER"), //TODO: game over text in this state
    IN_GAME("WORD JOURNEY"), //TODO: this needs to put the game panel on the screen
    LEADERBOARD("LEADERBOARD"),
    QUIT("QUIT_BUTTON");  //TODO: ADD FUNCTIONALITY THAT WILL EXIT THE GAME IF PRESSED

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

    /*
    //
    if (newState == GameState.MENU || newState == GameState.GAME_OVER) {
        menuPanel.updateTitle();
    }
     */

}