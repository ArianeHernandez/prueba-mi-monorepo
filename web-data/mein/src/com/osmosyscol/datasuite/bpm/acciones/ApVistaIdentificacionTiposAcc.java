package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaIdentificacionTiposDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaIdentificacionTiposAcc {
	
	private static ApVistaIdentificacionTiposAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaIdentificacionTiposAcc() {
		
	}
	
	public static ApVistaIdentificacionTiposAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaIdentificacionTiposAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA IDENTIF TIPOS$";	
		
		List<ApVistaIdentificacionTiposDto> listaIdentificacionTipos = new ArrayList<ApVistaIdentificacionTiposDto>();
		
		try {
			listaIdentificacionTipos = DS_SqlUtils.queryForList(ApVistaIdentificacionTiposDto.class, querySelect);
			entrega = gson.toJson(listaIdentificacionTipos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA IDENTIF TIPOS. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA IDENTIF TIPOS$ WHERE $APVISTA IDENTIF TIPOS.ID$=$I("+id+")$";
		
		ApVistaIdentificacionTiposDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaIdentificacionTiposDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA IDENTIF TIPOS por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorIdentificacionTipos(String IdentificacionTipo){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA IDENTIF TIPOS$ WHERE $APVISTA IDENTIF TIPOS.IDENTIFICACION TIPO$=$S("+IdentificacionTipo+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaIdentificacionTiposDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaIdentificacionTiposDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA IDENTIF TIPOS por IdentificacionTipo. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getNombrePorIdVista(String idVista){
		String entrega = "";
		String querySelect = "SELECT $APVISTA IDENTIF TIPOS.IDENTIFICACION TIPO$ FROM $APVISTA IDENTIF TIPOS$ WHERE $APVISTA IDENTIF TIPOS.ID VISTA$=$I("+idVista+")$";
		
		ApVistaIdentificacionTiposDto grupo = null;
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA IDENTIF TIPOS por IdentificacionTipo. Error: ", e);
		}
		
		return entrega;
	}

}
