package com.stockkeeper.Models.damages;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class DamagesTicket extends Ticket {
    public DamagesTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }
}
