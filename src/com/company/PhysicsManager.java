package com.company;

import java.util.ArrayList;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */
public class PhysicsManager {

    private ArrayList<Physics> objects = new ArrayList<>();

    public ArrayList<Physics> getObjects(){
        return objects;
    }

    public void add(Physics obj){
        objects.add(obj);
    }

    public void doGravity(){ /* adds gravity to all objects velocityY's */
        for (Physics obj : objects){
            obj.addGravity();
        }
    }

    public void doMove(){
        for (Physics obj : objects){
            obj.move();
        }
    }

    public void manage(){
        doGravity(); // add gravity
        doMove(); // move all objects
    }


}
