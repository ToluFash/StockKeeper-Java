package com.stockkeeper.Models.settings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Models.settings.accountsettings.AccountsSettings;
import com.stockkeeper.Models.settings.displaysettings.DisplaySettings;
import com.stockkeeper.Models.settings.languagesettings.LanguageSettings;
import com.stockkeeper.Models.settings.localesettings.LocaleSettings;
import com.stockkeeper.Views.labels.SubMenuLabel;
import com.stockkeeper.Views.labels.TopMenuLabel;
import com.stockkeeper.Views.labels.TopMenuLabel2;

public class PersonalSettings {
    private DisplaySettings displaySettings;
    private LanguageSettings languageSettings;

    public PersonalSettings(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            displaySettings = new DisplaySettings(settingsObj.get("displaySettings").toString());
            languageSettings = new LanguageSettings(settingsObj.get("languageSettings").toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public DisplaySettings getDisplaySettings() {
        return displaySettings;
    }

    public void setDisplaySettings(DisplaySettings displaySettings) {
        this.displaySettings = displaySettings;
    }

    public LanguageSettings getLanguageSettings() {
        return languageSettings;
    }

    public void setLanguageSettings(LanguageSettings languageSettings) {
        this.languageSettings = languageSettings;
    }

    public TopMenuLabel2 getSettingsTree(){
        TopMenuLabel2 perS = new TopMenuLabel2("Personal Settings");
        TopMenuLabel2 display = new TopMenuLabel2("Display");
        SubMenuLabel theme = new SubMenuLabel("Theme", 15);
        SubMenuLabel font = new SubMenuLabel("Font", 15);
        SubMenuLabel view = new SubMenuLabel("View", 15);
        SubMenuLabel language = new SubMenuLabel("Language/Region", 10);
        perS.add(display);
        perS.add(language);
        display.add(theme);
        display.add(font);
        display.add(view);
        return  perS;
    }





}
