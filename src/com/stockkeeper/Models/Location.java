package com.stockkeeper.Models;

import java.util.Locale;
import java.util.TimeZone;

public class Location {
    private Locale locale;
    private String language;
    private TimeZone timezone;
    private String state;
    private String province;

    public Location(String country, String language,TimeZone timezone) {
        locale= new Locale(language, country);
        this.language = language;
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public TimeZone getTimezone() {
        return timezone;
    }

    public void setTimezone(TimeZone timezone) {
        this.timezone = timezone;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
