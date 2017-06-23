package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

/**
 * Created by jeevanp on 30-05-2017.
 */

public class RemarkGetterSetter {
    String remark_table;
    ArrayList<String>remark_cd=new ArrayList<>();
    ArrayList<String>remark=new ArrayList<>();

    public String getRemark_table() {
        return remark_table;
    }

    public void setRemark_table(String remark_table) {
        this.remark_table = remark_table;
    }

    public ArrayList<String> getRemark_cd() {
        return remark_cd;
    }

    public void setRemark_cd(String remark_cd) {
        this.remark_cd.add(remark_cd);
    }

    public ArrayList<String> getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.add(remark);
    }
}
