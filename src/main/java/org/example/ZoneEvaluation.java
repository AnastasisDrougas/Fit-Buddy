package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ZoneEvaluation {
    private int age;
    private double weight;
    private Activity activity;

    private final static double []ceff = {0.07, 0.10, 0.13, 0.16, 0.20};
    private final static double []zones = {0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
    private double []zonesBPM = new double[6];
    private double []timestamps = new double[5];


    public ZoneEvaluation(int age, double weight, Activity activity){
        this.age = age;
        this.weight = weight;
        this.activity = activity;

        for (int i=0; i<6; i++){
            this.zonesBPM[i] = zones[i] * mhr();
        }
    }

    public void evaluation() {
        try{
            for (Laps lap : activity.getLaps()) {
                for (Tracks track : lap.getTracks()) {
                    int size = track.getTrackpoints().size();
                    for (int i=0; i<size; i++) {
                        double bpm = track.getTrackpoints().get(i).getHeartRate();
                        if (bpm >= zonesBPM[0] && bpm <= zonesBPM[1]) {
                            timestamps[0] += timeDiff(i, track);
                        } else if (bpm > zonesBPM[1] && bpm <= zonesBPM[2]) {
                            timestamps[1] += timeDiff(i, track);
                        } else if (bpm > zonesBPM[2] && bpm <= zonesBPM[3]) {
                            timestamps[2] += timeDiff(i, track);
                        } else if (bpm > zonesBPM[3] && bpm <= zonesBPM[4]) {
                            timestamps[3] += timeDiff(i, track);
                        } else if (bpm > zonesBPM[4] && bpm <= zonesBPM[5]) {
                            timestamps[4] += timeDiff(i, track);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            //we aproximate the zone evaluation using the avarage data
            //since the activity entered by the user lacks the necesary format (laps/tracks/trackpoints)
            double bpm = activity.getAvgHeartRate();
            if (bpm >= zonesBPM[0] && bpm <= zonesBPM[1]) {
                timestamps[0] += activity.getTotalTime();
            } else if (bpm > zonesBPM[1] && bpm <= zonesBPM[2]) {
                timestamps[1] += activity.getTotalTime();
            } else if (bpm > zonesBPM[2] && bpm <= zonesBPM[3]) {
                timestamps[2] += activity.getTotalTime();
            } else if (bpm > zonesBPM[3] && bpm <= zonesBPM[4]) {
                timestamps[3] += activity.getTotalTime();
            }
        }
    }
    private int mhr(){ return 220 - age; }

    private long timeDiff(int i, Tracks track){
        int size = track.getTrackpoints().size();
        if(i >= size - 1) {
            return 0;
        }

        LocalDateTime start = track.getTrackpoints().get(i).getTimestamp();
        LocalDateTime end = track.getTrackpoints().get(i + 1).getTimestamp();

        if(start == null || end == null){
            return 0;
        }

        Duration duration = Duration.between(start, end);
        return  duration.toMinutes();
    }

    public double calorieCalculator(){
        double cal = 0;
        for (int i = 0; i < 5; i++) {
            cal += timestamps[i] * ceff[i] * weight;
        }

        return cal;
    }

    public double[] getTimestamps() {
        double []array = new double[5];
        for(int i=0; i<5; i++){
            array[i] = timestamps[i];
        }
        return array;
    }
}
