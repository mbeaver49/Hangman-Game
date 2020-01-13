import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import java.lang.*;
import java.io.*;
import javax.swing.*;
import java.util.Arrays;


/**
 * This is the class we need to set up the game.
 * It makes the GUI, checks if inputs are valide,
 * and implements an actionlistener to the guess button
 * 
 * @author Beaver
 */
public class HMPanel extends JPanel{
	//initizlize variables to get word to be guessed, store guesses, and increment right and wrong
	private String word;
	private ArrayList<String> dict;
	private ArrayList<String> wordBank;
	private char[] wordToGuess;
	private	int amountOfGuesses = 7;
	private	int right = 0;
	private	int wrong = 0;

	//area to displayer word to be guessed
	private JPanel wordPanel = new JPanel();
	private JLabel wordMessage = new JLabel("Word to Guess: ");
	private JTextField wordLabel = new JTextField(" ");

	//area to display number of letters in the word to be guessed
	private JPanel infoPanel = new JPanel();
	private JLabel wordInfo = new JLabel("Word is long.");

	//area for button to guess
	private JPanel guessPanel = new JPanel();
	private JButton guessButton = new JButton("Guess a Letter");
	private JTextField guessField = new JTextField(10);

	//area to display resulting guess information (# of trys left and letters guessed)
	private JPanel guessesPanel = new JPanel();
	private JLabel guessesLabel = new JLabel("Already guessed: ");
	private JTextField guessesField = new JTextField(10);
	
	//initialize class drawHM
	private DrawHM drawHM;


	//layouts of panels that need to be added to HMPanel
	GridLayout rectLayout = new GridLayout(1,2);
	GridLayout boxLayout = new GridLayout(1,1);
	NumberFormat numberForm;


	public HMPanel(ArrayList<String> dict){
		//set layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//getting reference to arraylist dict
		this.dict = dict;

		//this is to get a random string from dict array list of strings, 
		//and pass that random string to word
		Random random = new Random();
		String word = dict.get(random.nextInt(dict.size()));

		//word panel set up
		this.word = word;
		wordPanel.setLayout(rectLayout);
		wordPanel.add(wordMessage);
		wordToGuess = new char[word.length()];
		
		String tmp1 = "";

		for(int u = 0; u < word.length(); u++){
			wordToGuess[u] = '_';
			tmp1 = tmp1 + " " + Character.toString(wordToGuess[u]);

		}

		wordLabel.setText(tmp1);
		wordPanel.add(wordLabel);
		add(wordPanel);
		wordLabel.setEditable(false);

		//word info panel set up
		infoPanel.setLayout(boxLayout);
		wordInfo.setText("Word is "+ word.length() +" long");
		infoPanel.add(wordInfo);
		add(infoPanel);

		//guess panel set up
		guessPanel.setLayout(rectLayout);
		guessPanel.add(guessButton);
		guessPanel.add(guessField);
		
		//Implement Action Listener to guessButton
		ActionListener guessing = new PlayingGame();
		guessButton.addActionListener(guessing);
		add(guessPanel);

		//guesses panel set up
		guessesPanel.setLayout(rectLayout);
		guessesPanel.add(guessesLabel);
		guessesPanel.add(guessesField);
		add(guessesPanel);
		//guessesField.setEditable(false);

		//for error checking
		numberForm =NumberFormat.getNumberInstance();
		//for display purposes
		numberForm.setMaximumFractionDigits(2);

	}

	/**
	 * Helper method to check if input is numeric
	 * @param s
	 * @return false
	 */
	public boolean isNumber(String s) { 
        for (int i = 0; i < s.length(); i++){
        	if (Character.isDigit(s.charAt(i))  == false){ 
            	return false; 

        	}
        }

        return true; 
    }

	/**
	 * Verifies that guess is a single characte, if not it asks again for character input and penalizes the player
	 * @param g
	 * @return false
	 */
	/**
	 * Verifies guess - if not it asks again for guess
	 * @param g
	 * @return false
	 */
	public boolean checkValidInputGuess(String g){
		boolean incorrect = true;
		while (incorrect){
			if(!isNumber(g)){
				if(g.length() == 0 || g.length() > 1 || g == ""){
						g = JOptionPane.showInputDialog("You need to guess one letter.");
						guessField.setText(g);
						wrong++;
						
					}
				}
			

			else{//nothing entered
			g = JOptionPane.showInputDialog("You need to guess one letter.");
			guessField.setText(g);
			wrong++;
		
			}

			incorrect = false;

		}

		return incorrect;
		
	}
	
	/**
	 * This where the game is implemented the most. If they guess right,
	 * then the hangman is not drawn and guessed letter is added into the
	 * guessed already window. If they guess wrong, then the hangman begins
	 * to be drawn and the guessed letter is added into the guessed already window.
	 * If you guess all the right letters you win. If you guess 7 wrong letters you lose.
	 * @author Beaver
	 *
	 */
	private class PlayingGame implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String g = guessField.getText();
			int aog = amountOfGuesses;
			int ri = right;
			int wr = wrong;

			if(checkValidInputGuess(g)) {
				boolean correct = false;
				
				for(int i = 0; i < word.length(); i++){
					int j = i;
					if(Character.toString(word.charAt(j)) == g) {
							wordToGuess[i] = word.charAt(j);
							correct = true;
							ri++;

					}
					else if(ri == word.length()){
						g = JOptionPane.showInputDialog("You won.");
						guessField.setText(g);
						add(guessesField);
					}
					else if(wr == aog){
						g = JOptionPane.showInputDialog("You Lose.");
						guessField.setText(g);
						add(guessesField);
					}else{
						wr++;
						g = JOptionPane.showInputDialog("You already guessed that letter. Penalty is given. Guess a letter that was not guessed already.");
						DrawHM drawHangMan = new DrawHM(aog, ri, wr);
						drawHangMan.setPreferredSize(new Dimension(500,500));
						//update screen
						revalidate();
						repaint();
						add(drawHangMan);
						guessField.setText(g);
						add(guessesField);

					}	
					
					
				}

				if(!correct){ 
					wr++;
					DrawHM drawHangMan = new DrawHM(aog, ri, wr);
					drawHangMan.setPreferredSize(new Dimension(500,500));
					//update screen
					revalidate();
					repaint();
					add(drawHangMan);
				
				}

				String tmp2 = "";
				for(int t = 0; t < wordToGuess.length; t++){
					tmp2 = tmp2 + wordToGuess[t];
				}

				wordLabel.setText(tmp2);	
				wordBank.add(g);
				add(wordLabel);
				
				
		}
		
	}
	
	
	}

}











	
	


