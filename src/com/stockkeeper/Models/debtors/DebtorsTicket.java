package com.stockkeeper.Models.debtors;

import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.Ticket;

public class DebtorsTicket extends Ticket {
    public DebtorsTicket(Product product, Integer quantity, Double unitCost, String comments, java.util.GregorianCalendar date) {
        super(product, quantity, unitCost, comments, date);
    }
}
