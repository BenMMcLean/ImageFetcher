package com.arctro.imagefetcher.hosts;

/**
 * Gets the appropriate HostFetcher for the given host
 * @author Ben McLean
 */
public class HostFetcherFactory {
	/**
	 * Returns the appropriate HostFetcher for the given host
	 * @param host The host to get the HostFetcher for
	 * @return The appropriate HostFetcher for the given host
	 */
	public static HostFetcher getFetcher(String host){
		switch(host){
		case "imgur.com":
			return new ImgurHostFetcher();
		case "giphy.com":
			return new GiphyHostFetcher();
		case "gfycat.com":
			return new GfycatHostFetcher();
		case "twitter.com":
			return new TwitterHostFetcher();
		}
		return null;
	}
}
