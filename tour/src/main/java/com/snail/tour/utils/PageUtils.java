package com.snail.tour.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PageUtils {

	private static final Logger logger = LoggerFactory.getLogger(PageUtils.class);

	public Map<String, String> request(String url) {

		return null;
	}
	
	public String getContent(String url){
		
		String strProxy="10.237.86.137";
		String strPort="8080";
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost",strProxy);
		systemProperties.setProperty("http.proxyPort",strPort);
		
		HttpHost proxy = new HttpHost(strProxy, 8080, "http");
		
		HttpGet request = new HttpGet(url);
		request.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		HttpResponse response;
		try {
			response = HttpClients.createDefault().execute(request);

			if(response.getStatusLine().getStatusCode()==200){
			
				return EntityUtils.toString(response.getEntity(),"utf8");
			} 
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "" ;
	}

	public static void main(String []args){
		
		String strProxy="10.237.86.137";
		String strPort="8080";
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost",strProxy);
		systemProperties.setProperty("http.proxyPort",strPort);
		
		HttpHost proxy = new HttpHost(strProxy, 8080, "http");
		
		HttpGet request = new HttpGet("http://tj.tuniu.com/tours/486231#source=cc");
		request.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		HttpResponse response;
		try {
			response = HttpClients.createDefault().execute(request);

			if(response.getStatusLine().getStatusCode()==200){
			
				String r = EntityUtils.toString(response.getEntity(),"utf8");
				System.out.println(r);
			} 
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
