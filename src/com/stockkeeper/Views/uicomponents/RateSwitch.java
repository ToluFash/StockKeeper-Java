package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;

public class RateSwitch extends JComponent {
    private String text;
    private Color bg;

    public RateSwitch(String text) {
        this.text = text;
        bg = new Color(0x59B3FF);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension dimension1 = getPreferredSize();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(bg);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(Global.colorScheme.getFontColorPrimary());
        g2d.drawString(text,7,18);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }
}