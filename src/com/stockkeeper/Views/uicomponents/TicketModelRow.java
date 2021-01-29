package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.Selectable;
import com.stockkeeper.Views.TicketDisplayItem;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicketModelRow extends TicketDisplayItem implements MouseListener, Selectable {
    private TicketModel ticket;
    private boolean selected;
    private FlowLayout layout;
    private GridBagLayout layout2;
    private StockHeaderLabel ticketID;
    private StockHeaderLabel ticketValue;
    private StockHeaderLabel customer;
    private StockHeaderLabel date;



    public TicketModelRow(TicketModel entry) {
        super();
        this.ticket = entry;
        setBackground(Global.colorScheme.getSecondaryColor());
        layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(new GridLayout(1,3));
        setBorder(new EmptyBorder(0,2,0,2));

        setUpRow();
    }

    public TicketModelRow() {
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public StockHeaderLabel getTicketID() {
        return ticketID;
    }

    public StockHeaderLabel getTicketValue() {
        return ticketValue;
    }

    public StockHeaderLabel getCustomer() {
        return customer;
    }

    public StockHeaderLabel getDate() {
        return date;
    }

    private void setUpRow(){
        ticketID = new StockHeaderLabel(ticket.getId());
        ticketValue = new StockHeaderLabel(ticket.getTotal() +"");
        customer = new StockHeaderLabel(ticket.getCustomer());
        date = new StockHeaderLabel(ticket.getDate().getTime().toString());
        ticketID.addMouseListener(this);
        ticketValue.addMouseListener(this);
        customer.addMouseListener(this);
        date.addMouseListener(this);

        //set sizes of labels

        add(ticketID);
        add(ticketValue);
        add(customer);
        add(date);
    }


    @Override
    public synchronized void addMouseListener(MouseListener l) {
        ticketID.addMouseListener(l);
        ticketValue.addMouseListener(l);
        customer.addMouseListener(l);
        date.addMouseListener(l);
        super.addMouseListener(l);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        ticketID.setPreferredSize(new Dimension((int) dimension.getWidth()/4 - 2,40));
        ticketValue.setPreferredSize(new Dimension((int) dimension.getWidth()/4- 2,40));
        customer.setPreferredSize(new Dimension((int) dimension.getWidth()/4- 2,40));
        date.setPreferredSize(new Dimension((int) dimension.getWidth()/4- 2,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        ticketID.revalidate();
        ticketValue.revalidate();
        customer.revalidate();
        date.revalidate();
    }

    public void turnOn(){

        setBackground(Global.colorScheme.getPrimaryColor());
        ticketID.setBackground(Global.colorScheme.getPrimaryColor());
        ticketValue.setBackground(Global.colorScheme.getPrimaryColor());
        customer.setBackground(Global.colorScheme.getPrimaryColor());
        date.setBackground(Global.colorScheme.getPrimaryColor());
        repaint();
        ticketID.repaint();
        ticketValue.repaint();
        customer.repaint();
        date.repaint();
    }
    public void turnOff(){


        setBackground(Global.colorScheme.getSecondaryColor());
        ticketID.setBackground(Global.colorScheme.getSecondaryColor());
        ticketValue.setBackground(Global.colorScheme.getSecondaryColor());
        customer.setBackground(Global.colorScheme.getSecondaryColor());
        date.setBackground(Global.colorScheme.getSecondaryColor());
        repaint();
        ticketID.repaint();
        ticketValue.repaint();
        customer.repaint();
        date.repaint();
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
       turnOn();

    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (!selected){

          turnOff();
        }

    }
}
