package com.snail.tour.site;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.snail.tour.site.analysis.AnalysisBean;
import com.snail.tour.site.analysis.BasePageAnalysis;
import com.snail.tour.site.analysis.PageAnalysis;

@Component
public class PageFactoryImpl implements PageFactory{

	@Override
	public PageAnalysis getAnalisis(String url) {
		return new BasePageAnalysis(getAnalysisBeanByUrl(url));
	}

	protected AnalysisBean getAnalysisBeanByUrl(String url){
		Map<String,AnalysisBean> analyisMap = ContextLoaderListener.getCurrentWebApplicationContext().getBeansOfType(AnalysisBean.class);
		for (String key:analyisMap.keySet()){
			AnalysisBean bean = analyisMap.get(key);
			if (url.indexOf(bean.getUrlPatternStr())>-1){
				return bean;
			}
		}
		
		return null;
	}
}
