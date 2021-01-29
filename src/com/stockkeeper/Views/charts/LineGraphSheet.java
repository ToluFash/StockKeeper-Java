package com.stockkeeper.Views.charts;

import com.stockkeeper.Models.CustomDate;
import com.stockkeeper.Views.uicomponents.fancytextinput.*;
import com.stockkeeper.Models.product.Ticket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.*;

public class LineGraphSheet extends JPanel implements MouseMotionListener, MouseListener {


    /**
     *
     * DATA
     */

    //JScrollPane
    private SScrollPane jScrollPane;

    //Helper Panels
    private JPanel topPanel;
    private  JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    //VerticalBarChart Data
    private HashMap<String, Integer> graphData;
    private HashMap<CustomDate, LineGraphContainer> linePoints;
    private int largest;
    private HashMap<Arc2D, Map.Entry<CustomDate, LineGraphContainer>> graphBars = new HashMap<>();
    //VerticalBarChart Helpers
    private double scale;
    //Bar Details Pop Up
    private JPanel barDetails;
    private JLabel titleLabel;
    private JLabel quantityLabel;
    // Bar Colors
    private Color[] colors;
    // Dimensions
    private Dimension dimension;
    private ArrayList<Ticket> data;


    public LineGraphSheet(ArrayList<Ticket> data, int largest,JPanel topPanel, JPanel bottomPanel, JPanel leftPanel, JPanel rightPanel) {
        this.data = data;
        this.largest = largest;
        this.linePoints = new HashMap<>();
        setLayout(new FlowLayout());
        initDimension();
        setPreferredSize(dimension);
        setBackground(Color.WHITE);
        initScrollPane();
        setVerticalScrollBarColor(new Color(0x85E7FF));
        setHorizontalScrollBarColor(new Color(0x85E7FF));
        initDimension();
        initHelperPanels(topPanel,bottomPanel,leftPanel,rightPanel);
        initBarDetails();
        initColors();
        initListeners();
        initTestData();
        setScale(1);
        getDistribution(data);
    }

    private void getDistribution(ArrayList<Ticket> data){
        try{
            Date time = data.get(0).getDate().getTime();
            time.setHours(0);
            time.setMinutes(0);
            time.setSeconds(0);
            for (int x = 0; x < 24; x++){
                CustomDate time2 = new CustomDate(time.getTime());
                time2.setHours(x);
                linePoints.put(time2, new LineGraphContainer());
            }
            for (Ticket ticket: data){
                CustomDate time3 = new CustomDate(ticket.getDate().getTime().getTime());
                time3.setMinutes(0);
                time3.setSeconds(0);
                LineGraphContainer linepoint = linePoints.get(time3);
                linepoint.addItem(ticket);
            }
        }
        catch (Exception e){
            e.printStackTrace();

        }


    }

    private void initScrollPane(){
        jScrollPane = new SScrollPane(this);
    }

    private void initDimension(){
        dimension = new Dimension(1000, 531);
    }


    private void initHelperPanels(JPanel topPanel, JPanel bottomPanel, JPanel leftPanel, JPanel rightPanel){
        this.topPanel =topPanel;
        this.bottomPanel = bottomPanel;
        this.rightPanel = rightPanel;
        this.leftPanel = leftPanel;
    }
    private void initTestData(){
        this.graphData = new HashMap<>();
        this.graphData.put("Heineken", 4);
        this.graphData.put("Gulder", 2);
        this.graphData.put("Star", 3);
        this.graphData.put("Guiness", 1);
        this.graphData.put("Budweiser", 5);
        this.graphData.put("33", 5);
        this.graphData.put("Legend", 7);
        this.graphData.put("Maltina", 8);
        this.graphData.put("Malta Guiness", 3);
        this.graphData.put("Grand Malt", 6);
        this.graphData.put("1960", 5);
        this.graphData.put("Origin", 5);
        this.graphData.put("Trophy", 8);
        this.largest = 8;
    }

    private  void initBarDetails(){

        barDetails = new JPanel();
        titleLabel = new JLabel();
        quantityLabel = new JLabel();
        this.barDetails.setLayout(new GridLayout(2,1));
        this.barDetails.add(titleLabel);
        this.barDetails.add(quantityLabel);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.barDetails.setBackground(Color.WHITE);
        this.barDetails.setBorder( new EmptyBorder(10,5,10,5));
        add(barDetails);

    }

