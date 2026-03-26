package com.osmosyscol.datasuite.webdata.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Error al descargar archivo", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer id_formato = Integer.parseInt(request.getParameter("id_formato"));

		Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);

		String original_filename = "F" + id_formato + "-" + StringUtils.toFileName(formato.getNombre()) + ".xls";

		String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/FORMATOS";
		String filename = nombrecarpeta + "/" + original_filename;

		File f = new File(filename);

		if (f.exists()) {
			f.delete();
		}

		boolean generado=CargaServicio.getInstance().crearArchivoExcel(formato, (Session) request.getSession().getAttribute("cocoon-session"));
		if(!generado){
			response.sendRedirect(response.encodeRedirectURL("/WebData/carga_informacion/lotes/1.2.do?descargaError=S"));
		}
		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(filename);

		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");

		byte[] bbuf = new byte[10000];
		DataInputStream in = new DataInputStream(new FileInputStream(f));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();
	}

}
