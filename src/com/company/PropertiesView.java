package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by miloshzelembaba on 2017-04-26.
 */
public class PropertiesView extends JFrame {

    Properties property;
    private JLabel emptyLabel;

    public PropertiesView(){
        super();
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(200, 300);
        setVisible(true);

        emptyLabel = new JLabel("Select an object to manipulate it's properties");
        add(emptyLabel);
    }

    public void updateProperties(Properties p){
        property = p;
        ArrayList<String> allValues = p.getAllValues();
        remove(emptyLabel);

        for (String value : allValues){
            JPanel pane = new JPanel();
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            Label label = new Label(value, Label.CENTER);
            JSlider slider= new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(100 * property.getValue(value)));

            addSliderListener(slider, value);

            pane.add(label);
            pane.add(slider);
            add(pane);

            revalidate();
        }
    }


    public void addSliderListener(JSlider slider, String value){
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider)e.getSource();
                property.updateProperty(value, ((double)s.getValue())/100);
            }
        });
    }


}
