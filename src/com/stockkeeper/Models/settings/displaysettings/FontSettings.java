package com.stockkeeper.Models.settings.displaysettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;

import java.awt.*;

public class FontSettings extends Font {

    public FontSettings(String name, int style, int size) {
        super(name, style, size);
    }

    public FontSettings(String json) {
        super("Segoe UI", Font.PLAIN, 10);
        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            name = settingsObj.get("name").toString();
            style = Integer.parseInt(settingsObj.get("style").toString());
            size = Integer.parseInt(settingsObj.get("size").toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
