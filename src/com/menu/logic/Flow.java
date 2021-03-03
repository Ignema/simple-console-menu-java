package com.menu.logic;

import com.menu.components.Field;
import com.menu.components.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Flow {

    public List<Menu> getMenuList(String file){
        JSONParser parser = new JSONParser();
        try {
            JSONArray data = (JSONArray) parser.parse(new FileReader(file));
            List<Menu> menuList = new ArrayList<>();
            data.forEach(object -> {
                menuList.add(objectToMenu(object));
            });
            return menuList;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("Failed to parse json files...");
            System.exit(2);
            return null;
        }
    }

    public Menu objectToMenu(Object object){
        JSONObject menu = (JSONObject) object;

        String title = (String) menu.get("title");
        List<Field> fields = getFields((JSONArray) menu.get("fields"));
        Handler handler = new Handler(this);

        if(menu.containsKey("previous")){
            String previous = (String) menu.get("previous");
            return new Menu(title, fields, previous, handler);
        } else {
            return new Menu(title, fields, handler);
        }
    }

    public List<Field> getFields(JSONArray jsonArray) {
        List<Field> fields = new ArrayList<>();
        jsonArray.forEach(object -> {
            fields.add(objectToField(object));
        });
        return fields;
    }

    public Field objectToField(Object object){
        JSONObject field = (JSONObject) object;

        Integer position = ((Long) field.get("position")).intValue();
        String body = (String) field.get("body");

        if(field.containsKey("target")){
            String target = (String) field.get("target");
            return new Field(position, body, target);
        } else if(field.containsKey("action")) {
            String action = (String) field.get("action");
            return new Field(position, body, action, true);
        } else {
            return new Field(position, body);
        }
    }
}
