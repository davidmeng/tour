package com.snail.tour.comparasion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import com.snail.tour.site.PageFactory;
import com.snail.tour.site.analysis.AnalysisBean;
import com.snail.tour.site.analysis.PageContent;

@Component
public class ComparasionComponent {
	
	@Autowired
	PageFactory pageFactory ;
	
	
	
	public List<PageContent> compareUrls(List<String>urls){
		
		List<PageContent> list = new ArrayList<>();
		
		for (String url:urls){
			
			PageContent pc = pageFactory.getAnalisis(url).getContents(url);
			if(pc != null){
				
				list.add(pc);
			}
		}
		
		return list; 
	}
	
	
	
	public Set<String> getAllKeySet(List<PageContent> list){
		Set<String> keySet = new TreeSet<>();
		
		for (PageContent pc : list){
			keySet.addAll(pc.getContentMap().keySet());
		}
		
		return keySet;
	}
	
	
	public List<ComparasionResult> compareContent(List<PageContent> contentList){
		
		List<ComparasionResult> list = new ArrayList<>();
		Set<String> keySet = new TreeSet<>();
		
		for (PageContent pc : contentList){
			keySet.addAll(pc.getContentMap().keySet());
		}
		
		for (String key : keySet){
			ComparasionResult r = new ComparasionResult();
			r.setKey(key);
			list.add(r);
			boolean common = true ;
			for (PageContent pc : contentList){
				if (pc.getContentMap().containsKey(key)){
					r.addContent(pc.getContentMap().get(key));
				}else {
					r.addContent("");
					common = false ;
				}
			}
			r.setCommon(common);
		}
		
		return list;
	}
	
	public static void main(String []args){
		System.out.println("tuniu.com".matches("http://tj.tuniu.com/tours/631663#source=cc"));
	}

}
