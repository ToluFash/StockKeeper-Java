package com.stockkeeper.Models.product;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.category.Category;

import java.util.ArrayList;

public class Product {

    private String id;
    private String name;
    private Category category;
    private Integer quantity;
    private Double unitCost;
    private Double taxRate;
    private Boolean isPercent;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }


    public Product(String name, Category category, Integer quantity, Double unitCost) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitCost =unitCost;
    }

    public Product(String name, Category category,  Double unitCost, Double taxRate, Boolean isPercent) {
        this.name = name;
        this.category = category;
        this.unitCost =unitCost;
        this.taxRate =taxRate;
        this.isPercent = isPercent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getPercent() {
        return isPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Boolean isPercent() {
        return isPercent;
    }

    public void setPercent(Boolean percent) {
        isPercent = percent;
    }


    public static ArrayList<Product> constructFromJson(String json) {
        ArrayList<Product> products = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj = (JSONObject) parser.parse(json);

            for (int y = 0; y < Integer.parseInt(obj.get("entryLength").toString()); y++ ){
                JSONObject ticket = (JSONObject) obj.get("entry" + String.valueOf(y));
                Product product = new Product(
                        ticket.get("name").toString(),
                        new Category(ticket.get("category").toString()),
                        Double.parseDouble(ticket.get("unitCost").toString()),
                        Double.parseDouble(ticket.get("taxRate").toString()),
                        Boolean.parseBoolean(ticket.get("isPercent").toString())
                );
                product.setId(ticket.get("id").toString());
                products.add(product);
            }
        }
        catch(ParseException pe) {
            System.out.println(pe.getMessage());

        }
        return products;
    }









}
