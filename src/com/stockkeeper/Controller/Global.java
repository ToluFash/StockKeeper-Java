package com.stockkeeper.Controller;

import com.stockkeeper.Models.account.ExpenseAccount;
import com.stockkeeper.Models.customexceptions.ColorScheme;
import com.stockkeeper.Models.customexceptions.NoUserFoundException;
import com.stockkeeper.Models.damages.Damages;
import com.stockkeeper.Models.debtors.Creditors;
import com.stockkeeper.Models.debtors.Debtors;
import com.stockkeeper.Models.expense.Expense;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.purchases.Purchases;
import com.stockkeeper.Models.returnsinward.ReturnsInward;
import com.stockkeeper.Models.returnsoutward.ReturnsOutward;
import com.stockkeeper.Models.sales.Sales;
import com.stockkeeper.Models.stock.Stock;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;
import com.stockkeeper.Views.EventPanel;
import com.stockkeeper.Views.MainView;
import com.stockkeeper.Views.TrafficPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Global  {
    public static MainView mainView;
    public static Entity entity;
    public static User user;
    public static ColorScheme colorScheme;
    public static EventPanel eventPanel;
    public static Stock stock;
    public static Sales sales;
    public static Purchases purchases;
    public static ReturnsInward returnsIn;
    public static ReturnsOutward returnsOut;
    public static Damages damages;
    public static Debtors debtors;
    public static Creditors creditors;
    public static ExpenseAccount expenses;
    public static int count;
    public static double bytes;
    public static int salesCount;
    public static int purchasesCount;
    public static int returnsInwardCount;
    public static int returnsOutwardCount;
    public static int damegesCount;
    public static int debtorsCount;
    public static int creditorsCount;
    public static int expenseCount;
    public static long updateDelay = 1;
    public static Semaphore mySemaphore = new Semaphore(1);
    public static Semaphore stockSemaphore = new Semaphore(1);
    public static TrafficPanel trafficPanel;
    public static ArrayList<Product> products;
    public static int colorCount = -1;
    public static Color[] thumbColors = {
            new Color(0x356A90),
            new Color(0xFF9E39),
            new Color(0x317A16),
            new Color(0x0762E7),
            new Color(0x0D8B78),
            new Color(0x6465A5),
            new Color(0x00743F),
            new Color(0xF4B210),
            new Color(0xED6450),
            new Color(0x0ABDA0),
            new Color(0x024029)
    };

    public static void Print(Object object){
        System.out.println(object);
    }

    public Global() {
    }

    public static void setUpColorScheme() {
        colorScheme = new ColorScheme();
        colorScheme.setPrimaryColor(new Color(0xDDCD85));
        colorScheme.setSecondaryColor(new Color(0xEAE3B9));
        colorScheme.setTertiaryColor(new Color(0xF0EBCD));
        colorScheme.setQuaternaryColor(new Color(0xF7F4E4));
        colorScheme.setQuinaryColor(new Color(0x564F33));
        colorScheme.setSenaryColor(new Color(0x898253));
        colorScheme.setSeptenaryColor(new Color(0x9B9462));
        colorScheme.setNonaryColor(new Color(0xAEA770));
        colorScheme.setDenaryColor(new Color(0xFFFFFF));
        colorScheme.setDenaryColor(new Color(0xFFFFFF));
        colorScheme.setGraphColor(new Color(0x7653FF));
        colorScheme.setAddnewColor1(new Color(0xB2A266));
        colorScheme.setAddnewColor2(new Color(0xD5C57C));
        colorScheme.setAddnewColor3(new Color(0x2C6114));
        colorScheme.setFontColorPrimary(new Color(0x000000));
        colorScheme.setFontColorSecondary(new Color(0xFFFFFF));
        colorScheme.setFontColorTertiary(new Color(0xEAE3B9));
        colorScheme.setCaretColor(new Color(0, 0, 0, 62));
        colorScheme.setSbButtonUp(new ImageIcon("/images/arrowWhiteUp.png"));
        colorScheme.setSbButtonDown(new ImageIcon("/images/arrowWhiteDown.png"));
        colorScheme.setSbButtonRight(new ImageIcon("/images/arrowWhiteRight.png"));
        colorScheme.setSbButtonLeft(new ImageIcon("/images/arrowWhiteLeft.png"));
        colorScheme.setdDButton(new ImageIcon("/images/ddButton.png"));
        colorScheme.setListIcon(new ImageIcon("/images/listIcon.png"));
        colorScheme.setTilesIcon(new ImageIcon("/images/tilesIcon.png"));
        colorScheme.setListIcon2(new ImageIcon("/images/listIcon2.png"));
        colorScheme.setTilesIcon2(new ImageIcon("/images/tilesIcon2.png"));
        colorScheme.setRefreshIcon(new ImageIcon("/images/refreshIcon.png"));
        colorScheme.setTaskIcon(new ImageIcon("/images/taskIcon.png"));
    }
    public static void setUpColorScheme2() {
        colorScheme = new ColorScheme();
        colorScheme.setPrimaryColor(new Color(0x18FFE4));
        colorScheme.setSecondaryColor(new Color(0xFFFFFF));
        colorScheme.setTertiaryColor(new Color(0xFFFFFF));
        colorScheme.setQuaternaryColor(new Color(0xFFFFFF));
        colorScheme.setQuinaryColor(new Color(0xFFFFFF));
        colorScheme.setSenaryColor(new Color(0xFFFFFF));
        colorScheme.setSeptenaryColor(new Color(0x0EC2B0));
        colorScheme.setNonaryColor(new Color(0x0EC2B0));
        colorScheme.setDenaryColor(new Color(0xFFFFFF));
        colorScheme.setSbButtonUp(new ImageIcon("/images/arrowWhiteUp.png"));
        colorScheme.setSbButtonDown(new ImageIcon("/images/arrowWhiteDown.png"));
        colorScheme.setSbButtonRight(new ImageIcon("/images/arrowWhiteRight.png"));
        colorScheme.setSbButtonLeft(new ImageIcon("/images/arrowWhiteLeft.png"));
        colorScheme.setListIcon(new ImageIcon("/images/listIcon.png"));
        colorScheme.setTilesIcon(new ImageIcon("/images/tilesIcon.png"));
        colorScheme.setListIcon2(new ImageIcon("/images/listIcon2.png"));
        colorScheme.setTilesIcon2(new ImageIcon("/images/tilesIcon2.png"));
        colorScheme.setRefreshIcon(new ImageIcon("/images/refreshIcon.png"));
        colorScheme.setTaskIcon(new ImageIcon("/images/taskIcon.png"));
    }

    public static void setUpColorScheme3() {
        colorScheme = new ColorScheme();
        colorScheme.setPrimaryColor(new Color(0x606363));
        colorScheme.setSecondaryColor(new Color(0x868989));
        colorScheme.setTertiaryColor(new Color(0x979A9A));
        colorScheme.setQuaternaryColor(new Color(0xC5C8C8));
        colorScheme.setQuinaryColor(new Color(0xE3E6E6));
        colorScheme.setSenaryColor(new Color(0xFFFFFF));
        colorScheme.setSeptenaryColor(new Color(0x0EC2B0));
        colorScheme.setNonaryColor(new Color(0x0EC2B0));
        colorScheme.setDenaryColor(new Color(0xFFFFFF));
        colorScheme.setSbButtonUp(new ImageIcon("/images/arrowWhiteUp.png"));
        colorScheme.setSbButtonDown(new ImageIcon("/images/arrowWhiteDown.png"));
        colorScheme.setSbButtonRight(new ImageIcon("/images/arrowWhiteRight.png"));
        colorScheme.setSbButtonLeft(new ImageIcon("/images/arrowWhiteLeft.png"));
        colorScheme.setListIcon(new ImageIcon("/images/listIcon.png"));
        colorScheme.setTilesIcon(new ImageIcon("/images/tilesIcon.png"));
        colorScheme.setListIcon2(new ImageIcon("/images/listIcon2.png"));
        colorScheme.setTilesIcon2(new ImageIcon("/images/tilesIcon2.png"));
        colorScheme.setRefreshIcon(new ImageIcon("/images/refreshIcon.png"));
        colorScheme.setTaskIcon(new ImageIcon("/images/taskIcon.png"));
    }


    public  static void startGetProducts(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    products = API.getProducts(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }


    public  static void startGetStock(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    stock = API.getStock(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }

    public  static void startGetSales(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    sales = API.getSales(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }
    public  static void startGetPurchases(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    purchases = API.getPurchases(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }
    public  static void startGetReturnsInward(){
        returnsIn = new ReturnsInward();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    returnsIn = API.getReturnsInward(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }

    public  static void startGetReturnsOutward(){
        returnsOut = new ReturnsOutward();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    returnsOut = API.getReturnsOutward(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }
    public  static void startGetDamages(){
        damages = new Damages();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    damages = API.getDamages(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }
    public  static void startGetDebtors(){
        debtors = new Debtors();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    debtors = API.getDebtors(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }

    public  static void startGetCreditors(){
        creditors = new Creditors();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    creditors = API.getCreditors(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }


    public  static void startGetExpenses(){
        expenses = new ExpenseAccount();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try{
                    expenses = API.getExpenses(entity, user);
                }
                catch (NoUserFoundException e){

                }
                catch (FileNotFoundException e){

                }
                catch (IOException e){

                }
                catch (Exception e){



                }
            }
        };
        new Thread(timerTask).start();
        timer.schedule(timerTask,updateDelay,10000);
    }






    public  static Color getThumbColor(){

        if (colorCount == 10){
            colorCount = -1;
        }
        colorCount++;

        return thumbColors[colorCount];
    }

}
