package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.*;

public class ToggleLabel extends JLabel {

    public ToggleLabel(String text) {
        super(text);
        setPreferredSize(new Dimension(45,26));
    }


    @Override
    protected void paintComponent(Graphics g) {
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/stockkeeper/images/toggleWhite.png"));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0));
        g2d.fillRect(0,0, getWidth(),getHeight());
        g2d.drawImage(image,0,0,null);

    }
}
