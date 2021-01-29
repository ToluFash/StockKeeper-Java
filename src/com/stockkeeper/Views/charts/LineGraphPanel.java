package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LineGraphPanel extends JPanel {


    private LineGraphSheetRevamped graph;

    public LineGraphPanel(HashMap<Date,Double> graphData, int displayType) {
        initGraphPanel(graphData, displayType);
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Global.colorScheme.getPrimaryColor(), 1, true));
        setPreferredSize(new Dimension(375,254));
    }

    private  void initGraphPanel(HashMap<Date,Double> graphData, int displayType){
        this.graph = new LineGraphSheetRevamped(graphData, displayType);
        add(graph.getjScrollPane());
    }

    public void refreshGraph(HashMap<Date,Double> graphData,int displayType){
        this.graph.setGraphData(graphData, displayType);
        this.graph.repaint();
    }
    public  void add(Map.Entry<Date, Double> entry){
        this.graph.add(entry);
        this.graph.getEnlarged().add(entry);
    }
    public void modify(Map.Entry<Date, Double> entry){
        this.graph.modify(entry);
    }


    public void setSortType(int sortType){
        this.graph.setSortType(sortType);
        this.graph.repaint();

    }







}
