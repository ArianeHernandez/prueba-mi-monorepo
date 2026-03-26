package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMacrosectorDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaMacrosectorAcc {
	
	private static ApVistaMacrosectorAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaMacrosectorAcc() {
		
	}
	
	public static ApVistaMacrosectorAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaMacrosectorAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA MACROSECTOR$";	
		
		List<ApVistaMacrosectorDto> listaGrupos = new ArrayList<ApVistaMacrosectorDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaMacrosectorDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MACROSECTOR. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA MACROSECTOR$ WHERE $APVISTA MACROSECTOR.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaMacrosectorDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaMacrosectorDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MACROSECTOR por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public ApVistaMacrosectorDto getPorLista(String lista) {

		List<ApVistaMacrosectorDto> listaGrupos = new ArrayList<ApVistaMacrosectorDto>();
		String querySelect = "SELECT * FROM $APVISTA MACROSECTOR$ WHERE $APVISTA MACROSECTOR.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaMacrosectorDto.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MACROSECTOR. por lista. Error: ", e);
		}
		
		if(listaGrupos.isEmpty()){
			return null;
		}
		
		return listaGrupos.get(0);
	}
	
	
	public String getIdVistaPorLista(String lista) throws Exception{

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA MACROSECTOR.ID VISTA$ FROM $APVISTA MACROSECTOR$ WHERE $APVISTA MACROSECTOR.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MACROSECTOR. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
	public String getNombrePorLista(String lista) {

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA MACROSECTOR.NOMBRE$ FROM $APVISTA MACROSECTOR$ WHERE $APVISTA MACROSECTOR.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA MACROSECTOR. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
}
