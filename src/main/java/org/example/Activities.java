package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

public class Activities {
    private ArrayList<Activity> activities ;

    /**
     * Constructor: Converts a NodeList of activity nodes
     * into an ArrayList of Activity objects.
     *
     * The anonymous function tells the converter
     * how to transform each node into an Activity
     * using the Activity constructor.
     */

    public Activities() {
        this.activities = new ArrayList<>();
    }

    public Activities(NodeList nodes) {
        ArrayListConverter<Activity> converter =
            new ArrayListConverter<>(
                nodes,
                new Function<Node, Activity>() {    //anonymous function
                    @Override
                    public Activity apply(Node node) {
                        return new Activity(node);
                    }
                });
        activities = converter.getList();
    }

    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> list = new ArrayList<Activity>();
        for(Activity e : activities){
            list.add(e);
        }
        return list;
    }
}




