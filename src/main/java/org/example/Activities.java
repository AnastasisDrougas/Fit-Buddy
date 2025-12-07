package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Activities {
    private ArrayList<Activity> activities = new ArrayList<>();

    public Activities(NodeList nodes) {
        System.out.println("Activities");
        ArrayListConverter<Activity> converter = new ArrayListConverter<>(nodes,node -> new Activity(node));
        activities = converter.getList();
    }

}









