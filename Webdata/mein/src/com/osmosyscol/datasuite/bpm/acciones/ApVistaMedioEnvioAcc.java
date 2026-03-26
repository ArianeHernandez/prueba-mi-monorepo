package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMedioEnvioDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaMedioEnvioAcc {
	
	private static ApVistaMedioEnvioAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaMedioEnvioAcc() {
		
	}
	
	public static ApVistaMedioEnvioAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaMedioEnvioAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA MEDIO ENVIO$";	
		
		List<ApVistaMedioEnvioDto> listaMedioEnvio = new ArrayList<ApVistaMedioEnvioDto>();
		
		try {
			listaMedioEnvio = DS_SqlUtils.queryForList(ApVistaMedioEnvioDto.class, querySelect);
			entrega = gson.toJson(listaMedioEnvio);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MEDIO ENVIO. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA MEDIO ENVIO$ WHERE $APVISTA MEDIO ENVIO.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaMedioEnvioDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaMedioEnvioDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MEDIO ENVIO por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getPorMedioEnvio(String MedioEnvio){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA MEDIO ENVIO$ WHERE $APVISTA MEDIO ENVIO.MEDIO ENVIO$=$S("+MedioEnvio+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaMedioEnvioDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaMedioEnvioDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MEDIO ENVIO por MEDIO ENVIO. Error: ", e);
		}
		
		return entrega;
	}

}
