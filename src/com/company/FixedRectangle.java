package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by miloshzelembaba on 2017-04-30.
 */
public class FixedRectangle extends StationaryObject{


    private Rectangle2D rectangle;
    private Canvas canvas;

    public FixedRectangle(double x, double y, double width, double height, Canvas c){
        super();
        rectangle = new Rectangle((int)x,(int)y,(int)width,(int)height);
        canvas = c;

    }

    @Override
    public void move(){

    }

    @Override
    public void onCollision(Physics obj, double vx, double vy){
        if (!obj.isBouncing()) {
            obj.setMovementController(new BounceMove(obj, vx, vy, obj.stiffness));
        }
    }

    @Override
    public Point2D getCenter(){
        return new Point2D.Double(x + width/2, y + height/2);
    }

    @Override
    public double getPropertyMin(String prop){
        return 0; //TODO: does this need to be changed?
    }

    @Override
    public double getPropertyMax(String prop){
        return 0;
    }

    @Override
    public boolean contains(Point2D p){
        return rectangle.contains(p);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.MAGENTA);
        g.draw(rectangle);
    }
}
