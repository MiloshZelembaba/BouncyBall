package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */
public abstract class MoveAlgorithm {

    protected Physics object;

    public abstract void move();
    public void finish(){
        object.onFinishMove();
    }
}
