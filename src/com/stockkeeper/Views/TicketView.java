package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.charts.GraphSheetRevamped;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicketView extends JDialog implements MouseListener {

    private String title;
    private GraphSheetRevamped barGraph;
    private String account;
    private TicketModel ticketModel;
    private Banner netTotal;
    private Banner discount;
    private Banner taxPayable;
    private JPanel north ;
    private JPanel center;
    private JPanel east;
    private JPanel south;
    private JPanel container;
    private JPanel ticketContainerAlt;
    private JPanel ticketHeaderAlt;
    private JScrollPane tableConSPaneAlt;
    private JLabel customer;
    private JLabel openNotes;
    private JLabel openTandC;
    private JLabel print;
    private JLabel export;
    private JLabel comments;
    private JLabel TandC;

    public TicketView(Frame owner, String title, String account, TicketModel ticketModel) {
        super(owner, title,true);
        this.title = title;
        this.account = account;
        setSize(new Dimension(1280, 700));
        setResizable(false);
        this.ticketModel = ticketModel;
        initTopPanels();
        initContentPanel();
        initDetailsPanel();
        initSouthArea();
        displayModel();
    }


    public void initTopPanels(){
        JPanel topPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,getWidth(),getHeight());
                g.setColor(Color.WHITE);
                //g.setColor(Global.colorScheme.getPrimaryColor());
                g.drawRect(1,0,getWidth()-3,getHeight()-2);
            }
        };
        topPanel.setPreferredSize(new Dimension(1280, 768));
        topPanel.setLayout(new BorderLayout());
        add(topPanel);
        north = new JPanel();
        north.setBorder(new EmptyBorder(30,0,0,0));
        north.setPreferredSize(new Dimension(1280, 175));
        north.setOpaque(false);
        center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        south.setPreferredSize(new Dimension(1280,187));

        south.setOpaque(false);
        east = new JPanel(new BorderLayout());
        //topPanel.add(north, BorderLayout.NORTH);
        topPanel.add(center, BorderLayout.CENTER);
        topPanel.add(east, BorderLayout.EAST);
        topPanel.add(south, BorderLayout.SOUTH);
    }

    public void initBanners(){

    }



    private void initContentPanel(){
        center.setPreferredSize(new Dimension(880, 484));

        //Init
        container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setPreferredSize(new Dimension(880, 484));
        container.setBorder(new EmptyBorder(30,50,0,50));
        container.setOpaque(false);
        //container.setBackground(Global.colorScheme.getQuaternaryColor());
        center.add(container, BorderLayout.CENTER);

        ticketHeaderAlt = new ProductsHeaderSaleView();
        ticketHeaderAlt.addMouseListener(this);
        ticketContainerAlt = new JPanel(){
            @Override
            public Component add(Component comp) {
                setPreferredSize(new Dimension(790, (int)getPreferredSize().getHeight()+37));
                return super.add(comp);
            }
        };
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        ticketContainerAlt.setLayout(flowLayout);
        ticketContainerAlt.setPreferredSize(new Dimension(790, 0));
        ticketContainerAlt.setBackground(Color.WHITE);
        ticketContainerAlt.setFocusable(true);
        ticketContainerAlt.addMouseListener(this);


        tableConSPaneAlt = new JScrollPane(ticketContainerAlt,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPaneAlt.setOpaque(false);
        tableConSPaneAlt.setPreferredSize(new Dimension(790, 480));
        Helper.setVerticalScrollBarColor(tableConSPaneAlt, Global.colorScheme.getNonaryColor());
        tableConSPaneAlt.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPaneAlt.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        tableConSPaneAlt.setBorder(BorderFactory.createEmptyBorder());
        container.add(tableConSPaneAlt, BorderLayout.CENTER);
        container.add(ticketHeaderAlt, BorderLayout.NORTH);
    }

    private void displayModel(){
        for(Entry entry: ticketModel.getEntries()){
                ProductsModelRowSale ticketModelRow1 = new ProductsModelRowSale(entry);
                ticketModelRow1.addMouseListener(this);
                ticketContainerAlt.add(ticketModelRow1);
        }
        ticketContainerAlt.revalidate();
        ticketContainerAlt.repaint();

    }

    private void initDetailsPanel(){
        east.setPreferredSize(new Dimension(400,768));
        east.setOpaque(false);
        JPanel detailsArea = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailsArea.setOpaque(false);
        detailsArea.setPreferredSize(new Dimension(400,768));
        detailsArea.setBorder(new EmptyBorder(30,0,0,0));




        //Banners
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(70);
        north.setLayout(flowLayout);
        double netTotalF = 0.0;
        double discountF = 0.0;
        double taxF = 0.0;

        for(Entry entry: ticketModel.getEntries()){
            netTotalF += entry.getTotal().getGrossTotal();
            discountF += entry.getTotal().getDiscountTotal();
            taxF += entry.getTotal().getTaxTotal();
        }
        netTotal = new Banner();
        netTotal.setBackground(new Color(0x2EE8FB));
        netTotal.setTitle("NET TOTAL");
        netTotal.setBannerText(netTotalF+"");
        discount = new Banner();
        discount.setBackground(new Color(0x2EE244));
        discount.setTitle("DISCOUNT");
        discount.setBannerText(discountF+"");
        taxPayable = new Banner();
        taxPayable.setBackground(new Color(0xD62F32));
        taxPayable.setTitle("TAX");
        taxPayable.setBannerText(taxF+"");
        detailsArea.add(netTotal);
        detailsArea.add(discount);
        detailsArea.add(taxPayable);





        barGraph = new GraphSheetRevamped(ticketModel.getGraphData(),0.0);
        barGraph.setBackground(Color.WHITE);
        barGraph.getjScrollPane().setBackground(Color.WHITE);
        east.add(detailsArea);
        JPanel graphPanel = new JPanel();
        graphPanel.setPreferredSize(new Dimension(380,300));
        graphPanel.setOpaque(false);
        detailsArea.add(graphPanel);
        graphPanel.add(barGraph.getjScrollPane());

        JPanel otherDetails = new JPanel(new FlowLayout(FlowLayout.CENTER));
        otherDetails.setPreferredSize(new Dimension(350,100));
        otherDetails.setOpaque(false);
        customer = new JLabel(ticketModel.getCustomer());
        customer.setPreferredSize(new Dimension(350,40));
        openNotes = new JLabel("Comments"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0xEF8F3E));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        openNotes.setHorizontalAlignment(JLabel.CENTER);
        openNotes.setPreferredSize(new Dimension(100,30));
        openTandC = new JLabel("Terms/Conditions"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0x46C1E4));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        openTandC.setHorizontalAlignment(JLabel.CENTER);
        openTandC.setPreferredSize(new Dimension(120,30));
        //otherDetails.add(customer);
        otherDetails.add(openNotes);
        otherDetails.add(openTandC);
        detailsArea.add(otherDetails);
    }

    private void initSouthArea(){
        south.setBorder(new EmptyBorder(100,0,0,30));

        JPanel pEArea = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pEArea.setPreferredSize(new Dimension(1200,50));
        pEArea.setOpaque(false);
        comments = new JLabel("Comments"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0xEF8F3E));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        comments.setHorizontalAlignment(JLabel.CENTER);
        comments.setPreferredSize(new Dimension(150,30));
        TandC = new JLabel("Terms/Conditions"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0x46C1E4));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        TandC.setHorizontalAlignment(JLabel.CENTER);
        TandC.setPreferredSize(new Dimension(150,30));
        print = new JLabel("Print"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0xEF313E));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        print.setHorizontalAlignment(JLabel.CENTER);
        print.setPreferredSize(new Dimension(150,30));
        export = new JLabel("Export"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0x46C168));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),8,8);
                super.paintComponent(g);
            }
        };
        export.setHorizontalAlignment(JLabel.CENTER);
        export.setPreferredSize(new Dimension(150,30));
        pEArea.add(comments);
        pEArea.add(export);
        pEArea.add(print);
        pEArea.add(TandC);
        south.add(pEArea);

        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(1280, 30));
        footer.setBackground(Global.colorScheme.getQuaternaryColor());
        south.add(footer);



    }


    @Override
    public void mouseClicked(MouseEvent e) {

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
