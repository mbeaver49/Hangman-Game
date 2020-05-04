import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.geom.*;



/**
 * This creates the area to draw on and draws the hangman.
 * 
 * @author Beaver
 *
 */
public class DrawHM extends JComponent {

	private int amountOfGuesses;
	private int right;
	private int wrong;
	
	/**
	 * constructor for drawHM
	 * @param amountOfGuesses either the string "rectangle" or "triangle"
	 * @param r - int right answers
	 * @param w - int wrong answers
	 */
	public DrawHM(int amountOfGuesses, int r, int w){
		this.amountOfGuesses = amountOfGuesses;
		right = r;
		wrong = w;
		
	}

	/**
	 * paint method that draws the selected shape
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g; 

		g2.setStroke(new BasicStroke(5,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		g2.setColor(Color.BLACK); 
		g.drawLine(50, 30, 50, 10);
		g.drawLine(50, 10, 130, 10);
		g.drawLine(130, 10, 130, 300);
		g.drawLine(20, 300, 150, 300);

		if (wrong==1){
			//draws the head
			g2.setStroke(new BasicStroke(3,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
			Ellipse2D.Double head = new Ellipse2D.Double(25, 30, 50, 50);
			g2.draw(head); 
		}

		else if (wrong==2){
			//draws the body
			g.drawLine(50, 80, 50, 180);
		}
		else if (wrong==3){
			//draws the left arm
			g.drawLine(10, 150, 50, 100);
		}
		else if (wrong==4){
			//draws the right arm
			g.drawLine(50, 100, 90, 150);
		}
		else if (wrong==5){
			//draws the left leg
			g.drawLine(30, 250, 50, 180);
		}
		else if (wrong==6){
			//draws the right leg
			g.drawLine(50, 180, 70, 250);
		}
		else if (wrong==7){
			//draws the left eye
			Ellipse2D.Double lefteye = new Ellipse2D.Double(40, 50, 1, 1);
			g2.draw(lefteye); 
			g2.fill(lefteye);
		}
		else if (wrong==8){
			//draws the right eye
			Ellipse2D.Double righteye = new Ellipse2D.Double(58, 50, 1, 1);
			g2.draw(righteye); 
			g2.fill(righteye);
		}
		else if (wrong==9){
			//draws the mouth
			Arc2D.Double mouth = new Arc2D.Double(40.00, 50.00, 20.00, 20.00, 180.00, 190.00, Arc2D.OPEN); 
			g2.draw(mouth); 
		}
	
	}
        
}
