package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 26-05-2017.
 */

public class PssStorewiseDetailGetterSetter {
    String pssstorewisedetailTable;
    ArrayList<String>store_id=new ArrayList<>();
    ArrayList<String>category_id=new ArrayList<>();
    ArrayList<String>category=new ArrayList<>();
    ArrayList<String>sos=new ArrayList<>();
    ArrayList<String>tot=new ArrayList<>();

    public String getPssstorewisedetailTable() {
        return pssstorewisedetailTable;
    }

    public void setPssstorewisedetailTable(String pssstorewisedetailTable) {
        this.pssstorewisedetailTable = pssstorewisedetailTable;
    }

    public ArrayList<String> getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id.add(store_id);
    }

    public ArrayList<String> getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id.add(category_id);
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category.add(category);
    }

    public ArrayList<String> getSos() {
        return sos;
    }

    public void setSos(String sos) {
        this.sos.add(sos);
    }

    public ArrayList<String> getTot() {
        return tot;
    }

    public void setTot(String tot) {
        this.tot.add(tot);
    }

    public ArrayList<String> getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid.add(paid);
    }

    public ArrayList<String> getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition.add(addition);
    }

    public ArrayList<String> getPss_store() {
        return pss_store;
    }

    public void setPss_store(String pss_store) {
        this.pss_store.add(pss_store);
    }

    ArrayList<String>paid=new ArrayList<>();
    ArrayList<String>addition=new ArrayList<>();
    ArrayList<String>pss_store=new ArrayList<>();
}
