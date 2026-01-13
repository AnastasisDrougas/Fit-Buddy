package org.example;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

/**
* A calorie calculator implementing two formulas using data extracted from the
* TCX File and personal user info.
*/
public class CalorieCalculator {
    private double calories;

    public CalorieCalculator() {
        this.calories = 0;
    }

    public CalorieCalculator(double m, double weight, double time){
        calories = m * weight * (time/60.0);
    }

    public CalorieCalculator(String  sex, int age, double weight, double time, double hbpm){

        if(sex.equals("f")){
            calories = ((-20.4022 + (0.4472 * hbpm) + (0.1263 * weight) + (0.074 * age) )* time)/4.184;
        } else if (sex.equals("m")) {
            calories = ((-55.0969 + (0.6309 * hbpm) + (0.1966 * weight) + (0.2017 * age) )* time)/4.184;
        } else {
            throw new IllegalArgumentException("Invalid input for sex");
        }
    }

    public double calculateCaloriesForGUI(Activity a, ViewUI view) {
        double weight = Double.parseDouble(view.getCard1().getWeightField().getText());
        int age = Integer.parseInt(view.getCard1().getAgeField().getText());
        String sex = view.getCard1().getSexField();

        double timeMinutes = a.getTotalTime();
        CalorieCalculator calc;

        if (view.getCard2().isHeartRateMethodSelected()) {
            calc = new CalorieCalculator(sex, age, weight, timeMinutes, a.getAvgHeartRate());
        } else {
            METValuesHashMap metMap = new METValuesHashMap();
            double met = metMap.get(a.getSport().trim());
            calc = new CalorieCalculator(met, weight, timeMinutes);
        }
        return calc.getCalories();
    }

    public double getCalories() {
        return calories;
    }
}
