package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by miloshzelembaba on 2017-04-30.
 */
public class Model {
    public PhysicsManager physicsManager;
    public Canvas canvas;
    public boolean paused = false;
    private static int downX, downY, upX, upY;
    private static double MAX_SPEED = 15;
    public static final String MOVING_BALL = "MOVINGBALL";
    public static final String FIXED_BALL = "FIXEDBALL";
    public static final String FIXED_RECTANGLE = "FIXEDRECTANGLE";
    private static String selectedObject = MOVING_BALL;

    public Model() {
        physicsManager = new PhysicsManager();
        canvas = new Canvas(physicsManager, this);
        canvas.setFocusable(true);
        addListeners();

        while (true){
            if (!paused) {
                try {
                /* should maybe wanna update the fact that EVERYTHING is in this sleeping thread */
                    Thread.sleep(14); // 70 fps
                    physicsManager.manage(); // does physics
                } catch (Exception e) {}
            }


            canvas.repaint(); // move everything
        }
    }

    public void setObject(String obj){
        selectedObject = obj;
    }

    public void createObject(){
        if (selectedObject == MOVING_BALL){
            createBall();
        } else if (selectedObject == FIXED_BALL){
            createFixedBall();
        } else if (selectedObject == FIXED_RECTANGLE){
            createFixedRectangle();
        }

    }

    public void createFixedBall(){

    }

    public void createFixedRectangle(){

    }

    public void createBall(){
        Ball ball = new Ball(downX - 37.5, downY - 37.5, 75, 75, canvas);
        int dy = upY - downY > 150 ? 15 : (upY - downY)/10;
        int dx = upX - downX > 150 ? 15 : (upX - downX)/10;

        ball.increaseYVelocity(dy);
        ball.increaseXVelocity(dx);
        physicsManager.add(ball);
        canvas.add(ball);
    }

    public void addListeners(){
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {  // creating a new ball on each click
                super.mouseClicked(e);
                downX = e.getX();
                downY = e.getY();
                if (!paused) {
                    canvas.drawArrow(downX, downY);
//                    Ball ball = new Ball(e.getX() - 37.5, e.getY() - 37.5, 75, 75, canvas);
//                    physicsManager.add(ball);
//                    canvas.add(ball);
                } else { // if we're paused, don't create a ball and instead invoke the editor
                    canvas.onClick(e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e){
                upX = e.getX();
                upY = e.getY();
                if (!paused){
                    canvas.stopDrawingArrow();

                    createObject();
                }

            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyChar() == 'p'){ // to pause the simulator
                    paused = !paused;
                    if (paused){
                        canvas.drawEditor();
                    } else {
                        canvas.stopDrawingEditor();
                    }
                } else if (e.getKeyChar() == 'r'){
                    physicsManager.clear();
                    canvas.clear();
                }
            }
        });
    }
}
