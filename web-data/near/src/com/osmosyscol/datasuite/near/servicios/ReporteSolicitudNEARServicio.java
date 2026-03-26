package com.osmosyscol.datasuite.near.servicios;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsulta;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaConf;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaRol;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.HistorialConsultaServicio;
import com.osmosyscol.datasuite.near.dtos.SolicitudNEAR;
import com.osmosyscol.datasuite.persistencia.dao.RolDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

import co.htsoft.commons.file.FileUtils;

public class ReporteSolicitudNEARServicio {

	private static ReporteSolicitudNEARServicio service;
	private static String dblink;

	private ReporteSolicitudNEARServicio() {
		if (StringUtils.esVacio(dblink)) {
			dblink = ConfiguracionServicio.getInstance()
					.obtenerValorByEtiqueta("DBLINK_DATASUITE");
		}
	}

	public static ReporteSolicitudNEARServicio getInstance() {
		if (service == null) {
			service = new ReporteSolicitudNEARServicio();
		}
		return service;
	}

	public Integer obtenerTotalSolicitudes(Map<String, String> params,
			Integer id_usuario) {
		String sql = "SELECT count (*) FROM V_SOLICITUD_UNION S "
				+ obtenerFiltros(params, id_usuario);
		return DS_SqlUtils.count(sql);
	}

	public List<SolicitudNEAR> obtenerSolicitudes(Map<String, String> params,
			Integer pagina_actual, Integer tamano_pagina, Integer id_usuario) {
		String sql = "SELECT * FROM V_SOLICITUD_UNION S "
				+ obtenerFiltros(params, id_usuario);
		return DS_SqlUtils.queryForList(SolicitudNEAR.class, sql,
				tamano_pagina, pagina_actual);
	}

	public List<Map<String, Object>> obtenerSolicitudesMap(
			Map<String, String> params, Integer pagina_actual,
			Integer tamano_pagina, Integer id_usuario, boolean excel) {
		String sql = "SELECT "
				+ (excel == false ? obtenerFiltroColumnas()
						: obtenerFiltroColumnasExcel())
				+ " FROM V_SOLICITUD_UNION S "
				+ obtenerFiltros(params, id_usuario) + "ORDER BY ID_CARGA DESC";
		//
		try {
			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);
			if (pagina_actual != null && tamano_pagina != null) {
				return SQLServicio.desencriptarMapaStringFormat(sqlDao
						.selectSQLPaginado(sql, pagina_actual, tamano_pagina));
			} else {
				return SQLServicio.desencriptarMapaStringFormat(sqlDao
						.selectSQL(sql));
			}

			// return historialConsultaDao.obtenerHistorial(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// return DS_SqlUtils.queryForList(HashMap.class, sql,
		// tamano_pagina, pagina_actual);
	}

	public List<Map<String, Object>> obtenerSolicitudesMapParametro(
			String parametro, Map<String, String> params, Integer id_usuario) {
		String sql = "SELECT DISTINCT S." + parametro
				+ " FROM V_SOLICITUD_UNION S "
				+ obtenerFiltros(params, id_usuario);
		//
		try {
			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);
			return SQLServicio.desencriptarMapaStringFormat(sqlDao
					.selectSQL(sql));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// return DS_SqlUtils.queryForList(HashMap.class, sql,
		// tamano_pagina, pagina_actual);
	}

	public void regenerarVista() {
		if (StringUtils.esNoVacio(dblink)) {

			SimpleLogger.setInfo("Regenerando vista V_SOLICITUD_UNION");
			// consultar vista
			List<HistorialConsulta> listaConsulta = HistorialConsultaServicio
					.getInstance().obtenerConsultasId(1);
			String consulta = listaConsulta.get(0).getConsulta();
			String sqlJoinViews = "CREATE OR REPLACE VIEW V_SOLICITUD_UNION AS ( "
					+ consulta + " )";

			DS_SqlUtils.update(sqlJoinViews);

		} else {
			SimpleLogger
					.setError("No se puede crear/recrear la vista porque no se encuentra la propiedad DBLINK_DATASUITE");
		}
	}
	
