package co.htsoft.commons.net;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import co.htsoft.commons.lang.StringUtils;

public class ServletUtils {

	public static void downloadFile(HttpServletResponse response, File f) {
		downloadFile(response, f, null);
	}

	public static void downloadFile(HttpServletResponse response, File arhivo, String nombre) {

		// Si el archivo no existe finaliza!!
		if (arhivo == null || !arhivo.exists()) {
			return;
		}

		// Si no se especifica el nombre se usa el del archivo
		if (nombre == null) {
			nombre = arhivo.getName();
		}

		// lee el archivo y lo escribe en el response
		try {

			int length = 0;
			ServletOutputStream op = response.getOutputStream();

			response.setContentType("application/octet-stream");
			response.setContentLength((int) arhivo.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + "\"");

			byte[] bbuf = new byte[100000];
			DataInputStream in = new DataInputStream(new FileInputStream(arhivo));

			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}

			in.close();
			op.flush();
			op.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static <T> T queryToObject(String a, Class<T> _class) {

		StringBuffer json = new StringBuffer();

		json.append("{");

		if (a == null) {
			return null;
		}
		
		int p = 0;

		String[] lineas = a.split("[&]");

		for (String linea : lineas) {

			String[] data = linea.split("=");
			if (data.length > 1) {
				if(p > 0){
					json.append(", ");
				}
				json.append("\"" + data[0].replace("\"", "\\\"") + "\":");
				json.append("\"" + StringUtils.decodeURIComponent(data[1]).replace("\"", "\\\"") + "\"");
				p++;
			}
		}

		json.append("}");

		return new Gson().fromJson(json.toString(), _class);
	}

}
