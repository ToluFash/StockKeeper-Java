package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsHeaderSaleView extends JPanel implements MouseListener {
    private Entry entry;
    private GridLayout layout;
    private ProductsModelHeaderLabel selected;
    private ProductsModelHeaderLabel product;
    private ProductsModelHeaderLabel qty;
    private ProductsModelHeaderLabel discount;
    private ProductsModelHeaderLabel shipping;
    private ProductsModelHeaderLabel tax;
    private ProductsModelHeaderLabel netTotal;



    public ProductsHeaderSaleView() {
        super();
        layout = new GridLayout(1,6);
        setBorder(new EmptyBorder(0,2,0,0));
        setLayout(layout);
        setBackground(Global.colorScheme.getQuaternaryColor());

        setUpRow();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        product.addMouseListener(l);
        qty.addMouseListener(l);
        netTotal.addMouseListener(l);
        discount.addMouseListener(l);
        shipping.addMouseListener(l);
        tax.addMouseListener(l);
        super.addMouseListener(l);
    }

    private void setUpRow(){
        setBackground(Global.colorScheme.getPrimaryColor());
        product = new ProductsModelHeaderLabel("Product", Global.colorScheme.getPrimaryColor(), Color.WHITE){
        };
        qty = new ProductsModelHeaderLabel("Quantity", Global.colorScheme.getPrimaryColor(), Color.WHITE);
        discount = new ProductsModelHeaderLabel("Discount", Global.colorScheme.getPrimaryColor(), Color.WHITE);
        shipping = new ProductsModelHeaderLabel("Shipping", Global.colorScheme.getPrimaryColor(), Color.WHITE){
        };
        tax = new ProductsModelHeaderLabel("Tax", Global.colorScheme.getPrimaryColor(), Color.WHITE);
        netTotal = new ProductsModelHeaderLabel("Net Total", Global.colorScheme.getPrimaryColor(), Color.WHITE);

        product.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        qty.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        discount.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        shipping.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        tax.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        netTotal.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));

        product.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        qty.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        discount.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        shipping.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        tax.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        netTotal.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));

        product.setType(1);
        qty.setType(2);
        discount.setType(3);
        shipping.setType(4);
        tax.setType(5);
        netTotal.setType(6);


        product.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        qty.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        discount.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        shipping.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        tax.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        netTotal.setFont(FontsList.getSansSerif(Font.BOLD, 12));
        selected = product;
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

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/3 - 2,35));
        qty.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,35));
        discount.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,35));
        shipping.setPreferredSize(new Dimension((int) dimension.getWidth()/3 - 2,35));
        tax.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,35));
        netTotal.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,35));
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

    public ProductsModelHeaderLabel getSelected() {
        return selected;
    }

    public void setSelected(ProductsModelHeaderLabel selected) {
        this.selected = selected;
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

    public void appropriateSelection(ProductsModelHeaderLabel stockHeaderLabel){

        selected.setSelected(false);
        stockHeaderLabel.setSelected(true);
        selected = stockHeaderLabel;
        if (stockHeaderLabel.getSortType() == 0){
            stockHeaderLabel.setSortType(1);
        }
        else{
            stockHeaderLabel.setSortType(0);
        }
        product.repaint();
        qty.repaint();
        netTotal.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == product){
            appropriateSelection(product);
        }
        if (e.getSource() == qty){
            appropriateSelection(qty);
        }
        if (e.getSource() == netTotal){
            appropriateSelection(netTotal);
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
