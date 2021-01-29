package com.stockkeeper.Models.settings;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Models.account.Account;
import com.stockkeeper.Models.settings.accountsettings.AccountsSettings;
import com.stockkeeper.Models.settings.localesettings.LocaleSettings;
import com.stockkeeper.Views.labels.SubMenuLabel;
import com.stockkeeper.Views.labels.TopMenuLabel;
import com.stockkeeper.Views.labels.TopMenuLabel2;

public class OrganizationSettings {
    private AccountsSettings accountsSettings;
    private LocaleSettings localeSettings;

    public OrganizationSettings(AccountsSettings accountsSettings, LocaleSettings localeSettings) {
        this.accountsSettings = accountsSettings;
        this.localeSettings = localeSettings;
    }

    public OrganizationSettings(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            accountsSettings = new AccountsSettings(settingsObj.get("accountSettings").toString());
            localeSettings = new LocaleSettings(settingsObj.get("localeSettings").toString());
        }
        catch(Exception e){
            e.printStackTrace();



        }
    }

    public AccountsSettings getAccountsSettings() {
        return accountsSettings;
    }

    public void setAccountsSettings(AccountsSettings accountsSettings) {
        this.accountsSettings = accountsSettings;
    }

    public LocaleSettings getLocaleSettings() {
        return localeSettings;
    }

    public void setLocaleSettings(LocaleSettings localeSettings) {
        this.localeSettings = localeSettings;
    }

    public TopMenuLabel2 getSettingsTree(){
        TopMenuLabel2 orgS = new TopMenuLabel2("Organization Settings");
        SubMenuLabel acctS = new SubMenuLabel("Account Options", 10);
        SubMenuLabel locS = new SubMenuLabel("Locale", 10);
        orgS.add(acctS);
        orgS.add(locS);

        return  orgS;
    }
}
