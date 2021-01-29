package com.stockkeeper.Views.Nav;
import com.stockkeeper.DatePicker.DateModel;
import com.stockkeeper.DatePicker.JDatePanel;
import com.stockkeeper.DatePicker.JDatePicker;
import com.stockkeeper.DatePicker.UtilDateModel;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Views.SearchBarAndViewPanel;
import com.stockkeeper.Views.StockDisplayItem;
import com.stockkeeper.Views.charts.*;
import com.stockkeeper.Views.uicomponents.*;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class StockView extends JPanel implements MouseListener, ActionListener, DocumentListener, ComponentListener {
    //Core Models
    private Entity entity;
    private User user;
    private HashMap<String, Entry> stockModel1;
    private HashMap<String, Entry> stockModel2;
    private double grossTotal = 0;
    private double grossTotalT = 0;

    //Top Panels
    private JPanel contentPanel;
    private JPanel detailsPanel;
    private JPanel tableCon;
    private JScrollPane tableConSPane;
    private JScrollPane tableConSPaneThumb;
    private StockHeader stockHeader;
    private Comparator<String> productSort = Helper.entryAscStockName;
    private Comparator<String> levelSort = Helper.entryAscStockLevel;
    private Comparator<String> valueSort = Helper.entryAscStockValue;
    private StockDisplayItem selected;
    private int colorsCount = 1;

    //Sorters
    private int productSortDirection = 0;
    private int stockLevelSortDirection = 0;
    private int stockValueSortDirection = 0;

    //StockItems
    private JPanel stockContainer;
    private int stockContainerHeight;
    private JPanel stockContainerThumb;
    private SearchBarAndViewPanel searchBarandViewChange;
    private DetailsPanel detailsCon;
    private StockDisplayItem lastSelected;

    //Graphs
    private LineGraphPanel lineGraphPanel;
    private VerticalBarChartPanel barChartPanel;


    UtilDateModel model;
    JDatePanel datePanel;
    JDatePicker datePicker;

    UtilDateModel model2;
    JDatePanel datePanel2;
    JDatePicker datePicker2;

    //Details
    private  JLabel totalLabel;
    private  JLabel productLabel;
    private  JLabel stockLevelLabel;
    private  JLabel priceLabel;
    private  JPanel salesPanel;

    public StockView(Entity entity, User user)
    {
        this.entity = entity;
        this.user = user;
        initModels();
        setLayout(new BorderLayout());
        initTopPanels();
        initGraphs();
        initContentPanel();
        initDetailsPanel();
        setUpTimer();
        displayModel();
        sortRowsAsc(1, stockContainer);
        addComponentListener(this);
    }

    private void initModels(){
        stockModel1 = Global.stock.getTickets();
        stockModel2 = (HashMap<String, Entry>) stockModel1.clone();
    }

    private void initTopPanels(){
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Global.colorScheme.getQuaternaryColor());
        contentPanel.setPreferredSize(new Dimension(320,710));
        detailsPanel = new JPanel(){
            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(Global.colorScheme.getDenaryColor());
                g.drawRect(0,0,getWidth(),getHeight());
            }
        };
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.setPreferredSize(new Dimension(400,710));

        add(contentPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.EAST);
    }

    private void initContentPanel(){
        //Init
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Global.colorScheme.getQuaternaryColor());
        initHeaderSearchViewPanel(container);
        initItemsView(container);
        contentPanel.add(container, BorderLayout.CENTER);

    }

    private void initDetailsPanel(){
        //InfoBar
        JPanel infoBar = new JPanel(new FlowLayout(FlowLayout.CENTER)){

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;

                //GradientPaint gradientPaint = new GradientPaint(0f,0f,new Color(0xffe259), getWidth(),getHeight(),new Color(0xffa751));
                //g2d.setPaint(gradientPaint);
                g.setColor(Global.colorScheme.getSecondaryColor());
                g.fillRoundRect(1,1,getWidth()-3, getHeight()-3, 5,5);



            }
        };
        infoBar.setPreferredSize(new Dimension(400,80));
        totalLabel = new JLabel();
        totalLabel.setFont(FontsList.getSitkaBanner(Font.BOLD,30));
        infoBar.add(totalLabel);
        infoBar.setBorder(new EmptyBorder(15,0,15,0));

        //Details

        productLabel = new DisplayLabel();
        stockLevelLabel = new DisplayLabel();
        priceLabel = new DisplayLabel();
        productLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        stockLevelLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        priceLabel.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
        model = new UtilDateModel();
        model2 = new UtilDateModel();



        GregorianCalendar g = new GregorianCalendar();
        GregorianCalendar g2 = (GregorianCalendar) g.clone();
        g2.add(Calendar.MONTH, -1);
        model.setDate(g2.get(Calendar.YEAR),g2.get(Calendar.MONTH),g2.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        model2.setDate(g.get(Calendar.YEAR),g.get(Calendar.MONTH),g.get(Calendar.DAY_OF_MONTH));
        model2.setSelected(true);
        datePanel = new JDatePanel(model);
        datePicker = new JDatePicker(datePanel);
        datePicker.setPreferredSize(new Dimension(120,25));
        datePicker.addActionListener(this);

        datePanel2 = new JDatePanel(model2);
        datePicker2 = new JDatePicker(datePanel2);
        datePicker2.setPreferredSize(new Dimension(120,25));
        datePicker2.addActionListener(this);

        salesPanel = new OneByTwoRow(datePicker,datePicker2, 10);
        salesPanel.setPreferredSize(new Dimension(300,25));
        detailsCon = new DetailsPanel(productLabel,stockLevelLabel,priceLabel,salesPanel);
        detailsCon.setDetailsVisible(false);
        //Nav Area
        //Loading Components
        detailsPanel.add(infoBar, BorderLayout.NORTH);
        detailsPanel.add(detailsCon, BorderLayout.CENTER);

        setChartViewBar();
    }

    private void initGraphs(){
        lineGraphPanel = new LineGraphPanel(new HashMap<>(),1);
        barChartPanel = new VerticalBarChartPanel(new HashMap<>(), 0.0);
    }

    private void setUpTimer(){

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                stockModel1 = Global.stock.getTickets();
                stockModel2 = (HashMap<String, Entry>) stockModel1.clone();
                displayModel();
            }
        };
        timer.schedule(timerTask,8000,8000);
    }


    private void displayModel(){
        if (searchBarandViewChange.getViewChangePanel().getSelected() == searchBarandViewChange.getViewChangePanel().getListLabel()){

            for(Map.Entry <String,Entry>  entry: stockModel2.entrySet()){
                StockRow stockRow = modelRowFound(entry.getValue());
                if(stockRow == null){
                    StockRow stockRow1 = new StockRow(entry.getValue());
                    stockRow1.addMouseListener(this);
                    stockContainer.add(stockRow1);
                    stockContainerHeight += 46;
                    stockContainer.setPreferredSize(new Dimension(0, stockContainerHeight));
                    barChartPanel.add(Map.entry(entry.getValue().getProduct().getName(), entry.getValue().getQty()));
                    grossTotal += entry.getValue().getTotal().getGrossTotal();
                }
                else{
                    double before = stockRow.getStockItem().getTotal().getGrossTotal();
                    stockRow.refresh(entry.getValue());
                    barChartPanel.modify(Map.entry(entry.getValue().getProduct().getName(), entry.getValue().getQty()));
                    grossTotal += entry.getValue().getTotal().getGrossTotal() - before;
                }
            }
            totalLabel.setText("$" + grossTotal);
            stockContainer.repaint();
            stockContainerThumb.revalidate();

        }
        else{
            for(Map.Entry <String,Entry> entry: stockModel2.entrySet()){
                FolderThumbNail folderThumbNail = modelThumbFound(entry.getValue());
                if(folderThumbNail == null){
                    FolderThumbNail folderThumbNail1 = new FolderThumbNail(entry.getValue());
                    folderThumbNail1.addMouseListener(this);
                    //folderThumbNail.setColor(Global.thumbColors[colorsCount % Global.thumbColors.length]);
                    /*if(stockContainerThumb.getComponents().length % 3 == 0){
                        stockContainerThumb.setPreferredSize(new Dimension(0, (int)(stockContainerThumb.getPreferredSize().getHeight() + 160)));
                    }*/
                    grossTotalT += entry.getValue().getTotal().getGrossTotal();
                    stockContainerThumb.add(folderThumbNail1);
                    colorsCount++;
                }
                else{
                    double before = folderThumbNail.getStockItem().getTotal().getGrossTotal();
                    grossTotalT += entry.getValue().getTotal().getGrossTotal() - before;
                    folderThumbNail.refresh(entry.getValue());
                }
            }
            totalLabel.setText("$" + grossTotalT);
            stockContainerThumb.revalidate();
            stockContainerThumb.repaint();
            componentResized(new ComponentEvent(stockContainerThumb, 2));
        }

        tableCon.revalidate();
    }


    public void setChartViewBar(){
        try{
                detailsCon.getGraphPanel().remove(lineGraphPanel);
                detailsCon.getGraphPanel().add(barChartPanel);
                detailsCon.getGraphPanel().repaint();
        }
        catch (Exception e){
            detailsCon.getGraphPanel().add(barChartPanel);
            detailsCon.getGraphPanel().repaint();
        }
    }


    public void setChartViewLine(Product product){

        try{

            detailsCon.getGraphPanel().remove(barChartPanel);
            DateModel d1 = datePicker.getModel();
            DateModel d2 = datePicker2.getModel();
            lineGraphPanel.refreshGraph(Global.sales.getGraphData(product,(Date)d1.getValue(), (Date)d2.getValue()),1);
            detailsCon.getGraphPanel().add(lineGraphPanel);
            detailsCon.getGraphPanel().repaint();
        }
        catch (Exception e){
            detailsCon.getGraphPanel().add(lineGraphPanel);
            detailsCon.getGraphPanel().repaint();
        }
    }

    public  void search(String pattern){
        for ( Component component : stockContainer.getComponents()){
            StockDisplayItem stockRow = (StockDisplayItem) component;
            component.setVisible(true);
            boolean found = false;
            if(!check(pattern, stockRow.getStockItem())){
                stockRow.setVisible(false);
            }
        }
        for ( Component component : stockContainerThumb.getComponents()){
            StockDisplayItem stockRow = (StockDisplayItem) component;
            component.setVisible(true);
            boolean found = false;
            if(!check(pattern, stockRow.getStockItem())){
                stockRow.setVisible(false);
            }
        }
    }


    public boolean check(String pattern, Entry entry){
        if (pattern.length() != 0){
            boolean found = false;
            if(Helper.KMPSearch(pattern,entry.getProduct().getName()))
                found = true;
            if(Helper.KMPSearch(pattern,entry.getQty() +""))
                found = true;
            if(Helper.KMPSearch(pattern,entry.getProduct().getUnitCost()*entry.getQty() +""))
                found = true;

            return found;
        }
        else return  true;
    }


    private void sortRowsAsc(int type, JPanel container){
        switch (type){
            case 1:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getProduct().getName().compareTo(stockRow.getStockItem().getProduct().getName()) >0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 2:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getQty() > stockRow.getStockItem().getQty()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 3:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getTotal().getGrossTotal() > stockRow.getStockItem().getTotal().getGrossTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
        }
    }
    private void sortRowsDesc(int type, JPanel container){
        switch (type){
            case 1:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getProduct().getName().compareTo(stockRow.getStockItem().getProduct().getName()) < 0){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 2:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getQty() < stockRow.getStockItem().getQty()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;

            case 3:
                for ( int index = 1; index  < container.getComponents().length; index++ ){

                    int position = index;
                    StockDisplayItem stockRow = (StockDisplayItem) container.getComponent(index);

                    while (position > 0 && ((StockDisplayItem)container.getComponent(position-1)).getStockItem().getTotal().getGrossTotal() < stockRow.getStockItem().getTotal().getGrossTotal()){
                        container.add(container.getComponent(position - 1), position);
                        position = position -1;
                    }
                    container.add(stockRow, position);
                }
                break;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof  StockHeaderLabel) {
            if (e.getSource() == stockHeader.getProduct() || e.getSource() == stockHeader.getStockLevel() || e.getSource() == stockHeader.getStockValue()) {

                if (e.getSource() == stockHeader.getProduct()) {
                    if(productSortDirection == 0)
                    {
                        sortRowsDesc(1, stockContainer);
                        this.barChartPanel.sort(productSort, 1);
                        productSortDirection = 1;
                    }
                    else{
                        sortRowsAsc(1, stockContainer);
                        this.barChartPanel.setSortType(0);
                        this.barChartPanel.sort(productSort, 0);
                        productSortDirection = 0;
                    }
                    stockContainer.revalidate();
                    stockContainer.repaint();
                }
                if (e.getSource() == stockHeader.getStockLevel()) {
                    if(stockLevelSortDirection == 0)
                    {
                        sortRowsDesc(2, stockContainer);
                        this.barChartPanel.sort(levelSort, 1);
                        stockLevelSortDirection = 1;
                    }
                    else{
                        sortRowsAsc(2, stockContainer);
                        this.barChartPanel.sort(levelSort, 0);
                        stockLevelSortDirection = 0;
                    }
                    stockContainer.revalidate();
                    stockContainer.repaint();

                }
                if (e.getSource() == stockHeader.getStockValue()) {
                    if(stockValueSortDirection == 0)
                    {
                        sortRowsDesc(3, stockContainer);
                        this.barChartPanel.sort(valueSort, 1);
                        stockValueSortDirection = 1;
                    }
                    else{
                        sortRowsAsc(3, stockContainer);
                        this.barChartPanel.sort(valueSort, 0);
                        stockValueSortDirection = 0;
                    }
                    stockContainer.revalidate();
                    stockContainer.repaint();
                }
            }
            else {
                StockRow stockRow = (StockRow) ((StockHeaderLabel) e.getSource()).getParent();
                productLabel.setText(stockRow.getStockItem().getProduct().getName());
                stockLevelLabel.setText(stockRow.getStockItem().getQty() + "");
                priceLabel.setText("$" + stockRow.getStockItem().getProduct().getUnitCost());
                setChartViewLine(stockRow.getStockItem().getProduct());
                if (lastSelected != null) {
                    if (!stockRow.isSelected()) {
                        lastSelected.turnOff();
                        lastSelected.setSelected(false);
                        stockRow.setSelected(true);
                        stockRow.turnOn();
                        lastSelected = stockRow;
                        selected = stockRow;
                    }
                }
                else {
                    stockRow.setSelected(true);
                    stockRow.turnOn();
                    lastSelected = stockRow;
                    selected = stockRow;
                }

                detailsCon.setDetailsVisible(true);
            }
        }
        if(e.getSource() instanceof FolderThumbNail){


            FolderThumbNail folderThumbNail = (FolderThumbNail) e.getSource();
            productLabel.setText(folderThumbNail.getStockItem().getProduct().getName());
            stockLevelLabel.setText(folderThumbNail.getStockItem().getQty() + "");
            priceLabel.setText("$" + folderThumbNail.getStockItem().getProduct().getUnitCost());
            setChartViewLine(folderThumbNail.getStockItem().getProduct());
            if (lastSelected != null) {
                if (!folderThumbNail.isSelected()) {
                    lastSelected.turnOff();
                    lastSelected.setSelected(false);
                    folderThumbNail.setSelected(true);
                    lastSelected = folderThumbNail;
                    selected = folderThumbNail;
                }
            }
            else {
                folderThumbNail.setSelected(true);
                folderThumbNail.turnOn();
                lastSelected = folderThumbNail;
                selected = folderThumbNail;
            }

            detailsCon.setDetailsVisible(true);

        }

        if (e.getSource() == stockContainer) {
            if (lastSelected != null) {
                lastSelected.turnOff();
                lastSelected.setSelected(false);
                selected = null;
                setChartViewBar();
            }
            detailsCon.setDetailsVisible(false);
        }
        if (e.getSource() == searchBarandViewChange.getViewChangePanel().getTilesLabel()){
            setThumbView();
            sortRowsAsc(1, stockContainerThumb);
        }
        if (e.getSource() == searchBarandViewChange.getViewChangePanel().getListLabel()){
            setListView();
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
    public void actionPerformed(ActionEvent e) {
        DateModel d1 = datePicker.getModel();
        DateModel d2 = datePicker2.getModel();
        Global.sales.getGraphData(Global.products.get(1),(Date)d1.getValue(), (Date)d2.getValue());
        detailsCon.getGraphPanel().remove(barChartPanel);
        detailsCon.getGraphPanel().add(lineGraphPanel);
        detailsCon.getGraphPanel().revalidate();
        lineGraphPanel.refreshGraph(Global.sales.getGraphData(lastSelected.getStockItem().getProduct(),(Date)d1.getValue(), (Date)d2.getValue()),1);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            displayModel();
        }
        catch (BadLocationException el){

        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try{
            search(e.getDocument().getText(0, e.getDocument().getLength()));
            displayModel();

        }
        catch (BadLocationException el){

        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }



    private void initHeaderSearchViewPanel(JPanel container){
        //Init Header Bar With Search and View Change Components
        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setPreferredSize(new Dimension(1000,60));
        headerBar.setBorder(new EmptyBorder(0,20,0,0));
        headerBar.setBackground(Global.colorScheme.getQuaternaryColor());

        JLabel accountTitle = new JLabel("Inventory");
        accountTitle.setFont(FontsList.getSitkaBanner(Font.PLAIN, 20));
        searchBarandViewChange = new SearchBarAndViewPanel();
        searchBarandViewChange.addMouseListener(this);
        searchBarandViewChange.addDocumentListener(this);
        headerBar.add(accountTitle, BorderLayout.CENTER);
        headerBar.add(searchBarandViewChange, BorderLayout.EAST);
        container.add(headerBar, BorderLayout.NORTH);
    }



    private void initItemsView(JPanel container){
        // Init TableContainer
        tableCon = new JPanel(new BorderLayout());

        // Init tableCOn
        tableCon.setPreferredSize(new Dimension(600, 600));
        tableCon.setBorder(new EmptyBorder(0,80,0,80));
        tableCon.setBackground(Global.colorScheme.getQuaternaryColor());
        container.add(tableCon, BorderLayout.CENTER);

        //Init Stock Container
        stockHeader = new StockHeader();
        stockHeader.addMouseListener(this);
        stockContainer = new JPanel();
        stockContainer.setPreferredSize(new Dimension(600, 0));
        stockContainer.setBackground(Global.colorScheme.getQuaternaryColor());
        stockContainer.setBorder(new EmptyBorder(0,0,0,0));
        stockContainer.setFocusable(true);
        stockContainer.addMouseListener(this);
        stockContainerThumb = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stockContainerThumb.setBackground(Global.colorScheme.getQuaternaryColor());
        stockContainerThumb.setBorder(new EmptyBorder(0,0,0,0));
        stockContainerThumb.setPreferredSize(new Dimension(0, 0));
        stockContainerThumb.addMouseListener(this);

        //Init Stock Container ScrollPane
        tableConSPane = new JScrollPane(stockContainer,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPane.setPreferredSize(new Dimension(600, 320));
        Helper.setVerticalScrollBarColor(tableConSPane, Global.colorScheme.getNonaryColor());
        tableConSPane.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPane.setBorder(new EmptyBorder(0,0,0,0));

        tableConSPaneThumb = new JScrollPane(stockContainerThumb,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tableConSPaneThumb.setPreferredSize(new Dimension(600, 320));
        Helper.setVerticalScrollBarColor(tableConSPaneThumb, Global.colorScheme.getNonaryColor());
        tableConSPaneThumb.getVerticalScrollBar().setUnitIncrement(10);
        tableConSPaneThumb.setBorder(new EmptyBorder(0,0,0,0));

        //Init tableCon Padding
        JPanel padding = new JPanel();
        padding.setBackground(Global.colorScheme.getQuaternaryColor());
        padding.setPreferredSize(new Dimension(600,100));

        tableCon.add(stockHeader, BorderLayout.NORTH);
        tableCon.add(tableConSPane, BorderLayout.CENTER);
        tableCon.add(padding, BorderLayout.SOUTH);

    }

    private StockRow modelRowFound(Entry entry){
        for(Component component: stockContainer.getComponents())
        {
            StockRow stockRow = (StockRow) component;
            if(entry.getId().equals(stockRow.getStockItem().getId()))
                return stockRow;
        }
        return null;

    }

    private FolderThumbNail modelThumbFound(Entry entry){
        for(Component component: stockContainerThumb.getComponents())
        {
            FolderThumbNail folderThumbNail = (FolderThumbNail) component;
            if(entry.getId().equals(folderThumbNail.getStockItem().getId()))
                return folderThumbNail;
        }
        return null;

    }

    private void setListView(){
        tableCon.remove(tableConSPaneThumb);
        stockContainerThumb.setVisible(false);
        tableCon.add(tableConSPane, BorderLayout.CENTER);
        stockContainer.setVisible(true);
        displayModel();
        tableCon.revalidate();
        tableCon.repaint();
        stockHeader.setVisible(true);

    }

    private void setThumbView(){
        tableCon.remove(tableConSPane);
        stockContainer.setVisible(false);
        tableCon.add(tableConSPaneThumb, BorderLayout.CENTER);
        stockContainerThumb.setVisible(true);
        displayModel();
        tableCon.revalidate();
        tableCon.repaint();
        stockHeader.setVisible(false);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        double ratio = 0.3064903846153846;
        double ratioHeight = 0.3064903846153846;
        int rowLength = (int)stockContainerThumb.getSize().getWidth() / 255;
        if(rowLength == 0)
            rowLength = 768/255;
        double rowNumber = (int)Math.ceil(stockContainerThumb.getComponents().length / (double)rowLength);
        double height = Math.ceil(rowNumber * 170);
        double newWidth = Math.ceil(ratio * getSize().getWidth());
        stockContainerThumb.setPreferredSize(new Dimension((int)newWidth, (int)height));

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}


