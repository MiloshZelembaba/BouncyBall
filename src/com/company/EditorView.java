package com.company;

import javax.swing.*;

/**
 * Created by miloshzelembaba on 2017-04-30.
 */
public class EditorView extends JFrame {


    public EditorView(JFrame propertyFrame){
        super();
        this.setLocation(propertyFrame.getX(), propertyFrame.getY() + propertyFrame.getHeight());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(200, 500);

        addItems();

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
    }



}
