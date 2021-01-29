package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class TableHeaderLabel extends JLabel {
    private  boolean setBorder;
    public TableHeaderLabel(String text) {
        super(text);
        setFont(new Font("SansSerif", Font.PLAIN, 10));
        setBackground(new Color(0xBEEEFF));
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setBorder = true;
    }

    @Override
    protected void paintBorder(Graphics g) {
        if(setBorder){
            g.setColor(Color.GRAY);
            g.drawLine(0,getHeight()-1,getWidth()*2,getHeight());
        }
    }

    public void setCustomBorder(boolean setBorder) {
        this.setBorder = setBorder;
    }
}
