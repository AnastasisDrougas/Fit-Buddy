package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DailyGoalAchievementLogic {
    private ArrayList<LocalDate> datesList = new ArrayList<>();
    private ArrayList<Double> calorieSums = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void isDailyGoalAchieved(ArrayList<Activity> activityList, ArrayList<Double> calorieList, HashMap<String, Double> map){
        for(int i = 0; i < activityList.size(); i ++){
            LocalDate date = activityList.get(i).getDate();
            String formattedDate = date.format(formatter);
            double currentCalories = calorieList.get(i);

            //If the date already exists in the Map, increase the old values.
            //Else, create new and start at 0.0.
            map.put(formattedDate, map.getOrDefault(formattedDate, 0.0) + currentCalories);
        }

    }

}
