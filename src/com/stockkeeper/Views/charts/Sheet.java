package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.GraphItemDetail;
import com.stockkeeper.Views.uicomponents.fancytextinput.SScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

public class Sheet extends JPanel{

    //JScrollPane
    protected SScrollPane jScrollPane;

    //VerticalBarChart Data
    protected ArrayList<Map.Entry<String,Long>> graphData;
    protected HashMap<String,Double> graphDataDouble;
    protected HashMap<Date,Double> graphDataDateDouble;
    protected TreeMap<String, Double> displayData;
    protected TreeMap<Date, Double> displayDataDate;
    protected long largest;
    protected double largestDouble;
    protected HashMap<RoundRectangle2D, Map.Entry<String, Long>> graph = new HashMap<>();
    //VerticalBarChart Helpers
    protected double scale;
    //Bar Details Pop Up
    protected GraphItemDetail barDetails;
    protected JLabel titleLabel;
    protected JLabel quantityLabel;
    // Lines
    protected HashMap<Rectangle2D, Double> lines;
    protected JPopupMenu abcissaPanel;
    protected JLabel abcissaValue;
    // Bar Colors
    protected Color[] colors;
    // Dimensions
    protected Dimension dimension;
    protected boolean isDouble;
    //Sorter
    protected  int sortType = 0;


    public Sheet(HashMap<String,Double> graphData, double buffer) {
        setLayout(null);
        setBackground(Color.WHITE);
        initScrollPane();
        initBarDetails();
        initLineDetails();
        initColors();
        setScale(1);
    }

    public Sheet(HashMap<Date,Double> graphData) {
        setLayout(null);
        setBackground(Color.WHITE);
        initScrollPane();
        initBarDetails();
        initLineDetails();
        initColors();
        setScale(1);
    }

