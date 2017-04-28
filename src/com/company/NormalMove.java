package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */

/**
 * Basic move algorithm, meant to be the the movement algorithm when the object has
 * nothing interacting with is and it's just galavanting around happily
 */
public class NormalMove extends MoveAlgorithm{

    private double horizontalResistance = 0.015;


    public NormalMove(Physics obj){
        object = obj;
    }

    public void move(){
        object.x += object.velocityX;
        object.y += object.velocityY;


        if (object.velocityX > 0 && object.velocityX - horizontalResistance < 0){
            object.velocityX = 0;
        } else if (object.velocityX < 0 && object.velocityX + horizontalResistance > 0){
            object.velocityX = 0;
        } else {
            if (object.velocityX > 0){
                object.velocityX -= horizontalResistance;
            } else if (object.velocityX < 0){
                object.velocityX += horizontalResistance;
            }
        }

        if (object.x + object.originalWidth >= object.getScreenWidth()){
            object.velocityX *= -0.75;
        } else if (object.x < 0){
            object.velocityX *= -0.75;
        }


        if (object.x < 0){
            object.x = 0;
        } else if (object.x + object.originalWidth > object.getScreenWidth()){
            object.x = object.getScreenWidth() - object.originalWidth;
        }

    }
}
