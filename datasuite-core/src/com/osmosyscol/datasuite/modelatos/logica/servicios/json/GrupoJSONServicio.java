package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.GrupoServicio;

public class GrupoJSONServicio {

	public Grupo obtenerGrupoPorNombre(String nombreGrupo, Integer id_modelo){
		
		Grupo grupo = GrupoServicio.getInstance().obtenerGrupoPorNombre(nombreGrupo, id_modelo);
		return grupo;
	
		
	}

}
