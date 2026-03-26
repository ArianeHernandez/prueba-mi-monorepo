package com.osmosyscol.datasuite.webdata.correval.bancos.entrada.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Session;
import org.apache.commons.fileupload.FileUploadException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.correval.consultaretiro.RetiroConsultaServicio;

public class RetiroConsultaServlet extends HttpServlet {
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

		Session cs = (Session) session.getAttribute("cocoon-session");

		String tipo_actualizacion = (String) cs.getAttribute("RetiroConsulta.tipo_actualizacion");
		Integer id_carga = (Integer) cs.getAttribute("RetiroConsulta.id_carga");
		Date fecha_giro_d = (Date) cs.getAttribute("RetiroConsulta.fecha_giro");
		Integer id_beneficiario = (Integer) cs.getAttribute("RetiroConsulta.id_beneficiario");
		String respuesta_banco = (String) cs.getAttribute("RetiroConsulta.respuesta_banco");
		BigDecimal valorbd = (BigDecimal) cs.getAttribute("RetiroConsulta.valor");
		Integer id_banco_girador = (Integer) cs.getAttribute("RetiroConsulta.id_banco_girador");
		Integer id_banco_destino = (Integer) cs.getAttribute("RetiroConsulta.id_banco_destino");
		String conArchivo = (String) cs.getAttribute("RetiroConsulta.conArchivo");

		File f = RetiroConsultaServicio.getInstance().obtenerArchivo(id_banco_girador, id_banco_destino, respuesta_banco, fecha_giro_d, id_beneficiario, tipo_actualizacion, valorbd, id_carga, conArchivo);

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
