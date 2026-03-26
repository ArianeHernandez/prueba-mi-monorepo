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
import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.GrupoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;

public class ImportGrupoServlet extends HttpServlet {
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
			
			String prefijo = "/Grupo_";
			String ruta = UtilsExport.copyFile(request, prefijo);
			
			File xmlDocument = null;
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			xmlDocument = new File(ruta);
			
			String mensaje = "";
			String nombreGrupo = UtilsExport.xpath(xPath, xmlDocument, "/ExportGrupo/Grupo/nombre");
			Grupo grupo = new Grupo();
			
			
			//Valida si el grupo ya existe
			if (GrupoServicio.getInstance().obtenerGrupoPorNombre(nombreGrupo, id_modelo) == null) {
				
				grupo.setNombre(nombreGrupo);
				Integer cantidadEstructuras = UtilsExport.xpathInteger(xPath, xmlDocument, "count(/ExportGrupo/ExportEstructura)");
				
				mensaje = guardarEstructuras(id_persona, id_modelo, xmlDocument, xPath, mensaje, cantidadEstructuras);
				
				if(org.apache.commons.lang.StringUtils.isBlank(mensaje)){
					
					GrupoServicio.getInstance().guardarGrupo(grupo, id_persona, id_modelo);
					Integer id_grupo = GrupoServicio.getInstance().obtenerGrupoPorNombre(nombreGrupo, id_modelo).getId_grupo();
				
					
					//Guarda los campos por Estructura
					
					for (int i = 1; i <= cantidadEstructuras; i++) {
						
						Integer cantidadCampos = UtilsExport.xpathInteger(xPath, xmlDocument, "count(//ExportEstructura[position()=" + i + "]/Campos/HashMap)");
						List<Campo> campos = new ArrayList<Campo>();
						
						String nombreEstructura = UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Estructura/nombre");
						Estructura estructura = EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nombreEstructura, id_modelo);
						
						for (int j = 1; j <= cantidadCampos; j++) {
							
							Campo campo = new Campo();
							
							campo.setOrden(UtilsExport.xpathInteger(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/orden"));
							campo.setNombre(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/nombre"));
							campo.setId_tipocampo(UtilsExport.xpathInteger(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/id_tipocampo"));
							
							
							// Si requiere que la estructura relacionada exista
							String nombreEstructurarel = UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/estructurarelacionada");
							String nombreLista = UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/lista_valores");
							
							if (!org.apache.commons.lang.StringUtils.isBlank(nombreEstructurarel)) {
								
								Estructura estructurarel = EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nombreEstructurarel, id_modelo);
								
								if (estructurarel != null) {
									campo.setId_estructurarelacionada(estructurarel.getId_estructura());
									campo.setTiposeleccionado("tc_" + estructurarel.getId_estructura());
								} else
									mensaje = "La estructura Relacionada " + nombreEstructurarel + " no existe";
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
				
							campo.setObligatorio(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/obligatorio"));
							campo.setPatronvalidacion(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/patronvalidacion"));
							campo.setLlaveprimaria(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/llaveprimaria"));
							campo.setVisualizacion(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/visualizacion"));
							campo.setMultiplicidad(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/multiplicidad"));
							campo.setUnico(UtilsExport.xpath(xPath, xmlDocument, "//ExportEstructura[position()=" + i + "]/Campos/HashMap[position()=" + j + "]/unico"));
							campos.add(campo);
						}
						
						//Guardar campos
						if(org.apache.commons.lang.StringUtils.isBlank(mensaje)){
							
							GrupoServicio.getInstance().guardarGrupoEstructura(id_grupo, estructura.getId_estructura(), "guardar");
							EstructuraServicio.getInstance().guardarCampoImport(campos, estructura);
						}
					
					}
					
				}
				
			}else{
				mensaje = "El Grupo " + nombreGrupo + " ya existe ";
			}
			
			SimpleLogger.setDebug(mensaje);

			session.setAttribute("MensajeImport", mensaje);
			
			String destino = "grupos/2.5.do";
			response.resetBuffer();
			response.setStatus(302);
				
			try {
				ServletUtils.sendRedirect(request, response, destino);
				
			} catch (IOException e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
		}
	}
	
	
	//---------------------------------------------
	
	//Recorre las Estructuras y las guarda
	private String guardarEstructuras(Integer id_persona, Integer id_modelo, File xmlDocument, XPath xPath, String mensaje, Integer cantidadEstructuras) {
		List<Estructura> estructuras = new ArrayList<Estructura>();
		for (int i = 1; i <= cantidadEstructuras; i++) {
			
			Estructura estructura = new Estructura();					
			String nomEstructura = UtilsExport.xpath(xPath, xmlDocument, "/ExportGrupo/ExportEstructura[position()=" + i + "]/Estructura/nombre");
			String descripcion = UtilsExport.xpath(xPath, xmlDocument, "/ExportGrupo/ExportEstructura[position()=" + i + "]/Estructura/descripcion");
			String estado = UtilsExport.xpath(xPath, xmlDocument, "/ExportGrupo/ExportEstructura[position()=" + i + "]/Estructura/estado");
			
			estructura.setNombre(nomEstructura);
			estructura.setDescripcion(descripcion);
			estructura.setEstado(estado);
			
			estructuras.add(estructura);
			if(EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nomEstructura, id_modelo)!=null){
				mensaje += "La estructura " +nomEstructura+ " ya existe\n";
			}
			
		} 
		if(org.apache.commons.lang.StringUtils.isBlank(mensaje)){
			for (Estructura estructura : estructuras) {
				EstructuraServicio.getInstance().guardarEstructuraImport(estructura, id_persona, id_modelo);
			}
		}
		return mensaje;
	}

}
