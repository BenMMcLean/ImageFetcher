package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for imgflip.com
 * @author Ben McLean
 */
public class ImgflipHostFetcher implements HostFetcher{
	
	@Override
	public String get(Document d, URL u) {
		return d.select("#im").attr("src");
	}

}
