/**
 * Car.java
 * A part of the Zombie Trails game created for Wellesley's CS230 end project. 
 * 
 * This class creates a car object which holds the supply and passenger variables. 
 * It executes events (updating variables and adding/removing passengers).
 * 
 * Sherry Xu was primarily responsible for it's implementation.
 * 
 * @author  Sherry Xu 
 * @author  Maxine Hood
 * @version 1.0
 * @since   2016-05-13
 */

public class Car{
  
  // instance variables
  private String name;
  private int gas; // from 0 to 100
  private int armor; //from 0 to 100
  private int food; // from 0 to 100
  private int ammo; //from 0 to 100
  private String[] passengers = {"yourname", "Takis", "Christine", "Stella", "Sohie", "Lyn", "Eni", "Shravanna","Maxery","Sherrine"};
  private int numPassengers;
  private final int MAXPASSENGERS = 10;
  private int time;
  private double weight;
  
  /**
   * Constructor sets variables based on params
   * 
   * @param n name of user
   * @param g amount of gas
   * @param a amount of armor
   * @param m amount of ammo
   * @param f amount of food
   * */
  public Car(String n, int g, int a, int m, int f){
    name = n;
    gas = g;
    armor = a;
    ammo = m;
    food = f;
    time = 720;
    passengers[0] = name;
    numPassengers = 1;  
  }
  
  // Getters and setters for each instance variable (when appropriate)
  
  public String getName(){
    return name;
  }
  
  public int getGas(){
    return gas;
  }
  
  public int getArmor(){
    return armor;
  }
  
  public int getFood(){
    return food;
  }
  
  public String[] getPassengers(){
    return passengers;
  }
  
  public double getWeight(){
    return weight;
  }
  
  public int getNumPassengers() {
    return numPassengers; 
  }
  
  public int getAmmo() {
    return ammo; 
  }
  
  public int getTime() {
    return time; 
  }
  
  public void setName(String n) {
    name = n; 
    passengers[0] = n;
  }
  
  public void setGas(int g){
    gas = g;
  }
  
  public void setArmor(int a){
    armor = a;
  }
  
  public void setAmmo(int m){
    ammo = m; 
  }
  
  public void setFood(int f){
    food = f;
  }
  
  public void setNumPassengers(int n) {
    numPassengers = n; 
  }
  
  /**
   * Easier way to set all the vars\
   * 
   * @param g amount of gas
   * @param f amount of food
   * @param a amount of armor
   * @param m amount of ammo
   * */
  public void setAll(int g, int f, int a, int m) {
    gas = g;
    food = f;
    armor = a;
    ammo = m;
  }
  
  /**
   * Adds param amounts to the current vars amounts
   * 
   * @param g amount of gas added
   * @param f amount of food added
   * @param a ammount of armor added
   * @param m ammount of ammo added
   * */
  public void addAll(int g, int f, int a, int m) {
    gas += g;
    food += f;
    armor += a;
    ammo += m;
  }
  
  /**
   * Calculates supply points based on gas, food, and ammo amounts
   * 
   * @return int of total supply points
   * */
  public int getSupplyPoints(){
    return gas + food + ammo; 
  }
  
  /**
   * Returns a string listing the passengers in a user friendly way.
   * 
   * @return String of current passengers
   * */
  public String getPassengersString() {
    String result = "";
    for (int i = 0; i < numPassengers - 1; i++) {
      result += passengers[i] + ", ";
    }
    result += passengers[numPassengers-1];
    return result;
  }
  
  /**
   * Calculates total points a user has at the end of the game based on vars left
   * 
   * @return int of total points based off vars ammounts
   * */
  public int calculatePoints() {
    return ((numPassengers *20) + (gas * 5) +(food* 5) + (ammo *5) + (armor * 5) + time); 
  }
  
  /**
   * Sets weight that is used to multiply with time.
   * Some double between 0 to 2, where 1 is an average weight
   * */
  public void updateWeight(){
    double n = gas*5 + armor*10 + ammo*2 +(numPassengers)*50 + food;
    weight = n/500.0;
  }
  
  /**
   * Method adds a passenger by updating numPassenger var, expanding access to list of names.
   * Also calls helper fcn to update weight of car
   * */
  public void addPassenger(){
    if (numPassengers == MAXPASSENGERS)
      System.out.println("Car is full!");
    else{
      numPassengers ++;
    }
    updateWeight();
  }
  
