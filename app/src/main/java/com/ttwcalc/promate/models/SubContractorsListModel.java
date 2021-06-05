package com.ttwcalc.promate.models;

public class SubContractorsListModel {

    private String name;
    private String nameOfWork;

    public SubContractorsListModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfWork() {
        return nameOfWork;
    }

    public void setNameOfWork(String nameOfWork) {
        this.nameOfWork = nameOfWork;
    }

    public SubContractorsListModel(String name, String nameOfWork) {
        this.name = name;
        this.nameOfWork = nameOfWork;
    }
}
