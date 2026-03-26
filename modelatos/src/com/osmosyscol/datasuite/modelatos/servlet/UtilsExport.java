package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.XPath;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.InputSource;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class UtilsExport {
	
	private UtilsExport() {
	}
	
	
	//Convierte Estructura a un XML
	public static String estructuraToXml(Estructura estructura) {
		List<Map<String, Object>> lisMap = new ArrayList<Map<String,Object>>();
			
		List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura());
		
		for (Campo campo : campos) {
			
			Map<String, Object> campoMap = new HashMap<String, Object>();
			
			campoMap.put("orden", campo.getOrden());
			campoMap.put("nombre", campo.getNombre());
			campoMap.put("id_tipocampo", campo.getId_tipocampo());
			if(campo.getId_estructurarelacionada()!=null){
				String estructurarelacionada = EstructuraServicio.getInstance().obtenerEstructura(campo.getId_estructurarelacionada()).getNombre();
				campoMap.put("estructurarelacionada", estructurarelacionada);
			}
			if(campo.getId_lista_valores()!=null){
				String listaValores = ListaValoresServicio.getInstance().obtenerListaValores(campo.getId_lista_valores()).getNombre();
				campoMap.put("lista_valores", listaValores);
			}
			campoMap.put("obligatorio", campo.getObligatorio());
			campoMap.put("patronvalidacion", campo.getPatronvalidacion());
			campoMap.put("llaveprimaria", campo.getLlaveprimaria());
			campoMap.put("visualizacion", campo.getVisualizacion());
			campoMap.put("multiplicidad", campo.getMultiplicidad());
			campoMap.put("unico", campo.getUnico());
			lisMap.add(campoMap);
		}
		
		estructura.setId_estructura(null);
		estructura.setId_modelo(null);
		
		StringBuffer stringEstructura = JavaToXML.exe("Estructura", estructura);
		StringBuffer stringCampos = JavaToXML.exe("Campos", lisMap, true);
		String cadena ="<ExportEstructura>" + stringEstructura + stringCampos + "</ExportEstructura>";
		return cadena;
	}
	
	// Copia un Archivo y Retorna el path
	public static String copyFile(HttpServletRequest request, String prefijo) throws FileUploadException {
		ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
		List<?> fileItemsList = servletFileUpload.parseRequest(request);

		FileItem fileItem = null;
		String optionalFileName = "";
		Iterator<?> it = fileItemsList.iterator();

		while (it.hasNext()) {
			FileItem fileItemTemp = (FileItem) it.next();
			if (fileItemTemp.isFormField()) {
				if (fileItemTemp.getFieldName().equals("filename")) {
					optionalFileName = fileItemTemp.getString();
				}
			} else {
				fileItem = fileItemTemp;
			}
		}

		if (fileItem != null ) {

			String fileName = fileItem.getName();
			if (optionalFileName.trim().equals("")) {
				fileName = FilenameUtils.getName(fileName);
			} else {
				fileName = optionalFileName;
			}

			String dirName = ParametrosInicio.getProperty("file.carpeta") + "/FILES";
			new File(dirName).mkdirs();
			String ruta = dirName + prefijo + fileName;
			try {
				byte[] buf = new byte[1024];
				InputStream in = fileItem.getInputStream();
				FileOutputStream out = new FileOutputStream(ruta);
				int numRead = 0;
				while ((numRead = in.read(buf)) >= 0) {
					out.write(buf, 0, numRead);
				}
				out.close();

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
			return ruta;
		}
		return null;
	}
	
	//-----------------------------------------
	
	public static String xpath(XPath xPath, File xmlDocument, String expr) {
		InputSource inputSource;
		try {
			inputSource = new InputSource(new FileInputStream(xmlDocument));
			return xPath.evaluate(expr, inputSource);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	//-----------------------------------------

	public static Integer xpathInteger(XPath xPath, File xmlDocument, String expr) {
		return new Integer(xpath(xPath, xmlDocument, expr));
	}

}
