package edu.wisc.my.apps.resttester.Controller;

import java.util.TreeSet;
import java.util.Iterator;

import java.util.ArrayList;
public class TripList{

private TreeSet<String> placesToVisit;
private Atlas atlas;
private ArrayList<Distance> scratchpad;
private String startingPoint;

public ArrayList<Distance> getDistances(){
    return this.scratchpad;
}

public TripList(TreeSet<String> placesToVisit, Atlas atlas){
    this.placesToVisit = placesToVisit;
    this.atlas = atlas;
}

public void setStartingPoint(String startingPoint){
   this.startingPoint = startingPoint;
   scratchpad = new ArrayList<Distance>();
}

public void makeNearestNeighborList(){
    
    String from = startingPoint;
    Distance d = atlas.getNearestNeighbor(from);
    String to = d.getOther(startingPoint);  
  
   while(to != null) { 
    System.out.println("from " + from);
    to = d.getOther(from);
    scratchpad.add(d);
    System.out.println(d.toString());
    atlas.popValue(from);
    from = to;
    d = atlas.getNearestNeighbor(from);
   }

   int total = 0;
   for(Distance dist: scratchpad) { 
      if(dist.getPointA() != null){
      System.out.println(dist.toString()); 
      total += dist.getMileage();
      System.out.println(total);
      }
   }
   System.out.println(total);
}


}