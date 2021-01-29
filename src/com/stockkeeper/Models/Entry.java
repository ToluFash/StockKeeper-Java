package com.stockkeeper.Models;

import com.stockkeeper.Models.product.Product;

public class Entry  {
    private String id;
    private Product product;
    private double qty;
    private double discount;
    private double shipping;
    private double taxOverride;
    private boolean isPercentDiscount;
    private boolean isPercentShipping;
    private boolean isDefaultTax;
    private boolean isPercenttaxOverride;


    public Entry() {
    }

    public Entry(Product product, double qty) {
        this.product = product;
        this.qty = qty;
    }

    public Entry(Product product, double qty, double discount, double shipping, double taxOverride, boolean isPercentDiscount, boolean isPercentShipping, boolean isDefaultTax, boolean isPercenttaxOverride) {
        this.product = product;
        this.qty = qty;
        this.discount = discount;
        this.shipping = shipping;
        this.taxOverride = taxOverride;
        this.isPercentDiscount = isPercentDiscount;
        this.isPercentShipping = isPercentShipping;
        this.isDefaultTax = isDefaultTax;
        this.isPercenttaxOverride = isPercenttaxOverride;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTaxOverride() {
        return taxOverride;
    }

    public void setTaxOverride(double taxOverride) {
        this.taxOverride = taxOverride;
    }

    public boolean isPercentDiscount() {
        return isPercentDiscount;
    }

    public void setPercentDiscount(boolean percentDiscount) {
        isPercentDiscount = percentDiscount;
    }

    public boolean isPercentShipping() {
        return isPercentShipping;
    }

    public void setPercentShipping(boolean percentShipping) {
        isPercentShipping = percentShipping;
    }

    public boolean isDefaultTax() {
        return isDefaultTax;
    }

    public void setDefaultTax(boolean defaultTax) {
        isDefaultTax = defaultTax;
    }

    public boolean isPercenttaxOverride() {
        return isPercenttaxOverride;
    }

    public void setPercenttaxOverride(boolean percenttaxOverride) {
        isPercenttaxOverride = percenttaxOverride;
    }


    public EntryTotal getTotal(){

        double defaultTaxRate;
        double defaultTax;
        double defaultTaxMultiplier ;
        boolean defaultTaxisPercent ;
        double discountMultiplier;
        double shippingMultiplier ;
        double taxOverrideMultiplier;

        defaultTaxRate = product.getTaxRate();
        defaultTax = product.getTaxRate();
        defaultTaxMultiplier = (1+(defaultTax/100));
        defaultTaxisPercent = product.isPercent();
        discountMultiplier = (1-(discount/100));
        shippingMultiplier = (1+(shipping/100));
        taxOverrideMultiplier = (1+(taxOverride/100));


        EntryTotal entryTotal = new EntryTotal();



        if (!isDefaultTax)
        {
            if(isPercentDiscount)
                if (isPercentShipping){
                    entryTotal.setGrossTotal(isPercenttaxOverride ? taxOverrideMultiplier * ( shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )): (shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )) + taxOverride);
                    entryTotal.setDiscountTotal((discount/100)  * product.getUnitCost() * qty );
                    entryTotal.setShippingTotal((shipping/100) * product.getUnitCost() * qty * discountMultiplier);
                    entryTotal.setTaxTotal(isPercenttaxOverride ? (taxOverride/100) * ( shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )): taxOverride);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(isPercenttaxOverride);
                    entryTotal.setDefaultTax(false);
                }
                else{
                    entryTotal.setGrossTotal(isPercenttaxOverride ?  taxOverrideMultiplier *( shipping + (product.getUnitCost() * qty * discountMultiplier )): taxOverride + (product.getUnitCost() * qty * discount ) + shipping);
                    entryTotal.setDiscountTotal((discount/100)  * product.getUnitCost() * qty );
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(isPercenttaxOverride ? (taxOverride/100) * ( shipping + (product.getUnitCost() * qty * discountMultiplier )): taxOverride);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(isPercenttaxOverride);
                    entryTotal.setDefaultTax(false);
                }

            else
            {
                if (isPercentShipping){
                    entryTotal.setGrossTotal(isPercenttaxOverride ? taxOverrideMultiplier * ( shippingMultiplier * (product.getUnitCost() * qty - discount )): (shippingMultiplier * (product.getUnitCost() * qty - discount )) + taxOverride);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal((shipping/100) * (product.getUnitCost() * qty - discount));
                    entryTotal.setTaxTotal(isPercenttaxOverride ? (taxOverride/100) * ( shippingMultiplier * (product.getUnitCost() * qty - discount)): taxOverride);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(isPercenttaxOverride);
                    entryTotal.setDefaultTax(false);
                }
                else{
                    entryTotal.setGrossTotal(isPercenttaxOverride ? shipping +(taxOverrideMultiplier * (product.getUnitCost() * qty - discount)): shipping + (product.getUnitCost() * qty - discount ) + taxOverride);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(isPercenttaxOverride ? (taxOverride/100) * ( shipping + (product.getUnitCost() * qty - discount)): taxOverride);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(isPercenttaxOverride);
                    entryTotal.setDefaultTax(false);
                }

            }
        }

        else{
            if(isPercentDiscount)
                if (isPercentShipping){
                    entryTotal.setGrossTotal(product.isPercent()? defaultTaxMultiplier *
                            ( shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )): (shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )) + defaultTax );
                    entryTotal.setDiscountTotal((discount/100)  * product.getUnitCost() * qty );
                    entryTotal.setShippingTotal((shipping/100) * product.getUnitCost() * qty * discountMultiplier);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shippingMultiplier * (product.getUnitCost() * qty * discountMultiplier )): defaultTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(product.isPercent());
                    entryTotal.setDefaultTax(true);
                }
                else{
                    entryTotal.setGrossTotal((product.isPercent()) ?  defaultTaxMultiplier *( shipping + (product.getUnitCost() * qty * discountMultiplier )): defaultTax + (product.getUnitCost() * qty * discount ) + shipping);
                    entryTotal.setDiscountTotal((discount/100)  * product.getUnitCost() * qty );
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shipping + (product.getUnitCost() * qty * discountMultiplier )): defaultTax);
                    entryTotal.setRateDiscount(true);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(product.isPercent());
                    entryTotal.setDefaultTax(true);
                }

            else
            {
                if (isPercentShipping){
                    entryTotal.setGrossTotal(product.isPercent() ? defaultTaxMultiplier * ( shippingMultiplier * (product.getUnitCost() * qty - discount )): (shippingMultiplier * (product.getUnitCost() * qty - discount )) + defaultTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal((shipping/100) * (product.getUnitCost() * qty - discount));
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shippingMultiplier * (product.getUnitCost() * qty - discount)): defaultTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(true);
                    entryTotal.setRateTax(product.isPercent());
                    entryTotal.setDefaultTax(true);
                }
                else{
                    entryTotal.setGrossTotal(product.isPercent() ? shipping +(defaultTaxMultiplier * (product.getUnitCost() * qty - discount)): shipping + (product.getUnitCost() * qty - discount ) + defaultTax);
                    entryTotal.setDiscountTotal(discount);
                    entryTotal.setShippingTotal(shipping);
                    entryTotal.setTaxTotal(product.isPercent() ? (defaultTax/100) * ( shipping + (product.getUnitCost() * qty - discount)): defaultTax);
                    entryTotal.setRateDiscount(false);
                    entryTotal.setRateShipping(false);
                    entryTotal.setRateTax(product.isPercent());
                    entryTotal.setDefaultTax(true);
                }

            }

        }
        return entryTotal;
    }
}
