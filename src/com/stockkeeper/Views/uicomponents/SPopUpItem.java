package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SPopUpItem extends JLabel implements MouseListener {

    private Color fg = new Color(0x202325);
    private Color bg = new Color(0x85E7FF);
    private Color bg2 = new Color(0x489EB2);
    private String owner;

    public SPopUpItem(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public SPopUpItem(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public SPopUpItem(String text) {
        super(text);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public SPopUpItem(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public SPopUpItem(Icon image) {
        super(image);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public SPopUpItem() {
        setBorder(new EmptyBorder(5,5,5,5));
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }



    public Color getFg() {
        return fg;
    }

    public void setFg(Color fg) {
        this.fg = fg;
    }

    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }

    public Color getBg2() {
        return bg2;
    }

    public void setBg2(Color bg2) {
        this.bg2 = bg2;
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
