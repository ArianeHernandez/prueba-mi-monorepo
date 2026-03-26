package co.htsoft.commons.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtils extends org.apache.commons.io.FileUtils {

	public static byte[] decodeBase64(String str) {
		if (str == null) {
			return new byte[0];
		}

		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encodeBase64(File file) {
		return encodeBase64(toBytes(file));
	}

	public static String encodeBase64(byte[] decode) {

		if (decode == null) {
			return null;
		}

		BASE64Encoder encoder = new BASE64Encoder();

		try {
			return encoder.encodeBuffer(decode).replace("\r\n", "").replace("\n", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] toBytes(File file) {

		if (file == null || !file.isFile() || !file.exists()) {
			return null;
		}

		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;

	}

	public static File toFile(byte[] bytes, String strFile) {

		File file = new File(strFile);

		if (file.exists()) {
			file.delete();
		}

		if (bytes == null) {
			return null;
		}

		try {
			FileOutputStream fileOuputStream = new FileOutputStream(strFile);
			fileOuputStream.write(bytes);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;

	}

	public static File newFile() {
		return newFile("tmp");
	}

	public static File newFile(String ext) {
		String fileName = System.getProperty("java.io.tmpdir") + "/T" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + "_" + Math.round(1000d * Math.random()) + "." + ext;

		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}

		return file;
	}

	public static File newDir() {
		String fileName = System.getProperty("java.io.tmpdir") + "/T" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + "_" + Math.round(1000d * Math.random());

		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}

		file.mkdirs();

		return file;
	}

	public static File toFile(String fileBase64) {

		byte[] bw = FileUtils.decodeBase64(fileBase64);

		File file = newFile();

		return FileUtils.toFile(bw, file.getAbsolutePath());
	}

	public static File toFile(String fileBase64, String filename) {

		byte[] bw = FileUtils.decodeBase64(fileBase64);

		File file = new File(filename);

		return FileUtils.toFile(bw, file.getAbsolutePath());
	}

	private static Map<Class<?>, Map<String, String>> cacheResource = new ConcurrentHashMap<>();

	public static String getResourceContent(Class<?> _class, String path) {

		Map<String, String> cache = cacheResource.get(_class);

		if (cache == null) {
			cache = new ConcurrentHashMap<>();
			cacheResource.put(_class, cache);
		}

		String val = cache.get(path);

		if (val != null) {
			return val;
		}

		try {
			InputStream in = _class.getResourceAsStream(path);
			String res = IOUtils.toString(in);
			in.close();
			cache.put(path, res);

			return res;

		} catch (Exception e) {
			return null;
		}

	}

	public static byte[] getFile(String url, Map<String, String> headers) {

		try {
			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setConnectTimeout(30000000);
			urlConn.setRequestMethod("GET");

			// ---------------------------------------------------------------- define headers

			urlConn.setRequestProperty("Connection", "Keep-Alive");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			urlConn.setRequestProperty("Pragma", "no-cache");

			if (headers != null) {
				for (String key : headers.keySet()) {
					urlConn.setRequestProperty(key, headers.get(key));
				}
			}

			InputStream input = urlConn.getInputStream();
			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			return bytes;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String getMimetype(File file) {

		String mt = "application/octet-stream";

		String ext = null;
		String filename = file.getName();

		if (filename.indexOf(".") > 0) {
			ext = filename.substring(filename.lastIndexOf(".")).substring(1);
		}

		if (ext != null) {

			switch (ext) {

			// ---
			case "abs":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "ai":
				mt = "application/postscript";
				break;
			// ---
			case "aif":
				mt = "audio/x-aiff";
				break;
			// ---
			case "aifc":
				mt = "audio/x-aiff";
				break;
			// ---
			case "aiff":
				mt = "audio/x-aiff";
				break;
			// ---
			case "aim":
				mt = "application/x-aim";
				break;
			// ---
			case "art":
				mt = "image/x-jg";
				break;
			// ---
			case "asf":
				mt = "video/x-ms-asf";
				break;
			// ---
			case "asx":
				mt = "video/x-ms-asf";
				break;
			// ---
			case "au":
				mt = "audio/basic";
				break;
			// ---
			case "avi":
				mt = "video/x-msvideo";
				break;
			// ---
			case "avx":
				mt = "video/x-rad-screenplay";
				break;
			// ---
			case "bcpio":
				mt = "application/x-bcpio";
				break;
			// ---
			case "bin":
				mt = "application/octet-stream";
				break;
			// ---
			case "bmp":
				mt = "image/bmp";
				break;
			// ---
			case "body":
				mt = "text/html";
				break;
			// ---
			case "cdf":
				mt = "application/x-cdf";
				break;
			// ---
			case "cer":
				mt = "application/x-x509-ca-cert";
				break;
			// ---
			case "class":
				mt = "application/java";
				break;
			// ---
			case "cpio":
				mt = "application/x-cpio";
				break;
			// ---
			case "csh":
				mt = "application/x-csh";
				break;
			// ---
			case "css":
				mt = "text/css";
				break;
			// ---
			case "dib":
				mt = "image/bmp";
				break;
			// ---
			case "doc":
				mt = "application/msword";
				break;
			// ---
			case "dtd":
				mt = "text/plain";
				break;
			// ---
			case "dv":
				mt = "video/x-dv";
				break;
			// ---
			case "dvi":
				mt = "application/x-dvi";
				break;
			// ---
			case "eps":
				mt = "application/postscript";
				break;
			// ---
			case "etx":
				mt = "text/x-setext";
				break;
			// ---
			case "exe":
				mt = "application/octet-stream";
				break;
			// ---
			case "gif":
				mt = "image/gif";
				break;
			// ---
			case "gtar":
				mt = "application/x-gtar";
				break;
			// ---
			case "gz":
				mt = "application/x-gzip";
				break;
			// ---
			case "hdf":
				mt = "application/x-hdf";
				break;
			// ---
			case "htc":
				mt = "text/x-component";
				break;
			// ---
			case "htm":
				mt = "text/html";
				break;
			// ---
			case "html":
				mt = "text/html";
				break;
			// ---
			case "hqx":
				mt = "application/mac-binhex40";
				break;
			// ---
			case "ief":
				mt = "image/ief";
				break;
			// ---
			case "jad":
				mt = "text/vnd.sun.j2me.app-descriptor";
				break;
			// ---
			case "jar":
				mt = "application/java-archive";
				break;
			// ---
			case "java":
				mt = "text/plain";
				break;
			// ---
			case "jnlp":
				mt = "application/x-java-jnlp-file";
				break;
			// ---
			case "jpe":
				mt = "image/jpeg";
				break;
			// ---
			case "jpeg":
				mt = "image/jpeg";
				break;
			// ---
			case "jpg":
				mt = "image/jpeg";
				break;
			// ---
			case "js":
				mt = "text/javascript";
				break;
			// ---
			case "jsf":
				mt = "text/plain";
				break;
			// ---
			case "jspf":
				mt = "text/plain";
				break;
			// ---
			case "kar":
				mt = "audio/x-midi";
				break;
			// ---
			case "latex":
				mt = "application/x-latex";
				break;
			// ---
			case "m3u":
				mt = "audio/x-mpegurl";
				break;
			// ---
			case "mac":
				mt = "image/x-macpaint";
				break;
			// ---
			case "man":
				mt = "application/x-troff-man";
				break;
			// ---
			case "me":
				mt = "application/x-troff-me";
				break;
			// ---
			case "mid":
				mt = "audio/x-midi";
				break;
			// ---
			case "midi":
				mt = "audio/x-midi";
				break;
			// ---
			case "mif":
				mt = "application/x-mif";
				break;
			// ---
			case "mov":
				mt = "video/quicktime";
				break;
			// ---
			case "movie":
				mt = "video/x-sgi-movie";
				break;
			// ---
			case "mp1":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "mp2":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "mp3":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "mpa":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "mpe":
				mt = "video/mpeg";
				break;
			// ---
			case "mpeg":
				mt = "video/mpeg";
				break;
			// ---
			case "mpega":
				mt = "audio/x-mpeg";
				break;
			// ---
			case "mpg":
				mt = "video/mpeg";
				break;
			// ---
			case "mpv2":
				mt = "video/mpeg2";
				break;
			// ---
			case "ms":
				mt = "application/x-wais-source";
				break;
			// ---
			case "nc":
				mt = "application/x-netcdf";
				break;
			// ---
			case "oda":
				mt = "application/oda";
				break;
			// ---
			case "pbm":
				mt = "image/x-portable-bitmap";
				break;
			// ---
			case "pct":
				mt = "image/pict";
				break;
			// ---
			case "pdf":
				mt = "application/pdf";
				break;
			// ---
			case "pgm":
				mt = "image/x-portable-graymap";
				break;
			// ---
			case "pic":
				mt = "image/pict";
				break;
			// ---
			case "pict":
				mt = "image/pict";
				break;
			// ---
			case "pls":
				mt = "audio/x-scpls";
				break;
			// ---
			case "png":
				mt = "image/png";
				break;
			// ---
			case "pnm":
				mt = "image/x-portable-anymap";
				break;
			// ---
			case "pnt":
				mt = "image/x-macpaint";
				break;
			// ---
			case "ppm":
				mt = "image/x-portable-pixmap";
				break;
			// ---
			case "ps":
				mt = "application/postscript";
				break;
			// ---
			case "psd":
				mt = "image/x-photoshop";
				break;
			// ---
			case "qt":
				mt = "video/quicktime";
				break;
			// ---
			case "qti":
				mt = "image/x-quicktime";
				break;
			// ---
			case "qtif":
				mt = "image/x-quicktime";
				break;
			// ---
			case "ras":
				mt = "image/x-cmu-raster";
				break;
			// ---
			case "rgb":
				mt = "image/x-rgb";
				break;
			// ---
			case "rm":
				mt = "application/vnd.rn-realmedia";
				break;
			// ---
			case "roff":
				mt = "application/x-troff";
				break;
			// ---
			case "rtf":
				mt = "application/rtf";
				break;
			// ---
			case "rtx":
				mt = "text/richtext";
				break;
			// ---
			case "sh":
				mt = "application/x-sh";
				break;
			// ---
			case "shar":
				mt = "application/x-shar";
				break;
			// ---
			case "sit":
				mt = "application/x-stuffit";
				break;
			// ---
			case "smf":
				mt = "audio/x-midi";
				break;
			// ---
			case "snd":
				mt = "audio/basic";
				break;
			// ---
			case "src":
				mt = "application/x-wais-source";
				break;
			// ---
			case "sv4cpio":
				mt = "application/x-sv4cpio";
				break;
			// ---
			case "sv4crc":
				mt = "application/x-sv4crc";
				break;
			// ---
			case "swf":
				mt = "application/x-shockwave-flash";
				break;
			// ---
			case "t":
				mt = "application/x-troff";
				break;
			// ---
			case "tar":
				mt = "application/x-tar";
				break;
			// ---
			case "tcl":
				mt = "application/x-tcl";
				break;
			// ---
			case "tex":
				mt = "application/x-tex";
				break;
			// ---
			case "texi":
				mt = "application/x-texinfo";
				break;
			// ---
			case "texinfo":
				mt = "application/x-texinfo";
				break;
			// ---
			case "tif":
				mt = "image/tiff";
				break;
			// ---
			case "tiff":
				mt = "image/tiff";
				break;
			// ---
			case "tr":
				mt = "application/x-troff";
				break;
			// ---
			case "tsv":
				mt = "text/tab-separated-values";
				break;
			// ---
			case "txt":
				mt = "text/plain";
				break;
			// ---
			case "ulw":
				mt = "audio/basic";
				break;
			// ---
			case "ustar":
				mt = "application/x-ustar";
				break;
			// ---
			case "xbm":
				mt = "image/x-xbitmap";
				break;
			// ---
			case "xml":
				mt = "text/xml";
				break;
			// ---
			case "xpm":
				mt = "image/x-xpixmap";
				break;
			// ---
			case "xsl":
				mt = "text/xml";
				break;
			// ---
			case "xwd":
				mt = "image/x-xwindowdump";
				break;
			// ---
			case "war":
				mt = "application/x-zip";
				break;
			// ---
			case "wav":
				mt = "audio/x-wav";
				break;
			// ---
			case "svg":
				mt = "image/svg+xml";
				break;
			// ---
			case "svgz":
				mt = "image/svg+xml";
				break;
			// ---
			case "wbmp":
				mt = "image/vnd.wap.wbmp";
				break;
			// ---
			case "wml":
				mt = "text/vnd.wap.wml";
				break;
			// ---
			case "wmlc":
				mt = "application/vnd.wap.wmlc";
				break;
			// ---
			case "wmls":
				mt = "text/vnd.wap.wmlscript";
				break;
			// ---
			case "wmlscriptc":
				mt = "application/vnd.wap.wmlscriptc";
				break;
			// ---
			case "wrl":
				mt = "x-world/x-vrml";
				break;
			// ---
			case "Z":
				mt = "application/x-compress";
				break;
			// ---
			case "z":
				mt = "application/x-compress";
				break;
			// ---
			case "zip":
				mt = "application/zip";
				break;
			}
		}

		return mt;
	}

}
