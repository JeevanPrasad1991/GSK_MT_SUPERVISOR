package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 26-05-2017.
 */

public class PssStorewiseGetterSetter {
    String pssstorewiseTable;
    ArrayList<String>storeN=new ArrayList<>();
    ArrayList<String>merchanD=new ArrayList<>();
    ArrayList<String>currentM=new ArrayList<>();
    ArrayList<String>pm2per=new ArrayList<>();
    ArrayList<String>currMonthper=new ArrayList<>();
    public ArrayList<String> getCurrMonthper() {
        return currMonthper;
    }

    public void setCurrMonthper(String currMonthper) {
        this.currMonthper.add(currMonthper);
    }

    public ArrayList<String> getPm2per() {
        return pm2per;
    }

    public void setPm2per(String pm2per) {
        this.pm2per.add(pm2per);
    }

    public ArrayList<String> getPm1per() {
        return pm1per;
    }

    public void setPm1per(String pm1per) {
        this.pm1per.add(pm1per);
    }

    public ArrayList<String> getPm3per() {
        return pm3per;
    }

    public void setPm3per(String pm3per) {
        this.pm3per.add(pm3per);
    }

    ArrayList<String>pm1per=new ArrayList<>();
    ArrayList<String>pm3per=new ArrayList<>();

    public String getPssstorewiseTable() {
        return pssstorewiseTable;
    }

    public void setPssstorewiseTable(String pssstorewiseTable) {
        this.pssstorewiseTable = pssstorewiseTable;
    }

    public ArrayList<String> getStoreN() {
        return storeN;
    }

    public void setStoreN(String storeN) {
        this.storeN.add(storeN);
    }

    public ArrayList<String> getMerchanD() {
        return merchanD;
    }

    public void setMerchanD(String merchanD) {
        this.merchanD.add(merchanD);
    }

    public ArrayList<String> getCurrentM() {
        return currentM;
    }

    public void setCurrentM(String currentM) {
        this.currentM.add(currentM);
    }

    public ArrayList<String> getPm1() {
        return pm1;
    }

    public void setPm1(String pm1) {
        this.pm1.add(pm1);
    }

    public ArrayList<String> getPm2() {
        return pm2;
    }

    public void setPm2(String pm2) {
        this.pm2.add(pm2);
    }

    public ArrayList<String> getPm3() {
        return pm3;
    }

    public void setPm3(String pm3) {
        this.pm3.add(pm3);
    }

    ArrayList<String>pm1=new ArrayList<>();
    ArrayList<String>pm2=new ArrayList<>();
    ArrayList<String>pm3=new ArrayList<>();
}
