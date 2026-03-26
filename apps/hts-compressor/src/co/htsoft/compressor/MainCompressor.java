package co.htsoft.compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class MainCompressor {

	public static void main(String[] args) throws Exception {

		System.out.println(new File(".").getAbsolutePath());

		P.println(args);

		for (String s : args) {
			exe(s);
		}
	}

	public static void exe(String path) {

		File warFile = new File(path);

		File dirFile = newDir();

		System.out.println(dirFile.getAbsolutePath());

		if (!warFile.exists()) {
			return;
		}

		System.out.println("Descomprimiendo");
		ZipUtil.extractZip(warFile.getAbsolutePath(), dirFile.getAbsolutePath());

		System.out.println("Iniciando compresion..");
		comprimirArchivo(dirFile);

		System.out.println("Comprimiendo");
		try {
			warFile.delete();
			ZipUtil.createZip(dirFile.getAbsolutePath(), warFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileUtils.deleteDirectory(dirFile);
		} catch (Exception e) {
		}

	}

	private static void comprimirArchivo(File f) {

		if (f.isDirectory()) {

			File a[] = f.listFiles();

			for (File file : a) {
				comprimirArchivo(file);
			}

			return;
		}

		// --------------------
		// si es un javascript lo comprime como javascript

		try {

			if (f.getName().endsWith(".js")) {
				comprimirJavascript(f, f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (f.getName().endsWith(".css")) {
				comprimirCss(f, f);
			}
		} catch (Exception e) {
		}

	}

	private static int conJS = 0;

	private static Set<String> exceptionJS = new HashSet<>();

	static {
		exceptionJS.add("angular.min.js");
		exceptionJS.add("bootstrap.js");
		exceptionJS.add("underscore-min.js");
		exceptionJS.add("jquery-2.1.1.js");
		exceptionJS.add("jquery-ui-1.10.4.js");
		exceptionJS.add("jquery.tools.min.js");
		exceptionJS.add("jsonrpc.js");
		exceptionJS.add("jquery.form.js");
		exceptionJS.add("jquery-1.11.1.js");
		exceptionJS.add("jquery-1.7.2.js");
		exceptionJS.add("jquery.min-2.2.0.js");
		exceptionJS.add("jquery-1.6.2.min.js");
		exceptionJS.add("jquery-ui-1.12.1.js");
		exceptionJS.add("jsDraw2D.js");
		exceptionJS.add("jsDraw2D_Uncompressed.js");
		exceptionJS.add("jqColorPicker.min.js");
		exceptionJS.add("rsa.js");
		exceptionJS.add("rng.js");
		exceptionJS.add("prng4.js");
		exceptionJS.add("jsbn.js");
		exceptionJS.add("base64.js");
		exceptionJS.add("jquery.timers.js");
		exceptionJS.add("jquery.js");
		exceptionJS.add("jquery.form.js");
		exceptionJS.add("jquery.datePicker.js");
		exceptionJS.add("jquery-ui.js");
		exceptionJS.add("jquery-ui-1.9.2.custom.js");
		exceptionJS.add("jquery-ui-1.8.16.custom.min.js");
		exceptionJS.add("SimpleTextEditor.js");
		exceptionJS.add("");
	}

	public synchronized static void comprimirJavascript(File input, File output) throws Exception {

		System.out.print("Procesando: " + input.getName() + ", " + input.length() + " : ");
		String charset = getCharset(input);

		if (exceptionJS.contains(input.getName())) {
			if (!output.getAbsolutePath().equals(output.getAbsolutePath())) {
				FileUtils.copyFile(input, output);
			}
			System.out.println(": omitido");
			return;
		} else {

			try {
				// prepare input reader

				InputStream is = new FileInputStream(input);
				Reader reader = new InputStreamReader(is, charset);
				JavaScriptCompressor compressor = new JavaScriptCompressor(reader, null);

				// write compressed output
				OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), charset);
				compressor.compress(writer, 100, false, false, false, true);
				writer.flush();
				writer.close();
				is.close();
			} catch (Exception e) {
			}
		}

		if (output.length() > 50000) {

			if (!output.getAbsolutePath().equals(output.getAbsolutePath())) {
				FileUtils.copyFile(input, output);
			}

			System.out.println(": none");
			return;
		}

		// ofuscar codigo
		String codeman = FileUtils.readFileToString(output, charset);

		// invierte la cadena
		codeman = new StringBuilder(codeman).reverse().toString();

		String bs = StringUtils.trimToEmpty(codeman);
		String mals = "()[]{}/*-+.,!\"#$%&\'\t;\\=";

		for (int i = 0; i < mals.length(); i++) {
			bs = bs.replace(mals.charAt(i), ' ');
		}

		String[] p = bs.split("[ ]");

		Set<String> cadenas = new HashSet<>();

		for (String c : p) {
			c = StringUtils.trimToEmpty(c);
			if (c.length() > 3) {
				cadenas.add(c);
			}
		}

		List<String> cadenas2 = new ArrayList<>();
		for (String c : cadenas) {

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < c.length(); i++) {
				sb.append(String.format("\\u%04x", Character.codePointAt(c, i)));
			}

			cadenas2.add(sb.toString());
		}
		
		// ------------------

		StringBuffer sb = new StringBuffer();

		conJS++;
		String var = "x$$" + conJS;
		sb.append(var + "= {c:[");
		for (String c : cadenas2) {
			sb.append("\"" + c + "\",");
		}

		sb.append("window],r:function(c){var x=c.length;var i='';while(x>=0){i=i+c.charAt(x);x--;};return i;}};");

		StringBuffer cd = new StringBuffer(var + ".z=\"");
		for (int i = 0; i < codeman.length(); i++) {
			cd.append(String.format("\\u%04x", Character.codePointAt(codeman, i)));
		}
		cd.append("\";");

		String f = cd.toString();
		for (int i = 0; i < cadenas2.size(); i++) {
			f = f.replace(cadenas2.get(i), "\";" + var + ".z+=" + var + ".c[" + i + "];" + var + ".z+=\"");
		}

		sb.append(f);
		
		sb.append(var + ".c[" + cadenas.size() + "][\"\\u0065\\u0076\\u0061\\u006c\"](" + var + ".r(" + var + ".z));");

		String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String bt = sb.toString();

		for (int i = 0; i < a.length(); i++) {

			String aa = String.format("\\u%04x", Character.codePointAt(a, i));
			bt = bt.replace(aa, a.charAt(i) + "");
		}

		FileUtils.write(output, bt, charset);

		System.out.println(": ok");

	}

	public static void comprimirCss(File input, File output) throws Exception {

		String charset = getCharset(input);

		// prepare input reader

		InputStream is = new FileInputStream(input);
		Reader reader = new InputStreamReader(is, charset);
		CssCompressor compressor = new CssCompressor(reader);

		// write compressed output
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), charset);
		compressor.compress(writer, 10000);
		writer.flush();
		writer.close();
		is.close();

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

			fis.close();

			// (3)
			detector.dataEnd();

			// (4)
			encoding = detector.getDetectedCharset();

			// (5)
			detector.reset();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (encoding == null) {
			encoding = "UTF-8";
		}

		return encoding;

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

}
