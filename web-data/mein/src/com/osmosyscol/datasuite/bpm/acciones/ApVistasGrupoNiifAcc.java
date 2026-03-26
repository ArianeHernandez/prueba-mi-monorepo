package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaGrupoNiifDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistasGrupoNiifAcc {
	
	private static ApVistasGrupoNiifAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistasGrupoNiifAcc() {
		
	}
	
	public static ApVistasGrupoNiifAcc getInstance() {
		if(instance == null) {
			instance = new ApVistasGrupoNiifAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA GRUPO NIIF$";	
		
		List<ApVistaGrupoNiifDto> listaGrupos = new ArrayList<ApVistaGrupoNiifDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaGrupoNiifDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPO NIIF. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String queryString = "SELECT * FROM $APVISTA GRUPO NIIF$ WHERE $APVISTA GRUPO NIIF.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaGrupoNiifDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaGrupoNiifDto.class, queryString);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPO NIIF por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getIdVistaPorId(int id){
		String entrega = "";
		String queryString = "SELECT $APVISTA GRUPO NIIF.ID VISTA$ FROM $APVISTA GRUPO NIIF$ WHERE $APVISTA GRUPO NIIF.ID$=$I("+id+")$";
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, queryString);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar id_vista de APVISTA GRUPO NIIF por ID. Error: ", e);
		}
		
		return entrega;
	}

}
