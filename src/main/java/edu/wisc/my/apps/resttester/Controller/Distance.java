
package edu.wisc.my.apps.resttester.Controller;
public class Distance{
    private String key;
    private String pointA;
    private String pointB;
    private String miles;

    public void setKey(String key){
        this.key = key;
        setPoints();
    }
    public void setMiles(String miles){
        this.miles = miles;
    }
    public String getKey(){
        return this.key;
    }
    public String getMiles(){
        return this.miles;
    }

    public int getMileage(){
        Integer i = Integer.valueOf(this.getMiles());
        return i.intValue();
    }

    public String getPointA(){
        return this.pointA;
    }

    public String getPointB(){
        return this.pointB;
    }

    private void setPoints(){
        
        String[] points = this.getKey().split("\\|");
       
        if(points[0].compareToIgnoreCase(points[1]) >0){
            this.pointA = points[1];
            this.pointB = points[0];
        }

        if(points[1].compareToIgnoreCase(points[0]) >0){
            this.pointA = points[0];
            this.pointB = points[1];
        }
    }

    public String toString(){
        return this.getKey() + " : " + this.getMiles();
    }
}