package com.arctro.imagefetcher;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tika.mime.MediaType;

public class FetchResult {
	byte[] image;
	MediaType type;
	
	public FetchResult(byte[] image, MediaType type) {
		super();
		this.image = image;
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public BufferedImage getBufferedImage() throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(image);
		return ImageIO.read(bais);
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
