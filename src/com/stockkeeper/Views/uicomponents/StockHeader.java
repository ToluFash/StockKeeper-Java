package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class StockHeader extends JPanel {
    private GridLayout layout;
    private StockHeaderLabel product;
    private StockHeaderLabel stockLevel;
    private StockHeaderLabel stockValue;



    public StockHeader() {
        super();
        layout = new GridLayout(1,3);
        layout.setHgap(1);
        setBorder(new EmptyBorder(0,2,0,0));
        setLayout(layout);
        setBackground(Global.colorScheme.getQuaternaryColor());

        setUpRow();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        stockLevel.addMouseListener(l);
        stockValue.addMouseListener(l);
        super.addMouseListener(l);
    }

    private void setUpRow(){
        product = new StockHeaderLabel("Product", Global.colorScheme.getPrimaryColor());
        stockLevel = new StockHeaderLabel("Stock Level", Global.colorScheme.getPrimaryColor());
        stockValue = new StockHeaderLabel("Stock Value", Global.colorScheme.getPrimaryColor());

        //set sizes of labels

         add(product);
         add(stockLevel);
         add(stockValue);
    }


    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/3 - 2,40));
        stockLevel.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,40));
        stockValue.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        stockLevel.revalidate();
        stockValue.revalidate();
    }

    public StockHeaderLabel getProduct() {
        return product;
    }

    public void setProduct(StockHeaderLabel product) {
        this.product = product;
    }

    public StockHeaderLabel getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(StockHeaderLabel stockLevel) {
        this.stockLevel = stockLevel;
    }

    public StockHeaderLabel getStockValue() {
        return stockValue;
    }

    public void setStockValue(StockHeaderLabel stockValue) {
        this.stockValue = stockValue;
    }
}
