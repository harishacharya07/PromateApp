package com.ttwcalc.promate;

public class SubContractor {
    private String name;
    private String date;
    private String id;
    private String amount;
    private String place;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    String pid;

    SubContractor() {

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

    public SubContractor(String name) {
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
