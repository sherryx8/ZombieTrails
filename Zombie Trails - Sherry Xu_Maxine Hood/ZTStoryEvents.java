/**
 * ZTStoryEvents.java
 * A part of the Zombie Trails game created for Wellesley's CS230 end project. 
 * 
 * This class reads from a .txt file to create a linked list of a set order of story events.
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

public class ZTStoryEvents{
  
  //private instace vars
  private LinkedList<ZTEvent> storyline;
  private int size;
  
  /**
   * constructor reads from file and creates a linked list of story events. 
   * Each time called is the same set order
   * 
   * @param filename name of file
   * @throw FileNotFoundException file not found
   * @throw NumberFormatException error in file
   * @throw ArrayIndexOutOfBoundsException Format Error in input file or event max reached
   * */
  public ZTStoryEvents(String filename){
    storyline = new LinkedList<ZTEvent>();
    
    try{
      Scanner scan = new Scanner(new File(filename));
      while (scan.hasNext()){ 
        String n = scan.nextLine(); //breakes String down into vars
        String t = scan.nextLine();
        String ca = scan.nextLine();
        String cb = scan.nextLine();
        String ta = scan.nextLine();
        String tb = scan.nextLine();
        String[] r1 = scan.nextLine().split(",");
        String[] r2 = scan.nextLine().split(",");
        String a = scan.nextLine();
        String b = scan.nextLine();
        String i = scan.nextLine();
        ZTEvent e = new ZTEvent(n, t, ca, cb, ta, tb);
        e.setAll(true, r1);
        e.setAll(false, r2); 
        
        if (Integer.parseInt(a) ==1){
          e.setInsertA(true);
        }
        if (Integer.parseInt(b) ==1){
          e.setInsertB(false); 
        }
        e.setImage(i); //sets image
        scan.nextLine();
        storyline.add(e);
        size ++;
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
   * getter
   * */
   public int getSize(){
     return size;
   }
   
   /**
    * method returns next event in linked list.
    * 
    * @return next event
    * */
   public ZTEvent nextStory(){
     return storyline.remove(); 
   }
   
   /**
    * toString calls the ZTEvent toString
    * 
    * @return string, see ZTEvent.java
    * */
   public String toString(){
     return storyline.toString();
   }
   
   /**
    * main method for internal testing
   * */
  public static void main(String[] args){
    ZTStoryEvents test = new ZTStoryEvents("ZTstory.txt"); 
    System.out.println(test);
  }
}