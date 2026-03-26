package com.itosmosys.firmadigital.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ibatis.dao.client.DaoManager;
import com.itosmosys.firmadigital.dto.DocumentoFirmado;
import com.itosmosys.firmadigital.firma.PKCS7VerUtil;
import com.itosmosys.firmadigital.firma.VerifierDocumentSigned;
import com.itosmosys.firmadigital.firma.VerifierFormXMLSigned;
import com.itosmosys.firmadigital.firma.XmlSignatureVerUtil;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.persistencia.dao.FirmaDigitalDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class FirmaServicio {

	private static FirmaServicio instance;

	public static FirmaServicio getInstance() {
		if (instance == null) {
			instance = new FirmaServicio();
		}
		return instance;

	}

	private FirmaServicio() {
	}

	public Boolean validarFormXML(String xmlSignature) {

		Boolean valido = false;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlSignature)));

			valido = XmlSignatureVerUtil.validarFirmarXML(doc);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return valido;

	}

	HashMap<String, String[]> archivosMultiPartes = new HashMap<String, String[]>();

	public Boolean recibirParte(String parte, int posParte, int tamArch, String idArch) {

		try {
			if (!archivosMultiPartes.containsKey(idArch)) {
				archivosMultiPartes.put(idArch, new String[tamArch]);
			}

			archivosMultiPartes.get(idArch)[posParte] = parte;

			int ultimaPos = tamArch - 1;
			if (posParte == ultimaPos) {
				Boolean valido = validarDocumento(idArch);
				archivosMultiPartes.remove(idArch);
			}

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	public Boolean validarDocumento(String idArch) {

		byte[] datosArchivoFirmado = obtenerBytesArchivo(idArch);

		String ruta = ParametrosInicio.getProperty("file.carpeta") + "/tmp_" + idArch + ".p7";

		File file = new File(ruta);

		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			out.write(datosArchivoFirmado);
		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);
		}

		Boolean valido = false;

		try {

			valido = PKCS7VerUtil.validatePkcs7Signature2(ruta, ruta.split(".p7")[0]);

			// falta borrar el archivo despues de verificar

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return valido;
	}

	private byte[] obtenerBytesArchivo(String idArch) {

		String[] archivoMultiPart = archivosMultiPartes.get(idArch);

		// Se crea el arreglo de bytes
		int tamanioDatos = 0;
		for (int i = 0; i < archivoMultiPart.length; i++) {
			tamanioDatos += archivoMultiPart[i].split(";").length;
		}
		byte[] datosArchivoFirmado = new byte[tamanioDatos];

		// Se construye el archivo como un arreglo de bytes
		int cursor = 0;
		for (int i = 0; i < archivoMultiPart.length; i++) {

			String[] parte = archivoMultiPart[i].split(";");

			for (int j = 0; j < parte.length; j++) {
				datosArchivoFirmado[cursor] = new Integer(parte[j]).byteValue();
				cursor++;
			}
		}

		return datosArchivoFirmado;
	}

	
	public Boolean validarFormXMLSigned(String formXML, String formXMLSigned, String login, String id_documento_firmado, Integer id_carga, String faseProceso){
		
		VerifierFormXMLSigned verifierFormXMLSigned= new VerifierFormXMLSigned();
		Boolean esValido = verifierFormXMLSigned.verifyFormXMLSigned(formXML, formXMLSigned) || ParametrosInicio.ISDEBUG;
		
		//Se crea registro de auditoria
		Date fechaRegistro = HorarioServicio.getInstance().obtenerFechaActual();
		
		DocumentoFirmado documentoFirmado = new DocumentoFirmado();
		documentoFirmado.setId_documento_firmado(id_documento_firmado);
		documentoFirmado.setTexto_claro(formXML);
		documentoFirmado.setTexto_firmado(formXMLSigned);
		documentoFirmado.setFecha_registro(fechaRegistro);
		documentoFirmado.setLogin_usuario_firma(login);
		documentoFirmado.setId_carga(id_carga);
		documentoFirmado.setFase_proceso(faseProceso);
		
		//Se establece si el documentoFirmado es valido
		if(esValido){
			documentoFirmado.setValido("S");
		}else{
			documentoFirmado.setValido("N");
		}
		
		crearAuditoriaDocumento(documentoFirmado);
		
		return esValido;
		
		
	}
	
	public Boolean validarDocumentoFirmado(String id_documento_firmado, String login, Integer id_carga, String faseProceso){
		
		Boolean esValido = false;
		
		if(id_documento_firmado!=null && id_documento_firmado.length()>0){
			String rutaArchivoP7Firmado = ParametrosInicio.getProperty("file.carpeta") + "/tmp_" +id_documento_firmado+".p7";
			String rutaArchivoP7FirmadoAuditoria = ParametrosInicio.getProperty("file.carpeta")+"/ARCHIVOS_FIRMA"+"/tmp_" +id_documento_firmado+".p7";
			
			
			File archivoP7Firmado = new File(rutaArchivoP7Firmado);
			File archivoP7FirmadoAuditoria = new File(rutaArchivoP7FirmadoAuditoria);
			
			
			if(archivoP7Firmado.exists() && archivoP7FirmadoAuditoria.exists()){
				
				VerifierDocumentSigned verifierDocumentSigned = new VerifierDocumentSigned();
			
				//Se revisa el documento de auditoria pero se almacena el contenido del documento firmado en una la ruta comun para la subida de archivos
				esValido = verifierDocumentSigned.verifyDocumentSigned(rutaArchivoP7FirmadoAuditoria, rutaArchivoP7Firmado.split(".p7")[0]) || ParametrosInicio.ISDEBUG;
				
				//Se crea registro de auditoria
				Date fechaRegistro = HorarioServicio.getInstance().obtenerFechaActual();
				
				DocumentoFirmado documentoFirmado = new DocumentoFirmado();
				documentoFirmado.setId_documento_firmado(id_documento_firmado);
				documentoFirmado.setRuta_documento_firmado(rutaArchivoP7FirmadoAuditoria);
				documentoFirmado.setFecha_registro(fechaRegistro);
				documentoFirmado.setLogin_usuario_firma(login);
				documentoFirmado.setId_carga(id_carga);
				documentoFirmado.setFase_proceso(faseProceso);
				
				//Se establece si el documentoFirmado es valido
				if(esValido){
					documentoFirmado.setValido("S");
				}else{
					documentoFirmado.setValido("N");
				}
				
				crearAuditoriaDocumento(documentoFirmado);
			
			}
		
		}
		
				
		return esValido;
	}
	
	public Boolean crearAuditoriaDocumento(DocumentoFirmado documentoFirmado){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FirmaDigitalDao firmaDigitalDao= (FirmaDigitalDao) daoManager.getDao(FirmaDigitalDao.class);

			return firmaDigitalDao.crearAuditoriaDocumento(documentoFirmado);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
	}
	
	public Boolean actualizarIDCargaParaDocumentoAuditoria(String id_documento_firmado, Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FirmaDigitalDao firmaDigitalDao= (FirmaDigitalDao) daoManager.getDao(FirmaDigitalDao.class);

			return firmaDigitalDao.actualizarIDCargaParaDocumentoAuditoria(id_documento_firmado, id_carga);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
	}
	
	
	/**
	 * Permite revisar si una carga tiene documento firmado en la fase de preparacion
	 * 
	 */
	
	public Boolean tieneDocumentoFirmadoPorFasePreparacion(Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FirmaDigitalDao firmaDigitalDao= (FirmaDigitalDao) daoManager.getDao(FirmaDigitalDao.class);

			Boolean tieneFirmaPorFormularioInteractivo =  firmaDigitalDao.tieneFormularioFirmadoPorFaseProceso(id_carga, "PREPARACION INDIVIDUAL");
			Boolean tieneFirmaPorDocumentoMasivo = firmaDigitalDao.tieneDocumentoFirmadoPorFaseProceso(id_carga, "PREPARACION MASIVA");
			
			if(tieneFirmaPorFormularioInteractivo || tieneFirmaPorDocumentoMasivo){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
		
	}
	
	public Boolean tieneFormularioFirmadoPorFasePreparacion(Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FirmaDigitalDao firmaDigitalDao= (FirmaDigitalDao) daoManager.getDao(FirmaDigitalDao.class);

			Boolean tieneFirmaPorFormularioInteractivo =  firmaDigitalDao.tieneFormularioFirmadoPorFaseProceso(id_carga, "PREPARACION INDIVIDUAL");
			Boolean tieneFirmaPorFormularioMasivo = firmaDigitalDao.tieneFormularioFirmadoPorFaseProceso(id_carga, "PREPARACION MASIVA");
			
			if(tieneFirmaPorFormularioInteractivo || tieneFirmaPorFormularioMasivo){
				return true;
				
			}else{
				return false;
				
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
		
	}
	
	
	/**
	 * Permite revisar si una carga tiene formulario firmado en la fase de liberacion
	 * 
	 */
	
	public Boolean tieneFormularioFirmadoPorFaseLiberacion(Integer id_carga){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			FirmaDigitalDao firmaDigitalDao= (FirmaDigitalDao) daoManager.getDao(FirmaDigitalDao.class);

			return firmaDigitalDao.tieneFormularioFirmadoPorFaseProceso(id_carga, "LIBERACION");
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		ContextInfo.getInstance().setRealPath(null);
		
		InitApp.startUp();
		
		Integer id_carga = 11533;
		
		SimpleLogger.setDebug("Tiene documento por fase preparacion"+FirmaServicio.getInstance().tieneDocumentoFirmadoPorFasePreparacion(id_carga));
																								
		SimpleLogger.setDebug("Tiene formulario por fase preparacion"+FirmaServicio.getInstance().tieneFormularioFirmadoPorFasePreparacion(id_carga));
		
		SimpleLogger.setDebug("Tiene formulario por fase liberacion"+FirmaServicio.getInstance().tieneFormularioFirmadoPorFaseLiberacion(id_carga));
		
		
		
	}
	
	
	
	
	
	
}
