package com.company;

import java.awt.geom.Point2D;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */
public abstract class Physics {

    /* properties about the object itself */
    private final static double GRAVITATIONAL_PULL = 0.15; /* gravity */
    protected double stiffness, bounceyness;

    /* properties about the objects movement */
    protected double velocityX, velocityY;
    protected double x, y;

    /* property about HOW the object moves */
    protected MoveAlgorithm moveController;

    public void addGravity(){
        increaseYVelocity(GRAVITATIONAL_PULL);
    }

    public void move(){
        x += velocityX;
        y += velocityY;
    }

    public abstract void doBounce();

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

        return p;
    }

    public void update(String s, Double d){
        if (s == Properties.STIFFNESS){
            stiffness = d;
        }
    }

    public void onFinishMove(){
        this.moveController = new NormalMove(this);
    }
}
