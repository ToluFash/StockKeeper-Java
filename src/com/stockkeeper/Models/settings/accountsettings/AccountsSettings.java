package com.stockkeeper.Models.settings.accountsettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;

import java.util.Locale;

public class AccountsSettings {
    private String type;
    private Boolean capitalAccountEnabled;
    private Boolean cashAccountEnabled;


    public AccountsSettings(String type, Boolean capitalAccountEnabled, Boolean cashAccountEnabled) {
        this.type = type;
        this.capitalAccountEnabled = capitalAccountEnabled;
        this.cashAccountEnabled = cashAccountEnabled;
    }

    public AccountsSettings(String json) {


        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            type = settingsObj.get("accountType").toString();
            capitalAccountEnabled = settingsObj.get("capitalAccountEnabled").toString().equals("True");
            cashAccountEnabled = settingsObj.get("cashAccountEnabled").toString().equals("True");
        }
        catch(Exception e){


            e.printStackTrace();

        }

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCapitalAccountEnabled() {
        return capitalAccountEnabled;
    }

    public void setCapitalAccountEnabled(Boolean capitalAccountEnabled) {
        this.capitalAccountEnabled = capitalAccountEnabled;
    }

    public Boolean getCashAccountEnabled() {
        return cashAccountEnabled;
    }

    public void setCashAccountEnabled(Boolean cashAccountEnabled) {
        this.cashAccountEnabled = cashAccountEnabled;
    }
}
