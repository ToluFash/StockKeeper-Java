package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DetailsPanel extends JPanel{


    private JLabel product;
    private JLabel stockLevel;
    private JLabel price;
    private JPanel sales;
    private JPanel details;
    private JPanel graphPanel;

    public DetailsPanel(JLabel product, JLabel stockLevel, JLabel price, JPanel sales) {
        setPreferredSize(new Dimension(400,600));
        setBackground(Global.colorScheme.getTertiaryColor());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(flowLayout);
        details = new JPanel(flowLayout);
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(400,150));
        add(details);
        this.product = product;
        this.stockLevel = stockLevel;
        this.price = price;
        this.sales = sales;
        this.graphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphPanel.setPreferredSize(new Dimension(380,400));
        graphPanel.add(new OneByTwoRow(new DetailsTitle("Charts"){

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 16);
            }
        }, new JLabel(){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(380,50);
            }
        },-7));
        graphPanel.setBackground(Global.colorScheme.getTertiaryColor());

        details.add(new OneByTwoRow(new DetailsTitle("Details"){

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 16);
            }
        }, new JLabel()){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,50);
            }
        });
        details.add(new OneByTwoRow(new DisplayLabel("Product"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50,20);
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
        });
        details.add(new OneByTwoRow(new DisplayLabel("Level"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.stockLevel){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        details.add(new OneByTwoRow(new DisplayLabel("Price"){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50,20);
            }
            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.price){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        details.add(new OneByTwoRowPanel(new DisplayLabel("Sales"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50,20);
            }
            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.sales){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        add(graphPanel);
        setBorder(new EmptyBorder(10,0,0,2));
    }

    public JLabel getProduct() {
        return product;
    }
    public void setDetailsVisible(Boolean  visible){
        details.setVisible(visible);
    }

    public void setProduct(JLabel product) {
        this.product = product;
    }

    public JLabel getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(JLabel stockLevel) {
        this.stockLevel = stockLevel;
    }

    public JLabel getPrice() {
        return price;
    }

    public void setPrice(JLabel price) {
        this.price = price;
    }

    public JPanel getSales() {
        return sales;
    }

    public void setSales(JPanel sales) {
        this.sales = sales;
    }


    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(JPanel graphPanel) {
        this.graphPanel = graphPanel;
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
    class DetailsLabel extends JLabel{


        public DetailsLabel(String text) {
            super(text, SwingConstants.LEFT);
            setForeground(Color.WHITE);
            setBorder(new EmptyBorder(0,2,0,0));
        }

        public DetailsLabel() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor(Global.colorScheme.getQuinaryColor());
            graphics2D.fillRect(0,0,getWidth(),getHeight());
            super.paintComponent(g);
        }
    }

    class DetailsTitle extends JLabel{


        public DetailsTitle(String text) {
            super(text);
            setPreferredSize(new Dimension(370,40));
        }


        @Override
        protected void paintComponent(Graphics g) {
            int width = Helper.getStringWidth(getText(), g.getFont(), g);
            System.out.println(width);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Global.colorScheme.getPrimaryColor());
            g2d.drawString(getText(),1, getHeight()/2 +6);
            g2d.drawLine(4 + width,getHeight()/2, getWidth() - 40, getHeight()/2);
            g2d.setColor(Color.WHITE);
            g2d.fillArc(getWidth()-40, 0, 40, getHeight(), 0, 360);
            g2d.setColor(Global.colorScheme.getPrimaryColor());
            g2d.fillArc(getWidth()-39, 1, 38, getHeight()-2, 0, 360);
        }

    }
}
