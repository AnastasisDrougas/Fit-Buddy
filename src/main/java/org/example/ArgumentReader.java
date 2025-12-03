package org.example;

import java.util.ArrayList;

public class ArgumentReader {
    ArrayList<String> fileList = new ArrayList<>();
    ArrayList<String> specificsList = new ArrayList<>();

    public void seperator(String[] args){
        for(int i=0; i< args.length; i++){
            if(args[i].equals("-w") || args[i].equals("-s") || args[i].equals("-a")){
                specificsList.add(args[i]);
                specificsList.add(args[i+1]);
            } else {
                fileList.add(args[i]);
            }
        }
        return;
    }

    public void fileNamesList(){
        XMLFileReader xml = new XMLFileReader();
        for(String value : fileList){
            xml.fileReader(value);
        }
        return;
    }

    //sex, age, weight
    public void specifics(){
        //TODO
    }

}
