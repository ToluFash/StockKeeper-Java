package com.stockkeeper.Models.stock;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.category.Category;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.product.Product;

import java.util.*;

public class Stock{

    private String id;
    private String title;
    private HashMap<String, Entry> tickets;
    private String type;
    private double largest =0;
    private HashMap<String, Double> graphData;

    public Stock() {
        tickets = new HashMap<>();
        type ="Stock Account";
    }

    public Stock constructFromJson(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj = (JSONObject) parser.parse(json);
            id = obj.get("id").toString();
            title = obj.get("title").toString();

            graphData = new HashMap<>();
            for (int y = 0; y < Integer.parseInt(obj.get("entryLength").toString()); y++ ){
                JSONObject ticket = (JSONObject) obj.get("entry" + String.valueOf(y));
                String product = ticket.get("product").toString();
                double quantity = Double.parseDouble(ticket.get("qty").toString());

                graphData.put(product,quantity);
                largest = largest < quantity ? quantity: largest;

                Product product1 = new Product(product,
                        new Category(ticket.get("category").toString()),
                        Double.parseDouble(ticket.get("unitCost").toString()),
                        Double.parseDouble(ticket.get("taxRate").toString()),
                        Boolean.parseBoolean(ticket.get("isPercent").toString())
                );
                Entry entry = new Entry(
                        product1,quantity);
                entry.setId(ticket.get("id").toString());
                this.tickets.put(product1.getName(), entry);
            }

        }
        catch(ParseException pe) {
            System.out.println(pe.getMessage());

        }
        return this;
    }

    public Stock(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Entry> getTickets() {
        return tickets;
    }

    public void setTickets(HashMap<String, Entry> tickets) {
        this.tickets = tickets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLargest() {
        return largest;
    }

    public void setLargest(double largest) {
        this.largest = largest;
    }

    public HashMap<String, Double>  getGraphData() {
        return graphData;
    }

    public void setGraphData(HashMap<String, Double>  graphData) {
        this.graphData = graphData;
    }
}

