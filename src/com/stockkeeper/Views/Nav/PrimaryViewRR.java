package com.stockkeeper.Views.Nav;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.Selectable;
import com.stockkeeper.Views.TicketEntry;
import com.stockkeeper.Views.TicketView;
import com.stockkeeper.Views.uicomponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PrimaryViewRR extends PrimaryView {

    protected JLabel ticketTaxPayableLabel;
    protected  JLabel ticketShippingTotalLabel;
    protected  JLabel ticketDiscountLabel;

    protected Selectable inContext;
    //Top Panels
    protected JPopupMenu rightClickMenu;
    protected JMenuItem openP;
    protected JMenuItem openR;
    protected JMenuItem view;
    protected JMenuItem sort;
    protected JMenu copy;
    protected JMenuItem cell;
    protected JMenuItem row;
    protected  JLabel referenceLabel;

    public PrimaryViewRR() {
    }

    protected void initJMenu(){
        rightClickMenu = new JPopupMenu();
        openP = new JMenuItem("OpenP");
        openR = new JMenuItem("OpenP Reference");
        view = new JMenuItem("View");
        sort = new JMenuItem("Sort");
        copy = new JMenu("Copy");
        cell = new JMenuItem("Cell");
        row = new JMenuItem("Row");
        rightClickMenu.add(openP);
        rightClickMenu.add(openR);
        rightClickMenu.add(view);
        rightClickMenu.add(sort);
        rightClickMenu.add(copy);
        copy.add(cell);
        copy.add(row);
        openP.addActionListener(this);
        openR.addActionListener(this);
    }

    @Override
    protected void displayModel(){
        for(TicketModel entry: ticketModel2){
            TicketModelRowR ticketModelRowR = modelRowFoundR(entry);
            if(ticketModelRowR == null){
                TicketModelRowR ticketModelRow1 = new TicketModelRowR(entry);
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


    @Override
    public void mouseClickedStockHeaderLabel(MouseEvent e) {
        if (e.getSource() instanceof  StockHeaderLabel) {
            if (e.getSource() == ticketHeader.getTicketID() || e.getSource() == ticketHeader.getTicketValue() ||
                    e.getSource() == ticketHeader.getCustomer()|| e.getSource() == ticketHeader.getDate())
            {
                ticketHeader.mouseClicked(e);
                sortRows( (ticketHeader.getSelected().getSortType() * 4) + ticketHeader.getSelected().getType(), ticketContainer);
            }
            else {
                if(e.getButton() == MouseEvent.BUTTON3){
                    rightClickMenu.show(this, e.getXOnScreen(),e.getYOnScreen());
                    inContext = (TicketModelRowR) ((StockHeaderLabel) e.getSource()).getParent();
                }
                if(e.getButton() == MouseEvent.BUTTON1){
                    TicketModelRowR ticketRow = (TicketModelRowR) ((StockHeaderLabel) e.getSource()).getParent();
                    ticketIDLabel.setText(ticketRow.getTicket().getId());
                    totalLabel.setText("$" + ticketRow.getTicket().getTotal());
                    referenceLabel.setText(ticketRow.getTicket().getCustomer());
                    dateLabel.setText(ticketRow.getTicket().getDate().getTime().toString());
                    setChartViewBar(ticketRow.getTicket().getGraphData());
                    detailsCon.switchView(2);
                    appropriateSelection(ticketRow);
                    detailsCon.setDetailsVisible(true);
                }
            }
        }

    }

    public void mouseClickedRR(MouseEvent e, String title){
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
