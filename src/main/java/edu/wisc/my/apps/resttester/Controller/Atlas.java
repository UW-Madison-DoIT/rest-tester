package edu.wisc.my.apps.resttester.Controller;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import java.util.TreeSet;

public class Atlas {

    private TreeSet<String> keys = new TreeSet();
    private ArrayList<Distance> distances = new ArrayList();
    private String startingPoint;
    private String other;

    public Atlas(){

    }

    public Atlas(Atlas atlas, String fromHere) {
        ArrayList<Distance> distancesFromHere = new ArrayList();
        this.startingPoint = fromHere;
        for (Distance d: atlas.getDistances()){
            if(d.getPointA().equalsIgnoreCase(fromHere) || 
            d.getPointB().equalsIgnoreCase(fromHere)){
                distancesFromHere.add(d);
                keys.add(d.getPointA());
                keys.add(d.getPointB());
            }
        }

        this.distances = distancesFromHere;
    }

   public TreeSet getKeys(){
       for(String key: this.keys){
           System.out.println(key);
       }
       return this.keys;
   }

   public ArrayList<Distance> getDistances(){
       return this.distances;
   }

   public Distance getDistance(String point1, String point2){
       String pA = null;
       String pB = null;
       if(point1.compareToIgnoreCase(point2) >0){
            pA = point2;
            pB = point1;
        }
   
        if(point1.compareToIgnoreCase(point2) <0){
            pA = point1;
            pB = point2;
        }
        for(Distance d:this.getDistances()){
            if(d.getPointA().equals(pA) && d.getPointB().equals(pB)){
                return d;
            }
        }
        return null;
   }

   public String getOther(Distance d){
       if(d.getPointA().equals(this.getStartingPoint())){
           return d.getPointB();
       }

       if(d.getPointB().equals(this.getStartingPoint())){
           return d.getPointA();
       }

       return d.getKey();
   }

   public String getStartingPoint(){
       return this.startingPoint;
   }

   public void setStartingPoint(String startingPoint){
       this.startingPoint = startingPoint;
   }

   public Distance getFarthestNeighbor(String startingPoint){
       Distance retVal = new Distance();
       Atlas searchMe = new Atlas(this, startingPoint);
       int miles = 0;
       for(Distance d: searchMe.getDistances()){
           if(d.getMileage() > miles){
               miles = d.getMileage();
               retVal = d;
           }
       }
    System.out.println (startingPoint + " is far from " + getOther(retVal) + " " + retVal.getMiles());   
    return retVal;
   }

   public Distance getNearestNeighbor(String startingPoint){
       Distance retVal = new Distance();
       Atlas searchMe = new Atlas(this, startingPoint);
       int miles = 3000;
       for(Distance d: searchMe.getDistances()){
           if(d.getMileage() < miles){
               miles = d.getMileage();
               retVal = d;
           }
       }
    System.out.println (startingPoint + " is near " + getOther(retVal) + " " + retVal.getMiles());   
    return retVal;
   }

    public boolean canAdd(Distance distance){
        boolean addMe = true;
        
        if(StringUtils.isBlank(distance.getPointA())){
           return false;
        }

        for(Distance d: this.distances){
            if ((d.getPointA().equalsIgnoreCase(distance.getPointA())
              && d.getPointB().equalsIgnoreCase(distance.getPointB()))
              ){
                  addMe = false;
                  return false;
              }
        }
        distances.add(distance);
        keys.add(distance.getPointA());
        keys.add(distance.getPointB());
        return addMe;
    }

}