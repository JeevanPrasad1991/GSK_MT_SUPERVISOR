package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

public class NonWorkingGetterSetter {
	
	ArrayList<String> REASON_ID = new ArrayList<String>();
	ArrayList<String> REASON = new ArrayList<String>();
	ArrayList<String> IMAGE_ALLOW = new ArrayList<String>();
	ArrayList<String> ENTRY_ALLOW = new ArrayList<String>();
	
	
	public String meta_data;
	
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	public ArrayList<String> getREASON_ID() {
		return REASON_ID;
	}
	public void setREASON_ID(String rEASON_ID) {
		this.REASON_ID.add(rEASON_ID);
		
	}
	public ArrayList<String> getREASON() {
		return REASON;
	}
	public void setREASON(String rEASON) {
		this.REASON.add(rEASON);
	}
	public ArrayList<String> getIMAGE_ALLOW() {
		return IMAGE_ALLOW;
	}
	public void setIMAGE_ALLOW(String iMAGE_ALLOW) {
		this.IMAGE_ALLOW.add(iMAGE_ALLOW);
	}
	public ArrayList<String> getENTRY_ALLOW() {
		return ENTRY_ALLOW;
	}
	public void setENTRY_ALLOW(String eNTRY_ALLOW) {
		this.ENTRY_ALLOW.add(eNTRY_ALLOW);
	}
	
	

}
