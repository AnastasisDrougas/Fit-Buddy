package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Activity {

    private ArrayList<Laps> laps = new ArrayList<>();
    private Calculator<Double> totalDistanceCalculator = new TotalDistanceCalculator();
    private Calculator<Double> avgHeartRateBpmCalculator = new AvgHeartRateBpmCalculator();
    private Calculator<Double> totalTimeCalculator = new TotalTimeCalculator();
    private Calculator<Double> maxPaceCalculator = new MaxPaceCalculator();
    private Calculator<Double> minPaceCalculator = new MinPaceCalculator();

    private double totalDistance;

    private double avgHeartRate;

    private double totalTime;

    private double avgSpeed; // km/h.
    private double avgPace;// min/km.

    private double maxPace;
    private double minPace;

    private String sport;

    public Activity(Node node) {
        Element activityElement = (Element) node;
        sport = activityElement.getAttribute("Sport");
        ArrayListConverter<Laps> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Lap"),Lapnode -> new Laps(Lapnode));
        laps = converter.getList();

        initiator();
        System.out.println("Activity");
    }

    private void initiator(){

        avgHeartRate = (double) avgHeartRateBpmCalculator.calculate(this);
        totalDistance = (double) totalDistanceCalculator.calculate(this);
        totalTime = (double) totalTimeCalculator.calculate(this) / 60;

        avgSpeed = (totalDistance / 1000) / (totalTime / 60); // km/h.
        avgPace = totalTime / (totalDistance / 1000) ;// min/km.

        maxPace = (double) maxPaceCalculator.calculate(this);
        minPace = (double) minPaceCalculator.calculate(this);

    }

    public double getAvgHeartRate() {
        return avgHeartRate;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public String getSport() {
        return sport;
    }

    public ArrayList<Laps> getLaps() {
        return laps;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public double getAvgPace() {
        return avgPace;
    }

    public double getMaxPace() {
        return maxPace;
    }

    public double getMinPace() {
        return minPace;
    }
}
