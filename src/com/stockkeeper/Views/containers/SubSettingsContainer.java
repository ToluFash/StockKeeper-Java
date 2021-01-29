package com.stockkeeper.Views.containers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SubSettingsContainer extends SettingsContainer {
    public SubSettingsContainer(int width, int items) {
        super(width, items);
        dimension = new Dimension(width, 0);
        setPreferredSize(dimension);
        setBorder(new EmptyBorder(2,0,2,0));
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

    @Override
    public void removeAll() {
        for (Component comp: this.getComponents()){
            remove(comp);
        }
    }
}
