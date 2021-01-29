package com.stockkeeper.Views.charts;

import com.stockkeeper.Views.uicomponents.fancytextinput.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Map;

public class PieSheet extends JPanel implements MouseMotionListener, MouseListener {


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
    private int total;
    private HashMap<Arc2D, Map.Entry<String, Integer>> graphArcs = new HashMap<>();
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
    private double diameter;


    public PieSheet(HashMap<String, Integer> graphData, int total, JPanel topPanel, JPanel bottomPanel, JPanel leftPanel, JPanel rightPanel) {
        this.graphData = graphData;
        this.total = total;
        setLayout(new FlowLayout());
        initDimension();
        setPreferredSize(dimension);
        setBackground(new Color(0xC5ECFF));
        initScrollPane();
        setVerticalScrollBarColor(new Color(0x85E7FF));
        setHorizontalScrollBarColor(new Color(0x85E7FF));
        initDimension();
        initHelperPanels(topPanel,bottomPanel,leftPanel,rightPanel);
        initBarDetails();
        initDiameter();
        initColors();
        initListeners();
        initTestData();
        setScale(1);
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
        this.total = 62;
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


    private void initDiameter(){
        diameter= 500;
    }



    public void setScale(int scale) {
        this.scale = scale;
    }


    @Override
    protected void paintBorder(Graphics g) {
        graphArcs.clear();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,    RenderingHints.VALUE_ANTIALIAS_ON);
        double startAngle = 0;
        double extent = 0;
        int color =0;
        int width = (int)(dimension.getWidth() * scale);
        int height = (int)(dimension.getHeight() * scale);

        double xInitialPosition = (double)getWidth()/2;
        double yInitialPosition = (double)getHeight()/2;

        double positionOffset = diameter/2;
        double circumference = diameter* Math.PI;
        double xPosition = xInitialPosition - positionOffset;
        double yPosition = yInitialPosition - positionOffset;
        double radius = diameter/2;
        double x;
        double y;

        Dimension newDimension = new Dimension(width,height);
        setPreferredSize(newDimension);
        int fontSize = (int)(12 * scale);
        if (fontSize > 6 && fontSize < 72)
            g.setFont(new Font("Century Gothic", Font.PLAIN, fontSize));
        else
            if (fontSize >= 72)
                g.setFont(new Font("Century Gothic", Font.PLAIN, 72));


        for (Map.Entry<String, Integer> entry : graphData.entrySet()) {
            Color barColor = colors[color % colors.length];
            g2d.setColor(barColor);
            extent = (double)entry.getValue()/total * 360;
            Arc2D pie = new Arc2D.Double(
                    xPosition,
                    yPosition,
                    diameter,
                    diameter,
                    startAngle,
                    extent,
                    Arc2D.PIE
                    );
            g2d.fill(pie);
            graphArcs.put(pie,entry);
            g.setColor(Color.WHITE);
            x = xInitialPosition + (radius/2) * Math.cos(Math.toRadians(startAngle+extent/2));
            y = yInitialPosition-(radius/2 * Math.sin(Math.toRadians(startAngle+extent/2)));
            double chordAtHalf= (Math.sin(extent*Math.PI/360) * radius);
            if (isFit(g, (int)chordAtHalf))
            if (fontSize > 6 )
                drawRotate(g2d,x, y,(360-(int)(startAngle + extent/2)),entry.getKey());
            startAngle += extent;
            color++;
        }
    }
    public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text)
    {
        if(angle <= 100 || angle >=260)
        {
            g2d.translate((float)x,(float)y);
            g2d.rotate(Math.toRadians(angle));
            g2d.drawString(text,0,0);
            g2d.rotate(-Math.toRadians(angle));
            g2d.translate(-(float)x,-(float)y);

        }
        else
        {
            g2d.translate((float)x,(float)y);
            g2d.rotate(Math.toRadians(180+angle));
            g2d.drawString(text,0,0);
            g2d.rotate(-Math.toRadians(180+angle));
            g2d.translate(-(float)x,-(float)y);


        }
    }

    private  boolean isFit(Graphics graphics, int height){
        return height > graphics.getFontMetrics().getHeight();
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
            this.diameter *= 2;
            this.scale *= 2;
            jScrollPane.getViewport().setViewPosition(new Point(0, getHeight() * 2));
            repaint();
            revalidate();

        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.diameter /= 2;
            this.scale /= 2;
            jScrollPane.getViewport().setViewPosition(new Point(0, getHeight() * 2));
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
        for (Map.Entry<Arc2D, Map.Entry<String, Integer>> entry : graphArcs.entrySet()) {
            Arc2D arc = entry.getKey();
            String title = entry.getValue().getKey();
            int qty = entry.getValue().getValue();
            boolean withinBar = arc.contains(e.getX(),e.getY());
            if (withinBar)
            {
                found = true;
                titleLabel.setText("Product: " +title);
                quantityLabel.setText("Quantity: "+ qty);
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
