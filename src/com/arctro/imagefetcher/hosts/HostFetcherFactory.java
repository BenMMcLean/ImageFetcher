package com.arctro.imagefetcher.hosts;

public class HostFetcherFactory {
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
