package com.stockkeeper.Models;

import java.util.Date;

public class CustomDate extends Date {
    public CustomDate() {
    }

    public CustomDate(long date) {
        super(date);
    }

    public CustomDate(int year, int month, int date) {
        super(year, month, date);
    }

    public CustomDate(int year, int month, int date, int hrs, int min) {
        super(year, month, date, hrs, min);
    }

    public CustomDate(int year, int month, int date, int hrs, int min, int sec) {
        super(year, month, date, hrs, min, sec);
    }

    public CustomDate(String s) {
        super(s);
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }
}
