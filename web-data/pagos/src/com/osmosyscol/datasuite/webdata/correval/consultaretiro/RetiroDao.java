package com.osmosyscol.datasuite.webdata.correval.consultaretiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;

public class RetiroDao {

	public Long guardarRetiro(Retiro retiro, CargaDao cargaDao) {

		Map<String, Object> mapaRetiro = new HashMap<String, Object>();
		Long id_beneficiario = guardarBeneficiario(retiro, cargaDao);

		Long id_retiro = cargaDao.obtenerSiguienteCreadatos().longValue();

		mapaRetiro.put("ESTADO", retiro.getEstado());
		mapaRetiro.put("IDCARGA", retiro.getId_carga());
		mapaRetiro.put("$RETIROS.VALOR$", Crypto.E(retiro.getValor()));
		mapaRetiro.put("$RETIROS.ID CLIENTE$", Crypto.E(retiro.getId_cliente()));
		mapaRetiro.put("$RETIROS.ESTADO$", Crypto.E("P"));
		mapaRetiro.put("$RETIROS.BENEFICIARIO$", id_beneficiario);
		mapaRetiro.put("$RETIROS.TIPO DE RETIRO$", Crypto.E("ACH"));
		mapaRetiro.put("$RETIROS.OBSERVACION$", Crypto.E(retiro.getObservacion()));
		mapaRetiro.put("ID", id_retiro);

		String sql = SQLServicio.createSQLInsert("$RETIROS$", mapaRetiro);
		sql = RDServicio.reemplazarNombres(sql);

		SimpleLogger.setDebug("SQL Retiro -> " + sql);

		cargaDao.insertSQL(sql);

		return id_retiro;
	}

	private Long guardarBeneficiario(Retiro retiro, CargaDao cargaDao) {

		if (retiro.getCuenta_beneficiario() == null) {
			new RuntimeException("Numero de Cuenta de Beneficiario invalida");
		}

		if (retiro.getTipo_cuenta_beneficiario() == null) {
			new RuntimeException("Tipo de Cuenta de Beneficiario invalida");
		}

		// ----------------------
		// ----------------------

		Map<String, Object> mapaBeneficiario = new HashMap<String, Object>();

		Long id_beneficiario = null;

		mapaBeneficiario.put("ESTADO", retiro.getEstado());
		mapaBeneficiario.put("IDCARGA", retiro.getId_carga());
		mapaBeneficiario.put("$BENEFICIARIO.ID CLIENTE$", Crypto.E(retiro.getId_cliente()));
		mapaBeneficiario.put("$BENEFICIARIO.NUMERO DE DOCUMENTO$", Crypto.E(retiro.getIdentificacion_beneficiario()));
		mapaBeneficiario.put("$BENEFICIARIO.NUMERO DE CUENTA$", Crypto.E(retiro.getCuenta_beneficiario()));
		mapaBeneficiario.put("$BENEFICIARIO.TIPO DE CUENTA$", retiro.getTipo_cuenta_beneficiario());
		mapaBeneficiario.put("$BENEFICIARIO.TIPO DE DOCUMENTO$", retiro.getTipo_documento_beneficiario());
		mapaBeneficiario.put("$BENEFICIARIO.BANCO$", retiro.getCod_banco());
		mapaBeneficiario.put("$BENEFICIARIO.PRIMER NOMBRE$", Crypto.E(retiro.getNombre_beneficiario()));

		id_beneficiario = buscarBeneficiario(retiro, cargaDao);

		if (id_beneficiario == null) {
			id_beneficiario = cargaDao.obtenerSiguienteCreadatos().longValue();
			mapaBeneficiario.put("ID", id_beneficiario);
			String sql = SQLServicio.createSQLInsert("$BENEFICIARIO$", mapaBeneficiario);
			sql = RDServicio.reemplazarNombres(sql);
			SimpleLogger.setDebug("SQL beneficiario-> " + sql);
			cargaDao.insertSQL(sql);
		} else {
			Map<String, Object> mapWhere = new HashMap<String, Object>();
			mapWhere.put("ID", id_beneficiario);
			String sql = SQLServicio.createSQLUpdate("$BENEFICIARIO$", mapaBeneficiario, mapWhere);
			sql = RDServicio.reemplazarNombres(sql);
			SimpleLogger.setDebug("SQL beneficiario-> " + sql);
			cargaDao.updateSQL(sql);
		}

		return id_beneficiario;
	}

	private Long buscarBeneficiario(Retiro retiro, CargaDao cargaDao) {

		// SE DEBE BUSCAR POR LOS CAMPOS QUE SON LLAVE PRIMARIA DE LA ESTRUCTURA
		// BENEFICIARIO

		String sql = "SELECT $BENEFICIARIO$.ID FROM $BENEFICIARIO$ " + "WHERE $BENEFICIARIO.NUMERO DE DOCUMENTO$ = '" + Crypto.E(retiro.getIdentificacion_beneficiario()) + "' " + " AND $BENEFICIARIO.TIPO DE DOCUMENTO$ = " + retiro.getTipo_documento_beneficiario() + " " + " AND $BENEFICIARIO.TIPO DE CUENTA$ = " + retiro.getTipo_cuenta_beneficiario() + " " + " AND $BENEFICIARIO.NUMERO DE CUENTA$ = '" + Crypto.E(retiro.getCuenta_beneficiario()) + "' " + " AND $BENEFICIARIO.BANCO$ = "
				+ retiro.getCod_banco() + " " + " AND IDCARGA = " + retiro.getId_carga();

		sql = RDServicio.reemplazarNombres(sql);

		List<Map<String, Object>> lista = cargaDao.selectSql(sql);

		if (lista != null && lista.size() > 0) {
			Map<String, Object> map = lista.get(0);
			Object id = map.get("ID");
			if (id instanceof Long) {
				return (Long) id;
			} else {
				return new Long(id.toString());
			}

		}

		return null;
	}

	public List<Map<String, Object>> obtenerBancos(CargaDao cargaDao) {

		String sql = RDServicio.reemplazarNombres("SELECT * FROM $BANCO$ WHERE $BANCO.CODIGO$ != $S(0)$");

		List<Map<String, Object>> valores = cargaDao.selectSql(sql);

		if (valores != null) {
			for (Map<String, Object> registro : valores) {
				Set<String> keys = registro.keySet();

				for (String key : keys) {
					registro.put(key, Crypto.DF(registro.get(key)));
				}

			}
		}

		return valores;
	}

	public List<Map<String, Object>> obtenerRespuestasBanco(CargaDao cargaDao) {
		
		String sql = RDServicio.reemplazarNombres("SELECT DISTINCT $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ ESTADO FROM $ESTADO RESPUESTA BANCO$");

		List<Map<String, Object>> valores = cargaDao.selectSql(sql);

		if (valores != null) {
			for (Map<String, Object> registro : valores) {
				Set<String> keys = registro.keySet();

				for (String key : keys) {
					registro.put(key, Crypto.DF(registro.get(key)));
				}
			}
		}

		return valores;
	}

}
