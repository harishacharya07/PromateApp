package com.ttwcalc.promate;

public class Project {

    String id, name, date, pid;

    Project() {

    }
    public Project(String id, String name, String date, String pid) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.pid = pid;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPid() {
        return pid;
    }
    public void setPid() {
        this.pid = pid;
    }
}
