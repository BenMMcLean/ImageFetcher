package com.arctro.imagefetcher;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageFetcher {
	
	public IFS[] search = {new IFS("[name=og:image]","href"), new IFS("[property=og:image]","content"), new IFS("[name=twitter:image]","content")};
	
	public ImageFetcher(){}
	
	public ImageFetcher(IFS[] search){
		this.search = search;
	}
	
	public BufferedImage fetch(URL url) throws IOException{
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Arctro Scraper");
		
		if(isImage(conn.getContentType())){
			return fetchImage(url);
		}
		
		Document doc = Jsoup.connect(url.toString()).get();
		
		for(IFS s : search){
			BufferedImage b = getFromAttr(url, doc, s);
			if(b != null){
				return b;
			}
		}
		
		Elements imgs = doc.select("img");
		BufferedImage b = null;
		int bArea = 0;
		for(Element img : imgs){
			BufferedImage tmp = fetchImage(genURL(url, img.attr("src")));
			int tmpArea = tmp.getWidth() * tmp.getHeight();
			
			if(b == null || bArea < tmpArea){
				b = tmp;
				bArea = tmpArea;
			}
		}
		
		return b;
	}
	
	public BufferedImage fetchImage(URL url) throws IOException{
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Arctro Scraper");
		return ImageIO.read(conn.getInputStream());
	}
	
	private boolean isImage(String ct){
		return (ct.contains("image"));
	}
	
	private BufferedImage getFromAttr(URL main, Document doc, IFS f) throws IOException{
		Elements link = doc.select(f.name);
		if(link.size() > 0){
			return fetchImage(genURL(main, link.attr(f.value)));
		}
		
		return null;
	}
	
	private URL genURL(URL main, String u) throws MalformedURLException{
		URL url = null;
		
		try{
			url = new URL(u);
		}catch(Exception e){
			url = new URL(main.getProtocol() + "://" + main.getHost() + "/" + u);
		}
		
		return url;
	}
	
	public static class IFS {
		String name;
		String value;
		
		public IFS(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
