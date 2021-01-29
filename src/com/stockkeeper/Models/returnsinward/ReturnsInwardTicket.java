package com.stockkeeper.Models.returnsinward;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class ReturnsInwardTicket extends Ticket {

    public ReturnsInwardTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }
}
