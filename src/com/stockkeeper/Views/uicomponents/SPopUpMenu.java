package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class SPopUpMenu extends JPopupMenu {


    public SPopUpMenu() {
    }

    public SPopUpMenu(String label) {
        super(label);
    }


    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0x6898DC));
        g.fillRect(0,0,getWidth()-1,getHeight()-1);

    }
}
