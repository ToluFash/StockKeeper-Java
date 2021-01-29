package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.OpenIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class SalesPanel extends JPanel implements  Details {


    private JLabel title;
    private JLabel product;
    private JLabel qty;
    private JLabel entrySize;
    private JLabel customer;
    private JLabel time;
    private JLabel taxPayable;
    private JLabel shippingTotal;
    private JLabel discount;
    private JPanel details;
    private JPanel graphPanel;
    private OneByTwoRow productP;
    private OneByTwoRow qtyP;
    private OneByTwoRow titleP;
    private OneByTwoRow entrySizeP;
    private OneByTwoRow customerP;
    private OneByTwoRow timeP;
    private OneByTwoRow taxPayableP;
    private OneByTwoRow shippingTotalP;
    private OneByTwoRow discountP;
    private OpenIcon openIcon;

    public SalesPanel(JLabel product, JLabel qty,JLabel title, JLabel entrySize, JLabel customer,JLabel time,JLabel taxPayable,JLabel shippingTotal,JLabel discount) {
        setPreferredSize(new Dimension(400,600));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Global.colorScheme.getTertiaryColor());
        details = new JPanel(flowLayout);
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(400,200));
        add(details);
        this.product = product;
        this.qty = qty;
        this.title = title;
        this.entrySize = entrySize;
        this.customer = customer;
        this.time = time;
        this.taxPayable = taxPayable;
        this.shippingTotal =shippingTotal;
        this.discount = discount;

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setPreferredSize( new Dimension(400,25));
        titlePanel.setOpaque(false);
        titlePanel.add((new JLabel("Details:"){
            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 15);
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(350,25);
            }
        }));
        openIcon = new OpenIcon();
        titlePanel.add(openIcon);

        details.add(titlePanel);
        this.productP = new OneByTwoRow(new DisplayLabel("Product"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.product){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.qtyP = new OneByTwoRow(new DisplayLabel("Quantity Sold"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.qty){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.titleP = new OneByTwoRow(new DisplayLabel("Ticket No"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.title){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.entrySizeP = new OneByTwoRow(new DisplayLabel("Size"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.entrySize){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.customerP = new OneByTwoRow(new DisplayLabel("Customer"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.customer){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.timeP = new OneByTwoRow(new DisplayLabel("Time"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.time){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.taxPayableP =new OneByTwoRow(new DisplayLabel("Tax Payable"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.taxPayable){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.shippingTotalP =new OneByTwoRow(new DisplayLabel("Shipping"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.shippingTotal){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.discountP = new OneByTwoRow(new DisplayLabel("Discount"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.discount){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };

        this.graphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphPanel.setPreferredSize(new Dimension(380,300));
        graphPanel.setBackground(Global.colorScheme.getTertiaryColor());

        details.add(productP);
        details.add(qtyP);
        details.add(titleP);
        details.add(entrySizeP);
        details.add(customerP);
        details.add(timeP);
        details.add(taxPayableP);
        details.add(shippingTotalP);
        details.add(discountP);

        add(graphPanel);
        setBorder(new EmptyBorder(10,0,0,2));
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getEntrySize() {
        return entrySize;
    }

    public void setEntrySize(JLabel entrySize) {
        this.entrySize = entrySize;
    }

    public JLabel getCustomer() {
        return customer;
    }

    public void setCustomer(JLabel customer) {
        this.customer = customer;
    }

    public JLabel getTime() {
        return time;
    }

    public void setTime(JLabel time) {
        this.time = time;
    }

    public JLabel getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(JLabel taxPayable) {
        this.taxPayable = taxPayable;
    }

    public JLabel getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(JLabel shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    public JLabel getDiscount() {
        return discount;
    }

    public void setDiscount(JLabel discount) {
        this.discount = discount;
    }

    public JPanel getDetails() {
        return details;
    }

    public void setDetails(JPanel details) {
        this.details = details;
    }

    public void setDetailsVisible(Boolean  visible){
        details.setVisible(visible);
    }

    public void switchView(int switchType){
        switch (switchType){
            case 1:
                productP.setVisible(true);
                qtyP.setVisible(true);
                titleP.setVisible(false);
                entrySizeP.setVisible(false);
                customerP.setVisible(false);
                timeP.setVisible(false);
                taxPayableP.setVisible(false);
                shippingTotalP.setVisible(false);
                discountP.setVisible(false);
                break;
            case 2:
                productP.setVisible(false);
                qtyP.setVisible(false);
                titleP.setVisible(true);
                entrySizeP.setVisible(true);
                customerP.setVisible(true);
                timeP.setVisible(true);
                taxPayableP.setVisible(true);
                shippingTotalP.setVisible(true);
                discountP.setVisible(true);
                break;

        }
    }
    public void setTicketDetailsVisible(Boolean  visible){
        details.setVisible(visible);
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        openIcon.addMouseListener(l);
        super.addMouseListener(l);
    }

    public OpenIcon getOpenIcon() {
        return openIcon;
    }

    class DisplayLabel extends JLabel{


        public DisplayLabel(String text) {
            super(text, SwingConstants.RIGHT);
            setForeground(Color.WHITE);
            setBorder(new EmptyBorder(0,0,0,3));
        }

        public DisplayLabel() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor(Global.colorScheme.getPrimaryColor());
            graphics2D.fillRect(0,0,getWidth(),getHeight());
            super.paintComponent(g);
        }
    }
}
