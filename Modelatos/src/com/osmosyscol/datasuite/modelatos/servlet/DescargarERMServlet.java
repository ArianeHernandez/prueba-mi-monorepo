package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Relacion;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.servlet.InitApp;

public class DescargarERMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Double ESCALA_X = 1.5;
	private static final Double ESCALA_Y = 1.7;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer id_modelo = Integer.parseInt(request.getParameter("id_modelo"));
		String xml = request.getParameter("xml");
		String onscreen = request.getParameter("onscreen");
		String grupo = request.getParameter("id_grupo");
		
		Integer id_grupo = 0;
		
		if (grupo != null){
			id_grupo = Integer.parseInt(grupo, 10);
		}
		
		List<Estructura> estructuras = new ArrayList<Estructura>();
		
		if (id_grupo == 0){
			estructuras = EstructuraServicio.getInstance().obtenerEstructurasAccesiblesPorPersona(id_modelo, null, null);
		}else {
			estructuras = EstructuraServicio.getInstance().obtenerEstructurasPorGrupo(id_grupo);
		}
		
		Integer i = 0;
		
		for (Estructura estructura : estructuras) {
			estructura.setId_xml(i);
			i++;
		}
		
		List<Relacion> relaciones = new ArrayList<Relacion>();
		
		i = 0;
		
		for (Estructura estructura : estructuras) {
			if (estructura.getId_estructuras_relacionadas() != null && !estructura.getId_estructuras_relacionadas().isEmpty()){
				for (Integer idest : estructura.getId_estructuras_relacionadas()){
					Integer idXml = obtenerIdXMLEstructura(idest, estructuras);
					if (idXml != null){
						Relacion relacion = new Relacion();
						relacion.setId(i);
						relacion.setSource(estructura.getId_xml());
						relacion.setIdEstructuraSource(estructura.getId_estructura());
						relacion.setTarget(idXml);
						relacion.setIdEstructuraTarget(idest);
						relacion.setName("FK_" + estructura.getNombreFisico() + "_" + i);
						relaciones.add(relacion);
						i++;	
					}
				}
			}
		}
		
		List<Campo> campos = new ArrayList<Campo>();
		
		for (Estructura estructura : estructuras){
			// Escala de posición para el diagrama
			Integer xpos = (int) Math.ceil(estructura.getXpos() * ESCALA_X);
			Integer ypos = (int) Math.ceil(estructura.getYpos() * ESCALA_Y);
			estructura.setXpos(xpos);
			estructura.setYpos(ypos);
			for (Campo campo : estructura.getCampos()){
				campos.add(campo);
			}
		}
		
		Campo campoID = new Campo();
		campoID.setId_campo(0);
		campoID.setId_estructura(0);
		campoID.setDescripcion("Id de la tabla");
		campoID.setNombre("ID");
		
		campos.add(campoID);
		
		i = 0;
		
		for (Campo campo : campos) {
			campo.setId_xml(i);
			i++;
		}
		
		List<Map<String, Integer>> columnaCampo = obtenerIdColumnas(estructuras, campos);
		
		ServletOutputStream op = response.getOutputStream();
		
		try {
			
			Date fecha = new Date();
			
			DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
			dfactory.setNamespaceAware(true);
			StringBuffer stringFecha = JavaToXML.exe("Fecha", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fecha), true);
			StringBuffer stringEstructura = JavaToXML.exe("Estructuras", estructuras, true);
			StringBuffer stringCampos = JavaToXML.exe("Campos", campos, true);
			StringBuffer stringRelaciones = JavaToXML.exe("Relaciones", relaciones, true);
			StringBuffer stringColumnaCampo = JavaToXML.exe("ColumnaCampo", columnaCampo, true);
			StringBuffer stringWordid = JavaToXML.exe("IdWordId", i-1, true);
			String cadena = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
					        "<OSM-ERM>" + 
					        stringFecha + 
					        stringEstructura + 
					        stringCampos + 
					        stringRelaciones + 
					        stringColumnaCampo +
					        stringWordid +
					        "</OSM-ERM>";
			
			String filename = "Modelo.erm";
			File f = new File(filename);
			
			if (onscreen == null){
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
			}else {
				response.setContentType("text/xml");	
			}
			
			if (xml != null && xml.equals("true")){
				byte[] bbuf = new byte[10000];
				int length = 0;
				
				bbuf = cadena.getBytes();
				length = cadena.length();
				
				op.write(bbuf, 0, length);
			}else{
				Document doc = dfactory.newDocumentBuilder().parse(new ByteArrayInputStream(cadena.getBytes()));
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				
				String path_template = InitApp.class.getResource("/").getPath() + "com/osmosyscol/datasuite/modelatos/servlet/erm.xsl";
				
				Transformer transformer = tFactory.newTransformer(new StreamSource(path_template));
				
				transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING,"ISO8859_1");

				transformer.transform(new DOMSource(doc), new StreamResult(op));	
			}
			
			op.flush();
			op.close();
			
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
	}
	
	private Integer obtenerIdXMLEstructura(Integer idEstructura, List<Estructura> estructuras){
		for (Estructura estructura : estructuras) {
			if (idEstructura.equals(estructura.getId_estructura()) ){
				return estructura.getId_xml();
			}
		}
		return null;
	}
	
	private List<Map<String, Integer>> obtenerIdColumnas(List<Estructura> estructuras, List<Campo> campos){
		List<Map<String, Integer>> lista = new ArrayList<Map<String, Integer>>();
		Integer i = 0;
		for(Estructura estructura : estructuras){
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("idEstructura", estructura.getId_estructura());
			map.put("idColumn", i);
			lista.add(map);
			i++;
			for (Campo campo : campos){
				if (campo.getId_estructura().equals(estructura.getId_estructura())){
					map = new HashMap<String, Integer>();
					map.put("idCampo", campo.getId_campo());
					map.put("idColumn", i);
					lista.add(map);
					i++;
				}
			}
		}
		return lista;
	}

	
}