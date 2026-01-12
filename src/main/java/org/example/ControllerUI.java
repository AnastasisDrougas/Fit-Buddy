package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerUI {
    private ViewUI view;
    private ArrayList<Activity> activities = new ArrayList<>();
    private double calories = 0;

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

        view.getAddActivity().addActionListener(new ActionListener() {
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
        addActivityPanel.add(new JLabel("Avg Speed Rate (km/h):"));
        addActivityPanel.add(speedField);

        //Open dialog for manual addition of activities.
        int result = JOptionPane.showConfirmDialog(null, addActivityPanel, "Enter Manual Activity Details", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            try{
                String sport = sportField.getText();
                double dist = Double.parseDouble(distanceField.getText());
                double time = Double.parseDouble(timeField.getText());
                int hr = Integer.parseInt(hrField.getText());
                double speed =  Double.parseDouble(speedField.getText());

                if(sport.isEmpty()){
                    throw new IllegalArgumentException("Please enter the sports field!");
                }
                if(time <= 0 || hr <= 0 || speed < 0 || dist < 0 || ((speed == 0 && dist > 0) || (dist == 0 && speed > 0))){
                    throw new IllegalArgumentException("Please enter valid information!");
                }
                Activity manualActivity = new Activity(sport, dist, time, speed, hr);
                activities.add(manualActivity);

                //Refresh table, add the new mannually added activity.
                refreshTable();

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Please enter valid information", "Input Error", JOptionPane.ERROR_MESSAGE);
            }catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
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
            processXMLFiles(chooser.getSelectedFiles());
        } else {
            return;
        }

        ArrayList<Double> caloriesList = updateStats();
        view.getCard3().getTableModel().setRowCount(0);
        for(int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);
            Object[] row = {a.getSport(), a.getTotalDistance(), a.getTotalTime(), a.getAvgHeartRate(),a.getAvgSpeed(),caloriesList.get(i)};
            view.getCard3().getTableModel().addRow(row);
        }
    }

    public void next1Button(){
        try {
            double weight = Double.parseDouble(view.getCard1().getWeightField().getText());
            double goal = Double.parseDouble(view.getCard1().getGoalField().getText());
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

    private void processXMLFiles(File[] fileList){
        XMLFileReader xml = new XMLFileReader();
        for (File f : fileList) {
            Activities activityList = xml.fileReader(f.getAbsolutePath());
            activities.addAll(activityList.getActivities());
        }
    }

    private ArrayList<Double> updateStats(){

        double weight = Double.parseDouble(view.getCard1().getWeightField().getText());
        double goal = Double.parseDouble(view.getCard1().getGoalField().getText());
        double totalCalBurned = 0;

        //For each Activity:
        ArrayList<Double> activityCalories = new ArrayList<>();
        for (Activity a : activities) {
            activityCalories.add(calculateCalories(a));
        }
        return activityCalories;
    }


    //Refresh table and add the activity that the user put mannually.
    private void refreshTable() {
        view.getCard3().getTableModel().setRowCount(0);

        //For each activity:
        for (Activity a : activities) {
            double calories = calculateCalories(a);
            Object[] row = {a.getSport(), a.getTotalDistance(), a.getTotalTime(), a.getAvgHeartRate(),a.getAvgSpeed(), calories};
            view.getCard3().getTableModel().addRow(row);
        }
    }

    private double calculateCalories(Activity a) {
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


}
