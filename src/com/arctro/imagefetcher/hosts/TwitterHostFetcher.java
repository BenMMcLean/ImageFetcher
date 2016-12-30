package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for twitter.com
 * @author Ben McLean
 */
public class TwitterHostFetcher implements HostFetcher{

	@Override
	public String get(Document d, URL u) {
		return d.select("[property=og:image]").attr("content");
	}

}
