package org.example;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

/**
 * This program extracts data from a TCX file.
 * It uses multiple ArrayLists that are managed hierarchically.
 * Calculates and prints the user's statistics.
 * Calculates and prints the calories burned using the extracted data and personal user info.
 */

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            //GUI Implementation.
            ViewUI view = new ViewUI();
            new ControllerUI(view);
            view.setVisible(true);
            System.out.println("GUI Window Successfully opened...");
        } else {
            //CLI Implementation.
            ArgumentReader arg = new ArgumentReader();
            arg.separator(args);
            arg.fileNamesList();
            //Print user's stats on the Console
            ConsoleOutputStream printer = new ConsoleOutputStream(arg.getSpecificsList(), arg.getActivities());
        }
    }
}
