import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.geom.*;



/**
* This class takes the argument of the amount of guesses left
* to indicate which visual state the hangman image will be.
* 
* @author Manvel Beaver
*/
public class DrawHM extends JComponent {

    private int incorrect;

    /**
    * constructor for drawHM
    * @param amountOfGuesses Stores the integer that indicates how many guesses are left.
    */
    public DrawHM(int inc){
        incorrect = inc;

    }

    /**
    * paint method that draws the selected shape
    */
    public void paintComponent(Graphics g){

        int wrong = incorrect;

        super.paintComponent(g);

        removeAll();
        setBackground(Color.WHITE);

        g.setColor(Color.black);

        if (wrong==1){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

        }
        else if (wrong==2){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);
        }
        else if (wrong==3){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

        }
        else if (wrong==4){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);


        }
        else if (wrong==5){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);

            //draw right leg
            g.drawLine(50, 140, 65, 210);
           
        }
        else if (wrong==6){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);

            //draw right leg
            g.drawLine(50, 140, 65, 210);
           
            //draw left arm
            g.drawLine(50, 70, 30, 100);
        }

        else if (wrong==7){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);

            //draw right leg
            g.drawLine(50, 140, 65, 210);

            //draw left arm
            g.drawLine(50, 70, 30, 100);

            //draw left leg
            g.drawLine(50, 140, 35, 210);

        }


        else if (wrong==8){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);

            //draw right leg
            g.drawLine(50, 140, 65, 210);

            //draw left arm
            g.drawLine(50, 70, 30, 100);

            //draw left leg
            g.drawLine(50, 140, 35, 210);

            //draw eyes
            g.setColor(Color.BLACK);
            g.fillOval(40, 38, 5, 5);
            g.fillOval(52, 38, 5, 5);

        }

        else if (wrong==9){
            //draw the gallow
            g.drawLine(50, 30, 50, 10);
            g.drawLine(50, 10, 130, 10);
            g.drawLine(130, 10, 130, 300);
            g.drawLine(20, 300, 150, 300);

            //draw the head
            g.setColor(Color.YELLOW);
            g.fillOval(30, 30, 40, 40);

            //draw body
            g.setColor(Color.BLUE);
            g.drawLine(50, 70, 50, 140);

            //draw right arm
            g.drawLine(50, 70, 70, 100);

            //draw right leg
            g.drawLine(50, 140, 65, 210);

            //draw left arm
            g.drawLine(50, 70, 30, 100);

            //draw left leg
            g.drawLine(50, 140, 35, 210);

            //draw eyes
            g.setColor(Color.BLACK);
            g.fillOval(40, 38, 5, 5);
            g.fillOval(52, 38, 5, 5);

            //draw frown
            g.fillOval(40, 50, 20, 10);
            g.setColor(Color.YELLOW);
            g.fillRect(40, 55, 20, 5);
            g.fillOval(40, 52, 20, 10);

        }








    }
}

