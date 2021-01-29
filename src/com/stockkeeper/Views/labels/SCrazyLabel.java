package com.stockkeeper.Views.labels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SCrazyLabel extends JPanel{


    private Shape shape;
    private JLabel label;

    public SCrazyLabel(String text) {
        label = new JLabel(text);
        setBorder(new LineBorder(new Color(0xffffff),1));
        setLayout(new FlowLayout());
        add(label);

    }

    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0xAEAEAE));
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0x828282));
        g.drawLine(getX()+getWidth(), getY(), getX()+getWidth()+5, getY()+getHeight());
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }


    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
