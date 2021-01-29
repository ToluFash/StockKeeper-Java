package com.stockkeeper.Models.returnsoutward;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class ReturnsOutwardTicket extends Ticket {

    public ReturnsOutwardTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }
}
