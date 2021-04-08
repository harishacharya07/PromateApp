package com.ttwcalc.promate;

public class ModelExpenditure {

    String name;
    String date;
    String id;
    String amount;
    String place;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    String pid;

    ModelExpenditure() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ModelExpenditure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place) {
        this.name = place;
    }

    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
