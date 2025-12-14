package org.example;

import java.util.ArrayList;

public class TotalDistanceCalculator implements Calculator<Double>{

    @Override
    public Double calculate(Activity activity) {
        double totalDistance = 0;
        for(Laps lap : activity.getLaps()){

            totalDistance += lap.getDistanceMeters();
        }
        return totalDistance;
    }
}
