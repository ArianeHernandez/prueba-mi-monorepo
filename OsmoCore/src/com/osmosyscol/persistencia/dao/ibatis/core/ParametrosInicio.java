package com.osmosyscol.persistencia.dao.ibatis.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servicio.GraficoSystemInfoServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.rsa.CoreRSA;
import com.osmosyscol.commons.utils.rsa.RSA;

public class ParametrosInicio {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(ParametrosInicio.class);
	// #endregion 01 -------------------------------------

	public final static Map<String, Object> propiedades = new HashMap<String, Object>();
	public static boolean initialized = false;

	private static Properties properties = null;

	public static String SYSVAR = null;
	public static String SYSVAR_VALUE = null;

	public static Boolean ISDEBUG = false;

	public static String getProperty(String name) {
		try {

			String val = getPropertyValue(properties, name);

			System.out.println("Propiedad: " + name + " = " + val);

			return val;

		} catch (Throwable e) {

			logger.warn("No se puede obtener la propiedad [" + name + "]", e);
			return null;
		}
	}

	// ---------------------

	private ParametrosInicio() {
	}

	/**
	 * Carga archivo de propiedades dentro de la carpeta $sysvar/config/config.properties
	 * 
	 * @param sysvar
	 * @return
	 */
	public static Properties loadProperties(String sysvar) {
		return loadProperties(sysvar, null);
	}

	/**
	 * Carga archivo de propiedades dentro de la carpeta $sysvalue/config/config.properties
	 * 
	 * @param sysvar
	 * @param sysvarValue
	 * @return
	 */
	public static Properties loadProperties(String sysvar, String sysvarValue) {
		return loadProperties(sysvar, sysvarValue, true);
	}

	/**
	 * Carga archivo de propiedades dentro de la carpeta $sysvalue/config/config.properties, permite definir si se inicia el grafico de la pagina system.info
	 *
	 * @param sysvar
	 * @param sysvarValue
	 * @param iniciarGrafico
	 * @return
	 */
	public static Properties loadProperties(String sysvar, String sysvarValue, Boolean iniciarGrafico) {

		if (iniciarGrafico) {
			GraficoSystemInfoServicio.getInstance().start();
		}

		AutenticacionServicio.getInstance();

		logger.info("Inicia carga de archivo de configuracion [" + sysvar + "] para aplicacion '" + AutenticacionServicio.NOMBRE_APLICACION.toUpperCase() + "', default-dir=" + sysvarValue);

		SYSVAR = sysvar;
		SYSVAR_VALUE = sysvarValue;

		properties = null;

		try {

			// lectura condicional de la variable de entorno si sysvarvalue es nulo
			String DATASUITE = Info.getConfigurationVariableValue(SYSVAR, sysvarValue);

			String path = DATASUITE + "/config/config.properties";
			File archivo = new File(path);
			properties = new Properties();

			FileInputStream stream = new FileInputStream(archivo);
			properties.load(stream);

			stream.close();

			ISDEBUG = StringUtils.esVerdad(getProperty("system.debug"));

			SimpleLogger.activateDebug(ISDEBUG);

			try {
				String debug_maxsize = getProperty("system.debug_maxsize");
				if (NumberUtils.isDigits(debug_maxsize)) {
					SimpleLogger.setMaxsize(new Integer(getProperty("system.debug_maxsize")));
				} else if (StringUtils.isNotEmpty(debug_maxsize)) {
					SimpleLogger.setError("El valor del parametro system.debug_maxsize debe ser numerico.");
				}
			} catch (Exception e) {
				SimpleLogger.setError("El valor del parametro system.debug_maxsize no es valido.", e);
			}

			// ----------------------- Correo

			String smtp = getProperty("Correo.smtp");
			String user = getProperty("Correo.user");
			String pass = getProperty("Correo.pass");
			String ssl = getProperty("Correo.ssl");
			String starttls = getProperty("Correo.starttls");
			String from = getProperty("Correo.from");
			String fromName = getProperty("Correo.fromName");
			String maildebug = getProperty("Correo.maildebug");
			//Si Correo.authentication no esta configurada, se toma por defecto true
			String authentication = getProperty("Correo.authentication") == null ? "true" : getProperty("Correo.authentication");
			
			if (smtp != null) {
				EnviaMails.configure(smtp, user, pass, ssl != null && ssl.trim().equalsIgnoreCase("true"), starttls != null && starttls.trim().equalsIgnoreCase("true"), from, fromName, maildebug, StringUtils.esVerdad(authentication));
			} else {
				SimpleLogger.setWarn("No se encontro configuracion de envio Correo. Se ha desactivado en envio de Correo.");
			}

			for (Enumeration<?> e = properties.keys(); e.hasMoreElements();) {
				String key = e.nextElement().toString();
				propiedades.put(key, properties.getProperty(key));
			}

		} catch (Throwable e) {
			logger.error("Ha ocurrido un error al Cargar lo configuracion de Correo.", e);
		}

		initialized = true;
		return properties;
	}

	/**
	 * Permite agregar/reemplazar una propiedad de nombre /name/ y valor /value/
	 * 
	 * @param name
	 *            nombre de la propiedad
	 * @param value
	 *            valor de la propiedad
	 * @return true si la propiedad pudo ser agregada/reemplazada o false si la propiedad no pudo ser agregada porque no se han cargado propiedades desde el metodo loadProperties.
	 */
	public static synchronized boolean addProperty(String name, String value) {
		if (initialized) {
			properties.put(name, value);
			return true;
		} else {
			return false;
		}
	}

	public static String getPropertyValue(String data) {

		if (data != null) {
			if (data.indexOf("CRYPTO://") == 0) {
				return StringUtils.trimToNull(RSA.desencripta(data.substring(9), RSA.clave));
			}

			if (data.indexOf("CRYPTO_CORE://") == 0) {
				return StringUtils.trimToNull(CoreRSA.decifrar(data.substring(14), CoreRSA.clave));
			}
		}

		return data;
	}

	public static String getPropertyValue(Properties properties, String key) {

		String data = StringUtils.trimToNull(properties.getProperty(key));

		return getPropertyValue(data);
	}

	/* ******* #region 01: INNER CLASS ********** */
	/**
	 * Utilitario para realizar condicionalmente la lectura de cierta variable de entorno.
	 */
	public static class Info {

		/**
		 * Permite la lectura de una variable de entorno si y solo si el vaor del parametro sysvarValue es nulo; de lo contrario, usa el valor sysvarValue.
		 * 
		 */
		public static String getConfigurationVariableValue(String sysVar, String sysvarValue) {
			boolean useSystemEnvironmentVariable = (null == sysvarValue);

			if (useSystemEnvironmentVariable) {
				sysvarValue = System.getenv(sysVar);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Cargando configuracion [path=%s,system-property=%b]", sysvarValue, useSystemEnvironmentVariable));
			}
			return ((sysvarValue));
		}
	}

	/* ******* #endregion 01 ********** */
}
