package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaNaturalezaDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaNaturalezaAcc {
	
	private static ApVistaNaturalezaAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaNaturalezaAcc() {
		
	}
	
	public static ApVistaNaturalezaAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaNaturalezaAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA NATURALEZA$";	
		
		List<ApVistaNaturalezaDto> listaGrupos = new ArrayList<ApVistaNaturalezaDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaNaturalezaDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA NATURALEZA. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA NATURALEZA$ WHERE $APVISTA NATURALEZA.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaNaturalezaDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaNaturalezaDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA NATURALEZA por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public ApVistaNaturalezaDto getPorLista(String lista) {

		List<ApVistaNaturalezaDto> listaGrupos = new ArrayList<ApVistaNaturalezaDto>();
		String querySelect = "SELECT * FROM $APVISTA NATURALEZA$ WHERE $APVISTA NATURALEZA.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaNaturalezaDto.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA NATURALEZA. por lista. Error: ", e);
		}
		
		if(listaGrupos.isEmpty()){
			return null;
		}
		
		return listaGrupos.get(0);
	}
	
	
	public String getIdVistaPorLista(String lista) throws Exception{

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA NATURALEZA.ID VISTA$ FROM $APVISTA NATURALEZA$ WHERE $APVISTA NATURALEZA.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA NATURALEZA. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
	public String getNombrePorLista(String lista) {

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA NATURALEZA.NOMBRE$ FROM $APVISTA NATURALEZA$ WHERE $APVISTA NATURALEZA.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA NATURALEZA. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
}
