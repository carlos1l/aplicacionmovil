package com.example.aplicacionmovil.entities;

public class Remission {
    private int id;
    private String code;
    private String value;

    public Remission() {
    }

    public Remission(int id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }

    public Remission(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
