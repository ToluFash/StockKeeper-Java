package com.stockkeeper.Views.Nav;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.DatePicker.DateModel;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class ExpenseView extends PrimaryViewSP implements MouseListener, ActionListener, DocumentListener, AdjustmentListener {
    //Core Models
    private Entity entity;
    private User user;
    private JLabel amountLabel;
    private JLabel referenceLabel;
    private JLabel titleLabel;
    private JLabel supplierLabel;
    private JLabel staffLabel;

    public ExpenseView(Entity entity, User user)
    {
        this.entity = entity;
        this.user = user;
        setLayout(new BorderLayout());
        initTopPanels();
        initContentPanel("Expense", "Reference");
        initDetailsPanel();
        setUpTimer(Global.expenses);
        initModels((Date)datePickerTicket.getModel().getValue(),Global.expenses);
        initGraphs(Global.expenses);
        setChartViewLine(Global.expenses.getDistribution((Date) datePickerTicket.getModel().getValue()));
        displayModel();
        sortRows( ticketHeader.getSelected().getSortType() * ticketHeader.getSelected().getType(), ticketContainer);
    }

    private void initDetailsPanel(){
        //InfoBar
        JPanel infoBar = new JPanel(new FlowLayout(FlowLayout.CENTER)){

            @Override
            protected void paintComponent(Graphics g) {

                g.setColor(Global.colorScheme.getSecondaryColor());
                g.fillRoundRect(1,1,getWidth()-3, getHeight()-3, 5,5);

            }
        };
        infoBar.setPreferredSize(new Dimension(400,80));
        totalLabel = new JLabel();
        totalLabel.setFont(FontsList.getSitkaBanner(Font.PLAIN,30));
        infoBar.add(totalLabel);
        infoBar.setBorder(new EmptyBorder(15,0,15,0));

        //Details

        amountLabel = new JLabel();
        referenceLabel = new JLabel();
        titleLabel = new JLabel();
        supplierLabel = new JLabel();
        dateLabel = new JLabel();
        staffLabel = new JLabel();


        amountLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        referenceLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        titleLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        supplierLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        dateLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        staffLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));

        detailsCon = new ExpensePanel(amountLabel, referenceLabel, titleLabel,supplierLabel, dateLabel,staffLabel);
        detailsCon.setDetailsVisible(false);
        detailsCon.addMouseListener(this);
        //Nav Area
        //Loading Components
        detailsPanel.add(infoBar, BorderLayout.NORTH);
        detailsPanel.add((ExpensePanel)detailsCon, BorderLayout.CENTER);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        mouseClickedSP(e, "Expense");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        DateModel d1 = datePickerTicket.getModel();
        initModels((Date) d1.getValue(),Global.expenses);
        ticketContainer.removeAll();
        ticketContainerAlt.removeAll();
        ticketContainerHeight = 0;
        ticketContainerHeightAlt = 0;
        ticketContainer.setPreferredSize(new Dimension(0,ticketContainerHeight));
        lineGraphPanel.refreshGraph(Global.expenses.getDistribution((Date)datePanelTicket.getModel().getValue()),0);
        ticketContainerAlt.setPreferredSize(new Dimension(0,ticketContainerHeightAlt));
        lastSelected = null;
        displayModel();
        insertUpdate(new DocumentEvent() {
            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public int getLength() {
                return searchBarandViewChange.getSearchBar().getDocument().getLength();
            }

            @Override
            public Document getDocument() {
                return searchBarandViewChange.getSearchBar().getDocument();
            }

            @Override
            public EventType getType() {
                return null;
            }

            @Override
            public ElementChange getChange(Element elem) {
                return null;
            }
        });
        ticketContainer.repaint();
        ticketContainer.revalidate();
        ticketContainerAlt.repaint();
        ticketContainerAlt.revalidate();
        if (isListView()){
            setChartViewLine(Global.expenses.getDistribution((Date) datePickerTicket.getModel().getValue()));
            detailsCon.setDetailsVisible(false);
        }
        else{
            //setChartViewBar(Global.expense.getEntriesbyAmountsModelGraph((Date) datePickerTicket.getModel().getValue()));
            detailsCon.setDetailsVisible(false);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            displayModel();

        }
        catch (BadLocationException el){

        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            displayModel();

        }
        catch (BadLocationException el){

        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

    }
}


