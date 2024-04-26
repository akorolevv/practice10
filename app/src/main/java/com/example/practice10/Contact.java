package com.example.practice10;

public class Contact {
    private int id;
    private String name;
    private String number;
    private String email;
    private String info;


    public Contact(String field2, String field3, String field4, String field5) {
        this.name = field2;
        this.number = field3;
        this.email = field4;
        this.info = field5;
    }

    public Contact(int id, String field2, String field3, String field4, String field5) {
        this.id = id;
        this.name = field2;
        this.number = field3;
        this.email = field4;
        this.info = field5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String field2) {
        this.name = field2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String field3) {
        this.number = field3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String field4) {
        this.email = field4;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String field5) {
        this.info = field5;
    }
}