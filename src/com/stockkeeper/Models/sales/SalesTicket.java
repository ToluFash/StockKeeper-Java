package com.stockkeeper.Models.sales;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class SalesTicket extends Ticket {

    public SalesTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }
}
