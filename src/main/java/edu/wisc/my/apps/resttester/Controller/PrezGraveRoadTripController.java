package edu.wisc.my.apps.resttester.Controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import org.json.JSONObject;

public class PrezGraveRoadTripController{
    public PrezGraveRoadTripController(JSONObject jsonObject){
        String start;
        Distance[] distances;
        ObjectMapper mapper = new ObjectMapper();
        Roadtrip roadtrip = new Roadtrip();
        Atlas at = new Atlas();
try{
        roadtrip = mapper.readValue(jsonObject.toString(), Roadtrip.class);
} catch (Exception e){
      System.out.println(e.getMessage());
}
       
        for(Distance d: roadtrip.getDistances()){
            at.canAdd(d);
        }
        TripList tl = new TripList(at.getKeys(), at);
        tl.setStartingPoint("GF");
        tl.makeNearestNeighborList();
        TripLog tripLog = new TripLog(tl.getDistances(), "GF");
        System.out.println(tripLog.toString());
       
        Iterator it = at.getKeys().iterator();
        while(it.hasNext()){
         String item = (String) it.next();  
          at.setStartingPoint(item);
          at.getNearestNeighbor(item);
          at.getFarthestNeighbor(item);
        }
    }
}