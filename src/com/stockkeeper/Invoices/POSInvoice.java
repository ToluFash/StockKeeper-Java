package com.stockkeeper.Invoices;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.ItemEntry;
import com.stockkeeper.Models.product.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;

public class POSInvoice extends BasicInvoice implements InvoicePrintable{


    public POSInvoice(Ticket ticket) {
        super(ticket);
        titleFont = new Font("Nirmala UI Semilight", Font.PLAIN, 10);
        titleFont2 = new Font("Nirmala UI",Font.PLAIN, 28);
        bodyFont = new Font("Nirmala UI Semilight", Font.PLAIN, 5);
        bodyFontThick = new Font("Nirmala UI Semilight", Font.BOLD, 5);
        bodyFontSmall= new Font("Nirmala UI Semilight", Font.PLAIN, 3);
        headerColor =new Color(0x132E57);
        logo = new ImageIcon(getClass().getResource("/com/stockkeeper/images/logoTestI.png"));
    }

    @Override
    public int getPaintHeight(Graphics2D g2d, int width) {
        return 0;
    }

    @Override
    public int paintInvoice(Container container) {
        return 0;
    }

    @Override
    public int drawInvoice(Graphics g, PageFormat pf, int page) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        pageWidth = (int)pf.getImageableWidth();
        nextLine= 0;
        drawInitials(g2d,pf,page);
        drawHeader(g2d,pf,page);
        for (ItemEntry entry: ticket.getEntries()
        ) {
            drawRow(g2d,pf,page, entry);
            count++;
        }
        drawFooter(g2d,pf,page);

