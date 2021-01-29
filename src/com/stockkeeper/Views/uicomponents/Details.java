package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Views.uicomponents.fancytextinput.OpenIcon;

import javax.swing.*;
import java.awt.event.MouseListener;

public interface Details {



    JPanel getGraphPanel();
    void setDetailsVisible(Boolean visible);
    void switchView(int switchType);
    OpenIcon getOpenIcon();
    void addMouseListener(MouseListener l);

}
