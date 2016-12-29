package com.arctro.imagefetcher.hosts;

import org.jsoup.nodes.Document;

/**
 * The interface that defines how known websites should be scraped
 * @author Ben McLean
 */
public interface HostFetcher {
	/**
	 * Returns the URL to the image associated with the document
	 * @param d The HTML document
	 * @return The URL to the image associated with the document
	 */
	public String get(Document d);
}
