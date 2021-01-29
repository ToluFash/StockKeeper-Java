package com.stockkeeper.Views;

import com.stockkeeper.Models.product.TicketModel;

public interface Selectable {


     boolean isSelected();

     void setSelected(boolean selected);
     void turnOn();
     void turnOff();
     TicketModel getTicket();
}
