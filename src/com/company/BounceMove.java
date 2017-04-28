package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */

/**
 * Controls bounce movements
 */
public class BounceMove extends MoveAlgorithm{


    double bounceVY, stiffness;
    private final double MAX_SPEED = 15.0;
    private MoveAlgorithm baseMovement;

    public BounceMove(Physics obj, double bvy, double bvx, double s){
        stiffness = s;
        object = obj;
        bounceVY = bvy;
        baseMovement = new NormalMove(object);
    }

    public void move(){
        baseMovement.move();

        /* so like, when an object is moving faster, it should compress more than when the object is moving slowly */
        double stiffnessFactor = Math.abs(object.velocityY)/MAX_SPEED < 1 ? Math.abs(object.velocityY)/MAX_SPEED : 1;
        double speedAdjustedStiffness = stiffness + (1 - stiffnessFactor) * (0.5 - stiffness);

        object.setHeight(object.getScreenHeight() - object.y);
        object.velocityY += -1 * bounceVY * speedAdjustedStiffness;

        if (bounceVY < 2){ // meant to eventually stop bouncing but doesn't really work
            object.velocityY /= 2;

            if (object.velocityY < 0.01){

                object.velocityY = 0;
            }
            object.height = object.originalHeight;
            object.y = object.getScreenHeight() - object.getHeight();
            finish();
            return;
        }

        if (object.y + object.originalHeight <= object.getScreenHeight()){
            object.velocityY = -1 * bounceVY * object.bounceyness;
            object.height = object.originalHeight;
            object.y = object.getScreenHeight() - object.getHeight() - 1;
            finish();
        }

    }
}
