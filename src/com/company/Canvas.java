package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by miloshzelembaba on 2017-04-24.
 */

/**
 * As of right now this is the main VIEW class. Should separate the canvas portion out
 */
public class Canvas extends JPanel{

    private ArrayList<Ball> drawables = new ArrayList<>();
    private PhysicsManager physicsManager;
    private PropertiesView propertyView;


    public Canvas(PhysicsManager pm){
        JFrame f = new JFrame();
        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 800);
        f.setVisible(true);

        physicsManager = pm;
    }

    public void add(Ball ball){
        drawables.add(ball);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        for (Ball ball : drawables) {
            if (ball != null) {
                ball.drawBall((Graphics2D) g);
            }
        }


    }

    public void drawProperties(){
        propertyView = new PropertiesView();
    }

    public void stopDrawingProperties(){
        propertyView.dispatchEvent(new WindowEvent(propertyView, WindowEvent.WINDOW_CLOSING));
    }


    // detects which object we clicked. how can i make this more efficient?
    // - put click listeners on each object?
    // - something else idfk
    // this method is only is use when the simulator is paused
    public void onClick(Point2D p){
        ArrayList<Physics> objects = physicsManager.getObjects();

        for (Physics obj : objects){
            if (obj.contains(p)){
                propertyView.updateProperties(obj.createProperties());
            }
        }
    }
}
