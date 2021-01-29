package com.stockkeeper.Controller.helpers;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Views.uicomponents.SButton;
import com.stockkeeper.Views.labels.SLabel;
import com.stockkeeper.Views.uicomponents.StockRow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class Helper {


    public static int getStringWidth(String string, Font font, Graphics g){
        FontMetrics metrics = g.getFontMetrics(font);
        int width = 0;

        for (int x = 0; x< string.length();x++){
            width += metrics.charWidth(string.charAt(x));
        }
        return width;
    }

    public static Comparator<Entry> entryAsc = new Comparator<>() {
        @Override
        public int compare(Entry o1, Entry o2) {

            String o1Name = o1.getProduct().getName();
            String o2Name = o2.getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? -1 : 1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };
    public static Comparator<Entry> entryDesc = new Comparator<>() {
        @Override
        public int compare(Entry o1, Entry o2) {

            String o1Name = o1.getProduct().getName();
            String o2Name = o2.getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };

    public static Comparator<String> entryAscStockName = new Comparator<>() {
        @Override
        public int compare(String e1,
                           String e2) {
            return e1.compareTo(e2);
        }
    };



    public static Comparator<Entry> entryDescStockName = new Comparator<>() {
        @Override
        public int compare(Entry o1, Entry o2) {

            String o1Name = o1.getProduct().getName();
            String o2Name = o2.getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };

    public static Comparator<String> entryAscStockLevel = new Comparator<>() {
        @Override
        public int compare(String o1, String o2) {

            double o1Name = Global.stock.getTickets().get(o1).getQty();
            double o2Name = Global.stock.getTickets().get(o2).getQty();
            if ((int)o1Name -(int)o2Name == 0)
                return o1.compareTo(o2);

            return (int)o1Name -(int)o2Name;
        }
    };

    public static Comparator<String> entryAscStockValue = new Comparator<>() {
        @Override
        public int compare(String o1, String o2) {

            double o1Name = Global.stock.getTickets().get(o1).getQty() * Global.stock.getTickets().get(o1).getProduct().getUnitCost();
            double o2Name = Global.stock.getTickets().get(o2).getQty() * Global.stock.getTickets().get(o2).getProduct().getUnitCost();
            if ((int)o1Name -(int)o2Name == 0)
                return o1.compareTo(o2);

            return (int)o1Name -(int)o2Name;
        }
    };
    public static Comparator<StockRow> stockRowAsc = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            String o1Name = o1.getStockItem().getProduct().getName();
            String o2Name = o2.getStockItem().getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? -1 : 1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };
    public static Comparator<StockRow> stockRowDesc = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            String o1Name = o1.getStockItem().getProduct().getName();
            String o2Name = o2.getStockItem().getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };

    public static Comparator<StockRow> stockRowAscStockName = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            String o1Name = o1.getStockItem().getProduct().getName();
            String o2Name = o2.getStockItem().getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? -1 : 1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };
    public static Comparator<StockRow> stockRowDescStockName = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            String o1Name = o1.getStockItem().getProduct().getName();
            String o2Name = o2.getStockItem().getProduct().getName();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };

    public static Comparator<StockRow> stockRowAscStockLevel = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            double o1Name = o1.getStockItem().getQty();
            double o2Name = o2.getStockItem().getQty();

            return (int)o1Name -(int)o2Name;
        }
    };

    public static Comparator<StockRow> stockRowDescStockLevel = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            double o1Name = o1.getStockItem().getQty();
            double o2Name = o2.getStockItem().getQty();

            return -((int)o1Name -(int)o2Name);
        }
    };

    public static Comparator<StockRow> stockRowAscStockValue = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {

            double o1Name = o1.getStockItem().getQty() * o1.getStockItem().getProduct().getUnitCost();
            double o2Name = o2.getStockItem().getQty() * o2.getStockItem().getProduct().getUnitCost();

            return (int)o1Name -(int)o2Name;
        }
    };
    public static Comparator<StockRow> stockRowDescStockValue = new Comparator<>() {
        @Override
        public int compare(StockRow o1, StockRow o2) {


            double o1Name = o1.getStockItem().getQty() * o1.getStockItem().getProduct().getUnitCost();
            double o2Name = o2.getStockItem().getQty() * o2.getStockItem().getProduct().getUnitCost();

            return -((int)o1Name -(int)o2Name);
        }
    };




    public static Comparator<TicketModel> entryAscTicketID = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            String o1Name = o1.getId();
            String o2Name = o2.getId();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? -1 : 1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };
    public static Comparator<TicketModel> entryDescTicketID = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            String o1Name = o1.getId();
            String o2Name = o2.getId();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };



    public static Comparator<TicketModel> entryAscTicketValue = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            double o1Name = o1.getTotal();
            double o2Name = o2.getTotal();

            return (int)(Math.ceil(o1Name - o2Name));
        }
    };
    public static Comparator<TicketModel> entryDescTicketValue= new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {


            double o1Name = o1.getTotal();
            double o2Name = o2.getTotal();

            return -((int)Math.ceil(o1Name - o2Name));
        }
    };



    public static Comparator<TicketModel> entryAscTicketCustomer = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            String o1Name = o1.getCustomer();
            String o2Name = o2.getCustomer();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? -1 : 1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };
    public static Comparator<TicketModel> entryDescTicketCustomer = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            String o1Name = o1.getCustomer();
            String o2Name = o2.getCustomer();
            int length = o1Name.length() <= o2Name.length()? o1Name.length(): o2Name.length() ;

            for (int x = 0; x < length ; x++){
                if (o1Name.charAt(x) < o2Name.charAt(x)){
                    return 1;
                }
                if (o1Name.charAt(x) > o2Name.charAt(x)){
                    return  -1;
                }
            }
            return o1Name.length() <= o2Name.length()? 1 : -1 ;
            //return o1Name.length() <= o2Name.length()? 1 : -1 ;
        }
    };



    public static Comparator<TicketModel> entryAscTicketDate = new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {

            GregorianCalendar o1Name = o1.getDate();
            GregorianCalendar o2Name = o2.getDate();

            return o1Name.getTime().compareTo(o2Name.getTime());
        }
    };
    public static Comparator<TicketModel> entryDescTicketDate= new Comparator<>() {
        @Override
        public int compare(TicketModel o1, TicketModel o2) {


            GregorianCalendar o1Name = o1.getDate();
            GregorianCalendar o2Name = o2.getDate();

            return -(o1Name.getTime().compareTo(o2Name.getTime()));
        }
    };















    public static void addComponents(JComponent subject, JComponent[] objects){

        for (JComponent object: objects)
            subject.add(object);
    }
    public static void addComponentstoContainer(Container subject, JComponent[] objects){

        for (JComponent object: objects)
            subject.add(object);
    }

    public static void setBGColor(JComponent[] objects, Color color){

        for (JComponent object: objects)
        {
            object.setBackground(color);
        }
    }
    public static void setFGColor(JComponent[] objects, Color color){

        for (JComponent object: objects)
        {
            object.setForeground(color);
        }
    }
    public static void setSLabelFGColor(SLabel[] objects, Color color){

        for (SLabel object: objects)
        {
            object.getLabel().setForeground(color);
        }
    }
    public static void setSButtonFGColor(SButton[] objects, Color color){

        for (SButton object: objects)
        {
            object.getLabel().setForeground(color);
        }
    }
    public static void setFont(JComponent[] objects, Font font){

        for (JComponent object: objects)
        {
            object.setFont(font);
        }
    }
    public static void setBorder(JComponent[] objects, Border border){

        for (JComponent object: objects)
        {
            object.setBorder(border);
        }
    }

    public static void setSize(JComponent[] objects, Dimension dimension){

        for ( JComponent object: objects)
        {
            object.setPreferredSize(dimension);
            object.setMinimumSize(dimension);
            object.setMaximumSize(dimension);

        }
    }


    public static void setVerticalScrollBarColor(JScrollPane jScrollPane, Color color){
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
                                return new Dimension(10, 18);
                            }

                            @Override
                            public Color getBackground() {
                                return Global.colorScheme.getQuaternaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Global.colorScheme.getQuaternaryColor());
                                g.fillRect(0, 0, getWidth()-1, getHeight());
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
                                g.drawImage(Global.colorScheme.getSbButtonUp().getImage(), 6,6,null);
                            }
                        };
                    }
                });


    }

    public static void setVerticalScrollBarColorNoButton(JScrollPane jScrollPane, Color color){
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
                                return Global.colorScheme.getQuaternaryColor();
                            }

                            @Override
                            public boolean isBorderPainted() {
                                return false;
                            }

                            @Override
                            protected void paintComponent(Graphics g) {
                                g.setColor(Global.colorScheme.getQuaternaryColor());
                                g.fillRect(0, 0, getWidth()-1, getHeight());
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
                            }
                        };
                    }
                });


    }


    public static boolean KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        Helper.computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                return true;
            }

            // mismatch after j matches
            else
            if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return  false;
    }


    public static void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    public static void search(String pattern){





    }




}
