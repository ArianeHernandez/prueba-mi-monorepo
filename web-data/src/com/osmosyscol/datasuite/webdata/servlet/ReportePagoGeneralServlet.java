package com.osmosyscol.datasuite.webdata.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Filtro;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Pago;

import co.htsoft.commons.lang.StringUtils;

public class ReportePagoGeneralServlet extends HttpServlet {
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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String pfecha_desde = StringUtils.trimToNull(request.getParameter("p_fecha_desde"));
		String pfecha_hasta = StringUtils.trimToNull(request.getParameter("p_fecha_hasta"));
		String pId_carga = StringUtils.trimToNull(request.getParameter("p_id_carga"));
		String pId_cliente = StringUtils.trimToNull(request.getParameter("p_id_cliente"));

		Filtro filtro = new Filtro();

		filtro.setId_cliente(pId_cliente == null ? null : Integer.parseInt(pId_cliente));
		filtro.setId_carga(pId_carga == null ? null : Integer.parseInt(pId_carga));
		filtro.setFecha_desde(pfecha_desde == null ? sdf.parse("01/01/2000") : sdf.parse(pfecha_desde));

		if (pfecha_hasta != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(pfecha_hasta));
			c.add(Calendar.DATE, 1);
			filtro.setFecha_hasta(c.getTime());
		} else {
			filtro.setFecha_hasta(sdf.parse("01/01/3000"));
		}

		Session session = CocoonUtils.getCocoonSession(request);

		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		if (id_usuario != null) {
			filtro.setId_cliente(id_usuario);
		}

		List<Pago> pagos = ReportePagosServicio.consultaPagos(filtro);

		File file = ReportePagosServicio.generarReporte(pagos);

		int length = 0;
		ServletOutputStream op = response.getOutputStream();

		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());

		response.setHeader("Content-Disposition", "attachment; filename=\"reporte_pagos_" + file.getName() + ".xlsx\"");

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
