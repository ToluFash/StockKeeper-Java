package com.stockkeeper;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.EventPanel;
import com.stockkeeper.Views.Login;
import com.stockkeeper.Views.TrafficPanel;

import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Global.count = 0;
        Global.setUpColorScheme();
        Global.eventPanel= new EventPanel();
        Global.trafficPanel = new TrafficPanel();
        Main main = new Main();
        Locale locale = new Locale("EN", "NG");
        Currency currency = Currency.getInstance(locale);
        UIManager.put("ComboBox.background", Global.colorScheme.getSenaryColor());
        UIManager.put("ComboBox.selectionForeground", Global.colorScheme.getDenaryColor());
        main.start();

   }

    private void start(){
       Login login = new Login();
       login.setVisible(true);
       }

}








