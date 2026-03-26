package com.osmosyscol.datasuite.webdata.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.S3SignedUrl;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;

public class BajarArchAdjuntoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer id_archivo_adjunto = Integer.parseInt(request.getParameter("id_archivo_adjunto"));

		ArchivoAdjunto archivoAdjunto = ArchivoAdjuntoServicio.getInstance().obtenerArchivoAdjunto(id_archivo_adjunto);
		
		String original_filename = archivoAdjunto.getNombre_archivo()+"."+archivoAdjunto.getExtension_archivo();
		
		if (S3Servicio.getInstance().esAlmacenamientoS3() && archivoAdjunto.getRuta().startsWith("s3")) {
			byte[] bytes = S3Servicio.getInstance().descargarBytesArchivo(archivoAdjunto.getRuta());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().close();
			response.flushBuffer();
		} else {
			String filename = archivoAdjunto.getRuta();
			File file = new File(filename);			
			int length = 0;
			ServletOutputStream op = response.getOutputStream();
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(filename);
			response.setContentType("application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");
			
			byte[] bbuf = new byte[10000];
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
			
			in.close();
			op.flush();
			op.close();
			
		}
		

		
	}

}
