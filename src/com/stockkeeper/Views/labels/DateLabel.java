package com.stockkeeper.Views.labels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DateLabel extends JLabel {

    private Boolean sentinel;
    public DateLabel() {
        super(new Date().toString());
        new Timer(100, updateClockAction).start();
    }

    private ActionListener updateClockAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setText(new Date().toString());
        }
    };













}