        return 0;

    }


    private void drawInitials(Graphics2D g2d, PageFormat pf, int page){
        // Render
        g2d.drawImage(logo.getImage(), (pageWidth-logo.getIconWidth())/2, nextLine, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });

        nextLine += logo.getIconHeight()+ 5;
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        drawBorder(nextLine+3, g2d);
        g2d.setFont(titleFont);
        g2d.drawString("SALES INVOICE", (pageWidth- Helper.getStringWidth("SALES INVOICE", titleFont,g2d))/2, nextLine + g2d.getFontMetrics(titleFont).getHeight()-2);
        nextLine += g2d.getFontMetrics(titleFont).getHeight() +2;
        g2d.setFont(bodyFont);
        drawBorder(nextLine, g2d);
    }

    private void drawHeader(Graphics2D g2d, PageFormat pf, int page){
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        int qtyWidth = (int)(pageWidth* 0.2);
        int descWidth = (int)(pageWidth* 0.5);
        int priceWidth = (int)(pageWidth* 0.2);
        int position =0;
        g2d.setStroke(new BasicStroke(1));
        g2d.drawString("Qty", position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        position+= qtyWidth;
        g2d.drawString("Description", position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        position+= descWidth;
        g2d.drawString("Unit Price", position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        nextLine += g2d.getFontMetrics(bodyFont).getHeight() +2;
        drawBorder(nextLine, g2d);
    }
    private void drawRow(Graphics2D g2d, PageFormat pf, int page, ItemEntry entry){
        int qtyWidth = (int)(pageWidth* 0.2);
        int descWidth = (int)(pageWidth* 0.5);
        int priceWidth = (int)(pageWidth* 0.2);
        int discountWidth = (int)(pageWidth* 0.1);
        int shippingWidth = (int)(pageWidth* 0.1);
        int taxesWidth = (int)(pageWidth* 0.1);
        int amountWidth = (int)(pageWidth* 0.2);
        int[] list ={descWidth,qtyWidth,priceWidth,discountWidth,shippingWidth,taxesWidth};

        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        int position =0 ;
        int lineAdd = 0;
        int buffer = 0;

        lineAdd = drawUnit(g2d,entry.getModel().getQty()+"", qtyWidth, position,nextLine);
        position+= qtyWidth;

        buffer = drawUnit(g2d,entry.getModel().getProduct().getName(), descWidth, position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= descWidth;
        buffer =drawUnit(g2d,entry.getModel().getProduct().getUnitCost()+"", descWidth, position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        nextLine = 3 +lineAdd;


        g2d.setFont(bodyFontSmall);
        position =0 ;
        g2d.drawString("***", position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        position+= Helper.getStringWidth("***    ",bodyFontSmall,g2d);
        g2d.drawString("Discount:" +"$"+entry.getModel().getTotal().getDiscountTotal()+", ", position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        position+= Helper.getStringWidth("Discount:" +"$"+entry.getModel().getTotal().getDiscountTotal()+",", bodyFontSmall,g2d);
        g2d.drawString("Shipping:" +"$"+entry.getModel().getTotal().getShippingTotal()+", ", position-2, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        position+= Helper.getStringWidth("Shipping:" +"$"+entry.getModel().getTotal().getShippingTotal()+",", bodyFontSmall,g2d);
        g2d.drawString("Tax:" +"$"+entry.getModel().getTotal().getTaxTotal(), position-4, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
        nextLine += g2d.getFontMetrics(bodyFont).getHeight() +3;

        subTotal += entry.getModel().getQty() * entry.getModel().getProduct().getUnitCost();
        discountTotal+= entry.getModel().getTotal().getDiscountTotal();
        shippingTotal+= entry.getModel().getTotal().getShippingTotal();
        taxesTotal+= entry.getModel().getTotal().getTaxTotal();
        netTotal+= entry.getModel().getTotal().getGrossTotal();
        nextLine+=1;
    }

    private void drawFooter(Graphics2D g2d, PageFormat pf, int page){
        g2d.setFont(bodyFont);
        drawBorder(nextLine, g2d);
        g2d.drawString("Sub Total:", 0, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-4);
        g2d.drawString("$"+subTotal, pageWidth- Helper.getStringWidth("$"+subTotal, bodyFont,g2d), nextLine+g2d.getFontMetrics(bodyFont).getHeight()-4);
        nextLine+=g2d.getFontMetrics(bodyFont).getHeight();
        drawBorder(nextLine, g2d);
        g2d.drawString("Discount:", 0, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-4);
        g2d.drawString("$"+discountTotal, pageWidth- Helper.getStringWidth("$"+discountTotal, bodyFont,g2d), nextLine+g2d.getFontMetrics(bodyFont).getHeight()-4);
        nextLine+=g2d.getFontMetrics(bodyFont).getHeight();
        g2d.drawString("Shipping:", 0, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-4);
        g2d.drawString("$"+shippingTotal, pageWidth- Helper.getStringWidth("$"+shippingTotal, bodyFont,g2d), nextLine+g2d.getFontMetrics(bodyFont).getHeight()-4);
        nextLine+=g2d.getFontMetrics(bodyFont).getHeight();
        g2d.drawString("Taxes:", 0, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-4);
        g2d.drawString("$"+taxesTotal, pageWidth- Helper.getStringWidth("$"+taxesTotal, bodyFont,g2d), nextLine+g2d.getFontMetrics(bodyFont).getHeight()-4);
        nextLine+=g2d.getFontMetrics(bodyFont).getHeight();
        drawBorder(nextLine, g2d);
        g2d.setFont(bodyFontThick);
        g2d.drawString("Total:", 0, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-4);
        g2d.drawString("$"+netTotal, pageWidth- Helper.getStringWidth("$"+netTotal, bodyFontThick,g2d), nextLine+g2d.getFontMetrics(bodyFontThick).getHeight()-4);
        nextLine+=g2d.getFontMetrics(bodyFont).getHeight();

    }

    private void drawBorder(int y, Graphics2D g2d){
        int x = 0;
    while (x < pageWidth){
        g2d.drawString("- ", x, y);
        x+= Helper.getStringWidth("- ", bodyFont, g2d)+1;
    }
    nextLine+=1;
    }

    @Override
    public int getPageMax(Graphics2D g2d, PageFormat pf) {
        return 0;
    }
}
