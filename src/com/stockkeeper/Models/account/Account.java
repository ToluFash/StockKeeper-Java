package com.stockkeeper.Models.account;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.TicketModel;

import java.util.*;

public class Account {

    protected String id;
    protected String title;
    protected HashMap<String,TicketModel> tickets;
    protected String type;
    protected int largest =0;

    //to delete
    protected HashMap <String, Double> graphData;
    public Account() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected HashMap<String, Double> getGraphData() {
        return graphData;
    }

    public int getLargest() {
        return largest;
    }

    public double getTotal(GregorianCalendar gregorianCalendar){
        double total = 0;
        ArrayList<TicketModel> arrayList = getTickets(gregorianCalendar.getTime());
        for(TicketModel ticketModel: arrayList){
            total += ticketModel.getTotal();
        }
        return total;

    }
    public double getTaxePayablebyDay(GregorianCalendar gregorianCalendar){
        double taxPayable = 0;
        ArrayList<TicketModel> arrayList = getTickets(gregorianCalendar.getTime());
        for(TicketModel ticketModel: arrayList){
            taxPayable += ticketModel.getTaxPayable();
        }
        return taxPayable;

    }

    public ArrayList<ProductsModel> getEntriesbyProductsModel(Date  date){
        HashMap<String, ProductsModel> hashMap = new HashMap<>();
        for (Product product: Global.products){
            ProductsModel productsModel =new ProductsModel(product,getEntries(product,date));
            hashMap.put(product.getName(), productsModel);
        }
        return new ArrayList<>(hashMap.values());
    }

    public HashMap<String, Double> getEntriesbyProductsModelGraph(Date  date){
        HashMap<String, Double> graphProducts = new HashMap<>();
        for (Product product: Global.products){
            ProductsModel productsModel =new ProductsModel(product,getEntries(product,date));
            graphProducts.put(product.getName(), productsModel.getSummedQty());
        }
        return graphProducts;
    }

    public HashMap<String, Double> getTaxesbyProductsModelGraph(Date  date){
        HashMap<String, Double> graphProducts = new HashMap<>();
        for (Product product: Global.products){
            ProductsModel productsModel =new ProductsModel(product,getEntries(product,date));
            graphProducts.put(product.getName(), productsModel.getGrossTax());
        }
        return graphProducts;
    }

    public Product getHighestGrossingonTax(Date  date){
        double highestGrossing = 0;
        Product product1 = new Product("");
        for (Product product: Global.products){
            ProductsModel productsModel =new ProductsModel(product,getEntries(product,date));
            if (productsModel.getGrossTax() > highestGrossing){
                highestGrossing = productsModel.getGrossTax();
                product1 = product;
            }
        }
        return product1;
    }

    public HashMap<Date, Double> getEntriesbyProductsModelDistribution(Product product, Date date){
        HashMap<Integer, Double> hash= new HashMap<>();
        GregorianCalendar buffer = new GregorianCalendar();
        buffer.set(Calendar.MINUTE, 0);
        buffer.set(Calendar.SECOND, 0);
        buffer.set(Calendar.MILLISECOND, 0);

        for(TicketModel ticketModel: getTickets(date)){
            for (Entry entry : ticketModel.getEntries()){
                if(entry.getProduct().getName().equals(product.getName()))
                    try{
                        double before = hash.get(ticketModel.getDate().get(Calendar.HOUR_OF_DAY));
                        hash.replace(ticketModel.getDate().get(Calendar.HOUR_OF_DAY), before+ entry.getTotal().getGrossTotal());
                    }
                    catch (Exception e){
                        hash.put(ticketModel.getDate().get(Calendar.HOUR_OF_DAY),entry.getTotal().getGrossTotal());
                    }
            }
        }
        HashMap<Date, Double> hashMap = new HashMap<>();
        for (Map.Entry<Integer, Double> entry: hash.entrySet()){
            buffer.set(Calendar.HOUR_OF_DAY, entry.getKey());
            hashMap.put(buffer.getTime(), hash.get(entry.getKey()));
        }
        return hashMap;
    }

    public HashMap<String,Double> getGraphData(Date date) {
        ArrayList<TicketModel> ticketModels = getTickets(date);
        HashMap<String,Double> hashMap =new HashMap<>();
        for (TicketModel ticketModel: ticketModels){
            hashMap.put(ticketModel.getDate().toString(), ticketModel.getTotal());
        }
        return hashMap;
    }

