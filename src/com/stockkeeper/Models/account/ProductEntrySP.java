package com.stockkeeper.Models.account;

import com.stockkeeper.Models.product.Product;

public class ProductEntrySP {

    private Product product;
    private double quantity;
    private double discount;
    private double taxRate;
    private double shipping;
    private boolean isTaxPercent;
    private boolean isDiscountPercent;
    private boolean isShippingPercent;
    private boolean isDefaultTax;


    public ProductEntrySP(Product product, double quantity, double discount, double taxRate, double shipping, boolean isTaxPercent, boolean isDiscountPercent, boolean isShippingPercent, boolean isDefaultTax) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.taxRate = taxRate;
        this.shipping = shipping;
        this.isTaxPercent = isTaxPercent;
        this.isDiscountPercent = isDiscountPercent;
        this.isShippingPercent= isShippingPercent;
        this.isDefaultTax = isDefaultTax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public boolean isTaxPercent() {
        return isTaxPercent;
    }

    public void setTaxPercent(boolean taxPercent) {
        isTaxPercent = taxPercent;
    }

    public boolean isDiscountPercent() {
        return isDiscountPercent;
    }

    public void setDiscountPercent(boolean discountPercent) {
        isDiscountPercent = discountPercent;
    }

    public boolean isDefaultTax() {
        return isDefaultTax;
    }

    public void setDefaultTax(boolean defaultTax) {
        isDefaultTax = defaultTax;
    }

    public boolean isShippingPercent() {
        return isShippingPercent;
    }

    public void setShippingPercent(boolean shippingPercent) {
        isShippingPercent = shippingPercent;
    }
}



