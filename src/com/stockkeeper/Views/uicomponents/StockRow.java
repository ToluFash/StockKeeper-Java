package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Views.StockDisplayItem;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StockRow  extends StockDisplayItem implements MouseListener {
    private FlowLayout layout;
    private GridBagLayout layout2;
    private StockHeaderLabel product;
    private StockHeaderLabel stockLevel;
    private StockHeaderLabel stockValue;



    public StockRow(Entry entry) {
        super();
        this.stockItem = entry;
        setBackground(Global.colorScheme.getSecondaryColor());
        layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(new GridLayout(1,3));
        setBorder(new EmptyBorder(0,2,0,2));

        setUpRow();
    }

    public StockRow() {
    }

    private void setUpRow(){
        product = new StockHeaderLabel(stockItem.getProduct().getName()){
        };
        stockLevel = new StockHeaderLabel(stockItem.getQty() + "");
        stockValue = new StockHeaderLabel("$"+ stockItem.getQty() * stockItem.getProduct().getUnitCost() +"");
        product.addMouseListener(this);
        stockLevel.addMouseListener(this);
        stockValue.addMouseListener(this);

        //set sizes of labels

         add(product);
         add(stockLevel);
         add(stockValue);
    }

    public void refresh(Entry entry){
        this.stockItem = entry;
        product.setText(stockItem.getProduct().getName());
        stockLevel.setText(stockItem.getQty() + "");
        stockValue.setText("$"+ stockItem.getQty() * stockItem.getProduct().getUnitCost() +"");
        repaint();
    }


    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        stockLevel.addMouseListener(l);
        stockValue.addMouseListener(l);
        super.addMouseListener(l);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        stockLevel.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        stockValue.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        stockLevel.revalidate();
        stockValue.revalidate();
    }

    public void turnOn(){

        setBackground(Global.colorScheme.getPrimaryColor());
        product.setBackground(Global.colorScheme.getPrimaryColor());
        stockLevel.setBackground(Global.colorScheme.getPrimaryColor());
        stockValue.setBackground(Global.colorScheme.getPrimaryColor());
        repaint();
        product.repaint();
        stockLevel.repaint();
        stockValue.repaint();
    }
    public void turnOff(){


        setBackground(Global.colorScheme.getSecondaryColor());
        product.setBackground(Global.colorScheme.getSecondaryColor());
        stockLevel.setBackground(Global.colorScheme.getSecondaryColor());
        stockValue.setBackground(Global.colorScheme.getSecondaryColor());
        repaint();
        product.repaint();
        stockLevel.repaint();
        stockValue.repaint();
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
