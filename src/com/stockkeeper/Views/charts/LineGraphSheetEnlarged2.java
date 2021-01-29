package com.stockkeeper.Views.charts;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.GraphFilterIcon;
import com.stockkeeper.Views.uicomponents.GraphPrintIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import java.util.concurrent.Semaphore;

public class LineGraphSheetEnlarged2 extends Sheet implements MouseMotionListener, MouseListener, MouseWheelListener, AdjustmentListener, ActionListener {

    private JLabel label2;
    private JPanel topPanel;
    private GraphFilterIcon filter;
    private int displayType;
    private GraphPrintIcon print;
    private HashMap<RoundRectangle2D, Map.Entry<String, Long>> graphBars = new HashMap<>();
    private HashMap<RoundRectangle2D, Map.Entry<Date, Double>> graphBarsDouble = new HashMap<>();
    private HashMap<String, JCheckBox> filterList;
    private JPanel filterPanel;
    private JScrollPane  filterScrollPane;
    private Insets insets;
    private Dimension dimension1;
    private Dimension dimension2;
    private Dimension dimension3;
    private HashMap<Date,Double> refreshedGraphData;
    private int count = 0;
    private Semaphore semaphore = new Semaphore(1);



    public LineGraphSheetEnlarged2(HashMap<Date,Double> graphData, String label, JPanel topPanel, int displayType) {
        super(graphData);
        this.topPanel = topPanel;
        initTitle(label);
        initFilter();
        setGraphData(graphData, displayType);
        initListeners();
        customizeScrollPane();
        initComponents();
        addMouseListener((this));
        isDouble = true;
    }

    private void initFilter(){
        filterList = new HashMap<>();
        filterPanel = new JPanel();
        filterPanel.setBackground(Global.colorScheme.getSecondaryColor());
        filterScrollPane = new JScrollPane(filterPanel);
        filterScrollPane.setPreferredSize(new Dimension(150,300));
        filterScrollPane.setBackground(Global.colorScheme.getSecondaryColor());
        filterScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        filterScrollPane.getVerticalScrollBar().setUnitIncrement(5);
        filterScrollPane.setBorder(BorderFactory.createLineBorder(Global.colorScheme.getPrimaryColor()));
        add(filterScrollPane);
        Dimension dimension = filterPanel.getPreferredSize();
        this.filterScrollPane.setBounds(870 + insets.left, 3,
                150, 300);
        setVerticalScrollBarColor(filterScrollPane, Global.colorScheme.getPrimaryColor());

        this.filterScrollPane.setVisible(false);
    }

    private void initTitle(String label){

        this.label2 = new JLabel(label);
        this.label2.setFont(FontsList.getAgencyFB(Font.PLAIN, 22));
        this.insets = topPanel.getInsets();
        topPanel.add(this.label2);
        dimension1= this.label2.getPreferredSize();
        this.label2.setBounds(500 + insets.left, 0,
                dimension1.width, dimension1.height);
    }

    private void initComponents(){

        this.filter = new GraphFilterIcon();
        this.topPanel.add(this.filter);
        dimension2 = this.filter.getPreferredSize();
        this.filter.setBounds(1030 + insets.left, 5+insets.top,
                dimension2.width, dimension2.height);

        this.print = new GraphPrintIcon();
        this.topPanel.add(this.print);
        dimension3 = this.print.getPreferredSize();
        this.print.setBounds(1060 + insets.left, 5+insets.top,
                dimension3.width, dimension3.height);

        this.filter.addMouseListener(this);

    }
    @Override
    public void add(Map.Entry<String, Double> entry, double buffer){
        this.graphDataDouble.put(entry.getKey(), entry.getValue());
        this.displayData.put(entry.getKey(), entry.getValue());
        this.largestDouble = getLargest(0.0);
        Dimension labelDimension = new Dimension(90,12);
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setBackground(Global.colorScheme.getSecondaryColor());
        jCheckBox.setSelected(true);
        jCheckBox.addActionListener(this);
        JLabel jLabel = new JLabel(entry.getKey());
        jLabel.setPreferredSize(labelDimension);
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        panel.add(jLabel);
        panel.add(jCheckBox);
        filterList.put(entry.getKey(), jCheckBox);
        filterPanel.add(panel);
    }

