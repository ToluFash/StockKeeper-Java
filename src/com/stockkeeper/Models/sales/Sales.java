package com.stockkeeper.Models.sales;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.ProductsModel;
import com.stockkeeper.Models.account.Account;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.TicketModel;

import java.util.*;

public class Sales extends Account {

    public Sales() {
        tickets = new HashMap<>();
        type ="Sales Account";
    }

    public Sales constructFromJson(String json) {
        JSONParser parser = new JSONParser();
        try
        {
            JSONObject obj = (JSONObject) parser.parse(json);
            id = obj.get("id").toString();
            title = obj.get("title").toString();

            for (int x = 0; x < Integer.parseInt(obj.get("entryLength").toString()); x++ ){
                JSONObject ticket = (JSONObject) obj.get("entry" + String.valueOf(x));
                String id = ticket.get("id").toString();
                String note = ticket.get("note").toString();
                String customer = ticket.get("customer").toString();
                GregorianCalendar date = new GregorianCalendar(
                        Integer.parseInt(ticket.get("year").toString()),
                        Integer.parseInt(ticket.get("month").toString())-1,
                        Integer.parseInt(ticket.get("day").toString()),
                        Integer.parseInt(ticket.get("hour").toString()),
                        Integer.parseInt(ticket.get("minute").toString()),
                        Integer.parseInt(ticket.get("second").toString())
                );
                date.setTimeZone(TimeZone.getTimeZone(ticket.get("timeZone").toString()));
                //date.setTimeZone(TimeZone.getTimeZone("America/Belize"));
                //System.out.println(date.getTimeZone());
                date.add(Calendar.SECOND,Integer.parseInt(ticket.get("offset").toString()));
                 LinkedList<Entry> entries = new LinkedList<>();

                for (int y = 0; y < Integer.parseInt(ticket.get("entryLength").toString()); y++ ){
                    JSONObject entryJson = (JSONObject) ticket.get("entry" + String.valueOf(y));
                    String product = entryJson.get("product").toString();
                    Product product1 = new Product(product);
                    ;
                    for (Product p : Global.products){
                        if (p.getName().equals(product)){
                            product1 = p;
                            break;
                        }
                    }
                    Entry entry = new Entry(
                            product1,
                            (int)Double.parseDouble(entryJson.get("qty").toString()),
                            Double.parseDouble(entryJson.get("discount").toString()),
                            Double.parseDouble(entryJson.get("shipping").toString()),
                            Double.parseDouble(entryJson.get("taxOverride").toString()),
                            Boolean.parseBoolean(entryJson.get("isPercentDiscount").toString()),
                            Boolean.parseBoolean(entryJson.get("isPercentShipping").toString()),
                            Boolean.parseBoolean(entryJson.get("isDefaultTax").toString()),
                            Boolean.parseBoolean(entryJson.get("isPercentOverrideTax").toString())
                    );
                    entries.addLast(entry);
                }
                TicketModel ticketModel = new TicketModel(entries, note, customer, date);
                ticketModel.setId(id);
                tickets.put(id, ticketModel);
            }
            GregorianCalendar g1 = new GregorianCalendar(2020,11,5,0,0,0);
            GregorianCalendar g2 = new GregorianCalendar(2020,11,7,0,0,0);

            getTickets(g1.getTime(), g2.getTime());


        }
        catch(ParseException pe) {
            System.out.println(pe.getMessage());

        }
        return this;
    }


}
