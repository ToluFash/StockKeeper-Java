package com.stockkeeper.Views.uicomponents.fancytextinput;

import javax.swing.*;
import java.awt.*;

public class GraphTitlePanel extends JPanel {




    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.drawLine(0,0,getWidth(),0);
        g.setColor(getBackground());
        g.fillRect(0,1,getWidth(),getHeight());
    }
}
