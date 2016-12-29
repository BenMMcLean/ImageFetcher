package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

public class ImgurHostFetcher implements HostFetcher{

	@Override
	public String get(Document d) {
		String url = d.select("[property=og:image]").attr("content");
		return url.substring(0, url.length() - 3);
	}
	
}
