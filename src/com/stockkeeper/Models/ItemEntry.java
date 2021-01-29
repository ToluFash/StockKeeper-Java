package com.stockkeeper.Models;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Views.uicomponents.IntegerInput;
import com.stockkeeper.Views.uicomponents.ProductComboBox;
import com.stockkeeper.Views.uicomponents.SwitchableTextInput;
import com.stockkeeper.Views.uicomponents.TickSwitchableTextInput;

import javax.swing.*;

public class ItemEntry {

    private JPanel panel;
    private ProductComboBox product;
    private IntegerInput qty;
    private SwitchableTextInput discount;
    private SwitchableTextInput shipping;
    private TickSwitchableTextInput tax;

    public ItemEntry(JPanel panel){
        this.panel = panel;
    }

    public ItemEntry(JPanel panel, ProductComboBox product, IntegerInput qty, SwitchableTextInput discount, SwitchableTextInput shipping, TickSwitchableTextInput tax) {
        this.panel = panel;
        this.product = product;
        this.qty = qty;
        this.discount = discount;
        this.shipping = shipping;
        this.tax = tax;
    }

    public ProductComboBox getProduct() {
        return product;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setProduct(ProductComboBox product) {
        this.product = product;
    }

    public IntegerInput getQty() {
        return qty;
    }

    public void setQty(IntegerInput qty) {
        this.qty = qty;
    }

    public SwitchableTextInput getDiscount() {
        return discount;
    }

    public void setDiscount(SwitchableTextInput discount) {
        this.discount = discount;
    }

    public TickSwitchableTextInput getTax() {
        return tax;
    }

    public void setTax(TickSwitchableTextInput tax) {
        tax = tax;
    }

    public SwitchableTextInput getShipping() {
        return shipping;
    }

    public void setShipping(SwitchableTextInput shipping) {
        this.shipping = shipping;
    }


    public Entry getModel(){

        Entry entry = new Entry();
        entry.setProduct(Global.products.get(getProduct().getSelectedIndex()));
        entry.setQty(Long.parseLong(qty.getNumberField().getValue().toString()));

        if (tax.getCheckBox().isSelected()){
            entry.setDefaultTax(false);
            entry.setTaxOverride(Double.parseDouble(tax.getSwitchableTextInput().getTextInput().getValue().toString()));
            entry.setPercenttaxOverride((tax.getSwitchableTextInput().getPercent()));
        }
        else {
            entry.setDefaultTax(true);
        }

        try{
            entry.setDiscount(Double.parseDouble(getDiscount().getTextInput().getValue().toString()));
        }
        catch (NullPointerException e){
            entry.setDiscount(0);
        }
        entry.setPercentDiscount(getDiscount().isPercent);

        try{
            entry.setShipping(Double.parseDouble(getShipping().getTextInput().getValue().toString()));

        }
        catch (NullPointerException e){
            entry.setShipping(0);
        }
        entry.setPercentShipping(getShipping().isPercent);



        return entry;
    }



}
