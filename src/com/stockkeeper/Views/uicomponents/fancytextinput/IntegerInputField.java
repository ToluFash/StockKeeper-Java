package com.stockkeeper.Views.uicomponents.fancytextinput;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.Format;

public class IntegerInputField extends JFormattedTextField  {

    public IntegerInputField() {
    }

    public IntegerInputField(Format format, Dimension dimension) {
        super(format);
        setPreferredSize(dimension);
        setBorder(new EmptyBorder(0,3,0,2));
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
    }

}
