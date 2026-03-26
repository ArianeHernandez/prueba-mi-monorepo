package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;

public interface CreaDatosDao {

	public boolean existeTabla(String idTabla);

	public boolean crearTabla(String idTabla);

	public boolean crearTablaLista(String idTabla, TipoCampo tipocampo);

	public boolean existeCampo(String idTabla, String idCampo);

	public Boolean crearCampo(Campo campo, TipoCampo tipoCampo);

	public Boolean eliminarCampo(Campo campo);

	public Boolean actualizarCampo(Campo campo, TipoCampo tipoCampo, List<Campo> camposAnteriores);

	public Boolean crearVista(String id_estructura, List<Campo> campos);

	public void eliminarVista(Integer id_estructura);

	public Boolean crearVistaDummy(Integer id_estructura, List<Campo> camposlist);

	public void eliminarPKUnique(Integer id_estructura);

	public void crearPKUnique(List<Campo> camposIdNuevos, Integer id_estructura);
}
