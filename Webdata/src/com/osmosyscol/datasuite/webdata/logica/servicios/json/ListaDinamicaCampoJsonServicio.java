package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;

public class ListaDinamicaCampoJsonServicio implements JsonService{

	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<ValorLista> obtenerValoresListaDinamica(Integer id_lista_dinamica, String parametro_json) {
		
		return ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamica(id_lista_dinamica, parametro_json, session);
	}
	
	public List<ValorLista> obtenerValoresListaDinamicaCampos(Integer id_lista_dinamica, String[] parametros_json) {
		
		return ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamicaCampos(id_lista_dinamica, parametros_json, session);
	}
	
	public List<ValorLista> obtenerValoresListaDinamicaParams(Integer id_lista_dinamica, String parametros) {
		return ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamicaParams(id_lista_dinamica, parametros, session);
	}
	
	public List<ValorLista> obtenerValoresLV(Integer id_listavalores) {
		return ListaValoresServicio.getInstance().obtenerValoresLV(id_listavalores);
	}

}
