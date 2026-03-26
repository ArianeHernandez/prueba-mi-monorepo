package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.logica.servicios.ValorAtributoServicio;

public class ValorAtributoJsonServicio implements JsonService{

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
	
	@SuppressWarnings("unchecked")
	public static Boolean actualizarListaValorAtributo(String json){
		Gson gson = new GsonBuilder().create();
		List<ValorAtributo> ListAtributo= (List<ValorAtributo>)gson.fromJson(json, ArrayList.class);
		return ValorAtributoServicio.getInstance().actualizarListaValorAtributo(ListAtributo);
	}
	
	public static Boolean crearValorAtributo(ValorAtributo valorAtributo){
		return ValorAtributoServicio.getInstance().crearValorAtributo(valorAtributo) != null;
	}
	
	public static Boolean eliminarValorAtributoAdminst(ValorAtributo valorAtributo){
		return ValorAtributoServicio.getInstance().eliminarValorAtributo(valorAtributo);
	}
	
}
