package com.osmosyscol.osmoautenticador.seguridad.ldap;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;


public class InitLDAPConfig {

	public static Map<String, ConfiguracionLDAP> cargarConfiguraciones() throws Exception {
		Map<String, ConfiguracionLDAP> configuraciones = new HashMap<String, ConfiguracionLDAP>();

		String OSMOAUTENTICADOR_HOME = System.getenv("OSMOAUTENTICADOR_HOME");
		if (StringUtils.isBlank(OSMOAUTENTICADOR_HOME)) {
			throw new Exception("Configuraci\u00F3n de varible de entorno OSMOAUTENTICADOR_HOME inválida. Verifique la instalaci\u00F3n. El valor actual es OSMOAUTENTICADOR_HOME=[" + OSMOAUTENTICADOR_HOME + "]");
		}
		String path = OSMOAUTENTICADOR_HOME + "/config/ldap";
		System.out.println("path: " + path);
	

		File file = new File(path);

		File[] archivos = file.listFiles();
		InputStream is = null;
		Properties ldapProps = null;
		if (archivos != null) {
			for (int i = 0; i < archivos.length; i++) {
				File archivo = archivos[i];
				if (archivo.isFile()) {

					try {
						is = new FileInputStream(archivo);
						ldapProps = new Properties();
						ldapProps.load(is);

						String identificador = ldapProps.getProperty("identificador");
						ConfiguracionLDAP configuracionLDAP = new ConfiguracionLDAP();

						configuracionLDAP.setApellidos(ldapProps.getProperty("Apellidos"));
						configuracionLDAP.setBaseName(ldapProps.getProperty("baseName"));
						configuracionLDAP.setDocumento(ldapProps.getProperty("documento"));
						configuracionLDAP.setInstalacion(Integer.parseInt(ldapProps.getProperty("instalacion")));
						configuracionLDAP.setNombre(ldapProps.getProperty("nombre"));
						configuracionLDAP.setNombreCompleto(ldapProps.getProperty("NombreCompleto"));
						configuracionLDAP.setNombres(ldapProps.getProperty("Nombres"));
						configuracionLDAP.setPuerto(ldapProps.getProperty("puerto"));
						configuracionLDAP.setServerIP(ldapProps.getProperty("serverIP"));
						configuracionLDAP.setVersionldap(ldapProps.getProperty("versionldap"));
						configuracionLDAP.setIdentificador(ldapProps.getProperty("identificador"));

						configuraciones.put(identificador, configuracionLDAP);
					} catch (Exception e) {
						SimpleLogger.setInfo("Error al cargar onfiguracion del LDAP", e);
					}
				}
			}
		}

		return configuraciones;
	}

}
