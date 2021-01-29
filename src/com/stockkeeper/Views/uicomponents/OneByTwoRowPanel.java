package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OneByTwoRowPanel extends JPanel {

    private JComponent component1;
    private JPanel component2;


    public OneByTwoRowPanel(JComponent component1, JPanel component2) {
        super();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        setBackground(Global.colorScheme.getTertiaryColor());
        layout.setHgap(0);
        setLayout(layout);
        this.component1= component1;
        this.component2 =component2;
        add(component1);
        add(component2);
        setBorder(new EmptyBorder(-3,15,0,0));
    }

    public JComponent getComponent1() {
        return component1;
    }

    public void setComponent1(JComponent component1) {
        this.component1 = component1;
    }

    public JPanel getComponent2() {
        return component2;
    }

    public void setComponent2(JPanel component2) {
        this.component2 = component2;
    }
}
