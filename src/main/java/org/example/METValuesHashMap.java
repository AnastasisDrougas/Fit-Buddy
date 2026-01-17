package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

/**
 * A HashMap storing all the MET values for different sports.
 */
public class METValuesHashMap {

    private final Map<String, Double> metValues;

    public METValuesHashMap(){
        Map<String, Double> temp = new HashMap<>();

        //Sports that we extracted from the XML File.
        temp.put("Running",9.8);
        temp.put("Walking",3.5);
        temp.put("Pool Swimming",6.0);
        temp.put("Biking",4.0);

        this.metValues = Map.copyOf(temp);
    }

    public double get(String key){
        /**
        *Everything Sport that is not
        * initialized, ("Other"), gets the default
        * MET value -> 3.5
        */
        return metValues.getOrDefault(key, 3.5);
    }

}
