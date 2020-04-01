package com.example.tm01_sistemavotacionessms;

public class TelephoneNumber {
    int id;
    String t_number;

    public TelephoneNumber(int id, String t_number) {
        this.id = id;
        this.t_number = t_number;
    }

    public TelephoneNumber(String t_number) {
        this.t_number = t_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getT_number() {
        return t_number;
    }

    public void setT_number(String t_number) {
        this.t_number = t_number;
    }
}
