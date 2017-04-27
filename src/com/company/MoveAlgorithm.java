package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */

/**
 * Super class for any Physic's Object's move algorithm
 */
public abstract class MoveAlgorithm {

    protected Physics object;

    public abstract void move();

    // called when the special movement is complete so that the object can switch
    // back to a different movement algorithm
    public void finish(){
        object.onFinishMove();
    }
}
