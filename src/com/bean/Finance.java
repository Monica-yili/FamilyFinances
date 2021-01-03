package com.bean;

public class Finance {
    private int f_id;
    private int fin_id;
    private String fin_type;
    private double fin_rate;
    private double fin_amount;
    private String start_time;
    private String end_time;
    private String fin_note;
    private double fin_earn;

    public Finance(){}

    public Finance(int f_id,String fin_type, double fin_rate, double fin_amount, String start_time, String end_time, String fin_note,double fin_earn) {
        this.f_id = f_id;
        this.fin_type = fin_type;
        this.fin_rate = fin_rate;
        this.fin_amount = fin_amount;
        this.start_time = start_time;
        this.end_time = end_time;
        this.fin_note = fin_note;
        this.fin_earn = fin_earn;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getFin_id() {
        return fin_id;
    }

    public void setFin_id(int fin_id) {
        this.fin_id = fin_id;
    }

    public String getFin_type() {
        return fin_type;
    }

    public void setFin_type(String fin_type) {
        this.fin_type = fin_type;
    }

    public double getFin_rate() {
        return fin_rate;
    }

    public void setFin_rate(double fin_rate) {
        this.fin_rate = fin_rate;
    }

    public double getFin_amount() {
        return fin_amount;
    }

    public void setFin_amount(double fin_amount) {
        this.fin_amount = fin_amount;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getFin_note() {
        return fin_note;
    }

    public void setFin_note(String fin_note) {
        this.fin_note = fin_note;
    }

    public double getFin_earn() {
        return fin_earn;
    }

    public void setFin_earn(double fin_earn) {
        this.fin_earn = fin_earn;
    }
}
