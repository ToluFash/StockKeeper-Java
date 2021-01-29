package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphPrintIcon extends JLabel implements MouseListener {
    Color color;

    public GraphPrintIcon() {
        color = Global.colorScheme.getTertiaryColor();
        setPreferredSize(new Dimension(18,18));
        addMouseListener(this);
        setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/graphPrintIcon.png")));
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
