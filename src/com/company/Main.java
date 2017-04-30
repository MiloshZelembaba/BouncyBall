package com.company;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();

        while (true){
            if (!model.paused) {
                try {
                    /* should maybe wanna update the fact that EVERYTHING is in this sleeping thread */
                    Thread.sleep(14); // 70 fps
                    model.physicsManager.manage(); // does physics
                } catch (Exception e) {}
            }


            model.canvas.repaint(); // move everything
        }
    }
}
