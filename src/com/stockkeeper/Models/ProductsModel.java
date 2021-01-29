package com.stockkeeper.Models;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.TicketModel;

import java.util.*;

public class ProductsModel {

    private ArrayList<Entry> entries;
    private Product product;
    private double netTotal;
    private double summedQty;
    private double grossTax;


    public ProductsModel() {
        entries = new ArrayList<>();
        netTotal = 0;
        summedQty = 0;
    }

    public ProductsModel(Product product, ArrayList<Entry> entries) {
        this.product = product;
        this.entries = entries;
        netTotal = calculateEntriesTotal();
        summedQty = calculateEntriesQty();
        grossTax = calculateEntriesTaxes();
    }

    private double calculateEntriesTotal(){
        double total = 0;

        for (Entry entry : entries)
            total += entry.getTotal().getGrossTotal();
        return total;
    }
    private double calculateEntriesQty(){
        double qty = 0;

        for (Entry entry : entries)
            qty += entry.getQty();
        return qty;
    }

    private double calculateEntriesTaxes(){
        double tax = 0;

        for (Entry entry : entries)
            tax += entry.getTotal().getTaxTotal();
        return tax;
    }

    public double getGrossTax() {
        return grossTax;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public double getSummedQty() {
        return summedQty;
    }

    public void setSummedQty(double summedQty) {
        this.summedQty = summedQty;
    }


    public HashMap<Date,Double> getDistribution(Date date) {

        GregorianCalendar g = new GregorianCalendar();
        HashMap<Date, Double> hashMap = new HashMap<>();

        for (Entry entry: entries){



        }

        return hashMap;
    }


}
