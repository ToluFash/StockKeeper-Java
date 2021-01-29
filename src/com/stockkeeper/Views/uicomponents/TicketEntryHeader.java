package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class TicketEntryHeader extends JPanel {
    private GridLayout layout;
    private JLabel product;
    private JLabel qty;
    private JLabel discount;
    private JLabel shipping;
    private JLabel tax;



    public TicketEntryHeader() {
        super();
        layout = new GridLayout(1,5);
        layout.setHgap(1);
        setLayout(layout);
        setBorder(new EmptyBorder(0,5,0,0));
        setBackground(new Color(0x606363));

        setUpRow();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        qty.addMouseListener(l);
        discount.addMouseListener(l);
        shipping.addMouseListener(l);
        tax.addMouseListener(l);
        super.addMouseListener(l);
    }

    private void setUpRow(){
        product = new JLabel("Product");
        qty = new JLabel("Quantity");
        discount = new JLabel("Discount");
        shipping = new JLabel("Shipping");
        tax = new JLabel("Tax");
        product.setForeground(Color.WHITE);
        qty.setForeground(Color.WHITE);
        discount.setForeground(Color.WHITE);
        shipping.setForeground(Color.WHITE);
        tax.setForeground(Color.WHITE);

        //set sizes of labels

         add(product);
         add(qty);
         add(discount);
         add(shipping);
         add(tax);
    }


    @Override
    public void paint(Graphics g) {
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        qty.revalidate();
        discount.revalidate();
        shipping.revalidate();
        tax.revalidate();
    }

    public JLabel getProduct() {
        return product;
    }

    public void setProduct(JLabel product) {
        this.product = product;
    }

    public JLabel getQty() {
        return qty;
    }

    public void setQty(JLabel qty) {
        this.qty = qty;
    }

    public JLabel getDiscount() {
        return discount;
    }

    public void setDiscount(JLabel discount) {
        this.discount = discount;
    }

    public JLabel getShipping() {
        return shipping;
    }

    public void setShipping(JLabel shipping) {
        this.shipping = shipping;
    }

    public JLabel getTax() {
        return tax;
    }

    public void setTax(JLabel tax) {
        this.tax = tax;
    }
}
