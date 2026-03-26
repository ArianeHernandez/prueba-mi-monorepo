package co.htsoft.commons.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

import ch.simschla.minify.css.CssMin;
import ch.simschla.minify.js.JsMin;

public class MinifyUtils {

	private static Map<String, byte[]> map = new ConcurrentHashMap<>();

	public static synchronized byte[] css(File file) {

		String path = file.getAbsolutePath();

		if (map.containsKey(path + file.lastModified())) {
			return map.get(path + file.lastModified());
		}

		try {
			byte[] data = FileUtils.readFileToByteArray(file);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			CssMin.builder().inputStream(new ByteArrayInputStream(data)).outputStream(byteArrayOutputStream).build().minify();

			data = byteArrayOutputStream.toByteArray();

			return data;

		} catch (Exception e) {
			System.out.println("Error: " + file.getAbsolutePath());
			e.printStackTrace();
			return null;
		}

	}

	public static synchronized byte[] js(File file) {

		String path = file.getAbsolutePath();

		if (map.containsKey(path + file.lastModified())) {
			return map.get(path + file.lastModified());
		}

		try {
			byte[] data = FileUtils.readFileToByteArray(file);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JsMin.builder().inputStream(new ByteArrayInputStream(data)).outputStream(byteArrayOutputStream).build().minify();
			data = byteArrayOutputStream.toByteArray();

			return data;

		} catch (Exception e) {
			System.out.println("Error: " + file.getAbsolutePath());
			e.printStackTrace();
			return null;
		}

	}

}
