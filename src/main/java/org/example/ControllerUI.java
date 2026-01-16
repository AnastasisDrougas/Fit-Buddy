package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerUI {
    private ViewUI view;
    private ArrayList<Activity> activities = new ArrayList<>();
    private XMLFileReader xmlFile = new XMLFileReader();
    private CalorieCalculator calorieCalculatorUI  = new CalorieCalculator();
    private boolean wantsDailyGoal;
    private ArrayList<Double> caloriesList;
    HashMap<String, Double> dailyGoalMap = new HashMap<>();

    public ControllerUI(ViewUI view){
        this.view = view;
        attachListeners();
    }

    private void attachListeners(){
        //Listener for loading .tcx files.
        view.getCard3().getLoadBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });

        view.getCard3().getShowDailyAchivement().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = "--- Daily Goal not provided ---\n\n";
                if(wantsDailyGoal){
                    report = "--- Daily Goal Report ---\n\n";

                    if(!activities.isEmpty()) {
                        DailyGoalAchievementLogic data = new DailyGoalAchievementLogic();
                        data.isDailyGoalAchieved(activities, caloriesList, dailyGoalMap);
                        double goal = Double.parseDouble(view.getCard1().getGoalField().getText());
                        report +="Goal = " + goal + "kcal\n\n";

                        for(HashMap.Entry<String, Double> entry : dailyGoalMap.entrySet()) {
                            String date = entry.getKey(); // From Activity.java
                            double total = entry.getValue();

                            report += "Date: " + date + "\n";
                            report += "Total: " + String.format("%.2f",total) + " kcal\n";

                            if (total >= goal) {
                                report += "Status: Goal Achieved!\n";
                            } else {
                                double remaining = goal - total;
                                report += "Status: " + String.format("%.2f",remaining) + " kcal remaining\n";
                            }
                            report += "--------------------------\n";
                        }
                    } else {
                        report = "No data provided yet!";
                    }

                }


                JTextPane textPane = new JTextPane();
                textPane.setText(report);
                textPane.setEditable(false);
                textPane.setOpaque(false);

                JScrollPane scrollPane = new JScrollPane(textPane);
                scrollPane.setOpaque(false);
                scrollPane.setPreferredSize(new Dimension(100,300));
                scrollPane.setBorder(null);

                JOptionPane.showMessageDialog(null, scrollPane, "Daily Achievements", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        view.getNext1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next1Button();
            }
        });

        view.getNext2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next2Button();
            }
        });

        view.getCard3().getAddActivity().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActivityData();
            }
        });

        view.getCard1().getWeightField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goNext(view.getCard1().getWeightField());
            }
        });

        view.getCard1().getAgeField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goNext(view.getCard1().getAgeField());
            }
        });
        //TODO
    }

    public void addActivityData() {
        JTextField sportField = new JTextField(10);
        JTextField distanceField = new JTextField(5);
        JTextField timeField = new JTextField(5);
        JTextField hrField = new JTextField(5);
        JTextField speedField = new JTextField(5);
        JTextField paceField = new JTextField(5);
        JTextField dateField = new JTextField(5);

        JPanel addActivityPanel = new JPanel();
        addActivityPanel.setLayout(new BoxLayout(addActivityPanel, BoxLayout.Y_AXIS));
        addActivityPanel.add(new JLabel("Sport (e.g., Running):"));
        addActivityPanel.add(sportField);
        addActivityPanel.add(new JLabel("Total Distance (meters):"));
        addActivityPanel.add(distanceField);
        addActivityPanel.add(new JLabel("Total Time (seconds):"));
        addActivityPanel.add(timeField);
        addActivityPanel.add(new JLabel("Avg Heart Rate:"));
        addActivityPanel.add(hrField);
        addActivityPanel.add(new JLabel("Avg Speed (km/h) (Cycling etc):"));
        addActivityPanel.add(speedField);
        addActivityPanel.add(new JLabel("Avg Pace (m/km) (Running etc):"));
        addActivityPanel.add(paceField);
        addActivityPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        addActivityPanel.add(dateField);

        //Open dialog for manual addition of activities.
        int result = JOptionPane.showConfirmDialog(null, addActivityPanel, "Enter Manual Activity Details", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            try{
                String sport = sportField.getText();
                double dist = Double.parseDouble(distanceField.getText());
                double time = Double.parseDouble(timeField.getText());
                int hr = Integer.parseInt(hrField.getText());
                double speed =  Double.parseDouble(speedField.getText());
                double pace = Double.parseDouble(paceField.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateField.getText(), formatter);

                if(sport.isEmpty()){
                    throw new IllegalArgumentException("Please enter the sports field!");
                }
                if(time <= 0 || hr <= 0 || speed < 0 || dist < 0 || ((speed == 0 && dist > 0) || (dist == 0 && speed > 0))){
                    throw new IllegalArgumentException("Please enter valid information!");
                }

                Activity manualActivity = new Activity(sport, dist, time, speed, pace, hr, date);
                activities.add(manualActivity);

                if (caloriesList == null) {
                    caloriesList = new ArrayList<>();
                }
                caloriesList.add(calorieCalculatorUI.calculateCaloriesForGUI(manualActivity, view));
                refreshTable();

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Please enter valid information", "Input Error", JOptionPane.ERROR_MESSAGE);
            }catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Date must be YYYY-MM-DD!", "Date Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        //filter only XML files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TCX Files", "tcx");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);

        int retVal = chooser.showOpenDialog(view);
        if(retVal == JFileChooser.APPROVE_OPTION){
            xmlFile.processXMLFiles(chooser.getSelectedFiles(), activities);
        } else {
            return;
        }
        caloriesList = updateStats();
        view.getCard3().getTableModel().setRowCount(0);
        for(int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);

            String formatedDistance = String.format("%.2f", a.getTotalDistance());
            String formatedSpeed = "N/A";
            String formatedCalories = String.format("%.2f", caloriesList.get(i));
            String formatedTime = String.format("%.2f", a.getTotalTime());
            String formatedHr = String.format("%.2f", a.getAvgHeartRate());
            String formatedAvgPace = "N/A";

            if(!a.getSport().equals("Biking")){
                formatedAvgPace = String.format("%.2f", a.getAvgPace());
            } else {
                formatedSpeed = String.format("%.2f", a.getAvgSpeed());
            }
            Object[] row = {a.getSport(), formatedDistance,formatedTime ,formatedHr ,formatedSpeed,formatedAvgPace,a.getDate(),formatedCalories};
            view.getCard3().getTableModel().addRow(row);
        }
    }

    public void next1Button(){
        try {
            String check = view.getCard1().getGoalField().getText();
            double goal = 0;
            if(!check.isEmpty()){
                goal = Double.parseDouble(view.getCard1().getGoalField().getText());
                wantsDailyGoal = true;
            }else{ wantsDailyGoal = false; }

            double weight = Double.parseDouble(view.getCard1().getWeightField().getText());
            int age = Integer.parseInt(view.getCard1().getAgeField().getText());

            if (!view.getCard1().getMale().isSelected() && !view.getCard1().getFemale().isSelected()) {
                throw new IllegalArgumentException("You must select one option.");
            }
            if( weight <= 0 || age <= 0 || goal < 0){
                throw new IllegalArgumentException("Please enter valid information!");
            }

            view.showFormula();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for all fields.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void next2Button() {
        try {
            if (!view.getCard2().getHrButton().isSelected() && !view.getCard2().getMetButton().isSelected()) {
                throw new IllegalArgumentException("You must select one option.");
            }
            view.showResults();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void goNext(JTextField cur){
        if(cur == view.getCard1().getWeightField()) {
            view.getCard1().getAgeField().requestFocusInWindow();
        } else {
            view.getCard1().getGoalField().requestFocusInWindow();
        }
    }

    private ArrayList<Double> updateStats(){
        //For each Activity:
        ArrayList<Double> activityCalories = new ArrayList<>();
        for (Activity a : activities) {
            activityCalories.add(calorieCalculatorUI.calculateCaloriesForGUI(a, view));
        }
        return activityCalories;
    }

    //Refresh table and add the activity that the user put manually.
    private void refreshTable() {
        view.getCard3().getTableModel().setRowCount(0);
        //For each activity:
        for (Activity a : activities) {
            double calories = calorieCalculatorUI.calculateCaloriesForGUI(a, view);
            String formatedDistance = String.format("%.2f", a.getTotalDistance());
            String formatedSpeed = "N/A";
            String formatedCalories = String.format("%.2f", calories);
            String formatedTime = String.format("%.2f", a.getTotalTime());
            String formatedHr = String.format("%.2f", a.getAvgHeartRate());
            String formatedAvgPace = "N/A";
            if(!a.getSport().equals("Biking")){
                formatedAvgPace = String.format("%.2f", a.getAvgPace());
            } else {
                formatedSpeed = String.format("%.2f", a.getAvgSpeed());
            }
            Object[] row = {a.getSport(), formatedDistance,formatedTime ,formatedHr ,formatedSpeed,formatedAvgPace,a.getDate(),formatedCalories};
            view.getCard3().getTableModel().addRow(row);
        }
    }


}
