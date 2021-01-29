package com.stockkeeper.Models;

import com.stockkeeper.Invoices.Address;

public class Customer {

    private String name;
    private String company;
    private Address address;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, String company, Address address) {
        this.name = name;
        this.company = company;
        this.address = address;
    }
}
