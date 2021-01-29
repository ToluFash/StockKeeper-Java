package com.stockkeeper.Invoices;

public class Address {

    String street;
    String city;
    String state;
    String zipCode;
    String phoneNo;
    String website;

    public Address(String street, String city, String state, String zipCode, String phoneNo, String website) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNo = phoneNo;
        this.website = website;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
