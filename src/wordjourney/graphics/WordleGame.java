package wordjourney.graphics;

import wordjourney.util.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordleGame implements KeyListener, ActionListener {

	public static GamePanel panel;
	public static JFrame gameFrame;
	private final WordPanel[] wordPanelArray = new WordPanel[6];
	private final UserPanel userPanel;
	private String wordleString;
	private int currentLine = 0;
	public static int score = 0;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterButtonEvent();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

	public WordleGame(GameFrame gameFrame) {
		
		// ok i just initialized GamePanel in this class because this is where all the JFrame stuff is
		panel = new GamePanel();

		gameFrame.add(panel, "Graphics");

		// and 1 row of user input
		JPanel wordleContainer = new JPanel(new GridLayout(7, 1));

		for (int i = 0; i < 6; i++) {
			wordPanelArray[i] = new WordPanel();
			wordleContainer.add(wordPanelArray[i]);
		}
		gameFrame.add(wordleContainer, "WordPanelGrid");

		userPanel = new UserPanel();
		userPanel.getEnterButton().addActionListener(this);
		// adds user input to 7th row of the wordleContainer GridLayout
		wordleContainer.add(userPanel, "UserPanel");
		GamePanel.background.setLayout(new FlowLayout());
		GamePanel.background.add(wordleContainer);
		gameFrame.pack();

		//load the word for the round
		wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);

		// focus on main JFrame
		gameFrame.requestFocus();
		userPanel.getUserInput().grabFocus();
		userPanel.getUserInput().addKeyListener(this);

//		GameManager.showStartMenuScreen(panel); // use this to look at just the GameOverPanel to design :3
	}

        
	public void enterButtonEvent() {
		String userWord = this.userPanel.getUserInput().getText().trim().toUpperCase();
		this.userPanel.clearUserInput();

		// dont allow words not equal to 5
		if (userWord.length() != 5) {
			return;
		}

		//check if user word is equal if so increments points and clears panel
		if (isWordleWordEqualTo(userWord)) {
			GameManager.addPoint(panel);
			clearAllPanels();
			return;
		}
		//checks if users current line is over guess limit, if so removes life and clears panel
		if (currentLine >=5) {
			GameManager.removeOneLife(panel);
			clearAllPanels();
			return;
		}
		currentLine++;
	}

	public WordPanel getActivePanel() {

		return this.wordPanelArray[currentLine];
	}
	@Override
	public void actionPerformed(ActionEvent e) {
            enterButtonEvent();
	}

	//function to clear panel and calls another wordle
	private void clearAllPanels() {
		for (int i = 0; i <= currentLine; i++) {
			wordPanelArray[i].clearWordPanel();                       
		}
		wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);
		currentLine=0;
	}

	//function that  checks if users guess is equal to the wordle
	private boolean isWordleWordEqualTo(String userWord) {
		List<String> wordleWordsList = Arrays.asList(wordleString.split(""));
		String[] userWordsArray = userWord.split("");
		List<Boolean> wordMatchesList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			if (wordleWordsList.contains(userWordsArray[i])) {
				if (wordleWordsList.get(i).equals(userWordsArray[i])) {
					getActivePanel().setPanelText(userWordsArray[i], i, Color.GREEN);
					wordMatchesList.add(true);
				} else {
					getActivePanel().setPanelText(userWordsArray[i], i, Color.YELLOW);
					wordMatchesList.add(false);
				}
			} else {
				getActivePanel().setPanelText(userWordsArray[i], i, Color.GRAY);
				wordMatchesList.add(false);
			}
		}
		return !wordMatchesList.contains(false);
	}


	public String getWordleString() {
		Path path = Paths.get("src/assets/Words.txt");
		List<String> wordList = new ArrayList<>();
		try {
			wordList = Files.readAllLines(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Random random = new Random();
		int position = random.nextInt(wordList.size());
		return wordList.get(position).trim().toUpperCase();
	}



}