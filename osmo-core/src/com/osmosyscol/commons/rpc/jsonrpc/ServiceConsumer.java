/*
 * Creado el 23/08/2005
 */
package com.osmosyscol.commons.rpc.jsonrpc;

import java.io.IOException;
import java.util.Stack;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.osmosyscol.commons.log.SimpleLogger;

/**
 * @author Carlos Herrera
 * @version 0.1
 */
public final class ServiceConsumer extends DefaultHandler {
	private final static Logger log = Logger.getLogger(ServiceConsumer.class.getName());

	@SuppressWarnings("unchecked")
	private Stack stack;
	private Service service;
	private Locator locator;
	// Referencia a la unica instancia de la clase
	private static ServiceConsumer instance;

	public static synchronized ServiceConsumer getInstance() {
		if (instance == null) {
			instance = new ServiceConsumer();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void startDocument() throws SAXException {
		log.info("Registering services in " + Configurator.CONFIGURATION_FILE + " file");
		stack = new Stack();
	}

	public void endDocument() throws SAXException {
		log.info("End of " + Configurator.CONFIGURATION_FILE + " file");
	}

	public void endElement(String namespace, String local, String name) throws SAXException {
		if ("service".equals(name)) {
			SimpleLogger.setInfo("endElement().... [" + service + "]");
			register(service);
		}
	}

	@SuppressWarnings("unchecked")
	public void startElement(String namespace, String local, String name, Attributes attrs) throws SAXException {
		stack.push(name);
		if ("service".equals(name)) {
			service = new Service();
			service.setUrn(attrs.getValue("urn"));
			service.setClassname(attrs.getValue("class"));
		}

		else if (name.length() == 0)
			throw new SAXParseException("XML names not available", locator);
	}

	public void characters(char buf[], int off, int len) throws SAXException {
		// Para usos futuros
		@SuppressWarnings("unused")
		String top = getCurrentElementName();
		@SuppressWarnings("unused")
		String value = new String(buf, off, len);
	}

	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	private String getCurrentElementName() {
		return (String) stack.peek();
	}

	private void register(Service service) {
		String className = service.getClassname();
		String urn = service.getUrn();

		try {

			Object obj = Configurator.instanceClass(className);

			if (obj != null) {
				JSONRPCBridge.getGlobalBridge().registerObject(urn, obj);
				log.info("Object Registered succesfully: " + className);
			}

		} catch (InstantiationException e) {
			SimpleLogger.setError("Error", e);
		} catch (IllegalAccessException e) {
			SimpleLogger.setError("Error", e);
		} catch (IOException e) {
			SimpleLogger.setError("Error", e);
		}
	}
}
