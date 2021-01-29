package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Views.charts.VerticalBarChart;
import com.stockkeeper.Views.charts.VerticalBarChartPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaxDetailsPanel extends JPanel{


    private JLabel totalRevenue;
    private JLabel taxPayable;
    private JLabel averageRate;
    private JLabel highestGrossing;
    private JPanel details;
    private JPanel graphPanel;

    public TaxDetailsPanel(JLabel totalRevenue, JLabel taxPayable, JLabel averageRate, JLabel highestGrossing) {
        setPreferredSize(new Dimension(400,600));
        setBackground(Global.colorScheme.getTertiaryColor());
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(flowLayout);
        details = new JPanel(flowLayout);
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(400,150));
        add(details);
        this.totalRevenue = totalRevenue;
        this.taxPayable = taxPayable;
        this.averageRate = averageRate;
        this.highestGrossing = highestGrossing;
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
        details.add(new OneByTwoRow(new DisplayLabel("Total Revenue"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(102,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.totalRevenue){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        details.add(new OneByTwoRow(new DisplayLabel("Tax Payable"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(102,20);
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
        });
        details.add(new OneByTwoRow(new DisplayLabel("Average Rate"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(102,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.averageRate){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        details.add(new OneByTwoRow(new DisplayLabel("Highest Grossing"){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(102,20);
            }
            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.highestGrossing){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        });
        add(graphPanel);
        setBorder(new EmptyBorder(10,0,0,2));
    }

    public void setDetailsVisible(Boolean  visible){
        details.setVisible(visible);
    }
    public  void setChartsVisible(Boolean visible){
        graphPanel.setVisible(visible);
    }

    public JLabel getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(JLabel taxPayable) {
        this.taxPayable = taxPayable;
    }

    public JLabel getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(JLabel averageRate) {
        this.averageRate = averageRate;
    }

    public JLabel getHighestGrossing() {
        return highestGrossing;
    }

    public void setHighestGrossing(JLabel highestGrossing) {
        this.highestGrossing = highestGrossing;
    }

    public JPanel getDetails() {
        return details;
    }

    public void setDetails(JPanel details) {
        this.details = details;
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
