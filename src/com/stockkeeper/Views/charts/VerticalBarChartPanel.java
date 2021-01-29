package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class VerticalBarChartPanel extends JPanel {


    private GraphSheetRevamped graph;

    public VerticalBarChartPanel(HashMap<String,Double> graphData, double buffer) {
        initGraphPanel(graphData, buffer);
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Global.colorScheme.getPrimaryColor(), 1, true));
        setSize(new Dimension(350,250));
    }


    private  void initGraphPanel(HashMap<String,Double> graphData, double buffer){
        this.graph = new GraphSheetRevamped(graphData, buffer);
        add(graph.getjScrollPane());
    }

    public void refreshGraph(HashMap<String,Double> graphData, double buffer){
        this.graph.setGraphData(graphData, buffer);
        this.graph.getEnlarged().refreshGraph(graphData, buffer);
        this.graph.repaint();

    }

    public  void add(Map.Entry<String, Double> entry){
        this.graph.add(entry,0.0);
        this.graph.getEnlarged().add(entry);
    }
    public void modify(Map.Entry<String, Double> entry){
        this.graph.modify(entry,0.0);
    }

    public void setSortType(int sortType){
        this.graph.setSortType(sortType);
        this.graph.repaint();
    }

    @Override
    public void repaint(Rectangle r) {
        graph.repaint();
        super.repaint(r);
    }

    public  void sort(Comparator<String> comparator, int sortType){
        this.graph.sort(comparator,sortType);
    }











}
