package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Invoices.Invoice1;
import com.stockkeeper.Invoices.InvoicePrintable;
import com.stockkeeper.Invoices.POSInvoice;
import com.stockkeeper.Models.product.Ticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintView extends JDialog implements MouseListener, Printable, ComponentListener {
    private TicketEntry ticketEntry;
    private JPanel center;
    private JPanel footer;
    private JLabel largePrint;
    private JPopupMenu jPopupMenu;
    private JScrollPane largePrintScrollPane;
    private JLabel largePrintPopUp;
    private JLabel thermalPrint;
    private JLabel submit;
    private JLabel print;
    //Prints
    private InvoicePrintable invoicePrintable;
    private InvoicePrintable invoicePrintableSmall;


    public PrintView(Frame owner, TicketEntry ticketEntry) {
        super(owner);
        this.ticketEntry = ticketEntry;
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
        addMouseListener(this);
        addComponentListener(this);
        setSize(new Dimension(780,480));
        setLayout(new BorderLayout());
        initTopPanels();
        setCenter();
        setFooter();
        initPopUp();
    }

    private void initTopPanels(){
        center = new JPanel();
        footer = new JPanel();
        center.setOpaque(false);
        footer.setOpaque(false);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void setCenter(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        center.setLayout(layout);
        center.setBorder(new EmptyBorder(0,80,0,80));
        center.setPreferredSize(new Dimension(780,400));
        largePrint = new JLabel();
        largePrint.addMouseListener(this);
        JScrollPane largePrintSP = new JScrollPane(largePrint, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        largePrintSP.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        largePrintSP.setPreferredSize(new Dimension(300, 350));
        largePrintSP.getViewport().setBackground(Color.WHITE);
        center.add(largePrintSP);

        JPanel padding = new JPanel();
        padding.setOpaque(false);
        padding.setPreferredSize(new Dimension(80, 400));
        center.add(padding);

        thermalPrint= new JLabel();
        thermalPrint.setOpaque(false);
        JScrollPane thermalPrintSP = new JScrollPane(thermalPrint, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        thermalPrintSP.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        thermalPrintSP.setPreferredSize(new Dimension(200, 250));
        thermalPrintSP.getViewport().setBackground(Color.WHITE);
        center.add(thermalPrintSP);
    }

    private void initPopUp(){

        jPopupMenu = new JPopupMenu();
        jPopupMenu.setBorderPainted(true);
        jPopupMenu.setPreferredSize(new Dimension(500,700));
        largePrintPopUp = new JLabel();
        largePrintScrollPane = new JScrollPane(largePrintPopUp);
        largePrintScrollPane.setPreferredSize(new Dimension(500,700));


    }

    private void setFooter(){
        FlowLayout layout = new FlowLayout();
        layout.setVgap(3);
        footer.setLayout(layout);
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(0,450,0,0));
        footer.setPreferredSize(new Dimension(780,50));


        submit = new JLabel("Submit"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRoundRect(0,0,getWidth()-1,getHeight()-1,8,8);
                super.paintComponent(g);
            }
        };
        submit.setForeground(Color.WHITE);
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setVerticalAlignment(SwingConstants.CENTER);
        submit.setPreferredSize(new Dimension(80,28));
        submit.addMouseListener(this);
        footer.add(submit);

        print = new JLabel("Print"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRoundRect(0,0,getWidth()-1,getHeight()-1,8,8);
                super.paintComponent(g);
            }
        };
        print.setHorizontalAlignment(SwingConstants.CENTER);
        print.setVerticalAlignment(SwingConstants.CENTER);
        print.setForeground(Color.WHITE);
        print.setPreferredSize(new Dimension(80,28));
        print.addMouseListener(this);
        footer.add(print);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == submit){
            ticketEntry.getTicket().saveTicketModel(ticketEntry.getTitle());
            this.setVisible(false);
            ticketEntry.setVisible(false);
        }
        if(e.getSource() == print){
            if (e.getSource() == print){
                PrinterJob job = PrinterJob.getPrinterJob();
                //PageFormat pf = job.pageDialog(job.defaultPage());
                job.setPrintable(this);
                if(job.printDialog()){
                    try{
                        invoicePrintable = new Invoice1(ticketEntry.getTicket());
                        invoicePrintableSmall = new POSInvoice(ticketEntry.getTicket());
                        job.print();
                    }
                    catch (PrinterException e1){
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());


        System.out.println(pageIndex);
        if(pageFormat.getImageableWidth() > 150){
            if(pageIndex > invoicePrintable.getPageMax((Graphics2D) graphics,pageFormat))
                return NO_SUCH_PAGE;
            return invoicePrintable.drawInvoice(graphics,pageFormat,pageIndex);
        }
        else{
            if(pageIndex >= 6)
                return NO_SUCH_PAGE;
            invoicePrintableSmall.drawInvoice(graphics,pageFormat,pageIndex);
        }
        return PAGE_EXISTS;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == largePrint){
            jPopupMenu.add(largePrintScrollPane);
            jPopupMenu.show(this, 50,50);
        }
        if(e.getSource() == center){
            jPopupMenu.setVisible(false);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println(this.getSize());
        System.out.println(this.getPreferredSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
