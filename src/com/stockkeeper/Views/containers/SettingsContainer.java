package com.stockkeeper.Views.containers;

import com.stockkeeper.Views.labels.TopMenuLabel;

import javax.swing.*;
import java.awt.*;

public class SettingsContainer  extends JPanel {
    protected Dimension dimension;
    protected FlowLayout layout;
    public SettingsContainer(int width, int items) {
        super();
        dimension = new Dimension(width, items * 10);
        layout = (FlowLayout) getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        setBackground(Color.WHITE);
        setPreferredSize(dimension);
    }


    @Override
    public Component add(Component comp) {
        dimension.height += comp.getPreferredSize().getHeight();
        return super.add(comp);
    }

    @Override
    public void remove(Component comp) {
        dimension.height -= comp.getPreferredSize().getHeight();
        super.remove(comp);
    }
    public void increaseHeight(int height, int deep){
        dimension.height += height* deep;
        if( getParent() instanceof  SettingsContainer)
        {
            ((SettingsContainer)getParent()).increaseHeight(height, deep +1);
        }
        revalidate();
        repaint();

    }
    public void decreaseHeight(int height, int deep){
        dimension.height -= height* deep;
        if( getParent() instanceof  SettingsContainer)
        {
            ((SettingsContainer)getParent()).decreaseHeight(height, deep+1);
        }
        revalidate();
        repaint();

    }
}