  /**
   * takes passenger's name and removes that pasenger from passengers array
   * 
   * @param String name of passenger that is being removed
   * */
  public void removePassenger(String name){
    for (int i = 0; i < numPassengers; i++){ //goes through passengers array
      if (passengers[i].equals(name)){ //if the names are the same remove it
        numPassengers--;
        if (numPassengers > 0){
          passengers[i] = passengers[i+1];
          
          //shift everything down
          for (int j = i; j < numPassengers; j++){
            passengers[j] = passengers[j+1];
          }
        }
        
        else {
          passengers [i] = null;
        }
        return; //early exit
      }
    }
    System.out.println("Passenger not found");
  }
  
  /**
   * Moves passenger to end of array and decreases numPassengers
   * This is so you dont get passenger with the same name again.
   * 
   * @param int index of passenger being removed
   * */
  public void removePassenger(int index){
    // if you are only passenger left.
    if (index == 0){
      numPassengers--;
    }
    else if (index < numPassengers){
      numPassengers --;
      //shift
      String removed = passengers[index];
      for (int i = index; i < passengers.length-1; i++){
        passengers[i] = passengers[i+1];
      }
      // add to end
      passengers[passengers.length-1] = removed;
    }
    else
      System.out.println("Passenger not found");    
  }
  
  
  /**
   * returns a String of a random passenger that is not the player(unless only player left)
   * 
   * @return String name of random passenger. If user is the only one it is the user, else it is not
   * */
  public String getRandom(){
    // if no passengers left besides self, return self
    if (numPassengers == 1)
      return passengers[0];
    //else get random passenger that is not self.
    else{
      int num = (int)(Math.random()*(numPassengers-1)+1);
      return passengers[num];
    }
  }
  
  /**
   * removes a random passenger that is not the player(unless only player left)
   * 
   * @return String name of random passenger. If user is the only one it is the user, else it is not
   * */
  public String removeRandom() {
    // if no passengers left besides self, remove self
    if (numPassengers == 1){
      removePassenger(0);
      return "You";
    }
    // else get random passenger that is not self.
    else{
      int num = (int)(Math.random()*(numPassengers-1)+1);
      String s = passengers[num];
      removePassenger(num); //calls helper fcn
      return s;
    }
  }
  
  /**
   * executes an event, meaning it updates the cars variables based on choice given in params.
   * 
   * @param event which event this is regarding
   * @param choice boolean of whichever choice. A is true, B is false
   * @return String name of passenger removed. If no passenger was removed it is an empty string.
   * */
  public String executeEvent(ZTEvent event, boolean choice){
    int[] effect;
    String n = "";
    
    if (choice)
      effect = event.getEffectA();
    else
      effect = event.getEffectB();
    //updates vars
    gas += effect[0];
    armor += effect[1];
    ammo += effect[2];
    food += effect[3];
    if (effect[4] == -1)
      n = removeRandom();
    if (effect[4] == 1){
      addPassenger();
      n = passengers[numPassengers];
    }
    //set subtractions for each event
    food -= numPassengers;
    gas -= 5;
    time += weight * effect[5] + 10;
    updateWeight(); //calls fcn to update weight
    return n;
  }
  
  /**
   *toString for testing and for running game outside of GUI
   * 
   * @return String report of vars
   * */
  public String toString(){
    String s = "";
    for (int i=0; i < numPassengers-1; i++){ //goes through passengers and adds name
      s += passengers[i] + ", ";
    }
    s += passengers[numPassengers-1];
    
    return name + //adds vars to list
      "\n gas: " + gas + " armor: " + armor + " ammo: " + ammo + 
      " food: " + food + "\n passengers: " + s + "\n time remaining: " + time;
  }
  
  /**
   * main method for testing
   * */
  public static void main(String[] args){
    Car c = new Car("Xu", 64, 6, 39, 10);
    c.addPassenger();
    c.addPassenger();
    c.addPassenger();
    System.out.println(c);
    c.removeRandom();
    System.out.println(c);
    c.addPassenger();
    System.out.println(c);
    for (int i = 0; i < c.passengers.length; i ++){
      System.out.print(c.getPassengers()[i]);
    }
    
  }
}
