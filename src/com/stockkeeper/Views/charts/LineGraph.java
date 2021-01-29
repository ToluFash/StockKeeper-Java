package com.stockkeeper.Views.charts;

import com.stockkeeper.Views.uicomponents.fancytextinput.GraphTitlePanel;
import com.stockkeeper.Views.uicomponents.fancytextinput.LegendPanel;
import com.stockkeeper.Models.product.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LineGraph extends JDialog {


    private GraphTitlePanel topPanel;
    private LegendPanel rightPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private LineGraphSheet graph;
    private Color bgColor;
    private ArrayList<Ticket> data;

    public LineGraph(Frame owner, String title, ArrayList<Ticket> data, int largest) {
        super(owner, title);
        this.bgColor = new Color(0x6898DC);
        initHelperPanels();
        initGraphPanel(data,largest);
        setVisible(true);
        setSize(new Dimension(1920,1080));
    }

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
    private  void initGraphPanel(ArrayList<Ticket> data, int largest){
        this.graph = new LineGraphSheet(data,largest,topPanel,bottomPanel,leftPanel,rightPanel);
        add(graph.getjScrollPane(), BorderLayout.CENTER);
    }









}
