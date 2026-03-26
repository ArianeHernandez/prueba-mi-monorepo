package com.osmosyscol.datasuite.logica.servicios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.CampoValor;
import com.osmosyscol.datasuite.logica.dto.Registro;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoCampoServicio;
import com.osmosyscol.datasuite.persistencia.dao.DatosEstructuraDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class DatosEstructuraServicio {

	private static DatosEstructuraServicio instance;

	private DatosEstructuraServicio() {

	}

	public static DatosEstructuraServicio getInstance() {
		if (instance == null) {
			instance = new DatosEstructuraServicio();
		}
		return instance;
	}

	public List<Map<String, Object>> obtenerDatosEstructura(Integer id_estructura) {
		List<Map<String, Object>> lista = obtenerDatos("select * from t" + id_estructura + " where idcarga = 0 order by ID");

		return desencriptarLista(lista);
	}

	public Integer contarDatosEstructuraPag(Integer id_estructura, Integer orderby, Integer id_campo, String filtro) {

		try {
			SQLDao sqldao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);
			String sql = crearSQL(id_estructura, orderby, id_campo, filtro);
			return sqldao.selectSQLNumber("select count(1) from (" + sql + ") t");

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;

	}

	/**
	 * Retorna los datos de la estructura por página.
	 * 
	 * @param id_estructura
	 * @param orderby
	 *            - id del campo para ordenar
	 * @param id_campo
	 *            - id del campo para hacer filtro
	 * @param id_pagina
	 *            - filtro ingresado por el usuario
	 * @return - lista de mapas
	 */

	public List<Map<String, Object>> obtenerDatosEstructuraPag(Integer id_estructura, Integer orderby, Integer id_campo, String filtro, Integer id_pagina) {

		try {
			SQLDao sqldao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			String sql = crearSQL(id_estructura, orderby, id_campo, filtro);
			List<Map<String, Object>> lista = sqldao.selectSQLPaginado(sql, id_pagina, Constantes.PAGINACIONLISTADO);

			return desencriptarLista(lista);
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;

	}

	private String crearSQL(Integer id_estructura, Integer orderby, Integer id_campo, String filtro) {

		String order = orderby == null ? "ID" : "C" + orderby;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from t");
		sql.append(id_estructura);
		sql.append(" where idcarga = 0 ");
		
		if (id_campo != null) {

			sql.append(" and (C");
			sql.append(id_campo);
			
			if (StringUtils.esVacio(filtro)) {
				sql.append(" is null or C" + id_campo + " = '' ");
			} else {
				
				Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
				filtro = SQLUtils.escapeSql(filtro);
				
				TipoCampo tipocampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
				campo.setTipocampo(tipocampo.getNombre());
				Object valor = getValorEncriptado(filtro, campo);
				sql.append(" = ");
				sql.append(valor);
			}
			sql.append(")");
		}
		
		sql.append(" order by ");
		sql.append(order);
		
		return sql.toString();
	}

	public List<Map<String, Object>> desencriptarLista(List<Map<String, Object>> lista) {
		if (lista != null) {
			for (Map<String, Object> map : lista) {
				Set<String> keys = map.keySet();
				for (String key : keys) {
					Object object = map.get(key);
					object = Crypto.DF(object);
					map.put(key, object);
				}

			}
		}
		return lista;
	}

	public List<Map<String, Object>> obtenerDatosVistaEstructura(Integer id_estructura) {
		List<Map<String, Object>> lista = obtenerDatos("select VISUALIZACION, ID FROM v" + id_estructura + " where id_carga = 0 order by ID");

		return desencriptarLista(lista);
	}

	public List<Map<String, Object>> obtenerDatos(String sql) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);

			List<Map<String, Object>> lista = dao.obtenerDatosEstructura(sql);

			return lista;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Campo> obtenerCamposPorEstructura(Integer id_estructura) {
		try {

			List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);
			for (Campo campo : campos) {
				if (campo.getId_lista_valores() != null) {
					List<ValorLista> valores = ListaValoresServicio.getInstance().obtenerValoresLV(campo.getId_lista_valores());
					campo.setValores(valores);
				}
				if (campo.getId_estructurarelacionada() != null) {
					List<Map<String, Object>> datos = obtenerDatosVistaEstructura(campo.getId_estructurarelacionada());
					campo.setDatos(datos);
				}
			}
			return campos;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	/**
	 * Guarda los datos de la estructura, actualiza, inserta o elimina, de acuerdo, a los valores de cada registro,
	 * puede limpiar los datos de la estructura antes de iniciar según el parametro reemplazarTodo 
	 * @param registros - listado lde registros
	 * @param id_estructura
	 * @param reemplazarTodo - Determina si se reemplazan todos los registros
	 * @return
	 */
	public Boolean guardarDatosEstructuraTodo(List<Registro> registros, Integer id_estructura, Boolean reemplazarTodo, Mensaje mensaje) {
		
		DaoManager daoManager = null;
		try {
			
			daoManager = DaoConfig.getDaoManager(2);
			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);
			
			daoManager.startTransaction();
			
			String tabla = "t" + id_estructura;
			
			if(mensaje != null){
				mensaje.setMensaje("Guardando información...");
			}
			
			if(reemplazarTodo){
				String sql = "delete from " + tabla + " where idcarga = 0";
				SimpleLogger.setDebug(sql);
				dao.deleteSQL(sql);
			}
			

			Map<Integer, Campo> campos = new HashMap<Integer, Campo>();

			List<Campo> listaCampos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

			for (Campo campo : listaCampos) {

				TipoCampo tipocampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());

				campo.setTipocampo(tipocampo.getNombre());
				campos.put(campo.getId_campo(), campo);
			}

			if (registros != null) {

				int i = 1;
				for (Registro registro : registros) {
					if(mensaje != null){
						mensaje.setMensaje("Guardando registro " + (i++) + " de " + registros.size());
					}
					if (!reemplazarTodo && registro.getEliminar() && registro.getId_registro() != null) {
						String sql = "DELETE FROM " + tabla + " WHERE id = " + registro.getId_registro() + " and idcarga = 0";
						SimpleLogger.setDebug(sql);
						dao.deleteSQL(sql);
					} else if (registro.getId_registro() == null || reemplazarTodo) {
						registro.setId_registro(dao.obtenerSiguiente());
						String sql = crearSQlInsert(tabla, registro, campos);
						SimpleLogger.setDebug(sql);
						dao.insertSQL(sql);

					} else {
						String sql = crearSQlUpdate(tabla, registro, campos);
						SimpleLogger.setDebug(sql);
						Integer actualizados = dao.updateSQL(sql);
						if(actualizados == 0){
							sql = crearSQlInsert(tabla, registro, campos);
							SimpleLogger.setDebug(sql);
							dao.insertSQL(sql);
						}
					}
				}

			}

			daoManager.commitTransaction();
			if(mensaje != null){
				mensaje.setMensaje("Finaliza la carga exitosamente, registros cargados: " + registros.size());
				mensaje.setExitoso(true);
				mensaje.setEstado("F");
			}
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			if(mensaje!=null){
				mensaje.setMensaje("No se ha podido cargar el archivo");
				mensaje.setEstado("F");
			}
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return false;
	}
	
	
	/**
	 * Guarda los datos de la estructura, solo actualiza los registros enviados, actualiza, inserta o elimina, 
	 * de acuerdo, a las propiedades en cada registro
	 * @param registros - listado lde registros
	 * @param id_estructura
	 * @return - exitoso
	 * @author oaortiz
	 */
	public Boolean guardarDatosEstructura(List<Registro> registros, Integer id_estructura) {

		return guardarDatosEstructuraTodo(registros, id_estructura, false, null);
	}

	private String crearSQlUpdate(String tabla, Registro registro, Map<Integer, Campo> mapCampos) {

		Map<String, Object> mapData = new HashMap<String, Object>();

		List<CampoValor> campos = registro.getCampos();

		for (CampoValor campoValor : campos) {
			Campo campo = mapCampos.get(campoValor.getId_campo());
			Object valor = getValorEncriptado(campoValor.getValor(), campo);

			mapData.put("C" + campo.getId_campo(), valor);
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();

		mapWhere.put("ID", registro.getId_registro());
		mapWhere.put("idcarga", 0);

		return SQLServicio.createSQLUpdate(tabla, mapData, mapWhere);
	}

	private String crearSQlInsert(String tabla, Registro registro, Map<Integer, Campo> mapCampos) {

		Map<String, Object> data = new HashMap<String, Object>();

		List<CampoValor> campos = registro.getCampos();

		for (CampoValor campoValor : campos) {
			Campo campo = mapCampos.get(campoValor.getId_campo());
			Object valor = getValorEncriptado(campoValor.getValor(), campo);

			data.put("C" + campo.getId_campo(), valor);

		}

		data.put("ID", registro.getId_registro());
		data.put("idcarga", 0);

		return SQLServicio.createSQLInsert(tabla, data);

	}

	// TODO Se debe convertir a la clase del tipo dato guardado en la base de
	// datos
	public static Object getValorEncriptado(String valor, Campo campo) {
		String tipoCampo = campo.getTipocampo();
		if (valor == null && !"Booleano".equalsIgnoreCase(tipoCampo)) {
			return null;
		}

		if ("Texto".equalsIgnoreCase(tipoCampo) || "Texto Largo".equalsIgnoreCase(tipoCampo)) {
			valor = "'" + Crypto.E(valor) + "'";
		}

		else if ("Booleano".equalsIgnoreCase(tipoCampo)) {
			valor = "'" + Crypto.E(StringUtils.esVerdad(valor)) + "'";
		}

		else if ("Fecha".equalsIgnoreCase(tipoCampo)) {

			valor = "'" + Crypto.E(StringUtils.toDate(valor)) + "'";
		}

		else if ("Entero".equalsIgnoreCase(tipoCampo)) {
			valor = "'" + Crypto.E(new Long(valor)) + "'";
		}

		else if ("Flotante".equalsIgnoreCase(tipoCampo)) {
			valor = "'" + Crypto.E(new BigDecimal(valor)) + "'";
		}

		// Nota: para una relación con otra estructura se guarda el mismo valor

		return valor;

	}

	/**
	 * Actualiza el valor de un campo por id de carga
	 * @param id_carga
	 * @param id_campo
	 * @param valor
	 * @return
	 * @author oaortiz
	 */
	public Boolean actualizarCampoEstructuraPorCargaTrans(Integer id_carga, Integer id_campo, String valor, DaoManager daoManager){
		
		try {

			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);
			
			Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
			
			TipoCampo tipocampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
			
			campo.setTipocampo(tipocampo.getNombre());
			
			Object valorEncriptado = getValorEncriptado(valor, campo);
			
			String sqlUpdate = "update t" + campo.getId_estructura() + //
						" set C" + campo.getId_campo() + //
						" = " +valorEncriptado + //
						" where idcarga = " + id_carga;
			
			Integer total = dao.updateSQL(sqlUpdate);
			return total > 0;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return false;
	}
	

	/**
	 * Actualiza el valor de un campo por id de registro
	 * @param id_registro
	 * @param id_campo
	 * @param valor
	 * @return
	 * @author oaortiz
	 */
	public Boolean actualizarCampoEstructuraPorIDTrans(Integer id_registro, Integer id_campo, String valor, DaoManager daoManager){
		
		try {

			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);
			
			Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
			
			TipoCampo tipocampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
			
			campo.setTipocampo(tipocampo.getNombre());
			
			Object valorEncriptado = getValorEncriptado(valor, campo);
			
			String sqlUpdate = "update t" + campo.getId_estructura() + //
						" set C" + campo.getId_campo() + //
						" = " +valorEncriptado + //
						" where id = " + id_registro;
			
			Integer total = dao.updateSQL(sqlUpdate);
			return total > 0;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return false;
	}
	
	/**
	 * Verifica si se puede eliminar el registro
	 * 
	 * @param id_estructura
	 * @param id_registro
	 * @return
	 * @author hjdiaz
	 */
	public Boolean validarEliminarRegistro(Integer id_estructura, Integer id_registro){
		
		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager(2);
			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);
			
			daoManager.startTransaction();

			String tabla = "t" + id_estructura;
			
			String sqlDelete = "delete from " + tabla + " where id = " + id_registro;
			SimpleLogger.setDebug(sqlDelete);
			dao.deleteSQL(sqlDelete);

			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en DatosEstructuraServicio.validarEliminarRegistro", e);
		}finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return false;
	}
	
	/**
	 * Verifica si se pueden remplazar los registros de una tabla
	 * 
	 * @param id_estructura
	 * @return
	 * @author hjdiaz
	 */
	public Boolean validarRemplazarDatos(Integer id_estructura){
		
		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager(2);
			DatosEstructuraDao dao = (DatosEstructuraDao) daoManager.getDao(DatosEstructuraDao.class);
			
			daoManager.startTransaction();

			String tabla = "t" + id_estructura;
			
			String sqlDelete = "delete from " + tabla + " where IDCARGA = 0 ";
			SimpleLogger.setDebug(sqlDelete);
			dao.deleteSQL(sqlDelete);

			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en DatosEstructuraServicio.validarRemplazarDatos", e);
		}finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return false;
	}
}
