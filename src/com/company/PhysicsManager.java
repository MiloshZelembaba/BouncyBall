package com.company;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */


/**
 * Meant to be the overall physics god/manager thingy
 */
public class PhysicsManager {

    private ArrayList<Physics> objects = new ArrayList<>(); // all objects in the simulator

    public ArrayList<Physics> getObjects(){
        return objects;
    }

    public void add(Physics obj){
        objects.add(obj);
    }

    public void doGravity(){ /* adds gravity to all objects velocityY's */
        for (Physics obj : objects){
            obj.addGravity();
        }
    }

    public void doMove(){ // moves all objects according to their own move algorithms
        for (Physics obj : objects){
            obj.move();
        }

        checkCollisions();
    }

    public void manage(){
        doGravity(); // add gravity
        doMove(); // move all objects
    }


    /**
     * This is a simple collision detector, it will just test every object with every other object
     * checking if there is collision between the two, and if there is then notify them
     *
     * However, I'd like to implement a 2-phase collision detecting
     * 1) check which objects COULD be colliding (using quadtrees or something)
     * 2) do simple collision detection among the results from (1)
     *
     * Also important to note that this is only testing circle on circle collisions
     */
    private void checkCollisions(){
        int size = objects.size();
        for (int i = 0; i < size; ++i){
            for (int j = i + 1; j < size; ++j){
                Physics obj1 = objects.get(i);
                Physics obj2 = objects.get(j);
                if (collision((Ball)obj1, (Ball)obj2)){
                    /*
                     * we must save the velocities of obj1 since they will change in obj1.onCollision(obj2)
                     * and then wont be the same they used to be for obj2.onCollision(obj1)
                     * However their positions and nothing else should change
                     */
                    double vx = obj1.velocityX;
                    double vy = obj1.velocityY;
                    obj1.onCollision(obj2, obj2.velocityX, obj2.velocityY);
                    obj2.onCollision(obj1, vx, vy);
                }
            }
        }
    }

    private boolean collision(Ball obj1, Ball obj2){
        Point2D center1 = obj1.getCenter();
        double radius1 = obj1.getHeight()/2;
        Point2D center2 = obj2.getCenter();
        double radius2 = obj2.getHeight()/2;

        return center1.distance(center2) < (radius1 + radius2);
    }


}
