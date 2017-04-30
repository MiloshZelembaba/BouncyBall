package com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by miloshzelembaba on 2017-04-30.
 */
public class EditorView extends JFrame {

    Model model;

    public EditorView(JFrame propertyFrame, Model m){
        super();
        this.setLocation(propertyFrame.getX(), propertyFrame.getY() + propertyFrame.getHeight());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(200, 500);

        addItems();
        model = m;

        setVisible(true);
    }

    public void addItems(){
        JLabel moveableCircle = new JLabel("Moveable Circle");
        JLabel fixedCircle = new JLabel("Fixed Circle");
        JLabel fixedRectangle = new JLabel("Fixed Square");

        add(moveableCircle);
        //mainView.add(somePanelToDrawOn);
        add(fixedCircle);
        //mainView.add(somePanelToDrawOn);
        add(fixedRectangle);
        //mainView.add(somePanelToDrawOn);


        moveableCircle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setObject(model.MOVING_BALL);
                System.out.println("creating movabel balls");
            }
        });
        fixedCircle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setObject(model.FIXED_BALL );
                System.out.println("creating fixed balls");
            }
        });
        fixedRectangle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setObject(model.FIXED_RECTANGLE);
                System.out.println("creating fixed rectangles");
            }
        });
    }



}
