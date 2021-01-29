package com.stockkeeper.Models.account;

import com.stockkeeper.Controller.Global;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.Entry;
import com.stockkeeper.Models.Expense;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.product.TicketModel;
import com.stockkeeper.Models.sales.Sales;

import java.util.*;

public class ExpenseAccount extends Account {

    public ExpenseAccount() {
        tickets = new HashMap<>();
        type ="Expense Account";
    }


    public ExpenseAccount constructFromJson(String json) {
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
                String staff = ticket.get("staff").toString();
                String reference = ticket.get("reference").toString();
                GregorianCalendar date = new GregorianCalendar(
                        Integer.parseInt(ticket.get("year").toString()),
                        Integer.parseInt(ticket.get("month").toString())-1,
                        Integer.parseInt(ticket.get("day").toString()),
                        Integer.parseInt(ticket.get("hour").toString()),
                        Integer.parseInt(ticket.get("minute").toString()),
                        Integer.parseInt(ticket.get("second").toString())
                );
                date.setTimeZone(TimeZone.getTimeZone(ticket.get("timeZone").toString()));
                date.add(Calendar.SECOND,Integer.parseInt(ticket.get("offset").toString()));
                LinkedList<Expense> expenses = new LinkedList<>();

                for (int y = 0; y < Integer.parseInt(ticket.get("entryLength").toString()); y++ ){
                    JSONObject entryJson = (JSONObject) ticket.get("entry" + String.valueOf(y));
                    Expense expense = new Expense(
                            entryJson.get("expenseType").toString(),
                            Double.parseDouble(entryJson.get("amount").toString()),
                            entryJson.get("description").toString());
                    expenses.addLast(expense);
                }
                TicketModel ticketModel = new TicketModel(expenses, note, reference,customer, staff, date);
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
