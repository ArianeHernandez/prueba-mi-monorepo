package com.osmosyscol.datasuite.webdata.correval.consultaretiro;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class RetiroConsultaServicio {

	private static RetiroConsultaServicio instance;

	private RetiroConsultaServicio() {
	}

	public static RetiroConsultaServicio getInstance() {
		if (instance == null) {
			instance = new RetiroConsultaServicio();
		}
		return instance;
	}

	// ---------------------

	public List<Map<String, Object>> obtenerBancos() {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

		RetiroDao retiroDao = new RetiroDao();

		try {
			return retiroDao.obtenerBancos(cargaDao);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ---------------------

	public List<Map<String, Object>> obtenerRespuestasBanco() {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

		RetiroDao retiroDao = new RetiroDao();

		try {
			return retiroDao.obtenerRespuestasBanco(cargaDao);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ---------------------------------------------------------
	// ---------------------------------------------------------

	private String obtenerSQLRetirosConsulta(Integer id_carga, Integer id_banco_destino, Integer id_beneficiario, Integer id_banco_girador, String respuesta_banco, String tipo_actualizacion, BigDecimal valorbd, Date fecha_giro_d, String con_respuesta) {

		String sql = "select * from (" + //
				"select r.id id_registro," + //
				"r.idcarga id_carga, " + //
				"t.NOMBRE_CLIENTE nombre_cliente, " + //
				"b.$beneficiario.numero de documento$ id_beneficiario, " + //
				"b.$beneficiario.primer nombre$ nombre_beneficiario, " + //
				"bd.id id_banco_destino, " + //
				"bd.$banco.nombre$ banco_destino, " + //
				"b.$beneficiario.numero de cuenta$ cuenta_destino, " + //
				"gg.$grupo giro.fecha pago$ fecha_giro, " + //
				"r.$retiros.valor$ valor, " + //
				"decode(r.$retiros.estado de respuesta banco$, null, 'Sin respuesta', r.$retiros.estado de respuesta banco$) respuesta_banco, " + //
				"$RETIROS.TIPO ACTUALIZACION$ tipo_actualizacion, " + //
				"bg.id id_banco_girador, " + //
				"bg.$banco.nombre$ banco_girador, " + //
				"gg.$GRUPO GIRO.ARCHIVO$ archivo " + //

				"from $RETIROS$ r, $BENEFICIARIO$ b, ts01 t, $BANCO$ bd, $GRUPO GIRO$ gg, $CUENTA - BANCO$ cb, $BANCO$ bg " + //
				"where t.id_carga =  r.idcarga " + //
				"and r.$retiros.beneficiario$ = b.id " + //
				"and bd.id = $beneficiario.banco$ " + //
				"and gg.$GRUPO GIRO.CODIGO REGISTRO$_num = r.id " + //
				"and cb.id = gg.$GRUPO GIRO.CUENTA$ " + //
				"and cb.$CUENTA - BANCO.BANCO$ = bg.id " + //
				"and gg.$GRUPO GIRO.ARCHIVO$ is not null" + //
				" ) cc " + //

				"where 1=1";

		if (id_carga != null) {
			sql += " and cc.id_carga = " + id_carga + " ";
		}

		if (id_banco_destino != null) {
			sql += " and cc.id_banco_destino = " + id_banco_destino + " ";
		}

		if (id_beneficiario != null) {
			sql += " and cc.id_beneficiario = '" + Crypto.E(id_beneficiario) + "' ";
		}

		if (id_banco_girador != null) {
			sql += " and cc.id_banco_girador = '" + id_banco_girador + "' ";
		}

		if (StringUtils.isNotBlank(respuesta_banco)) {
			sql += " and cc.respuesta_banco = '" + Crypto.E(respuesta_banco) + "' ";
		}

		if (StringUtils.isNotBlank(tipo_actualizacion)) {
			sql += " and cc.tipo_actualizacion = '" + Crypto.E(tipo_actualizacion) + "' ";
		}

		if (valorbd != null) {
			sql += " and cc.valor = '" + Crypto.E(valorbd) + "' ";
		}

		if (fecha_giro_d != null) {
			sql += " and cc.fecha_giro = '" + Crypto.E(fecha_giro_d) + "' ";
		}
		
		if (StringUtils.isNotBlank(con_respuesta)) {
			if(con_respuesta.equalsIgnoreCase("S")){
				sql += " and cc.respuesta_banco != 'Sin respuesta' ";
			}else{
				sql += " and cc.respuesta_banco = 'Sin respuesta' ";
			}			
		}

		return RDServicio.reemplazarNombres(sql);
	}

	// ---

	public Integer obtenerTotalRetirosRespuesta(Integer id_carga, Integer id_banco_destino, Integer id_beneficiario, Integer id_banco_girador, String respuesta_banco, String tipo_actualizacion, BigDecimal valorbd, Date fecha_giro_d, String conArchivo) {
		try {

			String sql = "select count(*) from (" + obtenerSQLRetirosConsulta(id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valorbd, fecha_giro_d, conArchivo) + ") dd";

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			return sqlDao.selectSQLNumber(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public List<Map<String, Object>> obtenerRetirosRespuesta(Integer id_carga, Integer id_banco_destino, Integer id_beneficiario, Integer id_banco_girador, String respuesta_banco, String tipo_actualizacion, BigDecimal valorbd, Date fecha_giro_d, String ordenado_por, String conArchivo, Integer tamano_pagina, Integer pagina_actual) {
		try {

			String sql = obtenerSQLRetirosConsulta(id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valorbd, fecha_giro_d, conArchivo);

			if (StringUtils.isNotBlank(ordenado_por)) {
				sql += " order by cc." + ordenado_por + " ";
			}

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Map<String, Object>> retiros = sqlDao.selectSQLPaginado(sql, pagina_actual, tamano_pagina);

			// desencriptar
			if (retiros != null) {
				retiros = SQLServicio.desencriptarMapaStringFormat(retiros);
			}

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ---------------------------------------------------------
	// ---------------------------------------------------------

	// -------------------------------------------------------------------------

	public File obtenerArchivo(Integer id_banco_girador, Integer id_banco_destino, String respuesta_banco, Date fecha_giro_d, Integer id_beneficiario, String tipo_actualizacion, BigDecimal valorbd, Integer id_carga, String conArchivo) {

		try {

			JasperDesign design = JRXmlLoader.load(new File(ContextInfo.getInstance().getDiskPathForResource("componentes/reporte/retiro_consulta.jrxml")));

			JasperReport report = JasperCompileManager.compileReport(design);

			Map<String, Object> parameters = new HashMap<String, Object>();

			JRDataSource dataSource = new RetiroJRDS(id_banco_girador, id_banco_destino, respuesta_banco, fecha_giro_d, id_beneficiario, tipo_actualizacion, valorbd, id_carga, conArchivo);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

			new File(ParametrosInicio.getProperty("file.carpeta") + "/consulta_retiro").mkdirs();

			String base_name = ParametrosInicio.getProperty("file.carpeta") + "/consulta_retiro/consulta_retiro_" + System.currentTimeMillis() + "_" + StringUtils.randomString(3);

			JRXlsxExporter apiExporter = new JRXlsxExporter();
			apiExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			apiExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, base_name + ".xls");
			apiExporter.exportReport();

			return new File(base_name + ".xls");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// -------------------------------------------------------------------------

}

class RetiroJRDS implements JRDataSource {

	private List<Map<String, Object>> retiro;
	private int pos = -1;

	public RetiroJRDS(Integer id_banco_girador, Integer id_banco_destino, String respuesta_banco, Date fecha_giro_d, Integer id_beneficiario, String tipo_actualizacion, BigDecimal valorbd, Integer id_carga, String conArchivo) {
		retiro = RetiroConsultaServicio.getInstance().obtenerRetirosRespuesta(id_carga, id_banco_destino, id_beneficiario, id_banco_girador, respuesta_banco, tipo_actualizacion, valorbd, fecha_giro_d, "id_carga", conArchivo, 1000000, 1);
	}

	public Object getFieldValue(JRField field) throws JRException {

		try {
			return retiro.get(pos).get(field.getName().toUpperCase());
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	public boolean next() throws JRException {

		pos++;

		return pos < retiro.size();
	}
}
