package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Atributo;

public interface AtributoDao {

	public Integer crearAtributo(Atributo atributo);

	public Boolean actualizarAtributo(Atributo atributo);

	public Boolean eliminarAtributo(Integer id_atributo);

	public Atributo obtenerAtributo(Integer id_atributo);
	
	public List<Atributo> obtenerAtributosUsuario(Integer id_usuario);
	
	public Integer obtenerIdSiguiente();
	
}
