package com.snail.tour.site.analysis;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.context.ContextLoaderListener;


public class TuNiuPageAnalysis extends BasePageAnalysis implements PageAnalysis {
		
	public TuNiuPageAnalysis(AnalysisBean bean) {
		super(bean);
	}


	protected Pattern keyPattern = Pattern.compile("【(?<key>[\u4e00-\u9fa50-9a-zA-Z]+)】");
	protected Pattern titlePattern = Pattern.compile("<title>(?<title>.*)</title>");
	
	protected void setTitle(Document doc, PageContent pc){
		pc.setTitle(doc.title());
	}
	
	protected void setPrice(Document doc, PageContent pc){
		Elements elements = doc.getElementsByClass("cx_price");
		if (elements!=null){
			pc.setPrice(elements.text());
		}
		
	}
	
	protected void setContent(Document doc, PageContent pc){
		Elements elements = doc.getElementsByClass("tripday_des");
		Elements elements1 = doc.getElementsByClass("pp");

		String content = elements.text();
		content += elements1.text();
		Matcher m = keyPattern.matcher(content);
		
		while(m.find()){
			
			String key = m.group("key") ;
			Pattern contentPattern = Pattern.compile("【"+key+"】(?<content>(.|\\s)*?(【|。|$))",Pattern.DOTALL);
			Matcher contentMatcher = contentPattern.matcher(content);
			String value = "" ;
			if (contentMatcher.find()){
				value = contentMatcher.group("content");
				if (value.length() > 10 ){
					pc.add(key, value);
				}
			}
		}
	}
	
	

	@Override
	public PageContent getContents(String url) {
	
		PageContent pc = new PageContent();
		pc.setAddress(url);
		String html = pageUtils.getContent(url);
		Document doc = Jsoup.parse(html);
		
		
		
		this.setTitle(doc, pc);
		this.setPrice(doc, pc);
		this.setContent(doc, pc);
		
		
		return pc;
	}
	
	
	public static void main(String []args){
		
		System.out.println(123);
		//System.out.println("tuniu.com".matches("http://tj.tuniu.com/tours/631663#source=cc"));
	}

}
