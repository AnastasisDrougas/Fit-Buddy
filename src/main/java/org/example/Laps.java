package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Laps {
    ArrayList<Tracks> tracks = new ArrayList<>();

    public Laps(Node node) {
        Element activityElement = (Element) node;
        System.out.println("Laps");
        ArrayListConverter<Tracks> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Track"),Tracknode -> new Tracks(Tracknode));
        tracks = converter.getList();

    }

}
