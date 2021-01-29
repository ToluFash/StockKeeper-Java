package com.stockkeeper.Models;

public class EntryTotal {

    private double grossTotal;
    private double discountTotal;
    private double taxTotal;
    private double shippingTotal;
    private boolean isRateDiscount;
    private boolean isRateTax;
    private boolean isRateShipping;
    private boolean defaultTax;

    public EntryTotal() {
    }


    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public double getShippingTotal() {
        return shippingTotal;
    }

    public void setShippingTotal(double shippingTotal) {
        this.shippingTotal = shippingTotal;
    }

    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public boolean isRateDiscount() {
        return isRateDiscount;
    }

    public void setRateDiscount(boolean rateDiscount) {
        isRateDiscount = rateDiscount;
    }

    public boolean isRateTax() {
        return isRateTax;
    }

    public void setRateTax(boolean rateTax) {
        isRateTax = rateTax;
    }

    public boolean isRateShipping() {
        return isRateShipping;
    }

    public void setRateShipping(boolean rateShipping) {
        isRateShipping = rateShipping;
    }

    public boolean isDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(boolean defaultTax) {
        this.defaultTax = defaultTax;
    }
}
