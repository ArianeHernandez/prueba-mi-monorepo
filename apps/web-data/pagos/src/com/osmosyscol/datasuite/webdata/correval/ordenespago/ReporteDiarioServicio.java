package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.utils.ObjectJRDS;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReporteDiarioServicio {

	public static File generarReporte(Integer id_cliente) {

		try {
			String path = ContextInfo.getInstance().getDiskPathForResource("componentes/reporte/ordenes_pago.jrxml");

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ruta_imagen", new File(ContextInfo.getInstance().getDiskPathForResource("componentes/reporte/logo.png")).getAbsolutePath());

			// -------------------------------------
			ReporteDiarioPagos test = CargaClienteServicio.obtenerReporteDiario(id_cliente);

			JasperReport report = JasperCompileManager.compileReport(JRXmlLoader.load(path));

			ObjectJRDS dataSource = new ObjectJRDS(test);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

			// genera pdf
			File outputFile = FileUtils.newFile("pdf");

			OutputStream output = new FileOutputStream(outputFile);
			JasperExportManager.exportReportToPdfStream(print, output);

			return outputFile;
		} catch (Exception e) {
			SimpleLogger.setError(e);
			return null;
		}

	}

	public static boolean enviarEmailReportePorCliente(Integer id_cliente) {

		try {

			File f = generarReporte(id_cliente);

			if (f == null) {
				return false;
			}

			String path = "file://" + f.getAbsolutePath();

			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("reportePagos", path);

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_cliente);

			Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());

			Empresa empresa = new Empresa();
			empresa.setNombre(persona.getNombreCompleto());

			// Enviar email al cliente administrativo
			{
				String html = ContenidoServicio.getInstance().obtenerContenido("General", "EMAIL REPORTE ORDENES DE PAGO").getTexto();
				html = EnviaMails.replazarTextos(html, persona, empresa);
				EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Reporte Diario de Ordenes de Pago", "plano:" + html, archivos);

				System.out.println("Se envio correo al cliente: " + persona.getNombreCompleto());
			}

			// Enviar Email a todos los liberadores del cliente
			{
				List<Liberador> liberadores = LiberadorServicio.getInstance().obtenerLiberadores(id_cliente, 1);

				for (Liberador liberador : liberadores) {
					Persona personaL = PersonaServicio.getInstance().obtenerPersona(liberador.getId_persona());

					String html = ContenidoServicio.getInstance().obtenerContenido("General", "EMAIL REPORTE ORDENES DE PAGO").getTexto();
					html = EnviaMails.replazarTextos(html, personaL, empresa);
					EnviaMails.enviar(personaL.getCorreo(), personaL.getNombreCompleto(), "Reporte Diario de Ordenes de Pago", "plano:" + html, archivos);

					System.out.println("Se envio correo al liberador: " + personaL.getNombreCompleto());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static void enviarEmailReporteDiarioPagos() {

		String sql = "SELECT DISTINCT T.ID_CLIENTE" + " FROM TS01 T, $RETIROS$ R " + " WHERE T.ID_CARGA = R.IDCARGA" + " AND T.ESTADO IN ('C', 'S', 'R')" + " AND $RETIROS.TIPO DE RETIRO$ = $S(ACH)$" + " AND TRUNC(T.FECHA_LIBERACION) = TRUNC(SYSDATE)";

		List<Integer> clientes = DS_SqlUtils.queryForList(Integer.class, sql);

		for (Integer cliente : clientes) {
			enviarEmailReportePorCliente(cliente);
		}

	}

}
