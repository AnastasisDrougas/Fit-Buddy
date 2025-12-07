package org.example;

public class Main {
    //Giga Nigga 4
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
