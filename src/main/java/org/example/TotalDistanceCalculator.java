package org.example;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

/**
 * An implementation of the calculator interface for Total Distance.
 */
public class TotalDistanceCalculator implements Calculator<Double>{

    @Override
    public Double calculate(Activity activity) {
        Trackpoints lastTrackpoint = null;
        double totalDistance = 0;

        //Get distance from each Lap.
        for(Laps lap : activity.getLaps()){
                totalDistance += lap.getDistanceMeters();
        }

        //each trackpoint has the cumulative distance.
        //Backup: if distance from each lap doesn't exist, get distance from the last trackpoint.
        if(totalDistance == 0){
            for(Laps lap : activity.getLaps()){
                for(Tracks track : lap.getTracks()){
                    for(Trackpoints trackpoints : track.getTrackpoints()){
                        lastTrackpoint = trackpoints;
                    }
                }
            }
            if(lastTrackpoint != null){
                totalDistance = lastTrackpoint.getDistanceMeters();
            } else {
                totalDistance = 0;
            }
        }
        return totalDistance;
    }
}
