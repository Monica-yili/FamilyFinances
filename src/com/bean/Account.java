package com.bean;

public class Account {
    private int f_id;
    private String f_email;
    private String f_password;
    private int f_familysize;
    private String f_sign;
    private String f_city;

    public Account(){

    }

    public Account(int f_id,String f_email,String f_password) {
        this.f_id = f_id;
        this.f_email = f_email;
        this.f_password = f_password;
    }

    public Account(String f_email, String f_password, int f_familysize, String f_sign, String f_city) {
        this.f_email = f_email;
        this.f_password = f_password;
        this.f_familysize = f_familysize;
        this.f_sign = f_sign;
        this.f_city = f_city;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_email() {
        return f_email;
    }

    public void setF_email(String f_email) {
        this.f_email = f_email;
    }

    public String getF_password() {
        return f_password;
    }

    public void setF_password(String f_password) {
        this.f_password = f_password;
    }

    public int getF_familysize() {
        return f_familysize;
    }

    public void setF_familysize(int f_familysize) {
        this.f_familysize = f_familysize;
    }

    public String getF_sign() {
        return f_sign;
    }

    public void setF_sign(String f_sign) {
        this.f_sign = f_sign;
    }

    public String getF_city() {
        return f_city;
    }

    public void setF_city(String f_city) {
        this.f_city = f_city;
    }
}
