package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TrafficPanel extends JPanel {


    private TrafficLight success;
    private TrafficLight pending;
    private TrafficLight failed;


    public TrafficPanel() {
        this.success = new TrafficLight(Color.GREEN);
        this.pending = new TrafficLight(Color.YELLOW);
        this.failed = new TrafficLight(Color.RED);
        setPreferredSize(new Dimension(60,20));
        setBackground(Global.colorScheme.getSecondaryColor());
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(this.success);
        add(this.pending);
        add(this.failed);
        turnOnSuccess();
    }

    public void turnOnSuccess(){
        this.success.turnOn();
        repaint();
    }
    public  void turnOffSuccess(){
        this.success.turnOff();
        repaint();
    }

    public void turnOnFailed(){
        this.failed.turnOn();
        repaint();
    }
    public  void turnOffFailed(){
        this.failed.turnOff();
        repaint();
    }

    public void turnOnPending(){
        this.pending.turnOn();
        repaint();
    }
    public  void turnOffPending(){
        this.pending.turnOff();
        repaint();
    }

}


class TrafficLight extends JPanel {

    private Color trafficColor;
    private Color defaultTrafficColor;
    public TrafficLight(Color trafficColor) {
        this.trafficColor = Color.GRAY;
        this.defaultTrafficColor = trafficColor;
        setPreferredSize(new Dimension(12,12));
    }

    public void turnOn(){
        trafficColor = defaultTrafficColor;
        repaint();
    }
    public  void turnOff(){
        trafficColor = Color.LIGHT_GRAY;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(trafficColor);
        Ellipse2D ellipse2D = new Ellipse2D.Double(2,2,8,8);
        g2d.fill(ellipse2D);
    }



}