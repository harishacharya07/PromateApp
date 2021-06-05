package com.ttwcalc.promate.models;

public class SubContractorsListModel {

    private String name;
    private String work;

    public SubContractorsListModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfWork() {
        return work;
    }

    public void setNameOfWork(String work) {
        this.work = work;
    }

    public SubContractorsListModel(String name, String work) {
        this.name = name;
        this.work = work;
    }
}
