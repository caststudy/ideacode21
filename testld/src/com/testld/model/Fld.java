package com.testld.model;


public class Fld {
    int flda;
    String fldb;


    float fldc;
    String fldd;

    public Fld(int flda, String fldb, float fldc, String fldd) {
        this.flda = flda;
        this.fldb = fldb;
        this.fldc = fldc;
        this.fldd = fldd;
    }

    public Fld() {
    }

    public int getFlda() {
        return flda;
    }

    public void setFlda(int flda) {
        this.flda = flda;
    }

    public String getFldb() {
        return fldb;
    }

    public void setFldb(String fldb) {
        this.fldb = fldb;
    }

    public float getFldc() {
        return fldc;
    }

    public void setFldc(float fldc) {
        this.fldc = fldc;
    }

    public String getFldd() {
        return fldd;
    }

    public void setFldd(String fldd) {
        this.fldd = fldd;
    }
}
