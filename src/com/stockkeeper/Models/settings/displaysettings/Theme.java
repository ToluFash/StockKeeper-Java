package com.stockkeeper.Models.settings.displaysettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;

import java.awt.*;

public class Theme {
    private Color primary;
    private Color secondary;

    public Theme(Color primary, Color secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public Theme(String json) {


        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            primary = new Color(Integer.parseInt(settingsObj.get("primary").toString(),16));
            secondary = new Color(Integer.parseInt(settingsObj.get("secondary").toString(),16));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public Color getPrimary() {
        return primary;
    }

    public void setPrimary(Color primary) {
        this.primary = primary;
    }

    public Color getSecondary() {
        return secondary;
    }

    public void setSecondary(Color secondary) {
        this.secondary = secondary;
    }
}
