package co.htsoft.commons.file;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.mozilla.universalchardet.UniversalDetector;

public class CharsetDF {

	public static void exe(File file, String charset) {

		if (file.isFile()) {

			try {
				String str = FileUtils.readFileToString(file, Charset.forName(getCharset(file)));

				FileUtils.write(file, str, charset);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (file.isDirectory()) {
			File[] archivos = file.listFiles();
			for (File f : archivos) {
				exe(f, charset);
			}
		}

	}

	// ----------------------------

	public static String getCharset(byte[] bytes) {

		UniversalDetector detector = new UniversalDetector(null);
		detector.handleData(bytes, 0, bytes.length);
		detector.dataEnd();

		String encoding = detector.getDetectedCharset();

		if (encoding == null) {
			encoding = "UTF-8";
		}

		return encoding;
	}

	public static String getCharset(File file) {

		String encoding = null;

		try {

			// (1)
			UniversalDetector detector = new UniversalDetector(null);

			FileInputStream fis = new FileInputStream(file);

			byte[] buf = new byte[1000];

			// (2)
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			// (3)
			detector.dataEnd();

			// (4)
			encoding = detector.getDetectedCharset();
			if (encoding != null) {
				System.out.println("Detected encoding = " + encoding);
			} else {
				System.out.println("No encoding detected.");
			}

			// (5)
			detector.reset();

			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (encoding == null) {
			encoding = "UTF-8";
		}

		return encoding;

	}

}
