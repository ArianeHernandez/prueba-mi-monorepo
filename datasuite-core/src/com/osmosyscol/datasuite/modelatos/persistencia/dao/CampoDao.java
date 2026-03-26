package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;

public interface CampoDao {

	public List<Campo> obtenerCamposPorEstructura(Integer id_estructura);

	public List<Campo> obtenerCamposPorEstructuraOrdenId(Integer id_estructura); 
	
	public boolean guardarCampo(Campo campo);

	public boolean eliminarCampo(Campo campo);
	
	public TipoCampo obtenerTipoCampo(Campo campo);

	public boolean campoTieneListaValores(Integer id_campo);

	public Campo obtenerCampo(Integer id_campo);

	public List<Campo> obtenerCampos();

	public Boolean actualizarOrdenCampo(Campo campo);

	public List<Campo> obtenerCamposLlavePrimaria(Integer id_estructura);

	public List<Campo> obtenerCamposInfoAdicionalDisponiblesPorAccion(Integer id_accion);

	public List<Campo> obtenerCamposInfoAdicionalPorAccion(Integer id_accion);

	public Boolean insertarAccionCampoInfoAdicional(Integer id_campo, Integer id_accion);

	public Boolean borrarAccionCampoInfoAdicional(Integer id_campo, Integer id_accion);
	
}
