package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.fancytextinput.OpenIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class ReturnsInPanel extends JPanel implements Details{


    private JLabel title;
    private JLabel product;
    private JLabel qty;
    private JLabel entrySize;
    private JLabel reference;
    private JLabel time;
    private JPanel details;
    private JPanel graphPanel;
    private OneByTwoRow productP;
    private OneByTwoRow qtyP;
    private OneByTwoRow titleP;
    private OneByTwoRow entrySizeP;
    private OneByTwoRow referenceP;
    private OneByTwoRow timeP;
    private OpenIcon openIcon;

    public ReturnsInPanel(JLabel product, JLabel qty, JLabel title, JLabel entrySize, JLabel reference, JLabel time) {
        setPreferredSize(new Dimension(400,600));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Global.colorScheme.getTertiaryColor());
        details = new JPanel(new FlowLayout(FlowLayout.LEFT));
        details.setOpaque(false);
        details.setPreferredSize(new Dimension(400,200));
        add(details);
        this.product = product;
        this.qty = qty;
        this.title = title;
        this.entrySize = entrySize;
        this.reference = reference;
        this.time = time;

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

        this.productP = new OneByTwoRow(new JLabel("Product:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,25);
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
        this.qtyP = new OneByTwoRow(new JLabel("Quantity:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,25);
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
        this.titleP = new OneByTwoRow(new JLabel("Ticket No:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,25);
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
        this.entrySizeP = new OneByTwoRow(new JLabel("Size:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,25);
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
        this.referenceP = new OneByTwoRow(new JLabel("Reference:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,20);
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
        this.timeP = new OneByTwoRow(new JLabel("Time:"){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(80,25);
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

        this.graphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphPanel.setPreferredSize(new Dimension(380,300));
        graphPanel.setBackground(Global.colorScheme.getTertiaryColor());

        details.add(productP);
        details.add(qtyP);
        details.add(titleP);
        details.add(entrySizeP);
        details.add(referenceP);
        details.add(timeP);

        add(graphPanel);
        setBorder(new EmptyBorder(10,0,0,2));
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        openIcon.addMouseListener(l);
        super.addMouseListener(l);
    }

    public OpenIcon getOpenIcon() {
        return openIcon;
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

    public JLabel getreference() {
        return reference;
    }

    public void setreference(JLabel reference) {
        this.reference = reference;
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

    public void switchView(int switchType){
        switch (switchType){
            case 1:
                productP.setVisible(true);
                qtyP.setVisible(true);
                titleP.setVisible(false);
                entrySizeP.setVisible(false);
                referenceP.setVisible(false);
                timeP.setVisible(false);
                break;
            case 2:
                productP.setVisible(false);
                qtyP.setVisible(false);
                titleP.setVisible(true);
                entrySizeP.setVisible(true);
                referenceP.setVisible(true);
                timeP.setVisible(true);
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
}
