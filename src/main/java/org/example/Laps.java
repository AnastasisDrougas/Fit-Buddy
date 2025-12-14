package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Laps {
    ArrayList<Tracks> tracks = new ArrayList<>();
    private double distanceMeters;
    private double timeInSeconds;

    public Laps(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Tracks> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Track"),Tracknode -> new Tracks(Tracknode));
        tracks = converter.getList();
        initiator(activityElement);
    }

    private void initiator(Element element){
        NodeList distList = element.getElementsByTagName("DistanceMeters");
        String distStr = distList.item(0).getTextContent();
        if((distStr != null && !distStr.isEmpty())){
            distanceMeters = Double.parseDouble(distStr);
        }else{ distanceMeters = 0; }

        NodeList TimeList = element.getElementsByTagName("TotalTimeSeconds");
        String TimeStr = TimeList.item(0).getTextContent();
        if((TimeStr != null && !TimeStr.isEmpty())){
            timeInSeconds= Double.parseDouble(TimeStr);
        }else{ timeInSeconds = 0; }

    }

    public ArrayList<Tracks> getTracks() {
        return tracks;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public double getTimeInSeconds() {
        return timeInSeconds;
    }
}
