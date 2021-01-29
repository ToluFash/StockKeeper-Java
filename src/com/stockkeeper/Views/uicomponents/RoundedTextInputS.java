package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.text.Format;

public class RoundedTextInputS extends JFormattedTextField {

    private Shape shape;
    private String placeholder;
    public RoundedTextInputS() {
    }

    public RoundedTextInputS(Format format) {
        super(format);
    }

    public RoundedTextInputS(AbstractFormatter formatter) {
        super(formatter);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRoundRect(0,0,getWidth()+1,getHeight()-1,5,5);
        super.paintComponent(g);
    }

    @Override public void updateUI() {
        super.updateUI();
        setOpaque(false);
        // setBackground(new Color(0x0, true));
        setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    }
}