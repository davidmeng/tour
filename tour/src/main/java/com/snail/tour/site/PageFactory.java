package com.snail.tour.site;

import com.snail.tour.site.analysis.PageAnalysis;

public interface PageFactory {
	
	public PageAnalysis getAnalisis(String url);

}
