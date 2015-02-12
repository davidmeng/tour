package com.snail.tour.comparasion;

import java.util.ArrayList;
import java.util.List;

public class ComparasionResult {
	
	String key ;
	List<String> contents = new ArrayList<>();
	boolean common = false ;  
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
	} 
	public void addContent(String content){
		contents.add(content);
	}
	public boolean isCommon() {
		return common;
	}
	public void setCommon(boolean common) {
		this.common = common;
	}

}
