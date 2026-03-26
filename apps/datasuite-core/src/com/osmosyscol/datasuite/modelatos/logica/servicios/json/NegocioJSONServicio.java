package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;

public class NegocioJSONServicio {
	
	public static Negocio guardarNegocio(String activo, String cod_negocio, String descripcion, Integer id_modelo, Integer id_negocio, String nombre){
		
		Negocio negocio = new Negocio();
		negocio.setActivo(activo);
		negocio.setCod_negocio(cod_negocio);
		negocio.setDescripcion(descripcion);
		negocio.setId_modelo(id_modelo);
		negocio.setId_negocio(id_negocio);
		negocio.setNombre(nombre);
		
		return NegocioServicio.getInstance().guardarNegocio(negocio);
	}
	
	public Boolean eliminarNegocio (Integer id_negocio){
		return NegocioServicio.getInstance().eliminarNegocio(id_negocio);
	}
	
	public Boolean actualizarActivoNegocio(Integer id_negocio, String activo) {
		return NegocioServicio.getInstance().actualizarActivoNegocio(id_negocio, activo);
	}

	public Negocio obtenerNegocioPorCodigo(String cod_negocio){
		return NegocioServicio.getInstance().obtenerNegocioPorCodigo(cod_negocio);
	}
	
}
