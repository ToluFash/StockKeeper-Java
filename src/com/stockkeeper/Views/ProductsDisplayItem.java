package com.stockkeeper.Views;

import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ProductsModel;

import javax.swing.*;

public class ProductsDisplayItem extends JPanel {

   protected boolean selected;
   protected ProductsModel productsModel;;

   public boolean isSelected() {
      return selected;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(ProductsModel productsModel) {
        this.productsModel = productsModel;
    }

    public void turnOn(){
   }
   public void turnOff(){

   }
}
