package com.stockkeeper.Views.labels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SubMenuLabel extends JLabel implements Cloneable, MouseListener{

    protected int textstart;
    protected boolean in;
    public SubMenuLabel(String text, int textstart) {
        super(text);
        this.textstart = textstart;
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(new Color(0x6898DC));
        setPreferredSize(new Dimension(260,20));
        in = false;
        addMouseListener(this);
    }

    public SubMenuLabel(String text, int textstart, Font font) {
        super(text);
        this.textstart = textstart;
        setFont(font);
        setPreferredSize(new Dimension(260,18));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (in)
        {
            changeBackground(g);
        }
        else
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,getWidth(),getHeight());
            g2d.setColor(getForeground());
            g2d.drawString(getText(), textstart,getHeight()/2 + 7);
        }
    }


    protected void changeBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0x6898DC));
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(getForeground());
        g2d.setColor(Color.white);
        g2d.drawString(getText(), textstart,getHeight()/2 + 7);
    }

    public int getTextstart() {
        return textstart;
    }

    public void setTextstart(int textstart) {
        this.textstart = textstart;
    }

    public SubMenuLabel clone() throws CloneNotSupportedException{
        return (SubMenuLabel) super.clone();
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
        changeBackground(getGraphics());
        in = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        in = false;
        repaint();
    }
}
