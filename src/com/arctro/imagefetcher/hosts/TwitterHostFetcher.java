package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

public class TwitterHostFetcher implements HostFetcher{

	@Override
	public String get(Document d) {
		return d.select("[property=og:image]").attr("content");
	}

}
