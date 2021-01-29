package com.stockkeeper.Views;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.DatePicker.JDatePanel;
import com.stockkeeper.DatePicker.JDatePicker;
import com.stockkeeper.DatePicker.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class CalendarView extends JFrame implements ActionListener {

    private JPanel container;
    private GregorianCalendar calendar;
    //Panels
    private JPanel header;
    private JPanel content;
    private JPanel footer;

    public CalendarView() throws HeadlessException {
        super("Calendar");
        setSize(300, 250);
        setResizable(false);
        container = new JPanel(new BorderLayout());
        calendar = new GregorianCalendar();
        getContentPane().add(container);
        setUpCalendar();
    }





    private void setUpCalendar(){

        container.setPreferredSize(new Dimension(280,230));

        header = new JPanel();
        content = new JPanel();
        footer = new JPanel();
        header.setBackground(Global.colorScheme.getDenaryColor());
        content.setBackground(Global.colorScheme.getDenaryColor());
        footer.setBackground(Global.colorScheme.getDenaryColor());
        header.setBorder(BorderFactory.createLineBorder(Color.gray));
        content.setBorder(BorderFactory.createLineBorder(Color.gray));
        footer.setBorder(BorderFactory.createLineBorder(Color.gray));

        header.setPreferredSize(new Dimension(280,30));
        content.setPreferredSize(new Dimension(280,170));
        footer  .setPreferredSize(new Dimension(280,30));


        container.add(header, BorderLayout.NORTH);
        container.add(content, BorderLayout.CENTER);
        container.add(footer, BorderLayout.SOUTH);


        UtilDateModel model = new UtilDateModel();
        JDatePanel datePanel = new JDatePanel(model){};
        JDatePicker datePicker = new JDatePicker(datePanel){
        };
        datePicker.setPreferredSize(new Dimension(120,25));
        content.add(datePicker);
        datePicker.addActionListener(this);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
       JDatePanel datePicker = (JDatePanel) e.getSource();
       Global.Print(datePicker.getModel().getYear() + ", ");
       Global.Print(datePicker.getModel().getMonth());
       Global.Print(datePicker.getModel().getDay());

    }
}
