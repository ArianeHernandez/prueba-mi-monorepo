package com.osmosyscol.datapi.logica.xml;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class XmlXpathReader {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(XmlXpathReader.class);
	// #endregion 01 -------------------------------------
	
	
	public static String evaluate(File archivo, String consulta) throws Exception {
		try{
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			InputSource inputSource = new InputSource(new FileInputStream(archivo));
			
			return xPath.evaluate(consulta, inputSource);
		}catch (Exception e) {
			logger.error("Error al evaluar el Path", e);
			throw new Exception(e);
		}
	}

}
