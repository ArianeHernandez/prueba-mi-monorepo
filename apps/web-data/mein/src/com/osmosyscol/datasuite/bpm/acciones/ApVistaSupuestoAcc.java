package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSupuestoDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaSupuestoAcc {
	
	private static ApVistaSupuestoAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaSupuestoAcc() {
		
	}
	
	public static ApVistaSupuestoAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaSupuestoAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA SUPUESTO$";	
		
		List<ApVistaSupuestoDto> listaGrupos = new ArrayList<ApVistaSupuestoDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaSupuestoDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SUPUESTO. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA SUPUESTO$ WHERE $APVISTA SUPUESTO.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaSupuestoDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaSupuestoDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SUPUESTO por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public ApVistaSupuestoDto getPorLista(String lista) {

		List<ApVistaSupuestoDto> listaGrupos = new ArrayList<ApVistaSupuestoDto>();
		String querySelect = "SELECT * FROM $APVISTA SUPUESTO$ WHERE $APVISTA SUPUESTO.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaSupuestoDto.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SUPUESTO. por lista. Error: ", e);
		}
		
		if(listaGrupos.isEmpty()){
			return null;
		}
		
		return listaGrupos.get(0);
	}
	
	
	public String getIdVistaPorLista(String lista) throws Exception{

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA SUPUESTO.ID VISTA$ FROM $APVISTA SUPUESTO$ WHERE $APVISTA SUPUESTO.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SUPUESTO. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
	public String getNombrePorLista(String lista) {

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA SUPUESTO.NOMBRE$ FROM $APVISTA SUPUESTO$ WHERE $APVISTA SUPUESTO.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA SUPUESTO. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
}
