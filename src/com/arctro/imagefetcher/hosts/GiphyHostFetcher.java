package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for giphy.com
 * @author Ben McLean
 */
public class GiphyHostFetcher implements HostFetcher{

	@Override
	public String get(Document d, URL u) {
		return d.select("[property=og:url]").attr("content");
	}
	
}