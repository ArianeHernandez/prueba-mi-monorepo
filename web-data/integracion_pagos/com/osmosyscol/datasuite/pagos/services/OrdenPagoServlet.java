package com.osmosyscol.datasuite.pagos.services;

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
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudAnulacionPago;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

import co.htsoft.commons.file.FileUtils;

public class OrdenPagoServlet extends HttpServlet {
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

		String query = "Select $SOLICITUD GENERAL DE PAGO$.IDCARGA, $SOLICITUD GENERAL DE PAGO$.ID id, $LINEA DE PRODUCTO.ID$ empresa_orden_pago, $ENCARGO.CODIGO$ codigo, $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ numero_orden_pago " //
				+ "from $SOLICITUD GENERAL DE PAGO$, $LINEA DE PRODUCTO$, $ENCARGO$ " //
				+ "where $SOLICITUD GENERAL DE PAGO.EMPRESA$ = $LINEA DE PRODUCTO$.ID " //
				+ " AND $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ = $ENCARGO$.ID " //
				+ " AND $SOLICITUD GENERAL DE PAGO$.ID = " + request.getParameter("id");//

		SolicitudAnulacionPago saop = DS_SqlUtils.queryForObject(SolicitudAnulacionPago.class, query);

		String[] s = saop.getCodigo().split("-");
		saop.setVigencia_orden_pago(new Integer(s[1]));

		File f = FileUtils.newFile("pdf");

		Carga carga = CargaServicio.getInstance().obtenerCarga(saop.getIdcarga());

		FileUtils.writeByteArrayToFile(f, CentralPagosService.reporteOrdenPago(saop.getEmpresa_orden_pago(), saop.getVigencia_orden_pago(), saop.getNumero_orden_pago(), carga.getId_negocio()));

		int length = 0;
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		String mimetype = context.getMimeType(f.getName());

		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) f.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"orden_pago.pdf\"");

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
