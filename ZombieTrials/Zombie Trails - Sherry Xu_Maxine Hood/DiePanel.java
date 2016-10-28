/**
* DiePanel.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This comes up when a supply got to 0 or below or they were killed by a random event.
* A message displays of how they were killed and a game over message.
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

public class DiePanel extends JPanel {
  
  //private vars
  private ZTGame game;
  
  //private swing vars
  private JLabel deathL, endGameL;
  
  //final dimentions
  private final int MAX_WIDTH = 700;
  private final int MAX_HEIGHT = 600;
  
   /**
 * Constructor creates a panel that displays users cause of death and end game method
 * 
 * @param game used to affect the vars of the overall game so other classes have correct info
 * */
  public DiePanel(ZTGame game) {
    this.game = game;
    
    //sets up panel
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    setBackground(new Color(180, 0, 0)); 
    setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT)); 
    
    //creates labels
    deathL = new JLabel(game.endGameString()); //calls game method to find out cause of death
    deathL.setAlignmentX(Component.CENTER_ALIGNMENT);
    deathL.setForeground(Color.WHITE);
    endGameL = new JLabel("GAME OVER");
    endGameL.setAlignmentX(Component.CENTER_ALIGNMENT);
    endGameL.setFont(new Font("Serif", Font.BOLD, 20)); 
    
    //adds to panel
    add(Box.createRigidArea(new Dimension(MAX_WIDTH, 200)));
    add(deathL);
    add(Box.createRigidArea(new Dimension(MAX_WIDTH, 50)));
    add(endGameL);
  }
  
}