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
import java.util.*;
import java.util.concurrent.Semaphore;

public class GraphSheetEnlarged2 extends Sheet implements MouseMotionListener, MouseListener, MouseWheelListener, AdjustmentListener, ActionListener {

    private JLabel label2;
    private JPanel topPanel;
    private GraphFilterIcon filter;
    private GraphPrintIcon print;
    private HashMap<Rectangle2D, Map.Entry<String, Map.Entry<Long, Color>>> graphBars = new HashMap<>();
    private HashMap<Rectangle2D, Map.Entry<String, Map.Entry<Double, Color>>> graphBarsDouble = new HashMap<>();
    private HashMap<String, JCheckBox> filterList;
    private JPanel filterPanel;
    private JScrollPane  filterScrollPane;
    private Insets insets;
    private Dimension dimension1;
    private Dimension dimension2;
    private Dimension dimension3;
    private ArrayList<Map.Entry<String, Long>> refreshedGraphData;
    private HashMap<String,Double> refreshedGraphDataDouble;
    private int count = 0;
    private Semaphore semaphore = new Semaphore(1);



    public GraphSheetEnlarged2(HashMap<String,Double> graphData, String label, JPanel topPanel, double buffer) {
        super(graphData, buffer);
        this.topPanel = topPanel;
        initTitle(label);
        initFilter();
        setGraphData(graphData, buffer);
        initListeners();
        customizeScrollPane();
        initComponents();
        addMouseListener((this));
        isDouble = true;
    }

    @Override
    public void add(Map.Entry<String, Double> entry, double buffer) {
        this.graphDataDouble.put(entry.getKey(), entry.getValue());
        this.displayData.put(entry.getKey(), entry.getValue());
        this.largestDouble = getLargest(0.0);
        Dimension labelDimension = new Dimension(90, 12);
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setBackground(Global.colorScheme.getSecondaryColor());
        jCheckBox.setSelected(true);
        jCheckBox.addActionListener(this);
        JLabel jLabel = new JLabel(entry.getKey());
        jLabel.setPreferredSize(labelDimension);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        panel.add(jLabel);
        panel.add(jCheckBox);
        filterList.put(entry.getKey(), jCheckBox);
        sortFilter();
        int width = graphDataDouble.size() * 55 + 10;
        setPreferredSize(new Dimension(width ,230));
    }

    @Override
    public void add(Map.Entry<Date, Double> entry) {
        this.graphDataDateDouble.put(entry.getKey(), entry.getValue());
        this.displayDataDate.put(entry.getKey(), entry.getValue());
        this.largestDouble = getLargest(0.0);
        Dimension labelDimension = new Dimension(90, 12);
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setBackground(Global.colorScheme.getSecondaryColor());
        jCheckBox.setSelected(true);
        jCheckBox.addActionListener(this);
        JLabel jLabel = new JLabel(entry.getKey().toString());
        jLabel.setPreferredSize(labelDimension);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);
        panel.add(jLabel);
        panel.add(jCheckBox);
        filterList.put(entry.getKey().toString(), jCheckBox);
        sortFilter();
        int width = graphDataDouble.size() * 55 + 10;
        setPreferredSize(new Dimension(width ,230));
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


