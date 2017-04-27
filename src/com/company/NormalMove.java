package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */
public class NormalMove extends MoveAlgorithm{


    public NormalMove(Physics obj){
        object = obj;
    }

    public void move(){
        object.x += object.velocityX;
        object.y += object.velocityY;
    }
}
