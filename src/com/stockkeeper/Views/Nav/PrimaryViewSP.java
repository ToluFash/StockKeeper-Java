package com.stockkeeper.Views.Nav;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.TicketEntry;
import com.stockkeeper.Views.TicketView;
import com.stockkeeper.Views.uicomponents.ProductsModelHeaderLabel;
import com.stockkeeper.Views.uicomponents.ProductsModelRow;
import com.stockkeeper.Views.uicomponents.StockHeaderLabel;
import com.stockkeeper.Views.uicomponents.TicketModelRow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PrimaryViewSP extends PrimaryView {

    protected JLabel ticketTaxPayableLabel;
    protected  JLabel ticketShippingTotalLabel;
    protected  JLabel ticketDiscountLabel;

    public PrimaryViewSP() {
    }


    @Override
    protected void displayModel(){
        for(TicketModel entry: ticketModel2){
            TicketModelRow ticketModelRow = modelRowFound(entry);
            if(ticketModelRow == null){
                TicketModelRow ticketModelRow1 = new TicketModelRow(entry);
                ticketModelRow1.addMouseListener(this);
                ticketContainer.add(ticketModelRow1);
                ticketContainerHeight += 46;
                ticketContainer.setPreferredSize(new Dimension(0, ticketContainerHeight));
                grossTotal += entry.getTotal();
            }
        }
        for(ProductsModel productsModel: productsModel2){
            if(productsModel.getSummedQty() != 0){

                ProductsModelRow productsModelRow = productsModelRowFound(productsModel);
                if(productsModelRow == null){
                    ProductsModelRow productsModelRow1 = new ProductsModelRow(productsModel);
                    productsModelRow1.addMouseListener(this);
                    ticketContainerAlt.add(productsModelRow1);
                    ticketContainerHeightAlt += 46;
                    ticketContainerAlt.setPreferredSize(new Dimension(0, ticketContainerHeightAlt));
                    grossTotalAlt += productsModel.getNetTotal();
                }
                else{
                    double before =  productsModelRow.getProductsModel().getNetTotal();
                    productsModelRow.refresh(productsModel);
                    grossTotalAlt += productsModel.getNetTotal() - before;
                }
            }
        }
        ticketContainer.revalidate();
        ticketContainerAlt.revalidate();
        ticketContainer.repaint();
        ticketContainerAlt.repaint();
    }


    public void mouseClickedSP(MouseEvent e, String title){
        if (e.getSource() instanceof StockHeaderLabel) {
            mouseClickedStockHeaderLabel(e);
        }
        if (e.getSource() instanceof ProductsModelHeaderLabel) {
            mouseClickedProductsHeaderLabel(e);
        }
        if (e.getSource() == ticketContainer) {
            mouseClickedTicketContainer(e);
        }
        if (e.getSource() == ticketContainerAlt) {
            mouseClickedTicketContaineAlt(e);
        }
        if (e.getSource() == searchBarandViewChange.getViewChangePanel().getTilesLabel()){
            setAltView();
        }
        if (e.getSource() == searchBarandViewChange.getViewChangePanel().getListLabel()){
            setListView();
        }
        if (e.getSource() == addButton){
            new TicketEntry(Global.mainView, entity,user,title).setVisible(true);
        }
        if(e.getSource() == leftArrow){
            mouseClickedLeftArrow(e);
        }
        if(e.getSource() == rightArrow){
            mouseClickedRightArrow(e);
        } if(e.getSource() == detailsCon.getOpenIcon()){
            new TicketView(Global.mainView, selected.getTicket().getId(),title.toUpperCase(),(TicketModel) selected.getTicket()).setVisible(true);
        }

        if (e.getClickCount() > 1){
            if(e.getSource() instanceof  StockHeaderLabel) {
                if (e.getSource() != ticketHeaderAlt.getProduct() && e.getSource() != ticketHeaderAlt.getQty() &&
                        e.getSource() != ticketHeaderAlt.getNetTotal()) {
                    new TicketView(Global.mainView, selected.getTicket().getId(), title.toUpperCase(), (TicketModel)selected.getTicket()).setVisible(true);
                }
            }
        }
    }
}
