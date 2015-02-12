package com.snail.tour.site.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snail.tour.utils.PageUtils;

@Component
public abstract class BasePageAnalysis implements PageAnalysis{
	@Autowired
	PageUtils pageUtils = new PageUtils();
	
	public String getTitle(String html){
		
		Matcher m = getTitlePattern().matcher(html);
		if (m.find()){
			String title = m.group("title");
			return title; 
		}
		return "活动" ; 
	}
	
	protected abstract Pattern getTitlePattern() ;
}
