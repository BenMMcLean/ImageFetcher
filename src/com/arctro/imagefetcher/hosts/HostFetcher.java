package com.arctro.imagefetcher.hosts;

import java.net.URL;

import org.jsoup.nodes.Document;

/**
 * The interface that defines how known websites should be scraped
 * @author Ben McLean
 */
public interface HostFetcher {
	/**
	 * Returns the URL to the image associated with the document
	 * @param d The HTML document
	 * @param u The URL to the document
	 * @return The URL to the image associated with the document
	 */
	public String get(Document d, URL u);
}
