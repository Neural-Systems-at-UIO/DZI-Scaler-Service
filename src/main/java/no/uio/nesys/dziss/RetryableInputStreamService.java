package no.uio.nesys.dziss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryableInputStreamService {

	@Retryable(value = IOException.class)
	public InputStream getRetryableInputStream(URL url) throws IOException {
		return url.openStream();
	}

	@Retryable(value = IOException.class)
	public ImageInputStream getRetryableImageInputStream(URL url) throws IOException {

		return ImageIO.createImageInputStream(getRetryableInputStream(url));
	}

}
