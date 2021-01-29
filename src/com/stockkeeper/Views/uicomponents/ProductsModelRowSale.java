package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.ProductsDisplayItem;
import com.stockkeeper.Views.Selectable;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsModelRowSale extends ProductsDisplayItem implements MouseListener, Selectable {
    private FlowLayout layout;
    private GridBagLayout layout2;
    private Entry entry;
    private ProductsModelHeaderLabel product;
    private ProductsModelHeaderLabel qty;
    private ProductsModelHeaderLabel discount;
    private ProductsModelHeaderLabel shipping;
    private ProductsModelHeaderLabel tax;
    private ProductsModelHeaderLabel netTotal;



    public ProductsModelRowSale(Entry entry) {
        super();
        this.entry = entry;
        setBackground(Global.colorScheme.getQuaternaryColor());
        layout = new FlowLayout(FlowLayout.LEFT);
        setLayout(new GridLayout(1,6));
        setBorder(new LineBorder(Global.colorScheme.getSecondaryColor()));

        setUpRow();
    }

    public ProductsModelRowSale() {
    }

    private void setUpRow(){
        product = new ProductsModelHeaderLabel(entry.getProduct().getName(),Global.colorScheme.getQuaternaryColor()){
        };
        qty = new ProductsModelHeaderLabel(entry.getQty() + "",Global.colorScheme.getQuaternaryColor());
        discount = new ProductsModelHeaderLabel((entry.getTotal().getDiscountTotal()+""),Global.colorScheme.getQuaternaryColor());
        shipping = new ProductsModelHeaderLabel((entry.getTotal().getShippingTotal()+""),Global.colorScheme.getQuaternaryColor());
        tax = new ProductsModelHeaderLabel((entry.getTotal().getTaxTotal()+""),Global.colorScheme.getQuaternaryColor());
        netTotal = new ProductsModelHeaderLabel("$"+ entry.getTotal().getGrossTotal() +"",Global.colorScheme.getQuaternaryColor());
        product.addMouseListener(this);
        qty.addMouseListener(this);
        discount.addMouseListener(this);
        shipping.addMouseListener(this);
        tax.addMouseListener(this);
        netTotal.addMouseListener(this);
        product.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        qty.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        discount.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        shipping.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        tax.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        netTotal.setFont(FontsList.getSansSerif(Font.BOLD, 12));
         add(product);
         add(qty);
         add(discount);
         add(shipping);
         add(tax);
         add(netTotal);
    }

    public void refresh(Entry entry){
        this.entry= entry;
        product.setText(entry.getProduct().getName());
        qty.setText(entry.getQty() + "");
        discount.setText("$"+ entry.getTotal().getDiscountTotal()+"");
        shipping.setText(entry.getTotal().getShippingTotal()+"");
        tax.setText(entry.getTotal().getTaxTotal()+"");
        netTotal.setText("$"+ entry.getTotal().getGrossTotal() +"");
        repaint();
    }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        qty.addMouseListener(l);
        discount.addMouseListener(l);
        shipping.addMouseListener(l);
        tax.addMouseListener(l);
        netTotal.addMouseListener(l);
        super.addMouseListener(l);
    }

    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        qty.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        discount.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        shipping.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        tax.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        netTotal.setPreferredSize(new Dimension((int) dimension.getWidth()/6 -5,35));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        qty.revalidate();
        discount.revalidate();
        shipping.revalidate();
        tax.revalidate();
        netTotal.revalidate();
    }

    public void turnOn(){

        setBackground(Global.colorScheme.getPrimaryColor());
        product.setBackground(Global.colorScheme.getPrimaryColor());
        qty.setBackground(Global.colorScheme.getPrimaryColor());
        discount.setBackground(Global.colorScheme.getPrimaryColor());
        shipping.setBackground(Global.colorScheme.getPrimaryColor());
        tax.setBackground(Global.colorScheme.getPrimaryColor());
        netTotal.setBackground(Global.colorScheme.getPrimaryColor());
        repaint();
        product.repaint();
        qty.repaint();
        discount.repaint();
        shipping.repaint();
        tax.repaint();
        netTotal.repaint();
    }
    public void turnOff(){


        setBackground(Global.colorScheme.getQuaternaryColor());
        product.setBackground(Global.colorScheme.getQuaternaryColor());
        qty.setBackground(Global.colorScheme.getQuaternaryColor());
        discount.setBackground(Global.colorScheme.getQuaternaryColor());
        shipping.setBackground(Global.colorScheme.getQuaternaryColor());
        tax.setBackground(Global.colorScheme.getQuaternaryColor());
        netTotal.setBackground(Global.colorScheme.getQuaternaryColor());
        repaint();
        product.repaint();
        qty.repaint();
        discount.repaint();
        shipping.repaint();
        tax.repaint();
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

    public ProductsModelHeaderLabel getDiscount() {
        return discount;
    }

    public ProductsModelHeaderLabel getShipping() {
        return shipping;
    }

    public ProductsModelHeaderLabel getTax() {
        return tax;
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
