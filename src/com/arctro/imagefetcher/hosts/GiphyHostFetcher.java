package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

public class GiphyHostFetcher implements HostFetcher{

	@Override
	public String get(Document d) {
		return d.select("[property=og:url]").attr("content");
	}
	
}