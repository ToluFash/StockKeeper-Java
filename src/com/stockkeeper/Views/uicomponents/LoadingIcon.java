package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class LoadingIcon extends JLabel implements ActionListener {

    private int colorCount = 0;
    private Ellipse2D ellipse2D;
    private Ellipse2D ellipse2D2;
    private Rectangle2D rectangle2D;

    public LoadingIcon() {
        setPreferredSize(new Dimension(50,50));
        ellipse2D = new Ellipse2D.Double(0,0,50,50);
        ellipse2D2 = new Ellipse2D.Double(5,5,40,40);
        rectangle2D = new Rectangle2D.Double(0,0,getWidth(),50);
        Timer timer = new Timer(50,this );
        timer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Global.colorScheme.getDenaryColor());
        g2d.fill(ellipse2D);
        g2d.setColor(Global.thumbColors[colorCount  % Global.thumbColors.length]);
        g2d.fill(new Arc2D.Double(0,0,50,50, 9 * colorCount, 90,Arc2D.PIE));
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(ellipse2D2);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        colorCount++;
        if(colorCount == 40)
            colorCount = 0;
        repaint();
    }
}
