package com.snail.tour.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snail.tour.comparasion.ComparasionComponent;
import com.snail.tour.comparasion.ComparasionResult;
import com.snail.tour.site.analysis.PageContent;

@Controller
public class CompareController {
	
	@Autowired
	ComparasionComponent comparasionComponent;  
	
	@RequestMapping("/home")
	public String home(String [] urls){
		
		
		return "home";
	}
	
	@RequestMapping("/compare")
	public String compare(String [] urls, Model model){
		
		
		if(urls == null || urls.length == 0){
			return "redirect/home";
		}
		
		List<String> urlList = new ArrayList<>();
		for (String url:urls){
			if (!StringUtils.isEmpty(url)){
				urlList.add(url);
			}
		}
		
		
		List<PageContent> list = comparasionComponent.compareUrls(urlList);
		Set<String> keySet = comparasionComponent.getAllKeySet(list);
		List<ComparasionResult> resultList =  comparasionComponent.compareContent(list);
		
		if (list.size() > 0){
			int size = list.size();
			int width = 10/size ;
			model.addAttribute("width",width);
		}else {
			model.addAttribute("width",5);
		}
		
		
		model.addAttribute("list", list);
		model.addAttribute("keySet", keySet);
		model.addAttribute("resultList", resultList);
		
		
		return "list";
	}

}
