package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaGruposDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSeguridadTipoDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaSeguridadTipoAcc {
	
	private static ApVistaSeguridadTipoAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaSeguridadTipoAcc() {
		
	}
	
	public static ApVistaSeguridadTipoAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaSeguridadTipoAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA SEGURIDAD TIPO$";	
		
		List<ApVistaSeguridadTipoDto> listaSeguridadTipo = new ArrayList<ApVistaSeguridadTipoDto>();
		
		try {
			listaSeguridadTipo = DS_SqlUtils.queryForList(ApVistaSeguridadTipoDto.class, querySelect);
			entrega = gson.toJson(listaSeguridadTipo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SEGURIDAD TIPO. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA SEGURIDAD TIPO$ WHERE $APVISTA SEGURIDAD TIPO.ID$=$I("+id+")$";
		
		ApVistaSeguridadTipoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaSeguridadTipoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SEGURIDAD TIPO por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorSeguridadTipo(String seguridadTipo){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA SEGURIDAD TIPO$ WHERE $APVISTA SEGURIDAD TIPO.SEGURIDAD TIPO$=$S("+seguridadTipo+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaSeguridadTipoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaSeguridadTipoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SEGURIDAD TIPO por SEGURIDAD TIPO. Error: ", e);
		}
		
		return entrega;
	}

}
