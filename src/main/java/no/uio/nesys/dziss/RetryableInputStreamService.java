package no.uio.nesys.dziss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class RetryableInputStreamService {

	public static InputStream getRetryableInputStream(URL url) {
		return getRetryableInputStream(url, 5, 500);
	}

	public /* synchronized */ static InputStream getRetryableInputStream(URL url, int retry, int wait) {

		int counter = 0;
		while (counter < retry) {
			try {
				return url.openStream();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Thread.sleep(wait);
					++counter;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	public /* synchronized */ static ImageInputStream getRetryableImageInputStream(URL url) {
		return getRetryableImageInputStream(url, 5, 500);
	}

	public /* synchronized */ static ImageInputStream getRetryableImageInputStream(URL url, int retry, int wait) {

		int counter = 0;
		while (counter < retry) {
			try {
				return ImageIO.createImageInputStream(getRetryableInputStream(url));
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Thread.sleep(wait);
					++counter;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
}