	public void regenerarVistaHistorico() {
		if (StringUtils.esNoVacio(dblink)) {

			SimpleLogger.setInfo("Regenerando vista V_HISTORICO_CLIENTE");
			// consultar vista
			List<HistorialConsulta> listaConsulta = HistorialConsultaServicio
					.getInstance().obtenerConsultasId(2);
			String consulta = listaConsulta.get(0).getConsulta();
			String sqlJoinViews = "CREATE OR REPLACE VIEW V_HISTORICO_CLIENTE AS ( "
					+ consulta + " )";

			DS_SqlUtils.update(sqlJoinViews);

		} else {
			SimpleLogger
					.setError("No se puede crear/recrear la vista porque no se encuentra la propiedad DBLINK_DATASUITE");
		}
	}

	public List<ValorLista> obtenerInstancias() {
		try {
			String sql = "select distinct i.id_instancia, i.nombre "
					+ "  from $SOLICITUD NEAR SOCIEDAD$ s "
					+ " inner join dst_carga_instancia@" + dblink + " ci "
					+ "    on s.idcarga = ci.id_carga "
					+ " inner join dst_instancia@" + dblink + " i "
					+ "    on i.id_instancia = ci.id_instancia "
					+ " order by i.nombre ";
			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			sql = RDServicio.reemplazarNombres(sql);

			List<Map<String, Object>> data = null;
			data = SQLServicio.desencriptarMapaStringFormat(sqlDao
					.selectSQL(sql));

			List<ValorLista> result = new ArrayList<ValorLista>();

			Estado estado = EstadoCargaServicio.getInstance().obtenerEstado(
					Constantes.CARGA_ESTADO_APROBADO);

			ValorLista valor = new ValorLista();
			valor.setId(estado.getEstado());
			valor.setValueid(estado.getEstado());
			valor.setNombre(estado.getNombre_interno());
			result.add(valor);

			for (Map<String, Object> reg : data) {
				valor = new ValorLista();
				valor.setId(StringUtils.toString(reg.get("ID_INSTANCIA")));
				valor.setValueid(reg.get("ID_INSTANCIA"));
				valor.setNombre(StringUtils.toString(reg.get("NOMBRE")));
				result.add(valor);
			}

			estado = EstadoCargaServicio.getInstance().obtenerEstado(
					Constantes.CARGA_ESTADO_RECHAZADO);

			valor = new ValorLista();
			valor.setId(estado.getEstado());
			valor.setValueid(estado.getEstado());
			valor.setNombre(estado.getNombre_interno());
			result.add(valor);

			return result;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	private String obtenerFiltroColumnas() {
		String filtroColumnas = "";
		// consultar obtenerConsultasConf
		List<HistorialConsultaConf> listaConsulta = HistorialConsultaServicio
				.getInstance().obtenerConsultasConf();
		if (listaConsulta != null && listaConsulta.size() > 0) {
			for (int i = 0; i < listaConsulta.size(); i++) {
				if (listaConsulta.get(i).getMostrar() != null
						&& listaConsulta.get(i).getMostrar() == 1) {
					filtroColumnas += " "
							+ listaConsulta.get(i).getNombre_columna()
							+ " as \""
							+ listaConsulta.get(i).getEtiqueta_columna()
							+ "\",";
				}

			}
			char compareChar = filtroColumnas
					.charAt(filtroColumnas.length() - 1);
			if (String.valueOf(compareChar).equals(",")) {
				filtroColumnas = filtroColumnas.substring(0,
						filtroColumnas.length() - 1);
			}
			return filtroColumnas;
		}
		return "*";
	}

	private String obtenerFiltroColumnasExcel() {
		String filtroColumnas = "";
		// consultar obtenerConsultasConf
		List<HistorialConsultaConf> listaConsulta = HistorialConsultaServicio
				.getInstance().obtenerConsultasConf();
		if (listaConsulta != null && listaConsulta.size() > 0) {
			for (int i = 0; i < listaConsulta.size(); i++) {
				if (listaConsulta.get(i).getExcel() != null
						&& listaConsulta.get(i).getExcel() == 1) {
					filtroColumnas += " "
							+ listaConsulta.get(i).getNombre_columna()
							+ " as \""
							+ listaConsulta.get(i).getEtiqueta_columna()
							+ "\",";
				}

			}
			char compareChar = filtroColumnas
					.charAt(filtroColumnas.length() - 1);
			if (String.valueOf(compareChar).equals(",")) {
				filtroColumnas = filtroColumnas.substring(0,
						filtroColumnas.length() - 1);
			}
			return filtroColumnas;
		}
		return "*";
	}

	private String obtenerFiltrosColumnas(Integer id_usuario) {
		List<Rol> roles = obtenerRolesAdministrativo(id_usuario);
		List<HistorialConsultaRol> listaConfRol = HistorialConsultaServicio
				.getInstance().obtenerConsultasRol();
		String sql = "";
		if (roles.size() > 0 && listaConfRol.size() > 0) {
			sql = "AND (";
			Integer contador = 0;
			for (int i = 0; i < roles.size(); i++) {
				for (int j = 0; j < listaConfRol.size(); j++) {
					if (roles.get(i).getId_rol() == Integer
							.parseInt(listaConfRol.get(j).getRol())) {
						String encriptado = Crypto.E(listaConfRol.get(j)
								.getValor());
						sql += " S." + listaConfRol.get(j).getNombre_columna()
								+ " = '" + encriptado + "' OR";
						contador++;
					}
				}
			}
			if (contador == 0) {
				sql = "";
			} else {
				sql = sql.substring(0, sql.length() - 2);// quitando or del
															// final
				sql += ")";
			}

		}
		return sql;
	}

	private String obtenerFiltros(Map<String, String> params, Integer id_usuario) {
		List<Rol> roles = obtenerRolesAdministrativo(id_usuario);

		// int intendenciaGAdmisiones = 0;
		// int intendenciaBarranquilla = 0;
		// int intendenciaCali = 0;
		// int intendenciaCartagena = 0;
		// int todasLasIntendencias = 0;
		//
		// for(int i=0; i<roles.size(); i++){
		// if(roles.get(i).getNombre_rol().equalsIgnoreCase("Ponente Bogot�") ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Juez Seccional Central")
		// ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Ponente de Reparto")
		// ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Supervisor") ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Secretar�a Delegatura")
		// ){
		// intendenciaGAdmisiones ++;
		// }else
		// if(roles.get(i).getNombre_rol().equalsIgnoreCase("Ponente Senior Barranquilla")
		// ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Juez Intendencia Barranquilla")){
		// intendenciaBarranquilla++;
		// }else
		// if(roles.get(i).getNombre_rol().equalsIgnoreCase("Juez Intendencia Cali")
		// ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Ponente Senior Cali")){
		// intendenciaCali++;
		// }else
		// if(roles.get(i).getNombre_rol().equalsIgnoreCase("Ponente Senior Cartagena")
		// ||
		// roles.get(i).getNombre_rol().equalsIgnoreCase("Juez Intendencia Cartagena")){
		// intendenciaCartagena++;
		// }else
		// if(roles.get(i).getNombre_rol().equalsIgnoreCase("Todas las intendencias")){
		// todasLasIntendencias++;
		// }
		// }
		//
		//
		//
		String sql = " WHERE 1 = 1 ";
		sql += obtenerFiltrosColumnas(id_usuario);
		//
		// if(todasLasIntendencias>0){
		// intendenciaGAdmisiones = 0;
		// intendenciaBarranquilla = 0;
		// intendenciaCali = 0;
		// intendenciaCartagena = 0;
		// }
		//
		// if(intendenciaBarranquilla>0){
		// String encriptado = Crypto.E("Intendencia Barranquilla");
		// sql += " AND S.INTENDENCIA = '" + encriptado+"'";
		// }
		//
		// if(intendenciaCali>0){
		// String encriptado = Crypto.E("Intendencia Cali");
		// sql += " AND S.INTENDENCIA = '" + encriptado+"'";
		// }
		//
		// if(intendenciaCartagena>0){
		// String encriptado = Crypto.E("Intendencia Cartagena");
		// sql += " AND S.INTENDENCIA = '" + encriptado+"'";
		// }
		//
		// if(intendenciaGAdmisiones>0){
		// String encriptado = Crypto.E("Grupo de admisiones");
		// sql += " AND S.INTENDENCIA = '" + encriptado+"'";
		// }

		if (StringUtils.esNoVacio(params.get("radicado"))) {
			String encriptado = Crypto.E(params.get("radicado"));
			sql += " AND S.RADICADO = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("estado"))) {
			String encriptado = Crypto.E(params.get("estado"));
			sql += " AND S.ESTADO_NOMBRE = '" + params.get("estado") + "'";
		}

		if (StringUtils.esNoVacio(params.get("fecha_solicitud"))) {
			sql += " AND TRUNC(S.FECHA_SOLICITUD) = to_date('"
					+ params.get("fecha_solicitud") + "', 'DD/MM/YYYY')";
		}

		if (StringUtils.esNoVacio(params.get("solicitante"))) {
			String encriptado = Crypto.E(params.get("solicitante"));
			sql += " AND S.SOLICITANTE = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("tipo_solicitante"))) {
			String encriptado = Crypto.E(params.get("tipo_solicitante"));
			sql += " AND S.TIPO_SOLICITANTE = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("fecha_radicacion"))
				&& StringUtils.esNoVacio(params.get("fecha_radicacion_fin"))) {
			sql += " AND TRUNC(S.FECHA_RADICADO) >= to_date('"
					+ params.get("fecha_radicacion")
					+ "', 'DD/MM/YYYY') AND TRUNC(S.FECHA_RADICADO) <= to_date('"
					+ params.get("fecha_radicacion_fin") + "', 'DD/MM/YYYY')";
		}

		if (StringUtils.esNoVacio(params.get("fecha_radicacion"))
				&& !StringUtils.esNoVacio(params.get("fecha_radicacion_fin"))) {
			sql += " AND TRUNC(S.FECHA_RADICADO) >= to_date('"
					+ params.get("fecha_radicacion") + "', 'DD/MM/YYYY') ";
		}

		if (StringUtils.esNoVacio(params.get("fecha_radicacion_fin"))
				&& !StringUtils.esNoVacio(params.get("fecha_radicacion"))) {
			sql += " AND TRUNC(S.FECHA_RADICADO) <= to_date('"
					+ params.get("fecha_radicacion_fin") + "', 'DD/MM/YYYY') ";
		}

		// if (StringUtils.esNoVacio(params.get("fecha_radicacion"))) {
		// sql += " AND TRUNC(S.FECHA_RADICADO) = to_date('"
		// + params.get("fecha_radicacion") + "', 'DD/MM/YYYY')";
		// }

		if (StringUtils.esNoVacio(params.get("identificacion"))) {
			String encriptado = Crypto.E(params.get("identificacion"));
			String encriptado_int = "";
			
			Integer identificacion_int = null;
			
			try {
				identificacion_int = Integer.parseInt(params.get("identificacion"));
			} catch (Exception e) {}
			
			if (identificacion_int != null) {
				encriptado_int = Crypto.E(identificacion_int);
				sql += " AND (S.IDENTIFICACION = '" + encriptado + "' OR S.IDENTIFICACION = '" + encriptado_int + "')";
			} else {
				sql += " AND S.IDENTIFICACION = '" + encriptado + "'";				
			}
		}

		if (StringUtils.esNoVacio(params.get("categoria"))) {
			String encriptado = Crypto.E(params.get("categoria"));
			sql += " AND S.CATEGORIA = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("id_instancia"))) {
			String encriptado = Crypto.E(params.get("id_instancia"));
			sql += " AND S.ID_INSTANCIA = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("intendencia"))) {
			String encriptado = Crypto.E(params.get("intendencia"));
			sql += " AND S.INTENDENCIA = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("id_solicitud"))) {
			sql += " AND S.ID_CARGA = " + params.get("id_solicitud") + "";
		}

		if (StringUtils.esNoVacio(params.get("proceso"))) {
			String encriptado = Crypto.E(params.get("proceso"));
			sql += " AND S.TIPO_SOLICITUD = '" + encriptado + "'";
		}

		if (StringUtils.esNoVacio(params.get("tramite"))) {
			String encriptado = Crypto.E(params.get("tramite"));
			sql += " AND S.TRAMITE = '" + params.get("tramite") + "'";
		}
		
		if (StringUtils.esNoVacio(params.get("responsable"))) {
			sql += " AND S.ID_CARGA in ( "
				+ "	 select ci.id_carga from dst_carga_responsable@to_datasuite cr "
				+ " inner join dst_carga_instancia@to_datasuite ci on ci.id_instancia = cr.id_instancia and ci.id_carga = cr.id_carga and ci.fecha_salida is null where cr.id_persona = " + params.get("responsable") 
			+ ") ";
		}

		return sql;
	}

	public File generarReporteMap(Map<String, String> params, Integer id_usuario) {
		List<Map<String, Object>> registros = obtenerSolicitudesMap(params,
				null, null, id_usuario, true);
		List<HistorialConsultaConf> excelConf = HistorialConsultaServicio
				.getInstance().obtenerConsultasConfExcel();
		if (registros != null) {
			XSSFWorkbook wb = new XSSFWorkbook();

			XSSFSheet hoja = wb.createSheet("Solicitudes");

			hoja.setDisplayGridlines(false);

			XSSFCellStyle hstyle = wb.createCellStyle();
			XSSFCellStyle rstyle = wb.createCellStyle();
			XSSFCellStyle datestyle = wb.createCellStyle();
			XSSFCellStyle numstyle = wb.createCellStyle();
			XSSFCellStyle moneystyle = wb.createCellStyle();

			XSSFColor headerColor = new XSSFColor(new Color(58, 97, 198));

			XSSFFont fbold = wb.createFont();
			fbold.setBold(true);
			fbold.setColor(IndexedColors.WHITE.index);

			{

				CreationHelper createHelper = wb.getCreationHelper();

				hstyle.setFillForegroundColor(headerColor);
				hstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				hstyle.setBorderBottom(BorderStyle.THIN);
				hstyle.setBorderRight(BorderStyle.THIN);
				hstyle.setBorderTop(BorderStyle.THIN);
				hstyle.setBorderLeft(BorderStyle.THIN);

				hstyle.setAlignment(HorizontalAlignment.CENTER);
				hstyle.setVerticalAlignment(VerticalAlignment.CENTER);
				hstyle.setFont(fbold);
				hstyle.setBorderBottom(BorderStyle.THIN);
				hstyle.setBorderRight(BorderStyle.THIN);
				hstyle.setBorderTop(BorderStyle.THIN);
				hstyle.setBorderLeft(BorderStyle.THIN);

				rstyle.setVerticalAlignment(VerticalAlignment.CENTER);
				rstyle.setBorderBottom(BorderStyle.THIN);
				rstyle.setBorderRight(BorderStyle.THIN);
				rstyle.setBorderTop(BorderStyle.THIN);
				rstyle.setBorderLeft(BorderStyle.THIN);

				datestyle.setDataFormat(createHelper.createDataFormat()
						.getFormat("dd/mm/yyyy hh:mm:ss"));
				datestyle.setBorderBottom(BorderStyle.THIN);
				datestyle.setBorderRight(BorderStyle.THIN);
				datestyle.setBorderTop(BorderStyle.THIN);
				datestyle.setBorderLeft(BorderStyle.THIN);

				numstyle.setDataFormat((short) 4);
				numstyle.setBorderBottom(BorderStyle.THIN);
				numstyle.setBorderRight(BorderStyle.THIN);
				numstyle.setBorderTop(BorderStyle.THIN);
				numstyle.setBorderLeft(BorderStyle.THIN);

				moneystyle.setDataFormat((short) 8);
				moneystyle.setBorderBottom(BorderStyle.THIN);
				moneystyle.setBorderRight(BorderStyle.THIN);
				moneystyle.setBorderTop(BorderStyle.THIN);
				moneystyle.setBorderLeft(BorderStyle.THIN);
			}

			XSSFRow fila = hoja.createRow(0);
			XSSFCell c = null;
			List<String> listadoCampos = new ArrayList<String>();
			for (int i = 0; i < excelConf.size(); i++) {
				listadoCampos.add(excelConf.get(i).getEtiqueta_columna());
			}

			for (int i = 0; i < listadoCampos.size(); i++) {
				c = fila.createCell(i);
				c.setCellValue(listadoCampos.get(i));
				c.setCellStyle(hstyle);
			}

			for (int i = 0; i < registros.size(); i++) {

				fila = hoja.createRow(i + 1);

				for (int j = 0; j < excelConf.size(); j++) {

					c = fila.createCell(j);
					c.setCellValue(StringUtils.trimToEmpty((registros.get(i)
							.get(excelConf.get(j).getEtiqueta_columna())) != null ? (registros
							.get(i).get(excelConf.get(j).getEtiqueta_columna()))
							.toString()
							: "-"));
					c.setCellStyle(rstyle);
					System.out.println("imprimiendo valores reporte columna");
					System.out.println(registros.get(i).get(
							excelConf.get(j).getEtiqueta_columna()));

				}
			}

			for (int i = 0; i < listadoCampos.size(); i++) {
				hoja.autoSizeColumn(i);
			}

			try {
				File file = FileUtils.newFile("xlsx");
				FileOutputStream fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				wb.close();

				return file;
			} catch (Exception e) {
				e.printStackTrace();
				SimpleLogger.setError(
						"Ha ocurrido un error generando el excel:", e);

				return null;
			}
		}
		SimpleLogger.setError("Ha ocurrido un error generando el excel");
		return null;

	}

	public File generarReporte(Map<String, String> params) {

		List<SolicitudNEAR> registros = obtenerSolicitudes(params, null, null,
				null);

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet hoja = wb.createSheet("Solicitudes");

		hoja.setDisplayGridlines(false);

		XSSFCellStyle hstyle = wb.createCellStyle();
		XSSFCellStyle rstyle = wb.createCellStyle();
		XSSFCellStyle datestyle = wb.createCellStyle();
		XSSFCellStyle numstyle = wb.createCellStyle();
		XSSFCellStyle moneystyle = wb.createCellStyle();

		XSSFColor headerColor = new XSSFColor(new Color(58, 97, 198));

		XSSFFont fbold = wb.createFont();
		fbold.setBold(true);
		fbold.setColor(IndexedColors.WHITE.index);

		{

			CreationHelper createHelper = wb.getCreationHelper();

			hstyle.setFillForegroundColor(headerColor);
			hstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			hstyle.setBorderBottom(BorderStyle.THIN);
			hstyle.setBorderRight(BorderStyle.THIN);
			hstyle.setBorderTop(BorderStyle.THIN);
			hstyle.setBorderLeft(BorderStyle.THIN);

			hstyle.setAlignment(HorizontalAlignment.CENTER);
			hstyle.setVerticalAlignment(VerticalAlignment.CENTER);
			hstyle.setFont(fbold);
			hstyle.setBorderBottom(BorderStyle.THIN);
			hstyle.setBorderRight(BorderStyle.THIN);
			hstyle.setBorderTop(BorderStyle.THIN);
			hstyle.setBorderLeft(BorderStyle.THIN);

			rstyle.setVerticalAlignment(VerticalAlignment.CENTER);
			rstyle.setBorderBottom(BorderStyle.THIN);
			rstyle.setBorderRight(BorderStyle.THIN);
			rstyle.setBorderTop(BorderStyle.THIN);
			rstyle.setBorderLeft(BorderStyle.THIN);

			datestyle.setDataFormat(createHelper.createDataFormat().getFormat(
					"dd/mm/yyyy hh:mm:ss"));
			datestyle.setBorderBottom(BorderStyle.THIN);
			datestyle.setBorderRight(BorderStyle.THIN);
			datestyle.setBorderTop(BorderStyle.THIN);
			datestyle.setBorderLeft(BorderStyle.THIN);

			numstyle.setDataFormat((short) 4);
			numstyle.setBorderBottom(BorderStyle.THIN);
			numstyle.setBorderRight(BorderStyle.THIN);
			numstyle.setBorderTop(BorderStyle.THIN);
			numstyle.setBorderLeft(BorderStyle.THIN);

			moneystyle.setDataFormat((short) 8);
			moneystyle.setBorderBottom(BorderStyle.THIN);
			moneystyle.setBorderRight(BorderStyle.THIN);
			moneystyle.setBorderTop(BorderStyle.THIN);
			moneystyle.setBorderLeft(BorderStyle.THIN);
		}

		XSSFRow fila = hoja.createRow(0);
		XSSFCell c = null;

		List<String> listadoCampos = new ArrayList<String>();
		listadoCampos.add("ID Solicitud");
		listadoCampos.add("Fecha Solicitud");
		listadoCampos.add("# Proceso");
		listadoCampos.add("# Radicación");
		listadoCampos.add("Fecha de Radicación");
		listadoCampos.add("Tipo de Solicitante");
		listadoCampos.add("Solicitante");
		listadoCampos.add("NIT/Identificación");
		listadoCampos.add("Municipio");
		listadoCampos.add("Departamento");
		listadoCampos.add("Número de Empleados");
		listadoCampos.add("Categoria");
		listadoCampos.add("Fecha de EF Mes Anterior");
		listadoCampos.add("Fecha de EF Ano Anterior");
		listadoCampos.add("Valor Activo al Mes Anterior");
		listadoCampos.add("Valor Pasivo al Mes Anterior");
		listadoCampos.add("Valor Activo al Año Anterior");
		listadoCampos.add("Valor Pasivo al Año Anterior");
		listadoCampos.add("CIIU");
		listadoCampos.add("Macrosector");
		listadoCampos.add("Supuestos de Insolvencia");
		listadoCampos.add("Ponente");
		listadoCampos.add("Intendencia");
		listadoCampos.add("Estado");
		listadoCampos.add("Tramite");

		for (int i = 0; i < listadoCampos.size(); i++) {
			c = fila.createCell(i);
			c.setCellValue(listadoCampos.get(i));
			c.setCellStyle(hstyle);
		}

		Integer nRow = 1;

		if (registros != null) {
			for (SolicitudNEAR reg : registros) {
				fila = hoja.createRow(nRow);

				c = fila.createCell(0);
				c.setCellValue(reg.getId_carga());
				c.setCellStyle(rstyle);

				c = fila.createCell(1);
				if (reg.getFecha_solicitud() != null) {
					c.setCellValue(reg.getFecha_solicitud());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(datestyle);

				c = fila.createCell(2);
				c.setCellValue(StringUtils.trimToEmpty(reg.getNumero_proceso()));
				c.setCellStyle(rstyle);

				c = fila.createCell(3);
				c.setCellValue(StringUtils.trimToEmpty(reg.getRadicado()));
				c.setCellStyle(rstyle);

				c = fila.createCell(4);
				if (reg.getFecha_radicado() != null) {
					c.setCellValue(reg.getFecha_radicado());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(datestyle);

				c = fila.createCell(5);
				c.setCellValue(StringUtils.trimToEmpty(reg
						.getTipo_solicitante()));
				c.setCellStyle(rstyle);

				c = fila.createCell(6);
				c.setCellValue(StringUtils.trimToEmpty(reg.getSolicitante()));
				c.setCellStyle(rstyle);

				c = fila.createCell(7);
				c.setCellValue(StringUtils.trimToEmpty(reg.getIdentificacion()));
				c.setCellStyle(rstyle);

				c = fila.createCell(8);
				c.setCellValue(StringUtils.trimToEmpty(reg.getMunicipio()));
				c.setCellStyle(rstyle);

				c = fila.createCell(9);
				c.setCellValue(StringUtils.trimToEmpty(reg.getDepartamento()));
				c.setCellStyle(rstyle);

				c = fila.createCell(10);
				c.setCellValue(reg.getTotal_trabajadores());
				c.setCellStyle(numstyle);

				c = fila.createCell(11);
				c.setCellValue(StringUtils.trimToEmpty(reg.getCategoria()));
				c.setCellStyle(rstyle);

				c = fila.createCell(12);
				if (reg.getFecha_ef() != null) {
					c.setCellValue(reg.getFecha_ef());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(datestyle);

				c = fila.createCell(13);
				if (reg.getFecha_ef() != null) {
					c.setCellValue(reg.getFecha_ef_anio());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(datestyle);

				c = fila.createCell(14);
				if (reg.getActivos_mes() != null) {
					c.setCellValue(reg.getActivos_mes().doubleValue());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(moneystyle);

				c = fila.createCell(15);
				if (reg.getPasivos_mes() != null) {
					c.setCellValue(reg.getPasivos_mes().doubleValue());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(moneystyle);

				c = fila.createCell(16);
				if (reg.getTotal_activos() != null) {
					c.setCellValue(reg.getTotal_activos().doubleValue());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(moneystyle);

				c = fila.createCell(17);
				if (reg.getTotal_pasivos() != null) {
					c.setCellValue(reg.getTotal_pasivos().doubleValue());
				} else {
					c.setCellValue("");
				}
				c.setCellStyle(moneystyle);

				c = fila.createCell(18);
				c.setCellValue(StringUtils.trimToEmpty(reg.getCiiu()));
				c.setCellStyle(rstyle);

				c = fila.createCell(19);
				c.setCellValue(StringUtils.trimToEmpty(reg.getMacrosector()));
				c.setCellStyle(rstyle);

				c = fila.createCell(20);
				c.setCellValue(StringUtils.trimToEmpty(reg
						.getSupuesto_insolvencia()));
				c.setCellStyle(rstyle);

				c = fila.createCell(21);
				c.setCellValue(StringUtils.trimToEmpty(reg.getPonente()));
				c.setCellStyle(rstyle);

				c = fila.createCell(22);
				c.setCellValue(StringUtils.trimToEmpty(reg.getIntendencia()));
				c.setCellStyle(rstyle);

				c = fila.createCell(23);
				c.setCellValue(StringUtils.trimToEmpty(reg.getEstado_nombre()));
				c.setCellStyle(rstyle);

				c = fila.createCell(24);
				c.setCellValue(StringUtils.trimToEmpty(reg.getTramite()));
				c.setCellStyle(rstyle);

				nRow++;
			}
		}

		for (int i = 0; i < listadoCampos.size(); i++) {
			hoja.autoSizeColumn(i);
		}

		try {
			File file = FileUtils.newFile("xlsx");
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			wb.close();

			return file;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}

	}

	public List<Rol> obtenerRolesAdministrativo(Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesPorPersona(id_administrativo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public List<Rol> obtenerRolesActivos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesActivos();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

}
