package com.arctro.imagefetcher;

import java.awt.image.BufferedImage;

import org.apache.tika.mime.MediaType;

public class FetchResult {
	BufferedImage image;
	MediaType type;
	
	public FetchResult(BufferedImage image, MediaType type) {
		super();
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FetchResult [image=" + image + ", type=" + type + "]";
	}
}
