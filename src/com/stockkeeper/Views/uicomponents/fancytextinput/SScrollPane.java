package com.stockkeeper.Views.uicomponents.fancytextinput;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SScrollPane extends JScrollPane {


    public SScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        setBorder(new EmptyBorder(4,4,0,0));
        setOpaque(false);
    }

    public SScrollPane(Component view) {
        super(view);
        setBorder(new EmptyBorder(0,0,0,5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Global.colorScheme.getPrimaryColor());
        g.fillRect(0,0,getWidth(),getHeight());
    }
    public SScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
    }

    public SScrollPane() {
    }

}
