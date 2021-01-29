package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Banner2 extends JLabel implements MouseMotionListener, ActionListener, MouseListener {

    private int width;
    private int x;
    private int xMouse = 0;
    private Timer timer;
    private String title;
    private String bannerText;
    private Color background;


    public Banner2(String title, String bannerText) {
        this.title = title;
        this.bannerText = bannerText;
        setPreferredSize(new Dimension(350,150));
        this.background = Global.colorScheme.getSecondaryColor();
        animateBanner();
    }

    public Banner2() {
        setPreferredSize(new Dimension(350,150));
        this.background = Global.colorScheme.getPrimaryColor();
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRect(1,1,getWidth()-1, getHeight());
        g2d.setColor(getBackground().darker().darker());
        g2d.setFont(FontsList.getSitkaBanner(Font.PLAIN, 12));
        g2d.drawString(title, 10, 20);
        g2d.setFont(FontsList.getSitkaBanner(Font.BOLD, 30));
        g2d.drawString(bannerText, x, getHeight()/2+10);
    }


    public int getStringWidth(String string){
        FontMetrics metrics = getFontMetrics(FontsList.getSitkaBanner(Font.BOLD, 30));
        int width = 0;

        for (int x = 0; x< string.length();x++){
            width += metrics.charWidth(string.charAt(x));
        }
        return width;
    }

    public void animateBanner(){
        width =  350 - getStringWidth(bannerText);
        x = 0;
        if(width >= -3){
            x= (350- getStringWidth(bannerText))/2;

        }
        else{
            timer = new Timer(100, this);
            timer.start();

        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
        animateBanner();
    }

    @Override
    public Color getBackground() {
        return background;
    }

    @Override
    public void setBackground(Color background) {
        this.background = background;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(timer!=null)
        timer.stop();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(timer!=null)
        timer.start();
        xMouse = 0;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(timer!=null){

            if(xMouse == 0)
                xMouse = e.getX();
            int direction = e.getX()- xMouse;


            if(direction < 0){
                if(x == width-1)
                    x += 0;
                else
                    x-=1;
            }
            if(direction > 0){
                if(x >= 0)
                    x += 0;
                else
                    x+=1;
            }
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x == width-1)
            x = 0;
        else
            x-=1;
        repaint();
    }
}
