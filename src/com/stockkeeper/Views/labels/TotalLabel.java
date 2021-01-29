package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TotalLabel extends JLabel implements  MouseListener{

    private Color fg = new Color(0x202325);
    private Color bg = new Color(0x85E7FF);
    private Color bg2 = new Color(0x489EB2);

    public TotalLabel() {
        setForeground(fg);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }

    public TotalLabel(String text) {
        super(text);
        setForeground(fg);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        addMouseListener(this);
    }

    public TotalLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setForeground(fg);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        addMouseListener(this);
    }

    private void changeColor(){
        Graphics g = getGraphics();
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setColor(bg2);
        gr2d.fillRoundRect(0, 0, getWidth(), getHeight(),0, 0);
        super.paintComponent(gr2d);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setColor(bg);
        gr2d.fillRoundRect(0, 0, getWidth(), getHeight(),2, 2);
        super.paintComponent(gr2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        changeColor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        repaint();

    }
}
