package com.stockkeeper.Models.settings.languagesettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;

import java.util.Locale;

public class LanguageSettings {
    Locale locale;


    public LanguageSettings(String json) {

        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            JSONObject localeS = (JSONObject) parser.parse(settingsObj.get("locale").toString());
            locale = new Locale(localeS.get("language").toString(),localeS.get("countryCode").toString());
        }
        catch(Exception e){


            e.printStackTrace();

        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
