package com.company;

/**
 * Created by miloshzelembaba on 2017-04-27.
 */
public class BounceMove extends MoveAlgorithm{


    double bounceVY, stiffness;
    private final double MAX_SPEED = 15.0;

    public BounceMove(Physics obj, double bvy, double bvx, double s){
        stiffness = s;
        object = obj;
        bounceVY = bvy;
    }

    public void move(){
        //object.x += object.velocityX;
        object.y += object.velocityY;

//        if (Math.abs(bounceVY) < 2){
//            System.out.println("hello)");
//            object.height = object.originalHeight;
//            object.y = object.getScreenHeight() - object.getHeight();
//            finish();
//            return;
//        }


        /* so like, when an object is moving faster, it should compress more than when the object is moving slowly */
        double stiffnessFactor = Math.abs(object.velocityY)/MAX_SPEED < 1 ? Math.abs(object.velocityY)/MAX_SPEED : 1;
        double speedAdjustedStiffness = stiffness + (1 - stiffnessFactor) * (0.5 - stiffness);
//        double speedAdjustedStiffness = stiffness;

        object.setHeight(object.getScreenHeight() - object.y);
        object.velocityY += -1 * bounceVY * speedAdjustedStiffness;


        if (object.y + object.originalHeight <= object.getScreenHeight()){
            object.velocityY = -1 * bounceVY * object.bounceyness;
            object.height = object.originalHeight;
            object.y = object.getScreenHeight() - object.getHeight() - 1;
            finish();
        }

    }
}
