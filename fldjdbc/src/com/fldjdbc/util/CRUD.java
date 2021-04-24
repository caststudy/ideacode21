package com.fldjdbc.util;

public enum CRUD {
    CHECK("check"),
    LOGIN("login"),  LOGOUT("logout"),
    INSERT("insert"), DELETE("delete"),AJAXDEL("ajaxdel"), UPDATE("update"), SELECT("select"), DEFAULT("DEFAULT");


    private CRUD(String string) {
    }

    public static CRUD getValue(String opName) {
        for (CRUD crud : CRUD.values()) {
            if (crud.toString().equalsIgnoreCase(opName)) {
                return crud;
            }
        }
        return DEFAULT;
    }
}

