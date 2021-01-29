package com.stockkeeper.Views.Nav;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.DatePicker.JDatePanel;
import com.stockkeeper.DatePicker.JDatePicker;
import com.stockkeeper.DatePicker.UtilDateModel;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.account.Account;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.SearchBarAndViewPanel;
import com.stockkeeper.Views.Selectable;
import com.stockkeeper.Views.TicketDisplayItem;
import com.stockkeeper.Views.charts.LineGraphPanel;
import com.stockkeeper.Views.charts.VerticalBarChartPanel;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class PrimaryView extends JPanel implements MouseListener, ActionListener, DocumentListener, AdjustmentListener {


    //Core Models
    protected Entity entity;
    protected User user;
    protected ArrayList<TicketModel> ticketModel1;
    protected ArrayList<TicketModel> ticketModel2;
    protected ArrayList<ProductsModel> productsModel1;
    protected ArrayList<ProductsModel> productsModel2;
    //Top Panels
    protected JPanel contentPanel;
    protected JPanel detailsPanel;
    protected JPanel container;
    protected AddNew addButton;
    protected JLabel leftArrow = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/dateArrowLeft.png")));
    protected JLabel rightArrow = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/dateArrowRight.png")));
    protected Details detailsCon;
    protected JPanel tableCon;
    protected JScrollPane tableConSPane;
    protected JScrollPane tableConSPaneAlt;
    protected TicketModelHeader ticketHeader;
    protected ProductsHeader ticketHeaderAlt;
    protected SearchBarAndViewPanel searchBarandViewChange;

    //Selectables
    protected Selectable selected;
    protected Selectable lastSelected;

    //Details

    protected  JLabel totalLabel;
    protected  JLabel productLabel;
    protected  JLabel qtyLabel;
    protected  JLabel ticketIDLabel;
    protected  JLabel ticketEntrySizeLabel;
    protected  JLabel customerLabel;
    protected  JLabel dateLabel;

    //StockItems
    protected JPanel ticketContainer;
    protected int ticketContainerHeight = 0;
    protected int ticketContainerHeightAlt = 0;
    protected JPanel ticketContainerAlt;

    //Graphs
    protected LineGraphPanel lineGraphPanel;
    protected VerticalBarChartPanel barChartPanel;

    protected UtilDateModel modelTicketsDate;
    protected JDatePanel datePanelTicket;
    protected JDatePicker datePickerTicket;

    //Totals

    protected double grossTotal = 0;
    protected double grossTotalAlt = 0;




    protected void initTopPanels(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Global.colorScheme.getQuaternaryColor());
        contentPanel.setPreferredSize(new Dimension(320,710));
        detailsPanel = new JPanel(){
            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Global.colorScheme.getDenaryColor());
                g.drawRect(0,0,getWidth(),getHeight());
            }
        };
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setPreferredSize(new Dimension(400,710));

        add(contentPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.EAST);
    }

    protected void initContentPanel(String title, String customer){
        //Init
        container = new JPanel(new BorderLayout());
        container.setBackground(Global.colorScheme.getQuaternaryColor());
        initHeaderSearchViewPanel(container, title);
        initItemsView(container, customer);

        //
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setPreferredSize(new Dimension(600,100));
        buttonPanel.setBackground(Global.colorScheme.getQuaternaryColor());
        Insets insets = buttonPanel.getInsets();
        addButton = new AddNew();
        buttonPanel.add(addButton);
        addButton.addMouseListener(this);
        addButton.setBounds(800 + insets.left, 5+insets.top, addButton.getWidth(),addButton.getHeight());
        container.add(buttonPanel, BorderLayout.SOUTH);
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        left.add(leftArrow);
        right.add(rightArrow);
        left.setBackground(Global.colorScheme.getQuaternaryColor());
        right.setBackground(Global.colorScheme.getQuaternaryColor());
        leftArrow.addMouseListener(this);
        rightArrow.addMouseListener(this);
        container.add(left, BorderLayout.WEST);
        container.add(right, BorderLayout.EAST);

        contentPanel.add(container, BorderLayout.CENTER);

    }

    private void initDateBar(JPanel panel, Account account){

        modelTicketsDate = new UtilDateModel();

        GregorianCalendar g = new GregorianCalendar();
        modelTicketsDate.setDate(g.get(Calendar.YEAR),g.get(Calendar.MONTH),g.get(Calendar.DAY_OF_MONTH));
        modelTicketsDate.setSelected(true);
        datePanelTicket = new JDatePanel(modelTicketsDate);
        datePickerTicket = new JDatePicker(datePanelTicket);
        datePickerTicket.setPreferredSize(new Dimension(120,25));
        datePickerTicket.addActionListener(this);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setBackground(Global.colorScheme.getQuaternaryColor());
        panel1.setBorder(new EmptyBorder(12,10,0,0));
        panel1.add(datePickerTicket);
        panel.add(panel1);
        initModels(g.getTime(), account);

    }
    protected void initGraphs(Account account){
        lineGraphPanel = new LineGraphPanel(account.getDistribution((Date)datePickerTicket.getModel().getValue()), 0);
        barChartPanel = new VerticalBarChartPanel(new HashMap<>(), 0.0);
    }

    protected void refreshGraphs(HashMap<Date,Double> graphData){
        lineGraphPanel.refreshGraph(graphData,0);
    }

    protected void refreshGraphBar(HashMap<String,Double> graphData, double buffer){
        barChartPanel.refreshGraph(graphData,0.0);
    }


    protected void initModels(Date date, Account account){
        ticketModel1 = account.getTickets(date);
        ticketModel2 = (ArrayList<TicketModel>) ticketModel1.clone();
        productsModel1 = account.getEntriesbyProductsModel(date);
        productsModel2= (ArrayList<ProductsModel>) productsModel1.clone();
    }


    protected void initItemsView(JPanel container, String customer){
        // Init TableContainer
        tableCon = new JPanel(new BorderLayout());

        // Init tableCOn
        tableCon.setPreferredSize(new Dimension(600, 600));
        tableCon.setBorder(new EmptyBorder(0,80,0,80));
        tableCon.setBackground(Global.colorScheme.getQuaternaryColor());
        container.add(tableCon, BorderLayout.CENTER);

        //Init Stock Container
        ticketHeader = new TicketModelHeader();
        ticketHeader.getCustomer().setText(customer);
        ticketHeader.addMouseListener(this);
        ticketContainer = new JPanel();
        ticketContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        ticketContainer.setPreferredSize(new Dimension(600, 0));
        ticketContainer.setBackground(Global.colorScheme.getQuaternaryColor());
        ticketContainer.setBorder(new EmptyBorder(0,0,0,0));
        ticketContainer.setFocusable(true);
        ticketContainer.addMouseListener(this);

        ticketHeaderAlt = new ProductsHeader();
        ticketHeaderAlt.addMouseListener(this);
        ticketContainerAlt = new JPanel();
        ticketContainerAlt.setLayout(new FlowLayout(FlowLayout.LEFT));
        ticketContainerAlt.setPreferredSize(new Dimension(600, 0));
        ticketContainerAlt.setBackground(Global.colorScheme.getQuaternaryColor());
        ticketContainerAlt.setBorder(new EmptyBorder(0,0,0,0));
        ticketContainerAlt.setFocusable(true);
        ticketContainerAlt.addMouseListener(this);

        //Init Ticket Container ScrollPane
        tableConSPane = new JScrollPane(ticketContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPane.setPreferredSize(new Dimension(600, 320));
        Helper.setVerticalScrollBarColor(tableConSPane, Global.colorScheme.getNonaryColor());
        tableConSPane.getVerticalScrollBar().setPreferredSize(new Dimension(10,0));
        tableConSPane.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPane.setBorder(new EmptyBorder(0,0,0,0));
        tableConSPane.getVerticalScrollBar().addAdjustmentListener(this);

        tableConSPaneAlt = new JScrollPane(ticketContainerAlt,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPaneAlt.setPreferredSize(new Dimension(600, 320));
        Helper.setVerticalScrollBarColor(tableConSPaneAlt, Global.colorScheme.getNonaryColor());
        tableConSPaneAlt.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPaneAlt.setBorder(new EmptyBorder(0,0,0,0));


        //Init tableCon Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Global.colorScheme.getQuaternaryColor());
        buttonPanel.setPreferredSize(new Dimension(0,100));
        //

        tableCon.add(ticketHeader, BorderLayout.NORTH);
        tableCon.add(tableConSPane, BorderLayout.CENTER);
    }


    protected void setChartViewBar(HashMap<String, Double> graphData){
        try{
            detailsCon.getGraphPanel().remove(lineGraphPanel);
            detailsCon.getGraphPanel().add(barChartPanel);
            barChartPanel.refreshGraph(graphData, 0.0);
            detailsCon.getGraphPanel().repaint();
        }
        catch (Exception e){
            barChartPanel.refreshGraph(graphData, 0.0);
            detailsCon.getGraphPanel().add(barChartPanel);
            detailsCon.getGraphPanel().repaint();
        }
    }

    protected void setChartViewLine(HashMap<Date, Double> hashMap){
        try{
            detailsCon.getGraphPanel().remove(barChartPanel);
            lineGraphPanel.refreshGraph(hashMap, 0);
            detailsCon.getGraphPanel().add(lineGraphPanel);
            detailsCon.getGraphPanel().repaint();
            lineGraphPanel.repaint();
        }
        catch (Exception e){
            lineGraphPanel.refreshGraph(hashMap, 0);
            detailsCon.getGraphPanel().add(lineGraphPanel);
            detailsCon.getGraphPanel().repaint();
        }
    }

    protected void setUpTimer(Account account){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ticketModel1 = account.getTickets((Date)datePanelTicket.getModel().getValue());
                ticketModel2 = (ArrayList<TicketModel>) ticketModel1.clone();
                productsModel1 = Global.purchases.getEntriesbyProductsModel((Date)datePanelTicket.getModel().getValue());
                productsModel2= (ArrayList<ProductsModel>) productsModel1.clone();
                lineGraphPanel.refreshGraph(Global.purchases.getDistribution((Date)datePanelTicket.getModel().getValue()),0);
                displayModel();
                if(searchBarandViewChange.getSearchBar().getDocument().getLength() != 0){
                    insertUpdate(new DocumentEvent() {
                        @Override
                        public int getOffset() {
                            return 0;
                        }

                        @Override
                        public int getLength() {
                            return 0;
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
                }
                ticketContainer.repaint();
                ticketContainerAlt.repaint();
            }
        };
        timer.schedule(timerTask,8000,8000);
    }



    protected void initHeaderSearchViewPanel(JPanel container, String title){
        //Init Header Bar With Search and View Change Components
        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setPreferredSize(new Dimension(1000,60));
        headerBar.setBorder(new EmptyBorder(0,20,0,0));
        headerBar.setBackground(Global.colorScheme.getQuaternaryColor());

        JLabel accountTitle = new JLabel(title);
        accountTitle.setFont(FontsList.getSitkaBanner(Font.PLAIN, 20));
        searchBarandViewChange = new SearchBarAndViewPanel();
        searchBarandViewChange.addMouseListener(this);
        searchBarandViewChange.addDocumentListener(this);
        headerBar.add(accountTitle, BorderLayout.WEST);
        initDateBar(headerBar, Global.purchases);
        headerBar.add(searchBarandViewChange, BorderLayout.EAST);
        container.add(headerBar, BorderLayout.NORTH);
    }


    protected boolean isListView(){
        return ticketContainer.isVisible();
    }

    protected void setListView(){
        tableCon.remove(tableConSPaneAlt);
        tableCon.remove(ticketHeaderAlt);
        ticketContainerAlt.setVisible(false);
        tableCon.add(tableConSPane, BorderLayout.CENTER);
        tableCon.add(ticketHeader, BorderLayout.NORTH);
        ticketContainer.setVisible(true);
        if (lastSelected != null){
            if(lastSelected instanceof  TicketModelRow){
                mouseClicked(new MouseEvent(((TicketModelRow)lastSelected).getTicketID(), 5,5,5,5,5,1,false,MouseEvent.BUTTON1));

            }
            else{
                detailsCon.setDetailsVisible(false);
                setChartViewLine(Global.purchases.getDistribution((Date) datePickerTicket.getModel().getValue()));
            }
        }
        else {
            detailsCon.setDetailsVisible(false);
            setChartViewLine(Global.purchases.getDistribution((Date) datePickerTicket.getModel().getValue()));
        }
        displayModel();
        tableCon.revalidate();
        tableCon.repaint();

    }

    protected  void displayModel(){

    }

    protected void setAltView(){
        tableCon.remove(tableConSPane);
        tableCon.remove(ticketHeader);
        ticketContainer.setVisible(false);
        tableCon.add(tableConSPaneAlt, BorderLayout.CENTER);
        tableCon.add(ticketHeaderAlt, BorderLayout.NORTH);
        ticketContainerAlt.setVisible(true);

        if (lastSelected != null){
            if(lastSelected instanceof  ProductsModelRow){
                mouseClicked(new MouseEvent(((ProductsModelRow)lastSelected).getProduct(), 5,5,5,5,5,1,false,MouseEvent.BUTTON1));
            }
            else{
                detailsCon.setDetailsVisible(false);
                setChartViewBar(Global.purchases.getEntriesbyProductsModelGraph((Date) datePickerTicket.getModel().getValue()));
            }
        }
        else {
            detailsCon.setDetailsVisible(false);
            setChartViewBar(Global.purchases.getEntriesbyProductsModelGraph((Date) datePickerTicket.getModel().getValue()));
        }
        displayModel();
        tableCon.revalidate();
        tableCon.repaint();
    }

    protected void search(String pattern){
        for ( Component component : ticketContainer.getComponents()){
            TicketDisplayItem stockRow = (TicketDisplayItem) component;
            component.setVisible(true);
            boolean found = false;
            if(!check(pattern, stockRow.getTicket())){
                stockRow.setVisible(false);
            }
        }
        for ( Component component : ticketContainerAlt.getComponents()){
            ProductsModelRow productsModelRow = (ProductsModelRow) component;
            component.setVisible(true);
            boolean found = false;
            if(!check(pattern, productsModelRow.getProductsModel())){
                productsModelRow.setVisible(false);
            }
        }
    }


    protected boolean check(String pattern, TicketModel entry){
        pattern = pattern.trim().toLowerCase();
        if (pattern.length() != 0){
            boolean found = false;
            if(Helper.KMPSearch(pattern,entry.getId().trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,(entry.getTotal() +"").trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,entry.getCustomer().trim().toLowerCase()))
                found = true;

            return found;
        }
        else return  true;
    }


    protected boolean check(String pattern, ProductsModel productsModel){
        pattern = pattern.trim().toLowerCase();
        if (pattern.length() != 0){
            boolean found = false;
            if(Helper.KMPSearch(pattern,productsModel.getProduct().getName().trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,(productsModel.getSummedQty()+"").trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,(productsModel.getNetTotal()+"").trim().toLowerCase()))
                found = true;

            return found;
        }
        else return  true;
    }



    protected void sortRows(int type, JPanel container){
        switch (type){
            case 1:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getId().compareTo(stockRow.getTicket().getId()) >0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 2:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getTotal() > stockRow.getTicket().getTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 3:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getCustomer().toLowerCase().compareTo(stockRow.getTicket().getCustomer().toLowerCase()) > 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
            case 4:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getDate().compareTo(stockRow.getTicket().getDate()) > 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
            case 5:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getId().compareTo(stockRow.getTicket().getId()) < 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 6:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getTotal() < stockRow.getTicket().getTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 7:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getCustomer().toLowerCase().compareTo(stockRow.getTicket().getCustomer().toLowerCase()) < 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
            case 8:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    TicketDisplayItem stockRow = (TicketDisplayItem) container.getComponent(index);

                    while (position > 0 && ((TicketDisplayItem)container.getComponent(position-1)).getTicket().getDate().compareTo(stockRow.getTicket().getDate()) < 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
        }
        container.revalidate();
        container.repaint();
    }
    protected void sortRowsProduct(int type, JPanel container){
        switch (type){
            case 1:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = (ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getProduct().getName().compareTo(productsModelRow.getProductsModel().getProduct().getName()) >0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;

            case 2:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = ( ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getSummedQty() > productsModelRow.getProductsModel().getSummedQty()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;

            case 3:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = ( ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getNetTotal() > productsModelRow.getProductsModel().getNetTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;
            case 4:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = (ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getProduct().getName().compareTo(productsModelRow.getProductsModel().getProduct().getName()) < 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;

            case 5:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = ( ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getSummedQty() < productsModelRow.getProductsModel().getSummedQty()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;

            case 6:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    ProductsModelRow productsModelRow = ( ProductsModelRow) container.getComponent(index);

                    while (position > 0 && (( ProductsModelRow)container.getComponent(position-1)).getProductsModel().getNetTotal() < productsModelRow.getProductsModel().getNetTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(productsModelRow, position);
                }
                break;
        }
    }


    public TicketModelRowR modelRowFoundR(TicketModel ticketModelR){
        for(Component component: ticketContainer.getComponents())
        {
            TicketModelRowR ticketModelRowR = (TicketModelRowR) component;
            if(ticketModelR.getId().equals(ticketModelRowR.getTicket().getId()))
                return ticketModelRowR;
        }
        return null;
    }

    protected TicketModelRow modelRowFound(TicketModel ticketModel){
        for(Component component: ticketContainer.getComponents())
        {
            TicketModelRow ticketModelRow = (TicketModelRow) component;
            if(ticketModel.getId().equals(ticketModelRow.getTicket().getId()))
                return ticketModelRow;
        }
        return null;
    }


    protected ProductsModelRow productsModelRowFound(ProductsModel productsModel){
        for(Component component: ticketContainerAlt.getComponents())
        {
            ProductsModelRow productsModelRow = (ProductsModelRow) component;
            if(productsModel.getProduct().getId().equals(productsModelRow.getProductsModel().getProduct().getId()))
                return productsModelRow;
        }
        return null;
    }

    protected void appropriateSelection(Selectable selectable) {
        if (lastSelected != null) {
            if (!selectable.isSelected()) {
                lastSelected.turnOff();
                lastSelected.setSelected(false);
                selectable.setSelected(true);
                selectable.turnOn();
                lastSelected = selectable;
                selected = selectable;
            }
        } else {
            selectable.setSelected(true);
            selectable.turnOn();
            lastSelected = selectable;
            selected = selectable;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseClickedStockHeaderLabel(MouseEvent e) {
        if (e.getSource() == ticketHeader.getTicketID() || e.getSource() == ticketHeader.getTicketValue() ||
            e.getSource() == ticketHeader.getCustomer()|| e.getSource() == ticketHeader.getDate())
    {
        ticketHeader.mouseClicked(e);
        sortRows( (ticketHeader.getSelected().getSortType() * 4) + ticketHeader.getSelected().getType(), ticketContainer);
    }
    else {
        TicketModelRow ticketRow = (TicketModelRow) ((StockHeaderLabel) e.getSource()).getParent();
        ticketIDLabel.setText(ticketRow.getTicket().getId());
        totalLabel.setText("$" + ticketRow.getTicket().getTotal());
        customerLabel.setText(ticketRow.getTicket().getCustomer());
        dateLabel.setText(ticketRow.getTicket().getDate().getTime().toString());
        setChartViewBar(ticketRow.getTicket().getGraphData());
        detailsCon.switchView(2);
        appropriateSelection(ticketRow);
        detailsCon.setDetailsVisible(true);
    }

    }
    public void mouseClickedProductsHeaderLabel(MouseEvent e) {
        if (e.getSource() == ticketHeaderAlt.getProduct() || e.getSource() == ticketHeaderAlt.getQty() ||
                e.getSource() == ticketHeaderAlt.getNetTotal())
        {
            ticketHeaderAlt.mouseClicked(e);
            sortRowsProduct( (ticketHeaderAlt.getSelected().getSortType() * 3) + ticketHeaderAlt.getSelected().getType(), ticketContainerAlt);
        }
        else {
            ProductsModelRow productsModelRow = (ProductsModelRow) ((ProductsModelHeaderLabel) e.getSource()).getParent();
            totalLabel.setText("$" + productsModelRow.getProductsModel().getNetTotal());
            qtyLabel.setText(productsModelRow.getProductsModel().getSummedQty()+"");
            productLabel.setText(productsModelRow.getProductsModel().getProduct().getName());
            setChartViewLine(Global.purchases.getEntriesbyProductsModelDistribution(productsModelRow.getProductsModel().getProduct(), (Date)datePickerTicket.getModel().getValue()));
            detailsCon.switchView(1);
            appropriateSelection(productsModelRow);
            detailsCon.setDetailsVisible(true);
        }
    }

    public void mouseClickedTicketContainer(MouseEvent e) {
        if (lastSelected != null) {
            lastSelected.turnOff();
            lastSelected.setSelected(false);
            selected = null;
            setChartViewLine(Global.purchases.getDistribution((Date) datePickerTicket.getModel().getValue()));
        }
        detailsCon.setDetailsVisible(false);

    }

    public void mouseClickedTicketContaineAlt(MouseEvent e) {
        if (lastSelected != null) {
            lastSelected.turnOff();
            lastSelected.setSelected(false);
            selected = null;
            setChartViewBar(Global.purchases.getEntriesbyProductsModelGraph((Date) datePickerTicket.getModel().getValue()));
        }
        detailsCon.setDetailsVisible(false);

    }
    public void mouseClickedLeftArrow(MouseEvent e) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime((Date)datePickerTicket.getModel().getValue());
        g.add(Calendar.DATE, -1);
        datePickerTicket.getModel().setDate(g.get(Calendar.YEAR),g.get(Calendar.MONTH),g.get(Calendar.DATE));
        actionPerformed(new ActionEvent(this, 1, "null"));

    }

    public void mouseClickedRightArrow(MouseEvent e) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime((Date)datePickerTicket.getModel().getValue());
        g.add(Calendar.DATE, 1);
        datePickerTicket.getModel().setDate(g.get(Calendar.YEAR),g.get(Calendar.MONTH),g.get(Calendar.DATE));
        actionPerformed(new ActionEvent(this, 1, "null"));

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
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
