package com.ttwcalc.promate;

public class ModelExpenditure {

    private String name;
    private String date;
    private String id;
    private String amount;
    private String place;
    private String isApproved;
    private String pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setApproved(String approved) {
        this.isApproved = approved;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }



    public ModelExpenditure() {
    }
}
