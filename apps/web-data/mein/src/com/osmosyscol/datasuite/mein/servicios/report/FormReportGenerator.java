package com.osmosyscol.datasuite.mein.servicios.report;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.logica.dto.Nodo;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class FormReportGenerator {

	private static JasperReport report = null;
	private static FormReportGenerator formReportGenerator;

	private FormReportGenerator() {
	}

	public static FormReportGenerator getInstance() {
		if (formReportGenerator == null) {
			formReportGenerator = new FormReportGenerator();
			if (report == null) compilarReporte();
		}
		return formReportGenerator;
	}
	
	public static void compilarReporte() {
		try {
			JasperDesign design = JRXmlLoader.load(new File(ContextInfo.getInstance().getDiskPathForResource("componentes/reporte/DetalleFormulario.jrxml")));
	    	report = JasperCompileManager.compileReport(design);
		} catch (Exception e) {
			SimpleLogger.setError("FormReportGenerator.compilarReporte: Ocurrio un error en la compilacion del reporte", e);
			report = null;
		}
	}
	
	public File generateReport(Integer id_carga, Integer id_formato_salida) {
		try {
			JRMapArrayDataSource dataSource = new JRMapArrayDataSource(
    			   new Object[]{ getDataSources(id_carga, id_formato_salida) });
           
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, getParameters(id_formato_salida), dataSource);
 
			String ruta = ParametrosInicio.getProperty("file.carpeta") + "/file_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + "_" + Math.round(1000d * Math.random()) + ".tmp";

			JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
           	File f = new File(ruta);
			
			return f;

       } catch (Exception e) {
    	   	SimpleLogger.setError("FormReportGenerator.generateReport: Ocurrio un error", e);
    	   	return null;
       }
   }
	
	public Map<String, Object> getParameters(Integer id_formato_salida) {
	       Map<String,Object> parameters = new HashMap<>();
	       
	       Formato formatoSalida = FormatoServicio.getInstance().obtenerFormato(id_formato_salida);
	       
	       parameters.put("LOGO_SSOC", ContextInfo.getInstance().getDiskPathForResource("resources/images/back/logo_compuesto.png"));
	       parameters.put("P_TITULO_FORMULARIO", formatoSalida.getNombre());
	 
	       return parameters;
	   }
	   public Map<String, Object> getDataSources(Integer id_carga, Integer id_formato_salida) {
	       Map<String,Object> dataSources = new HashMap<>();
	 
	       String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga);
			
	       List<Nodo> nodosEstructura = null;
	       
	       if( Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
	    	   nodosEstructura = SolicitudNearSociedadReport.getInstance().obtenerDetalleCarga(id_carga);
	       } else {
	    	   nodosEstructura = CargaServicio.getInstance().obtenerDetalleCargaPorFormato(id_carga, id_formato_salida);	    	   
	       }
	       
	       
	       JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nodosEstructura, false);
	       dataSources.put("structureDataSource", dataSource);
	 
	       return dataSources;
	   }
	
}
