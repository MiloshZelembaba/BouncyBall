package com.company;

import java.awt.geom.Point2D;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */
public abstract class Physics {

    /* properties about the object itself */
    public final static double GRAVITATIONAL_PULL = 0.3; /* gravity */
    protected double stiffness, bounceyness;
    protected double width, height, originalHeight, originalWidth; // attributes of the ball

    /* properties about the objects movement */
    protected double velocityX, velocityY;
    protected double x, y;

    /* property about HOW the object moves */
    protected MoveAlgorithm moveController;


    /* additonal references */
    protected Canvas canvas;

    public void addGravity(){
        increaseYVelocity(GRAVITATIONAL_PULL);
    }

    public void move(){
        x += velocityX;
        y += velocityY;
    }


    public abstract boolean contains(Point2D p);

    public void increaseXVelocity(double x){
        velocityX += x;
    }

    public void increaseYVelocity(double y){
        velocityY += y;
    }

    public Properties createProperties(){
        Properties p = new Properties(this);
        p.add(p.STIFFNESS, stiffness);
        p.add(p.BOUNCEYNESS, bounceyness);

        return p;
    }

    public void update(String s, Double d){
        if (s == Properties.STIFFNESS){
            stiffness = d;
        } else if (s == Properties.BOUNCEYNESS){
            bounceyness = d;
        }
    }

    public void onFinishMove(){
        this.moveController = new NormalMove(this);
    }



    public double getStiffness(){
        return stiffness;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double h){
        height = h;
    }

    public double getScreenHeight(){
        return (double)canvas.getHeight();
    }

    public abstract double getPropertyMax(String p);
}
