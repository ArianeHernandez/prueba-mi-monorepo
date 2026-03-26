package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;

public class GrupoGiroDao {

	/****
	 * Guarda los datos del archivo
	 */
	public Boolean guardarArchivoGrupoGiroSql(ArchivoGrupoGiro archivosGrupoGiro, CargaDao cargaDao) {

		Map<String, Object> mapData = new HashMap<String, Object>();

		mapData.put("$ARCHIVO.CODIGO$", Crypto.E(archivosGrupoGiro.getCodigo()));
		mapData.put("$ARCHIVO.PERSONA$", Crypto.E(archivosGrupoGiro.getPersona().toUpperCase()));
		mapData.put("$ARCHIVO.FECHA CREACION$", Crypto.E(archivosGrupoGiro.getFecha_creacion()));
		mapData.put("$ARCHIVO.NOMBRE$", Crypto.E(archivosGrupoGiro.getNombre()));
		mapData.put("$ARCHIVO.HASH$", Crypto.E(archivosGrupoGiro.getHash()));
		mapData.put("$ARCHIVO.ORDEN$", Crypto.E(archivosGrupoGiro.getOrden()));
		mapData.put("id", archivosGrupoGiro.getCodigo());
		mapData.put("idCarga", 0);

		String tabla = "$ARCHIVO$";

		String sql = SQLServicio.createSQLInsert(tabla, mapData);
		sql = RDServicio.reemplazarNombres(sql);
		return cargaDao.insertSQL(sql);

	}

	/***
	 * Guardar un grupo de giro en la estructura GRUPO GIRO
	 * 
	 * @param grupoGiroRegistro
	 * @param cargaDao
	 * @return exito
	 * 
	 * @author oaortizs
	 */
	public Boolean guardarGrupoGiroSql(GrupoGiro grupoGiroRegistro, CargaDao cargaDao, String estadoNuevo) {

		if (existeGrupoGiro(grupoGiroRegistro.getCodigo_registro(), cargaDao)) {
			return actualizarGrupoGiroSql(grupoGiroRegistro, cargaDao);
		}

		Map<String, Object> mapData = new HashMap<String, Object>();
		String tabla = "$GRUPO GIRO$";

		mapData.put("$GRUPO GIRO.CODIGO REGISTRO$", grupoGiroRegistro.getCodigo_registro());
		mapData.put("$GRUPO GIRO.FECHA PAGO$", Crypto.E(grupoGiroRegistro.getFecha_pago()));
		mapData.put("$GRUPO GIRO.CUENTA$", grupoGiroRegistro.getCuenta());
		mapData.put("$GRUPO GIRO.ESTRUCTURA$", Crypto.E(grupoGiroRegistro.getId_estructura()));
		mapData.put("id", cargaDao.obtenerSiguienteCreadatos());
		mapData.put("idCarga", 0);

		String sql = SQLServicio.createSQLInsert(tabla, mapData);
		sql = RDServicio.reemplazarNombres(sql);

		actualizarEstadoRetiro(cargaDao, estadoNuevo, grupoGiroRegistro.getCodigo_registro());

		cargaDao.insertSQL(sql);
		return true;

	}

	public void actualizarEstadoRetiro(CargaDao cargaDao, String estado, Object idRegistro) {

		String update = "UPDATE $RETIROS$ SET $RETIROS.ESTADO$ = '" + Crypto.E(estado) + "'" + " WHERE ID IN (" + idRegistro + ")";

		update = RDServicio.reemplazarNombres(update);

		cargaDao.updateSQL(update);

	}

	public Boolean existeGrupoGiro(Integer codigo_registro, CargaDao cargaDao) {

		String select = "select $GRUPO GIRO.CODIGO REGISTRO$ CODIGO_REGISTRO from $GRUPO GIRO$ where " + "$GRUPO GIRO.CODIGO REGISTRO$_NUM = " + codigo_registro + "";
		select = RDServicio.reemplazarNombres(select);
		List<Map<String, Object>> lista = cargaDao.selectSql(select);
		if (lista != null && lista.size() > 0) {
			return lista.get(0).size() > 0;
		}
		return false;

	}

	public Boolean actualizarGrupoGiroSql(GrupoGiro grupoGiroRegistro, CargaDao cargaDao) {

		Map<String, Object> mapData = new HashMap<String, Object>();
		String tabla = "$GRUPO GIRO$";

		mapData.put("$GRUPO GIRO.FECHA PAGO$", Crypto.E(grupoGiroRegistro.getFecha_pago()));
		mapData.put("$GRUPO GIRO.CUENTA$", grupoGiroRegistro.getCuenta());
		mapData.put("id", cargaDao.obtenerSiguienteCreadatos());
		mapData.put("idCarga", 0);

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("$GRUPO GIRO.CODIGO REGISTRO$_NUM", grupoGiroRegistro.getCodigo_registro());

		String sql = SQLServicio.createSQLUpdate(tabla, mapData, mapWhere);
		sql += " AND $GRUPO GIRO.ARCHIVO$ IS NULL";
		sql = RDServicio.reemplazarNombres(sql);
		return cargaDao.updateSQL(sql);
	}

