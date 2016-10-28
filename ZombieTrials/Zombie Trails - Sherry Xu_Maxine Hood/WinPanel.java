/**
* WinPanel.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This panel displays when they user has made it Oregon without dieing. A score is calculated based on 
* remaining supplies, people, and time. 
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

public class WinPanel extends JPanel {
  
  //private vars
  private ZTGame game;
  private Car car;
  
  //private swing vars
  private JLabel winL, peoplePoints, timePoints, supplyPoints, totalPoints;
  
  //final dimentions
  private final int MAX_WIDTH = 700;
  private final int MAX_HEIGHT = 600;
  
   /**
 * Constructor creates a panel that calculates users score and displays it.
 * 
 * @param game used to affect the vars of the overall game so other classes have correct info
 * */
  public WinPanel(ZTGame game) {
    this.game = game;
    car = game.getCar();  
    
    //creates set up
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
    setBackground(new Color(248,254,230)); 
    setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT)); 
    
    //creates labels and calls car methods to display points
    winL = new JLabel("Congratualations the lab cured you!");
    winL.setAlignmentX(Component.CENTER_ALIGNMENT);
    winL.setFont(new Font("Serif", Font.BOLD, 20)); 
    peoplePoints = new JLabel("People left: " + car.getNumPassengers());
    peoplePoints.setAlignmentX(Component.CENTER_ALIGNMENT);
    timePoints = new JLabel("Time left: " + car.getTime());
    timePoints.setAlignmentX(Component.CENTER_ALIGNMENT);
    supplyPoints = new JLabel("Supplies left: " + car.getSupplyPoints());
    supplyPoints.setAlignmentX(Component.CENTER_ALIGNMENT); 
    totalPoints = new JLabel("Total Points: " + car.calculatePoints()); 
    totalPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
    totalPoints.setFont(new Font("Serif", Font.BOLD, 20)); 
    
    //adds to panel
    add(Box.createRigidArea(new Dimension(MAX_WIDTH, 100)));
    add(winL);
    add(Box.createRigidArea(new Dimension(MAX_WIDTH, 30)));
    add(timePoints);
    add(peoplePoints);
    add(supplyPoints);
    add(Box.createRigidArea(new Dimension(MAX_WIDTH, 20)));
    add(totalPoints);
  }
  
}