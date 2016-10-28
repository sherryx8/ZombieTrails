/**
* PlayPanel.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This program is the second page of the game. The player sees their stats and different events pop
* up which prompt them to make a decision. Their decision affects their stats and can cause them 
* to win or lose the game. Winning or losing directs them to a different panel.
* 
* Maxine Hood was primarily responsible for it's implementation.
* 
* @author  Maxine Hood 
* @author  Sherry Xu
* @version 1.0
* @since   2016-05-13
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayPanel extends JPanel {
  
  //private swing vars
  private JLabel armorL, peopleL, gasL, foodL, ammoL, passengersL, statsL, timeL, choicesL, eventL, statResultL, currentEventL, imgL2;
  private JButton choiceA, choiceB, okButton;
  private JPanel statsPanel, choicesPanel, eventPanel, playPanel, buttonPanel, 
    resultPanel, resultTextPanel, eventTextPanel;
  private JTextArea eventText, resultText;
  private ImageIcon cityImg;
  
  //private instance vars
  private ZTGame game;
  private Car car;
  private ZTEvent events;
  private ZTCardLayout cardLayout;
  
  //dimentions
  private final int MAX_WIDTH = 700;
  private final int MAX_HEIGHT = 600;
  
   /**
 * Constructor creates game panel that displays stats, presents events, event choices, and event results
 * 
 * @param game used to affect the vars of the overall game so other classes have correct info
 * @param cardLayout used to call other panels
 * */
  public PlayPanel(ZTGame game, ZTCardLayout cardLayout) {
    this.game = game;
    this.cardLayout = cardLayout;
    car = game.getCar();  
    events = game.getEvent();
    
    setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
    setLayout (new BoxLayout (this, BoxLayout.X_AXIS)); //sets layout to box, things added vertically
    setBackground(new Color(204,229,255));
    
    //panels to craete design
    statsPanel = new JPanel();
    statsPanel.setLayout (new BoxLayout (statsPanel, BoxLayout.Y_AXIS));
    statsPanel.setBackground(new Color(167,152,139)); //color and size
    statsPanel.setPreferredSize(new Dimension(200, MAX_HEIGHT)); 
    
    playPanel = new JPanel();
    playPanel.setLayout (new BoxLayout (playPanel, BoxLayout.Y_AXIS));
    playPanel.setBackground(new Color(100,229,255)); //color and size
    playPanel.setPreferredSize(new Dimension(500, MAX_HEIGHT)); 
    
    eventPanel = new JPanel();
    eventPanel.setLayout (new BoxLayout (eventPanel, BoxLayout.Y_AXIS));
    eventPanel.setBackground(new Color(161,48,52)); //color and size
    eventPanel.setPreferredSize(new Dimension(500, 450)); 
    
    choicesPanel = new JPanel();
    choicesPanel.setLayout (new BoxLayout (choicesPanel, BoxLayout.Y_AXIS));
    choicesPanel.setBackground(new Color(110,13,37)); //color and size
    choicesPanel.setPreferredSize(new Dimension(500, 150)); 
    
    buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(110,13,37)); //color and size
    buttonPanel.setPreferredSize(new Dimension(500, 150)); 
    
    //creates stats labels
    statsL = new JLabel(car.getName() + "'s stats");
    statsL.setFont(new Font("Serif", Font.BOLD, 18));
    statsL.setAlignmentX(Component.CENTER_ALIGNMENT);
    timeL = new JLabel("Time remaining: " + "\t" + car.getTime());
    timeL.setAlignmentX(Component.CENTER_ALIGNMENT);
    peopleL = new JLabel ("Passengers: " + "\t" + car.getNumPassengers());
    peopleL.setAlignmentX(Component.CENTER_ALIGNMENT);
    armorL = new JLabel ("Armor: " + "\t" + car.getArmor());
    armorL.setAlignmentX(Component.CENTER_ALIGNMENT);
    gasL = new JLabel ("Gas: " + "\t" + car.getGas());
    gasL.setAlignmentX(Component.CENTER_ALIGNMENT);
    foodL = new JLabel ("Food: " + "\t" + car.getFood());
    foodL.setAlignmentX(Component.CENTER_ALIGNMENT);
    ammoL = new JLabel ("Ammo: " + "\t" + car.getAmmo());
    ammoL.setAlignmentX(Component.CENTER_ALIGNMENT);
    currentEventL = new JLabel("Last city reached: " + game.getCurrentCity());
    currentEventL.setAlignmentX(Component.CENTER_ALIGNMENT);
    currentEventL.setFont(new Font("Serif", Font.BOLD, 16));
    
    //create event labels 
    eventL = new JLabel(events.getName());
    eventL.setAlignmentX(Component.CENTER_ALIGNMENT);
    eventL.setForeground(Color.WHITE);
    eventL.setFont(new Font("Serif", Font.BOLD, 22));
    
    //panel to contain event JTextArea
    eventTextPanel = new JPanel();
    eventTextPanel.setLayout (new BoxLayout (eventTextPanel, BoxLayout.X_AXIS));
    eventTextPanel.setBackground(new Color(161,48,52)); //color and size
    eventTextPanel.setPreferredSize(new Dimension(500, 100)); 
    
    eventText = new JTextArea(events.getText());
    eventText.setAlignmentY(Component.CENTER_ALIGNMENT);
    eventText.setLineWrap(true);
    eventText.setWrapStyleWord(true);
    eventText.setOpaque(false);
    eventText.setEditable(false);
    eventText.setForeground(Color.WHITE);
    
    //labels and buttons of events
    choicesL = new JLabel("CHOICES");
    choicesL.setForeground(Color.WHITE);
    choiceA = new JButton(events.getChoiceA());
    choiceA.setAlignmentX(Component.CENTER_ALIGNMENT);
    choiceB = new JButton(events.getChoiceB());
    choiceB.setAlignmentX(Component.CENTER_ALIGNMENT);
    choiceA.addActionListener(new ButtonListener());
    choiceB.addActionListener(new ButtonListener());
    
    //displays names of passengers
    passengersL = new JLabel("Passengers: " + car.getPassengersString());
    passengersL.setForeground(Color.WHITE);
    passengersL.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //result panel is only shown after choice is made. 
    resultPanel = new JPanel();
    resultPanel.setBackground(new Color(161,48,52)); //color and size
    resultPanel.setPreferredSize(new Dimension(500, 500)); 
    resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
    
    //panel to hold result JTextArea
    resultTextPanel = new JPanel();
    resultTextPanel.setBackground(new Color(161,48,52)); //color and size
    resultTextPanel.setPreferredSize(new Dimension(500, 100)); 
    resultTextPanel.setLayout(new BoxLayout(resultTextPanel, BoxLayout.X_AXIS));
    
    resultText = new JTextArea("");
    resultText.setLineWrap(true);
    resultText.setWrapStyleWord(true);
    resultText.setOpaque(false);
    resultText.setEditable(false);
    
    //labels and button for result
    statResultL = new JLabel("");
    statResultL.setAlignmentX(Component.CENTER_ALIGNMENT);
    okButton = new JButton("OK");
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    okButton.addActionListener(new ButtonListener());
    
    //inserts picture
    ImageIcon mapImg = createImageIcon("ZTimages/Map.jpg", "map");
    cityImg = createImageIcon("ZTimages/" + events.getImage(), "city image");
    JLabel imgL = new JLabel();
    imgL2 = new JLabel();
    imgL.setIcon(mapImg);
    imgL.setAlignmentX(Component.CENTER_ALIGNMENT);
    imgL.setAlignmentY(Component.CENTER_ALIGNMENT);
    imgL2.setIcon(cityImg);
    imgL2.setAlignmentX(Component.CENTER_ALIGNMENT);
    imgL2.setAlignmentY(Component.CENTER_ALIGNMENT);
    
    //adds vars to panels
    statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    statsPanel.add(statsL);
    statsPanel.add(Box.createRigidArea(new Dimension (100, 5)));
    statsPanel.add(foodL);
    statsPanel.add(gasL);
    statsPanel.add(peopleL);
    statsPanel.add(armorL);
    statsPanel.add(ammoL);
    statsPanel.add(timeL);
    statsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    statsPanel.add(currentEventL);
    statsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    statsPanel.add(imgL);
    
    eventTextPanel.add(Box.createRigidArea (new Dimension (50, 0)));
    eventTextPanel.add(eventText);
    eventTextPanel.add(Box.createRigidArea (new Dimension (50, 0)));
    eventPanel.add(Box.createRigidArea (new Dimension (0, 10)));
    eventPanel.add(eventL);
    eventPanel.add(Box.createRigidArea (new Dimension (0, 15)));
    eventPanel.add(eventTextPanel);
    eventPanel.add(Box.createRigidArea (new Dimension (0, 10)));
    eventPanel.add(imgL2);
    eventPanel.add(Box.createRigidArea (new Dimension (0, 40)));
    
    buttonPanel.add(choiceA);
    buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
    buttonPanel.add(choiceB);
    choicesPanel.add(choicesL);
    choicesPanel.add(buttonPanel);
    choicesPanel.add(passengersL);
    choicesPanel.add(Box.createRigidArea(new Dimension(500, 40))); 
    
    playPanel.add(eventPanel);
    playPanel.add(choicesPanel);
    resultPanel.add(Box.createRigidArea(new Dimension(500, 250))); 

    resultTextPanel.add(Box.createRigidArea(new Dimension(50, 0)));
    resultTextPanel.add(resultText);
    resultTextPanel.add(Box.createRigidArea(new Dimension(50, 0)));
    
    resultPanel.add(resultTextPanel);
    resultPanel.add(Box.createRigidArea(new Dimension(500, 100)));
    resultPanel.add(statResultL);
    resultPanel.add(Box.createRigidArea(new Dimension(500, 20)));
    resultPanel.add(okButton);
    resultPanel.add(Box.createRigidArea(new Dimension(600, 250)));
    resultPanel.setVisible(false);
    
    add(statsPanel);
    add(playPanel);
    add(resultPanel);
  }
  
  /**
 * Class that listens to buttons
 * */
  private class ButtonListener implements ActionListener {
    
    /**
     * if a button is clicked this is called
 * */
    public void actionPerformed (ActionEvent event) { 
      
      //if the button was choiceA or choiceB
      if (event.getSource() == choiceA || event.getSource() == choiceB) {
        
        if (event.getSource() == choiceA) {
          String n = car.executeEvent(game.getEvent(), true); //executes event in backend code
          resultText.setText(n + game.getEvent().getTextA()); //sets text
          statResultL.setText(game.getEvent().printEffect(true));  
        }
        
        else if (event.getSource() == choiceB) {
          String n = car.executeEvent(game.getEvent(), false); //executes event in backend code
          resultText.setText(n + game.getEvent().getTextB()); //sets text
          statResultL.setText(game.getEvent().printEffect(false));
        }
        
        game.nextEvent(); //sets next game
        playPanel.setVisible(false); //displays results panel
        resultPanel.setVisible(true);
        
      }
      
      //okButton button is pushed
      else { 
        
        //if the game is lost
        if (!game.checkVars()) {
          cardLayout.endGame(false); //calls method in cardLayout.java that displays death panel
        }
        
        //if teh game is won
        else if (game.isGameOver()) {
          cardLayout.endGame(true); //calls method in cardLayout.java that displays win panel
          
        } else {
          resultPanel.setVisible(false); //return to playing screen
          playPanel.setVisible(true);
        }
        
        //updates labels, buttons, and pictures
        peopleL.setText("Passengers: " + car.getNumPassengers());
        gasL.setText("Gas: " + car.getGas());
        ammoL.setText("Ammo: " + car.getAmmo());
        foodL.setText("Food: " + car.getFood());
        armorL.setText("Armor: " + car.getArmor());
        timeL.setText("Time remaining: " + car.getTime());
        currentEventL.setText("Last city reached: " + game.getCurrentCity());
        eventL.setText(game.getEvent().getName());
        imgL2.setIcon(createImageIcon("ZTimages/" + game.getEvent().getImage(), "city image"));
        eventText.setText(game.getEvent().getText());
        choiceA.setText(game.getEvent().getChoiceA());
        choiceB.setText(game.getEvent().getChoiceB());
        passengersL.setText("Passengers: " + car.getPassengersString());
      }
    } 
  }
  
  /**
 * method that is called when picture is inserted
 * 
 * @param path where the picture is loacted
 * @param description String describing photo
 * @return ImageIcon
 * */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = PlayPanel.class.getResource(path); //gets path to picture location
    
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
      
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }  
}