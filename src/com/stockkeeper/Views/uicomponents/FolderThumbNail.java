package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Views.StockDisplayItem;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FolderThumbNail  extends StockDisplayItem {

    Image image;
    Color color;


    public FolderThumbNail(Entry stockItem) {
        this.stockItem = stockItem;
        setPreferredSize(new Dimension(250, 150));
        color = Global.getThumbColor();
    }

    public void refresh(Entry entry){
        this.stockItem = entry;
        repaint();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRoundRect(0,0,getWidth()-1,getHeight()-1, 10, 10);
        g.setColor(color.darker().darker());
        g2d.fillRoundRect(getWidth() - 50,-5,getWidth(),50, 10,10);
        g2d.setColor(Global.colorScheme.getQuaternaryColor());
        g2d.fillPolygon(new int[] {getWidth()-50, getWidth()+1, getWidth()}, new int[] {0, 46, 0},3);

        if (image != null){
        }
        else {

            char ch = stockItem.getProduct().getName().charAt(0);
            g2d.setColor(color.darker().darker());
            Ellipse2D ellipse2D = new Ellipse2D.Double(5,20,100,100);
            g2d.fill(ellipse2D);
            g2d.setFont(FontsList.getSansSerif(Font.BOLD, 70));
            FontMetrics fontMetrics = getFontMetrics(FontsList.getSansSerif(Font.BOLD, 70));
            g2d.setColor(Color.WHITE);
            g2d.drawString(ch+"", 5 + ((100 - fontMetrics.charWidth(ch))/2) ,95);
        }

        g.setFont(FontsList.getHelvetica(Font.BOLD, 12));
        g2d.setColor(color.darker().darker());
        g2d.fillRect(125, 60, 12,12);
        g2d.drawString(stockItem.getProduct().getName(), 150, 70);
        Ellipse2D ellipse2D = new Ellipse2D.Double(125,80,12,12);
        g2d.fill(ellipse2D);
        g2d.drawString(stockItem.getQty()+"", 150, 90);
        g2d.fillPolygon(new int[] {132, 125, 138}, new int[] {100, 112, 112},3);
        g2d.drawString("$" +stockItem.getQty() * stockItem.getProduct().getUnitCost(), 150, 110);


    }
}
