package com.snail.tour.site;

import org.springframework.stereotype.Component;

import com.snail.tour.site.analysis.PageAnalysis;
import com.snail.tour.site.analysis.TuNiuPageAnalysis;

@Component
public class PageFactoryImpl implements PageFactory{

	@Override
	public PageAnalysis getAnalisis(String url) {
		return new TuNiuPageAnalysis();
	}

}
