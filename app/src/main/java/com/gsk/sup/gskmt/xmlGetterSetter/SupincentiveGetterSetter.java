package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 25-05-2017.
 */

public class SupincentiveGetterSetter {
    public String supincentiveTable;
    ArrayList<String>curMonth=new ArrayList<>();
    ArrayList<String>PM1=new ArrayList<>();
    ArrayList<String>PM2=new ArrayList<>();
    ArrayList<String>PM3=new ArrayList<>();
    public String getSupincentiveTable() {
        return supincentiveTable;
    }
    public void setSupincentiveTable(String supincentiveTable) {
        this.supincentiveTable = supincentiveTable;
    }
    public ArrayList<String> getCurMonth() {
        return curMonth;
    }

    public void setCurMonth(String curMonth) {
        this.curMonth.add(curMonth);
    }

    public ArrayList<String> getPM1() {
        return PM1;
    }

    public void setPM1(String PM1) {
        this.PM1.add(PM1);
    }

    public ArrayList<String> getPM2() {
        return PM2;
    }

    public void setPM2(String PM2) {
        this.PM2.add(PM2);
    }

    public ArrayList<String> getPM3() {
        return PM3;
    }

    public void setPM3(String PM3) {
        this.PM3.add(PM3);
    }
}
