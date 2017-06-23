package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

public class QuestionnairGetterSetter {
	
	ArrayList<String>  STORE_ID  = new ArrayList<String>();
	ArrayList<String> PROCESS_ID = new ArrayList<String>();
	ArrayList<String> QSUB_CATEGORY_ID = new ArrayList<String>();
	ArrayList<String> QUESTION_SUB_CATEGORY = new ArrayList<String>();
	ArrayList<String> QUESTION_ID = new ArrayList<String>();
	ArrayList<String> QUESTION = new ArrayList<String>();
	ArrayList<String> ANSWER_ID = new ArrayList<String>();
	ArrayList<String> ANSWER = new ArrayList<String>();
	
	public String meta_data;
	
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
	public ArrayList<String> getPROCESS_ID() {
		return PROCESS_ID;
	}
	public void setPROCESS_ID(String pROCESS_ID) {
		this.PROCESS_ID.add(pROCESS_ID);
	}
	public ArrayList<String> getQSUB_CATEGORY_ID() {
		return QSUB_CATEGORY_ID;
	}
	public void setQSUB_CATEGORY_ID(String qSUB_CATEGORY_ID) {
		this.QSUB_CATEGORY_ID.add(qSUB_CATEGORY_ID);
	}
	public ArrayList<String> getQUESTION_SUB_CATEGORY() {
		return QUESTION_SUB_CATEGORY;
	}
	public void setQUESTION_SUB_CATEGORY(String qUESTION_SUB_CATEGORY) {
		this.QUESTION_SUB_CATEGORY.add(qUESTION_SUB_CATEGORY);
	}
	public ArrayList<String> getQUESTION_ID() {
		return QUESTION_ID;
	}
	public void setQUESTION_ID(String qUESTION_ID) {
		this.QUESTION_ID.add(qUESTION_ID);
	}
	public ArrayList<String> getQUESTION() {
		return QUESTION;
	}
	public void setQUESTION(String qUESTION) {
		this.QUESTION.add(qUESTION);
	}
	public ArrayList<String> getANSWER_ID() {
		return ANSWER_ID;
	}
	public void setANSWER_ID(String aNSWER_ID) {
		this.ANSWER_ID.add(aNSWER_ID);
	}
	public ArrayList<String> getANSWER() {
		return ANSWER;
	}
	public void setANSWER(String aNSWER) {
		this.ANSWER.add(aNSWER);
	}
	

    
}
