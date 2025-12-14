package org.example;

import java.util.ArrayList;

public class TotalDistanceCalculator implements Calculator<Double>{

    @Override
    public Double calculate(Activity activity) {
        double totalDistance = 0;
        for(Laps lap : activity.getLaps()){
//            for(Tracks tracks : lap.getTracks()){
//                ArrayList<Trackpoints> trackpoints = tracks.getTrackpoints();
//                for(int i = 0; i<trackpoints.size() - 1; i++){
//                    //totalDistance += trackpoints.get(i+1).getDistanceFrom(trackpoints.get(i));
//                }
//            }
            totalDistance += lap.getDistanceMeters();
        }
        return totalDistance;
    }
}
