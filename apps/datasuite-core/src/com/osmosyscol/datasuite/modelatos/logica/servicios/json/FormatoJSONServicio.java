package com.osmosyscol.datasuite.modelatos.logica.servicios.json;


import java.util.LinkedList;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;

public class FormatoJSONServicio implements JsonService{

	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	/**
	 * Activa el formato para que este disponible para el usuario.
	 */
	public Boolean activarFormatoUsuario(Integer id_formato, Boolean activar) {

		return FormatoServicio.getInstance().activarFormato(id_formato, activar);
	}

	public Boolean disponibleFormatoNegocio( Integer id_formato, Integer id_negocio, Boolean activo, Boolean todos) {
		return FormatoServicio.getInstance().disponibleFormatoNegocio( id_formato, id_negocio, activo, todos);
	}

	public List<Formato> obtenerFormatosProceso(Integer id_proceso){
		return FormatoServicio.getInstance().obtenerFormatosProceso(id_proceso);
	}
	
	public List<Formato> obtenerFormatosClienteActivo(Integer id_persona, Integer id_negocio){
		
		Integer id_usuario = (Integer)this.session.getAttribute("id_usuario");
		
		Object id = session.getAttribute("var.id_usuario");
		if(id_usuario==null && id != null){
			id_usuario = Integer.valueOf(id.toString());
		}
		
		
		return FormatoServicio.getInstance().obtenerFormatosClienteActivo(id_persona, id_negocio, null, id_usuario);
	}

	public List<Formato> obtenerFormatosPorGrupoFormato(Integer id_grupoformato) {
		return FormatoServicio.getInstance().obtenerFormatosPorGrupoFormato(id_grupoformato);
	}
	
	public List<Formato> obtenerFormatosPorFamilia(Integer idFormatoPadre) {
		return FormatoServicio.getInstance().obtenerSubFormatosPorFamilia(idFormatoPadre);
	}
	
	public List<Integer> obtenerIdsFormatosPorFamilia(Integer id_grupoformato) {
		
		List<Integer> idsFormatos = new LinkedList<Integer>();
		
		List<Formato> formatos = obtenerFormatosPorFamilia(id_grupoformato);
		for (Formato formato : formatos) {
			idsFormatos.add(formato.getId_formato());
		}
		
		return idsFormatos;
	}
	
	public List<FormatoCampo> obtenerFormatoCampoBandejaEntrada (Integer id_formato_salida) {
		return FormatoServicio.getInstance().obtenerFormatoCampoBandejaEntrada(id_formato_salida);
	}
	
	public Integer obtenerFormatoSalidaPorProceso () {
		String id_proceso_admin = (String)session.getAttribute("var.id_proceso_admin");
		return FormatoServicio.getInstance().obtenerFormatoSalidaPorProceso(Integer.parseInt(id_proceso_admin));
	}
	
	public Formato obtenerFormatoPorId (Integer id_formato) {
		return FormatoServicio.getInstance().obtenerFormato(id_formato);
	}
			
}
