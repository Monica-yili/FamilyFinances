package com.bean;

public class Income {
    private int f_id;
    private int in_id;
    private String in_type;
    private double in_amount;
    private String in_time;
    private String in_note;

    public Income(){

    }

    public Income(int f_id, String in_type, double in_amount, String in_time, String in_note) {
        this.f_id = f_id;
        this.in_type = in_type;
        this.in_amount = in_amount;
        this.in_time = in_time;
        this.in_note = in_note;
    }

    public Income(int in_id, String in_type, double in_amount, String in_note) {
        this.in_id = in_id;
        this.in_type = in_type;
        this.in_amount = in_amount;
        this.in_note = in_note;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getIn_id() {
        return in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }

    public String getIn_type() {
        return in_type;
    }

    public void setIn_type(String in_type) {
        this.in_type = in_type;
    }

    public double getIn_amount() {
        return in_amount;
    }

    public void setIn_amount(double in_amount) {
        this.in_amount = in_amount;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getIn_note() {
        return in_note;
    }

    public void setIn_note(String in_note) {
        this.in_note = in_note;
    }
}
