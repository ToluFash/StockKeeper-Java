package com.stockkeeper.Views.labels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SLabel extends JPanel{


    private JLabel label;

    public SLabel(String text) {
        label = new JLabel(text);
        setBorder(new LineBorder(new Color(0xffffff),1));
        setBackground(new Color(0xffffff));
        setLayout(new FlowLayout());
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
