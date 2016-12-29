package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for giphy.com
 * @author Ben McLean
 */
public class GiphyHostFetcher implements HostFetcher{

	@Override
	public String get(Document d) {
		return d.select("[property=og:url]").attr("content");
	}
	
}