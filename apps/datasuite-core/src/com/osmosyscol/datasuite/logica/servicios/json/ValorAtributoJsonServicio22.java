package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.logica.servicios.ValorAtributoServicio;

public class ValorAtributoJsonServicio22 implements JsonService{

	@SuppressWarnings("unused")
	private Session session;

	public void setSession(Session session) {
		this.session = session;

	}
	
	public static ValorAtributo[] obtenerValorAtributo(ValorAtributo valorAtributo){
		List<ValorAtributo> lista = ValorAtributoServicio.getInstance().obtenerValorAtributo(valorAtributo);
		ValorAtributo[] auxArr = new ValorAtributo[lista.size()];
		return lista.toArray(auxArr);
	}
	
	public static ValorAtributo[] obtenerValorAtributoPor(Integer id_administrativo){
		List<ValorAtributo> lista = ValorAtributoServicio.getInstance().obtenerValorAtributoPor(id_administrativo);
		ValorAtributo[] auxArr = new ValorAtributo[lista.size()];
		return lista.toArray(auxArr);
	}
	
	public static Boolean crearListaValorAtributo(List<ValorAtributo> ListAtributo){
		return  ValorAtributoServicio.getInstance().actualizarListaValorAtributo(ListAtributo);
	}
	
	
}
