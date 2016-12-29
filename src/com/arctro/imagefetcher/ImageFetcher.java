package com.arctro.imagefetcher;

import java.awt.image.BufferedImage;
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

public class ImageFetcher {
	public Detector detector = TikaConfig.getDefaultConfig().getDetector();
	
	public ImageFetcher(){
		
	}
	
	public FetchResult fetch(URL url) throws IOException{
		URLConnection conn = open(url);
		
		if(isImage(conn.getContentType())){
			return fetchImage(url);
		}
		
		Document doc = Jsoup.connect(url.toString()).get();
		
		HostFetcher fetcher = HostFetcherFactory.getFetcher(url.getHost());
		if(fetcher != null){
			return fetchImage(genURL(url, fetcher.get(doc)));
		}
		
		Elements imgs = doc.select("img");
		FetchResult b = null;
		int bArea = 0;
		for(Element img : imgs){
			FetchResult tmp = fetchImage(genURL(url, img.attr("src")));
			BufferedImage bi = tmp.getBufferedImage();
			int tmpArea = bi.getWidth() * bi.getHeight();
			
			if(b == null || bArea < tmpArea){
				b = tmp;
				bArea = tmpArea;
			}
		}
		
		return b;
	}
	
	public FetchResult fetchImage(URL url) throws IOException{
		InputStream is = new BufferedInputStream(open(url).getInputStream());
		
		MediaType t = detector.detect(is, new Metadata());
		return new FetchResult(IOUtils.toByteArray(is), t);
	}
	
	private boolean isImage(String ct){
		return (ct.contains("image"));
	}
	
	private URL genURL(URL main, String u) throws MalformedURLException{
		URL url = null;
		
		try{
			url = new URL(u);
		}catch(Exception e){
			try{
				url = new URL("http://" + u);
			}catch(Exception e1){
				try{
					url = new URL("http:" + u);
				}catch(Exception e2){
					url = new URL(main.getProtocol() + "://" + main.getHost() + "/" + u);
				}
			}
		}
		
		return url;
	}
	
	private URLConnection open(URL url) throws IOException{
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		return conn;
	}
}
