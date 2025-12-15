package org.example;

public class MaxPaceCalculator implements Calculator<Double> {
    @Override
    public Double calculate(Activity activity) {
        double max = Double.MIN_VALUE; //unreachable value
        for (Laps lap : activity.getLaps()) {
            for (Tracks tracks : lap.getTracks()) {
                for (Trackpoints trackpoints : tracks.getTrackpoints()) {
                    double speed = trackpoints.getSpeed();
                    if (speed > 0.1) {
                        double pace = 60 / speed;
                        if (pace > max) {
                            max = pace;
                        }
                    }
                }
            }
        }
        return max == Double.MIN_VALUE ? 0 : max;
    }
}