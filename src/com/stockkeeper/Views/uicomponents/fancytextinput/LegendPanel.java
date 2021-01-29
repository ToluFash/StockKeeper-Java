package com.stockkeeper.Views.uicomponents.fancytextinput;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class LegendPanel extends JPanel {

    // Bar Colors
    private Color[] colors;
    private Color bgColor;

    public LegendPanel(Color bgColor) {
        this.bgColor = bgColor;
        initColors();
    }


    private void initColors(){
        colors = new Color[]{
                new Color(0xE07A6D),
                new Color(0x6CDAE0),
                new Color(0xB8E089),
                new Color(0xE04FD9),
                new Color(0x6D8DE0),
                new Color(0xE0444B),
                new Color(0xE09854),
                new Color(0x4353B9),
                new Color(0x82E0B2),
                new Color(0x77AFE0),
                new Color(0xA271E0),
                new Color(0xE0AA89),
                new Color(0x4489FE),
                new Color(0x87E02B),
                new Color(0x62B58A),
                new Color(0x5E89B5),
                new Color(0x845CB6),
                new Color(0xB4896E),
                new Color(0x3061B4),
                new Color(0x6CB423),
                new Color(0x69B48F)
        };

    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        HashMap<String,Integer> graphData = new HashMap<>();
        graphData.put("Heineken", 4);
        graphData.put("Gulder", 2);
        graphData.put("Star", 3);
        graphData.put("Guiness", 1);
        graphData.put("Budweiser", 5);
        graphData.put("33", 5);
        graphData.put("Legend", 7);
        graphData.put("Maltina", 8);
        graphData.put("Malta Guiness", 3);
        graphData.put("Grand Malt", 6);
        graphData.put("1960", 5);
        graphData.put("Origin", 5);
        graphData.put("Trophy", 8);

        g.setColor(bgColor);
        g.fillRect(0,0,getWidth(),getHeight());

        int count= 0;
        int height =0;
        for (Map.Entry<String, Integer> entry : graphData.entrySet()) {
            Color barColor = colors[count % colors.length];
            g.setColor(barColor);
            g.setFont(new Font("Arial",Font.PLAIN,12));
            Rectangle2D bar = new Rectangle2D.Double(5,
                    height,
                    20,
                    20);
            g2d.fill(bar);
            g.drawString(entry.getKey(), 30, height+15);





            height+=25;
            count++;
        }




    }
}
