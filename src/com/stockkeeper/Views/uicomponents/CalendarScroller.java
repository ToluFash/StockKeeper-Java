package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class CalendarScroller extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ComponentListener {
    private GregorianCalendar gC;
    private int xMouseY = 0;
    private int xMouseYC = 0;
    private int xMouseM = 0;
    private int xMouseMC = 0;
    private int xMouseD = 0;
    private int xMouseDC = 0;
    //Top Panels
    private JPanel yearC;
    private JPanel monthC;
    private JPanel dayC;
    //Components
    private JPanel year;
    private JPanel day;
    private JPanel month;
    //Bounds
    private int yearBoundY;
    private int yearBoundHeight;
    private int dayBoundY;
    private int dayBoundHeight;
    private Dimension parentDimensions;
    //Insets
    private Insets yInsets ;
    //Carets
    private JLabel caretY;
    private JLabel caretM;
    private JLabel caretD;
    //First Instance
    private boolean isInit = false;
    private int offset;
    private int count;
    //Action Listeners
    private ArrayList<ActionListener> actionListeners;

    public CalendarScroller() {
        gC = new GregorianCalendar();
        actionListeners = new ArrayList<>();
        setBackground(Global.colorScheme.getTertiaryColor());
        setBorder(new EmptyBorder(5,0,5,0));
        setDayPanel();
        setMonthPanel();
        setYearPanel();
        addComponentListener(this);
    }
    public  void init(){
        initListeners();
    }

    public void setYearPanel(){
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setVgap(0);
        yearC = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getTertiaryColor());
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Global.colorScheme.getPrimaryColor());
                g2d.fillRoundRect(0,0, getWidth(),getHeight(),10,10);
            }
        };
        yearC.setLayout(null);
        yearC.setPreferredSize(new Dimension(29,570));
        year = new JPanel(flowLayout);
        year.setPreferredSize(new Dimension(28,7500));
        year.setBorder(new EmptyBorder(0,0,0,0));
        year.setOpaque(false);
        //year.setBackground(new Color(0x6167FF));
        year.addMouseMotionListener(this);
        year.addMouseListener(this);
        year.addMouseWheelListener(this);
        add(yearC);
        caretY = new Caret();
        yearC.add(year);
        yearC.add(caretY);
    }

    public void setMonthPanel(){
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setVgap(0);
        monthC = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getTertiaryColor());
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Global.colorScheme.getPrimaryColor());
                g2d.fillRoundRect(0,0, getWidth(),getHeight(),5,5);
            }
        };
        monthC.setLayout(null);
        monthC.setPreferredSize(new Dimension(10,180));
        month = new JPanel(flowLayout);
        month.setPreferredSize(new Dimension(10,180));
        month.setBorder(new EmptyBorder(0,0,0,0));
        month.setOpaque(false);
        //month.setBackground(new Color(0x6167FF));
        month.addMouseMotionListener(this);
        month.addMouseListener(this);
        month.addMouseWheelListener(this);
        add(monthC);
        caretM = new Caret();
        monthC.add(month);
        monthC.add(caretM);
    }

    public void setDayPanel(){
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setVgap(0);
        dayC = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Global.colorScheme.getTertiaryColor());
                g2d.fillRect(0,0,getWidth(),getHeight());
                g2d.setColor(Global.colorScheme.getPrimaryColor());
                g2d.fillRoundRect(0,0, getWidth(),getHeight(),10,10);
            }
        };
        dayC.setLayout(null);
        dayC.setPreferredSize(new Dimension(15,420));
        day = new JPanel(flowLayout);
        day.setBorder(new EmptyBorder(0,0,0,0));
        day.setOpaque(false);
        //day.setBackground(new Color(0x6167FF));
        day.addMouseMotionListener(this);
        day.addMouseListener(this);
        day.addMouseWheelListener(this);
        add(dayC);
        caretD = new Caret();
        dayC.add(day);
        dayC.add(caretD);
    }

    public void fillYear(){
        int current = gC.get(Calendar.YEAR);
        int height = (int)yearC.getPreferredSize().getHeight();
        int cNos = height/15;
        GregorianCalendar buffer = (GregorianCalendar) gC.clone();
        buffer.set(Calendar.YEAR, 1970);

        while (buffer.get(Calendar.YEAR) < 2271){
            year.add(new DateLabel(buffer.get(Calendar.YEAR)+""));
            buffer.add(Calendar.YEAR, 1);
        }

        yearBoundY =-(current- 1970 - cNos/2) *15;
        yearBoundHeight =(int)year.getPreferredSize().getHeight()-yearBoundY;
        yInsets = yearC.getInsets();
        year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);
        if(cNos % 2 == 0){
            caretY.setBounds(0,(height/2),30, 15);
        }
        else
            caretY.setBounds(0,height/2,30, 15);
        caretY.addMouseMotionListener(this);
        caretY.addMouseListener(this);
        yearC.setComponentZOrder(year, 1);
        yearC.setComponentZOrder(caretY, 0);
    }

    public void fillMonth(){
        //String[] months = {"Jan", "Feb", "Mar","Apr", "May","Jun","Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
        String[] months = {"J", "F", "M","A", "M","J","J", "A","S", "O", "N", "D"};

        for(String month: months ){
            this.month.add(new DateLabel(month));
        }

        month.setBounds(monthC.getInsets().left, monthC.getInsets().top, (int)month.getPreferredSize().getWidth(), (int)month.getPreferredSize().getHeight());
        caretM.setBounds(0,gC.get(Calendar.MONTH)*15,30, 15);
        caretM.addMouseMotionListener(this);
        caretM.addMouseListener(this);
        monthC.setComponentZOrder(month, 1);
        monthC.setComponentZOrder(caretM, 0);
    }

    public void fillDay(){
        /*
        gC.set(Calendar.DATE, 5);
        gC.set(Calendar.MONTH, 11);
        gC.set(Calendar.YEAR, 2020);
        */
        GregorianCalendar buffer = (GregorianCalendar) gC.clone();
        buffer.set(Calendar. DAY_OF_MONTH, 1);
        int current = gC.get(Calendar.DAY_OF_MONTH);
        int month = buffer.get(Calendar.MONTH);
        count = 0;
        while(buffer.get(Calendar.MONTH) == month){
            this.day.add(new DateLabel(buffer.get(Calendar.DAY_OF_MONTH)+""));
            buffer.add(Calendar.DATE, 1);
            count++;
        }
        day.setPreferredSize(new Dimension(14,15*count));

        if(count== 28){
            day.setBounds(dayC.getInsets().left, monthC.getInsets().top, (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight());
            caretD.setBounds(0,(gC.get(Calendar.DATE)-1)*15,30, 15);
        }
        if(count== 29){
            day.setBounds(dayC.getInsets().left, monthC.getInsets().top, (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight());
            caretD.setBounds(0,(gC.get(Calendar.DATE)-1)*15,30, 15);
            if(gC.get(Calendar.DATE) > 28){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(15), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
        }
        if(count== 30){
            day.setBounds(dayC.getInsets().left, monthC.getInsets().top, (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight());
            caretD.setBounds(0,(gC.get(Calendar.DATE)-1)*15,30, 15);

            if(gC.get(Calendar.DATE) == 29){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(15), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
            if(gC.get(Calendar.DATE) == 30){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(30), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
        }
        if(count== 31){
            day.setBounds(dayC.getInsets().left, monthC.getInsets().top, (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight());
            caretD.setBounds(0,(gC.get(Calendar.DATE)-1)*15,30, 15);

            if(gC.get(Calendar.DATE) == 29){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(15), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
            if(gC.get(Calendar.DATE) == 30){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(30), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
            if(gC.get(Calendar.DATE) == 31){
                day.setBounds(dayC.getInsets().left, monthC.getInsets().top -(45), (int)day.getPreferredSize().getWidth(), (int)day.getPreferredSize().getHeight() );
                caretD.setBounds(0,27*15,30, 15);
            }
        }

        dayBoundY = (int) day.getBounds().getY();
        dayBoundHeight =(int) day.getBounds().getY();
        caretD.addMouseMotionListener(this);
        caretD.addMouseListener(this);
        dayC.setComponentZOrder(day, 1);
        dayC.setComponentZOrder(caretD, 0);
    }



    public void resizePanels(Dimension d){

        int height1 = (int)d.getHeight()-(int)parentDimensions.getHeight();
        parentDimensions.height += height1;
        yearC.setPreferredSize(new Dimension(20,(int) yearC.getPreferredSize().getHeight() + height1));
        yearBoundY += height1;
        yearBoundHeight += height1;
        year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);
        caretY.setBounds(0,(int)caretY.getBounds().getY() + height1,30, 15);
        offset = (int) yearC.getPreferredSize().getHeight() % 15;

    }
    public void initListeners(){
        if(getParent() != null){
            getParent().addComponentListener(this);
        }
    }

    public GregorianCalendar getCalendar() {
        return gC;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == caretY || e.getSource() == year){
            mouseReleasedYear(e);
        }
        if(e.getSource() == caretM || e.getSource() == month){
            mouseReleasedMonth(e);
        }
        if(e.getSource() == caretD || e.getSource() == day){
            mouseReleasedDay(e);
        }
        ActionEvent actionEvent = new ActionEvent(this, 1, null);
        for(ActionListener actionListener: actionListeners){
            actionListener.actionPerformed(actionEvent);
        }
    }

    public void mouseReleasedYear(MouseEvent e){

        if(e.getSource() == caretY){
            int height = (int)caretY.getBounds().getY() - 1;

            int rem = height  % 15;
            if(rem >= 8){
                height+= 15-rem;
                caretY.setBounds(0,height+ offset,30, 15);
            }
            else{
                height-= rem;
                caretY.setBounds(0,height+offset,30, 15);
            }
        }
        else{
            int rem = yearBoundY %15;
            if(rem < -8){
                yearBoundY -= rem + 15;
                yearBoundHeight -= rem + 15;
            }
            else{
                yearBoundY -= rem;
                yearBoundHeight -= rem;
            }
            year.setBounds(yInsets.left, yearBoundY+offset, (int)year.getPreferredSize().getWidth(), yearBoundHeight);
            xMouseY = 0;
        }


        int location = ((int)caretY.getBounds().getY())/ 15;
        int locationP = -((int)year.getBounds().getY())/ 15;
        int yearAtLocationP = 1970 + locationP;
        int relativeYear = yearAtLocationP+ location;
        gC.set(Calendar.YEAR, relativeYear);

    }
    public void mouseReleasedMonth(MouseEvent e){


        if(e.getSource() == caretM){
            int height = (int)caretM.getBounds().getY() - 1;

            int rem = height  % 15;
            if(rem >= 8){
                height+= 15-rem;
                caretM.setBounds(0,height+ offset,30, 15);
            }
            else{
                height-= rem;
                caretM.setBounds(0,height+offset,30, 15);
            }
        }

        int location = ((int)caretM.getBounds().getY())/ 15;
        gC.set(Calendar.MONTH, location);

    }
    public void mouseReleasedDay(MouseEvent e){
        xMouseD = 0;

        if(e.getSource() == caretD){
            int height = (int)caretD.getBounds().getY() - 1;

            int rem = height  % 15;
            if(rem >= 8){
                height+= 15-rem;
                caretD.setBounds(0,height+ offset,30, 15);
            }
            else{
                height-= rem;
                caretD.setBounds(0,height+offset,30, 15);
            }
        }
        else{
            int rem = dayBoundY %15;
            if(rem < -8){
                dayBoundY -= rem + 15;
            }
            else{
                dayBoundY -= rem;
            }
            day.setBounds(day.getInsets().left, dayBoundY, (int)day.getPreferredSize().getWidth(),(int)day.getPreferredSize().getHeight());
            xMouseY = 0;
        }

        int location = ((int)caretD.getBounds().getY())/ 15;
        int locationP = -((int)day.getBounds().getY())/ 15;
        int relativeDay = locationP+ location+1;
        gC.set(Calendar.DATE, relativeDay);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getSource() == caretY || e.getSource() == year)
        mouseDraggedYear(e);
       if(e.getSource() == caretM || e.getSource() == month)
        mouseDraggedMonth(e);
        if(e.getSource() == caretD || e.getSource() == day)
        mouseDraggedDay(e);

    }

    public void mouseDraggedYear(MouseEvent e){
        if(e.getSource() == caretY){

            if(xMouseYC == 0)
                xMouseYC = e.getY();
            int direction = e.getY()- xMouseYC;

            if(direction < 0){
                if(caretY.getBounds().getY() > 0){
                    caretY.setBounds(0,(int)caretY.getBounds().getY() - (-direction),30, 15);
                }

            }
            if(direction > 0){

                if(caretY.getBounds().getY() < (int)yearC.getPreferredSize().getHeight() -15){
                    caretY.setBounds(0,(int)caretY.getBounds().getY() + direction,30, 15);
                }
            }
        }
        else{

            if(xMouseY == 0)
                xMouseY = e.getY();
            int direction = e.getY()- xMouseY;
            if(direction < 0){
                if(yearBoundY > -(301*15 - (int)yearC.getPreferredSize().getHeight()))
                {
                    yearBoundY -= (-direction);
                    yearBoundHeight -= (-direction);
                }
                if(yearBoundY < -(301*15 - (int)yearC.getPreferredSize().getHeight())){
                    yearBoundHeight  +=(-direction);
                    yearBoundY = -(301*15 - (int)yearC.getPreferredSize().getHeight());

                }
                year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);

            }
            if(direction > 0){

                if(yearBoundY < 0){

                    yearBoundY += direction;
                    yearBoundHeight += direction;
                }
                if(yearBoundY> 0){
                    yearBoundHeight  -=(direction);
                    yearBoundY = 0;

                }
                year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);
            }
        }
    }

    public void mouseDraggedMonth(MouseEvent e){

        if(e.getSource() == caretM){

            if(xMouseMC == 0)
                xMouseMC = e.getY();
            int direction = e.getY()- xMouseMC;

            if(direction < 0){
                if(caretM.getBounds().getY() > 0){
                    caretM.setBounds(0,(int)caretM.getBounds().getY() - (-direction),30, 15);
                }

            }
            if(direction > 0){

                if(caretM.getBounds().getY() < (int)monthC.getPreferredSize().getHeight() -15){
                    caretM.setBounds(0,(int)caretM.getBounds().getY() + direction,30, 15);
                }
            }
        }
    }

    public void mouseDraggedDay(MouseEvent e){

        if(e.getSource() == caretD){

            if(xMouseDC == 0)
                xMouseDC = e.getY();
            int direction = e.getY()- xMouseDC;

            if(direction < 0){
                if(caretD.getBounds().getY() > 0){
                    caretD.setBounds(0,(int)caretD.getBounds().getY() - (-direction),30, 15);
                }

            }
            if(direction > 0){

                if(caretD.getBounds().getY() < (int)dayC.getPreferredSize().getHeight() -15){
                    caretD.setBounds(0,(int)caretD.getBounds().getY() + direction,30, 15);
                }
            }
        }
        else{
            if(xMouseD == 0)
                xMouseD = e.getY();
            int direction = e.getY()- xMouseD;
            if(direction < 0){
                if(dayBoundY > -(count-28) *15){
                    dayBoundY += direction;
                }
                if(dayBoundY < -(count-28) *15){
                    dayBoundY = -(count-28)*15;
                }
                day.setBounds(day.getInsets().left,dayBoundY, (int)day.getPreferredSize().getWidth(),(int)day.getPreferredSize().getHeight() );

            }
            if(direction > 0){
                if(dayBoundY < 0){
                    dayBoundY +=direction;
                }
                if(dayBoundY > 0 ){
                    dayBoundY = 0;
                }
                day.setBounds(day.getInsets().left,dayBoundY, (int)day.getPreferredSize().getWidth(),(int)day.getPreferredSize().getHeight() );
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if(e.getSource() == caretY || e.getSource() == year)
            mouseWheelMovedYear(e);
        if(e.getSource() == caretD || e.getSource() == day)
            mouseWheelMovedDay(e);

        ActionEvent actionEvent = new ActionEvent(this, 1, null);
        for(ActionListener actionListener: actionListeners){
            actionListener.actionPerformed(actionEvent);
        }
    }

    public void mouseWheelMovedYear(MouseWheelEvent e){
        if(e.getPreciseWheelRotation() < 0){
            if(yearBoundY > -(301*15 - (int)yearC.getPreferredSize().getHeight()))
            {
                yearBoundY += e.getWheelRotation()*15;
                yearBoundHeight +=e.getWheelRotation()*15;
            }
            year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);

        }
        else{
            if(yearBoundY < 0){

                yearBoundY += e.getWheelRotation()*15;
                yearBoundHeight +=e.getWheelRotation()*15;
            }

            year.setBounds(yInsets.left, yearBoundY, (int)year.getPreferredSize().getWidth(), yearBoundHeight);
        }
        int location = ((int)caretY.getBounds().getY())/ 15;
        int locationP = -((int)year.getBounds().getY())/ 15;
        int yearAtLocationP = 1970 + locationP;
        int relativeYear = yearAtLocationP+ location;
        gC.set(Calendar.YEAR, relativeYear);
    }
    public void mouseWheelMovedDay(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            if (dayBoundY > -(count - 28) * 15) {
                {
                    dayBoundY += e.getWheelRotation() * 15;
                }
                day.setBounds(day.getInsets().left, dayBoundY, (int) day.getPreferredSize().getWidth(), (int) day.getPreferredSize().getHeight());

            }
        }

        else {
                if (dayBoundY < 0) {
                    dayBoundY += e.getWheelRotation() * 15;
                }
                day.setBounds(day.getInsets().left, dayBoundY, (int) day.getPreferredSize().getWidth(), (int) day.getPreferredSize().getHeight());
            }
        int location = ((int)caretD.getBounds().getY())/ 15;
        int locationP = -((int)day.getBounds().getY())/ 15;
        int relativeDay = locationP+ location+1;
        gC.set(Calendar.DATE, relativeDay);
        }

    @Override
    public void componentResized(ComponentEvent e) {
        if(!isInit){
            parentDimensions = e.getComponent().getSize();
            fillDay();
            fillMonth();
            fillYear();
            yearC.revalidate();
            year.revalidate();
            isInit = true;
        }

        if(getSize().getHeight() > 590){{
            setBorder(new EmptyBorder(((int)getSize().getHeight()-590)/2, 0,((int)getSize().getHeight()-590)/2,0));
        }}
        else{
            setBorder(new EmptyBorder(5,0,5,0));
        }

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


    public void addActionListener(ActionListener actionListener){
        actionListeners.add(actionListener);
    }

    class DateLabel extends JLabel{


        public DateLabel(String text) {
            super(text, SwingConstants.CENTER);
            setPreferredSize(new Dimension(30,15));
            setFont(FontsList.getHelvetica(Font.BOLD, 10));
            setForeground(Color.WHITE);
        }

        public DateLabel(String text, int horizontalAlignment) {
            super(text, horizontalAlignment);
        }

        public DateLabel() {
        }

        public int getValue(){
            return Integer.parseInt(getText());
        }
    }

    class Caret extends DateLabel{

        public Caret(String text) {
            super(text, SwingConstants.CENTER);
            setFont(FontsList.getHelvetica(Font.PLAIN, 8));
            setPreferredSize(new Dimension(40, 15));
        }

        public Caret() {
            setFont(FontsList.getHelvetica(Font.PLAIN, 8));
            setPreferredSize(new Dimension(40, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Global.colorScheme.getCaretColor());
            g2d.fillRect(0,0,getWidth(),getHeight());
            //g2d.fillPolygon(new int[]{10,0,10},new int[]{0,getHeight()/2,getHeight()},3);
        }
    }


}
