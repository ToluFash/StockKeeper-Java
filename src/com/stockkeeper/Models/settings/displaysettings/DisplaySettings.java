package com.stockkeeper.Models.settings.displaysettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Models.settings.accountsettings.AccountsSettings;
import com.stockkeeper.Models.settings.localesettings.LocaleSettings;

public class DisplaySettings {

    private Theme theme;
    private FontSettings fontSettings;
    private ViewSettings viewSettings;

    public DisplaySettings(Theme theme, FontSettings fontSettings, ViewSettings viewSettings) {
        this.theme = theme;
        this.fontSettings = fontSettings;
        this.viewSettings = viewSettings;
    }

    public DisplaySettings(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            theme = new Theme(settingsObj.get("theme").toString());
            fontSettings = new FontSettings(settingsObj.get("fontSettings").toString());
            viewSettings = new ViewSettings(settingsObj.get("viewSettings").toString());
        }
        catch(Exception e){
            e.printStackTrace();



        }
    }
    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public FontSettings getFontSettings() {
        return fontSettings;
    }

    public void setFontSettings(FontSettings fontSettings) {
        this.fontSettings = fontSettings;
    }

    public ViewSettings getViewSettings() {
        return viewSettings;
    }

    public void setViewSettings(ViewSettings viewSettings) {
        this.viewSettings = viewSettings;
    }
}
