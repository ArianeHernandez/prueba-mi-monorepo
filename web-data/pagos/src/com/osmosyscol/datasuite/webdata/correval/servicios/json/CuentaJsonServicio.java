package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.math.BigDecimal;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

import co.htsoft.commons.lang.StringUtils;

public class CuentaJsonServicio implements JsonService {

	public void setSession(Session session) {
	}

	// ----------------------------------------

	public BigDecimal saldo(Integer id_producto, String cod_cuenta) {

		cod_cuenta = StringUtils.trimToNull(cod_cuenta);

		if (cod_cuenta == null || id_producto == null) {
			return null;
		}

		try {
			String sql = "select $ENCARGO.SALDO DISPONIBLE$ saldo from $ENCARGO$ where $ENCARGO.CODIGO$ = $S(" + cod_cuenta + ")$ and $ENCARGO.PRODUCTO$ = " + id_producto;

			return DS_SqlUtils.queryForObject(BigDecimal.class, sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// ----------------------------------------

}
