/*** Game.java is main for our JVE.
     Takes .txt file as its only argument to make a dictionary out of.


     author: Manvel Beaver
***/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;
import java.lang.*;
import java.util.Arrays;
import java.io.*;
public class Game extends JFrame{
	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;

	//constructor to take an ArrayLists dict
	public Game(ArrayList<String> dict){
		//attempt at passing the argument
		HMPanel hmp = new HMPanel(dict);
		add(hmp);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}

	public static void main(String[] args){
    //Check if we have any arguments, if not then exit.
		try{
			if(args.length > 0){
				String file_name = args[0];
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
					JFrame frame = new Game(dict);
					frame.setTitle("Hang Man");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			}
		}//if no file was found then Game will close.
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
