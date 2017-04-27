package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by miloshzelembaba on 2017-04-24.
 */
public class Ball extends Physics{

    private double screenWidth, screenHeight;
    private boolean bouncing; /* when the ball is bouncing we need to keep track of the original veloctiy */
    private Ellipse2D.Double ball;




    public Ball(double x, double y, double width, double height, Canvas c){
        this.width = originalWidth = width;
        this.height = originalHeight = height;
        this.x = x;
        this.y = y;
        velocityX = velocityY = 0;
        velocityX = 1;
        canvas = c;
        screenHeight = c.getHeight();
        screenWidth = c.getWidth();
        stiffness = 0.10;
        bouncing = false;
        ball = new Ellipse2D.Double(x,y,width,height);
        bounceyness = 0.9;
        moveController = new NormalMove(this);
    }

    @Override
    public boolean contains(Point2D p){
        return ball.contains(p);
    }



    public void move(){
        moveController.move();

        if (y + originalHeight >= screenHeight){
            if (!(moveController instanceof  BounceMove)){
                moveController = new BounceMove(this, velocityY, velocityX, stiffness);
            }
        }

        if (x + originalWidth >= screenWidth){
            velocityX *= -1;
        } else if (x < 0){
            velocityX *= -1;
        }



        updateBall();
    }

    public double getPropertyMax(String p){
        double max = 0;
        if (p == Properties.STIFFNESS){
            max = 0.5;
        } else if (p == Properties.BOUNCEYNESS){
            max = 1;
        }

        return max;
    }

//    @Override
//    public void move(){
//        x += velocityX;
//        y += velocityY;
//
//        if (!bouncing){
//            bounceVY = velocityY;
//        }
//
//        if (y + originalHeight >= screenHeight){
//            bouncing = true;
//            doBounce();
//        } else if (bouncing){
//            velocityY = -1 * bounceVY * bounceyness;
//            height = originalHeight;
//            bouncing = false;
//
//        }
//
//        updateBall();
//    }

    private void updateBall(){
        ball.x = x;
        ball.y = y;
        ball.width = width;
        ball.height = height;
    }


    protected void drawBall(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.draw(ball);

    }
}
