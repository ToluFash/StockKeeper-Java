package com.stockkeeper.Views;

import com.stockkeeper.Models.product.TicketModel;

import javax.swing.*;

public class TicketDisplayItemR extends JPanel {

   protected boolean selected;
   protected TicketModel ticket;



   public boolean isSelected() {
      return selected;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
   }
   public TicketModel getTicket() {
      return ticket;
   }

   public void setTicket(TicketModel ticket) {
      this.ticket = ticket;
   }

   public void turnOn(){
   }
   public void turnOff(){

   }
}
