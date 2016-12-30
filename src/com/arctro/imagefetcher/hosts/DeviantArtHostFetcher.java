package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for deviantart.com
 * @author Ben McLean
 */
public class DeviantArtHostFetcher implements HostFetcher{

	@Override
	public String get(Document d, URL u) {
		return d.select("[property=og:image]").attr("content");
	}

}
