package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Variable;

public interface VariableDao {

	List<Variable> obtenerVariablesPorPersona(Integer id_persona, Integer id_modelo, Integer numero_pagina);

	Integer totalVariablesPorPersona(Integer id_persona, Integer id_modelo);

	Variable obtenerVariable(Integer id_variable);
	
	Variable obtenerVariableUsuarioPorIdUsuarioIdVariable(Integer id_usuario, Integer id_variable);
	
	Variable obtenerVariablePorNombre(String nombre_variable);
	
	Boolean eliminarVariableUsuario(Integer id_usuario, Integer id_variable);

	List<Variable> obtenerVariablesPorModelo(Integer id_modelo);

	Boolean guardarVariable(Variable variable);

	Boolean guardarVariableUsuario(Integer id_usuario, Integer id_variable);
	
	List<Variable> obtenerVariablesPorModeloId(Integer id_modelo, Integer id_persona);

	Boolean eliminarValoresVariables(Integer id_usuario);

	Boolean guardarVariableValor(Variable variable, Integer id_usuario);
	
	Boolean eliminarVariableValor(Integer id_variable, Integer id_usuario);

	String obtenerValorVariable(Integer id_variable, Integer id_persona);
	
	

	List<Variable> obtenerVariablesPorPersona(Integer idPersona);

}
