package com.arctro.imagefetcher.hosts;

import java.util.HashMap;

/**
 * Gets the appropriate HostFetcher for the given host
 * @author Ben McLean
 */
public class HostFetcherFactory {
	
	//Stores user defined HostFetchers
	public static HashMap<String, HostFetcher> customHosts = new HashMap<String, HostFetcher>();
	
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
		
		if(customHosts.containsKey(host)){
			return customHosts.get(host);
		}
		
		return null;
	}
	
	/**
	 * Add a user defined HostFetcher
	 * @param host The host to associate the HostFetcher with
	 * @param hostFetcher The HostFetcher
	 */
	public static void addCustomHost(String host, HostFetcher hostFetcher){
		customHosts.put(host, hostFetcher);
	}
}
