package com.osmosyscol.datasuite.webdata.correval.bancos.entrada.servlet;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.correval.soportepago.SoportePagoServicio;

public class SoportePagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			downloadFile(session, request, response);
		} catch (FileUploadException e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void downloadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {

		File f = SoportePagoServicio.getInstance().obtenerArchivoPorIdRetiro(new Integer(request.getParameter("id_retiro")));

		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(f.getName());

		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");

		byte[] bbuf = new byte[100000];
		DataInputStream in = new DataInputStream(new FileInputStream(f));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();

	}
}
