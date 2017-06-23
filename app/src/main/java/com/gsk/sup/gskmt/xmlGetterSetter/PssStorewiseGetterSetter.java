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
