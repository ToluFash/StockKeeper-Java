package com.stockkeeper.Invoices;

import com.stockkeeper.Models.ItemEntry;

import java.awt.*;
import java.awt.print.PageFormat;
import java.util.LinkedList;

public interface InvoicePrintable {

    int drawInvoice(Graphics g, PageFormat pf, int page);

    int getPageMax(Graphics2D g2d, PageFormat pf);

    int paintInvoice(Container container);
    int getPaintHeight(Graphics2D g2d, int width);

}

