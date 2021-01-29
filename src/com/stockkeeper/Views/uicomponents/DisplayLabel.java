package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DisplayLabel extends JLabel{

    public DisplayLabel(String text) {
        super(text, SwingConstants.LEFT);
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(0,0,0,3));
        setPreferredSize(new Dimension(0,20));
    }

    public DisplayLabel() {
        setHorizontalAlignment(SwingConstants.LEFT);
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(0,3,0,0));
        setPreferredSize(new Dimension(300,20));
    }

    public DisplayLabel(Dimension dimension) {
        setHorizontalAlignment(SwingConstants.LEFT);
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(0,3,0,0));
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Global.colorScheme.getPrimaryColor());
        graphics2D.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }
}
