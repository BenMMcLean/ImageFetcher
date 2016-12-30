package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * Host fetcher for gfycat.com
 * @author Ben McLean
 */
public class GfycatHostFetcher implements HostFetcher{

	@Override
	public String get(Document d, URL u) {
		return d.select("#large-gif").attr("href");
	}

}