    private  void sortFilter(){
        for ( int index = 1; index  < filterPanel.getComponents().length; index++ ){

            int position = index;
            JPanel jPanel = (JPanel) filterPanel.getComponent(index);
            JLabel jLabel;
            try{
                jLabel = (JLabel) jPanel.getComponent(0);
            }
            catch (Exception e){
                e.printStackTrace();
                jLabel = (JLabel) jPanel.getComponent(1);
            }

            while (position > 0 && (((JLabel)((JPanel) filterPanel.getComponent(position -1)).getComponent(0)).getText().compareTo(jLabel.getText()) >0)){
                filterPanel.add(filterPanel.getComponent(position - 1), position);
                position = position -1;
            }
            filterPanel.add(jPanel, position);
        }
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


    private void customizeScrollPane(){
        jScrollPane.setBackground(Global.colorScheme.getTertiaryColor());
        jScrollPane.setPreferredSize(new Dimension(1099,565));
        jScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
    }


    public void setGraphData(HashMap<String,Double> graphData, double buffer) {
        this.graphDataDouble = graphData;
        this.largestDouble = getLargest(0.0);
        displayData = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String e1,
                               String e2) {
                return e1.compareTo(e2);
            }
        });
        displayData.putAll(graphDataDouble);

        GridLayout gridLayout = new GridLayout(graphData.size(), 1);
        gridLayout.setVgap(0);
        filterList.clear();
        filterPanel.removeAll();
        filterPanel.setLayout(gridLayout);
        for (Map.Entry<String, Double> entry: graphData.entrySet()){
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
        int width = graphData.size() * 55 + 10;
        setPreferredSize(new Dimension(width ,230));
        isDouble = true;

    }

    public void sort(Comparator<String> comparator, int sortType){
        setSortType(sortType);
        this.displayData = new TreeMap<>(comparator);
        displayData.putAll(this.graphDataDouble);
        repaint();
    }
    public synchronized  void refreshGraphData(ArrayList<Map.Entry<String,Long>> graphData){
        refreshedGraphData = graphData;
        isDouble = false;
    }

    public synchronized  void refreshGraphData(HashMap<String,Double> graphData, double buffer){
        refreshedGraphDataDouble = graphData;
        isDouble = true;
    }

    public synchronized void refreshGraph(){
        if(refreshedGraphDataDouble != null && isDouble){
            setGraphData(refreshedGraphDataDouble,0.0);
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
        drawGraphBars(g, max);
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

    private void drawGraphBars(Graphics g, double max){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        double ratio;
        if (max != 0){
            ratio = (500 / max);
        }
        else { ratio = 1;
        }
        int xPosition = 40;
        int count = 0;

        graphBars.clear();
        graphBarsDouble.clear();
        if(sortType == 0)
            if(isDouble){
                for (Map.Entry<String, Double> item : displayData.entrySet()){
                    try{
                        JCheckBox jCheckBox = filterList.get(item.getKey());
                        if (jCheckBox.isSelected()){
                            Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                            g2d.setColor(barColor);
                            Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),50, (item.getValue() * ratio));
                            g2d.fill(bar);
                            g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                            graphBarsDouble.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                            xPosition += 70;
                        }
                    }
                    catch (Exception e){
                    }
                    count++;
                }


            }
            else{
                for (Map.Entry<String, Long> item : graphData){
                    try{
                        JCheckBox jCheckBox = filterList.get(item.getKey());
                        if (jCheckBox.isSelected()){
                            Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                            g2d.setColor(barColor);
                            Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),50, (item.getValue() * ratio));
                            g2d.fill(bar);
                            g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                            graphBars.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                            xPosition += 70;
                        }
                    }
                    catch (Exception e){
                    }
                    count++;
                }
            }
        else
        if(isDouble){
            for (Map.Entry<String, Double> item : displayData.descendingMap().entrySet()){
                try{
                    JCheckBox jCheckBox = filterList.get(item.getKey());
                    if (jCheckBox.isSelected()){
                        Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                        g2d.setColor(barColor);
                        Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),50, (item.getValue() * ratio));
                        g2d.fill(bar);
                        g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                        graphBarsDouble.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                        xPosition += 70;
                    }
                }
                catch (Exception e){
                }
                count++;
            }


        }
        else{
            for (Map.Entry<String, Long> item : graphData){
                try{
                    JCheckBox jCheckBox = filterList.get(item.getKey());
                    if (jCheckBox.isSelected()){
                        Color barColor = Global.thumbColors[count % Global.thumbColors.length];
                        g2d.setColor(barColor);
                        Rectangle2D bar = new Rectangle2D.Double(xPosition, (height-20) -(item.getValue() * ratio),50, (item.getValue() * ratio));
                        g2d.fill(bar);
                        g2d.drawString(getFormattedString(item.getKey(), g), xPosition, getHeight()-12);
                        graphBars.put(bar, Map.entry(item.getKey(), Map.entry(item.getValue(), barColor)));
                        xPosition += 70;
                    }
                }
                catch (Exception e){
                }
                count++;
            }
        }



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
                    barDetails.setLocation( e.getXOnScreen()-30,e.getYOnScreen());
                    barDetails.setVisible(true);
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
                    barDetails.setLocation( e.getXOnScreen()-30,e.getYOnScreen());
                    barDetails.setVisible(true);
                }
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
