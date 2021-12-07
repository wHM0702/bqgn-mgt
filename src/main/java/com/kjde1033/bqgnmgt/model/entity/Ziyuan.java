package com.kjde1033.bqgnmgt.model.entity;

public class Ziyuan {

    private int rid;
    private String rname,rpath,upTime;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRpath() {
        return rpath;
    }

    public void setRpath(String rpath) {
        this.rpath = rpath;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    @Override
    public String toString() {
        return "Ziyuan{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rpath='" + rpath + '\'' +
                ", upTime='" + upTime + '\'' +
                '}';
    }
}
