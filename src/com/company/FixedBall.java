package com.company;

/**
 * Created by miloshzelembaba on 2017-04-30.
 */
public class FixedBall extends Ball {

    public FixedBall(double x, double y, double width, double height, Canvas c){
        super(x,y,width,height,c);
    }

    @Override
    public void move(){
        velocityX = 0;
        velocityY = 0;
    }
}
