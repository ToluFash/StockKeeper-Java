package com.stockkeeper.Views.Nav;


import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.CalendarDisplay;
import com.stockkeeper.Views.charts.VerticalBarChartPanel;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaxesView extends JPanel implements ActionListener, MouseListener {
    private JPanel north;
    private JPanel padding;
    private JPanel center;
    private JPanel east;
    private JPanel west;
    private CalendarDisplay calendarDisplay;
    private TaxDetailsPanel taxDetailsPanel;
    private DisplayLabel totalRevenue;
    private DisplayLabel taxPayableLabel;
    private DisplayLabel aRateLabel;
    private DisplayLabel highestGrossingLabel;
    private CalendarScroller calendarScroller;
    private Banner2 mPayable;
    private Banner2 aRate;
    private Banner2 tRevenue;
    private GregorianCalendar previous;
    private VerticalBarChartPanel barChartPanel;
    private CalendarDisplay.CalendarLabel calendarLabelSelected;


    public TaxesView(Entity entity, User user) {
        setLayout(new BorderLayout());

        setBackground(Global.colorScheme.getQuaternaryColor());
        initTopPanels();
        initContent();
        initDetails();
        initScrollers();
    }


    public void initTopPanels(){
        north = new JPanel(new BorderLayout());
        north.setOpaque(false);
        north.setPreferredSize(new Dimension(1000, 50));
        center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        east = new JPanel(new FlowLayout(FlowLayout.LEFT));
        east.setPreferredSize(new Dimension(400, 10));
        east.setBackground(Global.colorScheme.getTertiaryColor());
        east.setOpaque(true);
        west = new JPanel(new BorderLayout());
        west.setPreferredSize(new Dimension(80, 10));
        west.setBackground(Global.colorScheme.getTertiaryColor());
        west.setOpaque(true);
        //add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(east, BorderLayout.EAST);
        add(west, BorderLayout.WEST);
    }

    private void initContent(){
        padding = new JPanel();
        padding.setBackground(Global.colorScheme.getQuaternaryColor());
        padding.setPreferredSize(new Dimension(50,50));
        center.add(padding,BorderLayout.NORTH);
        previous = new GregorianCalendar();
        calendarDisplay = new CalendarDisplay(previous, this);
        calendarLabelSelected = calendarDisplay.getCalendarLabelInstance();
        barChartPanel = new VerticalBarChartPanel(Global.sales.getEntriesbyProductsModelGraph(new GregorianCalendar().getTime()), 0.0);
        center.add(calendarDisplay);
        center.addMouseListener(this);
        padding.addMouseListener(this);

        center.setBorder(new EmptyBorder(0,20,50,20));
    }
    private void initDetails(){
        mPayable = new Banner2("Monthly Payable",calendarDisplay.getmPayable() + "");
        aRate = new Banner2("Average Rate",calendarDisplay.getmPayable()/calendarDisplay.gettRevenue() *100+"");
        tRevenue = new Banner2("Total Revenue",calendarDisplay.gettRevenue()+"");


        totalRevenue = new DisplayLabel(new Dimension(248,20));
        taxPayableLabel = new DisplayLabel(new Dimension(248,20));
        aRateLabel = new DisplayLabel(new Dimension(248,20));
        highestGrossingLabel = new DisplayLabel(new Dimension(248,20));
        taxPayableLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        aRateLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        highestGrossingLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));

        taxDetailsPanel = new TaxDetailsPanel(totalRevenue,taxPayableLabel,aRateLabel,highestGrossingLabel);
        taxDetailsPanel.getGraphPanel().add(barChartPanel);
        taxDetailsPanel.setDetailsVisible(false);
        taxDetailsPanel.setChartsVisible(false);
        east.add(mPayable);
        east.add(aRate);
        east.add(tRevenue);
        east.add(taxDetailsPanel);
        east.setBorder(new EmptyBorder(20,25,0,0));
    }

    private void setPrimaryDetailVisible(boolean visible){
        mPayable.setVisible(visible);
        aRate.setVisible(visible);
        tRevenue.setVisible(visible);
    }




    private void initScrollers(){
        JPanel paddingTop = new JPanel();
        JPanel paddingBottom = new JPanel();
        paddingTop.setPreferredSize(new Dimension(0,30));
        paddingBottom.setPreferredSize(new Dimension(0,30));
        calendarScroller = new CalendarScroller();
        calendarScroller.addActionListener(this);
        west.add(calendarScroller);
        calendarScroller.init();
    }


    private void setMPayable(){
        mPayable.setBannerText(calendarDisplay.getmPayable() + "");
        mPayable.repaint();
    }

    private  void setARate(){
        if(calendarDisplay.gettRevenue() == 0)
            aRate.setBannerText(0+"");
        else
            aRate.setBannerText(calendarDisplay.getmPayable()/calendarDisplay.gettRevenue() *100+"");

        aRate.repaint();
    }

    private  void setTRevenue(){
        tRevenue.setBannerText(calendarDisplay.gettRevenue() + "");
        tRevenue.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == calendarScroller){
            if(calendarScroller.getCalendar().get(Calendar.MONTH) != previous.get(Calendar.MONTH) || calendarScroller.getCalendar().get(Calendar.YEAR) != previous.get(Calendar.YEAR)){
                calendarDisplay.changeCalendar(calendarScroller.getCalendar());
                previous = (GregorianCalendar) calendarScroller.getCalendar().clone();
                setMPayable();
                setARate();
                setTRevenue();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof CalendarDisplay.CalendarLabel){
            CalendarDisplay.CalendarLabel calendarLabel = (CalendarDisplay.CalendarLabel) e.getSource();
            if(calendarLabel.isOn()){
                calendarLabelSelected.setSelected(false);
                calendarLabelSelected.repaint();
                calendarLabelSelected = calendarLabel;
                setPrimaryDetailVisible(false);
                barChartPanel.refreshGraph(Global.sales.getTaxesbyProductsModelGraph(calendarLabel.getDateTime()),0.0);
                totalRevenue.setText(calendarLabel.getTotalRevenue()+"");
                taxPayableLabel.setText(calendarLabel.getValue()+"");
                if(calendarLabel.getTotalRevenue() != 0)
                    aRateLabel.setText((calendarLabel.getValue()/calendarLabel.getTotalRevenue()) *100+"");
                else
                    aRateLabel.setText((0)+"");
                highestGrossingLabel.setText(Global.sales.getHighestGrossingonTax(calendarLabel.getDateTime()).getName());
                taxDetailsPanel.setChartsVisible(true);
                taxDetailsPanel.setDetailsVisible(true);
                east.setBorder(new EmptyBorder(20,0,0,0));
                calendarLabelSelected.setSelected(true);
                calendarLabelSelected.repaint();

            }
            else{
                taxDetailsPanel.setChartsVisible(false);
                taxDetailsPanel.setDetailsVisible(false);
                setPrimaryDetailVisible(true);
                east.setBorder(new EmptyBorder(20,25,0,0));
            }
        }
        if(e.getSource() == center || e.getSource() == padding){
            taxDetailsPanel.setChartsVisible(false);
            taxDetailsPanel.setDetailsVisible(false);
            setPrimaryDetailVisible(true);
            east.setBorder(new EmptyBorder(20,25,0,0));
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}