    public HashMap<Date,Double> getDistribution(Date date) {
        GregorianCalendar reference = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        reference.setTime(date);
        end.setTime(date);
        reference.set(Calendar.HOUR_OF_DAY, 0);
        reference.set(Calendar.MINUTE, 0);
        reference.set(Calendar.SECOND, 0);
        reference.set(Calendar.MILLISECOND, 0);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        end.roll(Calendar.HOUR_OF_DAY, 1);
        end.add(Calendar.MILLISECOND, -1);
        HashMap<Date,Double> buffer = new HashMap<>();
        boolean start = false;

        for(int count = 1; count < 25; count++){
            ArrayList<TicketModel> ticketModels = getTickets(reference.getTime(),end.getTime());
            for (TicketModel ticketModel: ticketModels){
                if(ticketModel.getTotal() > 0){
                    start = true;
                }
                if(start){
                    try{
                        buffer.put(reference.getTime(),
                                buffer.get(reference.getTime())+ ticketModel.getTotal());
                    }
                    catch (Exception e){
                        buffer.put(reference.getTime(),ticketModel.getTotal());
                    }
                }
            }
            reference.add(Calendar.HOUR_OF_DAY, 1);
            end.add(Calendar.HOUR_OF_DAY, 1);
        }
        return buffer;
    }

    public HashMap<String,TicketModel> getTickets(){
        return this.tickets;
    }

    public TicketModel getTicket(String id){
        return this.tickets.get(id);
    }

    public ArrayList<TicketModel> getTickets(Date start, Date end){

        ArrayList<TicketModel> ticketModels = new ArrayList<>();
        for(Map.Entry<String,TicketModel> entry: tickets.entrySet()){
            if(entry.getValue().getDate().getTime().compareTo(start) >= 0 &&  entry.getValue().getDate().getTime().compareTo(end) <= 0)
                ticketModels.add(entry.getValue());
        }
        return ticketModels;
    }

    public ArrayList<Entry> getEntries(Product product, Date start, Date end){
        ArrayList<Entry> entries = new ArrayList<>();
        for(Map.Entry<String,TicketModel> entry: tickets.entrySet()){
            if(entry.getValue().getDate().getTime().compareTo(start) >= 0 &&  entry.getValue().getDate().getTime().compareTo(end) <= 0){
                for (Entry entry2 : entry.getValue().getEntries()){
                    if(entry2.getProduct().getName().equals(product.getName()))
                        entries.add(entry2);
                }
            }
        }
        return entries;
    }

    public ArrayList<Entry> getEntries(Product product, Date date){
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        g.set(Calendar.HOUR_OF_DAY, 0);
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.MILLISECOND, 0);
        GregorianCalendar g2 = (GregorianCalendar) g.clone();
        g2.add(GregorianCalendar.DAY_OF_WEEK,1);
        g2.add(GregorianCalendar.MILLISECOND, -1);
        //getEntries(product, g.getTime(), g2.getTime());
        return getEntries(product, g.getTime(), g2.getTime());
    }


    public ArrayList<TicketModel> getTickets(Date date){
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        g.set(Calendar.HOUR_OF_DAY, 0);
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.MILLISECOND, 0);
        GregorianCalendar g2 = (GregorianCalendar) g.clone();
        g2.add(GregorianCalendar.DAY_OF_WEEK,1);
        g2.add(GregorianCalendar.MILLISECOND, -1);
        return getTickets(g.getTime(), g2.getTime());
    }


    public ArrayList<TicketModel> getTickets(Date date, int startHour, int endHour){
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        g.set(Calendar.HOUR_OF_DAY, startHour);
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.MILLISECOND, 0);
        GregorianCalendar g2 = (GregorianCalendar) g.clone();
        g.set(Calendar.HOUR_OF_DAY, endHour);
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.MILLISECOND, 0);
        return getTickets(g.getTime(), g2.getTime());
    }


    public double getEntriesTotal(Product product, Date date){

        ArrayList<Entry>  entries = getEntries(product, date);
        double total = 0;
        for (Entry entry : entries){
            total += entry.getTotal().getGrossTotal();
        }
        return total;
    }

    public double getEntriesQuantityTotal(Product product, Date date){

        ArrayList<Entry>  entries = getEntries(product, date);
        double total = 0;
        for (Entry entry : entries){
            total += entry.getQty();
        }
        return total;
    }


    public HashMap<Date,Double> getGraphData(Product product, Date start, Date end)
    {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(start);
        g.set(Calendar.HOUR_OF_DAY, 0);
        g.set(Calendar.MINUTE, 0);
        g.set(Calendar.SECOND, 0);
        g.set(Calendar.MILLISECOND, 0);
        start = g.getTime();
        GregorianCalendar g2 = new GregorianCalendar();
        g2.setTime(end);
        g2.set(Calendar.HOUR_OF_DAY, 0);
        g2.set(Calendar.MINUTE, 0);
        g2.set(Calendar.SECOND, 0);
        g2.set(Calendar.MILLISECOND, 0);
        end = g2.getTime();

        boolean start2 = false;

        HashMap<Date,Double> hashMap = new HashMap<>();

        while(end.compareTo(start) >= 0) {
            if (getEntriesQuantityTotal(product, start) > 0) {
                start2 = true;
            }
            if (start2) {
                hashMap.put(start, getEntriesQuantityTotal(product, start));
            }
            g.add(GregorianCalendar.DATE, 1);
            start = g.getTime();
        }

        return hashMap;

    }
}




