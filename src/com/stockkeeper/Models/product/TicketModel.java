package com.stockkeeper.Models.product;

import com.stockkeeper.Controller.API;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.Expense;
import com.stockkeeper.Models.ItemEntry;
import com.stockkeeper.Views.EventItem;
import com.stockkeeper.Views.EventPanel;
import com.stockkeeper.Views.EventRow;
import com.stockkeeper.Views.uicomponents.FontsList;
import com.stockkeeper.Views.uicomponents.RefreshButton;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TicketModel implements EventItem, Runnable {

    //Event
    private String id;
    private String account;
    private String eventText;
    private JProgressBar progressBar;
    private RefreshButton rButton;
    private EventRow eventRow;
    private LinkedList<Entry> entries;
    private LinkedList<Expense> expenses;
    private String staff;


    private String comments;
    private String note;
    private String customer;
    private String reference;
    private GregorianCalendar date;
    private LinkedList<ItemEntry> entries2;




    public TicketModel(LinkedList<Entry> entries, String note, String reference, GregorianCalendar date, int type) {
        progressBar = new JProgressBar(0,100){
        };
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.GREEN);
        progressBar.setFont(FontsList.getSitkaBanner(Font.PLAIN, 8));
        rButton = new RefreshButton();
        this.entries = entries;
        this.note = note;
        this.date = date;
        this.reference = reference;
    }


    public TicketModel(LinkedList<Expense> expenses, String note, String reference,String customer, String staff, GregorianCalendar date) {
        progressBar = new JProgressBar(0,100){
        };
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.GREEN);
        progressBar.setFont(FontsList.getSitkaBanner(Font.PLAIN, 8));
        rButton = new RefreshButton();
        this.entries = entries;
        this.note = note;
        this.date = date;
        this.reference = reference;
        this.customer= customer;
        this.staff = staff;
    }


    public TicketModel(LinkedList<Entry> entries, String note, GregorianCalendar date) {

        progressBar = new JProgressBar(0,100){
        };
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.GREEN);
        progressBar.setFont(FontsList.getSitkaBanner(Font.PLAIN, 8));
        rButton = new RefreshButton();
        this.entries = entries;
        this.note = note;
        this.date = date;
    }

    public TicketModel(LinkedList<Entry> entries, String note, String customer, GregorianCalendar date) {

        progressBar = new JProgressBar(0,100){
        };
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.GREEN);
        progressBar.setFont(FontsList.getSitkaBanner(Font.PLAIN, 8));
        rButton = new RefreshButton();
        this.entries = entries;
        this.note = note;
        this.customer = customer;
        this.date = date;
    }

    public TicketModel(LinkedList<Entry> entries) {
        this.entries = entries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        switch (account){
            case "Sales":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
            case "Purchases":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
            case "Returns Inward":
                eventText = eventText + " " + account + date.getTime().getTime()  + " to Cloud";
            case "Returns Outward":
                eventText = eventText + " " + account + date.getTime().getTime()  + " to Cloud";
            case "Damages":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
            case "Debtors":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
            case "Creditors":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
            case "expense":
                eventText = eventText + " " + account + date.getTime().getTime() + " to Cloud";
        }
    }

    public LinkedList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(LinkedList<Entry> entries) {
        this.entries = entries;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getTotal(){
        double total = 0;
        for (Entry entry: entries){
            total += entry.getTotal().getGrossTotal();
        }
        return total;
    }

    public double getTaxPayable(){
        double total = 0;
        for (Entry entry: entries){
            total += entry.getTotal().getTaxTotal();
        }
        return total;
    }

    public double getAverageTaxRate(){
        double total = 0;
        for (Entry entry: entries){
            if(entry.isDefaultTax()){
                if(entry.getProduct().isPercent()){
                    total += entry.getProduct().getTaxRate();
                }
                else{
                    double rate = (100*entry.getProduct().getTaxRate()) / (entry.getTotal().getGrossTotal() - entry.getProduct().getTaxRate());
                }
            }
            else{
                total+= entry.getTaxOverride();
            }
        }
        return total;
    }


    public String getDateString() {





        return date.toString();
    }

    public LinkedList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(LinkedList<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }


    @Override
    public String getEventText() {
        return eventText;
    }

    @Override
    public JProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public RefreshButton getButton() {
        return rButton;
    }

    @Override
    public EventRow getEventRow() {
        return eventRow;
    }

    @Override
    public void setEventRow(EventRow eventRow) {

        this.eventRow = eventRow;
    }

    public LinkedList<ItemEntry> getEntries2() {
        return entries2;
    }

    public void setEntries2(LinkedList<ItemEntry> entries2) {
        this.entries2 = entries2;
    }

    @Override
    public void run() {
        try{
            EventPanel.available.acquire();
            JSONObject object = new JSONObject();
            object.put("entity", Global.entity.getName());
            object.put("user", Global.user.getName());
            object.put("password", Global.user.getPassword());
            object.put("id", account + ""+ date.getTime().getTime() );
            object.put("comments", this.comments);
            object.put("customer", this.customer);
            JSONObject entries = new JSONObject();
            progressBar.setValue(10);
            double val = (double)80 / this.entries.size();

            int count = 0;
            for(Entry entry: this.entries){
                JSONObject entryJ = new JSONObject();
                entryJ.put("product", entry.getProduct().getName());
                entryJ.put("qty", entry.getQty());
                entryJ.put("discount", entry.getDiscount());
                entryJ.put("shipping", entry.getShipping());
                entryJ.put("tax", entry.getTaxOverride());
                entryJ.put("isPercentDiscount", entry.isPercentDiscount());
                entryJ.put("isPercentShipping", entry.isPercentShipping());
                entryJ.put("isDefaultTax", entry.isDefaultTax());
                entryJ.put("isPercentTaxOverride", entry.isPercenttaxOverride());
                entries.put("entry" + count, entryJ);
                count++;
                progressBar.setValue((int) val * count);
            }
            object.put("entries", entries);
            object.put("entryLength", count);
            switch (account){
                case "Sales":
                    API.putSale(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Purchases":
                    API.putPurchase(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Returns Inward":
                    API.putReturnInward(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Returns Outward":
                    API.putReturnOutward(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Damages":
                    API.putDamages(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Debtors":
                    API.putDebtors(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "Creditors":
                    API.putCreditors(Global.entity, Global.user, object.toJSONString());
                    Global.eventPanel.moveFromPendingtoCompleted(this);
                    progressBar.setValue(100);
                    break;
                case "expense":
            }
        }
        catch (InterruptedException e){

        }
        catch (Exception e){

            Global.eventPanel.moveFromPendingtoFailed(this);
        }

        finally {
            EventPanel.available.release();
        }

    }

    public HashMap<String,Double> getGraphData(){
        HashMap<String, Double> buffer = new HashMap<>();
        ArrayList<Map.Entry<String, Double>> graphData = new ArrayList<>();
        for (Entry entry : entries){
            String key = entry.getProduct().getName();
            double value = entry.getQty();
            if(buffer.containsKey(key)){
                buffer.put(key, buffer.get(key) + value);
            }
            else{
                buffer.put(key, value);
            }
        }
        return buffer;
    }

    public void initTask(String account){
        eventText = "Commit";
        setAccount(account);
        Global.eventPanel.addtoPending(this);
    }

}

