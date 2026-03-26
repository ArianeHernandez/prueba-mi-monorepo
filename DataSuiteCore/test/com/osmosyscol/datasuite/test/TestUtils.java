package com.osmosyscol.datasuite.test;

import java.util.Locale;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.InitDBConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class TestUtils {

	
	private static final String HOME = "../DataSuiteCore/configuracion/DATASUITE";

	public static void startUp() {

		try {

			AutenticacionServicio.INICIALIZAR_HILOS = false;
			Crypto.setTipoEncripcion(4);
			
			ContextInfo.getInstance().setRealPath(null);
			
			SimpleLogger.setLoggerEnabled(true);
			ParametrosInicio.loadProperties("DATASUITE", HOME);

			// ---------------------

			DaoConfig.setResourceDao("com/osmosyscol/datasuite/persistencia/dao/ibatis/config/dao.xml");
			DaoConfig.setResourceSqlMapConfig("com/osmosyscol/datasuite/persistencia/dao/ibatis/config/sql-map-config.xml");

			InitDBConfig.cargarConfiguraciones("DATASUITE", HOME);

			// ---------------------
			// Solo se cargan los archivos conf en modelatos

			String ct = ParametrosInicio.getProperty("crypto.type");

			if (ct != null) {
				Crypto.setTipoEncripcion(Integer.parseInt(ct));
			}
			else {
				Crypto.setTipoEncripcion(3);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

	}
	
}
