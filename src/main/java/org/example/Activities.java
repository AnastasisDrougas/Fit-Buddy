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
            System.out.println("Average Pace " + activity.getAvgPace() + " min/km");
            if(activity.getMaxPace() == 0 || activity.getMinPace() == 0 ){
                System.out.println("Minimum & Maximum Pace cannot be calculated!");
            }else{
                System.out.println("Maximum pace reached: " +  activity.getMaxPace());
                System.out.println("Minimum pace reached: " +  activity.getMinPace());
            }

            System.out.println("\n");
        }
    }

}




