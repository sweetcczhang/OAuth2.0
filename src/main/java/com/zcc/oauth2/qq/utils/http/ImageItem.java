package com.zcc.oauth2.qq.utils.http;

import com.zcc.oauth2.qq.QQConnectException;
import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

public class ImageItem {
	private byte[] content;
	private String name;
	private String contentType;

	public ImageItem(byte[] content) throws QQConnectException {
		this("pic", content);
	}

	public ImageItem(String name, byte[] content) throws QQConnectException {
		String imgtype = null;
		try {
			imgtype = getContentType(content);
		} catch (IOException e) {
			throw new QQConnectException(e);
		}

		if ((imgtype != null)
				&& ((imgtype.equalsIgnoreCase("image/gif")) || (imgtype.equalsIgnoreCase("image/png")) || (imgtype.equalsIgnoreCase("image/jpeg")))) {
			this.content = content;
			this.name = name;
			this.contentType = imgtype;
		} else {
			throw new QQConnectException("Unsupported image type, Only Suport JPG ,GIF,PNG!");
		}
	}

	public byte[] getContent() {
		return this.content;
	}

	public String getName() {
		return this.name;
	}

	public String getContentType() {
		return this.contentType;
	}

	@SuppressWarnings("unchecked")
	public static String getContentType(byte[] mapObj) throws IOException {
		String type = "";
		ByteArrayInputStream bais = null;
		MemoryCacheImageInputStream mcis = null;
		try {
			bais = new ByteArrayInputStream(mapObj);
			mcis = new MemoryCacheImageInputStream(bais);
			Iterator itr = ImageIO.getImageReaders(mcis);
			while (itr.hasNext()) {
				ImageReader reader = (ImageReader) itr.next();
				if ((reader instanceof GIFImageReader))
					type = "image/gif";
				else if ((reader instanceof JPEGImageReader))
					type = "image/jpeg";
				else if ((reader instanceof PNGImageReader))
					type = "image/png";
				else if ((reader instanceof BMPImageReader))
					type = "application/x-bmp";
			}
		} finally {
			if (bais != null)
				try {
					bais.close();
				} catch (IOException ioe) {
				}
			if (mcis != null)
				try {
					mcis.close();
				} catch (IOException ioe) {
				}
		}
		return type;
	}
}