	public String obtenerRegistrosGrupoGiro(Integer id_archivo) {

		String sql = "SELECT " //
				+ "$RETIROS$.IDCARGA, " //
				+ "$RETIROS$.ID, " //
				+ "$TIPO DE DOCUMENTO.CODIGO$ TIPO_DOC_BEN, " //
				+ "$BENEFICIARIO.NUMERO DE DOCUMENTO$ NUM_DOC_BEN," //
				+ "COALESCE($BENEFICIARIO.PRIMER NOMBRE$ || ' ', '') || COALESCE($BENEFICIARIO.SEGUNDO NOMBRE$ || ' ','') || COALESCE($BENEFICIARIO.PRIMER APELLIDO$ || ' ','') || COALESCE($BENEFICIARIO.SEGUNDO APELLIDO$ || ' ','') NOMBRE_BEN," //
				+ "$BENEFICIARIO.NUMERO DE CUENTA$ CUENTA, "//
				+ "$TIPO DE CUENTA.CODIGO$ TIPO_CUENTA,"//
				+ "$RETIROS.VALOR$ VALOR,"//
				+ "$BANCO.CODIGO$ CODIGO_BANCO, "//
				+ "$BENEFICIARIO.DIGITO DE VERIFICACION$ DIGITO_VERIFICACION, "//
				+ "$BENEFICIARIO.DIRECCION$ DIR_BEN, "//
				+ "$RETIROS.OBSERVACION$ OBSERVACION, " //
				+ "$PRODUCTO.ID$ PRODUCTO, " //
				+ "$PRODUCTO.LINEA DE PRODUCTO$ NEGOCIO, " //
				+ "$RETIROS.ID CUENTA$ CUENTA_CLIENTE " //

				+ "FROM $RETIROS$ INNER JOIN $BENEFICIARIO$  ON $RETIROS.BENEFICIARIO$ = $BENEFICIARIO$.ID "//
				+ "INNER JOIN $PRODUCTO$ ON $PRODUCTO$.ID = $RETIROS.PRODUCTO$ "//
				+ "INNER JOIN $GRUPO GIRO$  ON $RETIROS$.ID = $GRUPO GIRO.CODIGO REGISTRO$_NUM "//
				+ "INNER JOIN $BANCO$ ON $BANCO$.ID = $BENEFICIARIO.BANCO$ "//
				+ "INNER JOIN $TIPO DE CUENTA$ ON $BENEFICIARIO.TIPO DE CUENTA$ = $TIPO DE CUENTA$.ID "//
				+ "LEFT JOIN $TIPO DE DOCUMENTO$ ON $TIPO DE DOCUMENTO$.ID = $BENEFICIARIO.TIPO DE DOCUMENTO$ "//
				+ "WHERE $GRUPO GIRO.ARCHIVO$ = " + id_archivo;

		return RDServicio.reemplazarNombres(sql);

	}

	public String obtenerArchivoGrupoGiro(Integer id_archivo) {
		String sql = "SELECT "//
				+ "$ARCHIVO.CODIGO$ CODIGO, " + "$ARCHIVO.ORDEN$ ORDEN, " //
				+ "$GRUPO GIRO.FECHA PAGO$ FECHA," //
				+ "CB.$CUENTA - BANCO.CUENTA BANCARIA$ CUENTA, " //
				+ "CB.$CUENTA - BANCO.CUENTA BANCARIA PRINCIPAL$ CUENTA_PRINCIPAL, " //
				+ "$TIPO DE CUENTA.CODIGO$ TIPO_CUENTA," //
				+ "$BANCO.CODIGO$ CODIGO_BANCO ,"
				+ "$ARCHIVO.FECHA CREACION$ FECHA_CREACION " //
				+ "FROM $ARCHIVO$  " //
				+ "INNER JOIN $GRUPO GIRO$ ON $ARCHIVO$.ID = $GRUPO GIRO.ARCHIVO$ " //
				+ "INNER JOIN $CUENTA - BANCO$ CB ON $GRUPO GIRO.CUENTA$ = CB.ID  " //
				+ "INNER JOIN $BANCO$ ON $BANCO$.ID = CB.$CUENTA - BANCO.BANCO$ "//
				+ "INNER JOIN $TIPO DE CUENTA$ ON CB.$CUENTA - BANCO.TIPO DE CUENTA$ = $TIPO DE CUENTA$.ID " //
				+ "WHERE $ARCHIVO$.ID = " + id_archivo //
				+ " GROUP BY $ARCHIVO.CODIGO$, $ARCHIVO.ORDEN$, " //
				+ "$GRUPO GIRO.FECHA PAGO$," //
				+ "CB.$CUENTA - BANCO.CUENTA BANCARIA$, CB.$CUENTA - BANCO.CUENTA BANCARIA PRINCIPAL$, " //
				+ "$BANCO.CODIGO$, " //
				+ "$TIPO DE CUENTA.CODIGO$, "
				+ "$ARCHIVO.FECHA CREACION$ ";

		return RDServicio.reemplazarNombres(sql);
	}

}
