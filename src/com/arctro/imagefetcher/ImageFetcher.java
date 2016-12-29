package com.arctro.imagefetcher;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arctro.imagefetcher.hosts.HostFetcher;
import com.arctro.imagefetcher.hosts.HostFetcherFactory;

/**
 * Finds and fetches the main image from any website given to it
 * @author Ben McLean
 */
public class ImageFetcher {
	//The default filetype detector
	public Detector detector = TikaConfig.getDefaultConfig().getDetector();
	
	public ImageFetcher(){
		
	}
	
	/**
	 * Fetch the image at a URL
	 * @param url The url to scrape
	 * @return The result from the scrape
	 * @throws IOException Invalid URL, No connection
	 */
	public FetchResult fetch(URL url) throws IOException{
		URLConnection conn = open(url);
		
		//Check if the page is an image, if so directly fetch the image
		if(isImage(conn.getContentType())){
			return fetchImage(url);
		}
		
		//Parse the HTML doc
		Document doc = Jsoup.connect(url.toString()).get();
		
		//Check if it is a known host
		HostFetcher fetcher = HostFetcherFactory.getFetcher(url.getHost());
		if(fetcher != null){
			//If it is a known host use the HostFetcher returned by the HostFetcherFactory to fetch the image
			return fetchImage(genURL(url, fetcher.get(doc)));
		}
		
		//Fallback: Generic image fetcher
		//Finds the largest image on the page and downloads it
		
		//Selects all img elements
		Elements imgs = doc.select("img");
		String burl = null;
		int contentLen = 0;
		
		//Loop through all image elements
		for(Element img : imgs){
			//Download the image
			URLConnection dl = open(genURL(url, img.attr("src")));
			
			if(isImage(dl.getContentType()) && dl.getContentLength() > contentLen){
				contentLen = dl.getContentLength();
				burl = img.attr("src");
			}
		}
		
		if(burl == null){
			return null;
		}
		
		return fetchImage(genURL(url, burl));
	}
	
	/**
	 * Directly downloads an image
	 * @param url The URL of the image to download
	 * @return The fetch result
	 * @throws IOException Invalid URL, no connection
	 */
	public FetchResult fetchImage(URL url) throws IOException{
		//Wrap the input stream in a BufferedInputStream for the Detector
		InputStream is = new BufferedInputStream(open(url).getInputStream());
		
		//Get the type of image
		MediaType t = detector.detect(is, new Metadata());
		return new FetchResult(IOUtils.toByteArray(is), t);
	}
	
	/**
	 * Returns if the response is an image
	 * @param ct The Content-Type header
	 * @return If is image
	 */
	private boolean isImage(String ct){
		return (ct.contains("image"));
	}
	
	/**
	 * Generates a valid URL
	 * @param main The parent page's URL
	 * @param u The URL
	 * @return A valid URL
	 * @throws MalformedURLException No valid URL was found
	 */
	private URL genURL(URL main, String u) throws MalformedURLException{
		URL url = null;
		
		if(u.equals("") || u.equals("?") || u.equals("#")){
			return main;
		}
		if(u.equals("/")){
			return new URL("http://" + main.getHost());
		}
		
		try{
			//Check if already a valid URL
			url = new URL(u);
		}catch(Exception e){
			String[] uComp = u.split("/|\\?|#");
			
			//Check if contains a domain
			if(u.startsWith("//")){
				return new URL("http:" + u);
			}
			if(uComp[0].contains(".")){
				return new URL("http://" + u);
			}
			
			//Check if from base
			if(u.startsWith("/")){
				return new URL("http://" + main.getHost() + u);
			}
			
			//Add to end of current URL
			if(main.toString().endsWith("/")){
				return new URL(main.toString() + u);
			}else{
				return new URL(main.toString() + "/" + u);
			}
		}
		
		return url;
	}
	
	/**
	 * Opens a connection to a web page
	 * @param url The URL to connect to
	 * @return A connection to a webpage
	 * @throws IOException Invalid URL, no connection
	 */
	private URLConnection open(URL url) throws IOException{
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		return conn;
	}
}
