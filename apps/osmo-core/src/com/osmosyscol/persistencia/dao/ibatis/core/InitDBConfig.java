package com.osmosyscol.persistencia.dao.ibatis.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.utils.StringUtils;

public class InitDBConfig {
	
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(InitDBConfig.class);
	// #endregion 01 -------------------------------------

	public static String SYSVAR = null;

	public static void cargarConfiguraciones() throws Exception {
		cargarConfiguraciones(ParametrosInicio.SYSVAR);
	}

	public static void cargarConfiguraciones(String sysvar) throws Exception {
		cargarConfiguraciones(sysvar, null);
	}

	/**
	 * Inicia la lectura del paquete raiz de configuracion.
	 * 
	 * @param sysvarValue
	 *            valor del path raiz. Si este valor es nulo se usa la carga por el parametro variable de entorno
	 * @param sysvar
	 *            variable de entorno, a ser leida si el valor del parametro sysvarValue es nulo
	 */
	public static void cargarConfiguraciones(String sysvar, String sysvarValue) throws Exception {

		// ----------------------

		SYSVAR = sysvar;

		logger.info("Inicia carga configuraciones de conexion (Ibatis): " + SYSVAR);

		String PATHSYS = ParametrosInicio.Info.getConfigurationVariableValue(SYSVAR, sysvarValue);
		if (StringUtils.esVacio(PATHSYS)) {
			logger.error("Configuración de varible de entorno " + SYSVAR + " inválida. Verifique la instalación.");
			return;
		}

		String path = PATHSYS + "/config/db";
		logger.info("Ibatis --> Cargando Configuracion: " + path);

		File file = new File(path);

		if (!file.exists()) {
			logger.error("No existe la carpeta de configuracion " + path);
			return;
		}

		File[] archivos = file.listFiles();
		InputStream is = null;
		Properties ldapProps = null;
		if (archivos != null) {
			for (int i = 0; i < archivos.length; i++) {
				File archivo = archivos[i];
				if (archivo.isFile()) {

					logger.info("Cargando configuracion de archivo: " + archivo.getAbsolutePath());

					is = null;
					try {
						is = new FileInputStream(archivo);
						ldapProps = new Properties();
						ldapProps.load(is);
						
						// TODO: refactor this (use directly the properties to the DaoConfig.startConnection method)

						Integer id = Integer.parseInt(ParametrosInicio.getPropertyValue(ldapProps, "database.id"));
						String driver = ParametrosInicio.getPropertyValue(ldapProps, "database.driver");
						String url = ParametrosInicio.getPropertyValue(ldapProps, "database.url");
						String user = ParametrosInicio.getPropertyValue(ldapProps, "database.user");
						String password = ParametrosInicio.getPropertyValue(ldapProps, "database.password");
						String name = ParametrosInicio.getPropertyValue(ldapProps, "database.name");
						boolean defecto = StringUtils.esVerdad(ParametrosInicio.getPropertyValue(ldapProps, "database.defecto"));
						// Propiedades adicionales
						String maxActive = ParametrosInicio.getPropertyValue(ldapProps, "database.maxActive");
						String maxIdle = ParametrosInicio.getPropertyValue(ldapProps, "database.maxIdle");
						String maxWait = ParametrosInicio.getPropertyValue(ldapProps, "database.maxWait");
						String validationQuery = ParametrosInicio.getPropertyValue(ldapProps, "database.validationQuery");
						String testWhileIdle = ParametrosInicio.getPropertyValue(ldapProps, "database.testWhileIdle");
						String testOnBorrow = ParametrosInicio.getPropertyValue(ldapProps, "database.testOnBorrow");
						String timeBetweenEvictionRunsMillis = ParametrosInicio.getPropertyValue(ldapProps, "database.timeBetweenEvictionRunsMillis");
						String initialSize = ParametrosInicio.getPropertyValue(ldapProps, "database.initialSize");
						Boolean accessToUnderlyingConnectionAllowed = parseBoolean( ParametrosInicio.getPropertyValue(ldapProps,IConfigKeys.ACCESSTOUNDERLYINGCONNECTIONALLOWED_EXTERNAL) );

						DaoConfig.startConecction(id, driver, url, user, password, name, defecto, maxActive, maxIdle, maxWait, validationQuery, testWhileIdle, testOnBorrow, timeBetweenEvictionRunsMillis, initialSize, accessToUnderlyingConnectionAllowed);

					} catch (Exception e) {
						logger.error("Ha ocurrido un error al cargar la configuracion de conexion del archivo [" + archivo + "]", e);
					} finally {
						if (is != null) {
							is.close();
						}
					}
				}
			}
		} else {
			logger.error("No se encontraron archivos de configuracion para la base de datos [" + path + "]");
		}
	}

	private static Boolean parseBoolean(String propertyValue) {
		if( StringUtils.esVacio(propertyValue)){
			return null;
		}
		return Boolean.valueOf(propertyValue);
	}

}
