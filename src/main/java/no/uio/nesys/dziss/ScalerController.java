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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.uio.nesys.pyramidio.DeepZoomImageReaderUrl;

@RestController
public class ScalerController {
	
	private static final Logger logger = Logger.getLogger(
			ScalerController.class.getName());

	@RequestMapping("/scaler")
	public ResponseEntity<InputStreamResource> scaler(@RequestParam String dzi, @RequestParam int x,
			@RequestParam int y, @RequestParam int width, @RequestParam int height,
			@RequestParam(defaultValue = "1") double scale, @RequestParam(defaultValue = "png") String format) {

		try {
			DeepZoomImageReaderUrl reader = new DeepZoomImageReaderUrl(new URL(dzi));
			BufferedImage region = reader.getSubImage(new Rectangle(x, y, width, height), scale);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(region, format, baos);

			byte[] imageInByte = baos.toByteArray();

			HttpHeaders headers = new HttpHeaders();
			headers.add("content-disposition",
					"inline;filename=" + x + "_" + y + "_" + width + "_" + height + "_" + scale + "." + format);

			InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(imageInByte));
			
			logger.info("Image size: " + imageInByte.length);;

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

			return ResponseEntity.ok().headers(headers).contentLength(imageInByte.length).contentType(mt)
					.body(resource);

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}

		// https://dziss.apps-dev.hbp.eu/index.html?dzi=https://object.cscs.ch/v1/AUTH_08c08f9f119744cbbf77e216988da3eb/imgsvc-890b727c-f3b5-624f-028a-4f6d35acce18/D1R_P70_F_C60_s001.tif/D1R_P70_F_C60_s001.dzi&x=100&y=150&width=200&height=250&scale=0.5&format=png

	}

}