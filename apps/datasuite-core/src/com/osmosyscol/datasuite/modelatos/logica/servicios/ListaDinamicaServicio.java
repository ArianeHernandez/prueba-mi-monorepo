package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.commons.lang.math.NumberUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaDinamicaDao;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ListaDinamicaServicio {

	private static ListaDinamicaServicio listaDinamicaServicio;

	private ListaDinamicaServicio() {
	}

	public static ListaDinamicaServicio getInstance() {
		if (listaDinamicaServicio == null) {
			listaDinamicaServicio = new ListaDinamicaServicio();
		}
		return listaDinamicaServicio;
	}

	// ------------------------------

	public List<ListaDinamica> obtenerListasDinamicas() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaDao listaDinamicaDao = (ListaDinamicaDao) daoManager.getDao(ListaDinamicaDao.class);

			return listaDinamicaDao.obtenerListasDinamicas();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean guardarListaDinamica(ListaDinamica listaDinamica) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaDao listaDinamicaDao = (ListaDinamicaDao) daoManager.getDao(ListaDinamicaDao.class);
			listaDinamica.setConsulta(listaDinamica.getConsulta());
			listaDinamica.setC_id(listaDinamica.getC_id());
			listaDinamica.setEstado("A");
			listaDinamicaDao.guardarListaDinamica(listaDinamica);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean desactivarListaDinamica(Integer id_lista_dinamica) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaDao listaDinamicaDao = (ListaDinamicaDao) daoManager.getDao(ListaDinamicaDao.class);

			ListaDinamica listaDinamica = listaDinamicaDao.obtenerListaDinamica(id_lista_dinamica);

			listaDinamica.setEstado("E");
			listaDinamicaDao.guardarListaDinamica(listaDinamica);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	// ------------------------------

	public ListaDinamica obtenerListaDinamica(Integer id_lista_dinamica) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaDao listaDinamicaDao = (ListaDinamicaDao) daoManager.getDao(ListaDinamicaDao.class);

			return listaDinamicaDao.obtenerListaDinamica(id_lista_dinamica);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	@SuppressWarnings("rawtypes")
	public List<ValorLista> obtenerValoresListaDinamica(Integer id_lista_dinamica, Session session) {

		ListaDinamica listaDinamica = obtenerListaDinamica(id_lista_dinamica);

		// si la lista de valores tiene un servicio a ejecutar antes de obtener
		// los valores.
		if (StringUtils.esNoVacio(listaDinamica.getServicio())) {
			try {
				String servicio = listaDinamica.getServicio();

				for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
					String param = (String) e.nextElement();
					Object atributo = session.getAttribute(param);

					if (atributo != null) {
						servicio = servicio.replace("#" + param + "#", atributo.toString());
					}
				}

				SimpleLogger.setInfo("cargando servicio... " + servicio);

				String[] ser = servicio.split(":");
				String classname = ser[0];
				String operation = ser[1];

				String valores[] = ser[2].split(",");
				Object[] argumentos = new Object[valores.length];

				Class<?> clazz = Class.forName(classname);
				Method method = clazz.getMethod("getInstance", new Class[0]);
				Object object = method.invoke(null, new Object[0]);

				Method[] methods = clazz.getMethods();

				for (Method metodo : methods) {

					if (metodo.getName().equals(operation)) {
						Boolean argumentosValidos = true;

						Class<?>[] classes = metodo.getParameterTypes();
						for (int i = 0; i < classes.length; i++) {
							Class<?> tipo = classes[i];

							argumentos[i] = valores[i];

							if (tipo.getName().equals("java.lang.Integer")) {
								if (NumberUtils.isDigits(valores[i])) {
									argumentos[i] = Integer.parseInt(valores[i]);
								} else {
									argumentosValidos = false;
									SimpleLogger.setError("Argumentos no validos para ejecutar servicio:" + servicio);
								}
							}
						}

						if (argumentosValidos) {
							metodo.invoke(object, argumentos);
						}
					}
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error al ejecutar servicio de lista dinamica: " + id_lista_dinamica, e);
			}
		}

		Map<String, Object> parametros = new HashMap<String, Object>();

		List<ParametroReporte> params = new ArrayList<ParametroReporte>();

		if (session != null) {
			for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {

				String param = (String) e.nextElement();
				Object atributo = session.getAttribute(param);

				if (atributo != null && (listaDinamica.getConsulta().contains("#" + param + "#") || listaDinamica.getConsulta().contains("#:" + param + "#"))) {

					if (atributo instanceof String) {
						parametros.put(param, (String) atributo);
						parametros.put(":" + param, atributo + "");

						ParametroReporte parametro = new ParametroReporte();
						parametro.setTipo("string");
						parametro.setEncriptado("N");
						parametro.setNombre(param);

						params.add(parametro);

						ParametroReporte parametro2 = new ParametroReporte();
						parametro2.setTipo("string");
						parametro2.setEncriptado("S");
						parametro2.setNombre(":" + param);

						params.add(parametro2);
					}

					if (atributo instanceof Integer) {

						parametros.put(param, atributo);
						parametros.put(":" + param, atributo);

						ParametroReporte parametro = new ParametroReporte();
						parametro.setTipo("integer");
						parametro.setEncriptado("N");
						parametro.setNombre(param);

						params.add(parametro);

						ParametroReporte parametro2 = new ParametroReporte();
						parametro2.setTipo("integer");
						parametro2.setEncriptado("S");
						parametro2.setNombre(":" + param);

						params.add(parametro2);
					}
				}

			}
		}

		List<Resultado> resultados = new ArrayList<Resultado>();

		String str_id = listaDinamica.getC_id().trim();
		String str_nombre = listaDinamica.getC_nombre().trim();

		Resultado r1 = new Resultado();
		r1.setNombre("ID");
		r1.setTipo("string");
		r1.setEncriptado("N");
		if (str_id.indexOf(":") == 0) {
			r1.setEncriptado("S");
			str_id = str_id.substring(1);
		}
		resultados.add(r1);

		Resultado r2 = new Resultado();
		r2.setNombre("NOMBRE");
		r2.setTipo("string");
		r2.setEncriptado("N");
		if (str_nombre.indexOf(":") == 0) {
			r2.setEncriptado("S");
			str_nombre = str_nombre.substring(1);
		}
		resultados.add(r2);

		try {

			ServicioReporteDim srd = new ServicioReporteDim();
			String param = listaDinamica.getConsulta();
			if ((param.contains("#parametro_json#") || param.contains("#:parametro_json#")) && session.getAttribute("parametro_json") == null) {
				param = param.replace("#parametro_json#", "null").replace("#:parametro_json#", "null");
			}
			String sql_qe = "SELECT " + str_id + " as ID, " + str_nombre + " as NOMBRE FROM " + param + " ORDER BY " + str_nombre;

			System.out.println("LISTA DINAMICA: " + sql_qe);

			srd.setConsulta(sql_qe);
			srd.setNombre_conexion((listaDinamica.getBase_datos() == 2) ? "CREADATOS" : "DATASUITE");
			srd.setParametros(params);
			srd.setResultados(resultados);

			List<ValorLista> valores = new ArrayList<ValorLista>();

			ResultadoConsulta resultadoConsulta = RDServicio.getInstance().ejecutarServicio(srd, parametros, 1, Integer.MAX_VALUE);

			if (resultadoConsulta != null) {
				for (Map<String, Object> data : resultadoConsulta.getDatos()) {
					ValorLista valorLista = new ValorLista();
					valorLista.setId(data.get("ID").toString());
					valorLista.setNombre(StringUtils.toString(data.get("NOMBRE")));

					valores.add(valorLista);
				}
			} else {
				SimpleLogger.setError("Error realizando consulta: \n" + srd.getConsulta());
			}

			return valores;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	
	public List<ValorLista> obtenerValoresListaDinamicaCampos(Integer id_lista_dinamica, Session session, Map<String, Object> params_json) {

		ListaDinamica listaDinamica = obtenerListaDinamica(id_lista_dinamica);

		// si la lista de valores tiene un servicio a ejecutar antes de obtener
		// los valores.
		if (StringUtils.esNoVacio(listaDinamica.getServicio())) {
			try {
				String servicio = listaDinamica.getServicio();
				
				for (String param: params_json.keySet()) {
					Object atributo = params_json.get(param);
					if (atributo != null) {
						servicio = servicio.replace("#" + param + "#", atributo.toString());
					}
				}

				SimpleLogger.setInfo("cargando servicio... " + servicio);

				String[] ser = servicio.split(":");
				String classname = ser[0];
				String operation = ser[1];

				String valores[] = ser[2].split(",");
				Object[] argumentos = new Object[valores.length];

				Class<?> clazz = Class.forName(classname);
				Method method = clazz.getMethod("getInstance", new Class[0]);
				Object object = method.invoke(null, new Object[0]);

				Method[] methods = clazz.getMethods();

				for (Method metodo : methods) {

					if (metodo.getName().equals(operation)) {
						Boolean argumentosValidos = true;

						Class<?>[] classes = metodo.getParameterTypes();
						for (int i = 0; i < classes.length; i++) {
							Class<?> tipo = classes[i];

							argumentos[i] = valores[i];

							if (tipo.getName().equals("java.lang.Integer")) {
								if (NumberUtils.isDigits(valores[i])) {
									argumentos[i] = Integer.parseInt(valores[i]);
								} else {
									argumentosValidos = false;
									SimpleLogger.setError("Argumentos no validos para ejecutar servicio:" + servicio);
								}
							}
						}

						if (argumentosValidos) {
							metodo.invoke(object, argumentos);
						}
					}
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error al ejecutar servicio de lista dinamica: " + id_lista_dinamica, e);
			}
		}

		Map<String, Object> parametros = new HashMap<String, Object>();

		List<ParametroReporte> params = new ArrayList<ParametroReporte>();

		if (params_json != null) {
			for (String param: params_json.keySet()) {

				Object atributo = params_json.get(param);

				if (atributo != null && (listaDinamica.getConsulta().contains("#" + param + "#") || listaDinamica.getConsulta().contains("#:" + param + "#"))) {

					if (atributo instanceof String) {
						parametros.put(param, (String) atributo);
						parametros.put(":" + param, atributo + "");

						ParametroReporte parametro = new ParametroReporte();
						parametro.setTipo("string");
						parametro.setEncriptado("N");
						parametro.setNombre(param);

						params.add(parametro);

						ParametroReporte parametro2 = new ParametroReporte();
						parametro2.setTipo("string");
						parametro2.setEncriptado("S");
						parametro2.setNombre(":" + param);

						params.add(parametro2);
					}

					if (atributo instanceof Integer) {

						parametros.put(param, atributo);
						parametros.put(":" + param, atributo);

						ParametroReporte parametro = new ParametroReporte();
						parametro.setTipo("integer");
						parametro.setEncriptado("N");
						parametro.setNombre(param);

						params.add(parametro);

						ParametroReporte parametro2 = new ParametroReporte();
						parametro2.setTipo("integer");
						parametro2.setEncriptado("S");
						parametro2.setNombre(":" + param);

						params.add(parametro2);
					}
				}

			}
		}

		List<Resultado> resultados = new ArrayList<Resultado>();

		String str_id = listaDinamica.getC_id().trim();
		String str_nombre = listaDinamica.getC_nombre().trim();

		Resultado r1 = new Resultado();
		r1.setNombre("ID");
		r1.setTipo("string");
		r1.setEncriptado("N");
		if (str_id.indexOf(":") == 0) {
			r1.setEncriptado("S");
			str_id = str_id.substring(1);
		}
		resultados.add(r1);

		Resultado r2 = new Resultado();
		r2.setNombre("NOMBRE");
		r2.setTipo("string");
		r2.setEncriptado("N");
		if (str_nombre.indexOf(":") == 0) {
			r2.setEncriptado("S");
			str_nombre = str_nombre.substring(1);
		}
		resultados.add(r2);

		try {

			ServicioReporteDim srd = new ServicioReporteDim();
			String param = listaDinamica.getConsulta();
			if ((param.contains("#parametro_json#") || param.contains("#:parametro_json#")) && params_json.get("parametro_json") == null) {
				param = param.replace("#parametro_json#", "null").replace("#:parametro_json#", "null");
			}
			String sql_qe = "SELECT " + str_id + " as ID, " + str_nombre + " as NOMBRE FROM " + param + " ORDER BY " + str_nombre;

			System.out.println("LISTA DINAMICA: " + sql_qe);

			srd.setConsulta(sql_qe);
			srd.setNombre_conexion((listaDinamica.getBase_datos() == 2) ? "CREADATOS" : "DATASUITE");
			srd.setParametros(params);
			srd.setResultados(resultados);

			List<ValorLista> valores = new ArrayList<ValorLista>();

			ResultadoConsulta resultadoConsulta = RDServicio.getInstance().ejecutarServicio(srd, parametros, 1, Integer.MAX_VALUE);

			if (resultadoConsulta != null) {
				for (Map<String, Object> data : resultadoConsulta.getDatos()) {
					ValorLista valorLista = new ValorLista();
					valorLista.setId(data.get("ID").toString());
					valorLista.setNombre(StringUtils.toString(data.get("NOMBRE")));

					valores.add(valorLista);
				}
			} else {
				SimpleLogger.setError("Error realizando consulta: \n" + srd.getConsulta());
			}

			return valores;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
}
