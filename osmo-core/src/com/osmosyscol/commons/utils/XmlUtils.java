/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.osmosyscol.commons.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.osmosyscol.commons.utils.proc.ActionProcessor;

/**
 *
 * @author ahurtado
 */
public class XmlUtils {
	
    private static final Logger log  = LoggerFactory.getLogger(XmlUtils.class);

    //
    DocumentBuilderFactory documentBuilderFactory = null;
    DocumentBuilder documentBuilder = null;
    //
    TransformerFactory transformerFactory = null;
    Transformer transformer = null;

    public XmlUtils() {
        try {
        // parse utils
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        // transform utils
        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//	    	TRANSFORM_DEFAULT01.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
//	    	TRANSFORM_DEFAULT01.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
//	    	TRANSFORM_DEFAULT01.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//	    	TRANSFORM_DEFAULT01.setOutputProperty(OutputKeys.INDENT, "no"); // yes, no
//	    	TRANSFORM_DEFAULT01.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        }
        catch( Exception e ) {
            String errorType ="error inicializando xmlUtils";
            log.error(errorType, e);
            throw new RuntimeException(errorType,e);
        }

    }
    /**
     * convierte un string en un document y extrae su nodo raiz
     * @param stringValue
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Element w3cStringToXml(String stringValue) throws ParserConfigurationException, SAXException, IOException {
        long initTime = ActionProcessor.calculateTime();
        StringReader stringReader = new StringReader(stringValue);
        Document document = documentBuilder.parse(new InputSource(stringReader));
        long stopTime = ActionProcessor.calculateTime();

        if( log.isDebugEnabled() ) {
            log.debug( String.format( "parsing time %d ms", stopTime - initTime ) );
        }

        return document.getDocumentElement();
    }

/**
     * convierte un elemento xml en un string
     * @param value
     * @return
     */
    public String w3cXmlToString(Element value ) {
        try {
            StringWriter stringWriter = new StringWriter();
            transformer.transform( new DOMSource(value ), new StreamResult( stringWriter)  );
            return stringWriter.toString();
        }
        catch( Exception e ) {
            ; // ignore
            return null;
        }
    }

}
