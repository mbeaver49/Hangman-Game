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
 * This is our Game
 *
 * @author Beaver
 */
public class HMPanel extends JPanel{
	//initizlize variables
	private String word;
	private ArrayList<String> dict;
	private ArrayList<String> wordBank;
	private char[] wordToGuess;
	private	int amountOfGuesses = 9;
	private String lettersGuessed = "";
	private int correct = 0;
	private int incorrect = 0;

	//area to displayer word to be guessed
	private JPanel wordPanel = new JPanel();
	private JLabel wordMessage = new JLabel("Word to Guess: ");
	private JTextField wordLabel = new JTextField(" ");

	//area to display number of letters in the word to be guessed
	private JPanel infoPanel = new JPanel();
	private JLabel wordInfo = new JLabel("Word is long.");

	private JPanel resultPanel = new JPanel();
	private JLabel correctAnswerInfo = new JLabel("Correct answers are: ");
	private JLabel incorrectAnswerInfo = new JLabel("Incorrect answers are: ");

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
  private JPanel drawPanel = new JPanel();


	//layouts of panels that need to be added to HMPanel
	GridLayout rectLayout = new GridLayout(1,2);
	GridLayout boxLayout = new GridLayout(1,1);
	NumberFormat numberForm;

	public HMPanel(ArrayList<String> dict){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.dict = dict;

		Random random = new Random();
		String word = dict.get(random.nextInt(dict.size()));

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

		infoPanel.setLayout(boxLayout);
		wordInfo.setText("Word consists of "+ word.length() +" characters.");
		infoPanel.add(wordInfo);
		add(infoPanel);

		resultPanel.setLayout(boxLayout);
		correctAnswerInfo.setText("Right: "+ correct);
		incorrectAnswerInfo.setText("Wrong: "+ incorrect);
		resultPanel.add(correctAnswerInfo);
		resultPanel.add(incorrectAnswerInfo);
		add(resultPanel);

		guessPanel.setLayout(rectLayout);
		guessPanel.add(guessButton);
		guessPanel.add(guessField);

		ActionListener guessing = new PlayingGame();
		guessButton.addActionListener(guessing);
		add(guessPanel);

		guessesPanel.setLayout(rectLayout);
		guessesPanel.add(guessesLabel);
		guessesPanel.add(guessesField);
    guessesField.setEditable(false);
		add(guessesPanel);

    drawPanel.setLayout(rectLayout);
    add(drawPanel);

		numberForm =NumberFormat.getNumberInstance();
		numberForm.setMaximumFractionDigits(2);

	}

	public boolean isLetter(String s) {
    for (int i = 0; i < s.length(); i++){
    	if (Character.isDigit(s.charAt(i))  == true){
        	return false;
    	}
    }
    return true;
	}

	public boolean isSingle(String s) {
			int l = s.length();
			if(l == 1){
			  return true;
			}else{
				return false;
			}
	}

	public boolean isNull(String s){
		if(s == null){
			return true;
		}else{
			return false;
		}
	}

	public String checkValidInputGuess(String g, String lettersG){
		if(!isLetter(g)){
			if(!isSingle(g)){
				try{
					g = JOptionPane.showInputDialog("You need to guess a single letter. Cannot be GREATER than 1 letter.");
				}
				catch(Exception e){
					g = JOptionPane.showInputDialog("You need to guess a single letter. Cannot be NOTHING.");
					incorrect++;
					incorrectAnswerInfo.setText("Wrong: "+ incorrect);
				}
			}else{
				try{
					g = JOptionPane.showInputDialog("You need to guess one letter. Cannot be a NUMBER, only a 1 letter.");
				}
				catch(Exception e){
					g = JOptionPane.showInputDialog("You need to guess a single letter. Cannot be NOTHING.");
					incorrect++;
					incorrectAnswerInfo.setText("Wrong: "+ incorrect);
				}
			}
			incorrect++;
			incorrectAnswerInfo.setText("Wrong: "+ incorrect);
			return checkValidInputGuess(g, lettersG);
		}
		if(!isSingle(g)){
			try{
				g = JOptionPane.showInputDialog("You need to guess one letter. Cannot be Greater than 1 letter.");
			}
			catch(Exception e){
				g = JOptionPane.showInputDialog("You need to guess a single letter. Cannot be NOTHING.");
				incorrect++;
				incorrectAnswerInfo.setText("Wrong: "+ incorrect);
			}
			incorrect++;
			incorrectAnswerInfo.setText("Wrong: "+ incorrect);
			return checkValidInputGuess(g, lettersG);
		}else{
			for(int i = 0; i < lettersG.length(); i++){
			if(lettersG.charAt(i) == g.charAt(0)){
					try{
						g = JOptionPane.showInputDialog("You need to guess that has not already been guessed.");
					}
					catch(Exception e){
						g = JOptionPane.showInputDialog("You need to guess a single letter. Cannot be NOTHING.");
						incorrect++;
						incorrectAnswerInfo.setText("Wrong: "+ incorrect);
					}
					incorrect++;
					incorrectAnswerInfo.setText("Wrong: "+ incorrect);
					return checkValidInputGuess(g, lettersG);
				}
			}
			return g;
		}
	}