    private void initListeners(){
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void initColors(){
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
    public void setScale(int scale) {
        this.scale = scale;
    }


    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        int digits;
        int power;
        int max;
        double interval;

        int multiplier;
        //Number of digits in the largest Number for Scale
        digits = Integer.toString(largest).length();
        // Nearest (th)
        power = (int) Math.pow(10, digits);
        // Maximum on list
        max = (largest % power == 0 ? largest : (power - largest % power) + largest);
        //VerticalBarChart Significant Interval
        interval = (max / 10.0) / scale;
        //Interval Pixel ratio
        double intervalPixelRatio = (interval / 50.00);
        //Data and VerticalBarChart Ratio
        multiplier = max / 500;




        g2d.drawLine(0, 0, 0, getHeight());
        int count = 0;
        for (int y = getHeight() - 21; y > 0; y -= 10) {
            if (count % 5 != 0) {
                g2d.setColor(new Color(0xC7C4C8));
                g2d.drawLine(15, y, getWidth(), y);
            } else {
                g2d.setColor(new Color(0x919191));
                g2d.drawLine(13, y, getWidth(), y);
                String step = Double.toString(interval * count / 5);
                g2d.setFont(new Font("Arial", Font.PLAIN, 9));
                g2d.drawString(step, 2, y);
            }
            count++;
        }

        double preX = 0;
        double preY = 0;
        double spacing = 30 * scale;
        count = 0;
        g2d.setColor(Color.RED);
        for (Map.Entry<CustomDate, LineGraphContainer> entry : linePoints.entrySet()) {
            Color circleColor = colors[count % colors.length];
            g2d.setColor(circleColor);
            CustomDate date = entry.getKey();
            LineGraphContainer container = entry.getValue();
            double total = container.total;
            if(count == 0)
            {
                preX = spacing;
                preY = getBarHeight(container.total / intervalPixelRatio);
            }
            Line2D line = new Line2D.Double(preX, preY, spacing, getBarHeight(total/ intervalPixelRatio));
            Arc2D circle = new Arc2D.Double(
                    spacing-4,
                    getBarHeight(total / intervalPixelRatio)-4,
                    8,
                    8,
                    0,
                    360,
                    Arc2D.PIE
            );
            g2d.draw(line);
            g2d.setColor(circleColor);
            g2d.fill(circle);
            g2d.setColor(Color.RED);
            graphBars.put(circle,entry);
            preX = spacing;
            preY = getBarHeight(total / intervalPixelRatio);
            spacing += 60 * scale;
            count++;
            if (spacing > 980)
                setPreferredSize(new Dimension(getBarOffset(graphData)*(int)Math.ceil(scale), (dimension.height * (int) Math.ceil(scale))));
            else
                setPreferredSize(new Dimension(1000, (dimension.height * (int) Math.ceil(scale))));
        }
    }

    private String getFormattedString(String string, Graphics graphics) {
        int optimal = 50;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        StringBuilder newString = new StringBuilder();
        for (int x = 0; x < string.length(); x++) {
            if (optimal > 0) {
                char c = string.charAt(x);
                int cSize = fontMetrics.charWidth(c);
                newString.append(c);
                optimal -= cSize;
            } else {
                int length = newString.length();
                newString.deleteCharAt(length - 1);
                newString.deleteCharAt(length - 2);
                newString.deleteCharAt(length - 3);
                newString.append("...");
                break;
            }
        }
        return newString.toString();

    }

    private int getSpacingLeft(String string, Graphics graphics) {
        int optimal = 50;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int x = 0; x < string.length(); x++) {
            if (optimal > 0) {
                char c = string.charAt(x);
                int cSize = fontMetrics.charWidth(c);
                optimal -= cSize;
            }
        }
        if (optimal > 0)
            return optimal / 2;
        else
            return 0;

    }

    private  boolean checkSize(HashMap<String, Integer> graphData){
        int size = graphData.size();
        return (55*size)+30 < getWidth();
    }

    private  int getBarSpacing(HashMap<String, Integer> graphData){
        int size = graphData.size();
        return ((getWidth() - (55*size))/2) +15;
    }

    private  int getBarOffset(HashMap<String, Integer> graphData){
        int size = graphData.size();
        return (60*size) + 30;
    }

    private double getBarHeight(double height) {
        return (getHeight() - 21.00 - height);
    }

    private double getLabelHeight(double height) {
        return (getHeight() - 1.00 - height);
    }


    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }


    public void setVerticalScrollBarColor(Color color) {
        jScrollPane.getVerticalScrollBar().setUI(
                new BasicScrollBarUI() {
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }
                    protected JButton createDecreaseButton(int orientation){
                        return new SVDecreaseButton();
                    }
                    protected JButton createIncreaseButton(int orientation){
                        return new SVIncreaseButton();
                    }
                });
    }


    public void setHorizontalScrollBarColor(Color color) {
        jScrollPane.getHorizontalScrollBar().setUI(
                new BasicScrollBarUI() {
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }
                    protected JButton createDecreaseButton(int orientation){
                        return new SHDecreaseButton();
                    }
                    protected JButton createIncreaseButton(int orientation){
                        return new SHIncreaseButton();
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






    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.scale *= 2;
            jScrollPane.getViewport().setViewPosition(new java.awt.Point(0, getHeight() * 2));
            repaint();
            revalidate();

        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.scale /= 2;
            jScrollPane.getViewport().setViewPosition(new java.awt.Point(0, getHeight() * 2));
            repaint();
            revalidate();

        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            print(getGraphics());

        }

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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        for (Map.Entry<Arc2D, Map.Entry<CustomDate, LineGraphContainer>> entry : graphBars.entrySet()) {
            Arc2D bar = entry.getKey();
            CustomDate date = entry.getValue().getKey();
            LineGraphContainer container = entry.getValue().getValue();
            boolean withinBar = bar.contains(e.getX(),e.getY());
            if (withinBar)
            {
                found = true;
                titleLabel.setText("Product: " +date);
                quantityLabel.setText("Quantity: "+ container.total);
                barDetails.setVisible(true);
                barDetails.setLocation( e.getX(),e.getY());
            }
        }
        if (!found)
        {
            barDetails.setVisible(false);
            repaint();
        }
    }




}
