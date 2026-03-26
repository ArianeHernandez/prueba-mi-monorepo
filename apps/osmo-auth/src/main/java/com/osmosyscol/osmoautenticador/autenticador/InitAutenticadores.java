package com.osmosyscol.osmoautenticador.autenticador;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class InitAutenticadores {

	public static void cargarAutenticadores(String sysvar) throws Exception {

		SimpleLogger.setInfo("Inicia carga autenticadores: " + sysvar);

		String PATHSYS = System.getenv(sysvar);
		if (StringUtils.esVacio(PATHSYS)) {
			SimpleLogger.setError("Configuraci\u00F3n de varible de entorno " + sysvar + " inválida. Verifique la instalaci\u00F3n.");
			return;
		}

		String path = PATHSYS + "/config/autenticadores";
		SimpleLogger.setInfo("Ibatis --> Cargando Configuracion: " + path);

		List<String> rutasConfiguracion = new LinkedList<String>();
		List<String> idsAutenticadores = new LinkedList<String>();
		List<String> listaAutenticadores = new LinkedList<String>();

		File file = new File(path);

		if (!file.exists()) {
			SimpleLogger.setError("No existe la carpeta de configuracion " + path);
			return;
		}

		File[] archivos = file.listFiles();
		InputStream is = null;
		Properties autProps = null;
		if (archivos != null) {
			for (int i = 0; i < archivos.length; i++) {
				File archivo = archivos[i];
				if (archivo.isFile()) {
					try {
						is = new FileInputStream(archivo);
						autProps = new Properties();
						autProps.load(is);

						String clase = autProps.getProperty("autenticador.class");
						String id = autProps.getProperty("autenticador.id");
						String conf = System.getenv(ParametrosInicio.SYSVAR);

						if(clase == null || id == null){
							SimpleLogger.setWarn("El archivo " + archivo + " no es un archivo de propiedades valido");
						}else{
							rutasConfiguracion.add(conf);
							idsAutenticadores.add(id);
							listaAutenticadores.add(clase);
						}

					} catch (Exception e) {
						SimpleLogger.setError("Ha ocurrido un error al cargar la configuracion de conexion del archivo [" + archivo + "]", e);
					}
				}
			}

			try {

				GrupoAutenticacionFactory.cargarAutenticadores(listaAutenticadores, idsAutenticadores, rutasConfiguracion);
				
			} catch (Exception e) {
				
				SimpleLogger.setError(
						"Ha ocurrido un error en la carga de autenticadores", e);
			}
		}

	}

}
