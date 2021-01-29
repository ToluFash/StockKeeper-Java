package com.stockkeeper.Views.charts;

import com.stockkeeper.Views.uicomponents.fancytextinput.GraphTitlePanel;
import com.stockkeeper.Views.uicomponents.fancytextinput.LegendPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerticalBarChart extends JDialog {


    private GraphTitlePanel topPanel;
    private LegendPanel rightPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private GraphSheetEnlarged2 graph;
    private Color bgColor;

    private void initHelperPanels(){
        topPanel = new GraphTitlePanel();
        rightPanel = new LegendPanel(bgColor);
        leftPanel = new JPanel();
        bottomPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(200,25));
        rightPanel.setPreferredSize(new Dimension(150,200));
        leftPanel.setPreferredSize(new Dimension(150,200));
        bottomPanel.setPreferredSize(new Dimension(200,175));
        topPanel.setBackground(bgColor);
        leftPanel.setBackground(bgColor);
        bottomPanel.setBackground(bgColor);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);
    }







}
