package com.osmosyscol.commons.servlet;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.cocoon.environment.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Informacion sobre el contexto donde se ejecuta la aplicacion
 * (aplicable para contextos web)
 * @author ahurtado
 *
 */
public class ContextInfo {
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(new Object() { }.getClass().getEnclosingClass());
	// #endregion 01 -------------------------------------
	
	
	private static ContextInfo instance = null;
	
	public static ContextInfo getInstance() {
		if( null == instance ) {
			synchronized ( ContextInfo.class ) {
				if( null == instance ) {
					instance = new ContextInfo();
				}
			}
		}
		return instance;
	}
	
	/**
	 * configurado desde initApp e inyectado a la clase directamente
	 */
	private String APPLICATION_DISK_BASEPATH = null;
	
	
	public void autoConfigure( ServletContext context ) {
		if( logger.isInfoEnabled() )
			logger.info("loading local path of application ...");
		
		String realPath = context.getRealPath("/");
		
		if( logger.isInfoEnabled() )
			logger.info( String.format( "setting real path to %s" , realPath ) );
		
		setRealPath( realPath );
	}
	

	/**
	 * Permite configurar el path en disco para luego ubicar los archivos de configuracion
	 * @param applicationDiskBasePath
	 */
	public void setRealPath( String applicationDiskBasePath ) {
		logger.info("verifying property disk-path-base ...");
		File file = null;
		try {
			file = new File(applicationDiskBasePath);
			APPLICATION_DISK_BASEPATH = file.getAbsolutePath();
			logger.info("disk-path-base property verified:" + APPLICATION_DISK_BASEPATH );
		}
		catch( Exception e ) {
			logger.warn("error setting property disk-path-base");
		}
		finally {
			try {
				if( null == APPLICATION_DISK_BASEPATH || "".equals( APPLICATION_DISK_BASEPATH ) ) {
					String defaultPath =(new File(ContextInfo.class.getResource("/").getPath()).getParentFile().getParentFile().getAbsolutePath());
					logger.warn("....devolviendo la ubicacion por defecto (ubicacion relativa al classpath de esta clase rastreando root de la solucion)....:"+defaultPath);
					APPLICATION_DISK_BASEPATH = defaultPath;
				}
			}
			catch( Exception e ) {
				logger.error(".... error devolviendo la ubicacion por defecto relativa al path" , e );
			}
		}
		
	}
	/**
	 * Obtiene la direccion en disco de determinado recurso dentro de la aplicacion web
	 * @param request 
	 * @param request 
	 * @param relativeWebAppFileName
	 * @return
	 */
	public String getRealPath(Request request ) { 
		if( null == APPLICATION_DISK_BASEPATH || "".equals( APPLICATION_DISK_BASEPATH) ) {
			logger.warn( String.format( "ContextInfo.realPath aun no configurado. Revisar la entrada loadContextInfo en el descriptor de la solucion web. %s",
				new StringBuilder()
				.append(	"<servlet>\n" )
				.append(	" <servlet-name>loadContextInfo</servlet-name>\n" )
				.append(	" <servlet-class>com.osmosyscol.commons.servlet.LoadContextInfoServlet</servlet-class>\n" )
				.append(	" <load-on-startup>1</load-on-startup>\n" )
				.append(	"</servlet>" )
				.toString()
				)
			);
			
			return null;
			
		}
		
		return APPLICATION_DISK_BASEPATH;
	}
	/**
	 *  Obtiene la direccion en disco base para los recursos dentro de la aplicacion web
	 * @return la ruta completa del disco
	 */
	public String getRealPath() { 
		return getRealPath(null);
	}
	
	public String getDiskPathForResource( String relativeToContextPath ) {
		
		// si el modo de carga para obtener el path relativo a la maquina es el tradicional, 
		// se realiza el cargue relativo al context classloading de esta clase
		// este enfoque puede presentar problemas en otro tipo de contenedores
		// que tienen por separado las clases java y los recursos
		
		//		if( org.apache.commons.lang.StringUtils.equals( DISKLOCATION_MODE__RELATIVETOCLASSPATH, DISKLOCATION_MODE )) {
		//			String searchPath = org.apache.commons.lang.StringUtils.substringAfter( relativeToContextPath , "WEB-INF");
		//			return new File(AutenticacionServicio.class.getResource("/").getPath()).getParentFile().getAbsolutePath() + searchPath; 
		//		}
		
		//filename = new File(AutenticacionServicio.class.getResource("/").getPath()).getParentFile().getAbsolutePath() + "/actions.xml";
		return getRealPath(null) + System.getProperty("file.separator") + relativeToContextPath;
	}	
	
}
