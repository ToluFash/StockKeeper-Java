package com.stockkeeper.Invoices;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.ItemEntry;
import com.stockkeeper.Models.product.Ticket;
import com.stockkeeper.Views.uicomponents.FontsList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.util.LinkedList;

public class Invoice1 extends BasicInvoice implements InvoicePrintable {

    private boolean started;
    private int printPosition;
    private int printCount;
    private boolean ended;

    public Invoice1(Ticket ticket) {
        super(ticket);
        titleFont = new Font("Nirmala UI Semilight", Font.PLAIN, 28);
        titleFont2 = new Font("Nirmala UI Semilight",Font.BOLD, 28);
        bodyFont = new Font("Nirmala UI Semilight", Font.PLAIN, 10);
        bodyFontThick = new Font("Helvetica", Font.PLAIN, 10);
        headerColor =new Color(0x132E57);
        logo = new ImageIcon(getClass().getResource("/com/stockkeeper/images/logoTestI.png"));
        started = false;
        printPosition = 0;
        printCount =2;
        ended = false;
    }


    @Override
    public int paintInvoice(Container container) {
        return 0;
    }

    @Override
    public int getPaintHeight(Graphics2D g2d, int width) {
        return 0;
    }

    @Override
    public int drawInvoice(Graphics g, PageFormat pf, int page) {

        if(ended){
            return 1;
        }
        if(printCount % 2 != 0){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            printCount++;

            if(!started){
                pageWidth = (int)pf.getImageableWidth();
                drawInitials(g2d,pf,page);
                drawHeader(g2d,pf,page);
                pageHeight = (getInititalHeight(g2d, pf,page) + getHeaderHeight(g2d, pf,page)) * (page+1);
                started=true;
            }

            if(printPosition < ticket.getEntries().size()){
                for (int x = printPosition; x < ticket.getEntries().size(); x++) {
                    if (pageHeight + getRowHeight(g2d, pf, page, ticket.getEntries().get(x)) > pf.getImageableHeight()-30) {
                        pageHeight = (int)(pf.getImageableY())+30;
                        nextLine = (int)(pf.getImageableY())+30;
                        return 0;
                    }
                    else {
                        drawRow(g2d, pf, page, ticket.getEntries().get(x));
                        pageHeight+=getRowHeight(g2d, pf, page, ticket.getEntries().get(x));
                        printPosition++;
                        count++;
                    }
                }
                System.out.println(pf.getImageableHeight()-90 - pageHeight);
            }
            if(pageHeight+ getFooterHeight(g2d, pf, page) > pf.getImageableHeight()-90){
                pageHeight = 0;
                nextLine = 0;
                return  0;
            }
            else
            {
                drawFooter(g2d, pf, page);
                ended = true;
                return 0;
            }
        }
        printCount++;
        return 0;

        }


