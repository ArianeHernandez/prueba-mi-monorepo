package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.cocoon.environment.Session;
import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.persistencia.dao.ListaDinamicaCampoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/***
 * Servicios relacionados con la clase ListaDinamicaCampo
 * 
 * @author oaortizs
 */
public class ListaDinamicaCampoServicio {

	private static ListaDinamicaCampoServicio instance;

	private ListaDinamicaCampoServicio() {

	}

	public static ListaDinamicaCampoServicio getInstance() {
		if (instance == null) {
			instance = new ListaDinamicaCampoServicio();
		}
		return instance;
	}

	/**
	 * Retorna las listas creadas para el formato
	 * 
	 * @param id_formato
	 *            - id del formato
	 * @return lista
	 */
	public List<ListaDinamicaCampo> obtenerListasDCPorFormato(Integer id_formato) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaCampoDao dao = (ListaDinamicaCampoDao) daoManager.getDao(ListaDinamicaCampoDao.class);

			return dao.obtenerListasPorFormato(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean guardarListasDinamicaCampoTrans(List<ListaDinamicaCampo> listaDC, Integer id_formato, DaoManager daoManager) {

		try {

			ListaDinamicaCampoDao dao = (ListaDinamicaCampoDao) daoManager.getDao(ListaDinamicaCampoDao.class);

			Boolean rta = dao.guardarListasDinamicaCampo(listaDC, id_formato);

			return rta;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	/**
	 * Guarda las relaciones del formato con los campos y las listas dinamicas
	 * (antes de guardar se eliminan las listas del formato)
	 * 
	 * @param listaDC
	 * @param id_formato
	 * @return
	 */
	public Boolean guardarListasDinamicaCampo(List<ListaDinamicaCampo> listaDC, Integer id_formato) {

		DaoManager daoManager = null;
		try {
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();

			guardarListasDinamicaCampoTrans(listaDC, id_formato, daoManager);

			daoManager.commitTransaction();

			return true;
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return false;

	}

	public List<Object> obtenerListasDCPorFormatoValores(Integer id_formato, Session session) {
		try {

			List<Object> listaValores = new ArrayList<Object>();

			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaDinamicaCampoDao dao = (ListaDinamicaCampoDao) daoManager.getDao(ListaDinamicaCampoDao.class);

			List<ListaDinamicaCampo> lista = dao.obtenerListasPorFormato(id_formato);

			ListaDinamicaServicio servicio = ListaDinamicaServicio.getInstance();

			Map<String, Object> map = new HashMap<String, Object>();

			for (ListaDinamicaCampo listaDinamicaCampo : lista) {

				listaValores.add(listaDinamicaCampo);

				List<ValorLista> valores = servicio.obtenerValoresListaDinamica(listaDinamicaCampo.getId_lista_dinamica(), session);

				map.put("LV_" + listaDinamicaCampo.getId_lista_dinamica(), valores);

			}

			listaValores.add(map);

			return listaValores;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	/**
	 * Retorna los valores de lista dinámica recibiendo un listado de parámetros
	 * en un String
	 * 
	 * @param parametros
	 *            - Lista de parametros en el formato, se permiten valores vacíos
	 *            [parametro_1],[parametro_2],[parametro_3],[]
	 * @param session
	 *            - sesión del usuario
	 * @return
	 */
	public List<ValorLista> obtenerValoresListaDinamicaParams(Integer id_lista_dinamica, String parametros, Session session) {

		try {
			
			String parametro_json = parametros;

			if (StringUtils.isNotEmpty(parametros) &&  parametros.startsWith("[") && parametros.endsWith("]")) {
				
				parametros = parametros.substring(1, parametros.length() - 1);
				
				List<String> listParams = new ArrayList<String>();
				
				if (parametros.startsWith("],[")) {
					listParams.add("");
				}
				
				StringTokenizer stringTokenizer = new StringTokenizer(parametros, "],[");
				
				
				while (stringTokenizer.hasMoreTokens()) {
					String param = (String) stringTokenizer.nextToken();
					listParams.add(param);
				}
				if(listParams.size() == 0){
					listParams.add(parametros);
				}
				for (int i = 0; i < listParams.size(); i++) {
					session.setAttribute("parametro_json_" + (i + 1), listParams.get(i));
				}
				
				parametro_json = listParams.get(0);
				
			}

			return obtenerValoresListaDinamica(id_lista_dinamica, parametro_json, session);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public List<ValorLista> obtenerValoresListaDinamica(Integer id_lista_dinamica, String parametro_json, Session session) {
		try {

			session.setAttribute("parametro_json", parametro_json);
			try {
			
				Integer valor = new Integer(parametro_json);
			
				if (valor.toString().equals(parametro_json)) {
					session.setAttribute("parametro_json", valor);
				}
			} catch (Exception e) {
				try {
					
					Double valor = new Double(parametro_json);
				
					session.setAttribute("parametro_json", valor.toString().replace(".", ","));
					
				} catch (Exception ex) {		
				}
			}

			List<ValorLista> lista = ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(id_lista_dinamica, session);
			session.removeAttribute("parametro_json");
			return lista;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public List<ValorLista> obtenerValoresListaDinamicaCampos(Integer id_lista_dinamica, String[] parametros_json, Session session) {
		try {
			
			Map<String, Object> params = new HashMap<>();
			
			for (int i = 0; i < parametros_json.length; i++) {
				String nombre_atributo = (parametros_json.length > 1) ? "parametro_json_" + (i+1): "parametro_json";
				
				params.put(nombre_atributo, parametros_json[i]);
				try {
				
					Integer valor = new Integer(parametros_json[i]);
				
					if (valor.toString().equals(parametros_json[i])) {
						params.put(nombre_atributo, valor);
					}
				} catch (Exception e) {
					try {
						
						Double valor = new Double(parametros_json[i]);
					
						params.put(nombre_atributo, valor.toString().replace(".", ","));
						
					} catch (Exception ex) {		
					}
				}
			}
			List<ValorLista> lista = ListaDinamicaServicio.getInstance().obtenerValoresListaDinamicaCampos(id_lista_dinamica, session, params);

			return lista;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
}
