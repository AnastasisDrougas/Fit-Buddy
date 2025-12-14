package org.example;

public class Main {
    // mvn clean package
    // java -cp target/CoachFitness-1.0-SNAPSHOT.jar org.example.Main walking_activity_1.tcx

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments given.");
        } else {
            ArgumentReader arg = new ArgumentReader();
            arg.separator(args);
            arg.fileNamesList();
        }

    }
}
