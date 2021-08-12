package no.uio.nesys.dziss;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.uio.nesys.pyramidio.DeepZoomImageReaderUrl;

@RestController
public class ScalerController {

	private static final Logger logger = Logger.getLogger(ScalerController.class.getName());

	@GetMapping("/")
	public String index() {
		return "DZI Scaler Service v.1";
	}

	private ResponseEntity<InputStreamResource> getResponseEntity(BufferedImage bi, HttpHeaders headers, String format)
			throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);

		byte[] imageInByte = baos.toByteArray();

		InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(imageInByte));

		MediaType mt;
		switch (format.toLowerCase()) {
		case "png":
			mt = new MediaType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE));
			break;
		case "jpg":
			mt = new MediaType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE));
			break;
		case "jpeg":
			mt = new MediaType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE));
			break;
		default:
			return ResponseEntity.badRequest().body(null);
		}

		return ResponseEntity.ok().headers(headers).contentLength(imageInByte.length).contentType(mt).body(resource);
	}

	@RequestMapping("/scale")
	public ResponseEntity<InputStreamResource> scaler(@RequestParam String dzi, @RequestParam double scale,
			@RequestParam(defaultValue = "png") String format) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline;filename=" + "scaled_" + scale + "." + format);

		try {

			DeepZoomImageReaderUrl reader = new DeepZoomImageReaderUrl(new URL(dzi));
			BufferedImage scaled = reader.getWholeImage(scale);

			return getResponseEntity(scaled, headers, format);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);

		}

	}

	@RequestMapping("/region")
	public ResponseEntity<InputStreamResource> scaler(@RequestParam String dzi, @RequestParam int x,
			@RequestParam int y, @RequestParam int width, @RequestParam int height,
			@RequestParam(defaultValue = "1") double scale, @RequestParam(defaultValue = "png") String format) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition",
				"inline;filename=" + x + "_" + y + "_" + width + "_" + height + "_" + scale + "." + format);

		try {
			DeepZoomImageReaderUrl reader = new DeepZoomImageReaderUrl(new URL(dzi));
			BufferedImage region = reader.getSubImage(new Rectangle(x, y, width, height), scale);

			return getResponseEntity(region, headers, format);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}

	}

}