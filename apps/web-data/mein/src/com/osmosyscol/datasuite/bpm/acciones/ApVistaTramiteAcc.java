package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaTramiteDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaTramiteAcc {
	
	private static ApVistaTramiteAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaTramiteAcc() {
		
	}
	
	public static ApVistaTramiteAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaTramiteAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA TRAMITE$";	
		
		List<ApVistaTramiteDto> listaTramite = new ArrayList<ApVistaTramiteDto>();
		
		try {
			listaTramite = DS_SqlUtils.queryForList(ApVistaTramiteDto.class, querySelect);
			entrega = gson.toJson(listaTramite);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA TRAMITE. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA TRAMITE$ WHERE $APVISTA TRAMITE.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaTramiteDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaTramiteDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA TRAMITE por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorTramite(String Tramite){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA TRAMITE$ WHERE $APVISTA TRAMITE.TRAMITE$=$S("+Tramite+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaTramiteDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaTramiteDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA TRAMITE por TRAMITE. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getNombrePorCodigo(String codigo){
		String entrega = "{}";
		
		String querySelect = "SELECT $APVISTA TRAMITE.NOMBRETRAMITE$ FROM $APVISTA TRAMITE$ WHERE $APVISTA TRAMITE.CODIGO$ = $S( " + codigo +")$";
		
		String grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(String.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar NOMBRETRAMITE por CODIGO. Error: ", e);
		}
		
		return entrega;
	}

}
