package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */

/**
 * Basic move algorithm, meant to be the the movement algorithm when the object has
 * nothing interacting with is and it's just galavanting around happily
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
