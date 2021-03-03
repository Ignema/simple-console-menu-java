package com.menu.components;

import com.menu.logic.Actions;
import com.menu.logic.Handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Menu {

    private String title;
    private List<Field> fields;
    private String previous;

    private final Handler handler;

    public Menu(String title, List<Field> fields, Handler handler) {
        this.title = title;
        this.fields = fields;
        this.handler = handler;
        this.previous = "";
    }

    public Menu(String title, List<Field> fields, String previous, Handler handler) {
        this.title = title;
        this.fields = fields;
        this.previous = previous;
        this.handler = handler;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void processSelection(int i){
        if(i == 0 && this.previous.equals("")){
            System.exit(0);
        } else if (i == 0){
            this.handler.navigateToSubMenu("", this.previous);
        } else {
            this.fields.stream().filter(field -> field.getPosition().equals(i)).findFirst().ifPresent(field -> {
                if (!field.getTarget().equals("")){
                    this.handler.navigateToSubMenu(this.getTitle(), field.getTarget());
                } else if(!field.getAction().equals("")){
                    try {
                        Method action = Actions.class.getMethod(field.getAction());
                        action.invoke(new Actions());
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public String toString() {
        return "Menu{" +
                "title='" + title + '\'' +
                ", fields=" + fields +
                ", previous='" + previous + '\'' +
                ", handler=" + handler +
                '}';
    }
}
