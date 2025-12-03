package org.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileInputStream;

public class XMLFileReader {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public XMLFileReader() {}

    public void fileReader(String filename){
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new FileInputStream(filename));
            NodeList activityList = doc.getElementsByTagName("Activity");
            for (int i = 0; i < activityList.getLength(); i++) {
                Element activityElement = (Element) activityList.item(i);
                String sport = activityElement.getAttribute("Sport");
                System.out.println("Sport: " + sport);
                NodeList trackPoints = activityElement.getElementsByTagName("Trackpoint");
                for (int j = 0; j < trackPoints.getLength(); j++) {
                    Element e = (Element) trackPoints.item(j);
                    String distance = getNodeValue(e.getElementsByTagName("DistanceMeters"));
                    System.out.println("Distance: " + distance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static String getNodeValue(NodeList n) {
        return n.item(0).getChildNodes().item(0).getNodeValue();
    }


}
