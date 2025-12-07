package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Activity {
    private ArrayList<Laps> laps = new ArrayList<>();
    private String sport;

    public Activity(Node node) {
        Element activityElement = (Element) node;
        sport = activityElement.getAttribute("Sport");
        ArrayListConverter<Laps> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Lap"),Lapnode -> new Laps(Lapnode));
        laps = converter.getList();
        System.out.println("Activity");
        System.out.println(sport);
    }

}
