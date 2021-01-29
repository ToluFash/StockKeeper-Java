package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExpandIcon extends JLabel implements MouseListener {
    Color color;

    public ExpandIcon() {
        color = Global.colorScheme.getTertiaryColor();
        setPreferredSize(new Dimension(30,25));
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(3);
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.drawLine(1,1,10,1);
        g2d.drawLine(1,1,1,8);

        g2d.drawLine(24,1,15,1);
        g2d.drawLine(24,1,24,8);

        g2d.drawLine(1,19,10,19);
        g2d.drawLine(1,19,1,12);

        g2d.drawLine(24,19,15,19);
        g2d.drawLine(24,19,24,12);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        color = Global.colorScheme.getQuinaryColor();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {

        color = Global.colorScheme.getTertiaryColor();
        repaint();
    }
}
