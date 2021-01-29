package com.stockkeeper.Models.product;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ItemEntry;
import com.stockkeeper.Views.uicomponents.ReferenceDisplay;
import com.stockkeeper.Views.uicomponents.RoundedTextInput;
import com.stockkeeper.Views.uicomponents.STextArea;

import java.util.*;

public class Ticket {

    private LinkedList<ItemEntry> entries;
    private String comments;
    private STextArea note;
    private RoundedTextInput customer;
    private ReferenceDisplay reference;
    private GregorianCalendar date;

    private Product product;
    private Integer quantity;
    private Double unitCost;

    public Ticket(Product product, Integer quantity, Double unitCost, String comments, GregorianCalendar date) {
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.comments = comments;
        this.date = date;
    }

    public Ticket(LinkedList<ItemEntry> entries, STextArea note, RoundedTextInput customer, GregorianCalendar date) {
        this.entries = entries;
        this.note = note;
        this.customer = customer;
        this.date = date;
    }

    public Ticket(LinkedList<ItemEntry> entries, STextArea note, ReferenceDisplay reference, GregorianCalendar date) {
        this.entries = entries;
        this.note = note;
        this.reference = reference;
        this.date = date;
    }

    public Ticket(LinkedList<ItemEntry> entries) {
        this.entries = entries;
    }

    public void saveTicketModel(String account){

        LinkedList<Entry> entriesR = new LinkedList<>();
        for (ItemEntry itemEntry: entries){
            Product product = Global.products.get(itemEntry.getProduct().getSelectedIndex());
            long qty = Long.parseLong(itemEntry.getQty().getNumberField().getText());
            Object object = itemEntry.getDiscount().getTextInput().getText();
            double discount = Double.parseDouble(itemEntry.getDiscount().getTextInput().getText());
            double shipping = Double.parseDouble(itemEntry.getShipping().getTextInput().getText());
            double taxOverride = Double.parseDouble(itemEntry.getTax().getSwitchableTextInput().getTextInput().getText());
            boolean isPercentDiscount = itemEntry.getDiscount().getPercent();
            boolean isPercentShipping = itemEntry.getShipping().getPercent();
            boolean isDefaultTax = !itemEntry.getTax().getCheckBox().isSelected();
            boolean isPercenttaxOverride = itemEntry.getTax().getSwitchableTextInput().getPercent();
            Entry entry = new Entry(product,qty,discount,shipping,taxOverride,isPercentDiscount,isPercentShipping,isDefaultTax,isPercenttaxOverride);
            entriesR.addLast(entry);
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        TicketModel ticketModel;
        if(customer!= null)
        ticketModel = new TicketModel(entriesR,note.getText(), customer.getText(), new GregorianCalendar());
        else
            ticketModel = new TicketModel(entriesR,note.getText(), reference.getSelected(), new GregorianCalendar());
        ticketModel.initTask(account);

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public String getComments() {
        return comments;
    }

    public STextArea getNote() {
        return note;
    }

    public void setNote(STextArea note) {
        this.note = note;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }


    public LinkedList<ItemEntry> getEntries() {
        return entries;
    }

    public void setEntries(LinkedList<ItemEntry> entries) {
        this.entries = entries;
    }

    public RoundedTextInput getCustomer() {
        return customer;
    }

    public void setCustomer(RoundedTextInput customer) {
        this.customer = customer;
    }

    public ReferenceDisplay getReference() {
        return reference;
    }

    public void setReference(ReferenceDisplay reference) {
        this.reference = reference;
    }

    public String getShortDate(){

        return ""+date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH)+1+"-"+date.get(Calendar.DATE);
    }
}

