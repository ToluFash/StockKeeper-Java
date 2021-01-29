package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.ImageObserver;

public class ProductsModelHeaderLabel extends JLabel {

    private Color background;
    private boolean selected;
    private int sortType;
    private  ImageIcon asc;
    private  ImageIcon desc;
    private int type;

    public ProductsModelHeaderLabel(String text) {
        super(text);
        selected = false;
        setBorder(new EmptyBorder(0,10,0,0));
        background = Global.colorScheme.getSecondaryColor();
    }
    public ProductsModelHeaderLabel(String text, Color background) {
        super(text);
        this.background= background;
        selected = false;
        setBorder(new EmptyBorder(0,10,0,0));
        background = Global.colorScheme.getSecondaryColor();
    }
    public ProductsModelHeaderLabel(String text, Color background, Color foreground) {
        super(text);
        this.background= background;
        setForeground(foreground);
        selected = false;
        setBorder(new EmptyBorder(0,10,0,0));
        background = Global.colorScheme.getSecondaryColor();
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth()-3,getHeight());
        if(selected)
            if(sortType == 0)
                g.drawImage(asc.getImage(), getWidth()-25, 12, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            else
                g.drawImage(desc.getImage(), getWidth()-25, 12, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
        super.paintComponent(g);
    }
    @Override public void updateUI() {
        super.updateUI();
        setOpaque(false);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public ImageIcon getAsc() {
        return asc;
    }

    public void setAsc(ImageIcon asc) {
        this.asc = asc;
    }

    public ImageIcon getDesc() {
        return desc;
    }

    public void setDesc(ImageIcon desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Color getBackground() {
        return background;
    }

    @Override
    public void setBackground(Color background) {
        this.background = background;
    }
}
