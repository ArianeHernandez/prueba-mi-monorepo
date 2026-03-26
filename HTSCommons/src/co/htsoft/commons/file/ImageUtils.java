package co.htsoft.commons.file;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class ImageUtils {

	private static Map<String, byte[]> map = new ConcurrentHashMap<>();

	public static synchronized byte[] compressedImage(File imageFile, String formatName) {

		String path = imageFile.getAbsolutePath();

		if (map.containsKey(path + imageFile.lastModified())) {
			return map.get(path + imageFile.lastModified());
		}

		try {

			InputStream is = new FileInputStream(imageFile);
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			try {

				// create a BufferedImage as the result of decoding the supplied InputStream
				BufferedImage image = ImageIO.read(is);

				// get all image writers for JPG format
				Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);

				if (!writers.hasNext())
					throw new IllegalStateException("No writers found");

				ImageWriter writer = (ImageWriter) writers.next();
				ImageOutputStream ios = ImageIO.createImageOutputStream(os);
				writer.setOutput(ios);

				ImageWriteParam param = writer.getDefaultWriteParam();

				// compress to a given quality
				if (formatName.equalsIgnoreCase("jpg")) {
					float quality = 0.75f;
					param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
					param.setCompressionQuality(quality);
				}

				// appends a complete image stream containing a single image and
				// associated stream and image metadata and thumbnails to the output
				writer.write(null, new IIOImage(image, null, null), param);

				// close all streams
				is.close();
				os.close();
				ios.close();
				writer.dispose();

				byte[] b = os.toByteArray();
				map.put(path + imageFile.lastModified(), b);

				return b;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}