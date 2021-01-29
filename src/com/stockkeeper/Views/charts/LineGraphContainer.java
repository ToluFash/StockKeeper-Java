package com.stockkeeper.Views.charts;

import com.stockkeeper.Models.product.Ticket;

import java.util.HashMap;

public class LineGraphContainer {
    public double total;
    private HashMap<String,Integer> items;


    public LineGraphContainer() {
        total =0;
        items = new HashMap<>();
    }
    public void addItem(Ticket ticket){
        try{
            int qty = items.get(ticket.getProduct().getName());
            items.put(ticket.getProduct().getName(), qty+ticket.getQuantity());
            total+=qty;
        }
        catch (Exception e){
            items.put(ticket.getProduct().getName(), ticket.getQuantity());
            total+= ticket.getQuantity();
        }
    }
}
