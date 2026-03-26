package com.osmosyscol.datasuite.webdata.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.servicios.GeneradorArchivoCargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.json.CargaJsonServicio;

public class DownloadServletExcel extends HttpServlet {
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

		
		String idFile = request.getParameter("idfile");
		File f = null;
		
		Integer id_carga = Integer.parseInt(request.getParameter("id_carga"));

		if(idFile!=null){
			
			f = CargaJsonServicio.getFileExcel(idFile);
			
		}else{
			
			Integer id_formato_salida = Integer.parseInt(request.getParameter("id_formato"));

			f = GeneradorArchivoCargaServicio.getInstance().crearArchivo(id_carga, id_formato_salida);
		}
		
		int length = 0;
		ServletOutputStream op = response.getOutputStream();

		response.setContentType("application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"carga_" + id_carga + ".xls\"");

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