    private void customizeScrollPane(){
        jScrollPane.setBackground(Global.colorScheme.getTertiaryColor());
        jScrollPane.setPreferredSize(new Dimension(1099,565));
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
    }
    public void setGraphData(HashMap<Date,Double> graphData, int displayType) {
        this.graphDataDateDouble = graphData;
        this.displayType = displayType;
        this.largestDouble = getLargest();
        displayDataDate = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date e1,
                               Date e2) {
                return e1.compareTo(e2);
            }
        });
        displayDataDate.putAll(graphDataDateDouble);
        GridLayout gridLayout = new GridLayout(graphData.size(), 1);
        gridLayout.setVgap(0);
        filterList.clear();
        filterPanel.removeAll();
        filterPanel.setLayout(gridLayout);
        for (Map.Entry<Date, Double> entry: graphData.entrySet()){
            Dimension labelDimension = new Dimension(90,12);
            JCheckBox jCheckBox = new JCheckBox();
            jCheckBox.setBackground(Global.colorScheme.getSecondaryColor());
            jCheckBox.setSelected(true);
            jCheckBox.addActionListener(this);
            JLabel jLabel = new JLabel(entry.getKey().toString());
            jLabel.setPreferredSize(labelDimension);
            JPanel panel = new JPanel( new FlowLayout(FlowLayout.LEFT));
            panel.setOpaque(false);
            panel.add(jLabel);
            panel.add(jCheckBox);
            filterList.put(entry.getKey().toString(), jCheckBox);
            filterPanel.add(panel);
        }
        int width = graphData.size() * 75 + 10;
        setPreferredSize(new Dimension(width ,530));
        isDouble = true;

    }

    public synchronized  void refreshGraphData(HashMap<Date,Double> graphData){
        refreshedGraphData = graphData;
        isDouble = false;
    }

    public synchronized void refreshGraph(){
            if(refreshedGraphData != null){
                setGraphData(refreshedGraphData, this.displayType);
                refreshedGraphData = null;
            }
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
        g2d.setColor(Global.colorScheme.getTertiaryColor());
        g2d.fillRect(0,0,getWidth(),getHeight());
        drawInitialLinesandStrings(g2d, max,10);
        drawGraphLines(g, max);
    }


    protected long roundLargest(){
        int digits = Long.toString(largest).length();
        int power = (int) Math.pow(10, digits);
        int nines = power -1;
        long powered = ((largest + nines) / power) * power;

        if (powered != 0)
        {
            return powered;
        }
        else{
            return 10;
        }
    }

    protected void drawInitialLinesandStrings(Graphics g, double max, int increment){
        lines.clear();
        int width = getWidth();
        int height = getHeight();
        int interval = 10;
        int yPosition;
        BasicStroke stroke1 = new BasicStroke(1);
        BasicStroke stroke2 = new BasicStroke(2);
        yPosition = (height -(10+increment));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(FontsList.getSansSerif(Font.BOLD ,10));
        g2d.setColor(new Color(0x5E5E5E));
        g2d.setStroke(new BasicStroke(1));
        int count = 0;
        for(double x = 0; x <= max ; x += max/50){
            if (count % 5 != 0) {
                g2d.setStroke(stroke1);
                Line2D line2D = new Line2D.Double(5, yPosition, width - 7, yPosition);
                Rectangle2D rectangle2D = new Rectangle2D.Double(5, yPosition - (4), width - 7, 8);
                lines.put(rectangle2D, x);
                //g2d.draw(line2D);
            }
            else {
                g2d.setStroke(stroke1);
                Line2D line2D = new Line2D.Double(5, yPosition, width - 7, yPosition);
                Rectangle2D rectangle2D = new Rectangle2D.Double(5, yPosition - (4), width - 7, 8);
                lines.put(rectangle2D, x);
                g2d.draw(line2D);
                g2d.drawString(((double)Math.round((x*100)))/100 +"", 2, yPosition);
            }
            yPosition -= interval;
            count++;

        }
    }


    private void drawGraphLines(Graphics g, double max){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Global.colorScheme.getGraphColor());
        g2d.setStroke(new BasicStroke(2));
        g2d.setFont(FontsList.getSansSerif(Font.BOLD, 10));
        graphBarsDouble.clear();

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
            ratio = (500 / max);
        }
        else { ratio = 1;
        }
        int endx = 0;
        double endy = (height-10);
        int position = 0;
        boolean start = false;
        for (Map.Entry<Date, Double> item : displaySet) {
            if (item.getValue() > 0) {
                start = true;
            }
            if (start) {
                Line2D line2D;
                if (position == 0) {

                    RoundRectangle2D rr2d = new RoundRectangle2D.Double(endx + 72, (height - 23) - (item.getValue() * ratio), 8, 8, 8, 8);
                    //g2d.drawString(getPaddedString(item.getKey()), endx + 45, height-10);
                    endx += 75;
                    endy = (height - 20) - (item.getValue() * ratio);
                    if (item.getValue() > 0)
                        g2d.fill(rr2d);
                    graphBarsDouble.put(rr2d, item);
                } else {
                    line2D = new Line2D.Double(endx, endy, endx + 75, (height - 20) - (item.getValue() * ratio));
                    RoundRectangle2D rr2d = new RoundRectangle2D.Double(endx + 72, (height - 23) - (item.getValue() * ratio), 8, 8, 8, 8);
                    //g2d.drawString(getPaddedString(item.getKey()), endx + 45, height-10);
                    endx += 75;
                    endy = (height - 20) - (item.getValue() * ratio);
                    g2d.draw(line2D);
                    if (item.getValue() > 0)
                        g2d.fill(rr2d);
                    graphBarsDouble.put(rr2d, item);
                }
                position++;
            }
        }
    }

    public String getPaddedString(String string){
        int width = getStringWidth(string);
        if(width == 63)
            return string;
        if( width < 63){
            int diff = 63 - width;
            if (diff % 2 != 0){
                for (int y =0; y < diff; y+=3){
                    if (y % 2 == 0)
                        string = ' ' + string;
                }
            }
        }
        else{
            int diff = width - 63;
            int length = string.length();
            double ratio = length/(double)width;
            int preferred = (int) Math.floor(ratio * 63);
            return (getPaddedString(string.substring(0, preferred-1)));
        }

        return  string;

    }



    public int getStringWidth(String string){
        FontMetrics metrics = getFontMetrics(FontsList.getSansSerif(Font.BOLD, 10));
        int width = 0;

        for (int x = 0; x< string.length();x++){
            width += metrics.charWidth(string.charAt(x));
        }
        return width;
    }


    public JScrollPane getFilterScrollPane() {
        return filterScrollPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == this.filter){
            if (filterScrollPane.isVisible())
                filterScrollPane.setVisible(false);
            else
            {
                try{
                    semaphore.acquire();
                    filterScrollPane.setVisible(true);
                }
                catch (Exception el){

                }
                finally {
                    semaphore.release();
                }
            }
        }
        else{
            filterScrollPane.setVisible(false);
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

    @Override
    public void mouseMoved(MouseEvent e) {
        boolean found = false;
        boolean found2 = false;
            for (Map.Entry<RoundRectangle2D, Map.Entry<Date, Double>> entry : graphBarsDouble.entrySet()) { RoundRectangle2D  bar = entry.getKey();
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
                    barDetails.setLocation( e.getXOnScreen()-30,e.getYOnScreen());
                    barDetails.setColor(Color.RED);
                    barDetails.setVisible(true);
                }
            }
        if (!found)
        {
            barDetails.setVisible(false);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        filterScrollPane.setVisible(false);
        jScrollPane.dispatchEvent(e);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    private void initTestData(){
        this.graphData = new ArrayList<>();
        this.graphData.add(Map.entry("Heineken", 4L));
        this.graphData.add(Map.entry("Gulder", 2L));
        this.graphData.add(Map.entry("Star", 3L));
        this.graphData.add(Map.entry("Guiness", 1L));
        this.graphData.add(Map.entry("Budweiser", 5L));
        this.graphData.add(Map.entry("33", 5L));
        this.graphData.add(Map.entry("Legend", 7L));
        this.graphData.add(Map.entry("Maltina", 8L));
        this.graphData.add(Map.entry("Malta Guiness", 3L));
        this.graphData.add(Map.entry("Grand Malt", 6L));
        this.graphData.add(Map.entry("1960", 5L));
        this.graphData.add(Map.entry("Origin", 5L));
        this.graphData.add(Map.entry("Trophy", 8L));
        this.graphData.add(Map.entry("33z", 5L));
        this.graphData.add(Map.entry("Legendz", 7L));
        this.graphData.add(Map.entry("Maltinaz", 8L));
        this.largest = 8;
    }
}
