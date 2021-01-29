package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.product.Ticket;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.TicketDisplayItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReferenceDisplay extends JPanel implements ActionListener, MouseListener, DocumentListener {
    private JLabel textField;
    private JButton button;
    private JPopupMenu jPopupMenu;
    private JLabel title;
    private JTextField search;
    private JLabel selectNone;
    private TicketModelHeaderR headerR;
    private JPanel content;
    private boolean open;
    private HashMap<ProductComboBox.ListLabel,Integer> data;
    private String selected;
    private TicketModelRowS selectedLabel;
    private int type;


    public ReferenceDisplay(String title, int type) {
        this.type = type;
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        setPreferredSize(new Dimension(130,40));
        setLayout(layout);
        textField = new JLabel(""){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getQuaternaryColor());
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Global.colorScheme.getTertiaryColor());
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                super.paintComponent(g);
            }
        };
        textField.setBorder(new EmptyBorder(0,4,5,0));
        textField.setPreferredSize(new Dimension(100, 40));button = new JButton(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(30, 40);
            }

            @Override
            public Color getBackground() {
                return Global.colorScheme.getPrimaryColor();
            }

            @Override
            public boolean isBorderPainted() {
                return false;
            }

            @Override
            protected void paintComponent(Graphics g) {


                if (getModel().isPressed()) {
                    g.setColor(Global.colorScheme.getPrimaryColor());
                } else if (getModel().isRollover()) {
                    g.setColor(Global.colorScheme.getSecondaryColor());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                g.drawImage(Global.colorScheme.getdDButton().getImage(), 9,13,null);
            }
        };
        button.setPreferredSize(new Dimension(30, 40));
        button.addActionListener(this);
        add(textField);
        add(button);


        jPopupMenu = new JPopupMenu(){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getQuaternaryColor());
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                g2d.setColor(Color.GRAY);
                g2d.drawRoundRect(0,0,getWidth()-1,getHeight()-1,5,5);
            }
        };
        jPopupMenu.setOpaque(false);
        jPopupMenu.setLayout(new BorderLayout());
        jPopupMenu.setBorderPainted(false);
        jPopupMenu.setBorderPainted(false);
        jPopupMenu.setPreferredSize(new Dimension(600,400));
        search = new JTextField(){

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Global.colorScheme.getQuaternaryColor());
                g.fillRect(0,0,getWidth(),getHeight());
                g.setColor(Global.colorScheme.getTertiaryColor());
                g.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                super.paintComponent(g);
            }

        };
        search.getDocument().addDocumentListener(this);
        search.setPreferredSize(new Dimension(200,30));
        search.setBorder(new EmptyBorder(0,10,0,0));
        search.setBackground(Global.colorScheme.getSecondaryColor());
        selectNone = new JLabel();
        headerR = new TicketModelHeaderR();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        content = new JPanel();
        content.setPreferredSize(new Dimension(0, Global.sales.getTickets().size() * 45));
        content.setOpaque(false);


        //North Area
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        north.setOpaque(false);
        north.setPreferredSize(new Dimension(600, 50));
        this.title = new JLabel(title);
        this.title.setFont(FontsList.getHelvetica(Font.PLAIN, 20));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setPreferredSize(new Dimension(370, 50));
        searchPanel.setPreferredSize(new Dimension(200,50));
        titlePanel.setOpaque(false);
        searchPanel.setOpaque(false);
        titlePanel.add(this.title);
        searchPanel.add(search);
        north.add(titlePanel);
        north.add(searchPanel);

        //Center Area
        JPanel centerP = new JPanel(new BorderLayout());
        centerP.add(headerR, BorderLayout.NORTH);
        JScrollPane centerSP = new JScrollPane(content, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerSP.setOpaque(false);
        centerSP.setBorder(BorderFactory.createEmptyBorder());
        Helper.setVerticalScrollBarColor(centerSP, Global.colorScheme.getQuinaryColor());
        centerSP.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        centerSP.getVerticalScrollBar().setUnitIncrement(5);
        centerSP.setPreferredSize(new Dimension(550, 300));
        centerP.add(centerSP, BorderLayout.CENTER);

        //South Area
        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setPreferredSize(new Dimension(600, 40));
        south.add(selectNone);

        //Padding
        JPanel paddingLeft = new JPanel();
        JPanel paddingRight = new JPanel();
        paddingLeft.setOpaque(false);
        paddingRight.setOpaque(false);
        paddingLeft.setPreferredSize(new Dimension(30, 400));
        paddingRight.setPreferredSize(new Dimension(15, 400));

        jPopupMenu.add(north, BorderLayout.NORTH);
        jPopupMenu.add(centerP, BorderLayout.CENTER);
        jPopupMenu.add(south, BorderLayout.SOUTH);
        jPopupMenu.add(paddingLeft, BorderLayout.WEST);
        jPopupMenu.add(paddingRight, BorderLayout.EAST);

        populateContent();
    }


    private void populateContent(){

        if(type == 0)
            for (Map.Entry<String,TicketModel> entry : Global.sales.getTickets().entrySet()){
            TicketModelRowS ticketModelRowS= new TicketModelRowS(entry.getValue());
            ticketModelRowS.setPreferredSize(new Dimension(550, 40));
            ticketModelRowS.addMouseListener(this);
            content.add(ticketModelRowS);
        }
        else{
            for (Map.Entry<String,TicketModel> entry : Global.purchases.getTickets().entrySet()){
                TicketModelRowS ticketModelRowS= new TicketModelRowS(entry.getValue());
                ticketModelRowS.setPreferredSize(new Dimension(550, 40));
                ticketModelRowS.addMouseListener(this);
                content.add(ticketModelRowS);
            }
        }

    }
    public  void search(String pattern){
        int count = 0;
        for ( Component component : content.getComponents()){
            TicketModelRowS ticketModelRowS = (TicketModelRowS) component;
            component.setVisible(true);
            boolean found = false;
            if(!check(pattern, ticketModelRowS.getTicket())){
                ticketModelRowS.setVisible(false);
                count++;
            }
        }
        content.setPreferredSize(new Dimension(0,(Global.sales.getTickets().size() - count)*45));

    }
    private boolean check(String pattern, TicketModel entry){
        pattern = pattern.trim().toLowerCase();
        if (pattern.length() != 0){
            boolean found = false;
            if(Helper.KMPSearch(pattern,entry.getId().trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,entry.getCustomer().trim().toLowerCase()))
                found = true;
            if(Helper.KMPSearch(pattern,entry.getDate().toString().trim().toLowerCase()))
                found = true;

            return found;
        }
        else return  true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!open){
            jPopupMenu.show(this, 120,-200);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() instanceof StockHeaderLabel){
            TicketModelRowS rowR = (TicketModelRowS) ((StockHeaderLabel) e.getSource()).getParent();
            selected = rowR.getTicketID().getText();
            textField.setText(rowR.getTicketID().getText());
            if(selectedLabel!= null){
                selectedLabel.setSelected(false);
                selectedLabel.turnOff();
                selectedLabel.repaint();
            }
            rowR.setSelected(true);
            selectedLabel = rowR;
            jPopupMenu.setVisible(false);
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

    @Override
    public void insertUpdate(DocumentEvent e) {
        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            content.revalidate();
            content.repaint();

        }
        catch (BadLocationException el){

        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            content.revalidate();
            content.repaint();


        }
        catch (BadLocationException el){

        }
    }
    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
