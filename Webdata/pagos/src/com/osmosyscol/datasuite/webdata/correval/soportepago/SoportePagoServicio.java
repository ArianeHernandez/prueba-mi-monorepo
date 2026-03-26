package com.osmosyscol.datasuite.webdata.correval.soportepago;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.correval.soportepago.dto.SoportePago;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class SoportePagoServicio {

	// -------------------------------------------------------------------------

	private static SoportePagoServicio instancia;

	public static SoportePagoServicio getInstance() {
		if (instancia == null) {
			instancia = new SoportePagoServicio();
		}
		return instancia;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------

	public SoportePago obtenerSoportePago(Integer idRetiro) {

		try {

			SoportePago soportePago = new SoportePago();

			soportePago.setIdRetiro(idRetiro);

			// -----------------

			DaoManager dm_creadatos = DaoConfig.getDaoManager(2);

			SQLDao sql = (SQLDao) dm_creadatos.getDao(SQLDao.class);

			// -----------------
			// Obtiene la informacion del retiro y beneficiario

			String select = RDServicio.reemplazarNombres("select r.idcarga, " + //
					"r.$retiros.valor$ valor, " + //
					"r.$retiros.observacion$ concepto," + //
					"b.$beneficiario.primer nombre$ nombre_beneficiario, " + //
					"b.$beneficiario.numero de documento$ identificacion_beneficiario, " + //
					"k.$banco.nombre$ banco, " + //
					"t.$tipo de cuenta.descripcion$ tipo_cuenta, " + //
					"b.$beneficiario.numero de cuenta$ numero_cuenta, " + //
					"r.$retiros.estado de respuesta banco$ estado_pago " + //
					"from $retiros$ r, $beneficiario$ b, $banco$ k, $tipo de cuenta$ t " + //
					" where r.id = " + idRetiro + " and r.$retiros.beneficiario$ = b.id and b.$beneficiario.banco$ = k.id and $beneficiario.tipo de cuenta$ = t.id");

			Map<String, Object> registro = sql.selectSQLRegistro(select);

			// ------------------------------------------------

			soportePago.setId_carga(Integer.parseInt(registro.get("IDCARGA").toString()));
			soportePago.setMonto(StringUtils.toStringFormat(Crypto.DF(registro.get("VALOR"))));
			soportePago.setNombreDestinatario(Crypto.DF(registro.get("NOMBRE_BENEFICIARIO")).toString());
			soportePago.setIdentificacionDestinatario(Crypto.DF(registro.get("IDENTIFICACION_BENEFICIARIO")).toString());
			soportePago.setBancoDestino((String) Crypto.DF(registro.get("BANCO")));
			soportePago.setTipoCuenta((String) Crypto.DF(registro.get("TIPO_CUENTA")));
			soportePago.setCuentaDestino((String) Crypto.DF(registro.get("NUMERO_CUENTA")));
			soportePago.setEstadoPago((String) Crypto.DF(registro.get("ESTADO_PAGO")));
			soportePago.setConcepto((String) Crypto.DF(registro.get("CONCEPTO")));

			// ------------------------------------------------

			Carga carga = CargaServicio.getInstance().obtenerCarga(soportePago.getId_carga());
			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());

			soportePago.setFecha(carga.getFecha_liberacion());
			soportePago.setNombreOriginador(StringUtils.trimToEmpty(usuario.getNombre()) + " " + StringUtils.trimToEmpty(usuario.getApellido()));

			// ------------------------------------------------

			return soportePago;

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;
	}

	// -------------------------------------------------------------------------

	public Boolean generarPdfSoportePagoyEnviarCorreo(Integer idRetiro, String para, String asunto, String mensaje) {

		if(idRetiro == null || StringUtils.esVacio(para, asunto, mensaje) || StringUtils.esVacio(asunto) || StringUtils.esVacio(mensaje))
			return false;
		
		String paraArray[] = para.split(";");
		
		Map<String, String> archivos = new HashMap<String, String>();
		archivos.put("#coso#", "file://" + obtenerArchivoPorIdRetiro(idRetiro).getAbsolutePath());

		// enviar correos
		try {
			for(String receptor:paraArray)
				EnviaMails.enviar(receptor.trim(), receptor.trim(), asunto, "plano:" + mensaje, archivos);
		} catch (Exception e) {
			SimpleLogger.setError("No se pudo enviar el correo de soporte de pago", e);
			return false;
		}

		return true;
	}

	// -------------------------------------------------------------------------

	public File obtenerArchivoPorIdRetiro(Integer idRetiro) {

		try {

			JasperDesign design = JRXmlLoader.load(new File(ContextInfo.getInstance().getDiskPathForResource("componentes/reporte/soporte_pago.jrxml")));

			JasperReport report = JasperCompileManager.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ReportTitle", "PDF JasperReport");

			JRDataSource dataSource = new SoportePagoJRDS(idRetiro);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

			new File(ParametrosInicio.getProperty("file.carpeta") + "/soporte_pago").mkdirs();

			File f = new File(ParametrosInicio.getProperty("file.carpeta") + "/soporte_pago/soporte_pago_" + idRetiro + ".pdf");

			if (f.exists()) {
				f.delete();
			}

			OutputStream output = new FileOutputStream(f);
			JasperExportManager.exportReportToPdfStream(print, output);

			return f;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// -------------------------------------------------------------------------

}

class SoportePagoJRDS implements JRDataSource {

	private SoportePago soportePago;
	private boolean leido = false;

	public SoportePagoJRDS(Integer idRetiro) {
		soportePago = SoportePagoServicio.getInstance().obtenerSoportePago(idRetiro);
		
		SimpleLogger.setDebug(XMLFormat.format(JavaToXML.exe("SoportePago", soportePago).toString()));
		
	}

	public Object getFieldValue(JRField field) throws JRException {

		try {
			Method m = soportePago.getClass().getMethod("get" + field.getName());

			return m.invoke(soportePago, new Object[0]);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean next() throws JRException {

		if (!leido) {
			leido = true;
			return true;
		} else {
			return false;
		}
	}
}
