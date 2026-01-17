package org.example;

public class VO2max {
    private int age;
    private double rhr;
    private double t;
    private double vo2Max;
    private double cal;
    private String evaluation;

    public VO2max(int age, double rhr, double t) {
        this.age = age;
        this.rhr = rhr;
        this.t = t / 60.0;
        this.cal = 0;
        this.evaluation = "N/A";

        this.vo2Max = 15.3 * (mhr()/rhr);
    }

    public void calculateCalories(double weight){ cal = (vo2Max * weight * t) / 200.0; }

    private int mhr(){ return 220 - age; }

    public void evaluate(String sex){
        if (sex.equals("m")) {
            if (vo2Max > 50) evaluation =  "Superb!";
            else if (vo2Max > 40) evaluation = "Good!";
            else evaluation = "Average!";
        } else {
            if (vo2Max > 44) evaluation = "Superb!";
            else if (vo2Max > 34) evaluation = "Good!";
            else evaluation = "Average!";
        }
    }

    public double getVo2Max() { return vo2Max; }

    public double getCal() { return cal; }

    public String getEvaluation() { return evaluation; }
}
