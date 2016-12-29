package com.arctro.imagefetcher.hosts;

public class HostFetcherFactory {
	public static HostFetcher getFetcher(String host){
		switch(host){
		case "imgur.com":
			return new ImgurHostFetcher();
		case "giphy.com":
			return new GiphyHostFetcher();
		}
		return null;
	}
}
