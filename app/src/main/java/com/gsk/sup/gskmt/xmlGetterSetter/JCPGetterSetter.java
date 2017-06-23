package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

public class JCPGetterSetter {
	
	ArrayList<String>  STORE_ID  = new ArrayList<String>();
	ArrayList<String>  STORE  = new ArrayList<String>();
	ArrayList<String>  EMP_ID = new ArrayList<String>();
	ArrayList<String> 	CITY  = new ArrayList<String>();
	ArrayList<String>  VISIT_DATE = new ArrayList<String>();
	ArrayList<String>  PROCESS_ID = new ArrayList<String>();
	ArrayList<String>  UPLOAD_STATUS = new ArrayList<String>();
	ArrayList<String>  REGION_ID  = new ArrayList<String>();
	ArrayList<String>  KEY_ACCOUNT  = new ArrayList<String>();
	ArrayList<String>  STORE_TYPE = new ArrayList<String>();
	ArrayList<String>  CHECKOUT_STATUS = new ArrayList<String>();
	ArrayList<String>  KEY_ID = new ArrayList<String>();
	ArrayList<String>  PROCESS = new ArrayList<String>();

	public ArrayList<String> getPROCESS() {
		return PROCESS;
	}
	public void setPROCESS(String pROCESS) {
		this.PROCESS.add(pROCESS);
	}
	public ArrayList<String> getKEY_ID() {
		return KEY_ID;
	}
	public void setKEY_ID(String kEY_ID) {
		this.KEY_ID.add(kEY_ID);
	}
	public String meta_data;
	
	public ArrayList<String> getSTORETYPE_ID() {
		return STORETYPE_ID;
	}
	public void setSTORETYPE_ID(String sTORETYPE_ID) {
		this.STORETYPE_ID.add(sTORETYPE_ID);
	}
	ArrayList<String>  STORETYPE_ID  = new ArrayList<String>();
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	public ArrayList<String> getSTORE_ID() {
		return STORE_ID;
	}
	public void setSTORE_ID(String sTORE_ID) {
		this.STORE_ID.add(sTORE_ID);
	}
	public ArrayList<String> getSTORE() {
		return STORE;
	}
	public void setSTORE(String sTORE) {
		this.STORE.add(sTORE);
	}
	public ArrayList<String> getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(String eMP_ID) {
		this.EMP_ID.add(eMP_ID);
	}
	public ArrayList<String> getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		this.CITY.add(cITY);
	}
	public ArrayList<String> getVISIT_DATE() {
		return VISIT_DATE;
	}
	public void setVISIT_DATE(String vISIT_DATE) {
		this.VISIT_DATE.add(vISIT_DATE);
	}
	public ArrayList<String> getPROCESS_ID() {
		return PROCESS_ID;
	}
	public void setPROCESS_ID(String pROCESS_ID) {
		this.PROCESS_ID.add(pROCESS_ID);
	}
	public ArrayList<String> getUPLOAD_STATUS() {
		return UPLOAD_STATUS;
	}
	public void setUPLOAD_STATUS(String uPLOAD_STATUS) {
		this.UPLOAD_STATUS.add(uPLOAD_STATUS);
	}
	public ArrayList<String> getREGION_ID() {
		return REGION_ID;
	}
	public void setREGION_ID(String rEGION_ID) {
		this.REGION_ID.add(rEGION_ID);
	}
	public ArrayList<String> getKEY_ACCOUNT() {
		return KEY_ACCOUNT;
	}
	public void setKEY_ACCOUNT(String kEY_ACCOUNT) {
		this.KEY_ACCOUNT.add(kEY_ACCOUNT);
	}
	public ArrayList<String> getSTORE_TYPE() {
		return STORE_TYPE;
	}
	public void setSTORE_TYPE(String sTORE_TYPE) {
		this.STORE_TYPE.add(sTORE_TYPE);
	}
	public ArrayList<String> getCHECKOUT_STATUS() {
		return CHECKOUT_STATUS;
	}
	public void setCHECKOUT_STATUS(String cHECKOUT_STATUS) {
		this.CHECKOUT_STATUS.add(cHECKOUT_STATUS);
	}
	
	

}
