package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicketModelHeader extends JPanel implements  MouseListener{
    private GridLayout layout;
    private StockHeaderLabel selected;
    private StockHeaderLabel ticketID;
    private StockHeaderLabel ticketValue;
    private StockHeaderLabel customer;
    private StockHeaderLabel date;



    public TicketModelHeader() {
        super();
        layout = new GridLayout(1,4);
        layout.setHgap(1);
        setLayout(layout);
        setBackground(Global.colorScheme.getQuaternaryColor());

        setUpRow();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        ticketID.addMouseListener(l);
        //ticketID.addMouseListener(this);
        ticketValue.addMouseListener(l);
        //ticketValue.addMouseListener(this);
        customer.addMouseListener(l);
        //customer.addMouseListener(this);
        date.addMouseListener(l);
        //date.addMouseListener(this);
        super.addMouseListener(l);
    }

    private void setUpRow(){
        ticketID = new StockHeaderLabel("Ticket ID");
        ticketValue = new StockHeaderLabel("Value");
        customer = new StockHeaderLabel("Customer");
        date = new StockHeaderLabel("Date");
        selected = ticketID;

        ticketID.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        ticketValue.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        customer.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        date.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));

        ticketID.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        ticketValue.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        customer.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        date.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));

        ticketID.setType(1);
        ticketValue.setType(2);
        customer.setType(3);
        date.setType(4);

         add(ticketID);
         add(ticketValue);
         add(customer);
         add(date);
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

    public StockHeaderLabel getSelected() {
        return selected;
    }

    public void setSelected(StockHeaderLabel selected) {
        this.selected = selected;
    }

    public StockHeaderLabel getTicketID() {
        return ticketID;
    }

    public void setTicketID(StockHeaderLabel ticketID) {
        this.ticketID = ticketID;
    }

    public StockHeaderLabel getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(StockHeaderLabel ticketValue) {
        this.ticketValue = ticketValue;
    }

    public StockHeaderLabel getCustomer() {
        return customer;
    }

    public void setCustomer(StockHeaderLabel customer) {
        this.customer = customer;
    }

    public StockHeaderLabel getDate() {
        return date;
    }

    public void setDate(StockHeaderLabel date) {
        this.date = date;
    }

    public void appropriateSelection(StockHeaderLabel stockHeaderLabel){

        selected.setSelected(false);
        stockHeaderLabel.setSelected(true);
        selected = stockHeaderLabel;
        if (stockHeaderLabel.getSortType() == 0){
            stockHeaderLabel.setSortType(1);
        }
        else{
            stockHeaderLabel.setSortType(0);
        }
        ticketID.repaint();
        ticketValue.repaint();
        customer.repaint();
        date.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == ticketID){
            appropriateSelection(ticketID);
        }
        if (e.getSource() == ticketValue){
            appropriateSelection(ticketValue);
        }
        if (e.getSource() == customer){
            appropriateSelection(customer);
        }
        if (e.getSource() == date){
            appropriateSelection(date);
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
