package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Header2Label extends JLabel {


    private Color fg;
    private Color bg;

    public Header2Label(String text, Color background, Color foreground)
    {
        super(text);
        setFont(new Font("Segoe UI",  Font.BOLD, 12));
        fg = foreground;
        bg = background;
        setHorizontalAlignment(SwingConstants.CENTER);
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
        g.setColor(Color.GRAY);
        g.drawRect(0,0,getWidth()-1,getHeight()-1);
        super.paintBorder(g);
    }

}
