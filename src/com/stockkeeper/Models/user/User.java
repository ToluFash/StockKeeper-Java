package com.stockkeeper.Models.user;

import com.stockkeeper.Controller.API;
import com.stockkeeper.Controller.helpers.Encrypt;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.settings.OrganizationSettings;
import com.stockkeeper.Models.settings.PersonalSettings;

public class User {

    private String id;
    private String name;
    private String type;
    private String password;
    private PersonalSettings personalSettings;

    public User(){}
    public User(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User constructFromJson(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj = (JSONObject) parser.parse(json);
            id = obj.get("id").toString();
            name = obj.get("username").toString();
            personalSettings = new PersonalSettings(obj.get("settings").toString());
            password = obj.get("password").toString();
        }
        catch(ParseException pe) {
            pe.printStackTrace();

        }
        return this;
    }

    public String getJson() {
        return null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalSettings getPersonalSettings() {
        return personalSettings;
    }

    public void setPersonalSettings(PersonalSettings personalSettings) {
        this.personalSettings = personalSettings;
    }
}
