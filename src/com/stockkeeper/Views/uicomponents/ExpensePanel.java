package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.OpenIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class ExpensePanel extends JPanel implements  Details {


    private JLabel title;
    private JLabel amount;
    private JLabel reference;
    private JLabel supplier;
    private JLabel time;
    private JLabel staff;
    private JPanel details;
    private JPanel graphPanel;
    private OneByTwoRow amountP;
    private OneByTwoRow referenceP;
    private OneByTwoRow titleP;
    private OneByTwoRow supplierP;
    private OneByTwoRow timeP;
    private OneByTwoRow staffP;
    private OpenIcon openIcon;

    public ExpensePanel(JLabel amount, JLabel reference, JLabel title, JLabel supplier, JLabel time, JLabel staff) {
        setPreferredSize(new Dimension(400,600));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Global.colorScheme.getTertiaryColor());
        details = new JPanel(flowLayout);
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(400,200));
        add(details);
        this.title = title;
        this.amount = amount;
        this.reference = reference;
        this.supplier = supplier;
        this.time = time;
        this.staff = staff;

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
        this.titleP = new OneByTwoRow(new DisplayLabel("Title"){
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
        this.amountP = new OneByTwoRow(new DisplayLabel("Amount"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(75,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.amount){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.referenceP = new OneByTwoRow(new DisplayLabel("Reference"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.reference){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };
        this.supplierP = new OneByTwoRow(new DisplayLabel("Supplier"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.supplier){


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
        this.staffP = new OneByTwoRow(new DisplayLabel("Staff"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(74,20);
            }

            @Override
            public Font getFont() {
                return FontsList.getHelvetica(Font.BOLD, 12);
            }
        }, this.staff){


            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400,25);
            }
        };

        this.graphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphPanel.setPreferredSize(new Dimension(380,300));
        graphPanel.setBackground(Global.colorScheme.getTertiaryColor());

        details.add(titleP);
        details.add(amountP);
        details.add(referenceP);
        details.add(supplierP);
        details.add(timeP);
        details.add(staffP);

        add(graphPanel);
        setBorder(new EmptyBorder(10,0,0,2));
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JLabel getSupplier() {
        return supplier;
    }

    public void setSupplier(JLabel supplier) {
        this.supplier = supplier;
    }

    public JLabel getTime() {
        return time;
    }

    public void setTime(JLabel time) {
        this.time = time;
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

    public JLabel getAmount() {
        return amount;
    }

    public void setAmount(JLabel amount) {
        this.amount = amount;
    }

    public JLabel getReference() {
        return reference;
    }

    public void setReference(JLabel reference) {
        this.reference = reference;
    }

    public JLabel getStaff() {
        return staff;
    }

    public void setStaff(JLabel staff) {
        this.staff = staff;
    }

    public void switchView(int switchType){
        switch (switchType){
            case 1:
                amountP.setVisible(true);
                referenceP.setVisible(true);
                titleP.setVisible(false);
                supplierP.setVisible(false);
                timeP.setVisible(false);
                staffP.setVisible(false);
                break;
            case 2:
                amountP.setVisible(false);
                referenceP.setVisible(false);
                titleP.setVisible(true);
                supplierP.setVisible(true);
                timeP.setVisible(true);
                staffP.setVisible(true);
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
