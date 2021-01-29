package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.RefreshButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class EventPanel extends JPopupMenu  implements  MouseListener{

    private static final int MAX_AVAILABLE = 4;
    public static Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private JPanel content;
    private JScrollPane jScrollPane;
    private JPanel failed;
    private JPanel pending;
    private JPanel completed;
    private JPanel failedContent;
    private JPanel pendingContent;
    private JPanel completedContent;
    private int contentHeight;
    private int failedHeight;
    private int pendingHeight;
    private int completedHeight;
    private int failed1Height;
    private int pending1Height;
    private int completed1Height;
    private ArrayList<EventItem> eventItemsC;
    private ArrayList<EventItem> eventItemsF;
    private ArrayList<EventItem> eventItemsP;

    public EventPanel() {
        setBackground(Global.colorScheme.getSecondaryColor());
        setPreferredSize(new Dimension(400, 400));
        setBorder(new EmptyBorder(0,0,0,0));
        setLayout(new BorderLayout());
        initDataStorage();
        initTitle();
        initContent();
        initFailedPanel();
        initPendingPanel();
        initCompletedPanel();
        failedHeight = 0;
        pendingHeight = 0;
        completedHeight = 0;
    }
    private void initTitle(){

        JLabel label = new JLabel("TASKS"){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Global.colorScheme.getQuinaryColor());
                g.fillRect(0,0, getWidth()-2, getHeight());
                super.paintComponent(g);
            }
        };
        label.setPreferredSize(new Dimension(400,40));
        label.setFont(FontsList.getHelvetica(Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.NORTH);
        label.setBorder(new EmptyBorder(5,10,0,0));

    }

    public void initDataStorage(){
        eventItemsC = new ArrayList<>();
        eventItemsF = new ArrayList<>();
        eventItemsP = new ArrayList<>();
    }

    public void initContent(){
        contentHeight = 0;
        content = new JPanel(new FlowLayout(FlowLayout.LEFT));
        content.setBackground(Global.colorScheme.getSecondaryColor());;
        jScrollPane = new JScrollPane(content){

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getDenaryColor());
                g2d.drawRect(0,0,getWidth(),getHeight());
            }
        };
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(5);
        jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        setVerticalScrollBarColor(jScrollPane, Global.colorScheme.getPrimaryColor());
        content.setPreferredSize(new Dimension(400,contentHeight));
        jScrollPane.setPreferredSize(new Dimension(400, 400));
        add(jScrollPane, BorderLayout.CENTER);

    }


    private void initFailedPanel(){
        contentHeight += 45;
        failed1Height = 40;
        failed = new JPanel(new FlowLayout(FlowLayout.LEFT));
        failed.setPreferredSize(new Dimension(400,40));
        JPanel title = new JPanel();
        title.setBackground(Global.colorScheme.getSecondaryColor());
        title.setPreferredSize(new Dimension(400,30));
        JLabel titleLabel = new JLabel("Failed"){


            @Override
            protected void paintComponent(Graphics g) {
                paintLabel((Graphics2D) g, this);
                super.paintComponent(g);
            }
        };
        title.setPreferredSize(new Dimension(400,30));
        titleLabel.setPreferredSize(new Dimension(400,20));
        title.add(titleLabel);
        failed.add(title);

        //
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(2);
        failedContent = new JPanel(layout){

            @Override
            public Component add(Component comp) {
                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight()+ 35));
                return super.add(comp);
            }

            @Override
            public void remove(Component comp) {

                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight() - 35));
                super.remove(comp);
            }
        };
        failedContent.setPreferredSize(new Dimension(400,0));
        failed.setBackground(Global.colorScheme.getSecondaryColor());
        failedContent.setBackground(Global.colorScheme.getSecondaryColor());
        failed.add(failedContent);
        content.add(failed);
        content.setPreferredSize(new Dimension(400, contentHeight));

    }

    private void initPendingPanel(){
        contentHeight += 45;
        pending1Height = 40;
        pending = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pending.setPreferredSize(new Dimension(400,40));
        JPanel title = new JPanel();
        title.setBackground(Global.colorScheme.getSecondaryColor());
        title.setPreferredSize(new Dimension(400,30));
        JLabel titleLabel = new JLabel("Pending"){
            @Override
            protected void paintComponent(Graphics g) {
                paintLabel((Graphics2D) g, this);
                super.paintComponent(g);
            }
        };
        title.setPreferredSize(new Dimension(400,30));
        titleLabel.setPreferredSize(new Dimension(400,20));
        title.add(titleLabel);
        pending.add(title);

        //
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(2);
        pendingContent = new JPanel(layout){

            @Override
            public Component add(Component comp) {
                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight()+ 35));
                return super.add(comp);
            }

            @Override
            public void remove(Component comp) {

                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight() - 35));
                super.remove(comp);
            }
        };
        pendingContent.setPreferredSize(new Dimension(400,pendingHeight));
        pending.setBackground(Global.colorScheme.getSecondaryColor());
        pendingContent.setBackground(Global.colorScheme.getSecondaryColor());
        pending.add(pendingContent);
        content.add(pending);
        content.setPreferredSize(new Dimension(400, contentHeight));

    }

    private void initCompletedPanel(){
        contentHeight += 45;
        completed1Height = 40;
        completed = new JPanel(new FlowLayout(FlowLayout.LEFT));
        completed.setPreferredSize(new Dimension(400,40));
        JPanel title = new JPanel();
        title.setPreferredSize(new Dimension(400,30));
        title.setBackground(Global.colorScheme.getSecondaryColor());
        JLabel titleLabel = new JLabel("Completed"){

            @Override
            protected void paintComponent(Graphics g) {
                paintLabel((Graphics2D) g, this);
                super.paintComponent(g);
            }
        };
        title.setPreferredSize(new Dimension(400,30));
        titleLabel.setPreferredSize(new Dimension(400,20));
        title.add(titleLabel);
        completed.add(title);

        //
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(3);
        completedContent = new JPanel(layout){

            @Override
            public Component add(Component comp) {
                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight()+ 35));
                return super.add(comp);
            }

            @Override
            public void remove(Component comp) {

                setPreferredSize(new Dimension(400, (int)getPreferredSize().getHeight() - 35));
                super.remove(comp);
            }
        };;
        completedContent.setPreferredSize(new Dimension(400,completedHeight));
        completed.setBackground(Global.colorScheme.getSecondaryColor());
        completedContent.setBackground(Global.colorScheme.getSecondaryColor());
        completed.add(completedContent);
        content.add(completed);
        content.setPreferredSize(new Dimension(400, contentHeight));

    }

    public synchronized void addtoPending(EventItem eventItem){
        pending1Height += 35;
        contentHeight += 35;
        EventRow eventRow = new EventRow(eventItem,false,this);
        eventItem.setEventRow(eventRow);
        pendingContent.add(eventRow);
        pending.setPreferredSize(new Dimension(400,pending1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        pending.revalidate();
        pending.repaint();

        try{
            Thread t1 = new Thread((EventRow.EventTest) eventItem);
            t1.start();
            Global.trafficPanel.turnOffSuccess();
            Global.trafficPanel.turnOnPending();
        }
        catch (Exception e){
            Thread t1 = new Thread((TicketModel) eventItem);
            t1.start();
            Global.trafficPanel.turnOffSuccess();
            Global.trafficPanel.turnOnPending();


        }
    }
    public  synchronized  void retractToFailed(EventRow eventRow){
        failed1Height += 35;
        contentHeight += 35;

        eventRow.setButtonOn(true);
        eventRow.getEventItem().getProgressBar().setBackground(Color.WHITE);
        eventRow.getEventItem().getProgressBar().setForeground(Color.RED);
        failedContent.add(eventRow);
        failed.setPreferredSize(new Dimension(400, failed1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        failed.revalidate();
        failed.repaint();
    }

    public synchronized void retractToPending(EventRow eventRow){
        pending1Height += 35;
        contentHeight += 35;
        pendingContent.add(eventRow);
        eventRow.getEventItem().getProgressBar().setBackground(Color.WHITE);
        eventRow.getEventItem().getProgressBar().setForeground(Color.GREEN);
        pending.setPreferredSize(new Dimension(400,pending1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        pending.revalidate();
        pending.repaint();

        try{
            Thread t1 = new Thread((EventRow.EventTest) eventRow.getEventItem());
            t1.start();
        }
        catch (Exception e){
            Thread t1 = new Thread((TicketModel) eventRow.getEventItem());
            t1.start();
        }
    }

    public synchronized void retractToCompleted(EventRow eventRow){
        completed1Height += 35;
        contentHeight += 35;
        completedContent.add(eventRow);
        eventRow.getEventItem().getProgressBar().setBackground(Color.WHITE);
        eventRow.getEventItem().getProgressBar().setForeground(Color.GREEN);
        completed.setPreferredSize(new Dimension(400,  completed1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        completed.revalidate();
        completed.repaint();
    }


    public synchronized void removeFromFailed(EventItem eventItem){
        failedContent.remove(eventItem.getEventRow());
        failed1Height -= 35;
        contentHeight -= 35;
        failed.setPreferredSize(new Dimension(400, failed1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        failed.revalidate();
        failed.repaint();
        content.revalidate();
    }

    public  synchronized void removeFromPending(EventItem eventItem) {
        pendingContent.remove(eventItem.getEventRow());
        pending1Height -= 35;
        contentHeight -= 35;
        pending.setPreferredSize(new Dimension(400,pending1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        pending.revalidate();
        pending.repaint();
        content.revalidate();
    }

    public  synchronized void removeFromCompleted(EventItem eventItem){
        completedContent.remove(eventItem.getEventRow());
        completed1Height -= 35;
        contentHeight -= 35;
        completed.setPreferredSize(new Dimension(400,  completed1Height));
        content.setPreferredSize(new Dimension(400, contentHeight));
        completed.revalidate();
        completed.repaint();
        content.revalidate();
    }

    private void clearAllCompleted(){
        clearAll(completedContent, eventItemsC, completed, completedHeight);

    }
    private void clearAllFailed(){
        clearAll(failedContent, eventItemsC, failed, failedHeight);

    }
    private void clearAllPending(){
        clearAll(pendingContent, eventItemsC, pending, pendingHeight);

    }

    private void clearAll(JPanel content, ArrayList<EventItem> dataStorage, JPanel container, int height){
        content.removeAll();
        dataStorage.clear();
        height = 0;
        content.setPreferredSize(new Dimension(400,height));
        container.setPreferredSize(new Dimension(400,(int) container.getPreferredSize().getHeight() + height));
        container.revalidate();


    }
    private void paintLabel(Graphics2D g, JLabel label){

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Global.colorScheme.getPrimaryColor());
        FontMetrics fontMetrics = getFontMetrics(label.getFont());
        int textWidth = 0;

        for (int x = 0; x < label.getText().length(); x++){
            textWidth += fontMetrics.charWidth(label.getText().charAt(x));
        }

        g.setFont(label.getFont());
        g.drawLine(15 + textWidth, label.getHeight()-10, label.getWidth()-40, label.getHeight()-10);
    }

    private void setVerticalScrollBarColor(JScrollPane jScrollPane, Color color){
        jScrollPane.getVerticalScrollBar().setUI(
                new BasicScrollBarUI(){


                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = color;
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        ImageIcon imageIcon;

                        switch(orientation){
                            case SwingConstants.SOUTH: imageIcon = Global.colorScheme.getSbButtonDown();
                            case SwingConstants.NORTH: imageIcon = Global.colorScheme.getSbButtonUp();
                            case SwingConstants.EAST: imageIcon = Global.colorScheme.getSbButtonRight();
                            default: imageIcon = Global.colorScheme.getSbButtonDown();
                        }


                        return new JButton(imageIcon)
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

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        ImageIcon imageIcon;

                        switch(orientation){
                            case SwingConstants.SOUTH: imageIcon = Global.colorScheme.getSbButtonDown();
                            case SwingConstants.NORTH: imageIcon = Global.colorScheme.getSbButtonUp();
                            case SwingConstants.EAST: imageIcon = Global.colorScheme.getSbButtonRight();
                            default: imageIcon = Global.colorScheme.getSbButtonUp();
                        }


                        return new JButton(imageIcon)
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
                                g.drawImage(Global.colorScheme.getSbButtonUp().getImage(), 2,2,null);
                            }
                        };
                    }
                });


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof  RefreshButton){
            try{

                EventRow eventRow =(EventRow) ((RefreshButton) e.getSource()).getParent();
                moveFromFailedtoPending((EventRow.EventTest) eventRow.getEventItem());
            }
            catch (Exception e1){

                EventRow eventRow =(EventRow) ((RefreshButton) e.getSource()).getParent();
                moveFromFailedtoPending((TicketModel) eventRow.getEventItem());
            }
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

    public synchronized void moveFromPendingtoCompleted(EventItem eventItem){

        removeFromPending(eventItem);
        eventItem.getEventRow().setButtonOn(false);
        retractToCompleted(eventItem.getEventRow());
        pendingContent.revalidate();
        pending.revalidate();
        completedContent.revalidate();
        completed.revalidate();
        content.revalidate();
        pendingContent.repaint();
        pending.repaint();
        completedContent.repaint();
        completed.repaint();
        content.repaint();
        revalidate();
        repaint();
        if (pendingContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffPending();

        if (failedContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffFailed();


        if(failedContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnFailed();

        if(pendingContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnPending();

        if( pendingContent.getComponentCount() == 0 && failedContent.getComponentCount() == 0){
            Global.trafficPanel.turnOffPending();
            Global.trafficPanel.turnOffFailed();
            Global.trafficPanel.turnOnSuccess();

        }
        if(pendingContent.getComponentCount() != 0 || failedContent.getComponentCount() != 0){
            Global.trafficPanel.turnOffSuccess();
        }

    }
    public  synchronized void moveFromPendingtoFailed(EventItem eventItem){


        removeFromPending(eventItem);
        retractToFailed(eventItem.getEventRow());
        pending.revalidate();
        pendingContent.revalidate();
        failed.revalidate();
        failedContent.revalidate();
        pending.repaint();
        pendingContent.repaint();
        failed.repaint();
        failedContent.repaint();
        revalidate();
        repaint();

        if (pendingContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffPending();

        if (failedContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffFailed();


        if(failedContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnFailed();

        if(pendingContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnPending();

        if( pendingContent.getComponentCount() == 0 && failedContent.getComponentCount() == 0){
            Global.trafficPanel.turnOffPending();
            Global.trafficPanel.turnOffFailed();
            Global.trafficPanel.turnOnSuccess();

        }
        if(pendingContent.getComponentCount() != 0 || failedContent.getComponentCount() != 0){
            Global.trafficPanel.turnOffSuccess();
        }
    }


    public  synchronized void moveFromFailedtoPending(EventRow.EventTest eventItem){

        removeFromFailed(eventItem);
        retractToPending(eventItem.getEventRow());
        pending.revalidate();
        pendingContent.revalidate();
        failed.revalidate();
        failedContent.revalidate();
        pending.repaint();
        pendingContent.repaint();
        failed.repaint();
        failedContent.repaint();
        content.revalidate();
        revalidate();
        repaint();

        if (pendingContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffPending();

        if (failedContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffFailed();


        if(failedContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnFailed();

        if(pendingContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnPending();

        if( pendingContent.getComponentCount() == 0 && failedContent.getComponentCount() == 0){
            Global.trafficPanel.turnOffPending();
            Global.trafficPanel.turnOffFailed();
            Global.trafficPanel.turnOnSuccess();

        }
        if(pendingContent.getComponentCount() != 0 || failedContent.getComponentCount() != 0){
            Global.trafficPanel.turnOffSuccess();
        }
    }


    public  synchronized void moveFromFailedtoPending(TicketModel eventItem){

        removeFromFailed(eventItem);
        retractToPending(eventItem.getEventRow());
        pending.revalidate();
        pendingContent.revalidate();
        failed.revalidate();
        failedContent.revalidate();
        pending.repaint();
        pendingContent.repaint();
        failed.repaint();
        failedContent.repaint();
        content.revalidate();
        revalidate();
        repaint();

        if (pendingContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffPending();

        if (failedContent.getComponentCount() == 0)
            Global.trafficPanel.turnOffFailed();


        if(failedContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnFailed();

        if(pendingContent.getComponentCount() > 0)
            Global.trafficPanel.turnOnPending();

        if( pendingContent.getComponentCount() == 0 && failedContent.getComponentCount() == 0){
            Global.trafficPanel.turnOffPending();
            Global.trafficPanel.turnOffFailed();
            Global.trafficPanel.turnOnSuccess();

        }
        if(pendingContent.getComponentCount() != 0 || failedContent.getComponentCount() != 0){
            Global.trafficPanel.turnOffSuccess();
        }
    }


    public JPanel getContent() {
        return content;
    }

    public JPanel getFailed() {
        return failed;
    }

    public JPanel getPending() {
        return pending;
    }

    public JPanel getCompleted() {
        return completed;
    }

    public JPanel getFailedContent() {
        return failedContent;
    }

    public JPanel getPendingContent() {
        return pendingContent;
    }

    public JPanel getCompletedContent() {
        return completedContent;
    }

    public int getContentHeight() {
        return contentHeight;
    }

    public int getFailedHeight() {
        return failedHeight;
    }

    public int getPendingHeight() {
        return pendingHeight;
    }

    public int getCompletedHeight() {
        return completedHeight;
    }
}
