package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Tracks {
    ArrayList<Trackpoints> trackpoints = new ArrayList<>();

    public Tracks(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Trackpoints> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Trackpoint"),Trackpointnode -> new Trackpoints(Trackpointnode));
        trackpoints = converter.getList();
        System.out.println("tracks");
    }

}
