/**
* ZTGame.java
* A part of the Zombie Trails game created for Wellesley's CS230 end project. 
* This program is the second page of the game. The player sees their stats and different events pop
* up which prompt them to make a decision. Their decision affects their stats and can cause them 
* to win or lose the game. Winning or losing directs them to a different panel.
* 
* Maxine Hood and Sherry Xu were primarily responsible for it's implementation (both did half). 
* 
* @author  Maxine Hood 
* @author  Sherry Xu
* @version 1.0
* @since   2016-05-13
*/

import java.util.*;

public class ZTGame {
  
  //private instance vars
  private ZTRandomEvents rEvents;
  private ZTStoryEvents sEvents;
  private Car car;
  private ZTEvent currentEvent;
  private int count;
  private boolean isGameOver;
  private String currentCity;
  
   /**
 * Constructor combines pieces of game by calling other classes and setting vars
 * */
  public ZTGame() {
    rEvents = new ZTRandomEvents("ZTevents.txt");
    sEvents = new ZTStoryEvents("ZTstory.txt");
    car = new Car("",0,0,0,0);
    currentEvent = sEvents.nextStory();
    count = 1;
    isGameOver = false;
    currentCity = "Boston";
  }
  
 // Getters and setters for each instance variable (when appropriate)
  
  public Car getCar() {
    return car; 
  }
  
  public String getCurrentCity() {
   return currentCity; 
  }
  
   public ZTEvent getEvent() {
    return currentEvent; 
  }
  
  public void setCarName(String name) {
    car.setName(name);
  }
  
  public void setCurrentCity(String name) {
   currentCity = name; 
  }
  
   /**
 * Method selects the next event based on a count that keeps track of whether the next event
 * should be a city (linked list) or random event (priority queue).
 * Also sets currentCity variable.
 * */
  public void nextEvent() {
    if (!checkVars()){ //if game is over
      isGameOver = true;
      System.out.println("GameOver");
      
    }else if (count == (sEvents.getSize()*3) -2){ //if it's the last city event
      isGameOver = true;
      System.out.println("Congratz! You did it");
      
    }if (!isGameOver){ //if game is not over
      if (count%3 == 0) { //if it should be a city event
        currentEvent = sEvents.nextStory(); //sets city event, count, and current city
        count ++;
        currentCity = currentEvent.getName();
        
      }else { //sets next random event from queue
        currentEvent = rEvents.nextRand();
        count++;
      }
    }
  }
  
  
   /**
 * Returns a boolean based on whether the user has used too many supply points (max 100)
 * 
 * @param g gas amount
 * @param f food amount
 * @param m ammo amount
 * @returns true if the user still has points, false if they're over
 * */
  public boolean checkSupplyPoints(int g, int f, int m){
    return ((g + f + m) <= 100); 
  }
  
  
   /**
 * Sets all the starting vars based on parameters.
 * calls helper functions to add passengers to car
 * 
 * @param g gas amount
 * @param f food amount
 * @param m ammo amount
 * @param a armor amount
 * @param p passengers amount
 * */
  public void setStarting(int g, int f, int a, int m, int p){
    if (checkSupplyPoints(g, f, m)){ //if there's enough supply points
      car.setAll(g, f, a, m); //helper method sets vars
      for (int i = 0; i< p; i++){
        car.addPassenger(); //helper method adds passenger
      }
      car.updateWeight(); //helper method updates weight variable
    }
    else{
      System.out.println(" insufficient supply points");
    }
  }
  
  
   /**
 * while loop runs the game through the console. Not used in the GUI. Used for backend testing or playing
 * the game without the GUI. 
 * */
  public void runGame(){
    Scanner s = new Scanner(System.in);
    
    while (!isGameOver) { //while the game isn't over
      System.out.println(currentEvent.getName()); //reports info of current event
      System.out.println(currentEvent.getText());
      System.out.println("\t A: " + currentEvent.getChoiceA());
      System.out.println("\t B: " + currentEvent.getChoiceB());
      String answer = s.nextLine();
      
      if ((answer.toLowerCase()).equals("a")){ //users choice
        car.executeEvent(currentEvent, true);
        System.out.println(currentEvent.printEffect(true)); //reports effect
        System.out.println(currentEvent.getTextA());
        System.out.println("\n CURRENT STATS: " + car + "\n");
      }
      
      else if ((answer.toLowerCase()).equals("b")){ //users choice
        car.executeEvent(currentEvent, false);
        System.out.println(currentEvent.printEffect(false)); //reports effect
        System.out.println(currentEvent.getTextB());
        System.out.println("\n CURRENT STATS: " + car + "\n");
      }
      nextEvent(); //calls next event
    }
    if (!checkVars()){ //if a supply is too low, ends game
      System.out.println(endGameString());
    }
    else{ //user won
      System.out.println("YOU WON!");
      System.out.println("endstats:" + car);
    }
    s.close(); //closes scanner
  }
  
  
   /**
 *Checks if game is over
 * 
 * @return boolean of var
 * */
  public boolean isGameOver(){
    return isGameOver;
  }
  
  /**
 * Checks if any supplies are or below 0. 
 * 
 * @return boolean, false if game is over, true if user can continue playing
 * */
  public boolean checkVars() {
    if (!(car.getGas() <= 0) && !(car.getAmmo() <=0) && !(car.getFood() <= 0) && !(car.getNumPassengers() <= 0) && !(car.getArmor() <= 0) && !(car.getTime() <= 0))
      return true; 
    return false;
  }
  
  /**
 * Checks supplies amount and based on whichever the first one is 0 or below reports cause of death. 
 * If the user was killed by a random event a generick statement is made.
 * 
 * @return String report of what killed player
 * */
  public String endGameString() {
    if (car.getTime() <= 0){
      return ("You ran out of time and turned into a zombie.");
    } else if (car.getNumPassengers() <= 0) {
      return ("You died. Ouch.");
    } else if (car.getAmmo() <= 0) {
      return ("You ran out of ammo and were unable to defend yourself from a pack of zombies. Ouch."); 
    }else if (car.getFood() <= 0) {
      return ("You died from starvation.");   
    } else if (car.getArmor() <= 0) {
      return ("Your car broke down and a pack of zombies came and ate you. Ouch.");
    } else if (car.getGas() <= 0) {
      return ("You ran out of gas and a pack of zombies came and ate you. Ouch.");
    } else if (car.getTime() <= 0) {
      return ("Your time is up and you turn into a zombie, forever walking the earth a corpse.");
    }else {
      return ("You died."); 
    }
  }
  
  /**
 * Main method for testing
 * */
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    ZTGame test = new ZTGame();
    System.out.println("Enter your name");
    test.setCarName(scan.nextLine());
    int gas, food, armor, ammo, pass;
    scan.close();
    do{
      System.out.println("Please enter starting stats (num)");
      System.out.println("Gas");
      gas = Integer.valueOf(scan.nextLine());
      System.out.println("Food");
      food = Integer.valueOf(scan.nextLine());
      System.out.println("Armor");
      armor = Integer.valueOf(scan.nextLine());
      System.out.println("Ammo");
      ammo = Integer.valueOf(scan.nextLine());
      System.out.println("Number of Passengers");
      pass = Integer.valueOf(scan.nextLine());
      test.setStarting(food, gas, armor, ammo, pass);
    }
    while(!test.checkSupplyPoints(gas, food, ammo));
    test.runGame();
  }
}