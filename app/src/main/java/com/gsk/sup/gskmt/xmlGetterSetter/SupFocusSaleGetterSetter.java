package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 25-05-2017.
 */

public class SupFocusSaleGetterSetter {
    String supfocussaleTable;
    ArrayList<String>employee=new ArrayList<>();
    ArrayList<String>currMonth=new ArrayList<>();
    ArrayList<String>pm2=new ArrayList<>();
    ArrayList<String>pm1=new ArrayList<>();

    public ArrayList<String> getPm1() {
        return pm1;
    }

    public void setPm1(String pm1) {
        this.pm1.add(pm1);
    }

    public ArrayList<String> getPm3() {
        return pm3;
    }

    public void setPm3(String pm3) {
        this.pm3.add(pm3);
    }

    ArrayList<String>pm3=new ArrayList<>();

    public String getSupfocussaleTable() {
        return supfocussaleTable;
    }

    public void setSupfocussaleTable(String supfocussaleTable) {
        this.supfocussaleTable = supfocussaleTable;
    }

    public ArrayList<String> getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee.add(employee);
    }

    public ArrayList<String> getCurrMonth() {
        return currMonth;
    }

    public void setCurrMonth(String currMonth) {
        this.currMonth.add(currMonth);
    }

    public ArrayList<String> getPm2() {
        return pm2;
    }

    public void setPm2(String pm2) {
        this.pm2.add(pm2);
    }
}
