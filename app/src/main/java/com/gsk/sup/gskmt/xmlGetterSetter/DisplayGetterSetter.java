package com.gsk.sup.gskmt.xmlGetterSetter;

import java.util.ArrayList;

public class DisplayGetterSetter {
	
	ArrayList<String> display_id = new ArrayList<String>();
	ArrayList<String> display = new ArrayList<String>();
	ArrayList<String> Image_url = new ArrayList<String>();
	
	public ArrayList<String> getImage_url() {
		return Image_url;
	}
	public void setImage_url(String image_url) {
		this.Image_url.add(image_url);
	}
	String meta_data;
	
	public String getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
	public ArrayList<String> getDisplay_id() {
		return display_id;
	}
	public void setDisplay_id(String display_id) {
		this.display_id.add(display_id);
	}
	public ArrayList<String> getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display.add(display);
	}


}
