package edu.wisc.my.apps.resttester.Controller;

import java.util.TreeSet;

import java.util.ArrayList;
public class TripList{

private TreeSet<String> placesToVisit;
private Atlas atlas;
private ArrayList scratchpad;

public TripList(TreeSet<String> placesToVisit, Atlas atlas){
    this.placesToVisit = placesToVisit;
    this.atlas = atlas;
    
}
}