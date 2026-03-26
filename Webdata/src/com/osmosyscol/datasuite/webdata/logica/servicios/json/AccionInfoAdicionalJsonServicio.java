package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;

public class AccionInfoAdicionalJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public List<Campo> obtenerListaCamposInfoAdicionalDisponiblesPorAccion(Integer id_accion) {
		P.println(id_accion);
		return CampoServicio.getInstance().obtenerCamposInfoAdicionalDisponiblesPorAccion(id_accion);
	}

	public List<Campo> obtenerCamposInfoAdicionalPorAccion( Integer id_accion ) {
		P.println(id_accion);
		return CampoServicio.getInstance().obtenerCamposInfoAdicionalPorAccion(id_accion);
	}

	public Boolean insertarAccionCampoInfoAdicional(Integer id_campo, Integer id_accion) {
		P.println(id_campo, id_accion);
		return CampoServicio.getInstance().insertarAccionCampoInfoAdicional(id_campo, id_accion);
	}

	public Boolean borrarAccionCampoInfoAdicional(Integer id_campo,	Integer id_accion) {
		P.println(id_campo, id_accion);
		return CampoServicio.getInstance().borrarAccionCampoInfoAdicional(id_campo, id_accion);
	}
	
	public Campo obtenerCampoInfoAdicional(Integer id_campo) {
		P.println(id_campo);
		return CampoServicio.getInstance().obtenerCampo(id_campo);
	}

	public Boolean guardarCamposAdicionales(Integer id_carga, Integer id_campo, String valor) {
		P.println(id_carga, id_campo, valor);
		return CampoServicio.getInstance().guardarCamposAdicionales(id_carga, id_campo, valor);
	}
	
	public List<ValorLista> obtenerListaValores(Integer id_lista_valores){
		return ListaValoresServicio.getInstance().obtenerValoresLV(id_lista_valores);
	}
	
}
