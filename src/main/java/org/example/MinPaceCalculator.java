package org.example;

public class MinPaceCalculator implements Calculator<Double>{

    @Override
    public Double calculate(Activity activity) {
        double min = Double.MAX_VALUE; //unreachable value
        for (Laps lap : activity.getLaps()) {
            for (Tracks tracks : lap.getTracks()) {
                for (Trackpoints trackpoints : tracks.getTrackpoints()) {
                    double speed = trackpoints.getSpeed();
                    if (speed > 0) {
                        double pace = 60 / speed;
                        if (pace < min) {
                            min = pace;
                        }
                    }
                }
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }
}


