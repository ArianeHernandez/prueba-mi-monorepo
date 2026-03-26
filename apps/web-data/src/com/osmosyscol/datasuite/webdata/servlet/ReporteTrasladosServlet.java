package com.osmosyscol.datasuite.webdata.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReporteTrasladosGenerados;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReporteTrasladosGenerados.Traslado;

import co.htsoft.commons.lang.StringUtils;

public class ReporteTrasladosServlet extends HttpServlet {
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

		HttpSession session = request.getSession();
		if (session != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	
			String pfecha_desde = StringUtils.trimToNull(request.getParameter("p_fecha_desde"));
			String pfecha_hasta = StringUtils.trimToNull(request.getParameter("p_fecha_hasta"));
	
			Date fechaDesde = pfecha_desde == null ? sdf.parse("01/01/2000") : sdf.parse(pfecha_desde);
			Date fechaHasta = pfecha_hasta == null ? sdf.parse("01/01/3000") : sdf.parse(pfecha_hasta);
	
			List<Traslado> traslados = ReporteTrasladosGenerados.consultaTraslados(fechaDesde, fechaHasta);
	
			File file = ReporteTrasladosGenerados.generarReporte(traslados);
	
			int length = 0;
			ServletOutputStream op = response.getOutputStream();
	
			response.setContentType("application/octet-stream");
			response.setContentLength((int) file.length());
	
			String pfechadesde = fechaDesde == null ? "x" : sdf2.format(fechaDesde);
			String pfechahasta = fechaHasta == null ? "x" : sdf2.format(fechaHasta);
	
			response.setHeader("Content-Disposition", "attachment; filename=\"reporte_traslados_" + pfechadesde + "_" + pfechahasta + ".xlsx\"");
	
			byte[] bbuf = new byte[10000];
			DataInputStream in = new DataInputStream(new FileInputStream(file));
	
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
	
			in.close();
			op.flush();
			op.close();
		}else {
			return;
		}

	}

}
