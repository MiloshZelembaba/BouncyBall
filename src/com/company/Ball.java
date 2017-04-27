package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Created by miloshzelembaba on 2017-04-24.
 */
public class Ball extends Physics{

    private double width, height, originalHeight, originalWidth; // attributes of the ball
    private double bounceVX, bounceVY;
    private double screenWidth, screenHeight;
    private boolean bouncing; /* when the ball is bouncing we need to keep track of the original veloctiy */
    private Ellipse2D.Double ball;




    public Ball(double x, double y, double width, double height, double sw, double sh){
        this.width = originalWidth = width;
        this.height = originalHeight = height;
        this.x = x;
        this.y = y;
        velocityX = velocityY = 0;
        screenHeight = sh;
        screenWidth = sw;
        stiffness = 0.10;
        bouncing = false;
        ball = new Ellipse2D.Double(x,y,width,height);
        bounceyness = 0.90;
    }

    @Override
    public boolean contains(Point2D p){
        return ball.contains(p);
    }

    @Override
    public void move(){
        x += velocityX;
        y += velocityY;

        if (!bouncing){
            bounceVY = velocityY;
        }

        if (y + originalHeight >= screenHeight){
            bouncing = true;
            doBounce();
        }

        updateBall();
    }

    private void updateBall(){
        ball.x = x;
        ball.y = y;
        ball.width = width;
        ball.height = height;
    }

    @Override
    public void doBounce(){
        height = screenHeight - y;
        velocityY += -1 * bounceVY * stiffness;

        if (y + originalHeight <= screenHeight){
            velocityY = bounceVY;
            height = originalHeight;
            bouncing = false;
        }
    }


    protected void drawBall(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.draw(ball);

    }
}
