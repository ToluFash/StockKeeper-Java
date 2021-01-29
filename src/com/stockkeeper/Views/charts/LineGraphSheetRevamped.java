package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.ExpandIcon;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.fancytextinput.SScrollPane;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

public class LineGraphSheetRevamped extends Sheet implements MouseMotionListener, MouseListener, MouseWheelListener, AdjustmentListener {

    private ExpandIcon expandIcon;
    private int displayType;
    private LineGraphSheetEnlarged enlarged;
    private HashMap<RoundRectangle2D, Map.Entry<Date, Double>> graphBarsDouble = new HashMap<>();


    public LineGraphSheetRevamped(HashMap<Date,Double> graphData, int displayType) {
        super(graphData);
        enlarged = new LineGraphSheetEnlarged(graphData, "Stock",displayType);
        setGraphData(graphData, displayType);
        initExpandIcon();
        initListeners();
        isDouble = true;
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

    private void initListeners(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        jScrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
    }

    public void setGraphData(HashMap<Date,Double> graphData, int displayType) {
        this.graphDataDateDouble = graphData;
        this.displayType = displayType;
        displayDataDate = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date e1,
                               Date e2) {
                return e1.compareTo(e2);
            }
        });
        displayDataDate.putAll(graphDataDateDouble);
        this.largestDouble = getLargest();
        enlarged.refreshGraph(graphData);
        int width = graphData.size() * 20 + 10;
        setPreferredSize(new Dimension(width ,230));
        isDouble = true;

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
        drawInitialLinesandStrings(g, max);
        drawGraphLines(g, max);
    }

     private void drawGraphLines(Graphics g, double max){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setColor(Global.colorScheme.getGraphColor());
         g2d.setStroke(new BasicStroke(2));
             if(sortType == 0){
                 draw(g2d, max, displayDataDate.entrySet());
             }
             else{
                 draw(g2d, max, displayDataDate.descendingMap().entrySet());
             }
     }

     private void draw(Graphics2D g2d, double max, Set<Map.Entry<Date,Double>> displaySet){
         int height = getHeight();
         double ratio;
         if (max != 0){
             ratio = (200 / max);
         }
         else { ratio = 1;
         }
         int endx = 0;
         double endy = (height-10);
         int position = 0;
         boolean start = false;
         for (Map.Entry<Date, Double> item : displaySet){
             if(item.getValue() > 0){
                 start = true;
             }
             if(start){
                 Line2D line2D;
                 if (position == 0){
                     RoundRectangle2D rr2d = new RoundRectangle2D.Double(endx+12,(height-13) -(item.getValue() * ratio),8,8,8,8);
                     endx += 15;
                     endy = (height-10) -(item.getValue() * ratio);
                     if(item.getValue() > 0)
                         g2d.fill(rr2d);
                     graphBarsDouble.put(rr2d, item);
                 }
                 else{
                     line2D = new Line2D.Double(endx, endy, endx+20, (height-10) -(item.getValue() * ratio));
                     RoundRectangle2D rr2d = new RoundRectangle2D.Double(endx+16.5,(height-13) -(item.getValue() * ratio),8,8,8,8);
                     endx += 20;
                     endy = (height-10) -(item.getValue() * ratio);
                     g2d.draw(line2D);
                     if(item.getValue() > 0)
                         g2d.fill(rr2d);
                     graphBarsDouble.put(rr2d, item);
                 }
                 position++;
             }
             else {

             }
         }
     }

    public LineGraphSheetEnlarged getEnlarged() {
        return enlarged;
    }

    public SScrollPane getjScrollPane() {
        return jScrollPane;
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

    protected void drawInitialLinesandStrings(Graphics g, double max){
        lines.clear();
        int width = getWidth();
        int height = getHeight();
        int interval = 10;
        int yPosition = (height -25);
        yPosition = (height -10);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        g2d.setFont(FontsList.getSansSerif(Font.BOLD ,10));
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
    @Override
    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        boolean found2 = false;

            for (Map.Entry<RoundRectangle2D, Map.Entry<Date, Double>> entry : graphBarsDouble.entrySet()) {
                RoundRectangle2D  bar = entry.getKey();
                String title;
                if(displayType == 0){
                    title = entry.getValue().getKey().getHours() +":00 - " + (entry.getValue().getKey().getHours()+1+":00" );
                    if(entry.getValue().getKey().getHours() == 23)
                        title = entry.getValue().getKey().getHours() +":00 - " + entry.getValue().getKey().getHours() +":59";
                }
                else{
                    title = entry.getValue().getKey().toString();
                }
                double qty = entry.getValue().getValue();
                boolean withinBar = bar.contains(e.getX(),e.getY());
                if (withinBar)
                {
                    found = true;
                    barDetails.setPrimary(title);
                    barDetails.setSecondary(""+qty);
                    barDetails.setColor(Color.RED);
                    barDetails.show(this, e.getX()-30,e.getY());
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
