package wordjourney.graphics;

import wordjourney.util.GameUtility;

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
import wordjourney.util.GameManager;

public class WordleComponent implements KeyListener, ActionListener {

	public static GamePanel panel;
	public GameOverPanel gameOverPanel;
	public static JFrame gameFrame;
	private WordPanel[] wordPanelArray = new WordPanel[6];
	private UserPanel userPanel;
	private String wordleString;
	private int currentLine = 0;
	private JPanel wordleContainer;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
            //System.out.println("key pressed : " + e);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            //GameManager.move(panel);
            enterButtonEvent();
            //System.out.println("jump key");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}


	public WordleComponent() {
		// ok i just initialized GamePanel in this class because this is where all the JFrame stuff is
		panel = new GamePanel();

		gameFrame = new JFrame("Word Journey");
		gameFrame.setSize(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new FlowLayout());
		gameFrame.setVisible(true);
		gameFrame.setResizable(false);
		gameFrame.setAlwaysOnTop(true);
		gameFrame.add(panel, "Graphics");

		//gameFrame.addKeyListener(this);

		// wordle container is a JPanel that contains the 6 rows of letter boxes
		// and 1 row of user input
		wordleContainer = new JPanel(new GridLayout(7, 1));
                
		for (int i = 0; i < 6; i++) {
			wordPanelArray[i] = new WordPanel();
			wordleContainer.add(wordPanelArray[i]);
		}
		gameFrame.add(wordleContainer, "WordPanelGrid");
		userPanel = new UserPanel();
		userPanel.getEnterButton().addActionListener(this);
		// adds user input to 7th row of the wordleContainer GridLayo
		wordleContainer.add(userPanel, "UserPanel");

		// IMPORTANT: adds wordleContainer to the JLabel "background" which
		// has the background image. didn't know it was possible but i guess
		// we can add stuff on top of JLabels

		GamePanel.background.setLayout(new FlowLayout());
		GamePanel.background.add(wordleContainer);

		gameFrame.setLocationRelativeTo(null);
		gameFrame.pack();
		gameFrame.revalidate();

		//load the word for the round
		wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);
                
		// focus on main JFrame
		gameFrame.requestFocus();
		userPanel.getUserInput().grabFocus();
		userPanel.getUserInput().addKeyListener(this);
	}
        
	public void enterButtonEvent() {
		String userWord = this.userPanel.getUserInput().getText().trim().toUpperCase();
		this.userPanel.getUserInput().setText("");

		// dont allow words not equal to 5
		if (userWord.length() != 5) {
			return;
		}

		if (isWordleWordEqualTo(userWord)) {
			//TODO: implement successfully guessed word logic
			// points++
			clearAllPanels();
			return;
		}
		if (currentLine >=5) {
			//TODO: implement FAILED wordle logic
			// points--;
			GameManager.removeOneLife(panel);
			clearAllPanels();
			return;
		}

		currentLine++;
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

	//function that
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

	public WordPanel getActivePanel() {

		return this.wordPanelArray[currentLine];
	}

	public String getWordleString() {
		Path path = Paths.get("src/assets/Words.txt");
		List<String> wordList = new ArrayList<>();
		try {
			wordList = Files.readAllLines(path);
		} catch (IOException e) {
		}
		Random random = new Random();
		int position = random.nextInt(wordList.size());
		return wordList.get(position).trim().toUpperCase();
	}

}
