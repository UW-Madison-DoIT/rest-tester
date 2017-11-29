package edu.wisc.my.apps.resttester.Controller;

import java.util.Iterator;
 import java.lang.StringBuffer; 
 import java.util.ArrayList;

public class TripLog{

private ArrayList<Distance> distances;
private String startingPoint;
private Integer totalMiles = 0;

public TripLog(ArrayList<Distance> distances, String startingPoint){

    this.startingPoint = startingPoint;
    this.distances = distances;

    for (Iterator<Distance> iterator = distances.iterator(); iterator.hasNext();) {
            
       Distance d = iterator.next();
       if(d.getPointA() != null) {
           try{
               totalMiles += d.getMileage();}
               catch(Exception e){
                   System.out.println(e.getMessage());
               }
       }
    }
}

public String toString(){
   String from = startingPoint;
   String to = null;
   StringBuffer bf = new StringBuffer("");
   for (Iterator<Distance> iterator = distances.iterator(); iterator.hasNext();) {
       Distance d = iterator.next();
       to = d.getOther(from);
       if(to != null) {
           bf.append(from + ",");
           from = to;
       }
   }
   bf.append(from + "," + this.totalMiles);
   return bf.toString();
}

}