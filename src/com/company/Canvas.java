package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by miloshzelembaba on 2017-04-24.
 */

/**
 * As of right now this is the main VIEW class. Should separate the canvas portion out
 */
public class Canvas extends JPanel{

    private ArrayList<Physics> drawables = new ArrayList<>();
    private Line2D arrow;
    private Model model;
    private boolean drawArrow;
    private int ax, ay;
    private PhysicsManager physicsManager;
    private PropertiesView propertyView;
    private EditorView editorView;
    private JFrame f;


    public Canvas(PhysicsManager pm, Model m){
        f = new JFrame();
        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 800);
        f.setVisible(true);
        model = m;

        physicsManager = pm;
    }

    public void add(Physics ball){
        drawables.add(ball);
    }

    public void clear(){
        drawables.clear();
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        for (Physics ball : drawables) {
            if (ball != null) {
                ball.draw((Graphics2D) g);
            }
        }

        if (drawArrow){
            Point2D tmp = MouseInfo.getPointerInfo().getLocation();
            arrow = new Line2D.Double(ax,ay,tmp.getX() - this.getLocationOnScreen().x, tmp.getY() - this.getLocationOnScreen().y);
            ((Graphics2D)g).draw(arrow);
        }

        if (propertyView != null) {
            propertyView.repaint();
        }
        if (editorView != null){
            editorView.repaint();
        }


    }


    public void drawArrow(int x, int y){
        ax = x;
        ay = y;
        Point2D tmp = MouseInfo.getPointerInfo().getLocation();
        arrow = new Line2D.Double(ax,ay,tmp.getX() - this.getLocationOnScreen().x, tmp.getY() - this.getLocationOnScreen().y);
        drawArrow = true;
    }

    public void stopDrawingArrow(){
        drawArrow = false;
    }

    public void drawEditor(){
        propertyView = new PropertiesView(f);
        editorView = new EditorView(propertyView, model);
    }

    public void stopDrawingEditor(){
        propertyView.dispatchEvent(new WindowEvent(propertyView, WindowEvent.WINDOW_CLOSING));
        editorView.dispatchEvent(new WindowEvent(editorView, WindowEvent.WINDOW_CLOSING));
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
