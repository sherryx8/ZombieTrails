/**
* StartPanel.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This creates the start panel of the game, the first thing the user sees.
* It gives instructions, takes in their name, and uses sliders to get their supply number choices.
* A button starts the game, directing the code to PlayPanel.java
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
import javax.swing.event.*;
import java.text.*;

public class StartPanel extends JPanel {
  
  //private instance vars
  private ZTGame game;
  private Car car;
  private ZTCardLayout cardLayout;
  private int supplies;
  private double weight;
  
  //private vars for swing vars
  private JLabel intro1, intro2, intro3, intro4, intro5, weightL, suppliesL, nameL, armorL, peopleL, gasL, foodL, ammoL;
  private JButton submit;
  private JTextField nameText;
  private JSlider armorSlider, peopleSlider, gasSlider, foodSlider, ammoSlider;
  private JPanel introPanel, enterInfoPanel, namePanel, infoS, sliders1, sliders2, sliders3;
  
  //dimentions 
  private final int MAX_WIDTH = 700;
  private final int MAX_HEIGHT = 600;
  
  /**
 * Constructor sets vars and creates first panel by calling helper methods. 
 * 
 * @param game used to affect the vars of the overall game so other classes have correct info
 * @param cardLayout used to call other panels
 * */
  public StartPanel(ZTGame game, ZTCardLayout cardLayout) {
    this.game = game;
    this.cardLayout = cardLayout;
    car = game.getCar();  
    supplies = 0;
    weight = 0;
    
    setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
    setLayout (new BoxLayout (this, BoxLayout.Y_AXIS)); //sets layout to box, things added vertically
    setBackground(new Color(167,152,139));
    
    add(makeIntroPanel());
    add(makeInfoPanel());
  }
  
  /**
 * Method creates instructions and adds them to a panel
 * 
 * @returns a Jpanel that contains instructions
 * */
  private JPanel makeIntroPanel() {
    introPanel = new JPanel(); //returned jpanel
    introPanel.setLayout (new BoxLayout (introPanel, BoxLayout.Y_AXIS));
    introPanel.setBackground(new Color(80,35,45)); //color and size
    introPanel.setPreferredSize(new Dimension(MAX_WIDTH, 100));
    
    //Jlabels with instructions
    intro1 = new JLabel("WELCOME TO ZOMBIE TRAILS");
    intro2 = new JLabel("THERE’S BEEN A ZOMBIE APOCALYPSE AND YOU’VE BEEN BITTEN.");
    intro3 = new JLabel("YOU HAVE 30 DAYS TO GET TO OREGON WHERE THE CURE IS LOCATED BEFORE YOU TURN.");
    intro4 = new JLabel("YOU ARE TRAVELING BY CAR AND CAN ONLY TAKE A CERTAIN AMOUNT OF SUPPLIES AND PEOPLE ON THE 2,000 MILE TRIP.");
    intro5 = new JLabel("CHOOSE WISELY AND GOOD LUCK.");
    intro1.setAlignmentX(Component.CENTER_ALIGNMENT);
    intro1.setFont(new Font("Serif", Font.BOLD, 22)); //sets font and size
    intro1.setForeground(Color.WHITE);
    intro2.setAlignmentX(Component.CENTER_ALIGNMENT);
    intro2.setForeground(Color.WHITE);
    intro3.setAlignmentX(Component.CENTER_ALIGNMENT);
    intro3.setForeground(Color.WHITE);
    intro4.setAlignmentX(Component.CENTER_ALIGNMENT);
    intro4.setForeground(Color.WHITE);
    intro5.setAlignmentX(Component.CENTER_ALIGNMENT);
    intro5.setForeground(Color.WHITE);
    
    introPanel.add(intro1); 
    introPanel.add(intro2);
    introPanel.add(intro3);
    introPanel.add(intro4);
    introPanel.add(intro5);
    introPanel.setVisible(true);
    return introPanel; 
  }
  
  /**
 * Method creates a panel where the user inserts their name and uses sliders to determine their supplies.
 * Submit button takes takes them to the next panel. 
 * 
 * @returns a Jpanel that takes users inputs 
 * */
  private JPanel makeInfoPanel() {
    enterInfoPanel = new JPanel(); //returned jpanel
    enterInfoPanel.setLayout (new BoxLayout (enterInfoPanel, BoxLayout.Y_AXIS));
    enterInfoPanel.setBackground(new Color(167,152,139)); //color and size
    enterInfoPanel.setPreferredSize(new Dimension(MAX_WIDTH, 500));
    
    //prompts user to insert name
    nameL = new JLabel("Enter name"); 
    nameL.setAlignmentX(Component.LEFT_ALIGNMENT);
    nameText = new JTextField(10);
    nameText.setAlignmentX(Component.RIGHT_ALIGNMENT);
    
    suppliesL = new JLabel("Supply points left: 100");
    weightL = new JLabel("Weight of car: " + weight);
    peopleL = new JLabel ("Passengers: 1");
    peopleL.setAlignmentX(Component.LEFT_ALIGNMENT);
    armorL = new JLabel ("Armor: 0");
    armorL.setAlignmentX(Component.LEFT_ALIGNMENT);
    gasL = new JLabel ("Gas: 0");
    gasL.setAlignmentX(Component.LEFT_ALIGNMENT);
    foodL = new JLabel ("Food: 0");
    foodL.setAlignmentX(Component.LEFT_ALIGNMENT);
    ammoL = new JLabel ("Ammo: 0");
    ammoL.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    //sliders for supply amounts
    peopleSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 1);
    peopleSlider.setMajorTickSpacing(1);
    peopleSlider.setPaintTicks(true);
    peopleSlider.setPaintLabels(true);
    peopleSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
    peopleSlider.setBackground(new Color(167,152,139));
    
    armorSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10); //CHANGE THESE BACK TO ZERO *************************************************
    armorSlider.setMajorTickSpacing(10);
    armorSlider.setMinorTickSpacing(5);
    armorSlider.setPaintTicks(true);
    armorSlider.setPaintLabels(true);
    armorSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
    armorSlider.setBackground(new Color(167,152,139));
    
    gasSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    gasSlider.setMajorTickSpacing(10);
    gasSlider.setMinorTickSpacing(5);
    gasSlider.setPaintTicks(true);
    gasSlider.setPaintLabels(true);
    gasSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
    gasSlider.setBackground(new Color(167,152,139));
    
    foodSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    foodSlider.setMajorTickSpacing(10);
    foodSlider.setMinorTickSpacing(5);
    foodSlider.setPaintTicks(true);
    foodSlider.setPaintLabels(true);
    foodSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
    foodSlider.setBackground(new Color(167,152,139));
    
    ammoSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    ammoSlider.setMajorTickSpacing(10);
    ammoSlider.setMinorTickSpacing(5);
    ammoSlider.setPaintTicks(true);
    ammoSlider.setPaintLabels(true);
    ammoSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
    ammoSlider.setBackground(new Color(167,152,139));
    
    SliderListener listener = new SliderListener();
    peopleSlider.addChangeListener(listener);
    armorSlider.addChangeListener(listener);
    gasSlider.addChangeListener(listener);
    foodSlider.addChangeListener(listener);
    ammoSlider.addChangeListener(listener);
    
    //submit button leads to PlayPanel in listener
    submit = new JButton("Start");
    submit.addActionListener(new ButtonListener());
    submit.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    //panels to create design
    namePanel = new JPanel(); //new panel w/ dimension and color
    namePanel.setPreferredSize(new Dimension(MAX_WIDTH,40));
    namePanel.setBackground(new Color(167,152,139));
    
    infoS = new JPanel(); //new panel w/ dimension and color
    infoS.setPreferredSize(new Dimension(MAX_WIDTH,40));
    infoS.setBackground(new Color(167,152,139));
    
    sliders1 = new JPanel(); //new panel w/ dimension and color
    sliders1.setPreferredSize(new Dimension(MAX_WIDTH,90));
    sliders1.setBackground(new Color(167,152,139));
    
    sliders2 = new JPanel(); //new panel w/ dimension and color
    sliders2.setPreferredSize(new Dimension(MAX_WIDTH,90));
    sliders2.setBackground(new Color(167,152,139));
    
    sliders3 = new JPanel(); //new panel w/ dimension and color
    sliders3.setPreferredSize(new Dimension(MAX_WIDTH,90));
    sliders3.setBackground(new Color(167,152,139));
    
    //adds vars to design panels
    namePanel.add(nameL);
    namePanel.add(nameText);
    infoS.add(suppliesL);
    infoS.add(Box.createRigidArea(new Dimension(170, 0)));
    infoS.add(weightL);
    sliders1.add(foodL);
    sliders1.add(foodSlider);
    sliders1.add(Box.createRigidArea(new Dimension(40, 0)));
    sliders1.add(peopleL);
    sliders1.add(peopleSlider);
    sliders2.add(gasL);
    sliders2.add(gasSlider);
    sliders2.add(Box.createRigidArea(new Dimension(40, 0)));
    sliders2.add(armorL);
    sliders2.add(armorSlider);
    sliders3.add(ammoL);
    sliders3.add(ammoSlider);
    sliders3.add(Box.createRigidArea(new Dimension(310, 0)));
    
    //creates finished panel
    enterInfoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    enterInfoPanel.add(namePanel);
    enterInfoPanel.add(infoS);
    enterInfoPanel.add(sliders1);
    enterInfoPanel.add(sliders2);
    enterInfoPanel.add(sliders3);
    enterInfoPanel.add(submit);
    enterInfoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
    return enterInfoPanel;
  }
  
  
  /**
 * Class listens to sliders
 * */
  private class SliderListener implements ChangeListener {
    
    //private instance vars
    private int people, gas, ammo, food, armor;  
    
     
  /**
 * Method responds when a slider is moved.
 * It takes the variable and sets it to the car as well as display the number to the user.
 * It only displays number if they have not used up supply points. If used up nothing changes.
 * */
    public void stateChanged (ChangeEvent event) {
      people = peopleSlider.getValue(); //gets values of sliders
      gas = gasSlider.getValue();
      ammo = ammoSlider.getValue();
      food = foodSlider.getValue();
      armor = armorSlider.getValue();
      supplies = (gas + ammo + food);
      
      //if they still have supply points
      if (supplies <= 100) {
        //sets labels to display values
        peopleL.setText("Passengers: " + people);
        gasL.setText("Gas: " + gas);
        ammoL.setText("Ammo: " + ammo);
        foodL.setText("Food: " + food);
        armorL.setText("Armor: " + armor);
        suppliesL.setText("Supply points left: " + (100 - supplies));
        
        //sets car variables
        car.setNumPassengers(people);
        car.setAll(gas, food, armor, ammo);
        car.updateWeight();
        weight = car.getWeight();
        DecimalFormat df = new DecimalFormat("#.##"); //displays only 2 decimals of weight double
        weightL.setText("Weight of car: " + df.format(weight));
        
      } else {
        suppliesL.setText("Over max supplies"); //diplays this when supply points used up
      }
    }
  }
  
   
  /**
 * Class listens to start button
 * */
  private class ButtonListener implements ActionListener {
     
  /**
 * Method reacts when start button is pushed. If they havn't used more supplies then allowed,
 * it calls a method in cardLayout.java that takes the user to the PlayPanel.
 * */
    public void actionPerformed (ActionEvent event) { 
      
      if (supplies <= 100) {
        String name = nameText.getText();
        car.setName(name);
        
        cardLayout.setUpGame();
      }
    }
    
  }
  
  
}