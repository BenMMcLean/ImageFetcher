package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

public interface HostFetcher {
	public String get(Document d);
}
