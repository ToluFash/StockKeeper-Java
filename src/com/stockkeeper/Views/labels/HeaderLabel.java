package com.stockkeeper.Views.labels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HeaderLabel extends JLabel implements  MouseListener{

    private Color fg = new Color(0x202325);
    private Color bg = new Color(0xFFFFFF);
    private Color bg2 = new Color(0xB7B7B7);

    public HeaderLabel() {
        setForeground(fg);
        setFont(new Font("SansSerif",  Font.PLAIN, 12));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }

    public HeaderLabel(String text) {
        super(text);
        setForeground(fg);
        setFont(new Font("SansSerif", Font.PLAIN, 11));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
        setBorder(new EmptyBorder(5,5,5,5));
    }

    private void changeColor(){
        Graphics2D gr2d = (Graphics2D) getGraphics();
        gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        gr2d.setColor(bg2);
        gr2d.fillRoundRect(0, 0, getWidth(), getHeight(),0, 0);
        super.paintComponent(gr2d);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        gr2d.setColor(bg);
        gr2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1,2, 2);
        setForeground(fg);
        super.paintComponent(gr2d);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0x848484));
        g.drawRect(0,0,getWidth()-1,getHeight()-1);
        super.paintBorder(g);
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
        if (!getComponentPopupMenu().isVisible())
        changeColor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        repaint();

    }

    public Color getFg() {
        return fg;
    }

    public HeaderLabel setFg(Color fg) {
        this.fg = fg;
        return this;
    }

    public Color getBg() {
        return bg;
    }

    public HeaderLabel setBg(Color bg) {
        this.bg = bg;
        return this;
    }

    public Color getBg2() {
        return bg2;
    }

    public HeaderLabel setBg2(Color bg2) {
        this.bg2 = bg2;
        return this;
    }
}
