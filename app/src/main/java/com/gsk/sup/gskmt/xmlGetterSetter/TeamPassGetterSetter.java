package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 25-05-2017.
 */

public class TeamPassGetterSetter {
    String teampassTable;
    ArrayList<String>perfect=new ArrayList<>();
    ArrayList<String>near20perF=new ArrayList<>();
    ArrayList<String>not20perF=new ArrayList<>();
    ArrayList<String>total20store=new ArrayList<>();
    ArrayList<String>merchanD=new ArrayList<>();

    public String getTeampassTable() {
        return teampassTable;
    }

    public void setTeampassTable(String teampassTable) {
        this.teampassTable = teampassTable;
    }

    public ArrayList<String> getMerchanD() {
        return merchanD;
    }

    public void setMerchanD(String merchanD) {
        this.merchanD.add(merchanD);
    }

    public ArrayList<String> getPerfect() {
        return perfect;
    }

    public void setPerfect(String perfect) {
        this.perfect.add(perfect);
    }

    public ArrayList<String> getNear20perF() {
        return near20perF;
    }

    public void setNear20perF(String near20perF) {
        this.near20perF.add(near20perF);
    }

    public ArrayList<String> getNot20perF() {
        return not20perF;
    }

    public void setNot20perF(String not20perF) {
        this.not20perF.add(not20perF);
    }

    public ArrayList<String> getTotal20store() {
        return total20store;
    }

    public void setTotal20store(String total20store) {
        this.total20store.add(total20store);
    }


}
