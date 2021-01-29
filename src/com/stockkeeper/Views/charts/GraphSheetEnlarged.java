package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class GraphSheetEnlarged extends JPopupMenu  {

    GraphSheetEnlarged2 graphSheetEnlarged;
    SScrollPane sScrollPane;
    JPanel topPanel;


    public GraphSheetEnlarged(HashMap<String,Double> graphData, String label, double buffer) {
        setBorder(BorderFactory.createLineBorder(Global.colorScheme.getPrimaryColor(), 1));
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Global.colorScheme.getPrimaryColor()));
        topPanel.setBackground(Global.colorScheme.getPrimaryColor());
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1100,30));
        add(topPanel, BorderLayout.NORTH);
        graphSheetEnlarged = new GraphSheetEnlarged2(graphData,label, topPanel, buffer);
        sScrollPane = graphSheetEnlarged.getjScrollPane();
        setPreferredSize(new Dimension(1100,600));
        add(sScrollPane, BorderLayout.CENTER);
        setBackground(Global.colorScheme.getTertiaryColor());
    }

    public GraphSheetEnlarged2 getGraphSheetEnlarged() {
        return graphSheetEnlarged;
    }

    public void refreshGraph(ArrayList<Map.Entry<String,Long>> graphData){
        graphSheetEnlarged.refreshGraphData(graphData);
    }
    public void refreshGraph(HashMap<String,Double> graphData, double buffer){
        graphSheetEnlarged.refreshGraphData(graphData, buffer);
    }
    public  void add(Map.Entry<String, Double> entry){
        this.graphSheetEnlarged.add(entry,0.0);
    }
    public void modify(Map.Entry<String, Double> entry){
        this.graphSheetEnlarged.modify(entry,0.0);
    }
    public void setSortType(int sortType){
        this.graphSheetEnlarged.setSortType(sortType);

    }

    public  void sort(Comparator<String> comparator, int sortType){
        this.graphSheetEnlarged.sort(comparator,sortType);
    }

}