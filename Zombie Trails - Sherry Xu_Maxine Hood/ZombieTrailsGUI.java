/**
* ZombieTrailsGUI.java
* This program is the GUI of the game Zombie Trails created for Wellesley's CS230 end project. 
* It initiates the card layout panel which creates the visual game for the user.
* 
* Game summary: There's been a zombie apocalypse and the user is infected. They have 720 hrs (30 days)
* to get to Oregon where a lab can cure them. There is a set path of cities they go through and 2 random events
* between each city. At the begining of the game they choose their supplies and their choces throughout the 
* game affects the supplies. If a supply hits 0 or below or they are killed by a random event it's game over.
* If they make it to Oregon they win and a score is calculated. 
* 
* Maxine Hood was primarily responsible for it's implementation
* 
* @author  Maxine Hood 
* @author  Sherry Xu
* @version 1.0
* @since   2016-05-13
*/

import javax.swing.*;

public class ZombieTrailsGUI {
  
 /**
 * Main method that creates a GUI with a card layout. 
 *
 * @param  url  an absolute URL giving the base location of the image
 * @param  name the location of the image, relative to the url argument
 * @return      the image at the specified URL
 * @see         Image
 */
  public static void main (String[] args) {
    JFrame frame = new JFrame("Zombie Trails"); //new frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets close
    
    ZTGame game = new ZTGame(); //creates game here so other classes can use it
    
    ZTCardLayout panel = new ZTCardLayout(game);
    frame.getContentPane().add(panel);
   
    frame.pack();
    frame.setVisible(true);
  }
}