package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

public class AddNew extends JLabel implements ActionListener, MouseListener {

    private int colorCount = 0;
    private RoundRectangle2D rectangle2DOuter;
    private RoundRectangle2D rectangle2DInner;
    private Ellipse2D ellipse2d;
    private boolean isend;
    private Color bg;
    private Color bg1;
    private Color bg2;

    public AddNew() {
        setPreferredSize(new Dimension(82,82));
        isend =false;
        rectangle2DOuter = new RoundRectangle2D.Double(0,0,85,40,5,5);
        rectangle2DInner = new RoundRectangle2D.Double(3,3,79,34,3,3);
        ellipse2d = new Ellipse2D.Double(10,10,60,60);
        bg = Global.colorScheme.getAddnewColor1();
        this.bg1 = Global.colorScheme.getAddnewColor1();
        this.bg2 = Global.colorScheme.getAddnewColor2();
        Timer timer = new Timer(60,this );
        timer.start();
        addMouseListener(this);

    }

    public AddNew(Color bg1, Color bg2) {
        setPreferredSize(new Dimension(82,82));
        isend =false;
        rectangle2DOuter = new RoundRectangle2D.Double(0,0,85,40,5,5);
        rectangle2DInner = new RoundRectangle2D.Double(3,3,79,34,3,3);
        ellipse2d = new Ellipse2D.Double(10,10,60,60);
        bg = Global.colorScheme.getAddnewColor1();
        this.bg1 = bg1;
        this.bg2 = bg2;
        Timer timer = new Timer(60,this );
        timer.start();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
       /* Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(FontsList.getSitkaBanner(Font.BOLD, 18));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Global.colorScheme.getTertiaryColor());
        g2d.fill(rectangle2DOuter);
        g2d.setColor(bg);
        g2d.fill(rectangle2DInner);
        g2d.setColor(Global.colorScheme.getDenaryColor());
        g2d.drawString("ADD NEW", 6, 25);
        Toolkit.getDefaultToolkit().sync();
        */
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(FontsList.getAgencyFB(Font.BOLD, 18));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(bg);
        g2d.draw(new Ellipse2D.Double(10-colorCount,10-colorCount,60 +colorCount*2,60+colorCount*2));
        g2d.setColor(bg);
        g2d.fill(ellipse2d);
        g2d.setColor(Color.WHITE);
        g2d.drawString("NEW", 28, 48);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isend)
            colorCount--;
        else
            colorCount++;

        if(colorCount == 10)
            isend = true;
        if(colorCount ==0)
            isend = false;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        bg = bg2;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        bg = bg1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        bg = bg2;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        bg = bg1;
    }
}
