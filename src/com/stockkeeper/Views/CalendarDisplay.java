package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.FontsList;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarDisplay  extends JPanel implements ActionListener{

    private HashMap<Integer, CalendarLabel> index;
    private HashMap<Integer, Integer> index2;
    private JPanel header;
    private JPanel content;
    private GregorianCalendar gregorianCalendar;
    private double mPayable;
    private double tRevenue;
    private MouseListener mouseListener;



    public CalendarDisplay(GregorianCalendar gregorianCalendar, MouseListener mouseListener) {
        this.gregorianCalendar = gregorianCalendar;
        this.mouseListener = mouseListener;
        setBackground(Global.colorScheme.getPrimaryColor());
        setBorder(new EmptyBorder(0,0,0,0));
        setLayout(new BorderLayout());
        initDisplay();
        popCalendar();
    }

    public void initDisplay(){

        GridLayout gridLayoutH = new GridLayout(1,7);
        gridLayoutH.setHgap(1);
        gridLayoutH.setVgap(1);
        header = new JPanel(gridLayoutH){
        };
        header.setBorder(new EmptyBorder(1,0,0,0));
        GridLayout gridLayout = new GridLayout(6,7);
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        content = new JPanel(gridLayout);

        content.setOpaque(false);
        JLabel sunday = new JLabel("Sunday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0xFF5966));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel monday = new JLabel("Monday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0x8DFFB4));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel tuesday = new JLabel("Tuesday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0x7BE7FF));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel wednesday = new JLabel("Wednesday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0x6167FF));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel thursday = new JLabel("Thursday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0xFF51C4));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel friday = new JLabel("Friday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0xFFD655));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        JLabel saturday = new JLabel("Saturday",SwingConstants.CENTER){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0xFF9744));
                g.fillRect(0,0,getWidth(),getHeight());
                super.paintComponent(g);
            }
        };
        header.add(sunday);
        header.add(monday);
        header.add(tuesday);
        header.add(wednesday);
        header.add(thursday);
        header.add(friday);
        header.add(saturday);
        index = new HashMap<>();
        for (int x =1; x < 43; x++){
            CalendarLabel calendarLabel = new CalendarLabel();
            calendarLabel.addMouseListener(mouseListener);
            index.put(x, calendarLabel);
            content.add(calendarLabel);
        }
        add(header, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
        Timer timer = new Timer(5000, this);
        //timer.start();
    }

    public double getmPayable() {
        return mPayable;
    }

    public double gettRevenue() {
        return tRevenue;
    }

    public  void popCalendar(){
        index2 = new HashMap<>();
        GregorianCalendar buffer = (GregorianCalendar) gregorianCalendar.clone();
        buffer.set(Calendar.DAY_OF_MONTH, 1);
        int month = buffer.get(Calendar.MONTH);
        int day_of_week = buffer.get(Calendar.DAY_OF_WEEK);
        mPayable = 0;
        tRevenue = 0;
        while (month == buffer.get(Calendar.MONTH)){
            double taxPayable= Global.sales.getTaxePayablebyDay(buffer);
            double dayRevenue= Global.sales.getTotal(buffer);
            this.mPayable+= taxPayable;
            this.tRevenue+= dayRevenue;
            index.get(day_of_week).setDate(buffer.get(Calendar.DAY_OF_MONTH));
            index.get(day_of_week).setValue(taxPayable);
            if(buffer.get(Calendar.DAY_OF_MONTH) == gregorianCalendar.get(Calendar.DAY_OF_MONTH))
                index.get(day_of_week).setCurrent(true);

            index.get(day_of_week).setDateTime(buffer.getTime());
            index.get(day_of_week).setTotalRevenue(dayRevenue);
            index.get(day_of_week).turnOn(true);
            index2.put(buffer.get(Calendar.DAY_OF_MONTH), day_of_week);
            day_of_week++;
            buffer.add(Calendar.DAY_OF_MONTH, 1);
        }

    }
    public void changeCalendar(GregorianCalendar gregorianCalendar){
        this.gregorianCalendar= gregorianCalendar;
        clearCalendarDisplay();
        popCalendar();
        revalidate();
        repaint();

    }

    public void clearCalendarDisplay(){

        for(Map.Entry<Integer, CalendarLabel> entry: index.entrySet()){
            entry.getValue().setValue(0);
            entry.getValue().turnOn(false);

        }
    }

    public  CalendarLabel getCalendarLabelInstance(){
        return  new CalendarLabel();
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Global.colorScheme.getQuaternaryColor());
        g.drawRect(1,0,getWidth()-1,getHeight());
        g.setColor(Global.colorScheme.getPrimaryColor());
        g.fillRect(2,0, getWidth()-4,getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popCalendar();
    }

    public class CalendarLabel extends JLabel implements ActionListener, MouseListener, MouseMotionListener {


        private Date dateTime;
        private Color background;
        private int width;
        private int x;
        private int xMouse = 0;
        private Timer timer;
        private int date;
        private double value;
        private double totalRevenue;
        private Currency currency;
        private Boolean on;
        private Boolean current;
        private Boolean selected;

        public CalendarLabel(int date, double value, Currency currency) {
            this.date = date;
            this.value = value;
            this.currency = currency;
            setBorder(BorderFactory.createLineBorder(Global.colorScheme.getPrimaryColor()));
            this.background = Global.colorScheme.getSecondaryColor();
        }

        public CalendarLabel(String text) {
            super(text);
            setBackground(Global.colorScheme.getSecondaryColor());
        }

        public CalendarLabel() {
            turnOn(false);
            this.background = Color.WHITE;
            currency = Currency.getInstance("USD");
            addMouseListener(this);
            current = false;
            selected =false;
        }

        public CalendarLabel(Boolean current) {
            turnOn(false);
            this.background = Color.WHITE;
            currency = Currency.getInstance("USD");
            addMouseListener(this);
            this.current = current;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }

        public double getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(double totalRevenue) {
            this.totalRevenue = totalRevenue;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public Boolean isOn() {
            return on;
        }

        public void turnOn(Boolean on) {
            this.on = on;
            if(!on)
                current = false;

        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public void setCurrent(Boolean current) {
            this.current = current;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            if(isOn()){
                if(selected)
                    g2d.setColor(Global.colorScheme.getQuaternaryColor());
                else
                    g2d.setColor(background);
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Global.colorScheme.getPrimaryColor());
                //g2d.drawRect(0,0,getWidth(),getHeight());
                g.setFont(FontsList.getHelvetica(Font.PLAIN, 12));
                if(current){
                    int width = getStringWidth(date+"", FontsList.getHelvetica(Font.PLAIN, 12));
                    g2d.setColor(Color.RED);
                    g2d.fillRect(3, 5, width+4, 12);
                    g2d.setColor(Color.WHITE);
                    g2d.drawString(date+"", 5, 15);
                }
                else{

                    g2d.setColor(Color.BLACK);
                    g2d.drawString(date+"", 5, 15);
                }
                g2d.setFont(FontsList.getHelvetica(Font.PLAIN, 20));
                g2d.setColor(Global.colorScheme.getPrimaryColor().darker());
                g2d.drawString(currency.getSymbol()+value+"", (getWidth()-getStringWidth(currency.getSymbol()+value,FontsList.getHelvetica(Font.PLAIN, 20)))/2, getHeight()/2 + 20);
            }
            else{
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0,0,getWidth(),getHeight());
            }
        }

        public int getStringWidth(String string, Font font){
            FontMetrics metrics = getFontMetrics(font);
            int width = 0;

            for (int x = 0; x< string.length();x++){
                width += metrics.charWidth(string.charAt(x));
            }
            return width;
        }



        public void animateBanner(){
            width =  350 - getStringWidth(value+"",FontsList.getHelvetica(Font.PLAIN, 20));
            x = 0;
            if(width >= -3){
                x= (350- getStringWidth(value+"",FontsList.getHelvetica(Font.PLAIN, 20)))/2;

            }
            else{
                timer = new Timer(100, this);
                timer.start();

            }
        }


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(timer!=null)
                timer.stop();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(timer!=null)
                timer.start();
            xMouse = 0;

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(isOn()){
                background = Global.colorScheme.getQuaternaryColor();
                repaint();
            }


        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(isOn()){
                background = Color.WHITE;
                repaint();
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(timer!=null){

                if(xMouse == 0)
                    xMouse = e.getX();
                int direction = e.getX()- xMouse;


                if(direction < 0){
                    if(x == width-1)
                        x += 0;
                    else
                        x-=1;
                }
                if(direction > 0){
                    if(x >= 0)
                        x += 0;
                    else
                        x+=1;
                }
                repaint();
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(x == width-1)
                x = 0;
            else
                x-=1;
            repaint();
        }







    }

}
