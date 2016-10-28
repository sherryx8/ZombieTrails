/**
 * ZTRandomEvents.java
 * A part of the Zombie Trails game created for Wellesley's CS230 end project. 
 * 
 * This class reads from a .txt file to create a priority queue of random events.
 * Two of these events happen between each city and each game has a different order of random events.
 * 
 * Sherry Xu was primarily responsible for it's implementation.
 * 
 * @author  Sherry Xu 
 * @author  Maxine Hood
 * @version 1.0
 * @since   2016-05-13
 */

import java.util.*;
import java.io.*;

public class ZTRandomEvents{
  
  //private instace vars
  private PriorityQueue<ZTEvent> eventQ;
  private int size;
  
   /**
   * constructor reads from file and creates a priority queue of events. 
   * Each time called is a new order.
   * 
   * @param filename name of file
   * @throw FileNotFoundException file not found
   * @throw NumberFormatException error in file
   * @throw ArrayIndexOutOfBoundsException Format Error in input file or event max reached
   * */
  public ZTRandomEvents(String filename){
    eventQ = new PriorityQueue<ZTEvent>();
    try{
      Scanner scan = new Scanner(new File(filename));
      while (scan.hasNext()){ //splits up info and assigns to vars
        String n = scan.nextLine();
        String t = scan.nextLine();
        String ca = scan.nextLine();
        String cb = scan.nextLine();
        String ta = scan.nextLine();
        String tb = scan.nextLine();
        String[] r1 = scan.nextLine().split(",");
        String[] r2 = scan.nextLine().split(",");
        String a = scan.nextLine();
        String b = scan.nextLine();
        ZTEvent e = new ZTEvent(n, t, ca, cb, ta, tb);
        e.setAll(true, r1);
        e.setAll(false, r2); 
        
        if (Integer.parseInt(a)==1){
          e.setInsertA(true); 
        }
        if (Integer.parseInt(b) ==1){
          e.setInsertB(false); 
        }
        
        e.setPriority((int)(Math.random()*10)); //sets a random number for priority var
        e.setImage("Null.jpg"); //null image because only city events have images
        scan.nextLine();
        eventQ.add(e);
        size++;
      }
      scan.close(); //closes scanner
    }
    catch (FileNotFoundException e1){
      System.out.println("File not found");
    }
    catch (NumberFormatException e2) {
      System.out.println("Format Error in input file");
    }
    catch (ArrayIndexOutOfBoundsException e3){
      System.out.println("Format Error in input file or event max reached");
    }
  }
  
  /**
   * method returnsnext random event.
   * 
   * @return next event
   * */
  public ZTEvent nextRand(){
    return eventQ.poll(); 
  }
  
  /**
   * toString calls the ZTEvent toString
   * 
   * @return string, see ZTEvent.java
   * */
  public String toString(){
    return eventQ.toString();
  }
  
  /**
   * main method for internal testing
   * */
  public static void main(String[] args){
    ZTRandomEvents test = new ZTRandomEvents("ZTevents.txt");
    // System.out.println(test);
    //System.out.println(test.nextRand());
  }  
}




