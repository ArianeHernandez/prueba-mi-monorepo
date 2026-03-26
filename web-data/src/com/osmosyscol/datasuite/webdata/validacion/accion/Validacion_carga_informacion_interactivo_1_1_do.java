package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class Validacion_carga_informacion_interactivo_1_1_do implements ValidacionAccion{

	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		Session session = request.getSession();
		
		Integer id_persona = (Integer)session.getAttribute("id_persona");
		
		Integer id_negocio = (Integer)session.getAttribute("id_negocio");
		
		String id_formato = request.getParameter("id_formato");
		id_formato = id_formato == null ? (session.getAttribute("var.id_formato") +""): id_formato;
		
		Integer idFormato = null; 
		if (StringUtils.isNotEmpty(id_formato) && StringUtils.isNumeric(id_formato)) {
			idFormato = new Integer(id_formato);
		}
		if (id_persona != null && id_formato != null && id_negocio!= null) {

			Object idproc = session.getAttribute("id_proceso");
			Integer id_proceso = null;
			if (idproc != null) {
				id_proceso = new Integer((String)idproc);
			} else {
				resultadoValidacion.setPermitido(false);
				return resultadoValidacion;
			}
			Integer id_usuario = (Integer)session.getAttribute("id_usuario");
			if(id_usuario == null){
				String tmp = (String)session.getAttribute("var.id_usuario");
				if (StringUtils.isNotEmpty(tmp) && StringUtils.isNumeric(tmp)){
					id_usuario = new Integer (tmp);
				}
			}
			Integer idCarga= CargaServicio.getInstance().crearCargaInteractiva(null, id_persona, idFormato,id_proceso, id_usuario, id_negocio, request.getRemoteHost());
			if (idCarga != null) {
				session.setAttribute("var.id_carga", idCarga);
				
				// Se agregan valores de sesion para visualizar
				List<FormatoCampo> formatoCampos = FormatoServicio.getInstance().obtenerFormatoCampoValorSesion(idFormato);
				
				if (formatoCampos != null && formatoCampos.size() > 0) {
					
					Map<String, Object> camposValorSesion = new HashMap<String, Object>();
					
					for(FormatoCampo formatoCampo: formatoCampos) {
						Object valor = session.getAttribute(formatoCampo.getValor_sesion());
						
						camposValorSesion.put("valor_sesion_" + formatoCampo.getId_formato_campo(), valor);
					}
					
					request.setAttribute("obtenerValoresSesionFormatoCampo", camposValorSesion);
				}
			}
			else {
				SimpleLogger.setError("No es posible crear la carga interactiva.");
			}
		}
		
		return resultadoValidacion;
	}
	

	
}
