package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Activities {
    private ArrayList<Activity> activities = new ArrayList<>();
    private Calculator totalDistanceCalculator = new TotalDistanceCalculator();
    private double totalDistance;

    public Activities(NodeList nodes) {
        System.out.println("Activities");
        ArrayListConverter<Activity> converter = new ArrayListConverter<>(nodes, node -> new Activity(node));
        activities = converter.getList();
        System.out.println(test(activities));
        //System.out.println("dick " + activities.size());
        totalDistance = test(activities);
    }

        public double test(ArrayList<Activity> ac){
            double sum = 0;
            for (Activity activity : ac) {
                Double value = (Double) totalDistanceCalculator.calculate(activity);
                if (value != null) {
                    sum += value; // unbox safely
                }

            }
            return sum;
        }
}









