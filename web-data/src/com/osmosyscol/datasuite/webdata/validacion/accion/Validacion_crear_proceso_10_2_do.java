package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.logica.dto.RevisionRevisores;
import com.osmosyscol.datasuite.logica.dto.Revisor;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.PreparadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.RevisionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.RevisorServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_crear_proceso_10_2_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// ------

		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		Integer id_usuario = (Integer) request.getSession().getAttribute("id_usuario");

		CocoonUtils.osmSessionRequest(request, "id_proceso", "-1");

		Integer id_proceso = Integer.parseInt((String) request.getSession().getAttribute("var.id_proceso"));

		// ---------------------

		Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(id_proceso);
		List<Formato> formatosSinFiltrar = null;
		
		Boolean confListNeg = Boolean.parseBoolean(ParametrosInicio.getProperty("DisponibleListadoNegocios"));
		if(!confListNeg){
			formatosSinFiltrar = FormatoServicio.getInstance().obtenerFormatosPorCliente(id_usuario);
		}else{
			formatosSinFiltrar = FormatoServicio.getInstance().obtenerFormatosPorNegocio(id_negocio);
		}
		
		//Segun el grupoformato que tenga el proceso, se filtran los formatos
		List<Formato> formatos = new ArrayList<Formato>();
		
		if(proceso.getId_grupoformato()!=null && formatosSinFiltrar!=null){
			for (Formato formato : formatosSinFiltrar) {
				if(formato.getId_grupoformato()!=null && formato.getId_grupoformato().equals(proceso.getId_grupoformato())){
					formatos.add(formato);
				}
			}
		}

		List<Revision> revisiones = RevisionServicio.getInstance().obtenerRevisiones(id_proceso);
		List<Preparador> preparadores = PreparadorServicio.getInstance().obtenerPreparadoresPorProceso(id_proceso);
		List<Liberador> liberadores = LiberadorServicio.getInstance().obtenerLiberadoresPorProceso(id_proceso);

		Integer id_tipo_proceso = proceso.getId_tipo_proceso();
		
		List<Preparador> preparadores_usuario = PreparadorServicio.getInstance().obtenerPreparadores(id_usuario, id_tipo_proceso);
		List<Liberador> liberadores_usuario = LiberadorServicio.getInstance().obtenerLiberadores(id_usuario, id_tipo_proceso);
		List<Revisor> revisores_usuario = RevisorServicio.getInstance().obtenerRevisores(id_usuario, id_tipo_proceso);
		TipoProceso tipoProceso = ProcesoServicio.getInstance().obtenerTipoProcesoPorIdTipoProceso(id_tipo_proceso);
		
		Boolean permiteRevisorMultiplesInstancias = ProcesoServicio.getInstance().permiteRevisorMultiplesInstancias(id_tipo_proceso);

		List<RevisionRevisores> listaRevisionRevisores = new ArrayList<RevisionRevisores>();

		RevisionRevisores revRevAux;
		if (revisiones != null) {
			for (Revision revision : revisiones) {
				
				revRevAux = new RevisionRevisores();
				revRevAux.setId_revision(revision.getId_revision());
				revRevAux.setRevisores(RevisorServicio.getInstance().obtenerRevisoresPorRevision(revision.getId_revision()));
				
				listaRevisionRevisores.add(revRevAux);
				
			}
		}

		// ---------------------

		request.setAttribute("proceso", proceso);
		request.setAttribute("formatos", formatos);

		request.setAttribute("revisiones", revisiones);
		request.setAttribute("preparadores", preparadores);
		request.setAttribute("liberadores", liberadores);

		request.setAttribute("preparadores_usuario", preparadores_usuario);
		request.setAttribute("liberadores_usuario", liberadores_usuario);
		request.setAttribute("revisores_usuario", revisores_usuario);

		request.setAttribute("listaRevisionRevisores", listaRevisionRevisores);
		
		request.setAttribute("tipoProceso", tipoProceso);
		
		request.setAttribute("permiteRevisorMultiplesInstancias", permiteRevisorMultiplesInstancias);

		// ---------------------

		return resultadoValidacion;
	}

}