    protected void initScrollPane(){
        jScrollPane = new SScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setColor(getBackground());
                graphics2D.fillRoundRect(0,0,getWidth()-3,getHeight()-2,10,10);
            }
        };
        jScrollPane.setPreferredSize(new Dimension(353,235));
        jScrollPane.setBackground(Color.WHITE);
        setHorizontalScrollBarColor(jScrollPane,Global.colorScheme.getNonaryColor());
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(5);
        jScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0,5));
    }


    protected  void initBarDetails(){

        barDetails = new GraphItemDetail();

        titleLabel = new JLabel();
        quantityLabel = new JLabel();
        this.barDetails.setLayout(new GridLayout(2,1));
        this.barDetails.add(titleLabel);
        this.barDetails.add(quantityLabel);
        titleLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        quantityLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        titleLabel.setForeground(new Color(0xffffff));
        quantityLabel.setForeground(new Color(0xffffff));
        this.barDetails.setBackground(Global.colorScheme.getSenaryColor());
        this.barDetails.setBorder( new EmptyBorder(10,5,10,5));
        barDetails.setVisible(false);

    }

    protected  void initLineDetails(){
        lines = new HashMap<>();
        abcissaPanel = new JPopupMenu(){
        };
        abcissaPanel.setOpaque(false);
        abcissaPanel.setBorderPainted(false);
        abcissaValue = new JLabel();
        abcissaValue.setFont(FontsList.getSitkaBanner(Font.BOLD, 12));
        abcissaPanel.add(abcissaValue);
        abcissaPanel.setVisible(false);
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }


    public void add(Map.Entry<String, Double> entry, double buffer){
        this.graphDataDouble.put(entry.getKey(), entry.getValue());
        this.displayData.put(entry.getKey(), entry.getValue());
        this.largestDouble = getLargest(0.0);
    }
    public void add(Map.Entry<Date, Double> entry){
        this.graphDataDateDouble.put(entry.getKey(), entry.getValue());
        this.displayDataDate.put(entry.getKey(), entry.getValue());
        this.largestDouble = getLargest();
    }
    public void modify(Map.Entry<String, Double> entry, double buffer){
        try{
            this.graphDataDouble.get(entry.getKey());
            this.graphDataDouble.replace(entry.getKey(), entry.getValue());
            this.displayData.get(entry.getKey());
            this.displayData.replace(entry.getKey(), entry.getValue());
        }
        catch (Exception e){
            this.graphDataDouble.put(entry.getKey(),entry.getValue());
            this.displayData.put(entry.getKey(),entry.getValue());
        }
    }
    public void modify(Map.Entry<Date, Double> entry){
        try{
            this.graphDataDateDouble.get(entry.getKey());
            this.graphDataDateDouble.replace(entry.getKey(), entry.getValue());
            this.displayDataDate.get(entry.getKey());
            this.displayDataDate.replace(entry.getKey(), entry.getValue());
        }
        catch (Exception e){
            this.graphDataDateDouble.put(entry.getKey(),entry.getValue());
            this.displayDataDate.put(entry.getKey(),entry.getValue());
        }
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public ArrayList<Map.Entry<String,Long>> getGraphData() {
        return graphData;
    }

    public HashMap<String,Double> getGraphDataDouble() {
        return graphDataDouble;
    }

    protected double getLargest(double buffer){
        double largest = 0;

        for (Map.Entry<String, Double> g : graphDataDouble.entrySet()){
            if(g.getValue() > largest)
                largest = g.getValue();
        }
        return largest;
    }

    protected double getLargest(){
        double largest = 0;

        for (Map.Entry<Date, Double> g : graphDataDateDouble.entrySet()){
            if(g.getValue() > largest)
                largest = g.getValue();
        }
        return largest;
    }

    protected long roundLargest(){
        int digits = Long.toString(largest).length();
        int power = (int) Math.pow(10, digits);
        int nines = power -1;
        long powered = ((largest + nines) / power) * power;

        if (powered != 0)
        {
            while(powered/2 > largest)
                powered /= 2;
            return powered;
        }
        else{
            return 10;
        }
    }


    protected long roundLargest(double buffer){
        long largest = (int)Math.ceil(largestDouble);
        int digits = Integer.toString((int)Math.ceil(largestDouble)).length();
        int power = (int) Math.pow(10, digits);
        int nines = power -1;
        long powered = ((largest + nines) / power) * power;

        if (powered != 0)
        {
            while(powered/2 > largest)
                powered /= 2;
            return powered;
        }
        else{
            return 10;
        }
    }


    protected void drawInitialLinesandStrings(Graphics g, double max){
        lines.clear();
        int width = getWidth();
        int height = getHeight();
        int interval = 10;
        int yPosition;
        yPosition = (height -10);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(FontsList.getSansSerif(Font.BOLD ,10));
        g2d.setColor(new Color(0x5E5E5E));
        g2d.setStroke(new BasicStroke(1));
        for(double x = 0; x < max +1; x+=max/4){
            Line2D line2D = new Line2D.Double(5, yPosition, width-7, yPosition);
            Rectangle2D rectangle2D = new Rectangle2D.Double(5,yPosition-4, width-7, 8);
            lines.put(rectangle2D, x);
            g2d.draw(line2D);
            yPosition -= interval*5;
        }
    }

    protected void drawInitialLinesandStrings(Graphics g, double max, int increment){
        lines.clear();
        int width = getWidth();
        int height = getHeight();
        int interval = 10;
        int yPosition;
        yPosition = (height -(10+increment));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(FontsList.getSansSerif(Font.BOLD ,10));
        g2d.setColor(new Color(0x5E5E5E));
        g2d.setStroke(new BasicStroke(1));
        for(double x = 0; x < max +1; x+=max/4){
            Line2D line2D = new Line2D.Double(5, yPosition, width-7, yPosition);
            Rectangle2D rectangle2D = new Rectangle2D.Double(5,yPosition-(4), width-7, 8);
            lines.put(rectangle2D, x);
            g2d.draw(line2D);
            yPosition -= interval*5;
        }
    }


    protected String getFormattedString(String string, Graphics graphics) {
        int optimal = 30;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        StringBuilder newString = new StringBuilder();
        if (string.length() >6) {
            for (int x = 0; x < 4; x++) {
                char c = string.charAt(x);
                newString.append(c);
            }
            newString.append("...");
            return newString.toString();
        }
        else
            return string;


    }

    protected void initColors(){
        colors = new Color[]{
                new Color(0xE07A6D),
                new Color(0x6CDAE0),
                new Color(0xB8E089),
                new Color(0xE04FD9),
                new Color(0x6D8DE0),
                new Color(0xE0444B),
                new Color(0xE09854),
                new Color(0x4353B9),
                new Color(0x82E0B2),
                new Color(0x77AFE0),
                new Color(0xA271E0),
                new Color(0xE0AA89),
                new Color(0x4489FE),
                new Color(0x87E02B),
                new Color(0x62B58A),
                new Color(0x5E89B5),
                new Color(0x845CB6),
                new Color(0xB4896E),
                new Color(0x3061B4),
                new Color(0x6CB423),
                new Color(0x69B48F)
        };
    }
    public SScrollPane getjScrollPane() {
        return jScrollPane;
    }


    protected void setHorizontalScrollBarColor(JScrollPane jScrollPane, Color color){
        jScrollPane.getHorizontalScrollBar().setUI(
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
                                g.drawImage(Global.colorScheme.getSbButtonRight().getImage(), 3,3,null);
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
                                g.drawImage(Global.colorScheme.getSbButtonLeft().getImage(), 2,3,null);
                            }
                        };
                    }
                });


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

    public void setVerticalScrollBarUnitIncrement(int unit) {
        jScrollPane.getVerticalScrollBar().setUnitIncrement(unit);
    }

    public void setHorizontalScrollBarUnitIncrement(int unit) {
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(unit);
    }

    public void setHorizontalScrollBarDecrButton(Color color) {
        jScrollPane.getHorizontalScrollBar().setUI(
                new BasicScrollBarUI() {
                    @Override
                    protected void configureScrollBarColors() {
                        this.decrButton.setBackground(color);
                    }
                });
    }






}
