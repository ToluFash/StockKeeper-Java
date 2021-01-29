package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductsHeader extends JPanel implements MouseListener {
    private GridLayout layout;
    private ProductsModelHeaderLabel selected;
    private ProductsModelHeaderLabel product;
    private ProductsModelHeaderLabel qty;
    private ProductsModelHeaderLabel netTotal;



    public ProductsHeader() {
        super();
        layout = new GridLayout(1,3);
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
        product = new ProductsModelHeaderLabel("Product"){
        };
        qty = new ProductsModelHeaderLabel("Quantity");
        netTotal = new ProductsModelHeaderLabel("Net Total");

        product.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        qty.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));
        netTotal.setAsc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortAscIcon.png")));

        product.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        qty.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));
        netTotal.setDesc(new ImageIcon(getClass().getResource("/com/stockkeeper/images/sortDescIcon.png")));

        product.setType(1);
        qty.setType(2);
        netTotal.setType(3);
        selected = product;
        add(product);
        add(qty);
        add(netTotal);
    }


    @Override
    public void paint(Graphics g) {
        Dimension dimension = getParent().getSize();

        product.setPreferredSize(new Dimension((int) dimension.getWidth()/3 - 2,40));
        qty.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,40));
        netTotal.setPreferredSize(new Dimension((int) dimension.getWidth()/3- 2,40));
        revalidateChildren();
        super.paint(g);
    }

    public void revalidateChildren(){
        product.revalidate();
        qty.revalidate();
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
