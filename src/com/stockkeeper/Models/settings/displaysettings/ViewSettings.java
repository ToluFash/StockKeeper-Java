package com.stockkeeper.Models.settings.displaysettings;

import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;

public class ViewSettings {
    private String type;

    public ViewSettings(String json) {


        JSONParser parser = new JSONParser();
        try{
            JSONObject settingsObj = (JSONObject) parser.parse(json);
            type = settingsObj.get("type").toString();
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
}
