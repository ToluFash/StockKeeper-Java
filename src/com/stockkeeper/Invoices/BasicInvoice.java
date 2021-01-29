package com.stockkeeper.Invoices;

import com.stockkeeper.Controller.helpers.Helper;
import com.stockkeeper.Models.product.Ticket;

import javax.swing.*;
import java.awt.*;

public class BasicInvoice {


    protected Color headerColor;
    protected Color rowEvenColor;
    protected Color rowOddColor;
    protected double subTotal;
    protected double discountTotal;
    protected double shippingTotal;
    protected double taxesTotal;
    protected double netTotal;
    protected String email;
    protected String customerName;
    protected Address address;
    protected Font titleFont;
    protected Font titleFont2;
    protected Ticket ticket;
    protected ImageIcon logo;
    protected int nextLine;
    protected int pageWidth;
    protected int pageHeight;
    protected Font bodyFont;
    protected Font bodyFontThick;
    protected Font bodyFontSmall;
    protected int count;

    public BasicInvoice(Ticket ticket) {
        this.ticket = ticket;
        subTotal = 0;
        discountTotal = 0;
        taxesTotal = 0;
        netTotal = 0;
        rowEvenColor = Color.WHITE;
        rowOddColor = new Color(0xE0E0E0);
        String street = "No 29, Olorunwa Close, Alakia, Old Ife Road";
        String city = "Ibadan";
        String state = "Oyo State";
        String zipCode = "23412";
        String phoneNo = "+2349034881522";
        customerName ="Fasugba Tolulope";
        email = "fasolupat@yahoo.com";
        String website = "www.stockkkeeper.com";
        address = new Address(street,city,state,zipCode,phoneNo,website);
        count = 0;

    }


    protected int drawUnit(Graphics2D g2d, String unit, int max, int position,int nextLine){
        if(Helper.getStringWidth(unit, bodyFont, g2d) > max){
            String[] strings = cutString(unit,bodyFont,g2d,max);
            g2d.drawString(strings[0], position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
            nextLine += g2d.getFontMetrics(bodyFont).getHeight()-3;
            return drawUnit(g2d,strings[1],max,position,nextLine);
        }
        else{
            g2d.drawString(unit, position, nextLine + g2d.getFontMetrics(bodyFont).getHeight()-2);
            return  nextLine;
        }
    }

    protected int simulateDraw(Graphics2D g2d, String unit, int max, int position,int nextLine){
        if(Helper.getStringWidth(unit, bodyFont, g2d) > max){
            String[] strings = cutString(unit,bodyFont,g2d,max);
            nextLine += g2d.getFontMetrics(bodyFont).getHeight()-3;
            return simulateDraw(g2d,strings[1],max,position,nextLine);
        }
        else{
            return  nextLine;
        }

    }

    protected String[] cutString(String unit, Font font,Graphics g, int max){
        String cut = "";
        String rem = "";
        int width = 0;
        for (int x=0; x< unit.length();x++){
            char c = unit.charAt(x);
            width += g.getFontMetrics(font).charWidth(c);
            if(width > max)
                return new String[]{cut, rem};
            cut += c;
            try{
                rem = unit.substring(x+1);
            }
            catch (ArrayIndexOutOfBoundsException e){
                rem = "";

            }
        }
        return new String[]{cut, rem};
    }
}
