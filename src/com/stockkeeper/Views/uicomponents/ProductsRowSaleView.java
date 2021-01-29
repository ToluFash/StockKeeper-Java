package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsRowSaleView extends JPanel {
    private Entry entry;
    private GridLayout layout;
    private ProductsModelHeaderLabel product;
    private ProductsModelHeaderLabel qty;
    private ProductsModelHeaderLabel discount;
    private ProductsModelHeaderLabel shipping;
    private ProductsModelHeaderLabel tax;
    private ProductsModelHeaderLabel netTotal;



    public ProductsRowSaleView(Entry entry) {
        super();
        this.entry = entry;
        layout = new GridLayout(1,6);
        layout.setHgap(1);
        setLayout(layout);
        setBackground(Global.colorScheme.getQuaternaryColor());

        setUpRow();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        qty.addMouseListener(l);
        netTotal.addMouseListener(l);
        super.addMouseListener(l);
    }

    private void setUpRow(){
        product = new ProductsModelHeaderLabel(entry.getProduct().getName()){
        };
        qty = new ProductsModelHeaderLabel((entry.getQty()+""));
        discount = new ProductsModelHeaderLabel((entry.getTotal().getDiscountTotal()+""));
        shipping = new ProductsModelHeaderLabel((entry.getTotal().getShippingTotal()+""));
        tax = new ProductsModelHeaderLabel((entry.getTotal().getTaxTotal()+""));
        netTotal = new ProductsModelHeaderLabel((entry.getTotal().getGrossTotal()+""));

        add(product);
        add(qty);
        add(discount);
        add(shipping);
        add(tax);
        add(netTotal);
    }


    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/6 - 2,40));
        qty.setPreferredSize(new Dimension((int) dimension.getWidth()/6- 2,40));
        discount.setPreferredSize(new Dimension((int) dimension.getWidth()/6- 2,40));
        shipping.setPreferredSize(new Dimension((int) dimension.getWidth()/6 - 2,40));
        tax.setPreferredSize(new Dimension((int) dimension.getWidth()/6- 2,40));
        netTotal.setPreferredSize(new Dimension((int) dimension.getWidth()/6- 2,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        qty.revalidate();
        netTotal.revalidate();
    }


    public ProductsModelHeaderLabel getProduct() {
        return product;
    }

    public void setProduct(ProductsModelHeaderLabel product) {
        this.product = product;
    }

    public ProductsModelHeaderLabel getQty() {
        return qty;
    }

    public void setQty(ProductsModelHeaderLabel qty) {
        this.qty = qty;
    }

    public ProductsModelHeaderLabel getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(ProductsModelHeaderLabel netTotal) {
        this.netTotal = netTotal;
    }


}
