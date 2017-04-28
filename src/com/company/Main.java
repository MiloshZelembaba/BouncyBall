package com.company;

import java.awt.event.*;

public class Main {

    private static PhysicsManager physicsManager;
    private static Canvas canvas;
    private static boolean paused = false;
    private static int downX, downY, upX, upY;
    private static double MAX_SPEED = 15;

    public static void main(String[] args) {
        physicsManager = new PhysicsManager();
        canvas = new Canvas(physicsManager);
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

    public static void addListeners(){
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
                    Ball ball = new Ball(downX - 37.5, downY - 37.5, 75, 75, canvas);
                    int dy = upY - downY > 150 ? 15 : (upY - downY)/10;
                    int dx = upX - downX > 150 ? 15 : (upX - downX)/10;
                    ball.increaseYVelocity(dy);
                    ball.increaseXVelocity(dx);
                    physicsManager.add(ball);
                    canvas.add(ball);
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
                        canvas.drawProperties();
                    } else {
                        canvas.stopDrawingProperties();
                    }
                } else if (e.getKeyChar() == 'r'){
                    physicsManager.clear();
                    canvas.clear();
                }
            }
        });
    }
}
