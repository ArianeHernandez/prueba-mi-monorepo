package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.utils.ValidacionUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_liberacion_15_3_do implements ValidacionAccion{
	
	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();
		
		// define la carga en sesion

		if (request.getParameter("id_carga") != null){
			session.setAttribute("var.id_carga", request.getParameter("id_carga"));
		} 
		
		if(session.getAttribute("var.id_carga") == null){
			resultadoValidacion.setPermitido(false);
			return resultadoValidacion;
		}
		
		// ---------------
		
		CocoonUtils.osmSessionRequest(request, "id_formato_entrada");
		Integer id_formato_entrada = ValidacionUtils.getSessionValue(request, "id_formato_entrada", Integer.class);;
		
		Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato_entrada);
		
		if (formato != null && formato.getIdformato_salida() != null) {
			session.setAttribute("var.id_formato_salida", formato.getIdformato_salida());					
		}
		
		Integer id_carga = Integer.parseInt(String.valueOf(session.getAttribute("var.id_carga")));
		
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		
		if (carga != null && carga.getId_proceso() != null) {
			session.setAttribute("var.id_proceso", carga.getId_proceso());
		}
		// ------
		
		
		session.setAttribute("var.id_carga_detalle", id_carga);
		
		Boolean confListNeg = Boolean.parseBoolean(ParametrosInicio.getProperty("DisponibleListadoNegocios"));
		List<Negocio> negocios = null;
		
		Integer id_proceso = ValidacionUtils.getSessionValue(request, "id_proceso", Integer.class);

		if(!confListNeg){

			Integer id_usuario = (Integer) session.getAttribute("id_usuario");
			negocios = NegocioServicio.getInstance().obtenerNegociosPorUsuarioFormato(id_usuario, id_formato_entrada);
			
			if(negocios!=null && negocios.size()>0){
				
				Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(id_proceso);
				
				List<Negocio> negociosFiltrados = new ArrayList<Negocio>();
				
				List<Negocio> negociosPorTipoProceso = NegocioServicio.getInstance().obtenerListadoNegociosPorTipoProceso(proceso.getId_tipo_proceso());
				
				if(negociosPorTipoProceso!=null){
					
					for (Negocio negocioac : negocios) {
						for (Negocio negocio : negociosPorTipoProceso) {

							if (negocioac.getId_negocio().intValue() == negocio.getId_negocio()) {
								negociosFiltrados.add(negocio);
							}

						}

					}
				}
				
				negocios = negociosFiltrados;
				
			}
			
		}

		// ---------------------

		request.setAttribute("negocios_liberador", negocios);

		// ---------------------

		return resultadoValidacion;
	}


}
