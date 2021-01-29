package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleLabel extends JLabel implements  MouseListener{

    private Color fg = new Color(0x202325);
    private Color bg = new Color(0xC0EEFF);
    private Color bg2 = new Color(0x95B8C8);

    public TitleLabel() {
        setForeground(fg);
        setFont(new Font("SansSerif", Font.PLAIN, 10));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }

    public TitleLabel(String text) {
        super(text);
        setForeground(fg);
        setFont(new Font("SansSerif", Font.PLAIN, 10));
        addMouseListener(this);
    }

    public TitleLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setForeground(fg);
        setFont(new Font("SansSerif", Font.PLAIN, 10));
        addMouseListener(this);
    }

    private void changeColor(){
        Graphics g = getGraphics();
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setColor(bg2);
        gr2d.fillRoundRect(0, 0, getWidth(), getHeight(),getHeight()/2,getHeight()/2);
        super.paintComponent(gr2d);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setColor(bg);
        gr2d.fillRoundRect(0, 0, getWidth(), getHeight(),getHeight()/2, getWidth()/2);
        super.paintComponent(gr2d);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,getHeight()/2,getHeight()/2);
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
