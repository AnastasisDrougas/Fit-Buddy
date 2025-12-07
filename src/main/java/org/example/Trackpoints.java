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
        System.out.println("Trackpoint");
        System.out.println(longitude);
        //cadence = Integer.parseInt(TrackpointElement.getAttribute(""));
    }

//    private void initiator(Element element){
//        if(!element.getAttribute("Time").isEmpty()){
//            timestamp = null;
//        } else {
//            //timestamp = activityElement.getAttribute("timestamp");
//            //TODO
//
//        }
//
//        //child node position
//        NodeList positionList = element.getElementsByTagName("Position");
//        if (positionList.getLength() > 0) {
//            Element position = (Element) positionList.item(0);
//            longitude = Double.parseDouble(position.getAttribute("LongitudeDegrees"));
//            latitude  = Double.parseDouble(position.getAttribute("LatitudeDegrees"));
//        } else {
//
//        }
//
//        altitude = Double.parseDouble(element.getAttribute("AltitudeMeters"));
//
//        NodeList heartrateList = element.getElementsByTagName("HeartRateBpm");
//        if (heartrateList.getLength() > 0) {
//            Element value = (Element) heartrateList.item(0);
//            heartrate = Integer.parseInt(value.getAttribute("Value"));
//        } else {
//            // safe: no Position element
//        }
//
//    }

    private void initiator(Element element) {
        // timestamp (just a placeholder)
        timestamp = null;
        String timeAttr = element.getAttribute("Time");
        if (timeAttr != null && !timeAttr.isEmpty()) {
            // TODO: parse timestamp if needed
        }

        // Position
        NodeList positionList = element.getElementsByTagName("Position");
        if (positionList.getLength() > 0) {
            Element position = (Element) positionList.item(0);

            String lonStr = position.getElementsByTagName("LongitudeDegrees").item(0).getTextContent();
            String latStr = position.getElementsByTagName("LatitudeDegrees").item(0).getTextContent();

            longitude = (lonStr != null && !lonStr.isEmpty()) ? Double.parseDouble(lonStr) : 0;
            latitude  = (latStr != null && !latStr.isEmpty()) ? Double.parseDouble(latStr) : 0;
        } else {
            longitude = 0;
            latitude = 0;
        }

        // Altitude
        String altStr = element.getAttribute("AltitudeMeters");
        altitude = (altStr != null && !altStr.isEmpty()) ? Double.parseDouble(altStr) : 0;

        // Heart rate
        NodeList heartrateList = element.getElementsByTagName("HeartRateBpm");
        if (heartrateList.getLength() > 0) {
            Element value = (Element) heartrateList.item(0);
            String hrStr = value.getAttribute("Value");
            heartrate = (hrStr != null && !hrStr.isEmpty()) ? Integer.parseInt(hrStr) : 0;
        } else {
            heartrate = 0;
        }

        // Cadence (optional, safe default)
        cadence = 0;
    }
}