	public boolean isCorrect(String guess, String gWord){
		for(int i = 0; i < gWord.length(); i++){
			if(gWord.charAt(i) == guess.charAt(0)){
				return true;
			}
		}
		return false;
	}

	public int getChars(String guess, String gWord){
		for(int i = 0; i < gWord.length(); i++){	if(gWord.charAt(i) == guess.charAt(0)){	return i;}}	return -1;
	}

	public int getCharTotal(String guess, String gWord){
		int tmp = 0;
		for(int i = 0; i < gWord.length(); i++){	if(gWord.charAt(i) == guess.charAt(0)){	tmp++;}};
		return tmp;
	}

	public String updateDisplay(char[] disp, String wtg, int index){
		char[] uDisp = disp;
		char cG = wtg.charAt(index);
		for(int j = 0; j < wtg.length(); j++){	if(wtg.charAt(j) == cG){	uDisp[j] = cG;}}
		String rString = "";
		for(int i = 0; i < wtg.length(); i++){	rString+= (uDisp[i]+ " ");}
		return rString;
	}

	public void resetGame(){
		Random random = new Random();
		String word = dict.get(random.nextInt(dict.size()));
		//word panel set up
		this.word = word;
		wordToGuess = new char[word.length()];
		String tmp1 = "";
		for(int u = 0; u < word.length(); u++){
			wordToGuess[u] = '_';
			tmp1 = tmp1 + " " + Character.toString(wordToGuess[u]);
		}
		correct = incorrect = 0;
		lettersGuessed = "";
		wordLabel.setText(tmp1);
		wordInfo.setText("Word consists of "+ word.length() +" characters.");
		correctAnswerInfo.setText("Right: "+ correct);
		incorrectAnswerInfo.setText("Wrong: "+ incorrect);
		guessesField.setText("");
	}

	public void gameState(int aog, int right, int wrong, int wLength){
		if(wrong == aog){
			if(JOptionPane.showConfirmDialog(null, "You Lost\nWord was: "+ word +"\nKeep Playing?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				resetGame();
			} else {
    		System.exit(0);
			}
		}
		if(right == wLength){
			if(JOptionPane.showConfirmDialog(null, "You Won\nKeep Playing?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				resetGame();
			} else {
    		System.exit(0);
			}
		}
	}

	private class PlayingGame implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String g = guessField.getText();

			String gg = checkValidInputGuess(g, lettersGuessed);
			guessField.setText("");
			lettersGuessed+=(gg+" ");
			guessesField.setText(lettersGuessed);
			boolean checker = isCorrect(gg, word);
		if(!checker){
				incorrect++;
				incorrectAnswerInfo.setText("Wrong: "+ incorrect);
				gameState(amountOfGuesses, correct, incorrect, word.length());
        drawHM = new DrawHM(incorrect);
        drawHM.setPreferredSize(new Dimension(500, 500));
        revalidate();
        repaint();
        drawPanel.add(drawHM);

    }else{
				int ind = getChars(gg, word);
				if(ind < 0){
					JFrame frame = new JFrame("Error in Getting Chars");
					JOptionPane.showMessageDialog(frame, "Error, Check code for case study.");
					System.exit(0);
				}else{
					char cGuess = word.charAt(ind);
					for(int j = 0; j < getCharTotal(g, word); j++){
						correct++;
					}
					correctAnswerInfo.setText("Right: "+ correct);
					wordLabel.setText(updateDisplay(wordToGuess, word, ind));
					gameState(amountOfGuesses, correct, incorrect, word.length());
				}
			}
		}
	}
}