    private void drawInitials(Graphics2D g2d, PageFormat pf, int page){

        // Render
        g2d.drawImage(logo.getImage(), 30, 30, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        nextLine = logo.getIconHeight();
        int nextX = logo.getIconWidth() + 34;
        g2d.setFont(titleFont);
        nextLine = g2d.getFontMetrics(titleFont).getHeight() > logo.getIconHeight()? g2d.getFontMetrics(titleFont).getHeight()+10: nextLine+10;
        g2d.drawString(Global.entity.getName(), nextX , g2d.getFontMetrics(titleFont).getHeight()+20);
        g2d.setFont(titleFont2);
        g2d.drawString("INVOICE", pageWidth- Helper.getStringWidth("INVOICE", titleFont2,g2d)-30, g2d.getFontMetrics(titleFont2).getHeight()+20);
        nextLine = g2d.getFontMetrics(titleFont).getHeight() > logo.getIconHeight()? nextLine +g2d.getFontMetrics(titleFont).getHeight(): nextLine+logo.getIconHeight();
        nextLine-= 10;


        nextX = 30;
        g2d.setFont(bodyFont);
        g2d.drawString(address.getStreet(), nextX ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setFont(bodyFontThick);
        g2d.setColor(Color.GRAY);
        g2d.drawString("Date:    ", pageWidth- Helper.getStringWidth("Date:    "+ticket.getShortDate(), bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        g2d.drawString(ticket.getShortDate(), pageWidth- Helper.getStringWidth(ticket.getShortDate(), bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.drawString(address.getCity() + ", " + address.getState() + ", " + address.getZipCode() , nextX ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setFont(bodyFontThick);
        g2d.setColor(Color.GRAY);
        g2d.drawString("Invoice No:    ", pageWidth- Helper.getStringWidth("Invoice No:    "+"Sales3654", bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        g2d.drawString("Sales3654", pageWidth- Helper.getStringWidth("Sales3654", bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.drawString(address.getPhoneNo() , nextX ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setFont(bodyFontThick);
        g2d.setColor(Color.GRAY);
        g2d.drawString("Due Date:    ", pageWidth- Helper.getStringWidth("Due Date:    "+ticket.getShortDate(), bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        g2d.drawString(ticket.getShortDate(), pageWidth- Helper.getStringWidth(ticket.getShortDate(), bodyFont ,g2d)-30,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.drawString(email , nextX ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.drawString(address.getWebsite() , nextX ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=35;

        g2d.setFont(bodyFontThick);
        g2d.setColor(headerColor);
        g2d.fillRect(nextX, nextLine, 230, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("BILL TO" , nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight()-3);

        nextX+= 250;

        g2d.setColor(headerColor);
        g2d.fillRect(nextX, nextLine, 230, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("SHIP TO" , nextX+5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight()-3);

        nextX-= 250;


        nextLine+=13;
        g2d.setColor(Color.BLACK);
        g2d.setFont(bodyFont);
        g2d.drawString(customerName, nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(customerName, nextX +255 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.setFont(bodyFont);
        g2d.drawString(Global.entity.getName(), nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(Global.entity.getName(), nextX +255 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.setFont(bodyFont);
        g2d.drawString(address.getStreet(), nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(address.getStreet(), nextX +255 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.setFont(bodyFont);
        g2d.drawString(address.getCity() + ", " + address.getState() + ", " + address.getZipCode(), nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(address.getCity() + ", " + address.getState() + ", " + address.getZipCode(), nextX +255 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());

        nextLine+=15;
        g2d.setFont(bodyFont);
        g2d.drawString(address.getPhoneNo(), nextX +5 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(address.getPhoneNo(), nextX +255 ,nextLine+g2d.getFontMetrics(bodyFont).getHeight());
        nextLine+=15;
    }

    private void drawHeader(Graphics2D g2d, PageFormat pf, int page){
        nextLine+=30;
        g2d.setColor(new Color(0x132E57));
        g2d.fillRect(30, nextLine, pageWidth-60, 15);
        pageWidth-=30;
        int descWidth = (int)(pageWidth* 0.3);
        int qtyWidth = (int)(pageWidth* 0.1);
        int priceWidth = (int)(pageWidth* 0.1);
        int discountWidth = (int)(pageWidth* 0.1);
        int shippingWidth = (int)(pageWidth* 0.1);
        int taxesWidth = (int)(pageWidth* 0.1);
        int amountWidth = (int)(pageWidth* 0.2);
        pageWidth+=30;
        int[] list ={descWidth,qtyWidth,priceWidth,discountWidth,shippingWidth,taxesWidth};

        int position =30+2;
        g2d.setFont(bodyFontThick);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Description", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= descWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Qty", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= qtyWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Unit Price", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= priceWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Discount", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= discountWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Shipping", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= shippingWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Tax", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        position+= taxesWidth;
        g2d.setColor(Color.BLACK);
        g2d.draw(new Line2D.Double(position-2,nextLine+0.5, position-2, nextLine+14.5));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Amount", position, nextLine + g2d.getFontMetrics(bodyFontThick).getHeight()-2);
        nextLine+=15;

    }
    private void drawRow(Graphics2D g2d, PageFormat pf, int page, ItemEntry entry){
        if(count % 2 == 0) {

            g2d.setColor(rowEvenColor);
        }
        else{
            g2d.setColor(rowOddColor);
        }

        int rowHeight = getRowHeight(g2d,pf,page,entry);
        g2d.fillRect(30, nextLine, pageWidth-60, rowHeight);
        pageWidth-=30;
        int descWidth = (int)(pageWidth* 0.3);
        int qtyWidth = (int)(pageWidth* 0.1);
        int priceWidth = (int)(pageWidth* 0.1);
        int discountWidth = (int)(pageWidth* 0.1);
        int shippingWidth = (int)(pageWidth* 0.1);
        int taxesWidth = (int)(pageWidth* 0.1);
        int amountWidth = (int)(pageWidth* 0.2-30);
        int[] list ={descWidth,qtyWidth,priceWidth,discountWidth,shippingWidth,taxesWidth};
        pageWidth+=30;

        g2d.setFont(bodyFont);
        g2d.setColor(Color.BLACK);
        int position =30+2;
        int lineAdd = 0;
        int buffer = 0;
        g2d.setStroke(new BasicStroke(1));
        lineAdd = drawUnit(g2d,entry.getModel().getProduct().getName()+ count,descWidth-2,position,nextLine);
        position+= descWidth;

        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,entry.getModel().getQty()+"",qtyWidth-2,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= qtyWidth;

        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,"$"+entry.getModel().getProduct().getUnitCost()+"",priceWidth-2,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= priceWidth;
        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,"$"+entry.getModel().getTotal().getDiscountTotal()+"",discountWidth-2,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= discountWidth;

        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,"$"+entry.getModel().getTotal().getShippingTotal()+"",shippingWidth-2,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= shippingWidth;

        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,"$"+entry.getModel().getTotal().getTaxTotal()+"",taxesWidth-2,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= taxesWidth;
        g2d.draw(new Line2D.Double(position-2,nextLine, position-2, nextLine+rowHeight-0.5));
        buffer = drawUnit(g2d,"$"+entry.getModel().getTotal().getGrossTotal()+"",amountWidth-2,position,nextLine);

        nextLine=lineAdd;
        subTotal += entry.getModel().getQty() * entry.getModel().getProduct().getUnitCost();
        discountTotal+= entry.getModel().getTotal().getDiscountTotal();
        shippingTotal+= entry.getModel().getTotal().getShippingTotal();
        taxesTotal+= entry.getModel().getTotal().getTaxTotal();
        netTotal+= entry.getModel().getTotal().getGrossTotal();
        nextLine+=15;

    }
    private void drawFooter(Graphics2D g2d, PageFormat pf, int page){
        int neededHeight = 150;
        g2d.setFont(bodyFont);
        g2d.setColor(headerColor);
        g2d.fillRect(30, nextLine+30, 350, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("COMMENTS" , 35,nextLine+28 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.setColor(Color.BLACK);
        g2d.drawRect(30, nextLine+30, 350, 130);
        nextLine+=10;
        g2d.drawString("1. Payment due in 30 days" , 35,nextLine+35 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString("2. Please note the invoice number in your payment method" , 35,nextLine+50 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString("Account Information" , 35,nextLine+70 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(" Bank" , 35,nextLine+85 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(" Account Name" , 35,nextLine+100 + g2d.getFontMetrics(bodyFont).getHeight());
        g2d.drawString(" Account No" , 35,nextLine+115 + g2d.getFontMetrics(bodyFont).getHeight());


        nextLine+=15;
        g2d.drawString("Subtotal:    "+"$"+subTotal , pageWidth- Helper.getStringWidth("Subtotal:    "+"$"+subTotal, bodyFont ,g2d)-30, nextLine );
        nextLine+=15;
        g2d.drawString("Discounts:    "+"$"+discountTotal, pageWidth- Helper.getStringWidth("Discounts:    "+"$"+discountTotal, bodyFont ,g2d)-30, nextLine );
        nextLine+=15;
        g2d.drawString("Shipping:    "+"$"+shippingTotal, pageWidth- Helper.getStringWidth("Shipping:    "+"$"+shippingTotal, bodyFont ,g2d)-30, nextLine );
        nextLine+=15;
        g2d.drawString("Taxes:    "+"$"+taxesTotal, pageWidth- Helper.getStringWidth("Taxes:    "+"$"+taxesTotal, bodyFont ,g2d)-30, nextLine );
        nextLine+=15;
        g2d.drawString("Total:    "+"$"+netTotal, pageWidth- Helper.getStringWidth("Total:    "+"$"+netTotal, bodyFont ,g2d)-30, nextLine );
    }

    private int getRowHeight(Graphics2D g2d, PageFormat pf, int page, ItemEntry entry){
        pageWidth = (int)pf.getImageableWidth();
        int descWidth = (int)(pageWidth* 0.3)-2;
        int qtyWidth = (int)(pageWidth* 0.1)-2;
        int priceWidth = (int)(pageWidth* 0.1)-2;
        int discountWidth = (int)(pageWidth* 0.1)-2;
        int shippingWidth = (int)(pageWidth* 0.1)-2;
        int taxesWidth = (int)(pageWidth* 0.1)-2;
        int amountWidth = (int)(pageWidth* 0.2-30)-2;
        int position =30+2;
        int lineAdd = 0;
        int buffer = 0;
        g2d.setStroke(new BasicStroke(1));

        lineAdd = simulateDraw(g2d,entry.getModel().getProduct().getName(),descWidth,position,nextLine);
        position+= descWidth;

        buffer = simulateDraw(g2d,entry.getModel().getQty()+"",qtyWidth,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= qtyWidth;

        buffer = simulateDraw(g2d,entry.getModel().getProduct().getUnitCost()+"",priceWidth,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= priceWidth;

        buffer = simulateDraw(g2d,entry.getModel().getTotal().getDiscountTotal()+"",discountWidth,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= discountWidth;

        buffer = simulateDraw(g2d,entry.getModel().getTotal().getShippingTotal()+"",shippingWidth,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= shippingWidth;

        buffer = simulateDraw(g2d,entry.getModel().getTotal().getTaxTotal()+"",taxesWidth,position,nextLine);
        lineAdd = buffer > lineAdd? buffer: lineAdd;
        position+= taxesWidth;
        buffer = simulateDraw(g2d,entry.getModel().getTotal().getGrossTotal()+"",amountWidth,position,nextLine);

        return lineAdd - nextLine +15;
    }
    private int getInititalHeight(Graphics2D g2d, PageFormat pf, int page){
        int nextLine =0;
        nextLine = logo.getIconHeight();
        nextLine = g2d.getFontMetrics(titleFont).getHeight() > logo.getIconHeight()? g2d.getFontMetrics(titleFont).getHeight()+10: nextLine+10;
        nextLine = g2d.getFontMetrics(titleFont).getHeight() > logo.getIconHeight()? nextLine +g2d.getFontMetrics(titleFont).getHeight(): nextLine+logo.getIconHeight();
        nextLine-= 10;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=35;
        nextLine+=13;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;

        return nextLine;
    }
    private int getHeaderHeight(Graphics2D g2d, PageFormat pf, int page){
        int nextLine =0;
        nextLine+=30;
        nextLine+=15;

        return nextLine;
    }
    private int getFooterHeight(Graphics2D g2d, PageFormat pf, int page){
        int nextLine =0;
        nextLine+=10;

        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;
        nextLine+=15;

        return nextLine;
    }
    public int getPageMax(Graphics2D g2d, PageFormat pf){

        int pageHeight = 0;
        int page = 0;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        pageHeight = (getInititalHeight(g2d, pf,page) + getHeaderHeight(g2d, pf,page));


            for (int x = 0; x < ticket.getEntries().size(); x++) {
                if (pageHeight + getRowHeight(g2d, pf, page, ticket.getEntries().get(x)) > pf.getImageableHeight()-30) {
                    pageHeight = (int)(pf.getImageableY())+30;
                    page++;
                }
                else {
                    pageHeight+= getRowHeight(g2d, pf, page, ticket.getEntries().get(x));
                }
            }
            if(pageHeight + getFooterHeight(g2d, pf, page) > pf.getImageableHeight()-90)
            {
                page++;
                pageHeight = (int)(pf.getImageableY());
            }
            return page;

    }
}
