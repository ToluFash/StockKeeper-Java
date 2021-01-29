package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.SScrollPane;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LineGraphSheetEnlarged extends JPopupMenu  {

    LineGraphSheetEnlarged2 graphSheetEnlarged;
    SScrollPane sScrollPane;
    JPanel topPanel;


    public LineGraphSheetEnlarged(HashMap<Date,Double> graphData, String label, int displayType) {
        setBorder(BorderFactory.createLineBorder(Global.colorScheme.getPrimaryColor(), 1));
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Global.colorScheme.getPrimaryColor()));
        topPanel.setBackground(Global.colorScheme.getPrimaryColor());
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(1100,30));
        add(topPanel, BorderLayout.NORTH);
        graphSheetEnlarged = new LineGraphSheetEnlarged2(graphData,label, topPanel,displayType);
        sScrollPane = graphSheetEnlarged.getjScrollPane();
        setPreferredSize(new Dimension(1100,600));
        add(sScrollPane, BorderLayout.CENTER);
        setBackground(Global.colorScheme.getTertiaryColor());
    }

    public LineGraphSheetEnlarged2 getGraphSheetEnlarged() {
        return graphSheetEnlarged;
    }

    public void refreshGraph(HashMap<Date,Double> graphData){
        graphSheetEnlarged.refreshGraphData(graphData);
    }
    public  void add(Map.Entry<Date, Double> entry){
        this.graphSheetEnlarged.add(entry);
    }

    public void modify(Map.Entry<Date, Double> entry){
        this.graphSheetEnlarged.modify(entry);
    }

    public void setSortType(int sortType){
        this.graphSheetEnlarged.setSortType(sortType);

    }

}
