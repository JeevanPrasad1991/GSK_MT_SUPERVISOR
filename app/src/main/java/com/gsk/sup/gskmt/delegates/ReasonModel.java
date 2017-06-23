package com.gsk.sup.gskmt.delegates;

public class ReasonModel {
	
	
	private String reasonid;
	
	private String ReasonValid;
	private String IMAGE_ALLOW;
	public String getIMAGE_ALLOW() {
		return IMAGE_ALLOW;
	}
	public void setIMAGE_ALLOW(String iMAGE_ALLOW) {
		IMAGE_ALLOW = iMAGE_ALLOW;
	}
	public String getENTRY_ALLOW() {
		return ENTRY_ALLOW;
	}
	public void setENTRY_ALLOW(String eNTRY_ALLOW) {
		ENTRY_ALLOW = eNTRY_ALLOW;
	}
	private String ENTRY_ALLOW;
	
	private String sub_reasonId;
	public String getSub_reasonId() {
		return sub_reasonId;
	}
	public void setSub_reasonId(String sub_reasonId) {
		this.sub_reasonId = sub_reasonId;
	}
	public String getSub_reason() {
		return Sub_reason;
	}
	public void setSub_reason(String sub_reason) {
		Sub_reason = sub_reason;
	}
	private String Sub_reason;
	
	
	public String getReasonValid() {
		return ReasonValid;
	}
	public void setReasonValid(String reasonValid) {
		this.ReasonValid = reasonValid;
	}
	private String reason;
	
	private String image;
	private String entry;
	public String getReasonid() {
		return reasonid;
	}
	public void setReasonid(String reasonid) {
		this.reasonid = reasonid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	

}