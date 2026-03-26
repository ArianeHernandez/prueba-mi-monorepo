package com.osmosyscol.datasuite.webdata.correval.beneficiarios;

import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.servicios.DatosEstructuraServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.utils.ORASQLUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class BeneficiarioServicio {

	private static BeneficiarioServicio instance;

	private BeneficiarioServicio() {
	}

	public static BeneficiarioServicio getInstance() {
		if (instance == null) {
			instance = new BeneficiarioServicio();
		}
		return instance;
	}

	private String generarSQLBeneficiarios(Integer id_usuario) {

		return "SELECT $BENEFICIARIO$.ID, " + //
				"$TIPO DE DOCUMENTO.NOMBRE$ tipo_documento, " + //
				"$BENEFICIARIO.NUMERO DE DOCUMENTO$ numero_documento, " + //
				"$BENEFICIARIO.DIGITO DE VERIFICACION$ digito_verificacion, " + //
				"$BENEFICIARIO.PRIMER NOMBRE$ primer_nombre, " + //
				"$BENEFICIARIO.SEGUNDO NOMBRE$ segundo_nombre, " + //
				"$BENEFICIARIO.PRIMER APELLIDO$ primer_apellido, " + //
				"$BENEFICIARIO.SEGUNDO APELLIDO$ segundo_apellido, " + //
				"$BANCO.NOMBRE$ banco," + //
				"$TIPO DE CUENTA.DESCRIPCION$ tipo_cuenta, " + //
				"$BENEFICIARIO.NUMERO DE CUENTA$ numero_cuenta " + //
				"FROM $BENEFICIARIO$ " + //

				"LEFT JOIN $BANCO$ ON $BENEFICIARIO.BANCO$ = $BANCO$.ID " + //
				"LEFT JOIN $TIPO DE CUENTA$ ON $BENEFICIARIO.TIPO DE CUENTA$ = $TIPO DE CUENTA$.ID " + //
				"LEFT JOIN $TIPO DE DOCUMENTO$ ON $BENEFICIARIO.TIPO DE DOCUMENTO$ = $TIPO DE DOCUMENTO$.ID " + //

				"where $BENEFICIARIO$.IDCARGA = 0 AND " + //
				"$BENEFICIARIO.ID CLIENTE$ = '" + Crypto.E(id_usuario) + "'";

	}

	public Integer contarBeneficiariosCliente(Integer id_usuario) {

		try {

			String sqlBeneficiarios = "select count(1) from (" + generarSQLBeneficiarios(id_usuario) + ") t ";

			sqlBeneficiarios = RDServicio.reemplazarNombres(sqlBeneficiarios);

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);
			return sqlDao.selectSQLNumber(sqlBeneficiarios);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Map<String, Object>> obtenerBeneficiariosCliente(Integer id_usuario, Integer numero_pagina) {

		try {
			String sqlBeneficiarios = ORASQLUtils.addPaginacion(generarSQLBeneficiarios(id_usuario), numero_pagina, Constantes.PAGINACIONLISTADO);

			sqlBeneficiarios = RDServicio.reemplazarNombres(sqlBeneficiarios);

			List<Map<String, Object>> lista = CargaServicio.getInstance().selectSql(sqlBeneficiarios);
			lista = DatosEstructuraServicio.getInstance().desencriptarLista(lista);
			return lista;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean eliminarBeneficiario(Integer registro, Integer id_usuario) {
		try {
			if (registro == null || id_usuario == null) {
				return false;
			}
			String sql = "delete from $BENEFICIARIO$ " + "where $BENEFICIARIO$.ID = " + registro + " and " + "$BENEFICIARIO.ID CLIENTE$ = '" + Crypto.E(id_usuario) + "'";
			RDServicio.reemplazarNombres(sql);
			return CargaServicio.getInstance().eliminarSQL(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

}
