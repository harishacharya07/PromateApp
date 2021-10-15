package com.ttwcalc.promate;

public class SubContractor {
    private String name;
    private String date;
    private String id;
    private String amount;
    private String projectLocation;

    public SubContractor() {
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }
}
