package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.utils.FtpUtils;

/**
 * Servicio de conexión a FTP para Correval PTE
 * 
 * @author hjdiaz
 */

public class FTPServicio {

	private static FTPServicio ftpServicio;
	
	private FtpUtils client = null;
	
	private FTPServicio(){
		
	}
	
	public static FTPServicio getInstance(){
		if (ftpServicio == null){
			ftpServicio = new FTPServicio();
		}
		return ftpServicio;
	}
	
	/**
	 * Verifica que se pueda conectar al ftp <code>host</code> en el puerto
	 * <code>puerto</code> y que se tenga acceso a las <code>carpetas</code>
	 * 
	 * @param host     host a conectarse
	 * @param puerto   puerto de conexión
	 * @param usuario  nombre del usuario
	 * @param clave    clave del usuario
	 * @param carpetas mapa que contiene las carpetas a las cuales intentará
	 * 				   conectarse
	 * @return <code>true</code> si se puede acceder, <code>false</code>
	 * en otro caso
	 */
	public Boolean verificarDatos(String host, Integer puerto, String usuario, String clave,  Map<String, String> carpetas){
		try {
			
			SimpleLogger.setDebug("FTPServicio.verificarDatos: conectandose al ftp [ftp://" + usuario + "@" + host + ":" + puerto + "]");
			
			client = new FtpUtils(host, puerto, usuario, clave);
			
			if (!client.connect()) {
				SimpleLogger.setError("Error en FTPServicio.verificarDatos:\n	No se logro la conexion al ftp [ftp://" + usuario + "@" + host + ":" + puerto + "]");
				return false;
			}
			
			return verificarDatos(client, carpetas);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FTPServicio.verificarDatos:", e);
			return false;	
		}
	}
	
	
	/**
	 * Verifica que se pueda conectar al ftp representado por el objeto
	 * <code>cliente</code> y que se tenga acceso a las <code>carpetas</code>
	 * 
	 * @param cliente   objeto de FtpUtils
	 * @param carpetas mapa que contiene las carpetas a las cuales intentará
	 * 				   conectarse
	 * @return <code>true</code> si se puede acceder, <code>false</code>
	 * en otro caso
	 */
	public Boolean verificarDatos(FtpUtils cliente,  Map<String, String> carpetas){
		try {
			
			if (cliente instanceof FtpUtils){
				
				client = cliente;
				
				Set<Entry<String, String>> s = carpetas.entrySet();
	            Iterator<Entry<String, String>> i = s.iterator();
	            while (i.hasNext()) {
	            	Entry<String, String> carpeta = i.next();
	            	String nombre = carpeta.getKey();
	            	String path = carpeta.getValue();
	            	SimpleLogger.setDebug("FTPServicio.verificarDatos: verificando carpeta " + path + " para " + nombre);
	            	if(!verificarCarpeta(nombre, path)){
	            		return false;
	            	}
	            }
				
				return true;
			}else {
				SimpleLogger.setError("Error en FTPServicio.verificarDatos: El parametro cliente no es un cliente valido");
				return false;
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FTPServicio.verificarDatos:", e);
			return false;	
		}
	}
	
	/**
	 * Retorna <code>true</code> si se tiene permisos para acceder a la carpeta
	 * especificada
	 * 
	 * @param nombre nombre del recurso al cuál será asignada la carpeta
	 * @param path ruta de la carpeta en el ftp
	 * @return <code>true</code> si se tiene permiso, <code>false</code> en otro caso
	 */
	private Boolean verificarCarpeta(String nombre, String path){
		
		if (client == null){
			SimpleLogger.setError("Error en FTPServicio.verificarCarpeta: No hay cliente disponible");
			return false;
		}
		
		File file = new File(ContextInfo.getInstance().getDiskPathForResource("resources/.test"));
		File file2 = new File(ContextInfo.getInstance().getDiskPathForResource("resources/." + StringUtils.randomString(8) + ".test2"));
		
		if (!client.setFile(file, path, file2.getName())) {
			SimpleLogger.setError("Error en FTPServicio.verificarCarpeta: No se puede escribir en la carpeta " + path + " configurada como " + nombre);
			return false;
		}

		if (!client.getFile(path, file2.getName(), file2)) {
			SimpleLogger.setError("Error en FTPServicio.verificarCarpeta: No se puede leer en la carpeta " + path + " configurada como " + nombre);
			return false;
		}

		if (file2.exists()) {
			file2.delete();
		}

		if (!client.deleteFile(path, file2.getName())) {
			SimpleLogger.setError("Error en FTPServicio.verificarCarpeta: No se puede borrar en la carpeta " + path + " configurada como " + nombre);
			return false;
		}
		
		return true;
		
	}
	
	public void desconectar(){
		if (client != null){
			client.disconnect();
			client = null;
		}
	}
	
}
