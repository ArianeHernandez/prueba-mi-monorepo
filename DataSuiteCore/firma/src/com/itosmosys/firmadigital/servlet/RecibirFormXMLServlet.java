package com.itosmosys.firmadigital.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.itosmosys.firmadigital.servicios.FirmaServicio;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;

public class RecibirFormXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doValidar(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);
		}
	}

	private void doValidar(HttpServletRequest request, HttpServletResponse response) {

		Boolean valido = false;
		try{
			Map<String, Object> params = ServletUtils.getParameters(request);
			String xmlSignature = (String)params.get("docXmlsignature");
			valido = FirmaServicio.getInstance().validarFormXML(xmlSignature);
			
						
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(new InputSource(new StringReader(xmlSignature)));
	              
	        //Obtener Parametros del Formulario
	        Map<String, Object> parametros = obtenerParametros(doc);
	        SimpleLogger.setDebug("Parametros obtenidos del FormXMLFirmado" + parametros);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		
		//********************************************************************************//
		response.setContentType("text/html; charset=iso-8859-1");
		try {
			PrintWriter out = response.getWriter();
			out.println(valido);
			out.close();
		} catch (IOException e) {
			SimpleLogger.setError("Error", e);
		}
	}

	private Map<String, Object> obtenerParametros(Document doc) throws Exception{
		
//		String path = ParametrosInicio.getInstance().getCarpeta() + "/Firmformtemp.xml";
		String path = "c:/firma" + "/Firmformtemp.xml";
		OutputStream os = new FileOutputStream(path);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
		os.close();

		File file = new File(path); 
		
		XPathFactory xpfactory = XPathFactory.newInstance();
		XPath xPath = xpfactory.newXPath();
		
		InputSource inputSource = new InputSource(new FileInputStream(file));
		Integer cantidadParametros = new Integer( xPath.evaluate("count(firmar_formulario/*)", inputSource));
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		for (int i = 1; i < cantidadParametros; i++) {
			InputSource inputSourceNombre = new InputSource(new FileInputStream(file));
			InputSource inputSourceValor = new InputSource(new FileInputStream(file));
			String nombre = xPath.evaluate("firmar_formulario/*[position()= " + i + "]/@name", inputSourceNombre);
			String valor = xPath.evaluate("firmar_formulario/*[position()=" + i + "]", inputSourceValor);
			parametros.put(nombre, valor);
		}
//		file.delete();
		return parametros;
	}

}
