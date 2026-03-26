package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoRequest;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoResponse;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionFormatoResponseData;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CampoServicio {

	private static List<Campo> campos = new ArrayList<Campo>();

	public static List<Campo> getCampos() {

		if (campos.isEmpty()) {

			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

				campos = campoDao.obtenerCampos();

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}

		}

		return campos;
	}

	public static void clear() {
		campos.clear();
	};

	private static CampoServicio campoServicio;

	private CampoServicio() {
	}

	public static CampoServicio getInstance() {
		if (campoServicio == null) {
			campoServicio = new CampoServicio();
		}
		return campoServicio;
	}

	// ------------------------------

	public Campo obtenerCampo(Integer id_campo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			return campoDao.obtenerCampo(id_campo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Campo> obtenerCamposPorEstructura(Integer id_estructura) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			return campoDao.obtenerCamposPorEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public boolean campoTieneListaValores(Integer id_campo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			return campoDao.campoTieneListaValores(id_campo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	// ------------------------------

	/***
	 * Retorna el valor del campo de acuerdo al tipo de campo, si no tiene una
	 * relación con otra estructura lo encripta
	 * 
	 * @return Objeto del tipo del campo
	 * @author oaortizs
	 */
	public Object convertirDatoCampo(Integer id_campo, String dato) {
		try {
			
			if(dato == null){
				return null;
			}
			
			Object valor = null;

			Campo campo = obtenerCampo(id_campo);

			TipoCampo tipoCampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());

			if ("Object".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = new Integer(dato);
			}

			if ("Boolean".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = StringUtils.esVerdad(dato);
			}

			else if ("String".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = dato;
			}

			else if ("Integer".equalsIgnoreCase(tipoCampo.getTipo_dato()) || "Long".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = new Long(dato);
			}

			else if ("Date".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = StringUtils.toDate(dato);
			}

			else if ("Float".equalsIgnoreCase(tipoCampo.getTipo_dato()) || "Double".equalsIgnoreCase(tipoCampo.getTipo_dato())
					|| "BigDecimal".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
				valor = new BigDecimal(dato);
			}

			if (campo.getId_estructurarelacionada() == null) {
				valor = Crypto.E(valor);
			}

			return valor;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error valor:" + dato + ", id_campo:" + id_campo, e);
		}
		return null;
	}

	public Boolean actualizarOrdenCampos(List<Campo> campos) {
		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager();
			CampoDao campoDao = (CampoDao) daoManager.getDao(CampoDao.class);

			daoManager.startTransaction();

			for (Campo campo : campos) {
				campoDao.actualizarOrdenCampo(campo);
			}
			daoManager.commitTransaction();
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}

		return false;
	}

	public static Object getValorReal(String valor, String tipodato) {

		Object dato = null;

		if (valor == null) {
			return null;
		}

		valor = StringUtils.toString(Crypto.DF(valor));

		if ("string".equalsIgnoreCase(tipodato)) {
			dato = valor;
		}

		if ("date".equalsIgnoreCase(tipodato)) {
			dato = StringUtils.toDate(valor);
		}

		if ("integer".equalsIgnoreCase(tipodato) || "long".equalsIgnoreCase(tipodato)) {
			dato = new Long(valor);
		}

		if ("double".equalsIgnoreCase(tipodato) || "float".equalsIgnoreCase(tipodato) || "bigdecimal".equalsIgnoreCase(tipodato)) {
			dato = new BigDecimal(valor);
		}

		if ("boolean".equalsIgnoreCase(tipodato)) {
			dato = StringUtils.esVerdad(valor);
		}

		if ("object".equalsIgnoreCase(tipodato)) {
			dato = new Long(valor);
		}

		return dato;

	}

	/**
	 * obtener valor de visualizacion desencriptado de un campo a partir del
	 * id_campo y un valor
	 * 
	 */
	public String obtenerValorVisualizacionCampo(Integer id_campo, Object valor) {

		Campo campo = obtenerCampo(id_campo);

		if (campo.getId_estructurarelacionada() == null && campo.getId_lista_valores() == null) {

			return StringUtils.toString(valor);

		}

		if (campo.getId_estructurarelacionada() != null) {
			String sql = " select visualizacion from v" + campo.getId_estructurarelacionada() + " where ID= " + valor;

			try {
				DaoManager daoManager = DaoConfig.getDaoManager(2);
				SQLDao sqldao = (SQLDao) daoManager.getDao(SQLDao.class);

				sql = RDServicio.reemplazarNombres(sql);

				SimpleLogger.setDebug("SQL --> " + sql);

				return StringUtils.toString(Crypto.DF(StringUtils.toString(sqldao.selectSQLRegistro(sql).get("VISUALIZACION"))));

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}

		}

		if (campo.getId_lista_valores() != null) {
			String sql = " select nombre from L" + campo.getId_lista_valores() + " where ID= '" + Crypto.E(valor) + "'";

			try {
				DaoManager daoManager = DaoConfig.getDaoManager(2);
				SQLDao sqldao = (SQLDao) daoManager.getDao(SQLDao.class);

				sql = RDServicio.reemplazarNombres(sql);

				SimpleLogger.setDebug("SQL --> " + sql);

				return StringUtils.toString(Crypto.DF(StringUtils.toString(sqldao.selectSQLRegistro(sql).get("NOMBRE"))));

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}

		}

		return StringUtils.toString(valor);
	}

	/**
	 * Retorna los campos que hacen parte de la llave primaria si los hay
	 * 
	 * @param id_estructura
	 *            - id de la estructura para consultar los campos
	 * @return
	 */
	public List<Campo> obtenerCamposLlavePrimaria(Integer id_estructura) {
		
		try {

			CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
			
			return campoDao.obtenerCamposLlavePrimaria(id_estructura);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<Campo> obtenerCamposInfoAdicionalDisponiblesPorAccion(Integer id_accion) {
		try {

			CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
			
			return campoDao.obtenerCamposInfoAdicionalDisponiblesPorAccion(id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<Campo> obtenerCamposInfoAdicionalPorAccion( Integer id_accion ) {
		try {

			CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
			
			return campoDao.obtenerCamposInfoAdicionalPorAccion(id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean insertarAccionCampoInfoAdicional(Integer id_campo, Integer id_accion) {
		try {

			CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
			
			return campoDao.insertarAccionCampoInfoAdicional(id_campo, id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean borrarAccionCampoInfoAdicional(Integer id_campo,	Integer id_accion) {
		try {

			CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
			
			return campoDao.borrarAccionCampoInfoAdicional(id_campo, id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean guardarCamposAdicionales(Integer id_carga, Integer id_campo, String valor){
		
		try {
			Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
			TipoCampo tipo_campo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
			
			String tipodato = tipo_campo.getNombre();
			
			String valor_cifrado = null;
			
			if (StringUtils.esNoVacio(valor)) {
				
				if ("Booleano".equalsIgnoreCase(tipodato)) {
					valor_cifrado = Crypto.E(StringUtils.esVerdad(valor));
				}
				
				if ("Texto".equalsIgnoreCase(tipodato) || "Texto Largo".equalsIgnoreCase(tipodato) || "Correo Electronico".equalsIgnoreCase(tipodato)) {
					valor_cifrado = Crypto.E(valor);
				}
				
				if ("Entero".equalsIgnoreCase(tipodato) || "Long".equalsIgnoreCase(tipodato)) {
					valor_cifrado = Crypto.E(new Long(valor));
				}
				
				if ("Fecha".equalsIgnoreCase(tipodato)) {
					valor_cifrado = Crypto.E(StringUtils.toDate(valor));
				}
				
				if ("Flotante".equalsIgnoreCase(tipodato) || "Double".equalsIgnoreCase(tipodato) || "BigDecimal".equalsIgnoreCase(tipodato)) {
					valor_cifrado = Crypto.E(new BigDecimal(valor));
				}
			} else {
				valor_cifrado = null;
			}
			
			Integer id_estructura = campo.getId_estructura();
			String update = "update T" + id_estructura  + " set C" + id_campo + " = '" + valor_cifrado + "' where idcarga = " + id_carga;
			DS_SqlUtils.update(update);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ValidacionFormatoResponseData validarCamposFormato(Integer id_formato, ValidacionFormatoRequest request, Integer id_formato_campo){
		try {
			
			String endpoint = null;
			
			if (id_formato_campo == null){				
				Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);
				endpoint = formato.getEndpoint_validacion();
			}else {
				FormatoCampo campo = FormatoServicio.getInstance().obtenerFormatoCampoPorID(id_formato_campo);
				endpoint = campo.getEndpoint_validacion();
			}
			
			if (StringUtils.esVacio(endpoint)){
				throw new RuntimeException("No se ha configurado endpoint para la validacion de los campos del formato");
			}
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
			headers.put(Constantes.ACCEPT, Constantes.APPLICATION_JSON);
			
			CallPage call = new CallPage();
			Gson gson = new Gson();
			
			SimpleLogger.setDebug("Consultando servicio: " + endpoint);
			
			String response = call.callPost(endpoint, gson.toJson(request), headers);
			
			if (response != null){
				ValidacionFormatoResponse resp = gson.fromJson(response, ValidacionFormatoResponse.class);
				return resp.getData();
			}else {
				throw new RuntimeException("Respuesta vacia recibida desde el servidor");
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Ocurrio un error", e);
			return null;
		}
	}

}
