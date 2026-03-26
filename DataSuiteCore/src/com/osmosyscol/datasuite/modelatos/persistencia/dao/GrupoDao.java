package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;

public interface GrupoDao {

	public List<Grupo> obtenerGruposPorPersona(Integer id_persona, Integer id_negocio, Integer numero_pagina);
	public Integer totalGruposPorPersona(Integer id_persona, Integer id_negocio);

	public List<Grupo> obtenerGruposPorEstructura(Integer id_estructura);
	public Grupo obtenerGrupo(Integer id_grupo);
	public Boolean guardarGrupo(Grupo grupo, Integer id_persona, Integer id_modelo);
	public Boolean eliminarGrupo(Integer id_grupo);
	public Boolean guardarGrupoEstructura(Integer id_grupo, Integer id_estructura, String accion);
	public Grupo obtenerGrupoPorNombre(String nombreGrupo, Integer id_modelo);
	
}
