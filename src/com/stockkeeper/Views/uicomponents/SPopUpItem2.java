package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class SPopUpItem2 extends JMenuItem {

    public SPopUpItem2(String text) {
        super(text);
        setFont(new Font("SansSerif", Font.PLAIN, 12));
        setForeground(Color.WHITE);
        setBackground(new Color(0x6898DC));
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0,getHeight()-1,getWidth(),getHeight()-1);
    }

}
