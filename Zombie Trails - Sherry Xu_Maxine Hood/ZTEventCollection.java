// Maxine Hood and Sherry Xu
// CS 230 Final Project
// ZTEventCollection.java
// May 3, 2016

import java.util.*;
import java.io.*;
public class ZTEventCollection{
  private ZTEvent[] collection ;
  private int size;
  private final int MAX = 20;
  //constructor reads from file
  
  public ZTEventCollection(String filename){
    collection = new ZTEvent[MAX];
    try{
      Scanner scan = new Scanner(new File(filename));
      while (scan.hasNext()){
        String n = scan.nextLine();
        String t = scan.nextLine();
        String ca = scan.nextLine();
        String cb = scan.nextLine();
        String ta = scan.nextLine();
        String tb = scan.nextLine();
        
        String[] r1 = scan.nextLine().split(",");
        String[] r2 = scan.nextLine().split(",");
        ZTEvent e = new ZTEvent(n, t, ca, cb, ta, tb);
        e.setAll(true, r1);
        e.setAll(false, r2); 
        scan.nextLine();
        collection[size] = e;
        size ++;
      }
      scan.close();
      //String[] effects = parse(,)
      // for (int i = 0; i < 5; i ++)
      //setEffect(i, (int)(string[i]));
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
  
  public ZTEvent findEvent(String name){
    for (int i = 0; i < size; i++){
      if ((collection[i].getName()).equals(name))
        return collection[i];
    }
    return null;
  }
  
  public String toString(){
    String s = "";
      for (int i = 0; i < size; i++){
      s += collection[i]; 
    }
    return s;
  }
  
  public static void main(String[] args){
    ZTEventCollection e = new ZTEventCollection("ZTevents.txt");
    System.out.println(e);
    System.out.println(e.findEvent("House1"));
  }}



