package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;

public class ImportEstructuraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doImport(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	@SuppressWarnings("deprecation")
	private void doImport(HttpServletRequest request, HttpServletResponse response) throws FileUploadException {

		HttpSession session = request.getSession();
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		Integer id_modelo = (Integer) session.getAttribute("id_modelo");

		if (ServletFileUpload.isMultipartContent(request)) {

			String prefijo = "/Estructura_";
			String ruta = UtilsExport.copyFile(request, prefijo);
			
			// --------------------------------------------------------------
			
			Estructura estructura = new Estructura();
			List<Campo> campos = new ArrayList<Campo>();

			String mensaje = xmlToEstructura(id_modelo, ruta, estructura, campos);

			if(mensaje == null){
				
				EstructuraServicio.getInstance().guardarEstructura("GuardarEstructura", estructura, campos, id_persona, id_modelo);
			}
			session.setAttribute("MensajeImport", mensaje);

			String destino = "estructuras/1.4.do";
			response.resetBuffer();
			response.setStatus(302);
				
			try {
				ServletUtils.sendRedirect(request, response, destino);
			} catch (IOException e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}


		}
	}
	
	
	//--------------------------------------------------
	

	private String xmlToEstructura(Integer id_modelo, String ruta, Estructura estructura, List<Campo> campos) {
	
		File xmlDocument = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		xmlDocument = new File(ruta);
		
		String mensaje = null;
		String nombreEstructura = UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Estructura/nombre");

		if (EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nombreEstructura, id_modelo) == null) {
				
			estructura.setNombre(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Estructura/nombre"));
			estructura.setDescripcion(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Estructura/descripcion"));
			estructura.setEstado(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Estructura/estado"));
	
			// Guardar Campos de Estructura
	
			Integer cantidadCampos = UtilsExport.xpathInteger(xPath, xmlDocument, "count(/ExportEstructura/Campos/HashMap)");
			nombreEstructura = null;
			String nombreLista = null;
	
			for (int i = 1; i <= cantidadCampos; i++) {
	
				Campo campo = new Campo();
	
				campo.setOrden(UtilsExport.xpathInteger(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/orden"));
				campo.setNombre(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/nombre"));
				campo.setId_tipocampo(UtilsExport.xpathInteger(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/id_tipocampo"));
				
	
				// Si requiere que la estructura relacionada exista
				nombreEstructura = UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/estructurarelacionada");
				nombreLista = UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/lista_valores");
	
				if (!org.apache.commons.lang.StringUtils.isBlank(nombreEstructura)) {
					
					Estructura estructurarel = EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nombreEstructura, id_modelo);
					
					if (estructurarel != null) {
						campo.setId_estructurarelacionada(estructurarel.getId_estructura());
						campo.setTiposeleccionado("tc_" + estructurarel.getId_estructura());
					} else
						mensaje = "La estructura Relacionada " + nombreEstructura + " no existe";
				}
				
				// Si requiere que la lista de Valores Exista
				else if (!org.apache.commons.lang.StringUtils.isBlank(nombreLista)) {
	
					Integer id_lista_valores;
	
					if ((id_lista_valores = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre(nombreLista).getId_lista_valores()) != null) {
	
						campo.setId_lista_valores(id_lista_valores);
						campo.setTiposeleccionado("tc_" + id_lista_valores);
	
					} else
						mensaje = mensaje + "La lista de valores" + nombreLista + "no existe";
				} else
					campo.setTiposeleccionado(campo.getId_tipocampo().toString());
	
				campo.setObligatorio(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/obligatorio"));
				campo.setPatronvalidacion(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/patronvalidacion"));
				campo.setLlaveprimaria(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/llaveprimaria"));
				campo.setVisualizacion(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/visualizacion"));
				campo.setMultiplicidad(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/multiplicidad"));
				campo.setUnico(UtilsExport.xpath(xPath, xmlDocument, "/ExportEstructura/Campos/HashMap[position()=" + i + "]/unico"));
				campos.add(campo);
			}
		} else {
			mensaje = "La Estructura " + nombreEstructura + " ya existe ";
		}
		return mensaje;
	}
	
	//----------------------------------------- 
	
	

}
