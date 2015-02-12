package com.snail.tour.site.analysis;

import java.util.LinkedHashMap;
import java.util.Map;

public class PageContent {
	
	private String address ; 
	private String title ; 
	private String price ;
	private Map<String,String> contentMap = new LinkedHashMap<>() ;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, String> getContentMap() {
		return contentMap;
	}
	public void setContentMap(Map<String, String> contentMap) {
		this.contentMap = contentMap;
	} 
	public void add(String key,String content){
		contentMap.put(key, content);
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
