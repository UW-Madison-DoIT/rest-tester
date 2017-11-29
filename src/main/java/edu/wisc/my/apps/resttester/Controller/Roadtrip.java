package edu.wisc.my.apps.resttester.Controller;
public class Roadtrip{
    String name;
    Distance[] distance;

    public String getName(){
        return this.name;
    }

    public Distance[] getDistances(){
        return this.distance;
    }

    public void setDistance(Distance[] distance){
        this.distance = distance;
    }

    public void setName(String name){
        this.name = name;
    }
}