package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Trackpoints {
    private LocalDateTime timestamp;
    private double longitude;
    private double latitude;
    private double altitude;
    private double distanceMeters;
    private double speed;
    private int heartRate;
    private int cadence;

    public Trackpoints(Node node) {
        Element trackpointElement = (Element) node;
        initiator(trackpointElement);
//        System.out.println("Trackpoint");
//        System.out.println(timestamp);
//        System.out.println(longitude);
//        System.out.println(latitude);
//        System.out.println(altitude);
//        System.out.println(distanceMeters);
//        System.out.println(speed);
//        System.out.println(heartRate);
//        System.out.println(cadence);
//        System.out.println("\n\n\n");

    }

    private void initiator(Element element) {

        //Timestamp
        timestamp = null;
        NodeList timeList = element.getElementsByTagName("Time");
        if (timeList.getLength() > 0){
            String timeStr = timeList.item(0).getTextContent();
            if( !timeStr.isEmpty()){ timestamp = LocalDateTime.ofInstant(Instant.parse(timeStr), ZoneId.systemDefault()); }
        }

        //Position -> longitude ,latitude values.
        NodeList positionList = element.getElementsByTagName("Position");
        if (positionList.getLength() > 0) {
            Element position = (Element) positionList.item(0);

            String lonStr = position.getElementsByTagName("LongitudeDegrees").item(0).getTextContent();
            String latStr = position.getElementsByTagName("LatitudeDegrees").item(0).getTextContent();

            if ((lonStr != null && !lonStr.isEmpty())){
                longitude = Double.parseDouble(lonStr);
            }else{ longitude = 0; }

            if ((latStr != null && !latStr.isEmpty())){
                latitude = Double.parseDouble(latStr);
            }else{ latitude = 0; }

        }else {longitude = 0; latitude = 0;}


        // -> Altitude.
        NodeList altList = element.getElementsByTagName("AltitudeMeters");
        if (altList.getLength() > 0 && altList.item(0) != null) {
            String altStr = altList.item(0).getTextContent();
            if ((altStr != null && !altStr.isEmpty())) {
                altitude = Double.parseDouble(altStr);
            } else {
                altitude = 0;
            }
        } else {
            altitude = 0;
        }

        // -> DistanceMeters
        NodeList distList = element.getElementsByTagName("DistanceMeters");
        String distStr = distList.item(0).getTextContent();
        if((distStr != null && !distStr.isEmpty())){
            distanceMeters = Double.parseDouble(distStr);
        }else{ distanceMeters = 0; }

        //Heart Rate -> heartRate value.
        NodeList heartrateList = element.getElementsByTagName("HeartRateBpm");
        if (heartrateList.getLength() > 0) {
            Element hrElement = (Element) heartrateList.item(0);
            String hrStr = hrElement.getElementsByTagName("Value").item(0).getTextContent();


            if((hrStr != null) && (!hrStr.isEmpty())){
                heartRate = Integer.parseInt(hrStr);
            }else{ heartRate = 0; }
        }else { heartRate = 0; }

        // Extension -> ns3 : TPX -> speed, rundCadance
        NodeList ExtList = element.getElementsByTagName("Extensions");
        if (ExtList.getLength() > 0) {
            Element ns3  = (Element) ExtList.item(0);

            //String speedStr = ns3.getElementsByTagName("ns3:Speed").item(0).getTextContent();
            //String cadenceStr = ns3.getElementsByTagName("ns3:RunCadence").item(0).getTextContent();

            NodeList speedList = ns3.getElementsByTagName("ns3:Speed");
            String speedStr = null;
            if (speedList.getLength() > 0 && speedList.item(0) != null) {
                speedStr = speedList.item(0).getTextContent();
            }

            NodeList cadenceList = ns3.getElementsByTagName("ns3:RunCadence");
            String cadenceStr = null;
            if (cadenceList.getLength() > 0 && cadenceList.item(0) != null) {
                cadenceStr = cadenceList.item(0).getTextContent();
            }

            if ((speedStr != null && !speedStr.isEmpty())) {
                speed = Double.parseDouble(speedStr);
            } else { speed = 0; }

            if ((cadenceStr != null && !cadenceStr.isEmpty())) {
                cadence = Integer.parseInt(cadenceStr);
            } else { cadence = 0; }
        } else {
            speed = 0;
            cadence = 0;
        }

    }

    public int getHeartRate() {
        return heartRate;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public double getSpeed() {
        return speed;
    }
}
