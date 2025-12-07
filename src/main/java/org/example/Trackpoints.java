package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;

public class Trackpoints {
    private LocalDateTime timestamp;
    private double longitude;
    private double latitude;
    private double altitude;
    private int heartrate;
    private int cadence;

    public Trackpoints(Node node) {
        Element trackpointElement = (Element) node;
        initiator(trackpointElement);
        //cadence = Integer.parseInt(TrackpointElement.getAttribute(""));
    }

    private void initiator(Element element){
        if(!element.getAttribute("Time").isEmpty()){
            timestamp = null;
        } else {
            //timestamp = activityElement.getAttribute("timestamp");
            //TODO
        }

        //child node position
        NodeList positionList = element.getElementsByTagName("Position");
        if (positionList.getLength() > 0) {
            Element position = (Element) positionList.item(0);
            double longitude = Double.parseDouble(position.getAttribute("LongitudeDegrees"));
            double latitude  = Double.parseDouble(position.getAttribute("LatitudeDegrees"));
        } else {
            // safe: no Position element
        }

        altitude = Double.parseDouble(element.getAttribute("AltitudeMeters"));

        NodeList heartrateList = element.getElementsByTagName("HeartRateBpm");
        if (heartrateList.getLength() > 0) {
            Element value = (Element) heartrateList.item(0);
            heartrate = Integer.parseInt(value.getAttribute("Value"));
        } else {
            // safe: no Position element
        }

    }
}
