package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SubMenuLabel2 extends SubMenuLabel implements MouseListener {

    protected   boolean loaded;
    protected int textstart;

    public SubMenuLabel2(String text, int textstart) {
        super(text,textstart);
        this.textstart = textstart;
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setPreferredSize(new Dimension(260,15));
        addMouseListener(this);
    }

    public SubMenuLabel2(String text, int textstart, Font font) {
        super(text,textstart, font);
        this.textstart = textstart;
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setPreferredSize(new Dimension(260,15));
        addMouseListener(this);
    }


    public int getTextstart() {
        return textstart;
    }

    public void setTextstart(int textstart) {
        this.textstart = textstart;
    }

    public SubMenuLabel2 clone() throws CloneNotSupportedException{
        return (SubMenuLabel2) super.clone();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
        {


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
