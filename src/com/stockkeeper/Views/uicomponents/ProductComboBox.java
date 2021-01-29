package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class ProductComboBox extends JPanel implements ActionListener, MouseListener{

    private JLabel textField;
    private JButton button;
    private JPopupMenu popupMenu;
    private JPanel container;
    private JScrollPane jScrollPane;
    private Object[] items;
    private HashMap<String, Integer> itemsIndex;
    private boolean open;
    private HashMap<ListLabel,Integer> data;
    private int selected;
    private ListLabel selectedLabel;




    public ProductComboBox(Object[] items) {
        open = false;
        data= new HashMap<>();
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
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawRoundRect(0,0,getWidth()+2,getHeight()-1,5,5);
                super.paintComponent(g);
            }
        };
        textField.setBorder(new EmptyBorder(0,4,5,0));
        textField.setPreferredSize(new Dimension(100, 25));
        button = new JButton(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(30, 25);
            }

            @Override
            public Color getBackground() {
                return Color.LIGHT_GRAY;
            }

            @Override
            public boolean isBorderPainted() {
                return false;
            }

            @Override
            protected void paintComponent(Graphics g) {


                if (getModel().isPressed()) {
                    g.setColor(Color.LIGHT_GRAY);
                } else if (getModel().isRollover()) {
                    g.setColor(Color.GRAY);
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
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(BorderFactory.createEmptyBorder());
        container = new JPanel(layout);
        container.setBackground(Global.colorScheme.getTertiaryColor());
        jScrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        setVerticalScrollBarColor(jScrollPane, Global.colorScheme.getPrimaryColor());
        popupMenu.add(jScrollPane);
        this.items = items;
        itemsIndex = new HashMap<>();
        for (int x=0; x < items.length; x++){
            itemsIndex.put(items[x].toString(), x);
        }
        initContainer();
    }

    private  void initContainer(){
         int height =  items.length * 25;
         if(height > 320){
             jScrollPane.setPreferredSize(new Dimension(130, 320));
             container.setPreferredSize(new Dimension(130, height));
         }
         else{
             jScrollPane.setPreferredSize((new Dimension(130, height)));
             container.setPreferredSize((new Dimension(130, height)));
         }

         int count = 0;
         for (Object object : items){
             String string = object.toString();
             ListLabel label = new ListLabel(string);
             label.setPreferredSize(new Dimension(130, 25));
             label.addMouseListener(this);
             container.add(label);
             data.put(label, count);
             count++;
         }
         sortContainer();
         selectedLabel = (ListLabel) container.getComponent(0);
         selectedLabel.setSelected(true);
         selected = 0;
         textField.setText(selectedLabel.getText());
    }

    private  void sortContainer(){
        for ( int index = 1; index  < container.getComponents().length; index++ ){

            int position = index;
            ListLabel listLabel = (ListLabel) container.getComponent(index);
            JLabel jLabel;

            while (position > 0 && ((ListLabel)container.getComponent(position-1)).getText().compareTo(listLabel.getText()) >0){
                container.add(container.getComponent(position - 1), position);
                position = position -1;
            }
            container.add(listLabel, position);
        }
    }
    public int getSelectedIndex(){
    return  itemsIndex.get(selectedLabel.getText());
    }
    public Object getSelectedItem(){

    return  items[getSelectedIndex()];

    }

    public void addActionListener(ActionListener l){



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!open){
            popupMenu.show(this, 0,40);
        }

    }
    protected void setVerticalScrollBarColor(JScrollPane jScrollPane, Color color){
        jScrollPane.getVerticalScrollBar().setUI(
                new BasicScrollBarUI(){


                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        return new JButton()
                        {
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension(0, 0);
                            }

                            @Override
                            public Color getBackground() {
                                return Global.colorScheme.getQuinaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getQuinaryColor());
                                } else if (getModel().isRollover()) {
                                    g.setColor(Global.colorScheme.getSenaryColor());
                                } else {
                                    g.setColor(getBackground());
                                }
                                g.fillRect(0, 0, getWidth()-1, getHeight());
                                g.drawImage(Global.colorScheme.getSbButtonUp().getImage(), 3,3,null);
                            }
                        };
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation) {


                        return new JButton()
                        {
                            @Override
                            public Dimension getPreferredSize() {
                                return new Dimension(0, 0);
                            }

                            @Override
                            public Color getBackground() {
                                return Global.colorScheme.getQuinaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {


                                if (getModel().isPressed()) {
                                    g.setColor(Global.colorScheme.getQuinaryColor());
                                } else if (getModel().isRollover()) {
                                    g.setColor(Global.colorScheme.getSenaryColor());
                                } else {
                                    g.setColor(getBackground());
                                }
                                g.fillRect(0, 0, getWidth()-1, getHeight());
                                g.drawImage(Global.colorScheme.getSbButtonDown().getImage(), 2,3,null);
                            }
                        };
                    }
                });


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof  ListLabel){
            ListLabel listLabel = (ListLabel) e.getSource();
            selected = data.get(listLabel);
            textField.setText(listLabel.getText());
            selectedLabel.setSelected(false);
            selectedLabel.repaint();
            listLabel.setSelected(true);
            selectedLabel = listLabel;
            popupMenu.setVisible(false);
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

    class ListLabel extends JLabel implements MouseListener {

        private Color bg;
        private boolean selected;

        public ListLabel(String s) {
            super(s);
            bg = Global.colorScheme.getTertiaryColor();
            selected = false;
            addMouseListener(this);
            setBorder(new EmptyBorder(0,4,0,0));
        }


        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if(selected){
                g2d.setColor(Global.colorScheme.getPrimaryColor());
                bg = Global.colorScheme.getTertiaryColor();
            }
            else{
                g2d.setColor(bg);
            }
            g2d.fillRect(0,0,getWidth(),getHeight());
            super.paintComponent(g);
        }


        public Color getBg() {
            return bg;
        }

        public void setBg(Color bg) {
            this.bg = bg;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            selected = true;

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

            bg = Global.colorScheme.getPrimaryColor();
            this.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            bg = Global.colorScheme.getTertiaryColor();
            this.repaint();
        }
    }
}
