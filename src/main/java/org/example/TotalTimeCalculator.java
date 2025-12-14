package org.example;

public class TotalTimeCalculator implements Calculator<Double> {
        @Override
        public Double calculate(Activity activity) {
            double totalTimeInSeconds = 0;
            for(Laps lap : activity.getLaps()){
                totalTimeInSeconds += lap.getTimeInSeconds();
            }
            return totalTimeInSeconds;
        }

}
