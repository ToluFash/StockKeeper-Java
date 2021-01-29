package com.stockkeeper.Models.purchases;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class PurchasesTicket extends Ticket {
    public PurchasesTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }

}
