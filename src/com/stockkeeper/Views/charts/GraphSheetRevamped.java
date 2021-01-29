package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.ExpandIcon;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class GraphSheetRevamped extends Sheet implements MouseMotionListener, MouseListener, MouseWheelListener,AdjustmentListener {

    private ExpandIcon expandIcon;
    private Color background;
    private GraphSheetEnlarged enlarged;
    private HashMap<Rectangle2D, Map.Entry<String, Map.Entry<Long, Color>>> graphBars = new HashMap<>();
    private HashMap<Rectangle2D, Map.Entry<String, Map.Entry<Double, Color>>> graphBarsDouble = new HashMap<>();


    public GraphSheetRevamped(HashMap<String,Double> graphData, double buffer) {
        super(graphData, buffer);
        enlarged = new GraphSheetEnlarged(graphData, "Stock", buffer);
        setGraphData(graphData, buffer);
        initExpandIcon();
        initListeners();
        isDouble = true;
    }

    public void add(Map.Entry<String, Double> entry, double buffer){
        this.graphDataDouble.put(entry.getKey(), entry.getValue());
        this.displayData.put(entry.getKey(), entry.getValue());
        int width = graphDataDouble.size() * 55 + 10;
        setPreferredSize(new Dimension(width ,230));
        this.largestDouble = getLargest(0.0);
    }
    private void initExpandIcon(){
        expandIcon = new ExpandIcon();
        expandIcon.addMouseListener(this);
        add(expandIcon);
        Insets insets = getInsets();
        Dimension size = expandIcon.getPreferredSize();
        expandIcon.setBounds(320 + insets.left, 10 + insets.top,
                size.width, size.height);
    }

    public void setGraphData(HashMap<String,Double> graphData, double buffer){
        this.graphDataDouble = graphData;
        displayData = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String e1,
                               String e2) {
                return e1.compareTo(e2);
            }
        });
        displayData.putAll(graphDataDouble);

        this.largestDouble = getLargest(0.0);
        this.enlarged.refreshGraph(graphData, 0.0);
        int width = graphData.size() * 55 + 10;
        setPreferredSize(new Dimension(width ,230));
        isDouble = true;
    }
    public void sort(Comparator<String> comparator, int sortType){
        setSortType(sortType);
        this.enlarged.sort(comparator, sortType);
        this.displayData = new TreeMap<>(comparator);
        displayData.putAll(this.graphDataDouble);
        repaint();
    }

    private void initListeners(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        jScrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        double max;
        if(isDouble){
            max = roundLargest(0.0);
        }
        else {
            max = roundLargest();
        }

        Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);

        g2d.fillRect(0,0,getWidth(),getHeight());
        drawInitialLinesandStrings(g2d, max,10);
        if( graphDataDouble.size() != 0)
        drawGraphBars(g, max);
    }


    private void drawGraphBars(Graphics g, double max){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        double ratio;
        if (max != 0){
            ratio = (200 / max);
        }
        else { ratio = 1;
        }
        int xPosition = 20;
        int count = 0;
        graphBarsDouble.clear();
        if(sortType == 0)
            if(isDouble){
                for (Map.Entry<String, Double> item : displayData.entrySet()){
                    Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                    g2d.setColor(barColor);
                    Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),40, (item.getValue() * ratio));
                    g2d.fill(bar);
                    g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                    graphBarsDouble.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                    xPosition += 50;
                    count++;
                }
            }
            else{

                for (Map.Entry<String, Long> item : graphData){
                    Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                    g2d.setColor(barColor);
                    Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),40, (item.getValue() * ratio));
                    g2d.fill(bar);
                    g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                    graphBars.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                    xPosition += 50;
                    count++;
                }
            }
            else
                if(isDouble){
                    for (Map.Entry<String, Double> item : displayData.descendingMap().entrySet()){
                        Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                        g2d.setColor(barColor);
                        Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),40, (item.getValue() * ratio));
                        g2d.fill(bar);
                        g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                        graphBarsDouble.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                        xPosition += 50;
                        count++;
                    }

                }
                else{
                    for (Map.Entry<String, Long> item : graphData){
                        Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                        g2d.setColor(barColor);
                        Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),40, (item.getValue() * ratio));
                        g2d.fill(bar);
                        g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                        graphBars.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                        xPosition += 50;
                        count++;
                    }
                }


    }

    public GraphSheetEnlarged getEnlarged() {
        return enlarged;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double scale = 1 + Math.abs(e.getPreciseWheelRotation()/4);
        if (e.isControlDown())
        {
            if(e.getPreciseWheelRotation() < 0){
                this.scale *= scale;
                jScrollPane.getViewport().setViewPosition(new Point(0, getHeight() * 2));
                repaint();
                revalidate();
            }
            else{
                this.scale /= scale;
                jScrollPane.getViewport().setViewPosition(new Point(0, getHeight() * 2));
                repaint();
                revalidate();

            }
        }
        else {
            jScrollPane.dispatchEvent(e);

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == expandIcon){

            enlarged.getGraphSheetEnlarged().getFilterScrollPane().setVisible(false);
            enlarged.getGraphSheetEnlarged().refreshGraph();
            enlarged.show(this, e.getX()-500,e.getY()-50);

        }

    }

    @Override
    public Color getBackground() {
        return background;
    }

    @Override
    public void setBackground(Color background) {
        this.background = background;
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        boolean found2 = false;
        if (isDouble){
            for (Map.Entry<Rectangle2D, Map.Entry<String, Map.Entry<Double,Color>>> entry : graphBarsDouble.entrySet()) {
                Rectangle2D bar = entry.getKey();
                String title = entry.getValue().getKey();
                double qty = entry.getValue().getValue().getKey();
                Color color = entry.getValue().getValue().getValue();
                boolean withinBar = bar.contains(e.getX(),e.getY());
                if (withinBar)
                {
                    found = true;
                    barDetails.setPrimary(title);
                    barDetails.setSecondary(""+qty);
                    barDetails.setColor(color);
                    barDetails.show(this, e.getX()-30,e.getY());
                }
            }
        }
        else{
            for (Map.Entry<Rectangle2D, Map.Entry<String, Map.Entry<Long,Color>>> entry : graphBars.entrySet()) {
                Rectangle2D bar = entry.getKey();
                String title = entry.getValue().getKey();
                long qty = entry.getValue().getValue().getKey();
                Color color = entry.getValue().getValue().getValue();
                boolean withinBar = bar.contains(e.getX(),e.getY());
                if (withinBar)
                {
                    found = true;
                    barDetails.setPrimary(title);
                    barDetails.setSecondary(""+qty);
                    barDetails.setColor(color);
                    barDetails.show(this, e.getX()-30,e.getY());
                }
            }
        }


        for(Map.Entry<Rectangle2D, Double> line2D : lines.entrySet()){
            boolean withinBar = line2D.getKey().contains(e.getX(),e.getY());
            if (withinBar)
            {
                found2 = true;
                abcissaValue.setText(line2D.getValue().toString());
                abcissaPanel.show(this,e.getX()-30,(int)line2D.getKey().getY() -12);
            }
        }
        if (!found)
        {
            barDetails.setVisible(false);
            repaint();
        }
        if (!found2)
        {
            abcissaPanel.setVisible(false);
            repaint();
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Insets insets = getInsets();
        Dimension size = expandIcon.getPreferredSize();
        expandIcon.setBounds(320 + insets.left + e.getValue(), 10 + insets.top,
                size.width, size.height);
    }
}
