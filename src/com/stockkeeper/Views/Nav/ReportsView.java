package com.stockkeeper.Views.Nav;
/*
import com.stockkeeper.Controller.API;
import com.stockkeeper.DatePicker.JDatePanel;
import com.stockkeeper.DatePicker.JDatePicker;
import com.stockkeeper.DatePicker.UtilDateModel;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.customexceptions.NoUserFoundException;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.stock.Stock;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.EventRow;
import com.stockkeeper.Views.SearchBarAndViewPanel;
import com.stockkeeper.Views.charts.LineGraphPanel;
import com.stockkeeper.Views.charts.VerticalBarChartPanel;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class ReportsView extends JPanel implements MouseListener, ActionListener {
    //Core Models
    private Entity entity;
    private User user;
    private Stock stock;

    //Top Panels


    private JPanel contentPanel;
    private JPanel detailsPanel;

    //StockItems
    private JPanel stockContainer;
    private SearchBarAndViewPanel searchBarandViewChange;
    private DetailsPanel detailsCon;

    //Graphs
    private HashMap<String, Double> graphData;
    private LineGraphPanel lineGraphPanel;
    private VerticalBarChartPanel barChartPanel;


    //Details
    private  JLabel totalLabel;
    private  JLabel productLabel;
    private  JLabel stockLevelLabel;
    private  JLabel priceLabel;
    private  JPanel salesPanel;


    //Layouts
    private FlowLayout flowLayout;
    private FlowLayout flowLayout2;
    private FlowLayout flowLayoutNav;

    public ReportsView(Entity entity, User user)
    {

        this.entity = entity;
        this.user = user;
        setLayout(new BorderLayout());
        this.graphData = new HashMap();

        setUpLayout();
        initTopPanels();
        initContentPanel();


        setUpTimer();
    }

    private  void setUpLayout(){
        flowLayout = new FlowLayout();
        flowLayout2 = new FlowLayout();
        flowLayoutNav = new FlowLayout();
        flowLayoutNav.setHgap(15);
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout2.setAlignment(FlowLayout.RIGHT);
        flowLayoutNav.setAlignment(FlowLayout.LEFT);
    }



    private void getStock(){
        try{

            stock = API.getStock(entity, user);
            Global.products = API.getProducts(entity,user);
            initGraphData();
            initGraphs();


        }
        catch (NoUserFoundException e){
            e.printStackTrace();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){
            e.printStackTrace();

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    private void initGraphData(){
        graphData.clear();
        for (Entry entry : stock.getTickets()){
            String key;
            double value;
            key = entry.getProduct().getName();
            value = entry.getQty();
            graphData.put(key, value);
        }
    }
    private void initGraphs(){
        lineGraphPanel = new LineGraphPanel(graphData,0.0);
        barChartPanel = new VerticalBarChartPanel(graphData,0.0);

    }

    private void refreshGraphs(){
        lineGraphPanel.refreshGraph(graphData,0.0);
    }

    private void initTopPanels(){

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
    private void initContentPanel(){

        //Init
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Global.colorScheme.getQuaternaryColor());



        //Init Header Bar With Search and View Change Components
        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setPreferredSize(new Dimension(1000,60));
        headerBar.setBorder(new EmptyBorder(0,20,0,0));
        headerBar.setBackground(Global.colorScheme.getQuaternaryColor());

        JLabel accountTitle = new JLabel("Inventory");
        accountTitle.setFont(FontsList.getSitkaBanner(Font.PLAIN, 20));
        searchBarandViewChange = new SearchBarAndViewPanel();
        headerBar.add(accountTitle, BorderLayout.CENTER);
        headerBar.add(searchBarandViewChange, BorderLayout.EAST);
        container.add(headerBar, BorderLayout.NORTH);


        // Init TableContainer
        JPanel tableCon = new JPanel(new BorderLayout());

        // Init tableCOn
        tableCon.setPreferredSize(new Dimension(600, 600));
        tableCon.setBorder(new EmptyBorder(0,80,0,80));
        tableCon.setBackground(Global.colorScheme.getQuaternaryColor());
        container.add(tableCon, BorderLayout.CENTER);


        //Init Stock Container
        stockContainer = new JPanel();
        stockContainer.setPreferredSize(new Dimension(600, 0));
        stockContainer.setBackground(Global.colorScheme.getQuaternaryColor());
        stockContainer.setBorder(new EmptyBorder(0,0,0,0));


        //Init Stock Container ScrollPane
        JScrollPane tableConSPane = new JScrollPane(stockContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPane.setPreferredSize(new Dimension(600, 320));
        setVerticalScrollBarColor(tableConSPane, Global.colorScheme.getNonaryColor());
        tableConSPane.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPane.setBorder(new EmptyBorder(0,0,0,0));


        //Init tableCon Padding
        JPanel padding = new JPanel();
        padding.setBackground(Global.colorScheme.getQuaternaryColor());
        padding.setPreferredSize(new Dimension(600,100));



        tableCon.add(new StockHeader(), BorderLayout.NORTH);
        tableCon.add(tableConSPane, BorderLayout.CENTER);
        tableCon.add(padding, BorderLayout.SOUTH);

        contentPanel.add(container, BorderLayout.CENTER);

    }

    private void populateStockContainer(){
        stockContainer.removeAll();
        stockContainer.setPreferredSize(new Dimension(stockContainer.getWidth(), 0));
        ArrayList<Entry> tickets = stock.getTickets();
        int height = 0;

        for (Entry entry : tickets){

            Product product = entry.getProduct();
            double level = entry.getQty();
            double price = entry.getProduct().getUnitCost();
            double total = level * price;
            StockRow stockRow = new StockRow(entry);
            stockRow.addMouseListener(this);
            stockContainer.add(stockRow);
            height += 46;
        }

        stockContainer.setPreferredSize(new Dimension(stockContainer.getWidth(), height));
        stockContainer.revalidate();
        stockContainer.repaint();
    }


    private void setUpTimer(){

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {


                refreshGraphs();
            }
        };
        timer.schedule(timerTask,600000,5000);




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
        totalLabel = new JLabel("$100,000,000");
        totalLabel.setFont(FontsList.getSitkaBanner(Font.PLAIN,23));
        infoBar.add(totalLabel);
        infoBar.setBorder(new EmptyBorder(15,0,15,0));

        //Details

        productLabel = new JLabel("Heineken");
        stockLevelLabel = new JLabel("300");
        priceLabel = new JLabel("$350");
        productLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        stockLevelLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        priceLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        UtilDateModel model = new UtilDateModel();
        JDatePanel datePanel = new JDatePanel(model);
        JDatePicker datePicker = new JDatePicker(datePanel);
        datePicker.setPreferredSize(new Dimension(120,25));
        datePicker.addActionListener(this);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanel datePanel2 = new JDatePanel(model2);
        JDatePicker datePicker2 = new JDatePicker(datePanel2);
        datePicker2.setPreferredSize(new Dimension(120,25));
        datePicker.addActionListener(this);
        salesPanel = new OneByTwoRow(datePicker,datePicker2);
        salesPanel.setPreferredSize(new Dimension(300,40));
        salesPanel.add(datePicker);
        salesPanel.add(datePicker2);

        detailsCon = new DetailsPanel(productLabel,stockLevelLabel,priceLabel,salesPanel);
        //Nav Area
        //Loading Components
        detailsPanel.add(infoBar, BorderLayout.NORTH);
        detailsPanel.add(detailsCon, BorderLayout.CENTER);


        //Graph
        try{

            detailsCon.getGraphPanel().add(lineGraphPanel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setVerticalScrollBarColor(JScrollPane jScrollPane, Color color){
        jScrollPane.getVerticalScrollBar().setUI(
                new BasicScrollBarUI(){


                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        ImageIcon imageIcon;

                        switch(orientation){
                            case SwingConstants.SOUTH: imageIcon = Global.colorScheme.getSbButtonDown();
                            case SwingConstants.NORTH: imageIcon = Global.colorScheme.getSbButtonUp();
                            case SwingConstants.EAST: imageIcon = Global.colorScheme.getSbButtonRight();
                            default: imageIcon = Global.colorScheme.getSbButtonDown();
                        }


                        return new JButton(imageIcon)
                        {
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension(15, 15);
                            }

                            @Override
                            public Color getBackground() {
                                return Global.colorScheme.getQuinaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getQuinaryColor());
                                } else if (getModel().isRollover()) {
                                    g.setColor(Global.colorScheme.getSenaryColor());
                                } else {
                                    g.setColor(getBackground());
                                }
                                g.fillRect(0, 0, getWidth()-1, getHeight());
                                g.drawImage(Global.colorScheme.getSbButtonDown().getImage(), 6,6,null);
                            }
                        };
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        ImageIcon imageIcon;

                        switch(orientation){
                            case SwingConstants.SOUTH: imageIcon = Global.colorScheme.getSbButtonDown();
                            case SwingConstants.NORTH: imageIcon = Global.colorScheme.getSbButtonUp();
                            case SwingConstants.EAST: imageIcon = Global.colorScheme.getSbButtonRight();
                            default: imageIcon = Global.colorScheme.getSbButtonUp();
                        }


                        return new JButton(imageIcon)
                        {
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension(15, 15);
                            }

                            @Override
                            public Color getBackground() {
                                return Global.colorScheme.getQuinaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getQuinaryColor());
                                } else if (getModel().isRollover()) {
                                    g.setColor(Global.colorScheme.getSenaryColor());
                                } else {
                                    g.setColor(getBackground());
                                }
                                g.fillRect(0, 0, getWidth()-1, getHeight());
                                g.drawImage(Global.colorScheme.getSbButtonUp().getImage(), 6,6,null);
                            }
                        };
                    }
                });


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof  StockHeaderLabel){
            StockRow stockRow = (StockRow) ((StockHeaderLabel)e.getSource()).getParent();
            productLabel.setText(stockRow.getStockItem().getProduct().getName());
            stockLevelLabel.setText(stockRow.getStockItem().getQty() +"");
            priceLabel.setText("$"+ stockRow.getStockItem().getProduct().getUnitCost());
            Global.eventPanel.addtoPending(new EventRow.EventTest());
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}


*/