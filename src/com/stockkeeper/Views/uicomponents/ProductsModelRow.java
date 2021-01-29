package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.ProductsDisplayItem;
import com.stockkeeper.Views.Selectable;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsModelRow extends ProductsDisplayItem implements MouseListener, Selectable {
    private FlowLayout layout;
    private GridBagLayout layout2;
    private ProductsModelHeaderLabel product;
    private ProductsModelHeaderLabel qty;
    private ProductsModelHeaderLabel netTotal;



    public ProductsModelRow(ProductsModel productsModel) {
        super();
        this.productsModel = productsModel;
        setBackground(Global.colorScheme.getSecondaryColor());
        layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(new GridLayout(1,3));
        setBorder(new EmptyBorder(0,2,0,2));

        setUpRow();
    }

    public ProductsModelRow() {
    }

    private void setUpRow(){
        product = new ProductsModelHeaderLabel(productsModel.getProduct().getName()){
        };
        qty = new ProductsModelHeaderLabel(productsModel.getSummedQty() + "");
        netTotal = new ProductsModelHeaderLabel("$"+ productsModel.getNetTotal() +"");
        product.addMouseListener(this);
        qty.addMouseListener(this);
        netTotal.addMouseListener(this);
         add(product);
         add(qty);
         add(netTotal);
    }

    public void refresh(ProductsModel productsModel){
        this.productsModel= productsModel;
        product.setText(productsModel.getProduct().getName());
        qty.setText(productsModel.getSummedQty() + "");
        netTotal.setText("$"+ productsModel.getNetTotal() +"");
        repaint();
    }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        qty.addMouseListener(l);
        netTotal.addMouseListener(l);
        super.addMouseListener(l);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        qty.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        netTotal.setPreferredSize(new Dimension((int) dimension.getWidth()/3 -5,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        qty.revalidate();
        netTotal.revalidate();
    }

    public void turnOn(){

        setBackground(Global.colorScheme.getPrimaryColor());
        product.setBackground(Global.colorScheme.getPrimaryColor());
        qty.setBackground(Global.colorScheme.getPrimaryColor());
        netTotal.setBackground(Global.colorScheme.getPrimaryColor());
        repaint();
        product.repaint();
        qty.repaint();
        netTotal.repaint();
    }
    public void turnOff(){


        setBackground(Global.colorScheme.getSecondaryColor());
        product.setBackground(Global.colorScheme.getSecondaryColor());
        qty.setBackground(Global.colorScheme.getSecondaryColor());
        netTotal.setBackground(Global.colorScheme.getSecondaryColor());
        repaint();
        product.repaint();
        qty.repaint();
        netTotal.repaint();
    }

    @Override
    public TicketModel getTicket() {
        return null;
    }

    public ProductsModelHeaderLabel getProduct() {
        return product;
    }

    public ProductsModelHeaderLabel getQty() {
        return qty;
    }

    public ProductsModelHeaderLabel getNetTotal() {
        return netTotal;
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
