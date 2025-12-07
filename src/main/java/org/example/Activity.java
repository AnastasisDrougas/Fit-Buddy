package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Activity {
    private ArrayList<Laps> laps = new ArrayList<>();
    private String sport;
    private Node node;

    public Activity(Node node) {
        this.node = node;
        Element activityElement = (Element) node;
        sport = activityElement.getAttribute("Sport");
        ArrayListConverter(activityElement.getElementsByTagName("Laps"));
    }

    private void ArrayListConverter(NodeList nodes){
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Activity activity = new Activity(node);
            activities.add(activity);
        }
    }


}
