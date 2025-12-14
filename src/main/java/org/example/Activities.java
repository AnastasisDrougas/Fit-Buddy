package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Activities {
    private ArrayList<Activity> activities = new ArrayList<>();

    public Activities(NodeList nodes) {
        System.out.println("Activities");
        ArrayListConverter<Activity> converter = new ArrayListConverter<>(nodes, node -> new Activity(node));
        activities = converter.getList();

        test();
    }

    private void test(){
        for(Activity activity : activities){
            System.out.println("Sport: " + activity.getSport());
            System.out.println("Distance: " + activity.getTotalDistance() + " meters");
            System.out.println("Heart Rate: " + activity.getAvgHeartRate() + " bpm");
            System.out.println("TotalTime: " + activity.getTotalTime() + " mins");
            System.out.println("Average Speed " + activity.getAvgSpeed() + " km/h");

            System.out.println("\n");
        }
    }

}




