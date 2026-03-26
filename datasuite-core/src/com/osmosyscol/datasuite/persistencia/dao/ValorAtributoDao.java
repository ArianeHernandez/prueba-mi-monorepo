package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.ValorAtributo;

public interface ValorAtributoDao {

	public Integer crearValorAtributo(ValorAtributo valorAtributo);

	public Boolean actualizarValorAtributo(ValorAtributo valorAtributo);

	public Boolean eliminarValorAtributo(ValorAtributo valorAtributo);
	
	public Boolean eliminarValorAtributosAdministrativo(Integer id_administrativo);
	
	public Boolean eliminarTodoValoresAtributo(Integer id_atributo);

	public List<ValorAtributo> obtenerValorAtributo(ValorAtributo valorAtributo);
	
	public Integer obtenerIdSiguiente();
	
}
