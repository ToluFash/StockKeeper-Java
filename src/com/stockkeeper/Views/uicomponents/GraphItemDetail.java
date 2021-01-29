package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GraphItemDetail extends JPopupMenu {

    private String primary;
    private String secondary;

    public GraphItemDetail() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);
    }

    private Color color;
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g.setFont(FontsList.getHelvetica(Font.BOLD, 12));
        g2d.setColor(color.darker().darker());
        g2d.fillRect(5, 5, 12,12);
        g2d.drawString(primary, 20, 15);
        Ellipse2D ellipse2D = new Ellipse2D.Double(5,20,12,12);
        g2d.fill(ellipse2D);
        g2d.drawString(secondary, 20, 30);

    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
        setPreferredSize(new Dimension(getTextWidth(primary) + 30, 40));

    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public int getTextWidth(String text){
        FontMetrics fontMetrics = getFontMetrics(FontsList.getHelvetica(Font.BOLD, 12));
        int width = 0;
        for (int x = 0; x < text.length(); x++){

            width += fontMetrics.charWidth(text.charAt(x));
        }
        return width;
    }

}
