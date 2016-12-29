package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for imgur.com
 * @author Ben McLean
 */
public class ImgurHostFetcher implements HostFetcher{

	@Override
	public String get(Document d) {
		String url = d.select("[property=og:image]").attr("content");
		return url.substring(0, url.length() - 3);
	}
	
}
