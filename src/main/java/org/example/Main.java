package org.example;

public class Main {
    //Giga Nigga 4
    static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments given.");
            return;
        } else {
            ArgumentReader arg = new ArgumentReader();
            arg.seperator(args);
        }

    }
}
