package com.company;

import java.awt.event.*;

public class Main {

    private static PhysicsManager physicsManager;
    private static Canvas canvas;
    private static boolean paused = false;

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
            public void mouseClicked(MouseEvent e) {  // creating a new ball on each click
                super.mouseClicked(e);
                if (!paused) {
                    Ball ball = new Ball(e.getX(), e.getY(), 100, 100, canvas);
                    physicsManager.add(ball);
                    canvas.add(ball);
                } else { // if we're paused, don't create a ball and instead invoke the editor
                    canvas.onClick(e.getPoint());
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
                }
            }
        });
    }
}
