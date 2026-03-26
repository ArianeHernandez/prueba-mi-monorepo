package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.utils.ValidacionUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_carga_informacion_interactivo_1_1_1_do implements ValidacionAccion{

	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		Session session = request.getSession();
		
		ValidacionUtils.addParametersToRequest(request);
		
		Integer id_carga = (Integer)session.getAttribute("var.id_carga");
		
		Integer id_archivo_adjunto = ArchivoAdjuntoServicio.getInstance().obtenerSiguiente();
		
		String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS";
		
		
		
		return resultadoValidacion;
	}
	

	
}
