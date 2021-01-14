import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import java.lang.*;
import java.io.*;
import javax.swing.*;
import java.util.Arrays;


public class GameEngine {
    ArrayList<String> dict;
    String guessWord;
    String displayWord;
    int guessesLeft;
    String oldGuesses;

    public GameEngine(){

    }

    public ArrayList<String> Extract(String[] dictFile){
			if(!dictFile.equals(null)){
				String file_name = dictFile[0];
				File f = new File(file_name);

				/* checking to see if file exits, scans document,
				 * and adds string p to arraylist dict.
				 */
				if(f.exists()){
					ArrayList<String> dict = new ArrayList<String>();
					Scanner scan = new Scanner(f);

					while(scan.hasNextLine()){
						String l = scan.nextLine();
						Scanner parseLine = new Scanner(l);

						while(parseLine.hasNext()){
							String p = parseLine.next();
							dict.add(p);
						}
          }
				}
			}
    }

  public static void main(String[] args){

  }
}
