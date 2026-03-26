package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.ReporteDinamico;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ReporteServicio;
import com.osmosyscol.datasuite.reportedim.builder.ServicioReporteDimBuilder;
import com.osmosyscol.datasuite.reportedim.dto.Parametro;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ReporteDimServicio {

	private static ReporteDimServicio instance;

	public static ReporteDimServicio getInstance() {
		if (instance == null)
			instance = new ReporteDimServicio();
		return instance;
	}

	private ReporteDimServicio() {
	}

	@SuppressWarnings("unchecked")
	public ResultadoConsulta obtenerDatosReporte(String idReporteUsuario, Session session, Integer numPagina) {

		try {

			Map<String, Object> mapReporte = obtenerMapReporte(idReporteUsuario, session);

			ServicioReporteDim servicioReporteDim = (ServicioReporteDim) mapReporte.get(REPORTE_SERVICIO);
			Map<String, Object> parametrosReporte = (Map<String, Object>) mapReporte.get(REPORTE_PARAMETROS);

			ResultadoConsulta datos = RDServicio.getInstance().ejecutarServicio(servicioReporteDim, parametrosReporte, numPagina);
			return datos;

		} catch (Exception e) {
			SimpleLogger.setError("Error obteniendo el reporte de usuario " + idReporteUsuario, e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public ResultadoConsulta obtenerDatosReporte(String idReporteUsuario, Session session) {

		try {
			Map<String, Object> mapReporte = obtenerMapReporte(idReporteUsuario, session);

			ServicioReporteDim servicioReporteDim = (ServicioReporteDim) mapReporte.get(REPORTE_SERVICIO);
			Map<String, Object> parametrosReporte = (Map<String, Object>) mapReporte.get(REPORTE_PARAMETROS);

			ResultadoConsulta datos = RDServicio.getInstance().ejecutarServicio(servicioReporteDim, parametrosReporte, 1, Integer.MAX_VALUE);
			return datos;

		} catch (Exception e) {
			SimpleLogger.setError("Error obteniendo el reporte de usuario " + idReporteUsuario, e);
		}

		return null;
	}
	
	public ResultadoConsulta obtenerDatosReporte(Map<String, Object> parametrosReporte) {

		try {
			
			String id_reporte = (String) parametrosReporte.get("id_reporte");
			ServicioReporteDim reporte = ReporteServicio.getInstance().obtenerReporte(id_reporte);
			
			ResultadoConsulta datos = RDServicio.getInstance().ejecutarServicio(reporte, parametrosReporte, 1, Integer.MAX_VALUE);
			return datos;

		} catch (Exception e) {
			SimpleLogger.setError("Error obteniendo el reporte de usuario " , e);
		}

		return null;
	}

	public ResultadoConsulta obtenerDatosReporte(String idReporteUsuario, String parametros, Session session) {

		try {
			Map<String, Object> mapReporte = obtenerMapReporte(idReporteUsuario, session);

			ServicioReporteDim servicioReporteDim = (ServicioReporteDim) mapReporte.get(REPORTE_SERVICIO);

			Map<String, String> mapParametros = obtenerMapParametros(parametros);

			Map<String, Object> valoresParametros = new HashMap<String, Object>();

			List<ParametroReporte> parametrosReporte = servicioReporteDim.getParametros();

			if (parametrosReporte != null) {
				for (ParametroReporte parametro : parametrosReporte) {
					Object valor = convertirDato(parametro, mapParametros.get(parametro.getNombre()));
					valoresParametros.put(parametro.getNombre(), valor);
				}
			}

			agregarParametrosSesion(session, servicioReporteDim, valoresParametros);

			mapReporte.put(REPORTE_PARAMETROS, valoresParametros);

			ResultadoConsulta datos = RDServicio.getInstance().ejecutarServicio(servicioReporteDim, valoresParametros, 1, Integer.MAX_VALUE);
			return datos;

		} catch (Exception e) {
			SimpleLogger.setError("Error obteniendo el reporte de usuario " + idReporteUsuario, e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> obtenerMapReporte(String idReporteUsuario, Session session) {

		Object objectReportes = session.getAttribute(REPORTES);

		Map<String, Map<String, Object>> mapReportes = null;

		if (objectReportes != null) {
			mapReportes = (Map<String, Map<String, Object>>) objectReportes;
		} else {
			return null;
		}

		Map<String, Object> mapReporte = mapReportes.get(idReporteUsuario);
		return mapReporte;
	}

	public ReporteDinamico obtenerReporteDinamico(String idReporteUsuario, Session session) {

		try {

			Map<String, Object> mapReporte = obtenerMapReporte(idReporteUsuario, session);
			return (ReporteDinamico) mapReporte.get(REPORTE_DINAMICO);

		} catch (Exception e) {
		}

		return null;
	}

	public ReporteDinamico crearReporte(String id, Request request, Session session) {

		if (StringUtils.esVacio(id)) {
			return null;
		}

		try {

			ServicioReporteDim servicioReporteDim = ServicioReporteDimBuilder.getCopiaServicio(id);

			if (servicioReporteDim != null) {

				Map<String, Object> valoresParametros = new HashMap<String, Object>();

				List<ParametroReporte> parametrosReporte = servicioReporteDim.getParametros();

				if (parametrosReporte != null) {
					for (ParametroReporte parametro : parametrosReporte) {
						Object valor = convertirDato(parametro, request.getParameter(parametro.getNombre()));
						valoresParametros.put(parametro.getNombre(), valor);
					}
				}

				return crearReporteDinamico(session, servicioReporteDim, valoresParametros, parametrosReporte);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error creando el reporte " + id, e);
		}

		return null;

	}

	public ReporteDinamico crearSubReporte(String id, String parametros, Session session) {

		Map<String, String> map = obtenerMapParametros(parametros);
		return crearSubReporte(id, map, session);

	}

	private Map<String, String> obtenerMapParametros(String parametros) {
		Map<String, String> map = new HashMap<String, String>();

		if (!StringUtils.esVacio(parametros)) {

			String[] pares = parametros.split(",");

			for (String par : pares) {
				String pareja[] = par.split(":");
				if (pareja.length == 2) {
					map.put(pareja[0], pareja[1]);
				}
			}
		}

		return map;
	}

	public ReporteDinamico crearSubReporte(String id, Map<String, String> parametros, Session session) {

		try {

			ServicioReporteDim servicioReporteDim = ServicioReporteDimBuilder.getCopiaServicio(id);

			if (servicioReporteDim != null) {

				Map<String, Object> valoresParametros = new HashMap<String, Object>();

				List<ParametroReporte> parametrosReporte = servicioReporteDim.getParametros();

				if (parametrosReporte != null) {
					for (ParametroReporte parametro : parametrosReporte) {
						Object valor = convertirDato(parametro, parametros.get(parametro.getNombre()));
						valoresParametros.put(parametro.getNombre(), valor);
					}
				}

				agregarParametrosSesion(session, servicioReporteDim, valoresParametros);

				return crearReporteDinamico(session, servicioReporteDim, valoresParametros, parametrosReporte);

			}
		} catch (Exception e) {
			SimpleLogger.setError("Error creando el reporte " + id, e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public ReporteDinamico crearReporteDinamico(Session session, ServicioReporteDim servicioReporteDim, Map<String, Object> valoresParametros, List<ParametroReporte> parametrosReporte) {
		Map<String, Map<String, Object>> mapReportes = null;

		Object objectReportes = session.getAttribute(REPORTES);

		if (objectReportes != null) {
			mapReportes = (Map<String, Map<String, Object>>) objectReportes;
		} else {
			mapReportes = new HashMap<String, Map<String, Object>>();
		}

		agregarParametrosSesion(session, servicioReporteDim, valoresParametros);

		String idReporte = System.currentTimeMillis() + "" + StringUtils.randomString(5);

		session.setAttribute(REPORTES, mapReportes);

		ReporteDinamico reporteDinamico = new ReporteDinamico();
		reporteDinamico.setAccionfila(servicioReporteDim.getAccionfila());

		reporteDinamico.setNavegacion(servicioReporteDim.getNavegacion());
		reporteDinamico.setTitulo(servicioReporteDim.getNombre());
		reporteDinamico.setResultados(servicioReporteDim.getResultados());
		reporteDinamico.setIdReporte(idReporte);
		reporteDinamico.setValoresParametros(valoresParametros);
		reporteDinamico.setParametros(parametrosReporte);
		reporteDinamico.setNombre(servicioReporteDim.getId());
		String intervaloActualizacion = ParametrosInicio.getProperty("Reporte.intervaloAct");

		if (NumberUtils.isNumber(intervaloActualizacion)) {
			reporteDinamico.setIntervalo_actualizacion(new Integer(intervaloActualizacion) * 1000);
		}

		Map<String, Object> mapReporte = new HashMap<String, Object>();
		mapReporte.put(REPORTE_SERVICIO, servicioReporteDim);
		mapReporte.put(REPORTE_PARAMETROS, valoresParametros);
		mapReporte.put(REPORTE_DINAMICO, reporteDinamico);

		mapReportes.put(idReporte, mapReporte);
		return reporteDinamico;
	}

	public Object convertirDato(ParametroReporte parametro, String param) {

		Object objeto = null;
		if (param == null) {
			return null;
		}
		
		param = param.replace("\r","").replace("\n","");

		if (param == null || "null".equals(param) || param.length() == 0) {
			return null;
		}
		
		if (Parametro.TIPO_STRING.equalsIgnoreCase(parametro.getTipo())) {
			objeto = param;
		} else if (Parametro.TIPO_DATE.equalsIgnoreCase(parametro.getTipo())) {
			objeto = StringUtils.toDate(param);
		} else if (Parametro.TIPO_DOUBLE.equalsIgnoreCase(parametro.getTipo()) || Parametro.TIPO_FLOAT.equalsIgnoreCase(parametro.getTipo())) {
			objeto = new BigDecimal(param);
		} else if (Parametro.TIPO_INT.equalsIgnoreCase(parametro.getTipo())) {
			objeto = new BigInteger(param);
		} else if (Parametro.TIPO_BOOLEAN.equalsIgnoreCase(parametro.getTipo())) {
			objeto = StringUtils.esVerdad(param);
		}
		return objeto;

	}


	@SuppressWarnings("unchecked")
	public Boolean obtenerReporteExcel(String idReporteUsuario, Object objectReportes, String nombreArchivo) {

		try {
			Map<String, Map<String, Object>> mapReportes = null;

			if (objectReportes != null) {
				mapReportes = (Map<String, Map<String, Object>>) objectReportes;
			} else {
				return false;
			}

			Map<String, Object> mapReporte = mapReportes.get(idReporteUsuario);

			ServicioReporteDim servicioReporteDim = (ServicioReporteDim) mapReporte.get(REPORTE_SERVICIO);
			Map<String, Object> parametrosReporte = (Map<String, Object>) mapReporte.get(REPORTE_PARAMETROS);

			ResultadoConsulta datos = RDServicio.getInstance().ejecutarServicio(servicioReporteDim, parametrosReporte, 0, Integer.MAX_VALUE);

			crearExcel(servicioReporteDim, datos, nombreArchivo);
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error obteniendo el reporte de usuario " + idReporteUsuario, e);
		}
		return false;

	}

	public static Boolean crearExcel(ServicioReporteDim servicioReporteDim, ResultadoConsulta resultadoConsulta, String nombreArchivo) {

		try {

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFCellStyle headStyle = wb.createCellStyle();

			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headStyle.setFont(font);

			HSSFSheet newsheet = wb.createSheet(servicioReporteDim.getNombre());

			HSSFRow row0 = newsheet.createRow((short) 0);

			List<Resultado> resultados = servicioReporteDim.getResultados();
			Collections.sort(resultados, new ComparadorResultado());

			for (int i = 0; i < resultados.size(); i++) {
				HSSFCell cell0i = row0.createCell((short) i);
				HSSFRichTextString idPrueba = new HSSFRichTextString(resultados.get(i).getTitulo());
				cell0i.setCellValue(idPrueba);
				cell0i.setCellStyle(headStyle);
			}

			List<Map<String, Object>> datos = resultadoConsulta.getDatos();

			int i = 0;
			for (Map<String, Object> map : datos) {
				i++;
				HSSFRow rowi = newsheet.createRow((short) i);

				for (int j = 0; j < resultados.size(); j++) {

					HSSFCell cell0i = rowi.createCell((short) j);

					Object valor = map.get(resultados.get(j).getNombre());
					String valorstr = "";
					if (valor != null) {
						valorstr = valor.toString();
					}
					HSSFRichTextString idPrueba = new HSSFRichTextString(valorstr);

					cell0i.setCellValue(idPrueba);

				}

			}
			FileOutputStream fileOutputStream = new FileOutputStream(new File(nombreArchivo));
			wb.write(fileOutputStream);
			fileOutputStream.close();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error generando excel", e);
		}

		return false;

	}

	@SuppressWarnings("rawtypes")
	public void agregarParametrosSesion(Session session, ServicioReporteDim servicioReporteDim, Map<String, Object> mapParams) {

		if (servicioReporteDim.getParametros() == null) {
			servicioReporteDim.setParametros(new ArrayList<ParametroReporte>());
		}

		List<ParametroReporte> listaParams = servicioReporteDim.getParametros();

		for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {

			String param = (String) e.nextElement();
			Object atributo = session.getAttribute(param);

			if (atributo != null && (servicioReporteDim.getConsulta().contains("#" + param + "#") || servicioReporteDim.getConsulta().contains("#:" + param + "#"))) {

				if (atributo instanceof String) {
					mapParams.put(param, (String) atributo);
					mapParams.put(":" + param, atributo + "");

					ParametroReporte parametro = new ParametroReporte();
					parametro.setTipo("string");
					parametro.setEncriptado("N");
					parametro.setNombre(param);
					

					if(!listaParams.contains(parametro))
						listaParams.add(parametro);

					ParametroReporte parametro2 = new ParametroReporte();
					parametro2.setTipo("string");
					parametro2.setEncriptado("S");
					parametro2.setNombre(":" + param);

					if(!listaParams.contains(parametro2))
						listaParams.add(parametro2);
				}

				if (atributo instanceof Integer) {

					mapParams.put(param, atributo);
					mapParams.put(":" + param, atributo);

					ParametroReporte parametro = new ParametroReporte();
					parametro.setTipo("integer");
					parametro.setEncriptado("N");
					parametro.setNombre(param);

					if(!listaParams.contains(parametro))
						listaParams.add(parametro);

					ParametroReporte parametro2 = new ParametroReporte();
					parametro2.setTipo("integer");
					parametro2.setEncriptado("S");
					parametro2.setNombre(":" + param);

					if(!listaParams.contains(parametro2))
						listaParams.add(parametro2);
				}
			}

		}

	}

	public static final String REPORTE_SERVICIO = "SERVICIO";

	public static final String REPORTE_PARAMETROS = "PARAMETROS";

	public static final String REPORTE_DINAMICO = "DINAMICO";

	public static final String REPORTES = "REPORTES";

}
