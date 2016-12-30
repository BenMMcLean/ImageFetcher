package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for i.imgur.com
 * @author Ben McLean
 */
public class I_ImgurHostFetcher implements HostFetcher{

	@Override
	public String get(Document d, URL u) {
		String url = u.toString();
		if(url.endsWith(".gifv")){
			url = url.substring(0, url.length()-1);
		}
		return url;
	}
	
}
