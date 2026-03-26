/*
 * Creado el 22/08/2005
 */
package com.osmosyscol.commons.rpc.jsonrpc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.osmosyscol.commons.servlet.ContextInfo;

/**
 * Clase que implementa funciones de registro de objetos en el puente
 * JSONRPCBridge.
 * 
 * @author Carlos Herrera
 * @version 0.1 a1
 */

public class Configurator {
	private final static Logger log = Logger.getLogger(Configurator.class.getName());
	//public static final String CONFIGURATION_FILE = (new File(Configurator.class.getResource("/").getPath()).getParentFile().getAbsolutePath() + "/json-services.xml").replaceAll("%20", " ");
	public static final String CONFIGURATION_FILE = ContextInfo.getInstance().getDiskPathForResource( "WEB-INF/json-services.xml").replaceAll("%20", " ");

	/**
	 * Registra los servicios descritos en el archivo
	 * <code>CONFIGURATION_FILE</code> en el JSONRPCBridge.
	 * 
	 * @throws SAXException
	 *             Excepción al procesar o registrar servicios a partir del
	 *             archivo XML.
	 * @throws IOException
	 *             No es posible leer archivo de servicios.
	 */
	public static void registerServices() throws SAXException, IOException {
		
		System.out.println("CONFIGURATION_FILE: "+ CONFIGURATION_FILE);
		
		
		// Estableciendo receptor de eventos
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		// Estableciendo manejador de eventos de contenido
		parser.setContentHandler(ServiceConsumer.getInstance());
		// Procesar entrada
		parser.parse(new InputSource(new FileInputStream(new File(CONFIGURATION_FILE))));
	}

	/**
	 * Crea una instancia de clase con el nombre de la misma empleando un
	 * mecanismo multi-class-loader
	 * 
	 * @param className
	 *            nombre de la clase
	 * @return un objeto de clase <code>className</code>
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public static Object instanceClass(String className) throws InstantiationException, IllegalAccessException, IOException {
		Object object = null;
		try {
			object = Class.forName(className, true, Thread.currentThread().getContextClassLoader()).newInstance();
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "Not found for multi-classloader: " + className);
			try {
				object = Class.forName(className).newInstance();
			} catch (ClassNotFoundException e1) {
				log.log(Level.SEVERE, "Not found for single-classloader: " + className);
			}
		}
		return object;
	}
}