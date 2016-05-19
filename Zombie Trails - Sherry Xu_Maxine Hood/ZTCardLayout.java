/**
* ZTCardLayout.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This program initiates a card layout design which calls different panels to display the Zombie Trail game.
* 
* Maxine Hood was primarily responsible for it's implementation.
* 
* @author  Maxine Hood 
* @author  Sherry Xu
* @version 1.0
* @since   2016-05-13
*/

import java.awt.*;
import javax.swing.*;

public class ZTCardLayout extends JPanel {
  
  //private vars
  private JPanel cards; 
  private ZTGame game;
  
  //String of class descriptions
  private final String[] NAMES ={"Start Game", "Play Game", "Lose Game", "Win Game"};
  
  /**
 * Constructor creates card layout and adds the first card
 * 
 * @param game used to affect the vars of the overall game so other classes have correct info
 * */
  public ZTCardLayout (ZTGame game) {
    this.game = game;
    
    //Create the "cards".
    JPanel startPanel = new StartPanel(game, this);
    
    cards = new JPanel(new CardLayout());
    cards.add(startPanel, NAMES[0]); //first panel user sees
   
    add(cards);
  }
  
  /**
 * method switches to panel based on description given
 * 
 * @param event name of card description
 * */
  public void itemStateChanged(String event) {
    CardLayout cl = (CardLayout)(cards.getLayout());
    cl.show(cards, event);
}
  
  /**
 * method creates play panel card and switches to it
 * */
  public void setUpGame() {
    JPanel playPanel = new PlayPanel(game, this);
    cards.add(playPanel, NAMES[1]);
    CardLayout cl = (CardLayout)(cards.getLayout());
    cl.show(cards, NAMES[1]);
  }
  
   /**
 * method creates win or lose panel based on given boolean and displays it
 * 
 * @param winLose true if user won, false if loser lost.
 * */
  public void endGame(boolean winLose) {
    CardLayout cl = (CardLayout)(cards.getLayout());
    
    if (winLose) { //won
      JPanel winPanel = new WinPanel(game);
      cards.add(winPanel, NAMES[3]);
      cl.show(cards, NAMES[3]);
      
    } else { //lost
      JPanel diePanel = new DiePanel(game);
      cards.add(diePanel, NAMES[2]);
      cl.show(cards, NAMES[2]);
    }
  }
  
}