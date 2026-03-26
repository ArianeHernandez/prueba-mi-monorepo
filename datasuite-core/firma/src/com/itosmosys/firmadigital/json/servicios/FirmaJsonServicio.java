package com.itosmosys.firmadigital.json.servicios;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.environment.Session;
import org.xml.sax.InputSource;

import com.itosmosys.firmadigital.servicios.FirmaServicio;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class FirmaJsonServicio implements JsonService{

	private Session session;
	
	public void setSession(Session session) {
		this.session= session;
	}

	
	public static int NUM_EJECUCION = 0;

	public static Map<String, Map<String, String>> parametros = new HashMap<String, Map<String, String>>();

	public synchronized String validarFormXML(String xmlSignature) {
		boolean valido = FirmaServicio.getInstance().validarFormXML(xmlSignature);

		if (valido) {

			String idp = obtenerIdArchivo();

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();

			try {

				Map<String, String> p = new HashMap<String, String>();

				Integer cantidad = Integer.parseInt(xPath.evaluate("count(/INFO/*)", new InputSource(StringUtils.toInputStream(xmlSignature))));

				for (int i = 1; i <= cantidad; i++) {
					String parametro = xPath.evaluate("/INFO/*[" + i + "]/@name", new InputSource(StringUtils.toInputStream(xmlSignature)));
					String valor = xPath.evaluate("/INFO/*[" + i + "]", new InputSource(StringUtils.toInputStream(xmlSignature)));

					p.put(parametro, valor);
				}

				parametros.put(idp, p);

				return idp;

			} catch (Throwable e) {
				SimpleLogger.setError("Error al cargar parametros", e);
			}
		}

		return null;

	}

	public synchronized Boolean recibirParte(String parte, int posParte, int tamArch, String idArch) {
		return FirmaServicio.getInstance().recibirParte(parte, posParte, tamArch, idArch);
	}

	public synchronized String obtenerIdArchivo() {
		NUM_EJECUCION++;
		return NUM_EJECUCION + "_" + StringUtils.randomString(32);
	}
	
	public synchronized Boolean validarDocumento(String idArch) {
		return FirmaServicio.getInstance().validarDocumento(idArch);
	}
	
	/** Funcion que permite validar un el xml firmado de un formulario. En este caso la firma no es attach
	 * 
	 * @param formXML xml del documento en forma clara
	 * @param formXMLSigned  firma del documento xml 
	 * @param faseProceso fase del proceso en la que se realiza la firma
	 * @return
	 */
	
	public synchronized String validarFormXMLSigned(String formXML, String formXMLSigned, Integer id_carga, String faseProceso){
		
		String id_documento_firmado = obtenerIdArchivo();
		String login = obtenerLogin();
		Boolean esValido= false;
		
		if(login!= null && id_documento_firmado!=null){
			esValido = FirmaServicio.getInstance().validarFormXMLSigned(formXML, formXMLSigned, login , id_documento_firmado, id_carga, faseProceso);
		}
		if (esValido) {

		
			String idp = id_documento_firmado;

			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();

			try {

				Map<String, String> p = new HashMap<String, String>();

				Integer cantidad = Integer.parseInt(xPath.evaluate("count(/INFO/*)", new InputSource(StringUtils.toInputStream(formXML))));

				for (int i = 1; i <= cantidad; i++) {
					String parametro = xPath.evaluate("/INFO/*[" + i + "]/@name", new InputSource(StringUtils.toInputStream(formXML)));
					String valor = xPath.evaluate("/INFO/*[" + i + "]", new InputSource(StringUtils.toInputStream(formXML)));
					
					valor = URLDecoder.decode(valor, "ISO-8859-1");
					
					p.put(parametro, valor);
				}

				parametros.put(idp, p);

				return idp;

			} catch (Throwable e) {
				SimpleLogger.setError("Error al cargar parametros", e);
			}
		}
		
		return null;
		
	}
	
	/***
	 * Esta funcion permite validar si un documento p7z es valido. El archivo debe estar subido
	 * al servidor para la validacion correspondiente.
	 * 
	 * @param id_archivo_firmado identificador del archivo firmado que se haya subido al servidor
	 * @param id_carga  numero de la carga a la cual esta asociado el documento firmado
	 * @param faseProceso  Nombre de la fase del proceso en se realiza la firma 
	 * @return
	 */
	
	public synchronized Boolean validarDocumentoFirmado(String id_archivo_firmado, Integer id_carga, String faseProceso){
		//Se revisa si el documento subido es valido
		String login = obtenerLogin();
		Boolean esValido = false;
		
		if(login!=null){
			esValido = FirmaServicio.getInstance().validarDocumentoFirmado(id_archivo_firmado, login, id_carga, faseProceso);
		}
		
		return esValido;
	}
	
	private String obtenerLogin(){
		String usa_login_web = ParametrosInicio.getProperty("system.login_web");
		if (StringUtils.esVerdad(usa_login_web)){
			return (String) session.getAttribute("login_web");	
		}
		return (String) session.getAttribute("login");
	}
	
}
