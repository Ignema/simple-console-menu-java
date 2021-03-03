package com.menu.components;

public class Field {
    private Integer position;
    private String body;
    private String target;
    private String action;

    public Field(Integer position, String body, String target){
        this.position = position;
        this.body = body;
        this.target = target;
        this.action = "";
    }
    public Field(Integer position, String body, String action, Boolean actionIdentifier){
        this.position = position;
        this.body = body;
        this.action = action;
        this.target = "";
    }


    public Field(Integer position, String body){
        this.position = position;
        this.body = body;
        this.target = "";
        this.action = "";
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return position + "- " + body ;
    }
}
