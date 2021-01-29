package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OneByTwoRow extends JPanel {

    private JComponent component1;
    private JComponent component2;


    public OneByTwoRow(JComponent component1, JComponent component2) {
        super();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        setBackground(Global.colorScheme.getTertiaryColor());
        layout.setHgap(5);
        setLayout(layout);
        this.component1= component1;
        this.component2 =component2;
        add(component1);
        add(component2);
        setBorder(new EmptyBorder(0,10,0,0));
    }

    public OneByTwoRow(JComponent component1, JComponent component2, int borderLeft) {
        super();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        setBackground(Global.colorScheme.getTertiaryColor());
        layout.setHgap(5);
        setLayout(layout);
        this.component1= component1;
        this.component2 =component2;
        add(component1);
        add(component2);
        setBorder(new EmptyBorder(-3,0,0,0));
    }

    public JComponent getComponent1() {
        return component1;
    }

    public void setComponent1(JComponent component1) {
        this.component1 = component1;
    }

    public JComponent getComponent2() {
        return component2;
    }

    public void setComponent2(JComponent component2) {
        this.component2 = component2;
    }
}
