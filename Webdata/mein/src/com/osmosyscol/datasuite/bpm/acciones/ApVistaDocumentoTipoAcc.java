package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaDocumentoTipoDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaDocumentoTipoAcc {
	
	private static ApVistaDocumentoTipoAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaDocumentoTipoAcc() {
		
	}
	
	public static ApVistaDocumentoTipoAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaDocumentoTipoAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA DOCUMENTO TIPO$";	
		
		List<ApVistaDocumentoTipoDto> listaDocumentoTipo = new ArrayList<ApVistaDocumentoTipoDto>();
		
		try {
			listaDocumentoTipo = DS_SqlUtils.queryForList(ApVistaDocumentoTipoDto.class, querySelect);
			entrega = gson.toJson(listaDocumentoTipo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA DOCUMENTO TIPO. Error: ", e);
		}
		
		return entrega;
	}
	
	public ApVistaDocumentoTipoDto getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA DOCUMENTO TIPO$ WHERE $APVISTA DOCUMENTO TIPO.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaDocumentoTipoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaDocumentoTipoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA DOCUMENTO TIPO por ID. Error: ", e);
		}
		
		return grupo;
	}
	
	public ApVistaDocumentoTipoDto getPorIdVista(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA DOCUMENTO TIPO$ WHERE $APVISTA DOCUMENTO TIPO.ID VISTA$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaDocumentoTipoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaDocumentoTipoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA DOCUMENTO TIPO por ID. Error: ", e);
		}
		
		return grupo;
	}
	
	public String getPorDocumentoTipo(String DocumentoTipo){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA DOCUMENTO TIPO$ WHERE $APVISTA DOCUMENTO TIPO.DOCUMENTO TIPO$=$S("+DocumentoTipo+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaDocumentoTipoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaDocumentoTipoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA DOCUMENTO TIPO por DOCUMENTO TIPO. Error: ", e);
		}
		
		return entrega;
	}

}
