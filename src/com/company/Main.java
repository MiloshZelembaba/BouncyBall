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
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!paused) {
                    Ball ball = new Ball(e.getX(), e.getY(), 150, 150, 1000, 800);
                    physicsManager.add(ball);
                    canvas.add(ball);
                } else {
                    canvas.onClick(e.getPoint());
                }
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyChar() == 'p'){
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
