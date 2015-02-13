package com.snail.tour.site.analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.snail.tour.utils.PageUtils;

public class BasePageAnalysis implements PageAnalysis{
	@Autowired
	PageUtils pageUtils = new PageUtils();
	
	AnalysisBean bean;
	protected Pattern keyPattern ;
	protected Pattern contentPattern ;
	
	public BasePageAnalysis(AnalysisBean bean){
		this.bean = bean ;
		keyPattern = Pattern.compile(bean.getKeyStartStr()+"(?<key>[\u4e00-\u9fa50-9a-zA-Z]+)"+bean.getKeyEndStr());
		
	}

	
	
	protected void setTitle(Document doc, PageContent pc){
		pc.setTitle(doc.title());
	}
	
	protected void setPrice(Document doc, PageContent pc){
		Elements elements = doc.getElementsByClass(bean.getPriceCss());
		if (elements!=null){
			pc.setPrice(elements.text());
		}
		
	}
	
	protected void setContent(Document doc, PageContent pc){

		String content = getAllContentWithoutTag(doc);
		Matcher m = keyPattern.matcher(content);
		
		while(m.find()){
			
			String key = m.group("key") ;
			
			String value = this.getCommonContentByKey(key,content);
			if (value == null){
				value =getCommonContentByKey(key,content);
			}
			if (value == null){
				value = "exist" ;
			}
			
			pc.add(key, value);
		}
	}
	
	protected String getContentByKey(String key,String html){
		
		Pattern contentPattern = this.getContentPatternByKey(key);
		Matcher contentMatcher = contentPattern.matcher(html);
		while(contentMatcher.find()){
			String value = contentMatcher.group("content");
			if(value.length() > 10){
				return value ;
			}
		}
		
		return null;
	}
	
	protected String getCommonContentByKey(String key,String html){
		
		Pattern contentPattern = this.getCommonContentPatternByKey(key);
		Matcher contentMatcher = contentPattern.matcher(html);
		while(contentMatcher.find()){
			String value = contentMatcher.group("content");
			if(value.length() > 10){
				return value ;
			}
		}
		
		return null;
	}
	
	public String getAllContentWithoutTag(Document doc){
		
		String [] contentCss = bean.getKeyCss().split(",");
		StringBuffer sb = new StringBuffer();
		for (String css:contentCss){
			sb.append(doc.getElementsByClass(css).text());
		}
		
		return sb.toString();
		
	}
	
	public Pattern getContentPatternByKey(String key){
		return Pattern.compile(bean.getKeyStartStr()+key+bean.getKeyEndStr()+"(?<content>(.|\\s)*?"+bean.getContentEndStr()+")",Pattern.DOTALL);
	}
	
	public Pattern getCommonContentPatternByKey(String key){
		return Pattern.compile(key+"(?<content>(.|\\s)*?"+bean.getContentEndStr()+")",Pattern.DOTALL);
	}
	
	

	@Override
	public PageContent getContents(String url) {
		
		if (bean == null || StringUtils.isEmpty(url)){
			return null;
		}
	
		PageContent pc = new PageContent();
		pc.setAddress(url);
		String html = pageUtils.getContent(url);
		Document doc = Jsoup.parse(html);
		
		
		
		this.setTitle(doc, pc);
		this.setPrice(doc, pc);
		this.setContent(doc, pc);
		
		
		return pc;
	}
}
