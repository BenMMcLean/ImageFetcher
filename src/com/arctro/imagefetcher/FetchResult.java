package com.arctro.imagefetcher;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tika.mime.MediaType;

/**
 * Holds the result of an ImageFetch
 * @author Ben McLean
 */
public class FetchResult {
	//The raw data of an image
	byte[] image;
	//The type of image
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
	
	/**
	 * Generates and returns a BufferedImage
	 * @return Returns a BufferedImage
	 * @throws IOException
	 */
	public BufferedImage getBufferedImage(){
		ByteArrayInputStream bais = new ByteArrayInputStream(image);
		try{
			return ImageIO.read(bais);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
