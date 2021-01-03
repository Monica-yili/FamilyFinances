package com.bean;

public class Expend {
    private int f_id;
    private int out_id;
    private String out_type;
    private double out_amount;
    private String out_time;
    private String out_note;

    public Expend(int f_id, String out_type, double out_amount, String out_time, String out_note) {
        this.f_id = f_id;
        this.out_type = out_type;
        this.out_amount = out_amount;
        this.out_time = out_time;
        this.out_note = out_note;
    }

    public Expend(int out_id, String out_type, double out_amount, String out_note) {
        this.out_id = out_id;
        this.out_type = out_type;
        this.out_amount = out_amount;
        this.out_note = out_note;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getOut_id() {
        return out_id;
    }

    public void setOut_id(int out_id) {
        this.out_id = out_id;
    }

    public String getOut_type() {
        return out_type;
    }

    public void setOut_type(String out_type) {
        this.out_type = out_type;
    }

    public double getOut_amount() {
        return out_amount;
    }

    public void setOut_amount(double out_amount) {
        this.out_amount = out_amount;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getOut_note() {
        return out_note;
    }

    public void setOut_note(String out_note) {
        this.out_note = out_note;
    }
}
