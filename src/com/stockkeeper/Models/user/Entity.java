package com.stockkeeper.Models.user;

import com.stockkeeper.Models.Location;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Controller.helpers.json.simple.parser.ParseException;
import com.stockkeeper.Models.settings.OrganizationSettings;

import java.util.TimeZone;

public class Entity {
    private String id;
    private String name;
    private String password;
    private Location location;
    private OrganizationSettings organizationSettings;



    public Entity(){}

    public Entity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public Entity constructFromJson(String json) {
        JSONParser parser = new JSONParser();
        try{
            JSONObject obj = (JSONObject) parser.parse(json);
            name = obj.get("name").toString();
            id = obj.get("id").toString();
            organizationSettings = new OrganizationSettings(obj.get("settings").toString());
            //TimeZone timeZone = TimeZone.getTimeZone(obj.get("tz_info").toString());
            //location = new Location(obj.get("country_code").toString(),obj.get("language").toString(),timeZone);

        }
        catch(ParseException pe) {
            pe.printStackTrace();

        }
        return this;
    }

    public OrganizationSettings getOrganizationSettings() {
        return organizationSettings;
    }

    public void setOrganizationSettings(OrganizationSettings organizationSettings) {
        this.organizationSettings = organizationSettings;
    }
}
