package com.stockkeeper.Views;

import com.stockkeeper.Models.Entry;

import javax.swing.*;

public class StockDisplayItem extends JPanel {

   protected boolean selected;
   protected Entry stockItem;



   public boolean isSelected() {
      return selected;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }
   public Entry getStockItem() {
      return stockItem;
   }

   public void setStockItem(Entry stockItem) {
      this.stockItem = stockItem;
   }
   public void turnOn(){
   }
   public void turnOff(){

   }
}
