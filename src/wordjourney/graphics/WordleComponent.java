package wordjourney.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import wordjourney.util.GameManager;
import wordjourney.graphics.GamePanel;
import wordjourney.util.GameUtility;

public class WordleComponent implements ActionListener {

	class WordPanel extends JPanel {

		JLabel[] wordColumns = new JLabel[5];

		public WordPanel() {
			this.setLayout(new GridLayout(1, 5));
			Border blackBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
			for (int i = 0; i < 5; i++) {
				wordColumns[i] = new JLabel();
                                wordColumns[i].setSize(new Dimension(50, 50));
                                wordColumns[i].setPreferredSize(new Dimension(50, 50));
				wordColumns[i].setHorizontalAlignment(JLabel.CENTER);
				wordColumns[i].setOpaque(true);
                                wordColumns[i].setBackground(Color.WHITE);
				wordColumns[i].setBorder(blackBorder);
				this.add(wordColumns[i]);
			}
		}

		public void clearWordPanel() {
			for (int i = 0; i < 5; i++) {
				wordColumns[i].setText("");
                                wordColumns[i].setBackground(Color.WHITE);
			}
		}

		public void setPanelText(String charValue, int position, Color color) {
			this.wordColumns[position].setText(charValue);
			this.wordColumns[position].setBackground(color);
                        GameManager.move(panel);
		}
                
	}

	class UserPanel extends JPanel {

		private JTextField userInput;
		private JButton enterButton;

		public UserPanel() {
			this.setLayout(new GridLayout(1, 1));
			userInput = new JTextField();
			this.add(userInput);
			enterButton = new JButton("ENTER");
			this.add(enterButton);
		}
		public JTextField getUserInput() {
			return userInput;
		}
		public JButton getEnterButton() {
			return enterButton;
		}
	}

        private GamePanel panel;
	private JFrame gameFrame;
	private WordPanel[] wordPanelArray = new WordPanel[6];
	private UserPanel userPanel;
	private String wordleString;
	private int count = 0;
        
        private JPanel wordleContainer;
                      
                      
	public WordleComponent() {
                // ok i just initialized GamePanel in this class because this is where all the JFrame stuff is
                panel = new GamePanel();
                
		gameFrame = new JFrame("Word Journey");
		gameFrame.setSize(GameUtility.WINDOW_WIDTH, GameUtility.WINDOW_HEIGHT);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // any layout really works here, Flow is default i think
                // theres probably a better one
		gameFrame.setLayout(new FlowLayout());
		gameFrame.setVisible(true);
                gameFrame.setResizable(false);
                gameFrame.setAlwaysOnTop(true);
                gameFrame.add(panel, "Graphics");

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

                // adds user input to 7th row of the wordleContainer GridLayout
                
		wordleContainer.add(userPanel, "UserPanel");

                                        // IMPORTANT: adds wordleContainer to the JLabel "background" which
                                        // has the background image. didn't know it was possible but i guess
                                        // we can add stuff on top of JLabels

                                        // If you remove ", new GridBagConstraints()" the window looks exactly the same
                                        // but it might be necessary for some reason

                                            GamePanel.background.setLayout(new FlowLayout());
                                            GamePanel.background.add(wordleContainer);
                
		gameFrame.setLocationRelativeTo(null);
                                            gameFrame.pack();

                                            // always revalidate after adding component to window
                
		gameFrame.revalidate();

		wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);
	}
        
	@Override
	public void actionPerformed(ActionEvent e) {
		String userWord = this.userPanel.getUserInput().getText();
                                            this.userPanel.getUserInput().setText("");

                // dont allow words not equal to 5
		if (userWord.length() != 5) { 
                                                                  return; 
                                                        }
                
                                            if (isWordleWordEqualTo(userWord.trim().toUpperCase())) {
                
                                                                    //TODO: implement successfully guessed word logic
                                                                    // points++;
                                                                  clearAllPanels();
                                                                  return;
                                                       }
		if (count > 5) {
                                                                //TODO: implement FAILED wordle logic
                                                                // points--;
                                                                  clearAllPanels();
                                                                 return;
                                                       }
                                            count++;
                      }

	private void clearAllPanels() {
		for (int i = 0; i <= count; i++) {
			wordPanelArray[i].clearWordPanel();                       
		}
                                            wordleString = getWordleString();
		System.out.println("Word for the day : " + wordleString);
                                            count=0;
	}

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
		return this.wordPanelArray[count];
	}

	public String getWordleString() {
		Path path = Paths.get("src/assets/Words.txt");
		List<String> wordList = new ArrayList<>();
		try {
			wordList = Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		int position = random.nextInt(wordList.size());
		return wordList.get(position).trim().toUpperCase();
	}

}
