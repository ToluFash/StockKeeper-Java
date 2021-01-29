package com.stockkeeper.Views.labels;

import com.stockkeeper.Views.uicomponents.SPopUpMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuLabel extends JMenu implements  MouseListener{

    private Color fg = new Color(0xFFFFFF);
    private Color bg = new Color(0x6898DC);
    private Color bg2 = new Color(0x344E6E);
    private String text;
    public Boolean setBorder;

    public MenuLabel() {
        setForeground(fg);
        setFont(new Font("SansSerif", Font.PLAIN, 12));
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }

    public MenuLabel(String text) {
        this.text=text;
        setForeground(fg);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setHorizontalAlignment(SwingConstants.LEADING);
        setCustomBorder(true);
        getPopupMenu().setBorderPainted(true);
        addMouseListener(this);
    }

    private void changeColor(){
        Graphics g = getGraphics();
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        gr2d.setColor(bg2);
        gr2d.fillRect(0, 0, getWidth(), getHeight());
        gr2d.setColor(fg);
        gr2d.drawString(text,10,getHeight()/2 + getFontMetrics(getFont()).getAscent()/2-2);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/arrowright3.png"));
        gr2d.drawImage(image,getWidth()-18,getHeight()/2 - 3,null);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gr2d.setColor(bg);
        gr2d.fillRect(0, 0, getWidth(), getHeight());
        gr2d.setColor(fg);
        gr2d.drawString(text,10,getHeight()/2 + getFontMetrics(getFont()).getAscent()/2-2);
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/arrowright3.png"));
        gr2d.drawImage(image,getWidth()-18,getHeight()/2 - 3,null);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.GRAY);
        if(setBorder)
            g.drawLine(0,getHeight()-1,getWidth(),getHeight()-1);
    }

    public void setCustomBorder(Boolean setBorder) {
        this.setBorder = setBorder;
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

    }
}
