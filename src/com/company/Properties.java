package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */

/* used as a transfer mechanism to send to the properties view */


public class Properties {

    public static final String STIFFNESS = "STIFFNESS";
    public static final String BOUNCEYNESS = "BOUNCEYNESS";

    private Map<String, Double> values = new HashMap<>();
    private ArrayList<String> includedValues = new ArrayList<>();
    private Physics object;


    public Properties(Physics obj){
        object = obj;
    }

    public void add(String s, Double d){
        includedValues.add(s);
        values.put(s,d);
    }

    public Double getValue(String s){
        return values.get(s);
    }

    public ArrayList<String> getAllValues(){
        return includedValues;
    }

    public void updateProperty(String s, Double d){
        values.remove(s);
        values.put(s,d);

        object.update(s,d);
    }



}
