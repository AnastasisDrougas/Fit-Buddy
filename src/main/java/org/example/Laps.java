package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Laps {
    ArrayList<Tracks> tracks = new ArrayList<>();
    private double distanceMeters;

    public Laps(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Tracks> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Track"),Tracknode -> new Tracks(Tracknode));
        tracks = converter.getList();
        intiator(activityElement);
        System.out.println("Laps");
    }

    public ArrayList<Tracks> getTracks() {
        return tracks;
    }


    private void intiator(Element element){
        // -> DistanceMeters
        NodeList distList = element.getElementsByTagName("DistanceMeters");
        String distStr = distList.item(0).getTextContent();
        if((distStr != null && !distStr.isEmpty())){
            distanceMeters = Double.parseDouble(distStr);
        }else{ distanceMeters = 0; }
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }
}
