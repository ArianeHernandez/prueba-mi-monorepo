package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaGruposDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistasGruposAcc {
	
	private static ApVistasGruposAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistasGruposAcc() {
		
	}
	
	public static ApVistasGruposAcc getInstance() {
		if(instance == null) {
			instance = new ApVistasGruposAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA GRUPOS$";	
		
		List<ApVistaGruposDto> listaGrupos = new ArrayList<ApVistaGruposDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaGruposDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPOS. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA GRUPOS$ WHERE $APVISTA GRUPOS.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaGruposDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaGruposDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPOS por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public String getListaGruposPorParametro(String parametro, String valor) throws Exception{

		String entrega = "{}";
		String querySelect = "";
		
		if(parametro.equalsIgnoreCase("vista") || parametro.equalsIgnoreCase("nombre") || parametro.equalsIgnoreCase("grupo")){
			querySelect = "SELECT * FROM $APVISTA GRUPOS$ WHERE $APVISTA GRUPOS."+parametro.toUpperCase()+"$=$S("+valor+")$";
		} else if (parametro.equalsIgnoreCase("id") || parametro.equalsIgnoreCase("id vista")) {
			querySelect = "SELECT * FROM $APVISTA GRUPOS$ WHERE $APVISTA GRUPOS."+parametro.toUpperCase()+"$=$I("+valor+")$";
		} else {
			
			System.out.println("Parametro invalido. " + parametro);
			throw new Exception("Parámetro inválido" + parametro);
		}
		
		System.out.println("CONSULTA: "+querySelect);
		
		List<ApVistaGruposDto> listaGrupos = new ArrayList<ApVistaGruposDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaGruposDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPOS por parametro. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	
	public String getNombreForIdVistaAndGrupo(String idVista, String grupo){
		String entrega = "{}";
		String querySelect = "SELECT $APVISTA GRUPOS.NOMBRE$ FROM $APVISTA GRUPOS$ WHERE $APVISTA GRUPOS.ID VISTA$=$I("
				+ idVista +")$ AND $APVISTA GRUPOS.GRUPO$=$S(" + grupo +")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA GRUPOS por idVista y Grupo. Error: ", e);
		}
		
		return entrega;
	}

}
