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
        Distance r = at.getDistance("JFK", "AL");
        System.out.println("HERE>>" + r.toString());
        Iterator it = at.getKeys().iterator();
        while(it.hasNext()){
         String item = (String) it.next();
          System.out.println("START AT " + item);  
          at.setStartingPoint(item);
          at.getNearestNeighbor(item);
          at.getFarthestNeighbor(item);
        }
    }
}