package com.stockkeeper.Views;

import com.stockkeeper.Controller.API;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Invoices.Invoice1;
import com.stockkeeper.Invoices.InvoicePrintable;
import com.stockkeeper.Invoices.POSInvoice;
import com.stockkeeper.Models.ItemEntry;
import com.stockkeeper.Models.EntryTotal;
import com.stockkeeper.Models.customexceptions.NoUserFoundException;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.labels.SLabel;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class TicketEntry extends JDialog implements ActionListener, MouseListener, FocusListener, KeyListener, DocumentListener{
    //Title
    private String title;

    //Entity & User
    private Entity entity;
    private User user;

    // Server Models
    private Ticket ticket;

    //Layouts
    private FlowLayout flowLayoutLeft;
    private FlowLayout flowLayoutRight;

    //Borders
    private  EmptyBorder emptyBorder1;
    private  EmptyBorder emptyBorder2;
    private  EmptyBorder emptyBorder3;
    private  EmptyBorder emptyBorder4;

    //Fonts
    private Font sitkaBanner12;
    private Font sitkaBanner10;
    private Font sitkaBanner8;

    //Global Components
    private JPanel conP;
    private JPanel entriesSection;
    private JPanel entries;
    private JPanel noEntryDisplay;
    private TicketEntryHeader entryHeader;
    private JPanel invoiceContent;
    private SLabel error;
    private JPanel entryarea;
    private JPanel productsArea;
    private Boolean isHeaderOn;
    private JButton submit;
    private JButton print;
    private JScrollPane invoiceScrollPanel;

    public TicketEntry(Frame frame, Entity entity, User user, String title){
        //init
        super(frame,title);
        JPanel contentPane = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/entryPageBG.png")).getImage(), 0, 0, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        };
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.user = user;
        this.entity =entity;
        this.title =title;
        initContainer();
        setUpTitle();
        initLayout();
        initBorders();
        initFonts();

        //Loads Global.products from Server
        initProducts();

        //Set Up Ticket Model
        setUpTicketModel();

        //Set Up Entries Area
        initEntries();

        //Invoice Section
        setUpInvoiceArea();

        //Submit Section
        setUpSubmitArea();

        //start
        setVisible(true);
    }

    public Ticket getTicket() {
        return ticket;
    }

    private void print(Object object){
        System.out.println(object);
    }

    private void setUpTicketModel(){
        LinkedList<ItemEntry> items = new LinkedList<>();
        STextArea note = new STextArea("",5,5);
        RoundedTextInput customer = new RoundedTextInput(){

        };
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(new Date());
        this.ticket = new Ticket(items, note, customer,date);
    }

    private void initProducts(){
        try{

            Global.products = API.getProducts(entity,user);
        }
        catch (MalformedURLException e){

            print(e);
        }
        catch (NoUserFoundException e){

            print(e);
        }
        catch (FileNotFoundException e){

            print(e);
        }
        catch (IOException e){

            print(e);
        }
        catch (Exception e){

            print(e);
        }


    }
    private void initContainer(){
        setIconImage(new ImageIcon(getClass().getResource("/com/stockkeeper/images/title.png")).getImage());
        setSize(1280,700);
        setResizable(false);
        this.conP = new JPanel();
        conP.setLayout(new BorderLayout());
        conP.setBorder(new LineBorder(Global.colorScheme.getPrimaryColor()));
        add(conP);
        conP.setOpaque(false);
    }


    private void initLayout(){
        this.flowLayoutLeft  = new FlowLayout(FlowLayout.LEFT);
        this.flowLayoutRight  = new FlowLayout(FlowLayout.RIGHT);
        flowLayoutLeft.setVgap(0);
    }

    private void initBorders(){


        this.emptyBorder1 = new EmptyBorder(30,100,0,20);
        this.emptyBorder2 = new EmptyBorder(0,15,0,20);
        this.emptyBorder3 = new EmptyBorder(5,10,0,20);
        this.emptyBorder4 = new EmptyBorder(5,5,0,20);


    }

    private void initFonts(){

        this.sitkaBanner12 = new Font("Sitka Banner", Font.PLAIN, 12);
        this.sitkaBanner10 = new Font("Sitka Banner", Font.PLAIN, 10);
        this.sitkaBanner8 = new Font("Sitka Banner", Font.PLAIN, 8);


    }

    private void setUpTitle(){
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(new EmptyBorder(0,20,10,0));
        titlePanel.setOpaque(false);
        this.conP.add(titlePanel, BorderLayout.NORTH);
        //         Labelling
        titlePanel.setPreferredSize(new Dimension(900, 80));
        titlePanel.setBorder(new EmptyBorder(15,90,0,0));
        titlePanel.setOpaque(false);
        JLabel titleLabel  = new JLabel("ADD SALE");
        titleLabel.setFont(FontsList.getSitkaBanner(Font.PLAIN, 24));
        titleLabel.setForeground(new Color(0x606363));
        titleLabel.setPreferredSize(new Dimension(200,50));
        titlePanel.add(titleLabel);
    }

    private void initEntries(){

        this.entriesSection  = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        this.entriesSection.setBorder(new EmptyBorder(0,100,0,0));
        this.entriesSection.setPreferredSize(new Dimension(550,550));
        //this.entriesSection.setBorder(emptyBorder1);
        entriesSection.setOpaque(false);
        this.conP.add(entriesSection, BorderLayout.CENTER);
        setUpEntriesArea();
        //Populate Entries Area
        setUpProductsArea();
        setUpDetailsArea();

    }

    private void setUpEntriesArea(){
        this.entries = new JPanel();
        entries.setOpaque(false);
        this.entries.setBorder(emptyBorder2);
        entries.setLayout(flowLayoutLeft);
        entries.setPreferredSize(new Dimension(550,450));
        JScrollPane entriesScrollPane  = new JScrollPane(entries);
        entriesScrollPane.getViewport().setOpaque(false);
        entriesScrollPane.setOpaque(false);
        setVerticalScrollBarColor(entriesScrollPane, Global.colorScheme.getSecondaryColor());
        entriesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        entriesScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        entriesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        entriesScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        entriesSection.add(entriesScrollPane, BorderLayout.CENTER);
    }

    private void setUpProductsArea(){
        productsArea = new JPanel(flowLayoutLeft);
        productsArea.setPreferredSize(new Dimension(550,150));
        productsArea.setOpaque(false);
        //productsArea.setBorder(emptyBorder3);
        entries.add(productsArea);
        //Label
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setPreferredSize(new Dimension(550,40));
        titlePanel.setBorder(new EmptyBorder(0,0,0,0));
        titlePanel.setOpaque(false);
        JLabel title = new JLabel("ENTRIES");
        title.setForeground(new Color(0x606363));
        title.setFont(FontsList.getSitkaBanner(Font.PLAIN, 16));
        title.setPreferredSize(new Dimension(80,40));
        titlePanel.add(title);
        productsArea.add(titlePanel);

        // No Entry Display
        this.noEntryDisplay = new JPanel();
        noEntryDisplay.setPreferredSize(new Dimension(550,50));
        //JLabel display = new JLabel(new ImageIcon(getClass().getResource("/com/stockkeeper/images/noEntryDisplay.png")));
        //noEntryDisplay.add(display);

        //Entry Headers
        JPanel entryHeaderPanel = new JPanel(flowLayoutLeft){
        };
        entryHeaderPanel.setOpaque(false);
        entryHeaderPanel.setPreferredSize(new Dimension(550,40));
        entryHeaderPanel.setBorder(new EmptyBorder(5,5,5,0));
        this.entryHeader = new TicketEntryHeader();
        this.entryHeader.setPreferredSize(new Dimension(550,20));
        entryHeaderPanel.add(this.entryHeader);
        productsArea.add(entryHeaderPanel, BorderLayout.NORTH);
        isHeaderOn = true;

        // First Entry
        FlowLayout flowLayoutEntryArea = new FlowLayout(FlowLayout.LEFT);
        flowLayoutEntryArea.setVgap(2);
        entryarea = new JPanel(flowLayoutEntryArea);
        entryarea.setPreferredSize(new Dimension(550,15));
        entryarea.setBorder(new EmptyBorder(5,0,10,0));
        entryarea.setOpaque(false);
        productsArea.add(entryarea, BorderLayout.CENTER);
        addProductEntry();

        //Add Button
        JPanel addNewPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addNewPanel.setOpaque(false);
        addNewPanel.setPreferredSize(new Dimension(550,30));
        addNewPanel.setBorder(new EmptyBorder(0,20,5,2));
        AddButton  addNew = new AddButton();
        addNewPanel.add(addNew);
        addNew.addMouseListener(this);
        productsArea.add(addNewPanel,BorderLayout.SOUTH);
    }


    private void setUpDetailsArea(){
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        JPanel detailsArea = new JPanel(layout);
        //detailsArea.setBorder(emptyBorder3);
        detailsArea.setPreferredSize(new Dimension(550,300));
        detailsArea.setOpaque(false);

        //Label
        JPanel titlePanel = new JPanel(flowLayoutLeft);
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(550,30));
        titlePanel.setBorder(new EmptyBorder(0,0,0,0));
        JLabel title = new JLabel("DETAILS");
        title.setFont(FontsList.getSitkaBanner(Font.BOLD, 14));
        title.setForeground(new Color(0x606363));
        titlePanel.add(title);
        detailsArea.add(titlePanel);
        //Note
        JPanel notePanel = new JPanel(flowLayoutLeft);
        notePanel.setBorder(emptyBorder4);
        notePanel.setOpaque(false);
        notePanel.setPreferredSize(new Dimension(550,185));
        JLabel label = new JLabel("Comments:"){


        };
        label.setForeground(new Color(0x606363));
        label.setPreferredSize(new Dimension(550,20));
        notePanel.add(label);
        this.ticket.getNote().addKeyListener(this);
        this.ticket.getNote().setLineWrap(true);
        this.ticket.getNote().setFont(FontsList.getHelvetica(Font.PLAIN, 12));

        JScrollPane noteScrollPane = new JScrollPane(this.ticket.getNote());
        setVerticalScrollBarColor(noteScrollPane, Global.colorScheme.getSecondaryColor());
        noteScrollPane.setPreferredSize(new Dimension(500,150));
        noteScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        noteScrollPane.setOpaque(false);
        noteScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        noteScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,5));
        notePanel.add(noteScrollPane);
        detailsArea.add(notePanel);

        //Customer
        JPanel customerPanel = new JPanel(layout);
        customerPanel.setOpaque(false);
        customerPanel.setBorder(new EmptyBorder(0,8,0,0));
        customerPanel.setPreferredSize(new Dimension(550,80));
        JLabel label2 = new JLabel("Customer:"){};
        label2.setPreferredSize(new Dimension(550,20));
        label2.setForeground(new Color(0x606363));
        customerPanel.add(label2);



        this.ticket.getCustomer().setPreferredSize(new Dimension(200,25));
        this.ticket.getCustomer().setPlaceholder("");
        this.ticket.getCustomer().addKeyListener(this);
        customerPanel.add(this.ticket.getCustomer());
        detailsArea.add(customerPanel);

        entries.add(detailsArea);

    }

    private void setUpSubmitArea(){
        JPanel submitPanel = new JPanel(flowLayoutRight);
        submitPanel.setOpaque(false);
        submitPanel.setPreferredSize(new Dimension(550,40));
        submitPanel.setBorder(new EmptyBorder(0,20,0,20));
        submit = new JButton("Save");
        submit.addActionListener(this);
        print = new JButton("Print");
        print.addActionListener(this);
        submitPanel.add(submit);
        submitPanel.add(print);
        this.conP.add(submitPanel, BorderLayout.SOUTH);
    }

    private JPanel setupProductEntry(){
        //Declares Text Fields
        ProductComboBox product;
        IntegerInput quantity;
        SwitchableTextInput discount;
        TickSwitchableTextInput tax;
        SwitchableTextInput shipping;
        //Dimensions
        Dimension productDimension = new Dimension(130, 25);
        Dimension qtyDimension = new Dimension(80, 25);
        Dimension discountDimension = new Dimension(80, 25);
        Dimension taxDimension = new Dimension(100, 25);
        Dimension shippingDimension = new Dimension(80, 25);


        //Creates Container for Text Fields and inits them
        JPanel con = new JPanel();
        con.setLayout(flowLayoutLeft);

        String[] strings = new String[Global.products.size()];
        int count = 0;
        for (Product product1: Global.products){
            strings[count] = product1.getName();
            count++;
        }
        product = new ProductComboBox(strings);
        product.setPreferredSize(productDimension);
        product.setOpaque(false);
        product.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        product.addActionListener(this);
        product.addKeyListener(this);


        quantity= new IntegerInput(qtyDimension);
        quantity.getNumberField().getDocument().addDocumentListener(this);
        quantity.getNumberField().addKeyListener(this);

        discount = new SwitchableTextInput(discountDimension, true);
        discount.getTextInput().setPlaceholder("Discount");
        discount.setPreferredSize(discountDimension);
        discount.getTextInput().setText("0");
        discount.getTextInput().getDocument().addDocumentListener(this);
        discount.getTextInput().addKeyListener(this);
        discount.getLabel().addMouseListener(this);




        shipping = new SwitchableTextInput(shippingDimension, true);
        shipping.getTextInput().setPlaceholder("Shipping");
        shipping.setPreferredSize(shippingDimension);
        shipping.getTextInput().setText("0");
        shipping.getTextInput().getDocument().addDocumentListener(this);
        shipping.getTextInput().addKeyListener(this);
        shipping.getLabel().addMouseListener(this);


        String toolTip = "Overrides default product tax and adds rate to price";
        tax = new TickSwitchableTextInput(taxDimension, true, toolTip);
        tax.getSwitchableTextInput().getTextInput().setPlaceholder("Tax");
        tax.getSwitchableTextInput().getTextInput().setText("0");
        tax.getSwitchableTextInput().getTextInput().addKeyListener(this);
        tax.getSwitchableTextInput().getLabel().addMouseListener(this);
        tax.getCheckBox().addActionListener(this);

        // Inits Object for storge of text fields nd its top Panel
        ItemEntry itemEntry = new ItemEntry(con,product,quantity,discount,shipping,tax);


        //Creates  delete button tht takes  item argument to delete t ation event
        DeleteButton deleteButton= new DeleteButton(itemEntry, Global.colorScheme.getDenaryColor());




        //adds text fields to container
        con.add(product);
        con.add(quantity);
        con.add(discount);
        con.add(shipping);
        con.add(tax);
        con.add(deleteButton);


        // adds dt to linked list

        ticket.getEntries().addLast(itemEntry);


        //adds listener to delete button
        deleteButton.addMouseListener(this);
        return con;
    }

    private void addProductEntry(){
        JPanel con = setupProductEntry();
        con.setPreferredSize(new Dimension(550,35));
        con.setBorder(new EmptyBorder(3,5,0,0));
        con.setOpaque(false);
        double entriesDimension = entries.getPreferredSize().getHeight();
        double entryAreaDimension = entryarea.getPreferredSize().getHeight();
        double productAreaDimension = productsArea.getPreferredSize().getHeight();
        entries.setPreferredSize(new Dimension(550, (int) entriesDimension + 37));
        entryarea.setPreferredSize(new Dimension(550, (int) entryAreaDimension + 37));
        productsArea.setPreferredSize(new Dimension(550, (int) productAreaDimension + 37));
        entryarea.add(con);
    }

    private void removeProductEntry(){
    }

    private void setUpInvoiceArea(){
        JPanel invoiceArea = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };
        invoiceArea.setBorder(new EmptyBorder(0,10,0,10));
        //
        JPanel invoice = new JPanel();
        invoice.setOpaque(false);
        invoice.setPreferredSize(new Dimension(520,410));
        invoice.setLayout(new BorderLayout());
        invoiceArea.add(invoice);

        //Invoice Title
        JPanel invoiceTitle = new JPanel(flowLayoutLeft);
        invoiceTitle.setBackground(Color.LIGHT_GRAY);
        invoiceTitle.setBorder(new EmptyBorder(5,10,0,0));
        invoiceTitle.setPreferredSize(new Dimension(500,30));
        JLabel invoiceLabel = new JLabel("Invoice");
        invoiceLabel.setFont(new Font("Agency FB", Font.PLAIN,20));
        invoiceLabel.setForeground(Color.WHITE);
        invoiceTitle.add(invoiceLabel);
        invoice.add(invoiceTitle, BorderLayout.NORTH);

        //Invoice Content & Header
        this.invoiceContent = new JPanel();
        invoiceScrollPanel = new JScrollPane(invoiceContent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        invoiceScrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        invoiceScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        setVerticalScrollBarColor(invoiceScrollPanel, Global.colorScheme.getSenaryColor());
        invoiceScrollPanel.setPreferredSize(new Dimension(500,380));
        invoiceScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        invoiceScrollPanel.setOpaque(true);
        invoiceScrollPanel.getViewport().setOpaque(true);

        //  init
        invoiceContent.setBackground(Color.WHITE);
        invoiceContent.setBorder(new EmptyBorder(5,5,5,5));
        invoice.add(invoiceScrollPanel, BorderLayout.CENTER);
        updateInvoice();
        //Initili
        this.conP.add(invoiceArea, BorderLayout.EAST);


    }
    private void updateInvoice(){
        System.out.println(invoiceContent.getPreferredSize());
        System.out.println(invoiceScrollPanel.getSize());
        Font font = new Font("Sitka Banner", Font.PLAIN,12);
        EmptyBorder emptyBorder = new EmptyBorder(0,4,0,0);
        Dimension snDimension = new Dimension(30,13);
        Dimension itemDimension = new Dimension(60,13);
        Dimension qtyDimension = new Dimension(60,13);
        Dimension priceDimension = new Dimension(60,13);
        Dimension discountDimension = new Dimension(60,13);
        Dimension shippingDimension = new Dimension(60,13);
        Dimension taxDimension = new Dimension(60,13);
        Dimension totalDimension = new Dimension(60,13);

        //restart section
        invoiceContent.removeAll();
        int height = 20;
        JPanel header = new JPanel(flowLayoutLeft);
        header.setBorder(new EmptyBorder(0,4,0,0));
        header.setPreferredSize(new Dimension(500,15));
        header.setOpaque(false);
        JLabel sn0 = new JLabel("S/N");
        JLabel item0 =new JLabel("ITEM");
        JLabel qty0 =new JLabel("QTY");
        JLabel price0 =new JLabel("PRICE");
        JLabel discount0 =new JLabel("DISCOUNT");
        JLabel shipping0 =new JLabel("SHIPPING");
        JLabel tax0 =new JLabel("TAX");
        JLabel total0 =new JLabel("TOTAL");
        //Font
        sn0.setFont(font);
        item0.setFont(font);
        qty0.setFont(font);
        price0.setFont(font);
        discount0.setFont(font);
        shipping0.setFont(font);
        tax0.setFont(font);
        total0.setFont(font);
        //Dimension
        sn0.setPreferredSize(snDimension);
        item0.setPreferredSize(itemDimension);
        qty0.setPreferredSize(qtyDimension);
        price0.setPreferredSize(priceDimension);
        discount0.setPreferredSize(discountDimension);
        shipping0.setPreferredSize(shippingDimension);
        tax0.setPreferredSize(taxDimension);
        total0.setPreferredSize(totalDimension);
        //Loading
        header.add(sn0);
        header.add(item0);
        header.add(qty0);
        header.add(price0);
        header.add(discount0);
        header.add(shipping0);
        header.add(tax0);
        header.add(total0);
        invoiceContent.add(header);

        //Populate Invoice
        int count = 1;
        for (ItemEntry itemEntry: ticket.getEntries())
        {
            JPanel content = new JPanel(flowLayoutLeft);
            content.setPreferredSize(new Dimension(500,15));
            content.setBorder(emptyBorder);
            content.setOpaque(false);
            JLabel sn = new JLabel(count + "");
            JLabel item = new JLabel(itemEntry.getProduct().getSelectedItem().toString());
            JLabel qty = new JLabel(itemEntry.getQty().getNumberField().getText());

            //get Entry Total and core details
            EntryTotal entryTotal = calculateEntryTotal(itemEntry);
            String discountNote  = entryTotal.isRateDiscount()? " @ % rate": "flat";
            String shippingNote = entryTotal.isRateShipping()? " @ % rate": "flat";
            String taxNote= entryTotal.isRateTax()? " @ % rate": "flat";


            JLabel price = new JLabel(Global.products.get(itemEntry.getProduct().getSelectedIndex()).getUnitCost().toString());



            JLabel discount = new JLabel(entryTotal.getDiscountTotal() + discountNote);
            JLabel shipping = new JLabel(entryTotal.getShippingTotal() +shippingNote);
            JLabel tax = new JLabel(entryTotal.getTaxTotal() +taxNote);
            JLabel total = new JLabel(entryTotal.getGrossTotal() +"");


            sn.setFont(font);
            item.setFont(font);
            qty.setFont(font);
            price.setFont(font);
            discount.setFont(font);
            shipping.setFont(font);
            tax.setFont(font);
            total.setFont(font);

            sn.setPreferredSize(snDimension);
            item.setPreferredSize(itemDimension);
            qty.setPreferredSize(qtyDimension);
            price.setPreferredSize(priceDimension);
            discount.setPreferredSize(discountDimension);
            shipping.setPreferredSize(shippingDimension);
            tax.setPreferredSize(taxDimension);
            total.setPreferredSize(totalDimension);

            content.add(sn);
            content.add(item);
            content.add(qty);
            content.add(price);
            content.add(discount);
            content.add(shipping);
            content.add(tax);
            content.add(total);
            invoiceContent.add(content);
            height += 20;
            count++;
        }

        invoiceContent.setPreferredSize(new Dimension(500,height+2));
        invoiceContent.revalidate();
        invoiceContent.repaint();

    }
    private EntryTotal calculateEntryTotal(ItemEntry itemEntry){
        double qty ;
        double price;
        double defaultTaxRate;
        double defaultTax;
        double defaultTaxMultiplier ;
        boolean defaultTaxisPercent ;
        double discount;
        double discountMultiplier;
        double shipping;
        double shippingMultiplier ;
        double overrideTax;
        double overrideTaxMultiplier;
        Product product;


        try{
            product = Global.products.get(itemEntry.getProduct().getSelectedIndex());
            qty = (itemEntry.getQty().getNumberField().getText().length() > 0) ? Double.parseDouble(itemEntry.getQty().getNumberField().getText()): 0;
            price = product.getUnitCost();
            defaultTaxRate = product.getTaxRate();
            defaultTax = product.getTaxRate();
            defaultTaxMultiplier = (1+(defaultTax/100));
            defaultTaxisPercent = product.isPercent();
            discount = (itemEntry.getDiscount().getTextInput().getText().length() == 0) ? 0: Double.parseDouble(itemEntry.getDiscount().getTextInput().getText());
            discountMultiplier = (1-(discount/100));
            shipping = (itemEntry.getShipping().getTextInput().getText().length() == 0) ? 0: Double.parseDouble(itemEntry.getShipping().getTextInput().getText());
            shippingMultiplier = (1+(shipping/100));
            overrideTax = (itemEntry.getTax().getSwitchableTextInput().getTextInput().getText().length() == 0) ? 0: Double.parseDouble(itemEntry.getTax().getSwitchableTextInput().getTextInput().getText());
            overrideTaxMultiplier = (1+(overrideTax/100));

        }
        catch (Exception e)
        {

            product = Global.products.get(itemEntry.getProduct().getSelectedIndex());
            qty = 0;
            price =0;
            defaultTax = 0;
            defaultTaxMultiplier = 0;
            defaultTaxisPercent =false;
            discount = 0;
            discountMultiplier = 0;
            shipping =0;
            shippingMultiplier =0;
            overrideTax = 0;
            overrideTaxMultiplier = 0;

        }
        EntryTotal entryTotal = new EntryTotal();



        if (itemEntry.getTax().getCheckBox().isSelected())
        {
            if(itemEntry.getDiscount().isPercent)
                if (itemEntry.getShipping().isPercent){
                    entryTotal.setGrossTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? overrideTaxMultiplier * ( shippingMultiplier * (price * qty * discountMultiplier )): (shippingMultiplier * (price * qty * discountMultiplier )) + overrideTax);
                    entryTotal.setDiscountTotal((discount/100)  * price * qty );
                    entryTotal.setShippingTotal((shipping/100) * price * qty * discountMultiplier);
                    entryTotal.setTaxTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? (overrideTax/100) * ( shippingMultiplier * (price * qty * discountMultiplier )): overrideTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(itemEntry.getTax().getSwitchableTextInput().isPercent);
                    entryTotal.setDefaultTax(false);
                }
                else{
                    entryTotal.setGrossTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ?  overrideTaxMultiplier *( shipping + (price * qty * discountMultiplier )): overrideTax + (price * qty * discount ) + shipping);
                    entryTotal.setDiscountTotal((discount/100)  * price * qty );
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? (overrideTax/100) * ( shipping + (price * qty * discountMultiplier )): overrideTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(itemEntry.getTax().getSwitchableTextInput().isPercent);
                    entryTotal.setDefaultTax(false);
                }

            else
            {
                if (itemEntry.getShipping().isPercent){
                    entryTotal.setGrossTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? overrideTaxMultiplier * ( shippingMultiplier * (price * qty - discount )): (shippingMultiplier * (price * qty - discount )) + overrideTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal((shipping/100) * (price * qty - discount));
                    entryTotal.setTaxTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? (overrideTax/100) * ( shippingMultiplier * (price * qty - discount)): overrideTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(itemEntry.getTax().getSwitchableTextInput().isPercent);
                    entryTotal.setDefaultTax(false);
                }
                else{
                    entryTotal.setGrossTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? shipping +(overrideTaxMultiplier * (price * qty - discount)): shipping + (price * qty - discount ) + overrideTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(itemEntry.getTax().getSwitchableTextInput().isPercent ? (overrideTax/100) * ( shipping + (price * qty - discount)): overrideTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(itemEntry.getTax().getSwitchableTextInput().isPercent);
                    entryTotal.setDefaultTax(false);
                }

            }
        }

        else{

            if(itemEntry.getDiscount().isPercent)
                if (itemEntry.getShipping().isPercent){
                    entryTotal.setGrossTotal(product.isPercent()? defaultTaxMultiplier *
                            ( shippingMultiplier * (price * qty * discountMultiplier )): (shippingMultiplier * (price * qty * discountMultiplier )) + defaultTax );
                    entryTotal.setDiscountTotal((discount/100)  * price * qty );
                    entryTotal.setShippingTotal((shipping/100) * price * qty * discountMultiplier);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shippingMultiplier * (price * qty * discountMultiplier )): defaultTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(Global.products.get(itemEntry.getProduct().getSelectedIndex()).isPercent());
                    entryTotal.setDefaultTax(true);
                }
                else{
                    entryTotal.setGrossTotal((product.isPercent()) ?  defaultTaxMultiplier *( shipping + (price * qty * discountMultiplier )): defaultTax + (price * qty * discount ) + shipping);
                    entryTotal.setDiscountTotal((discount/100)  * price * qty );
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shipping + (price * qty * discountMultiplier )): defaultTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(Global.products.get(itemEntry.getProduct().getSelectedIndex()).isPercent());
                    entryTotal.setDefaultTax(true);
                }

            else
            {
                if (itemEntry.getShipping().isPercent){
                    entryTotal.setGrossTotal(product.isPercent() ? defaultTaxMultiplier * ( shippingMultiplier * (price * qty - discount )): (shippingMultiplier * (price * qty - discount )) + defaultTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal((shipping/100) * (price * qty - discount));
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shippingMultiplier * (price * qty - discount)): defaultTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(Global.products.get(itemEntry.getProduct().getSelectedIndex()).isPercent());
                    entryTotal.setDefaultTax(true);
                }
                else{
                    entryTotal.setGrossTotal(product.isPercent() ? shipping +(defaultTaxMultiplier * (price * qty - discount)): shipping + (price * qty - discount ) + defaultTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shipping + (price * qty - discount)): defaultTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(Global.products.get(itemEntry.getProduct().getSelectedIndex()).isPercent());
                    entryTotal.setDefaultTax(true);
                }

            }

        }
        return entryTotal;
    }




    public void setVerticalScrollBarColor(JScrollPane jScrollPane, Color color){
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
                                return Global.colorScheme.getSecondaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getSecondaryColor());
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
                                return Global.colorScheme.getSecondaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getSecondaryColor());
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


    public void setHorizontalScrollBarColor(JScrollPane jScrollPane, Color color){
        jScrollPane.getHorizontalScrollBar().setUI(
                new BasicScrollBarUI(){
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }
                });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        //Action Listeners { ProductComboBox product, JCheckBox tax)
        if(e.getSource() instanceof ProductComboBox){
            updateInvoice();
        }
        if(e.getSource() instanceof JCheckBox){
            updateInvoice();
        }
        if (e.getSource() == submit){

            new PrintView((JFrame)this.getOwner(), this).setVisible(true);

        }

        this.revalidate();
        this.repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof DeleteButton){
            DeleteButton button  = (DeleteButton) e.getSource();
            ItemEntry itemEntry = button.getItemEntry();
            JPanel panel = ticket.getEntries().get(ticket.getEntries().indexOf(itemEntry)).getPanel();
            entryarea.remove(panel);
            double entriesDimension = entries.getPreferredSize().getHeight();
            double entryAreaDimension = entryarea.getPreferredSize().getHeight();
            double productAreaDimension = productsArea.getPreferredSize().getHeight();
            ticket.getEntries().remove(itemEntry);
            entries.setPreferredSize(new Dimension(550, (int) entriesDimension - 37));
            entryarea.setPreferredSize(new Dimension(550, (int) entryAreaDimension - 37));
            productsArea.setPreferredSize(new Dimension(550, (int) productAreaDimension - 37));
            entryarea.revalidate();
            entryarea.repaint();
            updateInvoice();
            this.revalidate();
            this.repaint();
        }
        else
        if(e.getSource() instanceof AddButton){
            addProductEntry();
            entryarea.revalidate();
            entryarea.repaint();
            updateInvoice();
            this.revalidate();
            this.repaint();
        }
        else
        if(e.getSource() instanceof  JLabel)
        {
            updateInvoice();
            this.revalidate();
            this.repaint();
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
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && e.getKeyCode() == 81 )
        {
            addProductEntry();
            updateInvoice();
            this.revalidate();
            this.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

        updateInvoice();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        updateInvoice();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
