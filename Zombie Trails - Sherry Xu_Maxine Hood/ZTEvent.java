/**
 * ZTEvent.java
 * A part of the Zombie Trails game created for Wellesley's CS230 end project. 
 * 
 * This creates an event based on information given in it's constructor. 
 * 
 * Sherry Xu was primarily responsible for it's implementation.
 * 
 * @author  Sherry Xu 
 * @author  Maxine Hood
 * @version 1.0
 * @since   2016-05-13
 */

public class ZTEvent implements Comparable<ZTEvent>{
  
  //private vars
  private String name;
  private String text;
  private String choiceA;
  private String choiceB;
  private String textA;
  private String textB;
  private boolean insertA, insertB;
  private String image;
  
  private String[] STATS = {"Gas", "Armor", "Ammo", "Food", "Passengers", "Time"};
  // [gas, armor, ammo, food, passengers, time]
  private int[] effectsA = {0,0,0,0,0,0};
  private int[] effectsB = {0,0,0,0,0,0};    
  private int priority;
  
  /**
   * Constructor sets vars based on inserted vars
   * 
   * @param n name of event
   * @param txt text of info of event
   * @param a string of choice A
   * @param b string of choice B
   * @param txtA string of result of choice A
   * @param txtB string of result of choice B
   * */
  public ZTEvent(String n, String txt, String a, String b, String txtA, String txtB) {
    name = n;
    text = txt;
    choiceA = a;
    choiceB = b;
    textA = txtA;
    textB = txtB;
    priority = 0;
    insertA = false;
    insertB = false;
  }
  
  // Getters and setters for each instance variable (when appropriate)
  
  public void setPriority(int i){
    priority = i; 
  }
  
  public void setInsertA(boolean t){
    insertA = t;
  }
  
  public void setInsertB(boolean t){
    insertB = t;
  }
  
  public void setImage(String img){
   image = img; 
  }
  
  public int getPriority(){
    return priority; 
  }
  
  public String getName(){
    return name;
  }
  
  public String getText() {
    return text; 
  }
  
  public String getChoiceA() {
    return choiceA;
  }
  
  public String getChoiceB() {
    return choiceB; 
  }
  
  public int[] getEffectA(){
    return effectsA;   
  }
  
  public int[] getEffectB(){
    return effectsB;   
  }
  
  public String getTextA() {
    return textA; 
  }
  
  public String getTextB() {
    return textB; 
  }
  
  public boolean getInsertA() {
    return insertA;
  } 
  
  public boolean getInsertB() {
    return insertB;
  }
  
  public String getImage(){
    return image;
  }
  
  /**
   * Takes a string array and choice and updates the proper effects var (A or B) with it's values
   * 
   * @param choice boolean of choice, true for choice A, false of choice B
   * @param all string array of updates of to vars
   * @throws NumberFormatException error with input file
   * @throws ArrayIndexOutOfBoundsException error with array of ints
   * */
  public void setAll (boolean choice, String[] all) throws ArrayIndexOutOfBoundsException, NumberFormatException {  
    try{
      if (choice) //choice A
        for (int i = 0; i < 6; i++){ //for each string
        effectsA[i] = Integer.parseInt(all[i]);  //changes to int and addes to int array of effects
      } 
      else //choice B
        for (int i = 0; i < 6; i++){
        effectsB[i] = Integer.parseInt(all[i]); 
      }
    }
    
    catch (NumberFormatException e1) {
      System.out.println ("Format error in input file");
      throw e1;
    }
    
    catch (ArrayIndexOutOfBoundsException e2){
      System.out.println ("Format error in input file");
      throw e2;
    }
  }
  
  /**
   * determines rank based on priority var in ZTEvent
   * 
   * @param other ZTEvent's priority vars is compared
   * @return int 0 if the same, -1 if other is bigger, 1 if this is bigger
   * */
  public int compareTo(ZTEvent other){
    return this.priority - other.getPriority();
  }
  
   /**
   * Prints what was subtracted and added for user
   * 
   * @param choice boolean, true for A, false for B
   * @return String 0 if the same, -1 if other is bigger, 1 if this is bigger
   * */
  public String printEffect(boolean choice) {
    int[] results;
    
    if (choice) {
      results = effectsA;
    } else {
      results = effectsB;
    }
    String a = "";
    // [gas, armor, ammo, food, passengers, time]
    for (int i = 0; i < STATS.length; i++){
      // if there is a change in stat
      if (results[i] != 0){
        // add to string with positive or negative sign
        if (results[i] > 0){
          a += STATS[i] + ": +" + results[i] + " ";
        } else {
          a += STATS[i] + ": " + results[i] + " ";
        }
      }
    }
    return a;
  }
  
   /**
   * Prints off var info of event. Includes choices and the other vars
   * 
   * @return String of event var info
   * */
  public String toString(){
    String a = "";
    String b = "";
    for (int i = 0; i < 6; i++){
      a += effectsA[i] + " ";
      b += effectsB[i] + " ";
    }
    return name + "\n" + text + "\n A: " + choiceA + "\n B: " + choiceB
      + "\nA: " + textA + "\t" + a + "\nB: " + textB + "\t" + b 
      + "\nPriority: " + priority + "\n"; 
  }
  
   /**
   * Main method for internal testing
   * */
  public static void main(String[] args){
    ZTEvent event = new ZTEvent("Test","TestText", "ChoiceA", "ChoiceB", "TextA", "TextB");
    System.out.println(event);
    String[] a = {"1","1","1","1","1","1"};
    event.setAll(true, a);
    System.out.println(event);    
  }
}